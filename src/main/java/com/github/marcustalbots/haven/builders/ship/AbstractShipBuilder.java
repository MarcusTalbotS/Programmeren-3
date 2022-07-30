package com.github.marcustalbots.haven.builders.ship;

import com.github.marcustalbots.haven.impl.vehicles.ships.ContainerShip;
import com.github.marcustalbots.haven.impl.vehicles.ships.OilShip;
import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * Abstract Builder, which serves as the model for the {@link ContainerShipBuilder}- and {@link OilShipBuilder}-classes.
 * It is recommended to implement sub-classes of the {@link AbstractShipBuilder} as Singletons.
 *
 * @param <T> Extends AbstractContainer. Used to guarantee the type of the elements in the freight-queue.
 * @author Marcus Talbot (1041464)
 * @see ContainerShipBuilder
 * @see OilShipBuilder
 */
public abstract class AbstractShipBuilder<T extends AbstractContainer> {

    protected boolean fillOnBuild = false;

    @Contract("_ -> this")
    public abstract @NotNull AbstractShipBuilder<T> setFillOnBuild(boolean fillOnBuild);

    /**
     * Sets the identifier of the object being built to the given String.
     *
     * @param identifier String that will be used to set the identifier-field of the object being built.
     * @return This instance of the Builder, to accommodate chaining the various methods.
     * @author Marcus Talbot (1041464)
     * @see ContainerShipBuilder#setIdentifier(String)
     * @see OilShipBuilder#setIdentifier(String)
     */
    public abstract @NotNull AbstractShipBuilder<T> setIdentifier(@NotNull final String identifier);

    /**
     * Sets the Queue as the freight-queue of the object being built.
     *
     * @param queue Queue that will be used as the freight-queue of the object being built.
     * @return This instance of the builder, to accommodate chaining the various methods.
     * @author Marcus Talbot (1041464)
     * @see ContainerShipBuilder#setQueue(BlockingQueue)
     * @see OilShipBuilder#setQueue(BlockingQueue)
     */
    @SuppressWarnings("unused")
    public abstract @NotNull AbstractShipBuilder<T> setQueue(@NotNull final BlockingQueue<T> queue);

    /**
     * Sets the capacity of the internal freight-queue of the Object being built.
     *
     * @param capacity The new capacity of the internal freight-queue of the Object being built.
     * @return This instance of the builder, to accommodate chaining the various methods.
     * @author Marcus Talbot (1041464)
     * @see ContainerShipBuilder
     * @see OilShipBuilder
     */
    public abstract @NotNull AbstractShipBuilder<T> setCapacity(final int capacity);

    /**
     * Finalises the construction of the Object that is currently being built.
     *
     * @return An Object that extends AbstractShip.
     * @author Marcus Talbot (1041464)
     * @see ContainerShipBuilder
     * @see OilShipBuilder
     * @see ContainerShip
     * @see OilShip
     */
    public abstract @NotNull AbstractShip<T> build();

    /**
     * Creates a new internal object for the Builder to modify.
     *
     * @return This instance of the builder, to accommodate chaining the various methods.
     * @author Marcus Talbot (1041464)
     */
    public abstract @NotNull AbstractShipBuilder<T> reset();

}
