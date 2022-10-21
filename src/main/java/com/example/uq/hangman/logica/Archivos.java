package com.example.uq.hangman.logica;


import com.example.uq.hangman.controladores.Main;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class Archivos {
    private File fichero;
    public Archivos() {
    }
    int num ;

    String cat;
    File file;
    String   directorioLista;
    Image image;
    public void escogerFichero(int n) {
         num =n;
        File fichero;
                switch (num) {
            case 1 -> {
                fichero = new File("ficheros/Animals.txt");
                cat = "Animales";

                directorioLista ="img/icons8_pets_32.png";

            }

            case 2 -> {
                fichero = new File("ficheros/Cine.txt");
                cat = "Cine";
                directorioLista ="img/cine.png";
            }

            case 3 -> {
                fichero = new File("ficheros/Fruits.txt");
                cat = "Frutas";
                directorioLista ="img/fruits.png";
            }

         default ->   fichero = new File("ficheros/Fruits.txt");
        }

        System.out.println(Objects.requireNonNull(fichero).getName());
        this.fichero = fichero;

        System.out.println(fichero.exists());
        try {
            file = new File(Main.class.getResource(directorioLista).toURI());
            image = new Image(file.toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public String leerPalabra() throws IOException {
        int x;
        String palabra;
        ArrayList<String>  contenido = new ArrayList<>();
        FileReader fr=new FileReader(fichero);
        BufferedReader bfr=new BufferedReader(fr);
        String linea;
        while((linea = bfr.readLine())!=null)
        {
            contenido.add(linea);
        }
        bfr.close();
        fr.close();
        x = (int) (Math.random() * contenido.size());
        palabra = contenido.get(x);
        System.out.println(palabra);
        return palabra;
    }

    public String getCa() {
        return cat;
    }

    public Image getLogoCa() {
        return this.image;
    }
}
