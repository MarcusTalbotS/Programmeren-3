package com.github.marcustalbots.haven.factories.vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.vehicles.ships.ContainerShip;
import com.github.marcustalbots.haven.impl.vehicles.ships.OilShip;
import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockOffloadVehicle;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockTransportVehicle;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;

/**
 * AbstractFactory which provides an interface, that can be used to create simple {@link AbstractDockOffloadVehicle}-subclass
 * objects, and {@link AbstractDockTransportVehicle}-subclass objects.
 *
 * @param <T> The type of the {@link AbstractShip}-subclass the any created {@link AbstractDockOffloadVehicle}-object
 *            should service.
 * @param <U> The type of the {@link AbstractFreightContainer} the ship of type T should be able to transport.
 * @author Marcus Talbot (1041464)
 * @see ContainerDockVehicleFactory
 * @see OilDockVehicleFactory
 */
public abstract class AbstractDockVehicleFactory<T extends AbstractShip<? extends U>, U extends AbstractContainer> {

    /**
     * Abstract method that must be implemented to return an object of a subclass of {@link AbstractDockOffloadVehicle},
     * using the given parameters as its fields.
     *
     * @param dock       The {@link Dock}-object that the {@link AbstractDockOffloadVehicle}-object should associate with.
     * @param ship       The {@link AbstractShip}-subclass that the {@link AbstractDockOffloadVehicle}-object should associate
     *                   with.
     * @param identifier The (preferable unique) identifier of the {@link AbstractDockOffloadVehicle}.
     * @return A new object of a subclass of {@link AbstractDockOffloadVehicle}.
     * @author Marcus Talbot (1041464)
     * @see ContainerDockVehicleFactory#createOffloadVehicle(Dock, ContainerShip, String)
     * @see OilDockVehicleFactory#createOffloadVehicle(Dock, OilShip, String)
     */
    public abstract AbstractDockOffloadVehicle<U> createOffloadVehicle(final Dock dock, final T ship, final String identifier);

    /**
     * Abstract method that must be implemented to return an object of a subclass of {@link AbstractDockTransportVehicle},
     * using the given parameters as its fields.
     *
     * @param dock       The {@link Dock}-object that the {@link AbstractDockTransportVehicle}-object should associate with.
     * @param identifier The (preferable unique) identifier of the {@link AbstractDockTransportVehicle}.
     * @return A new object of a subclass of {@link AbstractDockTransportVehicle}.
     * @author Marcus Talbot (1041464)
     * @see ContainerDockVehicleFactory#createTransportVehicle(Dock, String)
     * @see OilDockVehicleFactory#createTransportVehicle(Dock, String)
     */
    public abstract AbstractDockTransportVehicle createTransportVehicle(final Dock dock, final String identifier);

}
