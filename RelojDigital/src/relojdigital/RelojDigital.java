/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojdigital;

import java.util.Calendar;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author cifue
 */
public class RelojDigital extends Application {

    Calendar calendario;
    Digito[] digitos;
    FadeTransition[]ft = new FadeTransition[4];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        ImageView fondoReloj = new ImageView("imagenes/Caja.jpg");
        StackPane root = new StackPane();
        Image ico = new Image("imagenes/reloj.png");

        digitos = new Digito[6];
        

        calendario = Calendar.getInstance();
        int horas = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);

        digitos[0] = new Digito(horas / 10);
        digitos[1] = new Digito(horas % 10);
        digitos[2] = new Digito(minutos / 10);
        digitos[3] = new Digito(minutos % 10);
        digitos[4] = new Digito(segundos / 10);
        digitos[5] = new Digito(segundos % 10);

        actualizarReloj();
        
        ejecutaReloj();

        VBox[] puntos = new VBox[2];
        puntos[0] = new VBox();
        puntos[1] = new VBox();

        for (VBox vBox : puntos) {
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            vBox.getChildren().addAll(new Circle(5, Color.BLUE), new Circle(5, Color.BLUE));
        }

        for(int i=0;i<4;i++){
            ft[i] = new FadeTransition(Duration.seconds(1));
            ft[i].setFromValue(1);
            ft[i].setToValue(0);
            ft[i].setCycleCount(Timeline.INDEFINITE);
        }
        
        ft[0].setNode(puntos[0].getChildren().get(0));
        ft[1].setNode(puntos[0].getChildren().get(1));
        ft[2].setNode(puntos[1].getChildren().get(0));
        ft[3].setNode(puntos[1].getChildren().get(1));
        
        HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getTransforms().add(new Translate(0, 20));
        contenedor.setSpacing(10);
        contenedor.getChildren().addAll(
                digitos[0],
                digitos[1],
                puntos[0],
                digitos[2],
                digitos[3],
                puntos[1],
                digitos[4],
                digitos[5]
        );

        root.getChildren().addAll(fondoReloj, contenedor);

        Scene scene = new Scene(root);
        stage.setTitle("Reloj Digital");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(ico);
        stage.sizeToScene();//foto ajustada a la ventana
        stage.show();

    }

    private void actualizarReloj() {
        calendario = Calendar.getInstance();
        int horas = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);

        digitos[0].actualizaDigito(horas / 10);
        digitos[1].actualizaDigito(horas % 10);
        digitos[2].actualizaDigito(minutos / 10);
        digitos[3].actualizaDigito(minutos % 10);
        digitos[4].actualizaDigito(segundos / 10);
        digitos[5].actualizaDigito(segundos % 10);
    }

    private void ejecutaReloj() {

        Timeline lineaTiempo = new Timeline();
        Timeline lineaSecundaria = new Timeline();
        lineaSecundaria.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyPrimaria = new KeyFrame(new Duration(1000 - (calendario.get(Calendar.MILLISECOND)) % 1000),
                (event) -> {
                    actualizarReloj();
                    lineaSecundaria.play();
                    for(FadeTransition fade : ft){
                        fade.play();
                    }
                }
        );
        KeyFrame keySec = new KeyFrame(
                Duration.seconds(1),
                (event) -> {
                    actualizarReloj();
                }
        );
        
        lineaTiempo.getKeyFrames().add(keyPrimaria);
        lineaSecundaria.getKeyFrames().add(keySec);
        
        lineaTiempo.play();
    }

}
