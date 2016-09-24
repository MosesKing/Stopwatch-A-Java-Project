/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkc42stopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author moeses
 */
class AnalogStopWatch {
    // Our Numbers, which is what we are gunna use to make the Stopwatch happen 
    private double tickTimeInSeconds = 1.0;  // change this to change resolution
    private double angleDeltaPerSeconds = 6.0;
    private double secondsElapsed = 0;
    // How are computer TimeLine/KeyFrame 
    private Timeline timeline;
    private KeyFrame keyFrame;
    // Other Variables for the Images 
    private StackPane analogClockContainer;
    // stackPane to stack the images on top of each other
    private ImageView dialImageView;
    private ImageView secondHandImageView;
    private Image dialImage;
    private Image secondHandImage;
    private String dialImageName = "clockface.png";
    private String secondHandImageName = "second.png";
    public String formatted;
    public GridPane grid;
    public Label digitalClock;
    
    
    
    public AnalogStopWatch() {
        setupUI();
        setupTimer();
    }
    
    private void setupUI() {
        // Setting Up the Clock on Our Scene
        analogClockContainer = new StackPane();
        dialImageView = new ImageView();
        secondHandImageView = new ImageView(); 
        // Making a Gripane
        grid = new GridPane();
        // positioning the grid on our scene
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(5);
        HBox buttonFieldBox = new HBox(5);
        // where we will put the label into 
        HBox labelFieldBox = new HBox(5);
        // making Label and adding some Style! 
        digitalClock = new Label();
        digitalClock.setStyle("-fx-font-size: 30pt; -fx-font-family: times new roman;");
        // positino the Label HBox onto our Scene
        labelFieldBox.setAlignment(Pos.CENTER);
        labelFieldBox.getChildren().add(digitalClock);
        // Creating our Buttons 
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button resetButton = new Button("Reset");
        // Positioning the Buttons onto the Scene
        buttonFieldBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonFieldBox.getChildren().addAll(startButton,stopButton,resetButton);
        // Setting the Images to the Scene
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        secondHandImage = new Image(getClass().getResourceAsStream(secondHandImageName));
        dialImageView.setImage(dialImage);
        secondHandImageView.setImage(secondHandImage);
        analogClockContainer.getChildren().addAll(dialImageView, secondHandImageView);
        
        // Adding Everything to the Grid
        grid.add(labelFieldBox, 0, 0);
        
        grid.add(analogClockContainer, 0, 1);
        
        grid.add(buttonFieldBox, 0, 2);
        
        //Our Original Clock 
        digitalClock.setText("00:00");
        
        
        // Setting Up our Button Actions !
        startButton.setOnAction((ActionEvent event) -> {
            start();
        });
        stopButton.setOnAction((ActionEvent event) -> {
            stop();
        });
        resetButton.setOnAction((ActionEvent event) -> {
            reset();
        });

    }
    // This method will Set-Up our Timer to work with the Time 
    public void setupTimer() {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    // This method will move the hand acround our clock to the Scene
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        secondHandImageView.setRotate(rotation);
        int minute = (int) secondsElapsed / 60;
        int timelayout = (int) secondsElapsed % 60;
        formatted = String.format("%02d:%02d", minute, timelayout);
        digitalClock.setText(formatted);
    }
    
    public Parent getRootContainer() {
        return grid;
    }
    // getting the width of the Image 
    // good to do this incase we wanna change the Images Used
    public Double getWidth() {
        if (dialImage != null) return dialImage.getWidth();
        else return 0.0;
    }
    // Get's Height 
    // Same as GetWidth for Reason
    public Double getHeight() {
        if (dialImage != null) return dialImage.getHeight() + 100;
        else return 0.0;       
    }
    // Starts the Timer / And Picks Up from Last Played
    public void start() {
        timeline.play();
    }
    // Stops the Timers
    public void stop() {
        timeline.stop();
    }
    // Resets the Whole Clock!!
    public void reset() {
        secondsElapsed = 0;
        secondHandImageView.setRotate(0);
        timeline.stop();
    }

  
}
