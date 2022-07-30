package com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.containers.CooledFreightContainer;
import com.github.marcustalbots.haven.impl.containers.DefaultFreightContainer;
import com.github.marcustalbots.haven.impl.containers.HeatedFreightContainer;
import com.github.marcustalbots.haven.impl.vehicles.ships.ContainerShip;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockOffloadVehicle;
import com.github.marcustalbots.haven.models.vehicles.ships.AbstractShip;
import com.github.marcustalbots.haven.strategies.offload.CooledOffloadStrategy;
import com.github.marcustalbots.haven.strategies.offload.DefaultOffloadStrategy;
import com.github.marcustalbots.haven.strategies.offload.HeatedOffloadStrategy;
import com.github.marcustalbots.haven.strategies.offload.OffloadStrategy;

/**
 * Implementation of the {@link AbstractDockOffloadVehicle}, to be used to offload
 * {@link AbstractFreightContainer}-objects from {@link ContainerShip}-objects. Makes use of
 * {@link OffloadStrategy OffloadStrategies} related to the various types of {@link AbstractFreightContainer}-types.
 * @author Marcus Talbot (1041464)
 */
public final class Crane extends AbstractDockOffloadVehicle<AbstractFreightContainer> {

    /**
     * OffloadStrategy used to offload {@link DefaultFreightContainer}-objects from {@link ContainerShip}-objects.
     */
    private static final OffloadStrategy<Crane, DefaultFreightContainer> DEFAULT_OFFLOAD_STRATEGY = new DefaultOffloadStrategy();

    /**
     * OffloadStrategy used to offload {@link HeatedFreightContainer}-objects from {@link ContainerShip}-objects.
     */
    private static final OffloadStrategy<Crane, HeatedFreightContainer> HEATED_OFFLOAD_STRATEGY = new HeatedOffloadStrategy();

    /**
     * OffloadStrategy used to offload {@link CooledOffloadStrategy}-objects from {@link ContainerShip}-objects.
     */
    private static final OffloadStrategy<Crane, CooledFreightContainer> COOLED_OFFLOAD_STRATEGY = new CooledOffloadStrategy();

    /**
     * Simple constructor to create a new {@link Crane}-object with the given configuration.
     *
     * @param dock       {@link Dock} that the {@link Crane} should associate with.
     * @param ship       {@link ContainerShip} that the {@link Crane should associate with.}
     * @param identifier (Preferably unique) Identifier of the Crane.
     * @see AbstractDockOffloadVehicle#AbstractDockOffloadVehicle(Dock, AbstractShip, String)
     */
    public Crane(final Dock dock, final ContainerShip ship, final String identifier) {
        super(dock, ship, identifier);
    }

    /**
     * Implementation of {@link Runnable#run()}. Responsible for offloading all types of
     * {@link AbstractFreightContainer}-implementations, using their respective {@link OffloadStrategy}-objects.
     */
    @Override
    public void run() {
        while (true) {
            switch (this.getShip().getNext()) {
                // NB: Requires Java 17 preview features to be enabled!
                case DefaultFreightContainer d -> Crane.DEFAULT_OFFLOAD_STRATEGY.execute(this, d);
                case HeatedFreightContainer h -> Crane.HEATED_OFFLOAD_STRATEGY.execute(this, h);
                case CooledFreightContainer c -> Crane.COOLED_OFFLOAD_STRATEGY.execute(this, c);
                // If the container returned is null, or something else that is not known, simply return.
                case default -> {
                    return;
                }
            }
        }
    }
}
