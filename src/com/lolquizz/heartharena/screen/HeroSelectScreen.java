package com.lolquizz.heartharena.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lolquizz.heartharena.Arena;


public class HeroSelectScreen extends AbstractScreen {
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private Button buttonHero;
	private ButtonStyle buttonStyleHero;
	private Button buttonExit;
	private ButtonStyle buttonStyleExit;

	public HeroSelectScreen(Arena game) {
		super(game);
	}

	@Override
	public void dispose() {
		stage.dispose();

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void render(float arg0) {
	    Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Table.drawDebug(stage); // REMOVE LATER
		
		stage.act(); // stage.act(delta);
		stage.draw();

	}


	@Override
	public void show() {
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("button/button.atlas");
		skin = new Skin(atlas);
		
		table = new Table(); // Se puede hacer Table(skin)
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		buttonStyleHero = new ButtonStyle();
		buttonStyleHero.up = skin.getDrawable("button");
		buttonStyleHero.down = skin.getDrawable("buttonpressed");
		
		buttonStyleExit = new ButtonStyle();
		buttonStyleExit.up = skin.getDrawable("exit");
		
		
		buttonHero = new Button(buttonStyleHero);
		buttonHero.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Arena) Gdx.app.getApplicationListener()).setScreen(game.ARENASIMULATOR);				
			}
		});
		
		buttonExit = new Button(buttonStyleExit);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		table.add(buttonHero);
		table.getCell(buttonHero).spaceBottom(10);
		table.row();
		table.add(buttonExit);
		table.getCell(buttonExit).spaceBottom(10);
		table.row();

		table.debug(); // REMOVE LATER
		stage.addActor(table);
		
	}

}
