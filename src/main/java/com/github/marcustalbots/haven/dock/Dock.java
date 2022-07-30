package com.github.marcustalbots.haven.dock;

import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles.Crane;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles.Pump;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.ContainerTruck;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.OilTruck;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dock serves as the shared resource between all the producers and consumers in this program.
 *
 * @author Marcus Talbot (1041464)
 */
public final class Dock {

    /**
     * Serves as the space where {@link AbstractFreightContainer}-objects can be placed by {@link Crane}-objects, to
     * be collected by {@link ContainerTruck}-objects. Due to the nature of the {@link BlockingQueue}-interface,
     * access to this object is thread-safe.
     */
    private final BlockingQueue<AbstractFreightContainer> backlog;

    /**
     * Serves as a space where {@link OilTruck}-objects wait to be called on by {@link Pump}-objects. Due to the nature
     * of the {@link BlockingQueue}-interface, access to this object is thread-safe.
     */
    private final BlockingQueue<OilTruck> oilTrucks;

    /**
     * Used to provide an Object-Oriented approach to wait/notify. Specifically, this lock is used by the
     * {@link #placeContainer(AbstractFreightContainer)}-method, to safely wait while the {@link #backlog}-field holds
     * five {@link AbstractFreightContainer}-objects, using the {@link #notFull}-{@link Condition}. Furthermore, it is
     * used by the {@link #getContainer()}-method, to safely notify objects monitoring the {@link #notFull}-field.
     */
    private final ReentrantLock containerLock;

    /**
     * Used to offer an Object-Oriented wait/notify-interface.
     */
    private final Condition notFull;

    /**
     * Maximum capacity of the {@link #backlog}.
     */
    private final int containerCapacity;

    /**
     * Creates a new Dock with the given configuration.
     *
     * @param capacity Maximum capacity of the {@link #backlog}.
     * @author Marcus Talbot (1041464)
     */
    public Dock(final int capacity) {
        this.backlog = new PriorityBlockingQueue<>(capacity);
        this.oilTrucks = new ArrayBlockingQueue<>(3, true);
        this.containerCapacity = capacity;

        this.containerLock = new ReentrantLock(true);

        this.containerLock.lock();

        try {
            this.notFull = this.containerLock.newCondition();
        } finally {
            this.containerLock.unlock();
        }
    }

    /**
     * Places a container into the {@link #backlog}. This method is thread-safe, due to the use of
     * {@link #containerLock}.
     *
     * @param container {@link AbstractFreightContainer}-subclass, that will be placed in the {@link #backlog}.
     * @author Marcus Talbot (1041464)
     */
    public boolean placeContainer(@NotNull final AbstractFreightContainer container) {
        this.containerLock.lock();
        try {
            while (this.backlog.size() == this.containerCapacity) {
                if (!this.notFull.await(30, TimeUnit.SECONDS))
                    return false;
            }
            this.backlog.put(container);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return false;
        } finally {
            // The lock should be released in a finally-block, to make sure it is released even
            // if an Exception is raised.
            this.containerLock.unlock();
        }
        return true;
    }

    /**
     * Gets the next container in the {@link #backlog}. This method is thread-safe, due to the use of
     * {@link PriorityBlockingQueue}. If the {@link #backlog} is empty, this method will block for, at most, fifteen
     * seconds, after which it must return either a valid container, or null.
     *
     * @return {@link AbstractFreightContainer}-subclass, or null if the {@link #backlog} remains empty for more than
     * fifteen seconds.
     * @author Marcus Talbot (1041464)
     */
    public @Nullable AbstractFreightContainer getContainer() {
        try {
            return this.backlog.poll(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        } finally {
            this.containerLock.lock();
            this.notFull.signal();
            this.containerLock.unlock();
        }
    }

    /**
     * Registers that an {@link OilTruck} is currently idling.
     *
     * @param oilTruck {@link OilTruck}-object that needs to be registered to the {@link #oilTrucks}-field.
     * @author Marcus Talbot (1041464)
     */
    public boolean registerOilTruck(@NotNull final OilTruck oilTruck) {
        try {
            this.oilTrucks.put(oilTruck);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }

    /**
     * Gets an idling {@link OilTruck}. This method is thread-safe, due to the use of {@link ArrayBlockingQueue}.
     *
     * @return An {@link OilTruck} registered to the {@link #oilTrucks}-field.
     * @author Marcus Talbot (1041464)
     */
    public @Nullable OilTruck getOilTruck() {
        try {
            return this.oilTrucks.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
