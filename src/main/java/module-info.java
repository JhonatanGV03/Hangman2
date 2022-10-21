module com.example.uq.hangman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.uq.hangman to javafx.fxml;
    opens com.example.uq.hangman.controladores;
    exports com.example.uq.hangman;
    exports com.example.uq.hangman.controladores;
}