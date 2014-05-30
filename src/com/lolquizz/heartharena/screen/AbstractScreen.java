package com.lolquizz.heartharena.screen;



import com.badlogic.gdx.Screen;
import com.lolquizz.heartharena.Arena;

public abstract class AbstractScreen implements Screen {
	
	protected Arena game;

	public AbstractScreen(Arena game) {
		this.game = game;
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}

}
