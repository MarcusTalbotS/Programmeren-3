package com.github.marcustalbots.haven.factories.vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles.Pump;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.OilTruck;
import com.github.marcustalbots.haven.impl.vehicles.ships.OilShip;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An implementation of the {@link AbstractDockVehicleFactory}-class.
 * Can be used to create {@link Pump}-objects and {@link OilTruck}-objects, with given parameters.
 *
 * @author Marcus Talbot (1041464)
 * @see AbstractDockVehicleFactory
 * @see ContainerDockVehicleFactory
 */
public final class OilDockVehicleFactory extends AbstractDockVehicleFactory<OilShip, OilContainer> {

    /**
     * Creates a new {@link Pump}-object, with the given configuration.
     *
     * @param dock       The {@link Dock}-object that the {@link Pump}-object should associate with.
     * @param ship       The {@link OilShip}-subclass that the {@link Pump}-object should associate
     *                   with.
     * @param identifier The (preferable unique) identifier of the {@link Pump}.
     * @return A new {@link Pump}-object.
     * @author Marcus Talbot (1041464)
     */
    @Contract("_, _, _ -> new")
    @Override
    public @NotNull Pump createOffloadVehicle(final Dock dock, final OilShip ship, final String identifier) {
        return new Pump(dock, ship, identifier);
    }

    /**
     * Creates a new {@link OilTruck}-object with the given configuration.
     *
     * @param dock       The {@link Dock}-object that the {@link OilTruck}-object should associate with.
     * @param identifier The (preferable unique) identifier of the {@link OilTruck}.
     * @return A new {@link OilTruck}-object
     * @author Marcus Talbot (1041464)
     */
    @Contract("_, _ -> new")
    @Override
    public @NotNull OilTruck createTransportVehicle(final Dock dock, final String identifier) {
        return new OilTruck(dock, identifier);
    }
}
