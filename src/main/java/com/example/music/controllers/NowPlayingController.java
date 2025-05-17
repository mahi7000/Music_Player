package com.example.music.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

public class NowPlayingController {
    @FXML private Slider playingSlider;
    @FXML private StackPane albumArtContainer;
    @FXML private Button playButton;
    @FXML private FontIcon playIcon;
    @FXML private Label currentTimeLabel;
    @FXML private Label totalTimeLabel;

    private RotateTransition rotateTransition;
    private Timeline progressTimeline;
    private boolean isPlaying = false;
    private double currentSeconds = 0;
    private final double totalSeconds = 260; // 4:20 in seconds

    @FXML
    public void initialize() {
        // Initialize total time label
        totalTimeLabel.setText(formatTime(totalSeconds));

        setupSlider();
        setupRotationAnimation();
        setupPlayPauseToggle();
        setupProgressTimer();

        startPlayback();
    }

    private void startPlayback() {
        if (!isPlaying) {
            togglePlayPause();
        }
    }

    private void setupSlider() {
        playingSlider.setMax(totalSeconds);

        // Update time label when slider is manually moved
        playingSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            currentSeconds = newVal.doubleValue();
            currentTimeLabel.setText(formatTime(currentSeconds));
            updateSliderStyle();
        });
    }

    private void updateSliderStyle() {
        double percent = (currentSeconds / totalSeconds) * 100;
        var track = playingSlider.lookup(".track");
        if (track != null) {
            String style = String.format(
                    "-fx-background-color: linear-gradient(to right, rgba(255, 205, 216, 1) 0%%, rgba(255, 205, 216, 1) %.2f%%, rgba(255, 205, 216, 0.4) %.2f%%, rgba(255, 205, 216, 0.4) 100%%);",
                    percent, percent
            );
            track.setStyle(style);
        }
    }

    private void setupRotationAnimation() {
        rotateTransition = new RotateTransition(Duration.seconds(10), albumArtContainer);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        rotateTransition.pause();
    }

    private void setupPlayPauseToggle() {
        playButton.setOnAction(event -> togglePlayPause());
    }

    private void togglePlayPause() {
        isPlaying = !isPlaying;
        if (isPlaying) {
            playIcon.setIconLiteral("fa-pause");
            rotateTransition.play();
            progressTimeline.play();
        } else {
            playIcon.setIconLiteral("fa-play");
            rotateTransition.pause();
            progressTimeline.pause();
        }
    }

    private void setupProgressTimer() {
        progressTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (currentSeconds < totalSeconds) {
                        currentSeconds++;
                        playingSlider.setValue(currentSeconds);
                        currentTimeLabel.setText(formatTime(currentSeconds));
                    } else {
                        // Song ended
                        progressTimeline.stop();
                        rotateTransition.stop();
                        isPlaying = false;
                        playIcon.setIconLiteral("fa-play");
                    }
                })
        );
        progressTimeline.setCycleCount(Animation.INDEFINITE);
        progressTimeline.pause();
    }

    private String formatTime(double seconds) {
        int minutes = (int) seconds / 60;
        int secs = (int) seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }
}