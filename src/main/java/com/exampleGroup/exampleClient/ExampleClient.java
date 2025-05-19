package com.exampleGroup.exampleClient;

import com.exampleGroup.exampleClient.command.CommandManager;
import com.exampleGroup.exampleClient.module.modules.render.TestModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleClient.MODID, version = ExampleClient.VERSION, name = ExampleClient.NAME)
public class ExampleClient {

    public static final String MODID = "modid";
    public static final String VERSION = "version";
    public static final String NAME = "Client Name";

    public static final CommandManager COMMAND_MANAGER = new CommandManager();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(COMMAND_MANAGER);
        MinecraftForge.EVENT_BUS.register(new TestModule());

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
