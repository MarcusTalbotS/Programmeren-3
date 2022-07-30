package com.github.marcustalbots.haven.factories.vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles.Crane;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.ContainerTruck;
import com.github.marcustalbots.haven.impl.vehicles.ships.ContainerShip;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An implementation of the {@link AbstractDockVehicleFactory}-class.
 * Can be used to create {@link Crane}-objects and {@link ContainerTruck}-objects, with given parameters.
 *
 * @author Marcus Talbot (1041464)
 * @see AbstractDockVehicleFactory
 * @see OilDockVehicleFactory
 */
public final class ContainerDockVehicleFactory extends AbstractDockVehicleFactory<ContainerShip, AbstractFreightContainer> {

    /**
     * Creates a new {@link Crane}-object, with the given configuration.
     *
     * @param dock       The {@link Dock}-object that the {@link Crane}-object should associate with.
     * @param ship       The {@link ContainerShip}-subclass that the {@link Crane}-object should associate
     *                   with.
     * @param identifier The (preferable unique) identifier of the {@link Crane}.
     * @return A new {@link Crane}-object.
     * @author Marcus Talbot (1041464)
     */
    @Contract("_, _, _ -> new")
    @Override
    public @NotNull Crane createOffloadVehicle(final Dock dock, final ContainerShip ship, final String identifier) {
        return new Crane(dock, ship, identifier);
    }

    /**
     * Creates a new {@link ContainerTruck}-object with the given configuration.
     *
     * @param dock       The {@link Dock}-object that the {@link ContainerTruck}-object should associate with.
     * @param identifier The (preferable unique) identifier of the {@link ContainerTruck}.
     * @return A new {@link ContainerTruck}-object
     * @author Marcus Talbot (1041464)
     */
    @Contract("_, _ -> new")
    @Override
    public @NotNull ContainerTruck createTransportVehicle(final Dock dock, final String identifier) {
        return new ContainerTruck(dock, identifier);
    }
}
