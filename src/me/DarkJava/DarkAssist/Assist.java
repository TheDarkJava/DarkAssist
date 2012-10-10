package me.DarkJava.DarkAssist;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.wrappers.interactive.Player;

public class Assist extends Node {

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		Player player = Players.getLocal();
	if(Variables.autoDrinkPrayerPotion && Prayer.getPoints() <= ((Prayer.getPoints()/2)+(Prayer.getPoints()/10))) {
		Utils.drinkPrayerPotion();

	}
	
	if(Variables.autoEat && player.getHpPercent() <= 55) {
		Utils.eatFood();
		Task.sleep(25, 50);
	}
	

		Task.sleep(100, 200);
	}

}
