package com.logouttimer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(LogoutTimerConfig.GROUP)
public interface LogoutTimerConfig extends Config
{
	String GROUP = "logouttimer";


	@ConfigItem(
		keyName = "showCombatTimer",
		name = "Combat cooldown timer",
		description = "Configures the display for the 10 second timer that prevents logout upon being hit in combat"
	)
	default boolean showCombatTimer()
	{
		return true;
	}
}
