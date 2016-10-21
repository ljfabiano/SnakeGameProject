package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import javafx.scene.text.*;

import java.awt.*;

/**
 * Created by jfabiano on 10/16/2016.
 */
public class GameOverScreen implements Screen
{
    SpriteBatch batch;
    BitmapFont yourBitmapFontName;
    private Game game;
    Stage stage;
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    public GameOverScreen(Game game)
    {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        button = new TextButton("Button1", textButtonStyle);
        button.setPosition(75, 0);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);


    }

    public void resume()
    {

    }

    public void resize(int x, int y)
    {

    }
    @Override
    public void render(float f)
    {
        batch.begin();
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, "Game Over", 25, 100);
        batch.end();
        stage.draw();
    }

    public void pause()
    {

    }

    public void hide()
    {

    }

    public void dispose()
    {

    }

}
