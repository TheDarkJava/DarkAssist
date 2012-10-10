package me.DarkJava.DarkAssist;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.tab.Inventory;

public class Utils {
	
	public static int getWildernessLevel() {
		if(Widgets.get(381, 2).getText() != null) {
		return Integer.parseInt(Widgets.get(381, 2).getText().split(":")[1].replaceAll(" ", ""));
		}
		return 0;
	}
	
	public static int getPrayerPoints() {
		if(Widgets.get(749, 6).getText() != null) {
		return Integer.parseInt(Widgets.get(749, 6).getText());
		}
		return 0;		
	}
	
	public static void drinkPrayerPotion() {
		Tabs currentTab = Tabs.getCurrent();
		Mouse.setSpeed(Speed.VERY_FAST);
		Tabs.INVENTORY.open();
		for(int i : Variables.prayerPotionIds) {
			if(Inventory.contains(i)) {
				Task.sleep(10,20);
				Inventory.getItem(i).getWidgetChild().click(true);
				break;
			}
		}
		currentTab.open();
		Mouse.setSpeed(Speed.NORMAL);
	}
	
	public static void eatFood() {
		Tabs currentTab = Tabs.getCurrent();
		Mouse.setSpeed(Speed.VERY_FAST);
		Tabs.INVENTORY.open();
		for(int i : Variables.foodIds) {
			if(Inventory.contains(i)) {
				Task.sleep(10,20);
				Inventory.getItem(i).getWidgetChild().click(true);
				break;
			}
		}
		currentTab.open();
	}

}
