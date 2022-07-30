package com.github.marcustalbots.haven.strategies.offload;

import com.github.marcustalbots.haven.impl.containers.HeatedFreightContainer;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles.Crane;
import com.github.marcustalbots.haven.utils.TimeUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible handling the offloading of {@link HeatedFreightContainer}-objects, by {@link Crane}-objects.
 *
 * @author Marcus Talbot (1041464)
 */
public final class HeatedOffloadStrategy implements OffloadStrategy<Crane,
        HeatedFreightContainer> {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public void execute(final @NotNull Crane offloadVehicle, final @NotNull HeatedFreightContainer container) {
        System.out.printf("[DECOUPLING][%tT] Decoupling %s from heating element of %s.\n", LocalDateTime.now(), container, offloadVehicle.getShip());
        System.out.printf("[OFFLOADING][%tT] %s offloading %s, from %s...\n", LocalDateTime.now(), offloadVehicle, container, offloadVehicle.getShip());
        try {
            Thread.sleep(TimeUtils.getDelayUsingGaussianDistribution(1000L, 6000L));
            System.out.printf("[PLACING][%tT] %s placing %s on dock...\n", LocalDateTime.now(), offloadVehicle, container);
            offloadVehicle.getDock().placeContainer(container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
