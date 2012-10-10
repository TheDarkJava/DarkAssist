package me.DarkJava.DarkAssist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	
	private String[] keys = {"F6","F7","F8","F9","F10","F11","F12"};
	private String[] options = {"Autoswitch","Specialattack","EatFood"};
	
	public Gui() {
		
		final JFrame frame = new JFrame("Setup");
		frame.setSize(300, 400);
		
		JLabel label1 = new JLabel();
		label1.setText("Select Key");
		label1.setSize(100, 20);
		label1.setLocation(10, 10);
		
		JLabel label2 = new JLabel();
		label2.setText("Auto drink prayer potion");
		label2.setSize(160, 20);
		label2.setLocation(10, 55);
		
		JLabel label3 = new JLabel();
		label3.setText("Auto eat");
		label3.setSize(160, 20);
		label3.setLocation(10, 75);
		
		final JComboBox<String> combo1 = new JComboBox<String>();
		combo1.setSize(50,20);
		combo1.setLocation(10, 30);
		for(String key : keys) {
			combo1.addItem(key);
		}
		
		final JComboBox<String> combo2 = new JComboBox<String>();
		combo2.setSize(90,20);
		combo2.setLocation(65, 30);
		for(String option : options) {
			combo2.addItem(option);
		}
		
		
		
		JButton button1 = new JButton("Bind key");
		button1.setSize(120, 20);
		button1.setLocation(160, 30);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				

					String keyinfo = "";
					if(combo2.getSelectedItem().toString().equals("Autoswitch")) {
						keyinfo = "Autoswitch:";
					Tabs.EQUIPMENT.open();
					Task.sleep(2000, 2200);
					for(int i : Variables.armourSlotWidgets){
						keyinfo = keyinfo + Widgets.get(387, i).getChildId() + ":";
					}
							Variables.auto.put(combo1.getSelectedItem().toString(), keyinfo);

					
					} else if(combo2.getSelectedItem().toString().equals("Specialattack")) {
						keyinfo = "Specialattack:";
						Variables.auto.put(combo1.getSelectedItem().toString(), keyinfo);
					} else if(combo2.getSelectedItem().toString().equals("EatFood")) {
						keyinfo = "EatFood:";
						Variables.auto.put(combo1.getSelectedItem().toString(), keyinfo);
					}
			}
			
		});
		

		
		final JRadioButton radio1 = new JRadioButton();
		radio1.setLocation(130, 55);
		radio1.setSize(20, 20);
		radio1.setSelected(false);
		
		final JRadioButton radio2 = new JRadioButton();
		radio2.setLocation(130, 75);
		radio2.setSize(20, 20);
		radio2.setSelected(false);
		
		
		JButton button2 = new JButton("Start");
		button2.setSize(100, 20);
		button2.setLocation(70, 300);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(radio1.isSelected()) {
					Variables.autoDrinkPrayerPotion = true;
				}
				if(radio2.isSelected()) {
					Variables.autoEat = true;
				}

				Variables.GuiDone = true;
				frame.dispose();
				
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(combo1);
		panel.add(combo2);
		panel.add(button1);
		panel.add(button2);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(radio1);
		panel.add(radio2);
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);
		
	}

}
