/**
 * IMPORTANT
 * <p>
 * This application must be built and ran with Java 17 preview features enabled.
 * <p>
 * To ensure that building the JAR-file goes well, this project was made as a Maven project.
 * As such, a pom.xml file will be provided.
 * <p>
 * To run the JAR-file, ensure that Java 17 is installed, and use the following command:
 * java --jar --enable-preview [filename].jar
 */
package com.github.marcustalbots.haven;

import com.github.marcustalbots.haven.facades.ShipBuilderFacade;
import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.factories.vehicles.ContainerDockVehicleFactory;
import com.github.marcustalbots.haven.factories.vehicles.OilDockVehicleFactory;
import com.github.marcustalbots.haven.models.vehicles.dock_vehicles.AbstractDockVehicle;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Contains main-method.
 *
 * @author Marcus Talbot (1041464)
 */
public class App {

    /**
     * Start-point of program.
     *
     * @param args Optional parameters.
     * @author Marcus Talbot (1041464)
     */
    public static void main(String[] args) throws InterruptedException {

        final var dock = new Dock(5);

        final var containerShip = ShipBuilderFacade.getInstance()
                .buildDefaultContainerShip("Sophia");
        final var oilShip = ShipBuilderFacade.getInstance()
                .buildDefaultOilShip("Amelia");

        final var containerVehicleFactory = new ContainerDockVehicleFactory();
        final var oilVehicleFactory = new OilDockVehicleFactory();

//        final var dockVehicleList = new ArrayList<AbstractDockVehicle>(10);

        containerVehicleFactory.createOffloadVehicle(dock, containerShip, "Isabella").run();
        containerVehicleFactory.createOffloadVehicle(dock, containerShip, "Olivia").run();
        oilVehicleFactory.createOffloadVehicle(dock, oilShip, "Noah").run();
        oilVehicleFactory.createOffloadVehicle(dock, oilShip, "Emma").run();
        containerVehicleFactory.createTransportVehicle(dock, "Mia").run();
        containerVehicleFactory.createTransportVehicle(dock, "Ava").run();
        containerVehicleFactory.createTransportVehicle(dock, "Barbara").run();
        oilVehicleFactory.createTransportVehicle(dock, "Evelyn").run();
        oilVehicleFactory.createTransportVehicle(dock, "Charlotte").run();
        oilVehicleFactory.createTransportVehicle(dock, "Jennifer").run();

//        final var executorService =
//                Executors.newFixedThreadPool(dockVehicleList.size() + 1);
//        dockVehicleList.forEach(executorService::submit);
//
//        executorService.shutdown();
//        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}
