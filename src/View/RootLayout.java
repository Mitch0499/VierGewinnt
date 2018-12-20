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
 * Vertigstellungsdatum: 20.12.2018
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
import javafx.stage.Stage;


public class RootLayout extends Application { 
	
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }  

	ArrayList<Line> values = new ArrayList<Line>();				//Arraylist for grid
	BorderPane root = new BorderPane();							//Layout


	public void start(Stage primaryStage) {

		int player = 0;

		root.setTop(createTopPane());
		root.setCenter(createCenterPane(player));
		root.setRight(getRightHBox());
		root.setLeft(getLeftHBox());
		//root.setBottom(createBottomPane());						//Kilian
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
			int zahl = i;                                                                      //Kilian anschauen
			button[i] = new Button("#" +(i+1));
			button[i].setFont(Font.font("Cambria", 10));
			button[i].setStyle("-fx-background-color: #3232ff"); //background color of button
			button[i].setOnAction(event -> {	mainApp.nextPlayer(player);
												mainApp.refreshPitch(zahl, player);				//Kilian anschauen
			});


		}
		hbox = new HBox(20, button[0], button[1], button[2], button[3], button[4], button[5], button[6]);
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
				point.setStroke(MainApp.pointColor(player));   //Color of Point
				//point.setFill(null);
				point.setStrokeWidth(5);
				gpane.add(point, mainApp.getCoordinateX(), mainApp.getCoordinateY());

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

	/*HBox createBottomPane() {

		Button newGame = new Button("new Game");
		newGame.setOnAction(event -> {	mainApp.startNewGame();
		});
		Button exit = new Button("Exit");
		exit.setOnAction(event -> {	mainApp.exitGame();
		});
		
		
		HBox hbox = new HBox(20, newGame, exit);
		hbox.setPadding(new Insets(20, 20, 20, 20));
		hbox.setAlignment(Pos.CENTER_RIGHT);
		return hbox;
	}*/

}