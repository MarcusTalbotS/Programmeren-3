package com.github.marcustalbots.haven.strategies.transport;

import com.github.marcustalbots.haven.impl.containers.DefaultFreightContainer;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.ContainerTruck;

import com.github.marcustalbots.haven.utils.TimeUtils;
import java.time.LocalDateTime;

/**
 * Responsible handling the offloading of {@link DefaultFreightContainer}-objects, by {@link ContainerTruck}-objects.
 *
 * @author Marcus Talbot (1041464)
 */
public final class DefaultTransportStrategy implements TransportStrategy<ContainerTruck, DefaultFreightContainer> {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public void execute(final ContainerTruck transportVehicle, final DefaultFreightContainer container) {
        System.out.printf("[LOADED][%tT] %s loaded %s.\n", LocalDateTime.now(), transportVehicle, container);
        System.out.printf("[TRANSPORT][%tT] %s starting transport of %s.\n", LocalDateTime.now(), transportVehicle, container);
        try {
            Thread.sleep(TimeUtils.getDelayUsingGaussianDistribution(1000L, 6000L));
            System.out.printf("[FINISHED][%tT] %s finished transporting %s.\n", LocalDateTime.now(), transportVehicle, container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
