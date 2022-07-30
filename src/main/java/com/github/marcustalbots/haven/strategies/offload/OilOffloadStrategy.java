package com.github.marcustalbots.haven.strategies.offload;

import com.github.marcustalbots.haven.impl.containers.OilContainer;
import com.github.marcustalbots.haven.impl.vehicles.dock_vehicles.offloading_vehicles.Pump;
import com.github.marcustalbots.haven.utils.TimeUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Implementation of {@link OffloadStrategy}, responsible for offloading {@link OilContainer}-objects, by
 * {@link Pump}-objects.
 *
 * @author Marcus Talbot (1041464)
 */
public final class OilOffloadStrategy implements OffloadStrategy<Pump,
        OilContainer> {

    /**
     * {@inheritDoc}
     *
     * @author Marcus Talbot (1041464)
     */
    @Override
    public void execute(final @NotNull Pump offloadVehicle, final @NotNull OilContainer container) {
        System.out.printf("[POLLING][%tT] %s looking for OilTruck on Dock...\n", LocalDateTime.now(), offloadVehicle);
        var oilTruck = offloadVehicle.getDock().getOilTruck();
        System.out.printf("[CONNECTING][%tT] Connecting hose of %s to %s.\n", LocalDateTime.now(), offloadVehicle, oilTruck);
        System.out.printf("[PUMPING][%tT] %s pumping contents of %s's %s, from %s...\n", LocalDateTime.now(),
                offloadVehicle, offloadVehicle.getShip(), container, oilTruck);
        try {
            Thread.sleep(TimeUtils.getDelayUsingGaussianDistribution(1000L, 6000L));
            System.out.printf("[FINISHED][%tT] Oil pumped into %s.\n", LocalDateTime.now(), oilTruck);
            oilTruck.setContainer(container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
