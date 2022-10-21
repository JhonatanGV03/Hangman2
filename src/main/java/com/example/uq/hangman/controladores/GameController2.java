package com.example.uq.hangman.controladores;

import com.example.uq.hangman.logica.Archivos;
import com.example.uq.hangman.logica.Funcionalidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameController2 {

    //Variables
    Archivos archivos = new Archivos();
    Funcionalidades funcionalidades = new Funcionalidades();
    private String palabra;
    int errores = 1, acertadas = 0;

    //Identificadores
    @FXML
    private Text txtCategoria;
    @FXML
    private ImageView imgMadero;
    @FXML
    private ImageView logoCategoria;
    @FXML
    private HBox hBox;
    @FXML
    private HBox hBox2;
    @FXML
    private Pane panel;
    @FXML
    private Button btnReiniciar;
    @FXML
    private MenuButton elegirOPc;

    //Metodo inicializador

    // metodo para iniciar componentes

    private void initJuego(int n) throws IOException {
        palabraRec = "";
        hBox.getChildren().clear();
        hBox2.getChildren().clear();
        archivos.escogerFichero(n);
        txtCategoria.setText(archivos.getCa());
        logoCategoria.setImage(archivos.getLogoCa());
        palabra = archivos.leerPalabra();

        btnReiniciar.setVisible(false);
        palabra = palabra.toUpperCase();


    //listamos la barra que muestra la palabra y los _
        for (char letra : palabra.toCharArray()) {
            Button btn_nuevo = new Button();
            btn_nuevo.setText(letra + "");
            btn_nuevo.setStyle(" -fx-background-color: transparent;" + " -fx-border-color: transparent;" + "-fx-font-size: 22;" + "-fx-text-fill: transparent;" + "-fx-pref-width: 80;"+"-fx-pref-height: 40;");
            hBox.getChildren().add(btn_nuevo);

            Button btn_nuevo2 = new Button();
            btn_nuevo2.setText("_");
            btn_nuevo2.setStyle(" -fx-background-color: transparent;" + " -fx-border-color: transparent;" + "-fx-font-size: 22;" + "-fx-text-fill: WHITE;"+ "-fx-pref-width: 80;"+"-fx-pref-height: 40;");
            hBox2.getChildren().add(btn_nuevo2);

        }
    }
    //Metodos recursivos
    String palabraRec;
    //metodo para mostrar las letras
    public void mostrarLetraRecursivo(String letra, int pos) {

       if(pos==palabra.length()){
           if(acertadas==palabra.length()){
               ganaPierde.setText("Ganaste! sos diff");
               mostrarSolucion();
           }
       }else {
           Node a = hBox.getChildren().get(pos);
           if (a instanceof Button) {

               if (((Button) a).getText().equals(letra)) {
                   acertadas++;
                   a.setStyle(" -fx-background-color: transparent;" + " -fx-border-color: transparent;" + "-fx-font-size: 22;" + "-fx-text-fill: white;" + "-fx-pref-width: 80;" + "-fx-pref-height: 40;");

                   Node b = hBox2.getChildren().get(pos);
                   if (b instanceof Button) {

                       b.setStyle(" -fx-background-color: transparent;" + " -fx-border-color: transparent;" + "-fx-font-size: 22;" + "-fx-text-fill: TRANSPARENT;" + "-fx-pref-width: 80;" + "-fx-pref-height: 40;");

                   }
               }

           }

           mostrarLetraRecursivo(letra, ++pos);
       }
    }
  //metodo para probar que la letra se encuentre dentro de la palabra a seleccionar
    public void probarLetra(String letra) {

        if (palabra.contains(letra)) {
            mostrarLetraRecursivo(letra,0);
        } else mostrarCuerpo();
    }

    @FXML
    private Label ganaPierde;

    //procedimiento para mostrar el cuerpo
    private void mostrarCuerpo() {
        errores++;
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/uq/hangman/img/" + errores + ".png")));

            imgMadero.setImage(icon);
        } catch (Exception e) {

        }

        if (errores > 5) {
            ganaPierde.setText("Perdiste! muy zzzz");
            mostrarSolucion();
        }

    }
   //procedimiento para mostrar la solucion
    private void mostrarSolucion() {
        btnReiniciar.setVisible(true);

        for (Node a : panel.getChildren()) {

            if (a instanceof Button) {

                a.setDisable(true);

            }

        }
        for (Node a : hBox.getChildren()) {

            if (a instanceof Button) {

                a.setStyle(" -fx-background-color: transparent;" + " -fx-border-color: transparent;" + "-fx-font-size: 22;" + "-fx-text-fill: white;"+ "-fx-pref-width: 80;"+"-fx-pref-height: 40;");
            }

        }
        for (Node a : hBox2.getChildren()) {

            if (a instanceof Button) {

                a.setStyle(" -fx-background-color: transparent;" + " -fx-border-color: transparent;" + "-fx-font-size: 22;" + "-fx-text-fill: TRANSPARENT;"+ "-fx-pref-width: 80;"+"-fx-pref-height: 40;");
            }

        }
    }

    @FXML
    void reiniciar(ActionEvent event) throws IOException {
        Stage stage2 = new Stage();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/uq/hangman/img/logo2.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "game2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 640);
        stage2.getIcons().add(icon);
        stage2.setResizable(false);
        stage2.setTitle("HangMan");
        stage2.setScene(scene);
        stage2.show();
        Stage stage = (Stage) elegirOPc.getScene().getWindow();
        stage.close();
    }

    @FXML
    void salir(ActionEvent event) {
        cerrarVentana(event);
    }


    //iniciamos las categorias

    public void sout(ActionEvent event) throws IOException {
        //reiniciar();
        MenuItem item = (MenuItem) event.getSource();
        String categoria = item.getText();
        if (categoria.equalsIgnoreCase("Animales")){
            initJuego(1);
        } else if (categoria.equalsIgnoreCase("Cine")) {
            initJuego(2);
        }else if (categoria.equalsIgnoreCase("Frutas")){
            initJuego(3);
        }
        elegirOPc.setVisible(false);
        panel.setDisable(false);
        for (Node a : panel.getChildren()) {

            if (a instanceof Button) {
                a.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                    a.setDisable(true);
                    probarLetra(((Button) a).getText());
                });
            }

        }
    }

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }

}
