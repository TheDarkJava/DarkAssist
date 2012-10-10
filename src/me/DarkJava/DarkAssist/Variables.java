package me.DarkJava.DarkAssist;

import java.util.HashMap;



public class Variables {

	public static long startTime;
	
	public static String status = "none";
	
	public static Boolean hasLoaded = false;
	
	public static Boolean GuiDone = false;
	
	public static Boolean autoDrinkPrayerPotion = false;
	
	public static boolean autoEat = false;
	
	public static HashMap<Integer,Integer> itemsLoaded = new HashMap<Integer,Integer>();
	
	public static int[] prayerPotionIds = {139,141,143,2434};
	
	//lobster,monkfish,shark,rocktail
	
	public static int[] foodIds = {379,7946,385,15272};
    
    public static int[] armourSlotWidgets = {6,9,12,15,18,21,24,27,30,33,36,45};
	
	public static HashMap<String, String> auto = new HashMap<String, String>();
	
	
}
