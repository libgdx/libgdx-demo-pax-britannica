package de.swagner.paxbritannica.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.swagner.paxbritannica.PaxBritannica;

public class DesktopLauncher {
	public static void main(String[] args) {
		if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1024, 550);
		config.setTitle("Pax Britannica");
		new Lwjgl3Application(new PaxBritannica(), config);
	}
}
