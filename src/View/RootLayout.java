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
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	MainApp game = new MainApp();

	public void start(Stage primaryStage) {

		int player = game.initPlayer();
		
		root.setTop(createTopPane());
		root.setCenter(createCenterPane(player));
		root.setRight(getRightHBox());
		root.setLeft(getLeftHBox());
		root.setBottom(createBottomPane());						
		root.setStyle("-fx-background-color: #ccebff;");


		Scene scene = new Scene(root, 800, 700);

		primaryStage.setTitle("Vier Gewinnt - Puissance Quatre");
		primaryStage.setScene(scene);
		primaryStage.show();
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

	Pane createCenterPane(int player) {

		//Button
		Button button[] = new Button[7];
		HBox hbox;

		for(int i=0; i<7; i++) {                                                             
			button[i] = new Button("#" +(i+1));
			button[i].setFont(Font.font("Cambria", 10));
			button[i].setStyle("-fx-background-color: #3232ff"); //background color of button

			button[i].setOnAction(event -> {	game.nextPlayer(player);
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
												//game.refreshPitch(i, player);									//Fehlermeldung
												if (game.searchingWinner()==true) {
													popupWinner();
												}
			});

		}
		
		//disable buttons if column is full
		//button[0].disableProperty().(game.checkFullColumnTwo());
		//button.disableProperty().bind(buttonActionProperty.not());
		//button[0].disableProperty().bind(Bindings.size(game.columnPoints(0)).greaterThan(4));
		//button[0].setDisable(game.setCoordinateX() == 5);
		/*
		button[1].disableProperty().bind(game.points[1] == 6);
		button[2].disableProperty().bind(game.points[2] == 6);
		button[3].disableProperty().bind(game.points[3] == 6);
		button[4].disableProperty().bind(game.points[4] == 6);
		button[5].disableProperty().bind(game.points[5] == 6);
		button[6].disableProperty().bind(game.points[6] == 6);
		*/
		hbox = new HBox(25, button[0], button[1], button[2], button[3], button[4], button[5], button[6]);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20, 10, 10, 10));

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
				Circle point = new Circle(0, 0, 25);
				point.setStroke(game.pointColor(player));   //Color of Point
				//point.setFill(null);
				point.setStrokeWidth(5);
				gpane.add(point, game.getCoordinateX(), game.getCoordinateY());
				GridPane.setHalignment(point, HPos.CENTER);
				gpane.setAlignment(Pos.TOP_CENTER );
			}  
		}

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

		Label s1 = new Label("Spieler 1");
		s1.setPadding(new Insets(40, 10, 10, 10));
		s1.setFont(new Font("ARIAL", 20));
		s1.setTextFill(Color.web("#000000"));

		Label s2 = new Label("Spieler 2");
		s2.setPadding(new Insets(10, 10, 10, 10));
		s2.setFont(new Font("ARIAL", 20));
		s2.setTextFill(Color.web("#000000"));

		VBox vbox = new VBox(s1, s2);
		return vbox;
	}

	HBox createBottomPane() {

		Button newGame = new Button("new Game");
		newGame.setOnAction(event -> {	game.resetGame();
		popupWinner();
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
		Label label1 = new Label("Herzlichen Glückwunsch");
		Label label2 = new Label("Spieler X hat gewonnen");
		VBox vbox = new VBox(20, label1, label2);
		Scene windowScene = new Scene(vbox, 300, 200);
		window.setScene(windowScene);
		window.show();
	}
}