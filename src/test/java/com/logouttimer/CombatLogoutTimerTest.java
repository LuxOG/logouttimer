package com.logouttimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CombatLogoutTimerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CombatLogoutTimerPlugin.class);
		RuneLite.main(args);
	}
}