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
 * Programmiern eines "Vier Gewinnt-Puissance quatre" mit JavaFX auf Eclipse. 
 * Zudem wird das Projekt mittels SCUM durchgeführt und in 3 Sprints aufgeteilt.
 * 
 * Fertigstellungsdatum: 22.12.2018
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	BorderPane root = new BorderPane();							//Layout
	Circle circle[][] = new Circle [7][6];
	Button button[] = new Button[7];
	
	MainApp game = new MainApp();

	public void start(Stage primaryStage) {
		game.initPlayer();
		
		root.setTop(createTopPane());
		root.setCenter(createCenterPane());
		root.setLeft(getLeftHBox());
		root.setBottom(createBottomPane());						
		root.setStyle("-fx-background-color: #ccebff;");


		Scene scene = new Scene(root, 800, 700);

		primaryStage.setTitle("Vier Gewinnt - Puissance Quatre");
		primaryStage.setScene(scene);
		primaryStage.show();
		popupSetNames();
	}

	public static void main(String[] args) {
		launch(args);
	}

	Pane createTopPane() {
		
		Label l1 = new Label("VIER GEWINNT - PUISSANCE QUATRE");
		l1.setPadding(new Insets(10, 10, 10, 10));
		l1.setFont(new Font("ALGERIAN", 35));
		l1.setTextFill(Color.web("BLUE"));
		l1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				l1.setScaleX(1.1);
				l1.setScaleY(1.1);
			}
		});
		l1.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				l1.setScaleX(1);
				l1.setScaleY(1);
			}
		});

		HBox hbox = new HBox();
		hbox.getChildren().add(l1);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}

	Pane createCenterPane() {

		//Playground
		GridPane gpane = new GridPane();
		for (int column = 0; column < 6; column++)
		{
			for (int row = 0; row < 7; row++)
			{
				//Grid
				Rectangle rect = new Rectangle(70, 70);
				rect.setStroke(Color.BLACK);
				rect.setFill(null);
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
				
		//Button
				HBox hbox;

				for(int i=0; i<7; i++) {                                                             
					button[i] = new Button("#" +(i+1));
					button[i].setFont(Font.font("Cambria", 10));
					button[i].setStyle("-fx-background-color: #3232ff"); //background color of button
					button[i].setMinSize(50, 25);
				}

				button[0].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(0, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[0][game.getCoordinateY()].setVisible(true);	
													circle[0][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[0][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[0].setDisable(true);
													}
				});


				button[1].setOnAction(event -> {	game.nextPlayer();											
													game.refreshPitch(1, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[1][game.getCoordinateY()].setVisible(true);	
													circle[1][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[1][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[1].setDisable(true);
													}
				});

				button[2].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(2, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[2][game.getCoordinateY()].setVisible(true);	
													circle[2][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[2][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[2].setDisable(true);
													}
				});
				
				button[3].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(3, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[3][game.getCoordinateY()].setVisible(true);	
													circle[3][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[3][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[3].setDisable(true);
													}
				});

				button[4].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(4, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[4][game.getCoordinateY()].setVisible(true);	
													circle[4][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[4][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[4].setDisable(true);
													}
				});

				button[5].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(5, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[5][game.getCoordinateY()].setVisible(true);	
													circle[5][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[5][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[5].setDisable(true);
													}
				});

				button[6].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(6, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													if (game.lookingForDraw()==true) {
														popupDraw();
													}
													circle[6][game.getCoordinateY()].setVisible(true);	
													circle[6][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[6][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
													if (game.getCoordinateY()==0) {
														button[6].setDisable(true);
													}
				});
		    
				hbox = new HBox(22, button[0], button[1], button[2], button[3], button[4], button[5], button[6]);
				hbox.setAlignment(Pos.CENTER);
				hbox.setPadding(new Insets(20, 10, 10, 10));
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(hbox, gpane);

		return vbox;
	}

	GridPane getRightHBox()	{
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
			text[i].setFont(new Font("ARIAL", 20));
			text[i].setTextFill(Color.web("#000000"));
			GridPane.setHalignment(text[i], HPos.CENTER);
		}
		
		

		
		gpane.setAlignment(Pos.CENTER);
		return gpane;
	}

	VBox getLeftHBox()	{
		Label text [] = new Label [4];
		text[0] = new Label("Spieler 1:");
		text[1] = new Label(game.getFirstPlayer());
		text[2] = new Label("Spieler 2:");
		text[3] = new Label(game.getSecondPlayer());
		
		for (int i=0; i<4; i++) {
			text[i].setPadding(new Insets(10, 10, 10, 10));
			text[i].setFont(new Font("ARIAL", 20));
			text[i].setTextFill(Color.web("#000000"));
		}
		/*
		//Scene 2
		Label text2 [] = new Label [4];
		text2[0] = new Label("Spieler 1:");
		text2[1] = new Label(game.getFirstPlayer());
		text2[2] = new Label("Spieler 2:");
		text2[3] = new Label(game.getSecondPlayer());
		
		for (int i=0; i<4; i++) {
			text2[i].setPadding(new Insets(10, 10, 10, 10));
			text2[i].setFont(new Font("ARIAL", 20));
			text2[i].setTextFill(Color.web("#000000"));
		}		
		text2[3].setStyle("-fx-font-weight: bold");
		
				
		*/
		VBox vbox = new VBox(text[0], text[1] , text[2], text[3]);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}

	HBox createBottomPane() {

		Button newGame = new Button("new Game");
		newGame.setOnAction(event -> {	game.resetGame();
										resetPitch();
										for (int i=0; i<7; i++) {
											button[i].setDisable(false);
										}
										root.setRight(getRightHBox());
		});
		Button exit = new Button("Exit");
		exit.setOnAction(event -> {	game.exitGame();
		});
		
		
		HBox hbox = new HBox(20, newGame, exit);
		hbox.setPadding(new Insets(20, 20, 20, 20));
		hbox.setAlignment(Pos.CENTER_RIGHT);
		return hbox;
	}

	void popupWinner() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Vier Gewinnt Gewinner - Puissance Quatre gagneur");
		Label label1 = new Label("Herzlichen Glückwunsch!");
		Label label2 = new Label();
		if (game.getPlayer()==1) {
			label2.setText(game.getFirstPlayer() + " hat gewonnen");
			game.setWinsFirstPlayer(game.getWinsFirstPlayer()+1);
		}
		if (game.getPlayer()==2) {
			label2.setText(game.getSecondPlayer() + " hat gewonnen");
			game.setWinsSecondPlayer(game.getWinsSecondPlayer()+1);
		}
		VBox vbox = new VBox(20, label1, label2);
		vbox.setAlignment(Pos.CENTER);
		Scene windowScene = new Scene(vbox, 300, 200);
		window.setScene(windowScene);
		window.show();
	}
	
	void popupDraw() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Vier Gewinnt Gewinner - Puissance Quatre gagneur");
		Label label1 = new Label("Sie haben das Spiel beendet.");
		Label label2 = new Label("Es steht unentschieden");
		VBox vbox = new VBox(20, label1, label2);
		vbox.setAlignment(Pos.CENTER);
		Scene windowScene = new Scene(vbox, 300, 200);
		window.setScene(windowScene);
		window.show();
	}
	
	void popupSetNames() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Spieler eingabe");
		Label label1 = new Label("Geben Sie die Namen der Spieler ein.");
		label1.setAlignment(Pos.CENTER);
		
		Label label2 = new Label("Spieler 1: ");
		Label label3 = new Label("Spieler 2: ");
		TextField textField1 = new TextField();
		textField1.setPromptText("Name player 1");
		TextField textField2 = new TextField();
		textField2.setPromptText("Name player 2");
		Button button1 = new Button("Save");
		button1.disableProperty().bind(Bindings.isEmpty(textField1.textProperty()).or(Bindings.isEmpty(textField2.textProperty())));
		button1.setOnKeyPressed(event -> {	switch(event.getCode()) {
											case ENTER:
												game.setFirstPlayer(textField1.getText().toString());
												game.setSecondPlayer(textField2.getText().toString());
												window.close();
												root.setLeft(getLeftHBox());
		};									
		});	
		button1.setOnAction(event -> {
			game.setFirstPlayer(textField1.getText().toString());
			game.setSecondPlayer(textField2.getText().toString());
			window.close();
			root.setLeft(getLeftHBox());
			root.setRight(getRightHBox());
		});
		Button button2 = new Button("Exit");
		button2.setOnKeyPressed(event -> {	switch(event.getCode()) {
											case ENTER:
												game.exitGame();
		};									
		});
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
		
		
		Scene windowScene = new Scene(vbox, 300, 140);
		window.setScene(windowScene);
		window.show();
	}
	
	public void resetPitch() {
		for (int i=0; i<7; i++) {
			for (int j=0; j<6; j++) {
				circle[i][j].setVisible(false);
			}
		}
	}
}