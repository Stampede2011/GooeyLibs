package ca.landonjw.gooeylibs;

import ca.landonjw.gooeylibs.implementation.AnimatedCommand;
import ca.landonjw.gooeylibs.implementation.SynchronizedCommand;
import ca.landonjw.gooeylibs.implementation.Test2Command;
import ca.landonjw.gooeylibs.implementation.TestCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
		modid = GooeyLibs.MOD_ID,
		name = GooeyLibs.MOD_NAME,
		version = GooeyLibs.VERSION,
		acceptableRemoteVersions = "*",
		serverSideOnly = true
)
public class GooeyLibs {

	/**
	 * The mod ID of the library.
	 */
	public static final String MOD_ID = "gooeylibs";
	/**
	 * The mod name of the library.
	 */
	public static final String MOD_NAME = "GooeyLibs";
	/**
	 * The version of the library.
	 */
	public static final String VERSION = "2.0.0";

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new TestCommand());
		event.registerServerCommand(new Test2Command());
		event.registerServerCommand(new SynchronizedCommand());
		event.registerServerCommand(new AnimatedCommand());
	}

}