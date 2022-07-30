package com.github.marcustalbots.haven.impl.vehicles.ships;

import com.github.marcustalbots.haven.builders.ship.ContainerShipBuilder;
import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;

import java.util.Objects;

/**
 * Implementation of the {@link AbstractShip}, used to differentiate between different types of Ships, and to force
 * a Ship to only hold a certain type of {@link AbstractContainer}.
 *
 * @author Marcus Talbot (1041464)
 */
public class ContainerShip extends AbstractShip<AbstractFreightContainer> {

    /**
     * Simple constructor, which creates a new object without an identifier. Use {@link ContainerShipBuilder} instead.
     *
     * @author Marcus Talbot (1041464)
     */
    public ContainerShip() {
        super(null, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContainerShip c) {
            if (Objects.equals(c.getIdentifier(), this.getIdentifier())) {
                if (c.getFreightQueue().containsAll(this.getFreightQueue())) {
                    return this.getFreightQueue().containsAll(c.getFreightQueue());
                }
            }
        }
        return false;
    }
}
