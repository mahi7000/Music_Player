package com.example.music.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    // Library pagination
    @FXML private GridPane librarySongsContainer;
    @FXML private Label libraryPageLabel;
    @FXML private Button libraryPrevBtn;
    @FXML private Button libraryNextBtn;
    private List<Song> allLibrarySongs = new ArrayList<>();
    private int currentLibraryPage = 0;
    private final int LIBRARY_PAGE_SIZE = 6;

    // Recent songs pagination
    @FXML private GridPane recentSongsContainer;
    @FXML private Label recentPageLabel;
    @FXML private Button recentPrevBtn;
    @FXML private Button recentNextBtn;
    private List<Song> allRecentSongs = new ArrayList<>();
    private int currentRecentPage = 0;
    private final int RECENT_PAGE_SIZE = 6;

    @FXML
    public void initialize() {
        loadAllSongs();
        loadAllRecentSongs();
        updateLibraryPagination();
        updateRecentPagination();
    }

    private void loadAllSongs() {
        // Replace with your actual song loading logic
        allLibrarySongs = List.of(
                new Song("Blinding Lights", "The Weeknd", "path/to/image1.jpg"),
                new Song("Save Your Tears", "The Weeknd", "path/to/image2.jpg"),
                new Song("Stay", "The Kid LAROI, Justin Bieber", "path/to/image3.jpg"),
                new Song("Levitating", "Dua Lipa", "path/to/image4.jpg"),
                new Song("Don't Start Now", "Dua Lipa", "path/to/image5.jpg"),
                new Song("Watermelon Sugar", "Harry Styles", "path/to/image6.jpg"),
                new Song("As It Was", "Harry Styles", "path/to/image7.jpg"),
                new Song("Bad Habits", "Ed Sheeran", "path/to/image8.jpg"),
                new Song("Shivers", "Ed Sheeran", "path/to/image9.jpg"),
                new Song("Easy On Me", "Adele", "path/to/image10.jpg"),
                new Song("Oh My God", "Adele", "path/to/image11.jpg")
        );
    }

    private void loadAllRecentSongs() {
        // Replace with your actual recent songs loading logic
        allRecentSongs = List.of(
                new Song("Blinding Lights", "The Weeknd", "path/to/image1.jpg"),
                new Song("Save Your Tears", "The Weeknd", "path/to/image2.jpg"),
                new Song("Stay", "The Kid LAROI, Justin Bieber", "path/to/image3.jpg"),
                new Song("Levitating", "Dua Lipa", "path/to/image4.jpg"),
                new Song("Don't Start Now", "Dua Lipa", "path/to/image5.jpg"),
                new Song("Watermelon Sugar", "Harry Styles", "path/to/image6.jpg"),
                new Song("As It Was", "Harry Styles", "path/to/image7.jpg")
        );
    }

    // Library pagination methods
    @FXML
    public void nextLibraryPage() {
        int maxPage = (int) Math.ceil((double) allLibrarySongs.size() / LIBRARY_PAGE_SIZE) - 1;
        if (currentLibraryPage < maxPage) {
            currentLibraryPage++;
            updateLibraryPagination();
        }
    }

    @FXML
    public void previousLibraryPage() {
        if (currentLibraryPage > 0) {
            currentLibraryPage--;
            updateLibraryPagination();
        }
    }

    private void updateLibraryPagination() {
        // Clear current songs
        librarySongsContainer.getChildren().clear();

        // Calculate start and end index
        int start = currentLibraryPage * LIBRARY_PAGE_SIZE;
        int end = Math.min(start + LIBRARY_PAGE_SIZE, allLibrarySongs.size());

        // Add songs to grid
        int row = 0, col = 0;
        for (int i = start; i < end; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/music/views/SongCard.fxml"));
                VBox songCard = loader.load();
                SongCardController controller = loader.getController();
                controller.setSongData(
                        allLibrarySongs.get(i).getTitle(),
                        allLibrarySongs.get(i).getArtist(),
                        allLibrarySongs.get(i).getImagePath()
                );

                librarySongsContainer.add(songCard, col, row);

                col++;
                if (col >= 3) { // 3 columns
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Update pagination controls
        libraryPageLabel.setText("Page " + (currentLibraryPage + 1));
        libraryPrevBtn.setDisable(currentLibraryPage == 0);
        libraryNextBtn.setDisable((currentLibraryPage + 1) * LIBRARY_PAGE_SIZE >= allLibrarySongs.size());
    }

    // Recent songs pagination methods (similar to library)
    @FXML
    public void nextRecentPage() {
        int maxPage = (int) Math.ceil((double) allRecentSongs.size() / RECENT_PAGE_SIZE) - 1;
        if (currentRecentPage < maxPage) {
            currentRecentPage++;
            updateRecentPagination();
        }
    }

    @FXML
    public void previousRecentPage() {
        if (currentRecentPage > 0) {
            currentRecentPage--;
            updateRecentPagination();
        }
    }

    private void updateRecentPagination() {
        recentSongsContainer.getChildren().clear();

        int start = currentRecentPage * RECENT_PAGE_SIZE;
        int end = Math.min(start + RECENT_PAGE_SIZE, allRecentSongs.size());

        int row = 0, col = 0;
        for (int i = start; i < end; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/music/views/SongCard.fxml"));
                VBox songCard = loader.load();
                SongCardController controller = loader.getController();
                controller.setSongData(
                        allRecentSongs.get(i).getTitle(),
                        allRecentSongs.get(i).getArtist(),
                        allRecentSongs.get(i).getImagePath()
                );

                recentSongsContainer.add(songCard, col, row);

                col++;
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        recentPageLabel.setText("Page " + (currentRecentPage + 1));
        recentPrevBtn.setDisable(currentRecentPage == 0);
        recentNextBtn.setDisable((currentRecentPage + 1) * RECENT_PAGE_SIZE >= allRecentSongs.size());
    }
}

class Song {
    private final String title;
    private final String artist;
    private final String imagePath;
    private final String filePath; // Add this for actual song file

    public Song(String title, String artist, String imagePath) {
        this(title, artist, imagePath, null);
    }

    public Song(String title, String artist, String imagePath, String filePath) {
        this.title = title;
        this.artist = artist;
        this.imagePath = imagePath;
        this.filePath = filePath;
    }

    // Getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getImagePath() { return imagePath; }
    public String getFilePath() { return filePath; }
}