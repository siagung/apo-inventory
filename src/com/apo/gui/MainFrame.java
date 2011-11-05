package com.apo.gui;

import java.awt.Graphics2D;
import java.awt.SplashScreen;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private final String TAG = this.getClass().getCanonicalName();
	
	public MainFrame (String title) {
		super (title);
		final SplashScreen splash = SplashScreen.getSplashScreen();
		if (splash == null) {
			System.out.println(TAG + "Sorry, no splash screen.");
			return;
		}
		Graphics2D graphics = splash.createGraphics();
		if (graphics == null) {
			System.out.println(TAG + "Sorry, g is null.");
			return;
		}
	}
	
	public static void main (String[] args) {
		MainFrame mf = new MainFrame("AP Oriental Industrial Trading, Inc.");
	}
	
}
