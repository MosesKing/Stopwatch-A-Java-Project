/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkc42stopwatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author moeses
 */
public class Mkc42Stopwatch extends Application {
    private String titleName = "StopWatch";
    
    @Override
    public void start(Stage primaryStage) {


        
        AnalogStopWatch analogStopWatch = new AnalogStopWatch(); 
        Scene scene = new Scene(analogStopWatch.getRootContainer(), 
                                analogStopWatch.getWidth(), 
                                analogStopWatch.getHeight());

        
        primaryStage.setTitle(titleName);
        primaryStage.setScene(scene);
        primaryStage.show();  
      
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
