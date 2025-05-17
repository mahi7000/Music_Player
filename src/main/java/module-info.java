module com.example.music {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.music to javafx.fxml;
    exports com.example.music;
    exports com.example.music.controllers;
    opens com.example.music.controllers to javafx.fxml;
}