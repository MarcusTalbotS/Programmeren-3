package com.github.marcustalbots.haven.strategies.transport;

import com.github.marcustalbots.haven.impl.containers.CooledFreightContainer;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.transport_vehicles.ContainerTruck;

import com.github.marcustalbots.haven.utils.TimeUtils;
import java.time.LocalDateTime;

/**
 * Responsible handling the offloading of {@link CooledFreightContainer}-objects, by {@link ContainerTruck}-objects.
 *
 * @author Marcus Talbot (1041464)
 */
public final class CooledTransportStrategy implements TransportStrategy<ContainerTruck, CooledFreightContainer> {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public void execute(final ContainerTruck transportVehicle, final CooledFreightContainer container) {
        System.out.printf("[COUPLING][%tT] Coupling %s to the cooling element in %s.\n", LocalDateTime.now(), container, transportVehicle);
        System.out.printf("[LOADED][%tT] %s coupled to cooling element in %s.\n", LocalDateTime.now(), container, transportVehicle);
        System.out.printf("[TRANSPORT][%tT] %s starting transport of %s.\n", LocalDateTime.now(), transportVehicle, container);
        try {
            Thread.sleep(TimeUtils.getDelayUsingGaussianDistribution(1000L, 6000L));
            System.out.printf("[FINISHED][%tT] %s finished transporting %s.\n", LocalDateTime.now(), transportVehicle, container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
