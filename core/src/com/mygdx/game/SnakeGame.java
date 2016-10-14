package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	Cell myCell;
	int xPosition = 0;
	int yPosition = 0;
	int xDirectionalMovement = 0;
	int yDirectionalMovement = 0;
    //Actor gridActor;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		playGrid = new ScreenGrid();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		myShape = new ShapeRenderer();
		myCell = createTestCell(0, 0);
        //gridActor = new Actor();
        //gridActor.setBounds(x, y, width, height);
        //stage.addActor(gridActor);


	}

	@Override
	public void render () {

		movePerGridCell();
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


		//myShape.rect(myCell.getX(), myCell.getY(), myCell.getCellSize(), myCell.getCellSize());
		myShape.rect(myCell.getX(), myCell.getY(), myCell.getCellSize(), myCell.getCellSize());
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

	void movePerPixel() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			xDirectionalMovement = 0;
			yDirectionalMovement = 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			xDirectionalMovement = 0;
			yDirectionalMovement = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xDirectionalMovement = 1;
			yDirectionalMovement = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xDirectionalMovement = -1;
			yDirectionalMovement = 0;
		}
		xPosition += xDirectionalMovement;
		yPosition += yDirectionalMovement;
	}
	void movePerGridCell()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			xDirectionalMovement = 0;
			yDirectionalMovement = 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			xDirectionalMovement = 0;
			yDirectionalMovement = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xDirectionalMovement = 1;
			yDirectionalMovement = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xDirectionalMovement = -1;
			yDirectionalMovement = 0;
		}
		xPosition += xDirectionalMovement;
		yPosition += yDirectionalMovement;
		if(xPosition == myCell.getX() + myCell.getCellSize() || xPosition == myCell.getX() - myCell.getCellSize()) {
			myCell.setX(xPosition);
		}
		if(yPosition == myCell.getY() + myCell.getCellSize() || yPosition == myCell.getY() - myCell.getCellSize()) {
			myCell.setY(yPosition);
		}
	}
}
