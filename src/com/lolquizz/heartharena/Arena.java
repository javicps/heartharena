package com.lolquizz.heartharena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Arena extends Game {
	
	public static final String TITLE = "Hearth Arena" , VERSION = "0.0.0";
	public final AbstractScreen HEROSELECT, ARENASIMULATOR;
	
	public Arena() {
		HEROSELECT = new HeroSelectScreen(this);
		ARENASIMULATOR = new ArenaSimulatorScreen(this);
	}
	
	@Override
	public void create() {
		
		Gdx.app.log(TITLE, "create()");
		setScreen(HEROSELECT);
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log(TITLE, "dispose()");

	}

	@Override
	public void render() {
		super.render();	
		Gdx.app.log(TITLE, "render()");

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(TITLE, "resize()");

	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}