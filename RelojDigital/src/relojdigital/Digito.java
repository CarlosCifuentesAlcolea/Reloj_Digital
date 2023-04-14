/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojdigital;

import javafx.scene.Parent;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author cifue
 */
public final class Digito extends Parent {

    private final ImageView DIGIT;
    private static final Image CERO = new Image("imagenes/0.jpg");
    private static final Image UNO = new Image("imagenes/1.jpg");
    private static final Image DOS = new Image("imagenes/2.jpg");
    private static final Image TRES = new Image("imagenes/3.jpg");
    private static final Image CUATRO = new Image("imagenes/4.jpg");
    private static final Image CINCO = new Image("imagenes/5.jpg");
    private static final Image SEIS = new Image("imagenes/6.jpg");
    private static final Image SIETE = new Image("imagenes/7.jpg");
    private static final Image OCHO = new Image("imagenes/8.jpg");
    private static final Image NUEVE = new Image("imagenes/9.jpg");
    
    public final Glow brillo = new Glow();

    public Digito(int numero) {
        DIGIT = new ImageView();
        actualizaDigito(numero);
        DIGIT.setEffect(brillo);
        //brillo.setInput(new InnerShadow(2, Color.AQUAMARINE));
        getChildren().add(DIGIT);
    }

    public void actualizaDigito(int n) {

        switch (n) {
            case 0:
                DIGIT.setImage(CERO);
                break;
            case 1:
                DIGIT.setImage(UNO);
                break;
            case 2:
                DIGIT.setImage(DOS);
                break;
            case 3:
                DIGIT.setImage(TRES);
                break;
            case 4:
                DIGIT.setImage(CUATRO);
                break;
            case 5:
                DIGIT.setImage(CINCO);
                break;
            case 6:
                DIGIT.setImage(SEIS);
                break;
            case 7:
                DIGIT.setImage(SIETE);
                break;
            case 8:
                DIGIT.setImage(OCHO);
                break;
            case 9:
                DIGIT.setImage(NUEVE);
                break;
            
        }
    }
}
