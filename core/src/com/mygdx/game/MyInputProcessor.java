package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by jfabiano on 10/21/2016.
 */

public class MyInputProcessor implements InputProcessor {

    SnakeGame myGame = null;

    public MyInputProcessor(SnakeGame myGame) {
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
            // Some stuff
//            mouseX = Gdx.input.getX();
//            mouseY = Gdx.input.getY();
            System.out.println("**** clicking the UP button.");

//            myGame.setLastTouchedPositionX(Gdx.input.getX());
//            myGame.setLastTouchedPositionY(Gdx.input.getY());

//            System.out.println("last touched x = " + myGame.getLastTouchedPositionX());
//            System.out.println("last touched y = " + myGame.getLastTouchedPositionY());

            return true;
        }
        if (keycode == Input.Keys.DOWN) {
            // Some stuff
//            mouseX = Gdx.input.getX();
//            mouseY = Gdx.input.getY();
            System.out.println("**** clicking the DOWN button.");

//            myGame.setLastTouchedPositionX(Gdx.input.getX());
//            myGame.setLastTouchedPositionY(Gdx.input.getY());

//            System.out.println("last touched x = " + myGame.getLastTouchedPositionX());
//            System.out.println("last touched y = " + myGame.getLastTouchedPositionY());

            return true;
        }
        if (keycode == Input.Keys.LEFT) {
            // Some stuff
//            mouseX = Gdx.input.getX();
//            mouseY = Gdx.input.getY();
            System.out.println("**** clicking the LEFT button.");

//            myGame.setLastTouchedPositionX(Gdx.input.getX());
//            myGame.setLastTouchedPositionY(Gdx.input.getY());

//            System.out.println("last touched x = " + myGame.getLastTouchedPositionX());
//            System.out.println("last touched y = " + myGame.getLastTouchedPositionY());

            return true;
        }
        if (keycode == Input.Keys.RIGHT) {
            // Some stuff
//            mouseX = Gdx.input.getX();
//            mouseY = Gdx.input.getY();
            System.out.println("**** clicking the RIGHT button.");

//            myGame.setLastTouchedPositionX(Gdx.input.getX());
//            myGame.setLastTouchedPositionY(Gdx.input.getY());

//            System.out.println("last touched x = " + myGame.getLastTouchedPositionX());
//            System.out.println("last touched y = " + myGame.getLastTouchedPositionY());

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


