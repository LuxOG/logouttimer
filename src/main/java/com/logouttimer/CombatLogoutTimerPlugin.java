package com.logouttimer;

import com.google.inject.Provides;
import static com.logouttimer.GameTimer.COMBAT_TIMER;
import java.time.Duration;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@Slf4j
@PluginDescriptor(
	name = "LogoutTimer"
)
public class CombatLogoutTimerPlugin extends Plugin
{

	@Inject
	private ItemManager itemManager;

	@Inject
	private SpriteManager spriteManager;

	@Inject
	private Client client;

	@Inject
	private CombatLogoutTimerConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;


	@Override
	protected void shutDown() throws Exception
	{
		infoBoxManager.removeIf(t -> t instanceof TimerTimer);
	}

	@Provides
	CombatLogoutTimerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CombatLogoutTimerConfig.class);
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event)
	{
		if (event.getActor() != client.getLocalPlayer())
		{
			return;
		}
		if (config.showCombatTimer())
		{
			boolean inWilderness = client.getVarbitValue(Varbits.IN_WILDERNESS) == 1;
			boolean inPvp = client.getVarbitValue(Varbits.PVP_SPEC_ORB) == 1;
			if (!inWilderness && !inPvp && config.disableOutsidePvP())
			{
				//Do nothing
			}
			else
			{
				createGameTimer(COMBAT_TIMER);
			}
		}
	}

	private TimerTimer createGameTimer(final GameTimer timer)
	{
		if (timer.getDuration() == null)
		{
			throw new IllegalArgumentException("Timer with no duration");
		}
		return createGameTimer(timer, timer.getDuration());
	}

	private TimerTimer createGameTimer(final GameTimer timer, Duration duration)
	{
		removeGameTimer(timer);

		TimerTimer t = new TimerTimer(timer, duration, this);
		switch (timer.getImageType())
		{
			case SPRITE:
				spriteManager.getSpriteAsync(timer.getImageId(), 0, t);
				break;
			case ITEM:
				t.setImage(itemManager.getImage(timer.getImageId()));
				break;
		}
		t.setTooltip(timer.getDescription());
		infoBoxManager.addInfoBox(t);
		return t;
	}

	private void removeGameTimer(GameTimer timer)
	{
		infoBoxManager.removeIf(t -> t instanceof TimerTimer && ((TimerTimer) t).getTimer() == timer);
	}
}
