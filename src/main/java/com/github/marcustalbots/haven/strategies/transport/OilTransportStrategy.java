package com.github.marcustalbots.haven.strategies.transport;

import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.OilTruck;

import com.github.marcustalbots.haven.utils.TimeUtils;
import java.time.LocalDateTime;

/**
 * Responsible handling the offloading of {@link OilContainer}-objects, by {@link OilTruck}-objects.
 *
 * @author Marcus Talbot (1041464)
 */
public final class OilTransportStrategy implements TransportStrategy<OilTruck,
        OilContainer> {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public void execute(final OilTruck transportVehicle, final OilContainer container) {
        System.out.printf("[PUMPING][%tT] Loading contents of %s onto %s.\n", LocalDateTime.now(), container, transportVehicle);
        System.out.printf("[TRANSPORT][%tT] %s starting transport of contents of %s.\n", LocalDateTime.now(), transportVehicle, container);
        try {
            Thread.sleep(TimeUtils.getDelayUsingGaussianDistribution(1000L, 6000L));
            System.out.printf("[FINISHED][%tT] %s finished transporting contents of %s.\n", LocalDateTime.now(), transportVehicle, container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
