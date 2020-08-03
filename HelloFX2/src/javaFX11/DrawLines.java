package javaFX11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawLines extends Application {
	
   //Displaying layout pane
    Pane pane = new Pane();
    double X = 100;
    double Y = 100;
    public void start(Stage tab) {
        pane.setOnKeyPressed(e -> {
        	
            //Functions to select arrow keys action
            switch (e.getCode()) {
                case UP:  pane.getChildren().add(new Line(X, Y, X, Y - 10));
                Y -= 10;
                break;
                case DOWN:  pane.getChildren().add(new Line(X, Y, X, Y + 10));
                Y += 10;
                break;
                case LEFT:  pane.getChildren().add(new Line(X, Y, X - 10, Y));
                X -= 10;
                break;
                case RIGHT:  pane.getChildren().add(new Line(X, Y, X + 10, Y));
                X += 10; 
                break;
            }
        });
        tab.setScene(new Scene(pane, 400, 200));
        tab.show();
        pane.requestFocus();
    }
   public static void main(String[] args) {
        DrawLines.launch(args);
    }
}

