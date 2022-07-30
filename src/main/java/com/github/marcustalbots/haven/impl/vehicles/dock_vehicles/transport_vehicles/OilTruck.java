package com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockTransportVehicle;
import com.github.marcustalbots.haven.strategies.transport.OilTransportStrategy;
import com.github.marcustalbots.haven.strategies.transport.TransportStrategy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of the {@link AbstractDockTransportVehicle}, responsible for simulating the transport of
 * {@link OilContainer}-objects from the {@link Dock}, to an unspecified location.
 * @author Marcus Talbot (1041464)
 */
public final class OilTruck extends AbstractDockTransportVehicle {

    /**
     * {@link OilTransportStrategy} used to simulate transporting {@link OilContainer}-objects to an unspecified
     * location.
     */
    private static final TransportStrategy<OilTruck, OilContainer> OIL_TRANSPORT_STRATEGY = new OilTransportStrategy();

    /**
     * Lock used in combination with {@link Condition}-objects, to wait until a container is placed on the
     * {@link OilTruck}.
     */
    private final ReentrantLock lock;

    /**
     * Condition used to wait until an {@link OilContainer} has been placed on the {@link OilTruck}.
     */
    private final Condition hasOil;

    /**
     * Condition used to wait until the {@link OilContainer} has been removed from the {@link OilTruck}.
     */
    private final Condition isEmpty;

    /**
     * Currently loaded {@link OilContainer}.
     */
    private OilContainer container;

    /**
     * Simple constructor which creates a new {@link OilTruck} with the given configuration, and initialises private
     * fields.
     *
     * @param dock       {@link Dock} the {@link OilTruck} should associate with.
     * @param identifier (Preferably unique) Identifier of the {@link OilTruck}.
     */
    public OilTruck(final Dock dock, final String identifier) {
        super(dock, identifier);

        this.lock = new ReentrantLock(true);
        this.lock.lock();
        try {
            this.hasOil = this.lock.newCondition();
            this.isEmpty = this.lock.newCondition();
        } finally {
            this.lock.unlock();
        }

        this.getDock().registerOilTruck(this);
    }

    /**
     * Implementation of {@link Runnable#run()}. Responsible for the simulation of {@link OilContainer} transport, using
     * the {@link #OIL_TRANSPORT_STRATEGY}.
     */
    @Override
    public void run() {
        while (true) {
            this.lock.lock();
            try {
                while (this.container == null) {
                    if (!this.hasOil.await(30, TimeUnit.SECONDS)) return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
            OilTruck.OIL_TRANSPORT_STRATEGY.execute(this, container);
            this.container = null;
            this.lock.lock();
            try {
                this.isEmpty.signal();
            } finally {
                this.lock.unlock();
            }
            this.getDock().registerOilTruck(this);
        }
    }

    /**
     * Places a new {@link OilContainer} on the {@link OilTruck}. Uses {@link #lock} to do so safely. If the
     * {@link OilTruck} is not empty, this method will block until it is empty, or the timeout of seven seconds has
     * passed.
     *
     * @param container {@link OilContainer} that should be loaded onto the {@link OilTruck}.
     */
    public void setContainer(final OilContainer container) {
        this.lock.lock();
        try {
            if (this.container != null) {
                while (this.container != null) {
                    if (!this.isEmpty.await(7, TimeUnit.SECONDS))
                        return;
                }
            }
            this.container = container;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.hasOil.signal();
            this.lock.unlock();
        }
    }
}
