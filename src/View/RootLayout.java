/*
 * Projektarbeit Programmieren II
 * 
 * Betreuer:
 * Matthias Bachmann
 * 
 * Team:
 * Carl Podevijn
 * Yannis Schmidt
 * Tamara Lodico
 * Kilian Vallotton
 * 
 * Projekt:
 * Anwenden von JavaFX Programmierung
 * Anwenden von SCRUM Projektführung
 * Anwenden von GitHub Projektführung
 * Anwenden von Maven
 * 
 * Fertigstellungsdatum: 22.12.2018, 23.55
 */

package View;


import java.util.ArrayList;

import VierGewinnt.MainApp;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class RootLayout extends Application { 

	ArrayList<Line> values = new ArrayList<Line>();				//Arraylist for grid
	BorderPane root = new BorderPane();							//Layout with Borderpane
	Circle circle[][] = new Circle [7][6];						//Points for gaming piece
	Button button[] = new Button[7];							//setOnAction the gaming pieces
	Label text [] = new Label [4];                //Label for showing whose turn it is

	MainApp game = new MainApp();

	public void start(Stage primaryStage) {

		root.setTop(createTopPane());
		root.setCenter(createCenterPane());
		root.setLeft(getLeftHBox());
		root.setRight(getRightHBox());
		root.setBottom(createBottomPane());						
		root.setStyle("-fx-background-color: #e5e5ff;");
		//EFEFFF

		Scene scene = new Scene(root, 1000, 700);

		primaryStage.setTitle("Connect Four");
		primaryStage.setScene(scene);

		//Set the application icon
		primaryStage.getIcons().add(new Image("file:///C:/Users/kiki1/git/VierGewinnt/resources/images/iconfinder_Games_BoardGames_Artboard_28_3828857.png"));

		primaryStage.show();
		popupSetNames();
	}

	public static void main(String[] args) {
		launch(args);
	}

	Pane createTopPane() {

		Label l1 = new Label("CONNECT FOUR");
		l1.setPadding(new Insets(10, 10, 10, 10));
		l1.setStyle("-fx-font-weight: bold");
		l1.setFont(new Font("Bernard MT Condensed", 40));
		l1.setTextFill(Color.web("#0000FF"));
		l1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				l1.setScaleX(2.5);
				l1.setScaleY(1.3);
			}
		});
		l1.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				l1.setScaleX(2);
				l1.setScaleY(1.1);
			}
		});

		HBox hbox = new HBox();
		hbox.getChildren().add(l1);
		hbox.setAlignment(Pos.CENTER);
		return hbox;

	}

	Pane createCenterPane() {

		//Playground Grid & Points
		GridPane gpane = new GridPane();
		for (int column = 0; column < 6; column++)
		{
			for (int row = 0; row < 7; row++)
			{
				//Grid
				Rectangle rect = new Rectangle(70, 70);
				rect.setStroke(Color.BLUE);
				rect.setFill(null);
				rect.setStrokeWidth(3);
				gpane.add(rect, row, column);

				//Points
				circle[row][column] = new Circle(0, 0, 25);
				circle[row][column].setStroke(Color.TRANSPARENT);
				circle[row][column].setStrokeWidth(5);
				gpane.add(circle[row][column], row, column);
				circle[row][column].setVisible(false);

				GridPane.setHalignment(circle[row][column], HPos.CENTER);
				gpane.setAlignment(Pos.TOP_CENTER );
			}  
		}
		
		//Button to place the Points
		for(int i=0; i<7; i++) {                                                             
			button[i] = new Button("#" +(i+1));
			button[i].setFont(Font.font("Bernard MT Condensed", 15));
			button[i].setTextFill(Color.web("#5D4E84"));
			//background color of button
			button[i].setStyle("-fx-background-color: #b2b2ff"); 	
			button[i].setMinSize(50, 25);
		}

		button[0].setOnAction(event -> {	setOnAction(0);
		});


		button[1].setOnAction(event -> {	setOnAction(1);						
		});

		button[2].setOnAction(event -> {	setOnAction(2);
		});

		button[3].setOnAction(event -> {	setOnAction(3);
		});

		button[4].setOnAction(event -> {	setOnAction(4);
		});

		button[5].setOnAction(event -> {	setOnAction(5);
		});

		button[6].setOnAction(event -> {	setOnAction(6);
		});
		
		HBox hbox = new HBox(22, button[0], button[1], button[2], button[3], button[4], button[5], button[6]);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20, 10, 10, 10));

		VBox vbox = new VBox();
		vbox.getChildren().addAll(hbox, gpane);

		return vbox;
	}
	
	//Mehtod of setOnAction of the Buttons
	void setOnAction(int buttonNumber) {
		game.nextPlayer();
		game.refreshPitch(buttonNumber, game.getPlayer());
		if (game.searchingWinner()==true) {
			popupWinner();
		}
		if (game.lookingForDraw()==true) {
			popupDraw();
		}
		circle[buttonNumber][game.getCoordinateY()].setVisible(true);	
		circle[buttonNumber][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
		circle[buttonNumber][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
		if (game.getCoordinateY()==0) {
			button[buttonNumber].setDisable(true);
		}
		markPlayer();
	}

	GridPane getRightHBox()	{
		
		//Result of games
		Label text[] = new Label[6];
		text[0] = new Label(game.getFirstPlayer());
		text[1] = new Label(":");
		text[2] = new Label(game.getSecondPlayer());
		text[3] = new Label(Integer.toString(game.getWinsFirstPlayer()));
		text[4] = new Label(":");
		text[5] = new Label(Integer.toString(game.getWinsSecondPlayer()));

		GridPane gpane = new GridPane();
		gpane.add(text[0], 0, 0);
		gpane.add(text[1], 1, 0);
		gpane.add(text[2], 2, 0);
		gpane.add(text[3], 0, 1);
		gpane.add(text[4], 1, 1);
		gpane.add(text[5], 2, 1);

		for(int i=0; i<text.length; i++) {
			text[i].setPadding(new Insets(10, 10, 10, 10));
			text[i].setFont(new Font("Bernard MT Condensed", 20));
			text[i].setTextFill(Color.web("#5D4E84"));
			GridPane.setHalignment(text[i], HPos.CENTER);
		}

		gpane.setAlignment(Pos.CENTER);
		gpane.setMinSize(200, 500);
		return gpane;
	}

	VBox getLeftHBox()	{
		
		//set Name of Player to number of Player
		text[0] = new Label("Player 1:");
		text[1] = new Label(game.getFirstPlayer());
		text[2] = new Label("Player 2:");
		text[3] = new Label(game.getSecondPlayer());

		for (int i=0; i<4; i++) {
			text[i].setPadding(new Insets(10, 10, 10, 10));
			text[i].setFont(new Font("Bernard MT Condensed", 20));
			text[i].setTextFill(Color.web("#5D4E84"));
		}

		text[1].setFont(Font.font("Bernard MT Condensed",30));
		text[1].setTextFill(Color.RED);

		VBox vbox = new VBox(text[0], text[1] , text[2], text[3]);
		vbox.setAlignment(Pos.CENTER);
		vbox.setMinWidth(200);
		return vbox;
	}

	//define which Player have to play
	void markPlayer() {
		if (game.getPlayer()==2) {
			text[1].setFont(Font.font("Bernard MT Condensed", 30));
			text[1].setTextFill(Color.RED);
			text[3].setFont(Font.font("Bernard MT Condensed", 20));
		}
		if (game.getPlayer()==1) {
			text[1].setFont(Font.font("Bernard MT Condensed", 20));
			text[3].setFont(Font.font("Bernard MT Condensed", 30));
			text[3].setTextFill(Color.GREEN);
		}
	}

	HBox createBottomPane() {

		//Button for new Game
		Button newGame = new Button("new Game");
		newGame.setFont(Font.font("Bernard MT Condensed", 20));
		newGame.setTextFill(Color.web("#5D4E84"));
		newGame.setStyle("-fx-background-color: #b2b2ff"); 	
		newGame.setOnAction(event -> {	game.resetGame();

		resetPitch();
		for (int i=0; i<7; i++) {
			button[i].setDisable(false);
		}
		root.setRight(getRightHBox());
		markPlayer();

		});
		
		//Button for Exit Game
		Button exit = new Button("Exit");
		exit.setFont(Font.font("Bernard MT Condensed", 20));
		exit.setTextFill(Color.web("#5D4E84"));
		exit.setStyle("-fx-background-color: #b2b2ff"); 	
		exit.setOnAction(event -> {	game.exitGame();
		});
		
		//Initials of the Crew
		Label initial = new Label("TL-KV-YS-CP");
		initial.setPadding(new Insets(0, 680, -15, 0));
		initial.setAlignment(Pos.BASELINE_LEFT);
		initial.setFont(Font.font("Bernard MT Condensed", 15));
		initial.setTextFill(Color.web("#5D4E84"));
		
		HBox hbox = new HBox(20, initial, newGame, exit);
		hbox.setPadding(new Insets(20, 20, 20, 20));
		hbox.setAlignment(Pos.CENTER_RIGHT);
		return hbox;
	}

	//Window to enter the name of players
	void popupSetNames() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Player input");
		
		//Set the application icon
		window.getIcons().add(new Image("file:///C:/Users/kiki1/git/VierGewinnt/resources/images/iconfinder_Games_BoardGames_Artboard_28_3828857.png"));
		
		Label label1 = new Label("Enter name of player");
		label1.setFont(new Font("Bernard MT Condensed", 20));
		label1.setTextFill(Color.web("#5D4E84"));
		label1.setAlignment(Pos.CENTER);
	
		Label label2 = new Label("Player 1: ");
		label2.setFont(new Font("Bernard MT Condensed", 20));
		label2.setTextFill(Color.web("#5D4E84"));
		
		Label label3 = new Label("Player 2: ");
		label3.setFont(new Font("Bernard MT Condensed", 20));
		label3.setTextFill(Color.web("#5D4E84"));
		
		TextField textField1 = new TextField();
		textField1.setPromptText("name player 1");
		textField1.setFont(Font.font("Bernard MT Condensed", 20));
		TextField textField2 = new TextField();
		textField2.setFont(Font.font("Bernard MT Condensed", 20));
		textField2.setPromptText("name player 2");
		
		//Button save
		Button button1 = new Button("Save");
		button1.setFont(Font.font("Bernard MT Condensed", 20));
		button1.setTextFill(Color.web("#5D4E84"));
		button1.setStyle("-fx-background-color: #b2b2ff"); 	
		button1.disableProperty().bind(Bindings.isEmpty(textField1.textProperty()).or(Bindings.isEmpty(textField2.textProperty())));
		//setOnKeyPressed with Enter for button save
		button1.setOnKeyPressed(event -> {	switch(event.getCode()) {
		case ENTER:
			game.setFirstPlayer(textField1.getText().toString());
			game.setSecondPlayer(textField2.getText().toString());
			window.close();
			root.setLeft(getLeftHBox());
			root.setRight(getRightHBox());
		};									
		});	
		//setOnAction with Mouse for button save
		button1.setOnAction(event -> {
			game.setFirstPlayer(textField1.getText().toString());
			game.setSecondPlayer(textField2.getText().toString());
			window.close();
			root.setLeft(getLeftHBox());
			root.setRight(getRightHBox());
		});
		
		//Button Exit
		Button button2 = new Button("Exit");
		button2.setFont(Font.font("Bernard MT Condensed", 20));
		button2.setTextFill(Color.web("#5D4E84"));
		button2.setStyle("-fx-background-color: #b2b2ff"); 	
		//setOnKeyPressed with Enter for button exit
		button2.setOnKeyPressed(event -> {	switch(event.getCode()) {
		case ENTER:
			game.exitGame();
		};									
		});
		//setOnAction with Mouse for button exit
		button2.setOnAction(event -> {	game.exitGame();
		});
	
		GridPane grid = new GridPane();
		grid.add(label2, 0, 0);
		grid.add(label3, 0, 1);
		grid.add(textField1, 1, 0);
		grid.add(textField2, 1, 1);
		grid.setHgap(10);
		grid.setVgap(5);
	
		HBox hbox = new HBox(10, button1, button2);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
	
		VBox vbox = new VBox(10, label1, grid, hbox);
		vbox.setPadding(new Insets(10,10,10,10));
		
		//set Background color
		Pane pane = new Pane(vbox);
		pane.setStyle("-fx-background-color: #e5e5ff;");
	
		Scene windowScene = new Scene(pane, 330, 200);
		window.setScene(windowScene);
		window.show();
	}

	//Window which shows winner
	void popupWinner() {
		
		GridPane gpane = new GridPane();
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Connect Four");
		
		//Set the application icon
		window.getIcons().add(new Image("file:///C:/Users/kiki1/git/VierGewinnt/resources/images/iconfinder_Games_BoardGames_Artboard_28_3828857.png"));
		
		Label label1 = new Label("Congratulations");
		label1.setFont(new Font("Bernard MT Condensed", 50));
		label1.setTextFill(Color.web("#5D4E84"));
		label1.setScaleX(1.3);
		label1.setScaleY(1);
		gpane.add(label1, 0, 0);
		
		Label label2 = new Label();
		if (game.getPlayer()==1) {
			label2.setText(game.getFirstPlayer() + " wins the game");
			game.setWinsFirstPlayer(game.getWinsFirstPlayer()+1);
		}
		if (game.getPlayer()==2) {
			label2.setText(game.getSecondPlayer() + " wins the game");
			game.setWinsSecondPlayer(game.getWinsSecondPlayer()+1);
		}
		label2.setFont(new Font("Bernard MT Condensed", 20));
		label2.setTextFill(Color.web("#5D4E84"));
		gpane.add(label2, 0, 1);
		
		//set Background color
		gpane.setStyle("-fx-background-color: #e5e5ff;");
		gpane.setAlignment(Pos.CENTER);
		GridPane.setHalignment(label1, HPos.CENTER);
		GridPane.setHalignment(label2, HPos.CENTER);
		
		Scene windowScene = new Scene(gpane , 450, 300);
		window.setScene(windowScene);
		window.show();
	}

	//Window when drawn game
	void popupDraw() {
		GridPane gpane = new GridPane();
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Connect Four");
		
		//Set the application icon
		window.getIcons().add(new Image("file:///C:/Users/kiki1/git/VierGewinnt/resources/images/iconfinder_Games_BoardGames_Artboard_28_3828857.png"));
		
		Label label1 = new Label("Game Over");
		label1.setFont(new Font("Bernard MT Condensed", 45));
		label1.setTextFill(Color.web("#5D4E84"));
		gpane.add(label1, 0, 0);
		
		Label label2 = new Label("It's a draw game");
		label2.setFont(new Font("Bernard MT Condensed", 20));
		label2.setTextFill(Color.web("#5D4E84"));
		gpane.add(label2, 0, 1);
		
		
		//set Background color
		gpane.setStyle("-fx-background-color: #e5e5ff;");
		gpane.setAlignment(Pos.CENTER);
		GridPane.setHalignment(label1, HPos.CENTER);
		GridPane.setHalignment(label2, HPos.CENTER);
		
		Scene windowScene = new Scene(gpane, 450, 300);
		window.setScene(windowScene);
		window.show();
	}

	//Disable all points
	public void resetPitch() {
		for (int i=0; i<7; i++) {
			for (int j=0; j<6; j++) {
				circle[i][j].setVisible(false);
			}
		}
	}
}