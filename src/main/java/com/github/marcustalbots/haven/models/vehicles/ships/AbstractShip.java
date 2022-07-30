package com.github.marcustalbots.haven.models.vehicles.ships;

import com.github.marcustalbots.haven.models.vehicles.AbstractTransportVehicle;
import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Used to differentiate between the family of ship-classes, and other transport-vehicles. This class also introduces a
 * new field; {@link #freight}, which will be used to hold containers of type T.
 *
 * @param <T> Type of the containers that the ship will hold.
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractShip<T extends AbstractContainer> extends AbstractTransportVehicle {

    /**
     * BlockingQueue that will hold the container-objects 'on board of the ship'. A {@link BlockingQueue} was chosen to
     * ensure thread-safety.
     */
    private BlockingQueue<T> freight;

    /**
     * The maximum capacity of the internal {@link BlockingQueue}. The ship can hold no more than this amount of
     * containers.
     */
    private transient int capacity;

    /**
     * Simple constructor that will create a new object, with the given configuration.
     *
     * @param identifier (Preferably unique) Identifier of the ship.
     * @author Marcus Talbot (1041464)
     */
    public AbstractShip(final String identifier) {
        super(null, identifier);
    }

    /**
     * Constructs a new object with the given configuration.
     *
     * @param identifier (Preferably unique) Identifier of the ship.
     * @param capacity   Maximum capacity of the internal {@link BlockingQueue}.
     * @author Marcus Talbot (1041464)
     */
    @SuppressWarnings("unused")
    public AbstractShip(final String identifier, final int capacity) {
        super(null, identifier);
        this.freight = new ArrayBlockingQueue<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Sets the contents of the internal {@link BlockingQueue} to the contents of the given {@link Collection}.
     *
     * @param freight {@link Collection} of container that should be added to the internal {@link BlockingQueue}.
     * @return true if all insertions succeeded, false if any insertions failed.
     * @author Marcus Talbot (1041464)
     */
    @SuppressWarnings("unused")
    public boolean setFreight(final Collection<T> freight) {
        if (freight.size() > this.capacity) {
            throw new IllegalArgumentException("Size of Collection freight, passed to " +
                    "AbstractShip#getFreight(Collection<T>) larger than capacity of Ship's internal freight collection.");
        }
        this.freight.removeAll(freight);
        for (T e : freight) {
            if (!this.addFreight(e)) return false;
        }
        return true;
    }

    /**
     * Gets the head of the internal {@link BlockingQueue} and removes it from the queue.
     *
     * @return The head of the interal {@link BlockingQueue}.
     * @author Marcus Talbot (1041464)
     */
    public T getNext() {
        return this.freight.poll();
    }

    /**
     * Adds a single container to the internal {@link BlockingQueue}.
     *
     * @param freight Container to be added to the internal {@link BlockingQueue}.
     * @return true if the container was added to the queue, false otherwise.
     * @author Marcus Talbot (1041464)
     */
    @SuppressWarnings("unused")
    public boolean addFreight(@NotNull final T freight) {
        return this.freight.offer(freight);
    }

    /**
     * Gets a reference to the internal {@link BlockingQueue}.
     *
     * @return Reference to {@link #freight}.
     * @author Marcus Talbot (1041464)
     */
    @Contract(pure = true)
    @NotNull
    public BlockingQueue<T> getFreightQueue() {
        return this.freight;
    }

    /**
     * Sets the value of the internal {@link BlockingQueue}.
     *
     * @param freightQueue New value of {@link #freight}.
     * @author Marcus Talbot (1041464)
     */
    public void setFreightQueue(@NotNull final BlockingQueue<T> freightQueue) {
        this.freight = freightQueue;
    }

    /**
     * Gets the maximum allowed capacity of the internal {@link BlockingQueue}.
     *
     * @return The maximum capacity; {@link #capacity}.
     * @author Marcus Talbot (1041464)
     */
    @Contract(pure = true)
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the maximum allowed capacity of the internal {@link BlockingQueue}. Also creates a new {@link BlockingQueue}
     * and drains current queue's containers into it.
     *
     * @param capacity New maximum capacity.
     * @throws IllegalArgumentException If the amount of containers in the queue is greater than the new size of the
     *                                  queue.
     * @author Marcus Talbot (1041464)
     */
    public void setCapacity(final int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity of ship cannot be smaller or equal to zero.");
        if (capacity > this.capacity) {
            var temp = this.freight;
            this.freight = new ArrayBlockingQueue<>(capacity);
            if (temp != null)
                temp.drainTo(this.freight);
        } else if (capacity < this.freight.size()) {
            throw new IllegalArgumentException("New capacity is smaller than current occupation.");
        }
        this.capacity = capacity;
    }
}
