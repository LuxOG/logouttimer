package com.logouttimer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(CombatLogoutTimerConfig.GROUP)
public interface CombatLogoutTimerConfig extends Config
{
	String GROUP = "combatlogouttimer";


	@ConfigItem(
		keyName = "showLogoutTimer",
		name = "Combat logout timer",
		description = "Configures the display for the 10 second timer that prevents logout upon being hit in combat"
	)
	default boolean showLogoutTimer()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showWorldHopTimer",
		name = "Combat world hop timer",
		description = "Configures the display for the 18 tick timer that prevents world hopping upon being hit in combat"
	)
	default boolean showWorldHopTimer()
	{
		return true;
	}

	@ConfigItem(
		position = 16,
		keyName = "disableOutsidePvP",
		name = "Disable outside PvP",
		description = "Disable the overlays outside of PvP areas"
	)
	default boolean disableOutsidePvP()
	{
		return false;
	}
}
