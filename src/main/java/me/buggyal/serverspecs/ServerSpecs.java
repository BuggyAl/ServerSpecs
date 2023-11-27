package me.buggyal.serverspecs;

import org.bukkit.plugin.java.JavaPlugin;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServerSpecs extends JavaPlugin {

    @Override
    public void onEnable() {

        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        OperatingSystem os = systemInfo.getOperatingSystem();

        CentralProcessor processor = hardware.getProcessor();
        GlobalMemory memory = hardware.getMemory();

        List<GraphicsCard> graphicsCards = hardware.getGraphicsCards();

        Logger logger = getLogger();
        logger.log(Level.INFO, "Operating System: " + os);
        logger.log(Level.INFO, "CPU Model: " + processor.getProcessorIdentifier().getName());
        logger.log(Level.INFO, "Total Memory: " + memory.getTotal());
        logger.log(Level.INFO, "Available Memory: " + memory.getAvailable());
        logger.log(Level.INFO, "Virtual Memory: " + memory.getVirtualMemory());

        for (GraphicsCard graphicsCard : graphicsCards) {
            String deviceID = graphicsCard.getDeviceId();
            String name = graphicsCard.getName();
            String vendor = graphicsCard.getVendor();
            long vRAM = graphicsCard.getVRam();
            String versionInfo = graphicsCard.getVersionInfo();
            logger.log(Level.INFO, "Graphics Card: " + name + " (" + vendor + ") " + vRAM + "MB " + deviceID + " " + versionInfo);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
