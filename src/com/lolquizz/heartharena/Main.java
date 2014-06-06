//package com.lolquizz.heartharena;
//
//
//
//import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//
//public class Main {
//	public static void main(String[] args) {
//		
//		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
//		cfg.title = "Heart Arena";
//		cfg.useGL30 = true;
//		cfg.width = 1280;
//		cfg.height = 720;
//		
//		new LwjglApplication(new Arena(), cfg);
//
//	}
//}
package com.lolquizz.heartharena;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class Main extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGLSurfaceView20API18 = true;
        
		initialize(new Arena(), cfg);
	}
}
