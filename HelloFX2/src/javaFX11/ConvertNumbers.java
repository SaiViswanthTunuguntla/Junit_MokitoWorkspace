package javaFX11;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConvertNumbers extends Application {
	 public static void main(String[] args) {
		 ConvertNumbers.launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception
	{
	
	GridPane Gpane = new GridPane();
	Gpane.setAlignment(Pos.CENTER);
	Gpane.setPadding(new Insets(25, 22, 23, 24));
	Gpane.setHgap(6);
	Gpane.setVgap(6);
	
	
	Gpane.add(new Label("Decimal-"), 0, 0);
	TextField dectf = new TextField();
	TextField hextf = new TextField();
	TextField bintf = new TextField();
	Gpane.add(dectf, 1, 0);
	Gpane.add(new Label("Hex-"), 0, 1);
	Gpane.add(hextf, 1, 1);
	Gpane.add(new Label("Binary-"), 0, 2);
	Gpane.add(bintf, 1, 2);
	
	
	dectf.setOnKeyPressed(e->{
		//dec to hex
		if(e.getCode()==KeyCode.ENTER) {
			int decimal = Integer.parseInt(dectf.getText());
			String dctohx = Integer.toHexString(decimal);
			String dctobn = Integer.toBinaryString(decimal);
			
			hextf.setText(String.format(dctohx));
			bintf.setText(String.format(dctobn));
		}
	});
	
	hextf.setOnKeyPressed(e->{
		// hex to deec
		if(e.getCode()==KeyCode.ENTER) {
			int hex = Integer.parseInt(hextf.getText(),16);
			String hxtodc = Integer.toString(hex);
			String hxtobn = Integer.toBinaryString(hex);
			
			hextf.setText(hxtodc);
			bintf.setText(String.format(hxtobn));
		}
	});
	
	hextf.setOnKeyPressed(e->{
		//binary to hex
		if(e.getCode()==KeyCode.ENTER) {
			int binary = Integer.parseInt(bintf.getText(),2);
			String bntodc = Integer.toString(binary);
			String bntohx = Integer.toHexString(binary);
			
			dectf.setText(bntodc);
			hextf.setText(String.format(bntohx));
		}
	});
	//
	Scene scene = new Scene(Gpane);
	primaryStage.setTitle("Java_Excercise");
	primaryStage.setScene(scene);
	primaryStage.show();
	Gpane.requestFocus();
}
	
	
}
