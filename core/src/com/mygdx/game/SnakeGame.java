package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SnakeGame extends Game {
	Game game;
    MyInputProcessor myProcessor;
    SpriteBatch batch;
	Texture img;
	ScreenGrid playGrid;
    Stage gamePlayStage;
    Stage endGameStage;

    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;

    Stage startGameStage;
    TextButton beginSinglePlayerGame;
    TextButton beginTwoPlayerGame;
    TextButton exitGame;
    TextButton playAgainButton;
    TextButton returnToMainMenu;

	ShapeRenderer myShape;
    //first snake
	Cell myCell;
    Cell myBody;
    Cell myFood;
    Coordinates currentCoordinate;
	int xPosition;
	int yPosition;
	int xDirectionalMovement;
	int yDirectionalMovement;

    //second snake
    Cell snakeHeadP2;
    int xPositionP2;
    int yPositionP2;
    int xDirectionalMovementP2;
    int yDirectionalMovementP2;

    boolean gameOver = false;
    boolean gameStarting = true;
    boolean singlePlayerGame = false;
    boolean twoPlayerGame = false;
    public SnakeGame()
    {
        game = this;
    }
	
	@Override
	public void create () {
        //Initialize the SpriteBatch/ShapeRenderer
        batch = new SpriteBatch();
        myShape = new ShapeRenderer();
        //Initialize the game grid, cell, and movement variables
		playGrid = new ScreenGrid();
        playGrid.setScreenHeight(Gdx.graphics.getHeight());
        playGrid.setScreenWidth(Gdx.graphics.getWidth());
        playGrid.setCoordinateGrid();
        //initialize first snake
        myCell = new Cell("head");
        myCell.setX(0);
        myCell.setY(0);
        xPosition = 0;
        yPosition = 0;
        xDirectionalMovement = 0;
        yDirectionalMovement = 0;

        playGrid.addCellToGrid(myCell);

        if(twoPlayerGame == true)
        {
            //Initialize second snake
            snakeHeadP2 = new Cell("head");
            snakeHeadP2.setX(63);
            snakeHeadP2.setY(47);
            xPositionP2 = 630;
            yPositionP2 = 470;
            xDirectionalMovementP2 = 0;
            yDirectionalMovementP2 = 0;

            playGrid.addCellToGrid(snakeHeadP2);
        }
        //initialize a body and food cell
        myFood = new Cell("food");
        playGrid.addFoodCellToGrid(myFood);
        //Initialize the endGameStage for menus/buttons
        endGameStage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        playAgainButton = new TextButton("Play Again", textButtonStyle);
        playAgainButton.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 + 20);
        returnToMainMenu = new TextButton("Main Menu", textButtonStyle);
        returnToMainMenu.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 20);
        endGameStage.addActor(playAgainButton);
        endGameStage.addActor(returnToMainMenu);

        startGameStage = new Stage();
        font = new BitmapFont();
        beginSinglePlayerGame = new TextButton("Single Player Game", textButtonStyle);
        beginSinglePlayerGame.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 + 40);
        beginTwoPlayerGame = new TextButton("Two Player Game", textButtonStyle);
        beginTwoPlayerGame.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2);
        exitGame = new TextButton("Exit", textButtonStyle);
        exitGame.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 40);
        startGameStage.addActor(beginSinglePlayerGame);
        startGameStage.addActor(beginTwoPlayerGame);
        startGameStage.addActor(exitGame);

        gamePlayStage = new Stage();

        //MyInputProcessor myProcessor;
//        if(gameStarting == true)
//        {
//            MyInputProcessor myProcessor = new MyInputProcessor(this);
//            Gdx.input.setInputProcessor(myProcessor);

//            Gdx.input.setInputProcessor(startGameStage);
            MyInputProcessor myProcessor = new MyInputProcessor(this);
            InputMultiplexer multiplexer = new InputMultiplexer();
            multiplexer.addProcessor(startGameStage);
            multiplexer.addProcessor(myProcessor);
            multiplexer.addProcessor(endGameStage);
            Gdx.input.setInputProcessor(multiplexer);
//        }
//        else if(gameOver == true)
//        {
//            myProcessor = null;
//            Gdx.input.setInputProcessor(endGameStage);
//        }
//        else
//        {
//            MyInputProcessor myProcessor = new MyInputProcessor(this);
//            Gdx.input.setInputProcessor(myProcessor);
//        }

        //Create a listener for the play again button
        playAgainButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Play again button pressed");
                gameOver = false;
                create();
            }
        });

        returnToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Return to main menu button pressed");
                gameOver = false;
                gameStarting = true;
                singlePlayerGame = false;
                twoPlayerGame = false;
                create();
            }
        });
        beginSinglePlayerGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Begin single player game button pressed");
                gameStarting = false;
                singlePlayerGame = true;
                twoPlayerGame = false;
                create();
            }
        });
        beginTwoPlayerGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Begin two player game button pressed");
                gameStarting = false;
                singlePlayerGame = false;
                twoPlayerGame = true;
                create();
            }
        });
        exitGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Exit game button pressed");
                exit();
            }
        });
        //Used to disable the continuous calling of the render method, and requestRendering calls the render method just once.
//        Gdx.graphics.setContinuousRendering(false);
//        Gdx.graphics.requestRendering();
	}

	@Override
	public void render () {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(gameStarting == true)
        {
            startGameStage.draw();
        }
        else {

            if (gameOver == true) {
                endGameStage.draw();
            } else {
                //gamePlayStage.draw();
                try {
                    if(singlePlayerGame == true)
                    {
                        moveCellGrid();
                    }
                    else if(twoPlayerGame == true)
                    {
                        moveCellGridTwoSnakes();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                    System.out.println("game over! Hit a wall!");
                    gameOver = true;
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
                    {
                        myShape.rect(myCell.getBreadCrumbsList().get(index).getX() * myCell.getCellSize(), myCell.getBreadCrumbsList().get(index).getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
                    }
                }
                if(twoPlayerGame == true)
                {
                    myShape.setColor(0, 0, 0, 1);
                    myShape.rect(snakeHeadP2.getX() * snakeHeadP2.getCellSize(), snakeHeadP2.getY() * snakeHeadP2.getCellSize(), snakeHeadP2.getCellSize(), snakeHeadP2.getCellSize());
                    //this is causing a tail to appear, but it is not following the head, and it is continuously growing along the x/y axis' at the same rate when the user moves along the x axis
                    if (!snakeHeadP2.getBody().isEmpty()) {
                        for (int index = snakeHeadP2.getBreadCrumbsList().size() - 1; index >= 0; index--)
                        {
                            myShape.rect(snakeHeadP2.getBreadCrumbsList().get(index).getX() * snakeHeadP2.getCellSize(), snakeHeadP2.getBreadCrumbsList().get(index).getY() * snakeHeadP2.getCellSize(), snakeHeadP2.getCellSize(), snakeHeadP2.getCellSize());
                        }
                    }
                }
                myShape.setColor(0, 0, 1, 1);
                myShape.rect(myFood.getX() * myFood.getCellSize(), myFood.getY() * myFood.getCellSize(), myFood.getCellSize(), myFood.getCellSize());
                myShape.end();
            }
        }
		batch.end();
    }
	@Override
	public void dispose () {
        batch.dispose();
        myShape.dispose();
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
    void moveInGridCell()throws Exception
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

    void moveCellGrid()throws Exception
    {
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            if(yDirectionalMovement == -1)
//            {
//                xDirectionalMovement = 0;
//                yDirectionalMovement = -1;
//            }
//            else
//            {
//                xDirectionalMovement = 0;
//                yDirectionalMovement = 1;
//            }
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            if(yDirectionalMovement == 1)
//            {
//                xDirectionalMovement = 0;
//                yDirectionalMovement = 1;
//            }
//            else
//            {
//                xDirectionalMovement = 0;
//                yDirectionalMovement = -1;
//            }
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            if(xDirectionalMovement == -1)
//            {
//                xDirectionalMovement = -1;
//                yDirectionalMovement = 0;
//            }
//            else
//            {
//                xDirectionalMovement = 1;
//                yDirectionalMovement = 0;
//            }
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            if(xDirectionalMovement == 1)
//            {
//                xDirectionalMovement = 1;
//                yDirectionalMovement = 0;
//            }
//            else
//            {
//                xDirectionalMovement = -1;
//                yDirectionalMovement = 0;
//            }
//        }
        xPosition += xDirectionalMovement;
        yPosition += yDirectionalMovement;
        if(xPosition >= myCell.getX() * myCell.getCellSize() + myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPosition >= myCell.getY() * myCell.getCellSize() + myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(xPosition < myCell.getX() * myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPosition < myCell.getY() * myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
    }
    //Same as the regular move method, but for 2 snakes
    void moveCellGridTwoSnakes()throws Exception
    {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
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
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
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
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
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
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
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
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(yDirectionalMovementP2 == -1)
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = -1;
            }
            else
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(yDirectionalMovementP2 == 1)
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = 1;
            }
            else
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = -1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(xDirectionalMovementP2 == -1)
            {
                xDirectionalMovementP2 = -1;
                yDirectionalMovementP2 = 0;
            }
            else
            {
                xDirectionalMovementP2 = 1;
                yDirectionalMovementP2 = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(xDirectionalMovementP2 == 1)
            {
                xDirectionalMovementP2 = 1;
                yDirectionalMovementP2 = 0;
            }
            else
            {
                xDirectionalMovementP2 = -1;
                yDirectionalMovementP2 = 0;
            }
        }
        xPosition += xDirectionalMovement;
        yPosition += yDirectionalMovement;
        xPositionP2 += xDirectionalMovementP2;
        yPositionP2 += yDirectionalMovementP2;
        if(xPosition >= myCell.getX() * myCell.getCellSize() + myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPosition >= myCell.getY() * myCell.getCellSize() + myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(xPosition < myCell.getX() * myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPosition < myCell.getY() * myCell.getCellSize()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(myCell);
            if(myCell.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < myCell.getBreadCrumbsList().size(); index++) {
                    System.out.println("index = " + index);
                    System.out.println("x = " + myCell.getBreadCrumbsList().get(index).getX());
                    System.out.println("y = " + myCell.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(xPositionP2 >= snakeHeadP2.getX() * snakeHeadP2.getCellSize() + snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPositionP2 >= snakeHeadP2.getY() * snakeHeadP2.getCellSize() + snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(xPositionP2 < snakeHeadP2.getX() * snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPositionP2 < snakeHeadP2.getY() * snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
    }
    void moveCellGridP2SnakeOnly()throws Exception
    {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(yDirectionalMovementP2 == -1)
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = -1;
            }
            else
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(yDirectionalMovementP2 == 1)
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = 1;
            }
            else
            {
                xDirectionalMovementP2 = 0;
                yDirectionalMovementP2 = -1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(xDirectionalMovementP2 == -1)
            {
                xDirectionalMovementP2 = -1;
                yDirectionalMovementP2 = 0;
            }
            else
            {
                xDirectionalMovementP2 = 1;
                yDirectionalMovementP2 = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(xDirectionalMovementP2 == 1)
            {
                xDirectionalMovementP2 = 1;
                yDirectionalMovementP2 = 0;
            }
            else
            {
                xDirectionalMovementP2 = -1;
                yDirectionalMovementP2 = 0;
            }
        }
        xPositionP2 += xDirectionalMovementP2;
        yPositionP2 += yDirectionalMovementP2;
        if(xPositionP2 >= snakeHeadP2.getX() * snakeHeadP2.getCellSize() + snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPositionP2 >= snakeHeadP2.getY() * snakeHeadP2.getCellSize() + snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(xPositionP2 < snakeHeadP2.getX() * snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
        if(yPositionP2 < snakeHeadP2.getY() * snakeHeadP2.getCellSize()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(snakeHeadP2);
            if(snakeHeadP2.getBreadCrumbsList().size() > 0)
            {
                for (int index = 0; index < snakeHeadP2.getBreadCrumbsList().size(); index++) {
                    System.out.println("P2 index = " + index);
                    System.out.println("P2 x = " + snakeHeadP2.getBreadCrumbsList().get(index).getX());
                    System.out.println("P2 y = " + snakeHeadP2.getBreadCrumbsList().get(index).getY());
                }
            }
        }
    }

    public void exit()
    {
        Gdx.app.exit();
    }
}
