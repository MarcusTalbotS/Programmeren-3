package com.github.marcustalbots.haven.strategies.offload;

import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockOffloadVehicle;
import org.jetbrains.annotations.NotNull;

/**
 * Provides a common interface for offloading-strategies, used by offloading-vehicles.
 *
 * @param <T> Type of offloading-vehicle
 * @param <U> Type of container.
 * @author Marcus Talbot (1041464)
 */
@FunctionalInterface
public interface OffloadStrategy<T extends AbstractDockOffloadVehicle<?>, U extends AbstractContainer> {

    /**
     * Handles offloading of the given container U, by the given offload-vehicle T.
     *
     * @param offloadVehicle Offload-vehicle responsible for offloading the container.
     * @param container      Container that will be offloaded.
     * @author Marcus Talbot (1041464)
     */
    void execute(@NotNull final T offloadVehicle, @NotNull final U container);

}
