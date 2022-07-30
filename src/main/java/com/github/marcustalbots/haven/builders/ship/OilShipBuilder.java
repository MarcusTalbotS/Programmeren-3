package com.github.marcustalbots.haven.builders.ship;

import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.impl.vehicles.ships.OilShip;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Implementation of the {@link AbstractShipBuilder}.
 * Allows the user to easily create {@link OilShip}-objects.
 * This class was implemented as a Singleton.
 *
 * @author Marcus Talbot (1041464)
 * @see AbstractShipBuilder
 * @see ContainerShipBuilder
 */
public final class OilShipBuilder extends AbstractShipBuilder<OilContainer> {

    /**
     * Used to comply with the Singleton pattern.
     */
    private static volatile OilShipBuilder instance;
    /**
     * Will be replaced with a fresh {@link OilShip}-object, after every {@link #reset()}-call.
     */
    private OilShip oilShip;

    /**
     * Private constructor, that initialises the {@link #oilShip}-field. Should only be called once per program,
     * by the {@link #getInstance()}-method.
     *
     * @author Marcus Talbot (1041464)
     */
    private OilShipBuilder() {
        this.oilShip = new OilShip();
    }

    /**
     * Static method used as the only way to get a reference to the {@link OilShipBuilder}-object.
     *
     * @return A reference to the instance of the {@link OilShipBuilder}-singleton.
     * @author Marcus Talbot (1041464)
     */
    @Contract(pure = true)
    public static @NotNull OilShipBuilder getInstance() {
        var builder = OilShipBuilder.instance;

        if (builder != null)
            return OilShipBuilder.instance;

        synchronized (ContainerShipBuilder.class) {
            if (OilShipBuilder.instance == null) {
                OilShipBuilder.instance = new OilShipBuilder();
            }
        }

        return OilShipBuilder.instance;
    }

    @Override
    public @NotNull OilShipBuilder setFillOnBuild(final boolean fillOnBuild) {
        this.fillOnBuild = fillOnBuild;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public @NotNull OilShipBuilder setIdentifier(final @NotNull String identifier) {
        this.oilShip.setIdentifier(identifier);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public @NotNull OilShipBuilder setQueue(final @NotNull BlockingQueue<OilContainer> queue) {
        this.oilShip.setFreightQueue(queue);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public @NotNull OilShipBuilder setCapacity(int capacity) {
        this.oilShip.setCapacity(capacity);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public @NotNull OilShip build() {
        if (this.oilShip.getFreightQueue() == null) {
            this.oilShip.setFreightQueue(
                    new ArrayBlockingQueue<>(
                            this.oilShip.getCapacity()
                    )
            );
        }

        if (this.oilShip.getCapacity() > this.oilShip.getFreightQueue().size() && this.fillOnBuild) {
            while (this.oilShip.getFreightQueue().remainingCapacity() > 0) {
                this.oilShip.addFreight(new OilContainer());
            }
        }

        return oilShip;
    }

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Contract("-> this")
    @Override
    public @NotNull OilShipBuilder reset() {
        this.oilShip = new OilShip();
        return this;
    }

}
