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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SnakeGame extends Game {
	Game game;
    SpriteBatch batch;
	Texture img;
	ScreenGrid playGrid;
    Stage stage;
    TextButton playAgainButton;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;

    Stage startGameStage;
    TextButton beginGame;
    //TextButton.TextButtonStyle beginGameButtonStyle;

	ShapeRenderer myShape;
	Cell myCell;
    Cell myBody;
    Cell myFood;
    Coordinates currentCoordinate;
	int xPosition;
	int yPosition;
	int xDirectionalMovement;
	int yDirectionalMovement;
    boolean gameOver = false;
    boolean gameStarting = true;
    //Actor gridActor;

    public SnakeGame()
    {
        game = this;
    }
	
	@Override
	public void create () {
        //Initialize the SpriteBatch/ShapeRenderer
        batch = new SpriteBatch();
        //batch.setColor(0, 0, 1, 1);
        myShape = new ShapeRenderer();
        //Initialize the game grid, cell, and movement variables
		playGrid = new ScreenGrid();
        playGrid.setScreenHeight(Gdx.graphics.getHeight());
        playGrid.setScreenWidth(Gdx.graphics.getWidth());
        playGrid.setCoordinateGrid();
        myCell = new Cell("head");
        myCell.setX(0);
        myCell.setY(0);
        myBody = new Cell("body");
        myFood = new Cell("food");
        playGrid.addFoodCellToGrid(myFood);
        xPosition = 0;
        yPosition = 0;
        xDirectionalMovement = 0;
        yDirectionalMovement = 0;
        //Initialize the stage for menus/buttons
        stage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        playAgainButton = new TextButton("Click to play again", textButtonStyle);
        playAgainButton.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 10);
        stage.addActor(playAgainButton);

        startGameStage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        beginGame = new TextButton("Click to start the game", textButtonStyle);
        beginGame.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 10);
        startGameStage.addActor(beginGame);

//        Drawable myDrawable = new BaseDrawable();
//        myDrawable.draw(batch, Gdx.graphics.getWidth()/2 - 40, Gdx.graphics.getHeight()/2 - 60, 80, 30);
//        beginGameButtonStyle = new Button.ButtonStyle();
//        beginGame = new Button(beginGameButtonStyle);
//        beginGame.setPosition(Gdx.graphics.getWidth()/2 - 40, Gdx.graphics.getHeight()/2 - 60);
//        stage.addActor(beginGame);
        if(gameStarting == true)
        {
            Gdx.input.setInputProcessor(startGameStage);
        }
        else
        {
            Gdx.input.setInputProcessor(stage);
        }
        //Create a listener for the play again button
        playAgainButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
//                buttonSelectCounter++;
                System.out.println("Play again button pressed");
                gameOver = false;
                create();
//                playAgainButton.setText("playAgainButton has been selected " + buttonSelectCounter + " times.");
            }
        });
        beginGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
//                buttonSelectCounter++;
                System.out.println("Begin game button pressed");
                gameStarting = false;
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

        if(gameStarting == true)
        {
            startGameStage.draw();
        }
        else {

            if (gameOver == true) {
                stage.draw();
            } else {
                try {
                    //moveInGridCell();
                    moveCellGrid();
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                    System.out.println("game over! Hit a wall!");
                    gameOver = true;
                    //GameOverScreen gameOver = new GameOverScreen();
                    //this.setScreen(new GameOverScreen(this.screen));
                    //game.setScreen(new GameOverScreen(game));
                    //dispose();
                    //exit();
                    //return;
                    //game.getScreen();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    System.out.println("game over! Hit a snake head or body!");
                    gameOver = true;
                }
                ShapeRenderer myShape = new ShapeRenderer();
                myShape.begin(ShapeRenderer.ShapeType.Filled);
                myShape.setColor(0, 1, 0, 1);
                myShape.rect(myCell.getX() * myCell.getCellSize(), myCell.getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
                //this is causing a tail to appear, but it is not following the head, and it is continuously growing along the x/y axis' at the same rate when the user moves along the x axis
                if(!myCell.getBody().isEmpty())
                {
                    for (int index = myCell.getBreadCrumbsList().size() - 1; index >= 0; index--)
                    //for (int index = 1; index <= myCell.getLength(); index++)
                    {
                        myShape.rect(myCell.getBreadCrumbsList().get(index).getX() * myCell.getCellSize(), myCell.getBreadCrumbsList().get(index).getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
                    }
                }
                myShape.setColor(0, 0, 1, 1);
                myShape.rect(myFood.getX() * myFood.getCellSize(), myFood.getY() * myFood.getCellSize(), myFood.getCellSize(), myFood.getCellSize());
                myShape.end();
            }
            //myShape.rect(myCell.getX() * myCell.getCellSize(), myCell.getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
        }


		//batch.draw(img, 0, 0);
		batch.end();
        //stage.draw();
		//super.render();
        if(myCell.getBreadCrumbsList().size() > 0)
        {
            for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                System.out.println("index = " + index);
                System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
            }
        }
    }
	
	@Override
	public void dispose () {
        batch.dispose();
        myShape.dispose();
		//img.dispose();
	}

//    Cell createTestCell(int x, int y)
//    {
//        Cell gridCell = new Cell();
//		gridCell.setX(x);
//		gridCell.setY(y);
//        return gridCell;
//    }
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
    void moveInGridCell()throws Exception//ArrayIndexOutOfBoundsException
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

    void moveCellGrid()throws Exception//ArrayIndexOutOfBoundsException
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
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            //Cell bodyCell = new Cell();
            //myCell.addBodyCellToList(bodyCell);
            playGrid.moveGridCellRight(myCell);
        }
        if(yPosition >= myCell.getY() * myCell.getCellSize() + myCell.getCellSize()) {
            //myCell.setY(yPosition);
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            //Cell bodyCell = new Cell();
            //myCell.addBodyCellToList(bodyCell);
            playGrid.moveGridCellUp(myCell);
        }
        if(xPosition < myCell.getX() * myCell.getCellSize()) {
            //myCell.setX(myCell.getX() - myCell.getCellSize());
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            //Cell bodyCell = new Cell();
            //myCell.addBodyCellToList(bodyCell);
            playGrid.moveGridCellLeft(myCell);
        }
        if(yPosition < myCell.getY() * myCell.getCellSize()) {
            //myCell.setY(myCell.getY() - myCell.getCellSize());
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            //Cell bodyCell = new Cell();
            //myCell.addBodyCellToList(bodyCell);
            playGrid.moveGridCellDown(myCell);
        }
    }
    public void exit()
    {
        this.exit();
    }

}
