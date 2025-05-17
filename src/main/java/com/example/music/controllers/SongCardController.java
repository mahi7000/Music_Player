package com.example.music.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

import java.io.File;

public class SongCardController {
    @FXML private VBox root;
    @FXML private ImageView albumArt;
    @FXML private Label songTitle;
    @FXML private Label artistName;
    @FXML private Button playButton;
    private Song song;

    public void setSongData(String title, String artist, String imagePath) {
        this.song = new Song(title, artist, imagePath);
        songTitle.setText(title);
        artistName.setText(artist);

        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                albumArt.setImage(new Image(file.toURI().toString()));
            }
        }
    }

    @FXML
    private void handlePlayAction() {
        if (song != null) {
            System.out.println("Playing: " + song.getTitle());
            // Add your play logic here
            // You might want to notify the HomeController or a MusicPlayer service
        }
    }
}