package com.logouttimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LogoutTimerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LogoutTimerPlugin.class);
		RuneLite.main(args);
	}
}