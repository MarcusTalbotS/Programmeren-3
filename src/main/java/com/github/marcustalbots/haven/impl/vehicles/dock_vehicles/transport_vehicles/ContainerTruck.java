package com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.impl.containers.CooledFreightContainer;
import com.github.marcustalbots.haven.impl.containers.DefaultFreightContainer;
import com.github.marcustalbots.haven.impl.containers.HeatedFreightContainer;
import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockTransportVehicle;
import com.github.marcustalbots.haven.strategies.transport.CooledTransportStrategy;
import com.github.marcustalbots.haven.strategies.transport.DefaultTransportStrategy;
import com.github.marcustalbots.haven.strategies.transport.HeatedTransportStrategy;
import com.github.marcustalbots.haven.strategies.transport.TransportStrategy;

/**
 * Implementation of the {@link AbstractDockTransportVehicle}, used to transport
 * {@link AbstractFreightContainer}-objects, after they have been placed on the {@link Dock}.
 * @author Marcus Talbot (1041464)
 */
public final class ContainerTruck extends AbstractDockTransportVehicle {

    /**
     * {@link DefaultTransportStrategy} used to transport {@link DefaultFreightContainer}-objects.
     */
    private static final TransportStrategy<ContainerTruck, DefaultFreightContainer> DEFAULT_TRANSPORT_STRATEGY =
            new DefaultTransportStrategy();

    /**
     * {@link HeatedTransportStrategy} used to transport {@link HeatedFreightContainer}-objects.
     */
    private static final TransportStrategy<ContainerTruck, HeatedFreightContainer> HEATED_TRANSPORT_STRATEGY =
            new HeatedTransportStrategy();

    /**
     * {@link CooledTransportStrategy} used to transport {@link CooledFreightContainer}-objects.
     */
    private static final TransportStrategy<ContainerTruck, CooledFreightContainer> COOLED_TRANSPORT_STRATEGY =
            new CooledTransportStrategy();

    /**
     * Simple constructor used to create a new {@link ContainerTruck}-object with the given configuration.
     *
     * @param dock       {@link Dock} the {@link ContainerTruck} should associate with.
     * @param identifier (Preferably unique) Identifier of the {@link ContainerTruck}.
     * @see AbstractDockTransportVehicle#AbstractDockTransportVehicle(Dock, String)
     */
    public ContainerTruck(final Dock dock, final String identifier) {
        super(dock, identifier);
    }

    /**
     * Implementation of {@link Runnable#run()}. Responsible for retrieving {@link AbstractFreightContainer}-objects
     * from the {@link Dock}, and simulating their transport.
     * <br><br>
     * When the dock contains no more
     * {@link AbstractFreightContainer}-objects, and the time-out of fifteen seconds has passed, the method returns,
     * gracefully terminating the {@link Thread}.
     */
    @Override
    public void run() {
        while (true) {

            switch (this.getDock().getContainer()) {
                case DefaultFreightContainer d -> ContainerTruck.DEFAULT_TRANSPORT_STRATEGY.execute(this, d);
                case HeatedFreightContainer h -> ContainerTruck.HEATED_TRANSPORT_STRATEGY.execute(this, h);
                case CooledFreightContainer c -> ContainerTruck.COOLED_TRANSPORT_STRATEGY.execute(this, c);
                default -> {return;}
            }
        }
    }
}
