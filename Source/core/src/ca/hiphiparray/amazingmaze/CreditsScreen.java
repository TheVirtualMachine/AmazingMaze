/********************************************************************************
 * Amazing Maze is an educational game created in Java with the libGDX library.
 * Copyright (C) 2017 Hip Hip Array
 *
 * This file is part of Amazing Maze.
 *
 * Amazing Maze is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Amazing Maze is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Amazing Maze. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package ca.hiphiparray.amazingmaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import ca.hiphiparray.amazingmaze.MusicManager.Song;

/**
 * The credits screen.
 *
 * @since 0.3
 * @author Vincent Macri
 * <br>
 * Time (Vincent): 2 hours
 */
public class CreditsScreen implements Screen {

	/** How long to wait between credits items. */
	private static final float ITEM_DELAY = 5f;

	/** The {@link AmazingMazeGame} instance that is managing this screen. */
	private final AmazingMazeGame game;

	/** The stage to display the credits actors on. */
	private Stage stage;

	/** The table for containing the components in {@link #stage}. */
	private Table table;

	/** The game's logo. */
	private Image gameLogo;
	/** The company's logo. */
	private Image companyLogo;

	/** The {@link Label} explaining the role of the people currently on the screen. */
	private Label header;

	/** Label of who coded the game */
	private Label codeGroup;
	/** Label of who did the art */
	private Label artGroup;
	/** Label grouping of who wrote the story */
	private Label storyGroup;
	/** Label of who did the music */
	private Label musicGroup;
	/** Label of who to thank */
	private Label thanksGroup;

	private static final String[] HEADERS = {"", "Code", "Art", "Story", "Music", "Thanks", ""};

	/** The components to display. */
	private Actor[] components;

	/** The index of the components currently being displayed from {@link #components}. */
	private int currentComponentIndex = 0;

	/** The total number of components. */
	private static final int COMPONENT_COUNT = 7;

	/** Who coded the game. */
	private static final String[] CODE = {
		"Vincent Macri",
		"Chloe Nguyen",
		"Susie Son"
	};
	/** Who did the art. */
	private static final String[] ART = {"Susie Son"};
	/** Who wrote the story. */
	private static final String[] STORY = {
		"Vincent Macri",
		"Chloe Nguyen",
		"Susie Son"
	};
	/** Who did the music. */
	private static final String[] MUSIC = {
		"\"Babylon\", \"Digital Lemonade\", \"Lightless Dawn\", \"Secrets of the Schoolyard\", \"Vanes\"",
		"Kevin MacLeod (incompetech.com)",
		"Licensed under Creative Commons: By Attribution 3.0",
		"http://creativecommons.org/licenses/by/3.0/",
	};

	/** Who to thank. */
	private static final String[] THANKS = {
		"The libGDX Team",
		"Ms. Krasteva"
	};

	/**
	 * Create the credits screen.
	 *
	 * @param game the {@link AmazingMazeGame} instance that is managing this screen.
	 */
	public CreditsScreen(final AmazingMazeGame game) {
		this.game = game;

		setupComponents();
		createActions();
		components = new Actor[] {gameLogo, codeGroup, artGroup, storyGroup, musicGroup, thanksGroup, companyLogo};
		assert components.length == COMPONENT_COUNT : "Number of components does not match COMPONENT_COUNT.";
		assert HEADERS.length == COMPONENT_COUNT : "Number of headers does not match COMPONENT_COUNT.";
		updateComponents();
	}

	/** Create the actions of the credits actors. */
	private void createActions() {
		gameLogo.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		codeGroup.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		artGroup.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		storyGroup.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		musicGroup.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		thanksGroup.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		companyLogo.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
	}

	/**
	 * Instantiate the stage and its actors for the credits screen.
	 * Add all actors to the stage.
	 */
	private void setupComponents() {
		stage = new Stage();
		table = new Table();
		table.top();
		table.setFillParent(true);
		stage.addActor(table);

		gameLogo = new Image(game.assets.manager.get(Assets.GAME_LOGO, Texture.class));

		header = new Label("", game.assets.skin, Assets.SERIF_HEADER_STYLE);
		codeGroup = setupCreditsLabel(CODE, Assets.CREDITS_CONTENTS);
		artGroup = setupCreditsLabel(ART, Assets.CREDITS_CONTENTS);
		storyGroup = setupCreditsLabel(STORY, Assets.CREDITS_CONTENTS);
		musicGroup = setupCreditsLabel(MUSIC, Assets.SMALL_CREDITS_CONTENTS);
		thanksGroup = setupCreditsLabel(THANKS, Assets.CREDITS_CONTENTS);

		companyLogo = new Image(game.assets.manager.get(Assets.COMPANY_LOGO, Texture.class));
	}

	/**
	 * Helper method to create centred labels.
	 *
	 * @param lines the lines to put in the label. Assumed to have at least one element.
	 * @param skin the skin to load the style from.
	 * @return a new {@link Label} with the given properties.
	 */
	private Label setupCreditsLabel(String[] lines, String style) {
		String s = lines[0];
		for (int i = 1; i < lines.length; i++) {
			s += "\n" + lines[i];

		}
		Label label = new Label(s, game.assets.skin, style);
		label.setAlignment(Align.center);
		return label;
	}

	/** Advance the credits screen, or go back to the main menu if it is finished. */
	private void advanceCredits() {
		currentComponentIndex++;
		if (currentComponentIndex >= COMPONENT_COUNT) {
			game.setScreen(game.menuScreen);
			return;
		}

		updateComponents();

	}

	/**
	 * Set the contents of {@link #table} based on {@link #currentComponentIndex}.
	 * Increment {@link #currentComponentIndex}.
	 */
	private void updateComponents() {
		table.clear();
		header.setText(HEADERS[currentComponentIndex]);
		header.addAction(Actions.sequence(Actions.alpha(0), Actions.delay(1f), Actions.fadeIn(1f), Actions.delay(ITEM_DELAY), Actions.fadeOut(1f)));
		table.add(header);
		table.row();
		table.add(components[currentComponentIndex]).expand();
	}

	@Override
	public void show() {
		game.music.setSong(Song.CREDITS);
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!components[currentComponentIndex].hasActions()) {
			advanceCredits();
		}

		stage.act(delta);

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
