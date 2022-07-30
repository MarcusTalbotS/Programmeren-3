package com.github.marcustalbots.haven.models.vehicles.dock_vehicles;

import com.github.marcustalbots.haven.dock.Dock;

/**
 * Used to differentiate between the family of dock-transport-vehicles, and other dock-vehicles.
 *
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractDockTransportVehicle extends AbstractDockVehicle {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    public AbstractDockTransportVehicle(final Dock dock, final String identifier) {
        super(dock, identifier);
    }

}
