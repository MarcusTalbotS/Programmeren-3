package com.github.marcustalbots.haven.facades;

import com.github.marcustalbots.haven.builders.ship.ContainerShipBuilder;
import com.github.marcustalbots.haven.builders.ship.OilShipBuilder;
import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.impl.vehicles.ships.ContainerShip;
import com.github.marcustalbots.haven.impl.vehicles.ships.OilShip;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link ShipBuilderFacade}-class offers a simplified interface for creating objects that extend the
 * {@link AbstractShip AbstractShip}-class.
 * <br><br>
 * This class was designed and implemented as a Singleton, as there is no need for multiple objects.
 *
 * @author Marcus Talbot (1041464)
 * @see AbstractShip
 * @see ContainerShip
 * @see OilShip
 */
public final class ShipBuilderFacade {

    /**
     * Instance-field to comply with the Singleton-pattern.
     */
    private static volatile ShipBuilderFacade instance;
    /**
     * The default capacity of any Ship-object created with one of the default methods in this class.
     */
    private int defaultCapacity;

    /**
     * Simple private constructor, which initialises the {@link #defaultCapacity}-field to 100.
     *
     * @author Marcus Talbot (1041464)
     */
    private ShipBuilderFacade() {
        this.defaultCapacity = 100;
    }

    /**
     * Static method that allows the user to get a reference to the {@link ShipBuilderFacade}-object.
     * If no {@link ShipBuilderFacade}-object exists, this method will create one atomically.
     *
     * @return A reference to the {@link ShipBuilderFacade}-object.
     * @author Marcus Talbot (1041464)
     */
    public static ShipBuilderFacade getInstance() {
        var shipBuilderDirector = ShipBuilderFacade.instance;

        if (shipBuilderDirector != null)
            return ShipBuilderFacade.instance;

        synchronized (ShipBuilderFacade.class) {
            if (ShipBuilderFacade.instance == null) {
                ShipBuilderFacade.instance = new ShipBuilderFacade();
            }
        }

        return ShipBuilderFacade.instance;
    }

    /**
     * Creates an {@link OilShip}-object, with the given identifier, and a default capacity of 100
     * {@link OilContainer OilContainer}-objects.
     *
     * @param identifier The (preferably unique) identifier of the ship.
     * @return The newly created {@link OilShip}-object.
     * @author Marcus Talbot (1041464)
     */
    public @NotNull OilShip buildDefaultOilShip(final String identifier) {
        return this.buildOilShip(identifier, this.defaultCapacity);
    }

    /**
     * Creates a {@link ContainerShip}-object, with the given identifier, and a default capacity of 100
     * {@link AbstractFreightContainer AbstractFreightContainer}-objects.
     *
     * @param identifier The (preferably unique) identifier of the ship.
     * @return The newly created {@link ContainerShip}-object.
     */
    public @NotNull ContainerShip buildDefaultContainerShip(final String identifier) {
        return this.buildContainerShip(identifier, this.defaultCapacity);
    }

    /**
     * Builds an {@link OilShip}-object with the given identifier and capacity.
     *
     * @param identifier The (preferably unique) identifier of the ship.
     * @param capacity   The {@link OilContainer}-capacity.
     * @return The newly created {@link OilShip}-object.
     * @author Marcus Talbot (1041464)
     */
    public @NotNull OilShip buildOilShip(final String identifier, final int capacity) {
        return OilShipBuilder.getInstance()
                .reset()
                .setIdentifier(identifier)
                .setCapacity(capacity)
                .setFillOnBuild(true)
                .build();
    }

    /**
     * Build a {@link ContainerShip}-object with the given identifier and capacity.
     *
     * @param identifier The (preferably unique) identifier of the ship.
     * @param capacity   The {@link AbstractFreightContainer}-capacity.
     * @return The newly created {@link ContainerShip}-object.
     * @author Marcus Talbot (1041464)
     */
    public @NotNull ContainerShip buildContainerShip(final String identifier, final int capacity) {
        return ContainerShipBuilder.getInstance()
                .reset()
                .setIdentifier(identifier)
                .setCapacity(capacity)
                .setFillOnBuild(true)
                .build();
    }

    /**
     * Sets the {@link #defaultCapacity}field of the {@link ShipBuilderFacade}-object.
     *
     * @param defaultCapacity The new default capacity.
     * @author Marcus Talbot (1041464)
     */
    @SuppressWarnings("unused")
    public void setDefaultCapacity(final int defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }
}
