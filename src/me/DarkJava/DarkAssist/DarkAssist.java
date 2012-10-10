package me.DarkJava.DarkAssist;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import org.powerbot.core.bot.Bot;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.Player;


@Manifest(authors = {"DarkJava"}, name = "Dark Assist", description = "Helps you while pking!", version = 0.1)
public class DarkAssist extends ActiveScript implements PaintListener, MessageListener, KeyListener {
	
	private Tree jobs = null;

	@Override
	public void onRepaint(Graphics g) {
		if(Players.getLocal().isInCombat()) {
			if(Players.getLocal().getInteracting() != null) {
			Point point = Players.getLocal().getInteracting().getCentralPoint();
			g.setColor(Color.RED);
			g.drawString(Players.getLocal().getInteracting().getHpPercent() +" / 100", (int)point.getX(), (int)point.getY()-30);
		}
		}
		
		for(Player player : Players.getLoaded()) {

			int wildLevel = Utils.getWildernessLevel();
			if(player.isOnScreen() && wildLevel > 0 && player.getName() != Players.getLocal().getName()) {
			if(player.getLevel() + wildLevel >= Players.getLocal().getLevel() && player.getLevel() - wildLevel <= Players.getLocal().getLevel()) {
				if(player.getLevel() > Players.getLocal().getLevel()) {
					g.setColor(Color.RED);
					player.getLocation().draw(g);	
				} else if(player.getLevel() == Players.getLocal().getLevel()) {
					g.setColor(Color.ORANGE);
					player.getLocation().draw(g);
				} else if(player.getLevel() < Players.getLocal().getLevel()){
					g.setColor(Color.GREEN);
					player.getLocation().draw(g);
				}
			}


		}
			
		}
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 50, 200, 50);
		g.setColor(Color.WHITE);
		if(Prayer.getActive().length != 0) {
		g.drawString("Prayer runs out in " + (int)(Utils.getPrayerPoints()/(0.3 * (1 + 0)))/10 + " seconds.", 10, 60);
		} else {
			g.drawString("Prayer runs out never", 10, 60);
		}
		
	}

	@Override
	public int loop() {
		if(Game.isLoggedIn()) { 
		if(!Variables.hasLoaded) {
		System.out.println("Loading Dark Assist");

		Variables.startTime = System.currentTimeMillis();
		new Gui();
		Variables.hasLoaded = true;
		
		} 

		if(Variables.GuiDone){
		
		if(jobs == null) {
			jobs = new Tree(new Node[]{new Assist()});
		} else {
		
		final Node job = jobs.state();
		if(job != null) {
		jobs.set(job);
		getContainer().submit(job);
		job.join();
		}
		}

		}
		} else {
			System.out.println("Please login before starting script");
			Bot.getInstance().stopScript();
		}
		return Random.nextInt(200, 300);
	}

	@Override
	public void messageReceived(MessageEvent e) {

		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F6 ||
				e.getKeyCode() == KeyEvent.VK_F7 ||
				e.getKeyCode() == KeyEvent.VK_F8 ||
				e.getKeyCode() == KeyEvent.VK_F9 ||
				e.getKeyCode() == KeyEvent.VK_F10 ||
				e.getKeyCode() == KeyEvent.VK_F11 ||
				e.getKeyCode() == KeyEvent.VK_F12 ) {
			String keyinfo = null;
			
			if(e.getKeyCode() == KeyEvent.VK_F6) {
				keyinfo = Variables.auto.get("F6");	
			} else if(e.getKeyCode() == KeyEvent.VK_F7) {
				keyinfo = Variables.auto.get("F7");	
			} else if(e.getKeyCode() == KeyEvent.VK_F8) {
				keyinfo = Variables.auto.get("F8");	
			} else if(e.getKeyCode() == KeyEvent.VK_F9) {
				keyinfo = Variables.auto.get("F9");	
			} else if(e.getKeyCode() == KeyEvent.VK_F10) {
				keyinfo = Variables.auto.get("F10");	
			} else if(e.getKeyCode() == KeyEvent.VK_F11) {
				keyinfo = Variables.auto.get("F11");	
			} else if(e.getKeyCode() == KeyEvent.VK_F12) {
				keyinfo = Variables.auto.get("F12");	
			}
			
			
			String[] keyinfosplit = keyinfo.split(":");
			Tabs currentTab = Tabs.getCurrent();
			Mouse.setSpeed(Speed.VERY_FAST);
			if(keyinfosplit[0].equals("Autoswitch")) {
				Tabs.INVENTORY.open();
				for(int i : Variables.armourSlotWidgets) {				
					for(String s : keyinfosplit) {
						if(!s.equals("Autoswitch") && Integer.parseInt(s) != -1) {
							if(Inventory.contains(Integer.parseInt(s))) {
								Inventory.getItem(Integer.parseInt(s)).getWidgetChild().click(true);
								Task.sleep(1);
							}
						}
					}
				}
			} else if(keyinfosplit[0].equals("Specialattack")) {
				Tabs.ATTACK.open();
				Task.sleep(10,20);
				if(Widgets.get(884, 36) != null) {
					Widgets.get(884, 36).click(true);
				}
			} else if(keyinfosplit[0].equals("EatFood")) {
				Utils.eatFood();
			}  
			currentTab.open();
			Mouse.setSpeed(Speed.NORMAL);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
