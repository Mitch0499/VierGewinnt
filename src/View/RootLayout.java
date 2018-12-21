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
	
	MainApp game = new MainApp();

	public void start(Stage primaryStage) {
		game.initPlayer();
		
		root.setTop(createTopPane());
		root.setCenter(createCenterPane());
		root.setRight(getRightHBox());
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
				Button button[] = new Button[7];
				HBox hbox;

				for(int i=0; i<7; i++) {                                                             
					button[i] = new Button("#" +(i+1));
					button[i].setFont(Font.font("Cambria", 10));
					button[i].setStyle("-fx-background-color: #3232ff"); //background color of button
				}

				button[0].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(0, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[0][game.getCoordinateY()].setVisible(true);	
													circle[0][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[0][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});


				button[1].setOnAction(event -> {	game.nextPlayer();											
													game.refreshPitch(1, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[1][game.getCoordinateY()].setVisible(true);	
													circle[1][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[1][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});

				button[2].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(2, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[2][game.getCoordinateY()].setVisible(true);	
													circle[2][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[2][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});
				
				button[3].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(3, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[3][game.getCoordinateY()].setVisible(true);	
													circle[3][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[3][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});

				button[4].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(4, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[4][game.getCoordinateY()].setVisible(true);	
													circle[4][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[4][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});

				button[5].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(5, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[5][game.getCoordinateY()].setVisible(true);	
													circle[5][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[5][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});

				button[6].setOnAction(event -> {	game.nextPlayer();
													game.refreshPitch(6, game.getPlayer());
													if (game.searchingWinner()==true) {
														popupWinner();
													}
													circle[6][game.getCoordinateY()].setVisible(true);	
													circle[6][game.getCoordinateY()].setStroke(game.pointColor(game.getPlayer()));
													circle[6][game.getCoordinateY()].setFill(game.pointColor(game.getPlayer()));
				});
				
				//disable buttons if column is full

				//button[0].disableProperty().(game.checkFullColumnTwo());
				//button.disableProperty().bind(buttonActionProperty.not());
				//button[0].disableProperty().bind(Bindings.size(game.columnPoints(0)).greaterThan(4));
				//button[0].setDisable(game.setCoordinateX() == 5);
				//button[1].disableProperty().bind(game.points[1] == 6);

				//button[0].disableProperty().bind(Bindings.equal(5, (ObservableNumberValue) game.observalbe(0)));
				//button[0].disableProperty().bind(game.fullColumnOne());
				
				/*button[1].disableProperty().bind(game.points[1] == 6);

				button[2].disableProperty().bind(game.points[2] == 6);
				button[3].disableProperty().bind(game.points[3] == 6);
				button[4].disableProperty().bind(game.points[4] == 6);
				button[5].disableProperty().bind(game.points[5] == 6);

				button[6].disableProperty().bind(game.points[6] == 6);*/
		    
				hbox = new HBox(45, button[0], button[1], button[2], button[3], button[4], button[5], button[6]);
				hbox.setAlignment(Pos.CENTER);
				hbox.setPadding(new Insets(20, 10, 10, 10));
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(hbox, gpane);

		return vbox;
	}

	HBox getRightHBox()	{

		Label resultat = new Label("Resultat");
		resultat.setPadding(new Insets(40, 10, 10, 10));
		resultat.setFont(new Font("ARIAL", 20));
		resultat.setTextFill(Color.web("#000000"));

		HBox hbox = new HBox(resultat);
		return hbox;
	}

	VBox getLeftHBox()	{
		Label s1a = new Label("Spieler 1:");
		s1a.setPadding(new Insets(10, 10, 10, 10));
		s1a.setFont(new Font("ARIAL", 20));
		s1a.setTextFill(Color.web("#000000"));
		Label s1 = new Label(game.getFirstPlayer());
		s1.setPadding(new Insets(10, 10, 10, 10));
		s1.setFont(new Font("ARIAL", 20));
		s1.setTextFill(Color.web("#000000"));

		Label s2a = new Label("Spieler 2:");
		s2a.setPadding(new Insets(10, 10, 10, 10));
		s2a.setFont(new Font("ARIAL", 20));
		s2a.setTextFill(Color.web("#000000"));
		Label s2 = new Label(game.getSecondPlayer());
		s2.setPadding(new Insets(10, 10, 10, 10));
		s2.setFont(new Font("ARIAL", 20));
		s2.setTextFill(Color.web("#000000"));

		VBox vbox = new VBox(s1a,s1 , s2a, s2);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}

	HBox createBottomPane() {

		Button newGame = new Button("new Game");
		newGame.setOnAction(event -> {	game.resetGame();
										resetPitch();
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
		}
		if (game.getPlayer()==2) {
			label2.setText(game.getSecondPlayer() + " hat gewonnen");
		}
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
		button1.setOnAction(event -> {
			game.setFirstPlayer(textField1.getText().toString());
			game.setSecondPlayer(textField2.getText().toString());
			window.close();
			root.setLeft(getLeftHBox());
		});
		Button button2 = new Button("Exit");
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