package com.github.marcustalbots.haven.models.vehicles.dock_vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.models.vehicles.AbstractVehicle;

/**
 * Used as a common interface between all dock-vehicles. Implements {@link Runnable}, as all dock-vehicles should be
 * used in separate {@link Thread Threads}.
 *
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractDockVehicle extends AbstractVehicle implements Runnable {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    public AbstractDockVehicle(final Dock dock, final String identifier) {
        super(dock, identifier);
    }
}
