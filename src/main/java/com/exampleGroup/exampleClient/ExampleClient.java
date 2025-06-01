package com.exampleGroup.exampleClient;

import com.exampleGroup.exampleClient.clickGui.ClickGui;
import com.exampleGroup.exampleClient.clickGui.HudGui;
import com.exampleGroup.exampleClient.command.CommandManager;
import com.exampleGroup.exampleClient.config.ConfigManager;
import com.exampleGroup.exampleClient.module.ModuleManager;
import com.exampleGroup.exampleClient.relations.RelationManager;
import com.exampleGroup.exampleClient.utility.KeyUtils;
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
    public static final String PREFIX = "§8[§aExampleClient§8]§r";

    public static final RelationManager RELATION_MANAGER = new RelationManager();
    public static final CommandManager COMMAND_MANAGER = new CommandManager();
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final ConfigManager CONFIG_MANAGER = new ConfigManager();
    public static final ClickGui CLICK_GUI = new ClickGui();
    public static final HudGui HUD_GUI = new HudGui();

    // Utility
    KeyUtils keyUtils = new KeyUtils();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(COMMAND_MANAGER);
        MinecraftForge.EVENT_BUS.register(MODULE_MANAGER);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    /*
    TODO:
    - Move gui
    - click gui
    - util rendering classes
     */
}
