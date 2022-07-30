package com.github.marcustalbots.haven.builders.ship;

import com.github.marcustalbots.haven.impl.containers.CooledFreightContainer;
import com.github.marcustalbots.haven.impl.containers.DefaultFreightContainer;
import com.github.marcustalbots.haven.impl.containers.HeatedFreightContainer;
import com.github.marcustalbots.haven.impl.vehicles.ships.ContainerShip;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementation of the {@link AbstractShipBuilder}.
 * Allows the user to easily create new {@link ContainerShip}-objects.
 * This class was implemented as a Singleton.
 *
 * @author Marcus Talbot (1041464)
 * @see AbstractShipBuilder
 * @see OilShipBuilder
 */
public final class ContainerShipBuilder extends AbstractShipBuilder<AbstractFreightContainer> {

    /**
     * Used to comply with the Singleton pattern.
     */
    private static volatile ContainerShipBuilder instance;
    /**
     * Will be replaced with a fresh object, after every {@link #reset()}-call
     */
    private ContainerShip containerShip;

    /**
     * Private constructor, that initialises the {@link #containerShip}-field. Should only be called once per program,
     * by the {@link #getInstance()}-method.
     *
     * @author Marcus Talbot (1041464)
     */
    private ContainerShipBuilder() {
        this.containerShip = new ContainerShip();
    }

    /**
     * Static method used as the only way to get a reference to the {@link ContainerShipBuilder}-object.
     *
     * @return A reference to the instance of the {@link ContainerShipBuilder}-singleton.
     * @author Marcus Talbot (1041464)
     */
    public static ContainerShipBuilder getInstance() {
        var builder = ContainerShipBuilder.instance;

        if (builder != null)
            return ContainerShipBuilder.instance;

        synchronized (ContainerShipBuilder.class) {
            if (ContainerShipBuilder.instance == null) {
                ContainerShipBuilder.instance = new ContainerShipBuilder();
            }
        }

        return ContainerShipBuilder.instance;
    }

    @Override
    public @NotNull ContainerShipBuilder setFillOnBuild(final boolean fillOnBuild) {
        this.fillOnBuild = fillOnBuild;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Contract("_ -> this")
    @Override
    public @NotNull ContainerShipBuilder setIdentifier(final @NotNull String identifier) {
        this.containerShip.setIdentifier(identifier);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Contract("_ -> this")
    @Override
    public @NotNull ContainerShipBuilder setQueue(final @NotNull BlockingQueue<AbstractFreightContainer> queue) {
        this.containerShip.setFreightQueue(queue);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Contract("_ -> this")
    @Override
    public @NotNull ContainerShipBuilder setCapacity(final int capacity) {
        this.containerShip.setCapacity(capacity);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public @NotNull ContainerShip build() {

        if (this.containerShip.getCapacity() > this.containerShip.getFreightQueue().size() && this.fillOnBuild) {
            for (var index = 0; index < this.containerShip.getCapacity(); index++) {
                switch (ThreadLocalRandom.current().nextInt(3)) {
                    case 0 -> this.containerShip.addFreight(new DefaultFreightContainer());
                    case 1 -> this.containerShip.addFreight(new HeatedFreightContainer());
                    case 2 -> this.containerShip.addFreight(new CooledFreightContainer());
                }
            }
        }
        return this.containerShip;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Contract("-> this")
    @Override
    public @NotNull ContainerShipBuilder reset() {
        this.containerShip = new ContainerShip();
        return this;
    }

}
