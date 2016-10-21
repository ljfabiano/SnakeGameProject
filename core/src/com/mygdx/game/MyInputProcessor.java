package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by jfabiano on 10/21/2016.
 */

public class MyInputProcessor extends Stage implements InputProcessor {

    SnakeGame myGame = null;

    public MyInputProcessor() {
        super();
    }

    public MyInputProcessor(SnakeGame myGame) {
        super();
        this.myGame = myGame;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {

        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }
    @Override
    public boolean keyDown (int keycode) {
        System.out.println("Key Down method *****");

        if (keycode == Input.Keys.UP) {

            System.out.println("**** clicking the UP button.");

            if(myGame.yDirectionalMovement == -1)
            {
                myGame.xDirectionalMovement = 0;
                myGame.yDirectionalMovement = -1;
            }
            else
            {
                myGame.xDirectionalMovement = 0;
                myGame.yDirectionalMovement = 1;
            }

            return true;
        }
        if (keycode == Input.Keys.DOWN) {

            System.out.println("**** clicking the DOWN button.");

            if(myGame.yDirectionalMovement == 1)
            {
                myGame.xDirectionalMovement = 0;
                myGame.yDirectionalMovement = 1;
            }
            else
            {
                myGame.xDirectionalMovement = 0;
                myGame.yDirectionalMovement = -1;
            }

            return true;
        }
        if (keycode == Input.Keys.LEFT) {

            System.out.println("**** clicking the LEFT button.");

            if(myGame.xDirectionalMovement == 1)
            {
                myGame.xDirectionalMovement = 1;
                myGame.yDirectionalMovement = 0;
            }
            else
            {
                myGame.xDirectionalMovement = -1;
                myGame.yDirectionalMovement = 0;
            }

            return true;
        }
        if (keycode == Input.Keys.RIGHT) {

            System.out.println("**** clicking the RIGHT button.");

            if(myGame.xDirectionalMovement == -1)
            {
                myGame.xDirectionalMovement = -1;
                myGame.yDirectionalMovement = 0;
            }
            else
            {
                myGame.xDirectionalMovement = 1;
                myGame.yDirectionalMovement = 0;
            }

            return true;
        }
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }

}



