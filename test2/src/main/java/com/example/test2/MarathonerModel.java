package com.example.test2;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MarathonerModel extends ImageView {
    private final String name;
    public static final String resourcePath = "\\test2\\src\\main\\resources\\com\\example\\test2\\";
    private final int number;
    private boolean running = true;
    public static boolean waiting = true;
    private TranslateTransition runAnim, resetAnim;
    private final AudioClip endOfRaceCheer;

    public void run() {
        //this function begins the marathoner's run
        runAnim.play();
        waiting = false;
        this.running = true;
    }

    public void reset() {
        //return the marathoner back to start
        resetAnim.play();
        waiting = true;
        setAnim();
    }

    public void pause() {
        //pause the marathoner (stop running)
        runAnim.pause();
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setAnim() {
        //this sets a random-length TranslateTransition animation to the marathoner
        runAnim = new TranslateTransition(Duration.seconds(5.0d - Math.random() * 4.0f), this);
        runAnim.setToX(700.0d);

        runAnim.setOnFinished(e -> {
            //when one marathoner finishes, the winner is determined
            this.running = false;
            if (!Main.weHaveAWinner) {
                Main.weHaveAWinner = true;
                Main.winner = this.toString();
            }
            //this plays the cheering sound when the runner has finished the run
            try {
                endOfRaceCheer.restart();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    public void setResetAnim() {
        //this sets the reset-animation (that puts the marathoner back to the beginning of the race)
        resetAnim = new TranslateTransition(Duration.seconds(0.1d), this);
        resetAnim.setToX(20.0d);
        resetAnim.setOnFinished(e -> {
            running = true;
        });
    }

    public MarathonerModel(String name, int number, String imgName) {
        //initiating the marathoner with each of the following parameters:
        //1. Name, 2. Number, 3. Image, 4. Cheering sound, 5. Run and Reset animations
        this.name = name;
        this.number = number;
        this.setImage(fetchImage(imgName));
        try {
            this.endOfRaceCheer = fetchAudioPlayer();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setFitWidth(100.0d);
        this.setFitHeight(100.0d);

        setAnim();
        setResetAnim();
    }

    public Image fetchImage(String name) {
        //This fct makes sure to get the image from the appropriate folder
        String filePathAndName = new File("").getAbsolutePath() + resourcePath + name;
        return new Image(filePathAndName);
    }

    public AudioClip fetchAudioPlayer() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //getting the audio clip for the cheering sound effect
        String filePathAndName = new File("").getAbsolutePath() + resourcePath + "cheeringAudience.wav";
        return new AudioClip(filePathAndName);
    }

    public String toString() {
        //toString fct that displays the runner's name and number in parentheses
        return this.name + "(" + this.number + ")";
    }


}
