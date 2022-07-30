package com.github.marcustalbots.haven.models.vehicles.dock_vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;

/**
 * Used to differentiate between the family of offload-vehicles, and other dock-vehicles. This class holds the
 * {@link #ship}-field, which is used to associate a {@link AbstractShip}-subclass with an offload-vehicle.
 *
 * @param <T> Type of {@link AbstractContainer}-objects the ship should be able to transport.
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractDockOffloadVehicle<T extends AbstractContainer> extends AbstractDockVehicle {

    /**
     * Ship that will be unloaded by the offload-vehicle.
     */
    private AbstractShip<T> ship;

    /**
     * Simple constructor which creates a new object, with the given configuration.
     *
     * @param dock       {@link Dock} the {@link AbstractDockOffloadVehicle} should associate with.
     * @param ship       {@link AbstractShip} the {@link AbstractDockOffloadVehicle} should associate with.
     * @param identifier (Preferably unique) Identifier of the {@link AbstractDockOffloadVehicle}.
     * @author Marcus Talbot (1041464)
     */
    public AbstractDockOffloadVehicle(final Dock dock, final AbstractShip<T> ship, final String identifier) {
        super(dock, identifier);
        this.ship = ship;
    }

    /**
     * Gets the ship this object associates with.
     *
     * @return {@link #ship}.
     * @author Marcus Talbot (1041464)
     */
    public AbstractShip<T> getShip() {
        return this.ship;
    }

    /**
     * Can be used to set a (new) ship this object should associate with.
     *
     * @param ship {@link AbstractShip} this object should associate with.
     * @author Marcus Talbot (1041464)
     */
    @SuppressWarnings("unused")
    public void setShip(final AbstractShip<T> ship) {
        this.ship = ship;
    }
}
