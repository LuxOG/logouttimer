package com.logouttimer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.timers.TimersConfig;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@Slf4j
@PluginDescriptor(
	name = "LogoutTimer"
)
public class LogoutTimerPlugin extends Plugin
{

	@Inject
	private ItemManager itemManager;

	@Inject
	private SpriteManager spriteManager;

	@Inject
	private Client client;

	@Inject
	private TimersConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;


	@Override
	protected void shutDown() throws Exception
	{
		infoBoxManager.removeIf(t -> t instanceof TimerTimer);
	}

	@Provides
	LogoutTimerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LogoutTimerConfig.class);
	}
}
