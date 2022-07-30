package com.github.marcustalbots.haven.strategies.transport;

import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockTransportVehicle;

/**
 * Provides a common interface for transporting-strategies, used by dock-transport-vehicles.
 *
 * @param <T> Type of transport-vehicle
 * @param <U> Type of container.
 * @author Marcus Talbot (1041464)
 */
@FunctionalInterface
public interface TransportStrategy<T extends AbstractDockTransportVehicle, U extends AbstractContainer> {

    /**
     * Handles transport of the given container U, by the given transport-vehicle T.
     *
     * @param transportVehicle Transport-vehicle responsible for transporting the container.
     * @param container        Container that will be offloaded.
     * @author Marcus Talbot (1041464)
     */
    void execute(final T transportVehicle, final U container);

}
