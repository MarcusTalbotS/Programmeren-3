package com.github.marcustalbots.haven.impl.vehicles.ships;

import com.github.marcustalbots.haven.builders.ship.OilShipBuilder;
import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.models.containers.AbstractContainer;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;

import java.util.Objects;

/**
 * Implementation of the {@link AbstractShip}, used to differentiate between different types of Ships, and to force
 * a Ship to only hold a certain type of {@link AbstractContainer}.
 *
 * @author Marcus Talbot (1041464)
 */
public class OilShip extends AbstractShip<OilContainer> {

    /**
     * Simple constructor, which creates a new OilShip without an identifier. Use {@link OilShipBuilder} instead.
     *
     * @author Marcus Talbot (1041464)
     */
    public OilShip() {
        super(null, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OilShip c) {
            if (Objects.equals(c.getIdentifier(), this.getIdentifier())) {
                if (c.getFreightQueue().containsAll(this.getFreightQueue())) {
                    return this.getFreightQueue().containsAll(c.getFreightQueue());
                }
            }
        }
        return false;
    }

}
