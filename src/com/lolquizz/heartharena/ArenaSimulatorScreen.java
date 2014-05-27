package com.lolquizz.heartharena;

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


public class ArenaSimulatorScreen extends AbstractScreen {
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private Button buttonExit;
	private ButtonStyle buttonStyleExit;
	private Button card1;
	private Button card2;
	private Button card3;

	public ArenaSimulatorScreen(Arena game) {
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
		
		stage.act(); // stage.act(arg0);
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
		
		card1 = createCard(skin,"1");
		card2 = createCard(skin,"2");
		card3 = createCard(skin,"3");
	
		buttonStyleExit = new ButtonStyle();
		buttonStyleExit.up = skin.getDrawable("exit");
		buttonExit = new Button(buttonStyleExit);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		table.add(card1);
		table.getCell(card1).spaceBottom(100);
		table.add(card2);
		table.getCell(card2).spaceBottom(100);
		table.add(card3);
		table.getCell(card3).spaceBottom(100);
		table.row();
		table.add();
		table.add(buttonExit);
		table.getCell(buttonExit).spaceBottom(10);
		table.row();
		
		table.debug(); // REMOVE LATER
		stage.addActor(table);

	}
	
	public Button createCard (Skin piel, String img) {
		
		Button card = new Button();
		ButtonStyle cardStyle = new  ButtonStyle();
		
		cardStyle.up = piel.getDrawable(img);
		card = new Button(cardStyle);
		card.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		return card;
	}

}
