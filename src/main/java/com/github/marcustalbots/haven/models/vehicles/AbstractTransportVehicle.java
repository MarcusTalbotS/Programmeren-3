package com.github.marcustalbots.haven.models.vehicles;

import com.github.marcustalbots.haven.dock.Dock;

/**
 * Used to differentiate between the family of transport-vehicles, and other types of vehicles.
 *
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractTransportVehicle extends AbstractVehicle {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    public AbstractTransportVehicle(final Dock dock, final String identifier) {
        super(dock, identifier);
    }

}
