package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    public GameOverScreen(Game game)
    {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        //font = new BitmapFont(Gdx.files.internal("font.fnt"));
        stage = new Stage();
    }

        //batch = new SpriteBatch();
        //stage = new Stage();
        //stage.addActor(yourBitmapFontName);
        //Skin mySkin = new Skin();
        //Label gameOver = new Label("Game Over", mySkin);


    public void resume()
    {

    }

    public void resize(int x, int y)
    {

    }
    //@Override
    public void render(float f)
    {
        batch.begin();
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, "Game Over", 25, 100);
        batch.end();
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
