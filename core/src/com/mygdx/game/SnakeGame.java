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

import java.io.IOException;

public class SnakeGame extends Game {
	Game game;
    MyInputProcessor myProcessor;
    SpriteBatch batch;
    BitmapFont yourBitmapFontName;
    String yourScoreName;// = "Snake Game";

	Texture img;
	ScreenGrid playGrid;
    Stage gamePlayStage;
    Stage endGameStage;
    long startTime;
    long endTime;

    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;

    Stage startGameStage;
    TextButton beginSinglePlayerGame;
    TextButton beginTwoPlayerGame;
    TextButton beginTwoPlayerServer;
    TextButton beginTwoPlayerClient;
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
    boolean twoPlayerServer = false;
    boolean twoPlayerClient = false;

    //multiplayer section
    Client myClient;
    Server myServer;
    public SnakeGame()
    {
        game = this;
    }
	
	@Override
	public void create () {
        //Initialize the SpriteBatch/ShapeRenderer
        batch = new SpriteBatch();
        yourBitmapFontName = new BitmapFont();
        yourBitmapFontName.setColor(.1f, 1.0f, 1.0f, 1.0f);
        yourScoreName = "Snake Game";
        //yourBitmapFontName.

        myShape = new ShapeRenderer();
        //Initialize the game grid, cell, and movement variables
		playGrid = new ScreenGrid(this);
        playGrid.setScreenHeight(Gdx.graphics.getHeight());
        playGrid.setScreenWidth(Gdx.graphics.getWidth());
        playGrid.setCoordinateGrid();

        //initialize a body and food cell
        myFood = new Cell("food");
        //if(singlePlayerGame == true || twoPlayerGame == true || twoPlayerServer == true) {
            playGrid.addFoodCellToGrid(myFood);
        //}
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
        beginTwoPlayerServer = new TextButton("Two Player Server", textButtonStyle);
        beginTwoPlayerServer.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 80);
        beginTwoPlayerClient = new TextButton("Two Player Client", textButtonStyle);
        beginTwoPlayerClient.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 120);
        exitGame = new TextButton("Exit", textButtonStyle);
        exitGame.setPosition(Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 - 40);
        startGameStage.addActor(beginSinglePlayerGame);
        startGameStage.addActor(beginTwoPlayerGame);
        startGameStage.addActor(beginTwoPlayerServer);
        startGameStage.addActor(beginTwoPlayerClient);
        startGameStage.addActor(exitGame);

        myProcessor = new MyInputProcessor(this);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(startGameStage);
        multiplexer.addProcessor(myProcessor);
        multiplexer.addProcessor(endGameStage);
        Gdx.input.setInputProcessor(multiplexer);

        //Create a listener for the play again button
        playAgainButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Play again button pressed");
                gameOver = false;
                resetGame();
//                if(twoPlayerClient == true) {
//                    myClient.closeConnection();
//                }
                //create();
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
                if(twoPlayerClient == true) {
                    myClient.closeConnection();
                    try {
                        myClient.myServer.myHandler.connection.close();
                        myClient.myServer.serverListener.close();
                    }
                    catch(IOException ex)
                    {
                        System.out.println("there was an IO issue closing the connection handler socket for the client's server.");
                        ex.printStackTrace();
                    }
                }
                if(twoPlayerServer == true) {
                    myServer.myHandler.myClient.closeConnection();
                    try {
                        myServer.myHandler.connection.close();
                        myServer.serverListener.close();
                    }
                    catch(IOException ex)
                    {
                        System.out.println("there was an IO issue closing the connection handler socket for the server.");
                        ex.printStackTrace();
                    }
                }
                twoPlayerServer = false;
                twoPlayerClient = false;
//                playGrid.setCoordinateGrid();
//                myCell.setX(0);
//                myCell.setY(0);
//                xPosition = 0;
//                yPosition = 0;
//                xDirectionalMovement = 0;
//                yDirectionalMovement = 0;
//
//                playGrid.addCellToGrid(myCell);
                //myServer.
                //create();
            }
        });
        beginSinglePlayerGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Begin single player game button pressed");
                gameStarting = false;
                singlePlayerGame = true;
                twoPlayerGame = false;
                twoPlayerServer = false;
                twoPlayerClient = false;
                resetGame();
                //create();
            }
        });
        beginTwoPlayerGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Begin two player game button pressed");
                gameStarting = false;
                singlePlayerGame = false;
                twoPlayerGame = true;
                twoPlayerServer = false;
                twoPlayerClient = false;
                resetGame();
                //create();
            }
        });
        beginTwoPlayerServer.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Begin two player server button pressed");
                gameStarting = false;
                singlePlayerGame = false;
                twoPlayerServer = true;
                twoPlayerClient = false;
                createServer();
                resetGame();
                //create();
            }
        });
        beginTwoPlayerClient.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Begin two player client button pressed");
                gameStarting = false;
                singlePlayerGame = false;
                twoPlayerClient = true;
                twoPlayerServer = false;
                createClient();
                resetGame();
                //create();
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
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
        //get the current time from java
        //long startTime = System.currentTimeMillis();
        //System.out.println("Time elapsed in seconds = " + ((System.currentTimeMillis() - startTime) / 1000));
        //callRenderMethodPerTime();
	}

	@Override
	public void render () {

        Gdx.gl.glClearColor(.7f, .5f, .6f, .8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        if(gameStarting == true)
        {
            startGameStage.draw();
            batch.begin();
            yourBitmapFontName.draw(batch, yourScoreName, Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 + 90);
            batch.end();
        }
        else {

            if (gameOver == true) {
                endGameStage.draw();
                batch.begin();
                yourBitmapFontName.draw(batch, yourScoreName, Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()/2 + 90);
                batch.end();
            } else {
                try {
                    if(singlePlayerGame == true)
                    {
                        moveCellGrid();
                    }
                    else if(twoPlayerGame == true || twoPlayerServer == true || twoPlayerClient == true)
                    {
                        moveCellGridTwoSnakes();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                    System.out.println("game over! Hit a wall!");
                    if(myCell.isCrashed == true) {
                        System.out.println("Player 1 Lost!");
                        yourScoreName = "Player 1 Lost!";
                    }
                    else if (snakeHeadP2.isCrashed == true){
                        System.out.println("Player 2 Lost!");
                        yourScoreName = "Player 2 Lost!";
                    }
                    gameOver = true;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    System.out.println("game over! Hit a snake head or body!");
                    if(myCell.isCrashed == true) {
                        System.out.println("Player 1 Lost!");
                        yourScoreName = "Player 1 Lost!";
                    }
                    else if(snakeHeadP2.isCrashed == true){
                        System.out.println("Player 2 Lost!");
                        yourScoreName = "Player 2 Lost!";
                    }
                    gameOver = true;
                }
                ShapeRenderer myShape = new ShapeRenderer();
                myShape.begin(ShapeRenderer.ShapeType.Filled);
                myShape.setColor(0, .9f, .1f, 1);
                myShape.rect(myCell.getX() * myCell.getCellSize(), myCell.getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
                //this is causing a tail to appear, but it is not following the head, and it is continuously growing along the x/y axis' at the same rate when the user moves along the x axis
                if(!myCell.getBody().isEmpty())
                {
                    for (int index = myCell.getBreadCrumbsList().size() - 1; index >= 0; index--)
                    {
                        myShape.rect(myCell.getBreadCrumbsList().get(index).getX() * myCell.getCellSize(), myCell.getBreadCrumbsList().get(index).getY() * myCell.getCellSize(), myCell.getCellSize(), myCell.getCellSize());
                    }
                }
                if(twoPlayerGame == true || twoPlayerServer == true|| twoPlayerClient == true)
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
                myShape.setColor(1, 1, 0, 1);
                myShape.rect(myFood.getX() * myFood.getCellSize(), myFood.getY() * myFood.getCellSize(), myFood.getCellSize(), myFood.getCellSize());
                myShape.end();
            }
        }
		//batch.end();
        startTime = System.currentTimeMillis();
        callRenderMethodBasedOnTime();
    }
	@Override
	public void dispose () {
        batch.dispose();
        myShape.dispose();
	}

    void moveCellGrid()throws Exception
    {
        xPosition += xDirectionalMovement;
        yPosition += yDirectionalMovement;

        if(xPosition > myCell.getX()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(myCell);

            myProcessor.moving = false;
        }
        else if(yPosition > myCell.getY()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(myCell);

            myProcessor.moving = false;
        }
        else if(xPosition < myCell.getX()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(myCell);

            myProcessor.moving = false;
        }
        else if(yPosition < myCell.getY()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(myCell);

            myProcessor.moving = false;
        }
    }
    //Same as the regular move method, but for 2 snakes
    void moveCellGridTwoSnakes()throws Exception
    {
        xPosition += xDirectionalMovement;
        yPosition += yDirectionalMovement;
        xPositionP2 += xDirectionalMovementP2;
        yPositionP2 += yDirectionalMovementP2;

        if(xPosition > myCell.getX()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(myCell);

            myProcessor.moving = false;
        }
        if(yPosition > myCell.getY()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(myCell);

            myProcessor.moving = false;
        }
        if(xPosition < myCell.getX()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(myCell);

            myProcessor.moving = false;
        }
        if(yPosition < myCell.getY()) {
            currentCoordinate = new Coordinates(myCell.getX(), myCell.getY());
            myCell.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(myCell);

            myProcessor.moving = false;
        }
        if(xPositionP2 > snakeHeadP2.getX()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellRight(snakeHeadP2);

            myProcessor.movingP2 = false;
        }
        if(yPositionP2 > snakeHeadP2.getY()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellUp(snakeHeadP2);

            myProcessor.movingP2 = false;
        }
        if(xPositionP2 < snakeHeadP2.getX()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellLeft(snakeHeadP2);

            myProcessor.movingP2 = false;
        }
        if(yPositionP2 < snakeHeadP2.getY()) {
            currentCoordinate = new Coordinates(snakeHeadP2.getX(), snakeHeadP2.getY());
            snakeHeadP2.addCoordinateToList(currentCoordinate);
            playGrid.moveGridCellDown(snakeHeadP2);

            myProcessor.movingP2 = false;
        }
    }

    public void callRenderMethodBasedOnTime()
    {
        while (gameOver == false)
        {
            endTime = System.currentTimeMillis();
            if (endTime >= startTime + 300)
            {
                Gdx.graphics.requestRendering();
                break;
            }
        }
    }

    public void exit()
    {
        Gdx.app.exit();
    }
    public void createServer()
    {
//        if(myServer == null) {
            myServer = new Server(this);
            myServer.setConnection();
//        }
    }
    public void createClient()
    {
//        if(myClient == null) {
            myClient = new Client(this);
            myClient.runClient();
//        }
    }
    public void resetGame()
    {
        if(singlePlayerGame == true || twoPlayerGame == true || twoPlayerServer == true)
        {
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
        }
        if(twoPlayerGame == true)
        {
            //Initialize second snake
            snakeHeadP2 = new Cell("head");
            snakeHeadP2.setX(playGrid.coordinateGrid.length - 1);
            snakeHeadP2.setY(playGrid.coordinateGrid[0].length - 1);
            xPositionP2 = snakeHeadP2.getX();
            yPositionP2 = snakeHeadP2.getY();
            xDirectionalMovementP2 = 0;
            yDirectionalMovementP2 = 0;
            playGrid.addCellToGrid(snakeHeadP2);
        }
        if(twoPlayerServer == true)
        {
            //Initialize second snake
            snakeHeadP2 = new Cell("head");
            snakeHeadP2.setX(playGrid.coordinateGrid.length - 1);
            snakeHeadP2.setY(playGrid.coordinateGrid[0].length - 1);
            xPositionP2 = snakeHeadP2.getX();
            yPositionP2 = snakeHeadP2.getY();
            xDirectionalMovementP2 = 0;
            yDirectionalMovementP2 = 0;
            playGrid.addCellToGrid(snakeHeadP2);
//            if(myServer == null) {
//                myServer = new Server(this);
//                myServer.setConnection();
//            }

        }
        if(twoPlayerClient == true)
        {
            playGrid.setCoordinateGrid();
            myCell = new Cell("head");
            myCell.setX(playGrid.coordinateGrid.length - 1);
            myCell.setY(playGrid.coordinateGrid[0].length - 1);
            xPosition = myCell.getX();
            yPosition = myCell.getY();
            xDirectionalMovement = 0;
            yDirectionalMovement = 0;

            playGrid.addCellToGrid(myCell);
            //Initialize second snake
            snakeHeadP2 = new Cell("head");
            snakeHeadP2.setX(0);
            snakeHeadP2.setY(0);
            xPositionP2 = 0;
            yPositionP2 = 0;
            xDirectionalMovementP2 = 0;
            yDirectionalMovementP2 = 0;

            playGrid.addCellToGrid(snakeHeadP2);

//            if(myClient == null) {
//                myClient = new Client(this);
//                myClient.runClient();
//            }
        }
        myProcessor.moving = false;
        myProcessor.movingP2 = false;
        //myFood = new Cell("food");
        playGrid.addFoodCellToGrid(myFood);
//        while(myServer.myHandler.myClient == null)
//        {
//
//        }
//        if (twoPlayerServer == true) {//&& myGame.myServer.myHandler.myClient != null
//
//            myServer.myHandler.myClient.dialogWithServer("x" + myFood.getX());
//            myServer.myHandler.myClient.dialogWithServer("y" + myFood.getY());
//        }
    }
}
