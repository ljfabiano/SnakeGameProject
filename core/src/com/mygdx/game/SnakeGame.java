package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ScreenGrid playGrid;
    Stage stage;
	ShapeRenderer myShape;
    //Actor gridActor;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		playGrid = new ScreenGrid();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		myShape = new ShapeRenderer();
        //gridActor = new Actor();
        //gridActor.setBounds(x, y, width, height);
        //stage.addActor(gridActor);


	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		ShapeRenderer myShape = new ShapeRenderer();
		myShape.begin(ShapeRenderer.ShapeType.Filled);
		myShape.setColor(0, 1, 0, 1);
		playGrid.setScreenHeight(Gdx.graphics.getHeight());
		playGrid.setScreenWidth(Gdx.graphics.getWidth());

		//int drawPositionY = playGrid.getScreenHeight() - lastTouchedPositionY;
		//myShape.rect(lastTouchedPositionX, drawPositionY, 100, 100);

		Cell myCell = createTestCell(20, 30);
		//myShape.rect(myCell.getX(), myCell.getY(), myCell.getCellSize(), myCell.getCellSize());
		myShape.rect(20, 30, 50, 50);
		myShape.end();


		//batch.draw(img, 0, 0);
		batch.end();

		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}

    Cell createTestCell(int x, int y)
    {
        Cell gridCell = new Cell();
		gridCell.setX(x);
		gridCell.setY(y);
        return gridCell;
    }



}
