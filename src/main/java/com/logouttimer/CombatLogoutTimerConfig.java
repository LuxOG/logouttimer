package com.logouttimer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(CombatLogoutTimerConfig.GROUP)
public interface CombatLogoutTimerConfig extends Config
{
	String GROUP = "combatlogouttimer";


	@ConfigItem(
		keyName = "showCombatTimer",
		name = "Combat logout timer",
		description = "Configures the display for the 10 second timer that prevents logout upon being hit in combat"
	)
	default boolean showCombatTimer()
	{
		return true;
	}
}
