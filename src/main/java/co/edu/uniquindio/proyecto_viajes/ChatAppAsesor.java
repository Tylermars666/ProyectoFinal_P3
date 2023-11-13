package co.edu.uniquindio.proyecto_viajes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Este es el programa que usará el asesor para comunicarse con el cliente
 * simplemente es una ventana de chat para recibir y responder mensajes
 */
public class ChatAppAsesor extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatAppAsesor.class.getResource("clienteAsesor/chat-asesor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}