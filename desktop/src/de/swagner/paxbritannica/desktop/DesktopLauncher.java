package de.swagner.paxbritannica.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.swagner.paxbritannica.PaxBritannica;

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 550;
		config.title = "Pax Britannica";
		new LwjglApplication(new PaxBritannica(), config);
	}
}
