package com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.impl.vehicles.ships.OilShip;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockOffloadVehicle;
import com.github.marcustalbots.haven.strategies.offload.OffloadStrategy;
import com.github.marcustalbots.haven.strategies.offload.OilOffloadStrategy;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;

/**
 * Implementation of the {@link AbstractDockOffloadVehicle}-class, to be used for offloading
 * {@link OilContainer}-objects from an {@link OilShip}-object.
 * @author Marcus Talbot (1041464)
 */
public final class Pump extends AbstractDockOffloadVehicle<OilContainer> {

    /**
     * Object used to serve as the {@link OffloadStrategy} for offloading {@link OilContainer}-objects.
     */
    private static final OffloadStrategy<Pump, OilContainer> OIL_OFFLOAD_STRATEGY = new OilOffloadStrategy();

    /**
     * Simple constructor used to create a Pump-object with the given configuration.
     *
     * @param dock       {@link Dock} that the {@link Pump} should associate with.
     * @param oilShip    {@link OilShip} that the {@link Pump} should associate with.
     * @param identifier (Preferably unique) Identifier of the Pump.
     * @see AbstractDockOffloadVehicle#AbstractDockOffloadVehicle(Dock, AbstractShip, String)
     */
    public Pump(final Dock dock, final OilShip oilShip, final String identifier) {
        super(dock, oilShip, identifier);
    }

    /**
     * Implementation of {@link Runnable#run()}. Responsible for offloading {@link OilContainer}-objects from
     * {@link OilShip}-objects.
     */
    @Override
    public void run() {
        var container = this.getShip().getNext();
        while (true) {
            container = this.getShip().getNext();
            if (container == null)
                return;
            Pump.OIL_OFFLOAD_STRATEGY.execute(this, container);
        }
    }
}
