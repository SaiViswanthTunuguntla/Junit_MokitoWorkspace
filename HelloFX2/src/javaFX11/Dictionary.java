package javaFX11;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;

public class Dictionary extends Application {
	
	 private Connection connection ;
	 
    // PreparedStatement for executing queries
    private PreparedStatement preparedStatement1;
    private PreparedStatement preparedStatement2;
    private TextField tfWord = new TextField();
    private ListView<String> lvWords = new ListView();
    private TextArea taDef = new TextArea();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
    	
        // Initialize database connection and create a Statement object
        initializeDB();
        
        System.out.println("the Connection "+ connection);
        Button btSearch = new Button("Search");
        lvWords.setPrefSize(200, 200);
        taDef.setPrefSize(200, 200);
        HBox hBox = new HBox(6);
        hBox.getChildren().addAll(new Label("Word"), tfWord, btSearch,
                lvWords, taDef);

        tfWord.setPrefColumnCount(6);
        btSearch.setOnAction(e -> this.showWords());
        lvWords.getSelectionModel().selectedItemProperty().addListener(
                (v,ov,nv) -> showDef(v,ov,nv));

        // Create a scene and place it in the stage
        Scene scene = new Scene(hBox, 600, 245);
        primaryStage.setTitle("Dictionary"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void initializeDB() {
        
     System.out.println("Initialize DB");  	

        	    
    try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Dictionary", "root", "Indira@248483");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
             

            System.out.println("Driver loaded");

            // ToDo: Insert the code that establishes a connection
            // to the database.
            //
            // ...
 

            System.out.println("Database connected");

            // ToDo: Create Prepared statements to do the queries 
            // you need to do.
            // They will look like:
            // preparedStatement1 = connection.some_method...
            // preparedStatement12 = connection.some_method...
            // 
            // Where you call some method on your connection Object
        
    }

    private void showWords() {
    	
        String word = tfWord.getText();
        
        String query = "select * from entries where word Like  '%"+ word +"%' ";
        
        System.out.println(query + " Connection " + connection);
        ArrayList<String> wordsList = new ArrayList<String>();
        try {
        	ResultSet rset = connection.createStatement().executeQuery(query);
			
			while (rset.next()) {
            String resWord = rset.getString(1);
            wordsList.add(resWord);
       }
       lvWords.setItems(FXCollections.observableArrayList(wordsList));
    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
             
    }

    private void showDef(ObservableValue<? extends String> ov,
                         String old_val, String new_val) {
    	System.out.println(old_val+new_val);
    	String query = "select * from entries where id = '"+ new_val +"'";
    	
        System.out.println(query + " Connection " + connection);
        ArrayList<String> wordsList = new ArrayList<String>();
        try {
        	ResultSet rset = connection.createStatement().executeQuery(query);
			
        	if (rset.next()) {
            String resDef = rset.getString(4);
            taDef.setText(resDef);
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        try {
//            // ToDo:  Add code to use a prepared statement to
//            // retrieve a definition from the database
//            // for the word selected by the user from the
//            // ListView lvWords.
//            // The results from there will be stored in rset:
//            //
//            // ResultSet rset = ...
//
////
////            if (rset.next()) {
////                String resDef = rset.getString(1);
////                taDef.setText(resDef);
////            }
//        }
//        catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

