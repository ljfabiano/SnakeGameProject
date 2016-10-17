package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SnakeGame extends Game {
	Game game;
    SpriteBatch batch;
	Texture img;
	ScreenGrid playGrid;
    Stage stage;
    TextButton playAgainButton;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
	ShapeRenderer myShape;
	Cell myCell;
	int xPosition;
	int yPosition;
	int xDirectionalMovement;
	int yDirectionalMovement;
    boolean gameOver = false;
    //Actor gridActor;

    public SnakeGame()
    {
        game = this;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		playGrid = new ScreenGrid();
        xPosition = 0;
        yPosition = 0;
        xDirectionalMovement = 0;
        yDirectionalMovement = 0;
        stage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        playAgainButton = new TextButton("Click to play again", textButtonStyle);
        playAgainButton.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 10);
        stage.addActor(playAgainButton);
        Gdx.input.setInputProcessor(stage);
		myShape = new ShapeRenderer();
		myCell = createTestCell(0, 0);
        playGrid.setScreenHeight(Gdx.graphics.getHeight());
    	playGrid.setScreenWidth(Gdx.graphics.getWidth());
        playGrid.setCoordinateGrid();

        playAgainButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
//                buttonSelectCounter++;
                System.out.println("Button Pressed");
                gameOver = false;
                create();
//                playAgainButton.setText("playAgainButton has been selected " + buttonSelectCounter + " times.");
            }
        });
        //Used to disable the continuous calling of the render method, and requestRendering calls the render method just once.
//        Gdx.graphics.setContinuousRendering(false);
//        Gdx.graphics.requestRendering();

        //gridActor = new Actor();
        //gridActor.setBounds(x, y, width, height);
        //stage.addActor(gridActor);


	}

	@Override
	public void render () {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//movePerGridCell();
        batch.begin();
//		playGrid.setScreenHeight(Gdx.graphics.getHeight());
//		playGrid.setScreenWidth(Gdx.graphics.getWidth());
		//int drawPositionY = playGrid.getScreenHeight() - lastTouchedPositionY;
		//myShape.rect(lastTouchedPositionX, drawPositionY, 100, 100);
//if else to control what is being drawn/rendered.
		//myShape.rect(myCell.getX(), myCell.getY(), myCell.getCellSize(), myCell.getCellSize());
        if(gameOver == true)
        {
            stage.draw();
        }
        else
        {
            try
            {
                //moveInGridCell();
                moveCellGrid();
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("game over!");
                gameOver = true;
                //GameOverScreen gameOver = new GameOverScreen();
                //this.setScreen(new GameOverScreen(this.screen));
                //game.setScreen(new GameOverScreen(game));
                //dispose();
                //exit();
                //return;
                //game.getScreen();


            }
            ShapeRenderer myShape = new ShapeRenderer();
            myShape.begin(ShapeRenderer.ShapeType.Filled);
            myShape.setColor(0, 1, 0, 1);
            myShape.rect(myCell.getX() * myCell.getCellSize(), myCell.getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
            myShape.end();
        }
        //myShape.rect(myCell.getX() * myCell.getCellSize(), myCell.getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());



		//batch.draw(img, 0, 0);
		batch.end();
        //stage.draw();
		//super.render();
	}
	
	@Override
	public void dispose () {
        batch.dispose();
        myShape.dispose();
		//img.dispose();
	}

    Cell createTestCell(int x, int y)
    {
        Cell gridCell = new Cell();
		gridCell.setX(x);
		gridCell.setY(y);
        return gridCell;
    }
    //Per pixel movement method
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
	//Per pixel movement, but the image is updated once the length of the cell + the previous position is reached(simulating the grid)
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
        // || xPosition > myCell.getX()
		if(xPosition == myCell.getX() + myCell.getCellSize()) {
			myCell.setX(xPosition);
		}
		if(yPosition == myCell.getY() + myCell.getCellSize()) {
			myCell.setY(yPosition);
		}
        if(xPosition < myCell.getX()) {
            myCell.setX(myCell.getX() - myCell.getCellSize());
        }
        if(yPosition < myCell.getY()) {
            myCell.setY(myCell.getY() - myCell.getCellSize());
        }
	}
    void moveInGridCell()throws ArrayIndexOutOfBoundsException
    {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(yDirectionalMovement == -1)
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = -1;
            }
            else
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(yDirectionalMovement == 1)
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = 1;
            }
            else
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = -1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(xDirectionalMovement == -1)
            {
                xDirectionalMovement = -1;
                yDirectionalMovement = 0;
            }
            else
            {
                xDirectionalMovement = 1;
                yDirectionalMovement = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(xDirectionalMovement == 1)
            {
                xDirectionalMovement = 1;
                yDirectionalMovement = 0;
            }
            else
            {
                xDirectionalMovement = -1;
                yDirectionalMovement = 0;
            }
        }
        xPosition += xDirectionalMovement;
        yPosition += yDirectionalMovement;
        if(yDirectionalMovement == 1)
        {
                playGrid.moveGridCellUp(myCell);
        }
        if(yDirectionalMovement == -1)
        {
                playGrid.moveGridCellDown(myCell);
        }
        if(xDirectionalMovement == 1)
        {
                playGrid.moveGridCellRight(myCell);
        }
        if(xDirectionalMovement == -1)
        {
                playGrid.moveGridCellLeft(myCell);
        }

    }

    void moveCellGrid()throws ArrayIndexOutOfBoundsException
    {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(yDirectionalMovement == -1)
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = -1;
            }
            else
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(yDirectionalMovement == 1)
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = 1;
            }
            else
            {
                xDirectionalMovement = 0;
                yDirectionalMovement = -1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(xDirectionalMovement == -1)
            {
                xDirectionalMovement = -1;
                yDirectionalMovement = 0;
            }
            else
            {
                xDirectionalMovement = 1;
                yDirectionalMovement = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(xDirectionalMovement == 1)
            {
                xDirectionalMovement = 1;
                yDirectionalMovement = 0;
            }
            else
            {
                xDirectionalMovement = -1;
                yDirectionalMovement = 0;
            }
        }
        xPosition += xDirectionalMovement;
        yPosition += yDirectionalMovement;
        // || xPosition > myCell.getX()
        if(xPosition >= myCell.getX() * myCell.getCellSize() + myCell.getCellSize()) {
            //myCell.setX(xPosition);
            playGrid.moveGridCellRight(myCell);
        }
        if(yPosition >= myCell.getY() * myCell.getCellSize() + myCell.getCellSize()) {
            //myCell.setY(yPosition);
            playGrid.moveGridCellUp(myCell);
        }
        if(xPosition < myCell.getX() * myCell.getCellSize()) {
            //myCell.setX(myCell.getX() - myCell.getCellSize());
            playGrid.moveGridCellLeft(myCell);
        }
        if(yPosition < myCell.getY() * myCell.getCellSize()) {
            //myCell.setY(myCell.getY() - myCell.getCellSize());
            playGrid.moveGridCellDown(myCell);
        }
    }
    public void exit()
    {
        this.exit();
    }

}
