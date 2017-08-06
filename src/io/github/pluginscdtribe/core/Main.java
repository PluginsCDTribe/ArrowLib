package io.github.pluginscdtribe.core;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		new OpCheckRunnable().runTaskTimer(this, 0L, 10L);
	}
}
