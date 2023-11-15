package co.edu.uniquindio.proyecto_viajes.clienteAsesor.controller;

import co.edu.uniquindio.proyecto_viajes.client.logic.MensajeChat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AsesorController implements Runnable, Initializable {

    @FXML
    private Button btnEnviar;

    @FXML
    private TextArea txtAreaChat;

    @FXML
    private TextField txtMensaje;

    @FXML
    private TextField txtMiNick;

    @FXML
    private TextField txtIpReceptor;

    @FXML
    private TextField txtIpServidor;


    @FXML
    void enviarMensaje(ActionEvent event) {

        try{
            Socket clienteAsesor = new Socket(this.txtIpServidor.getText(),9090);

            ObjectOutputStream flujoSalida = new ObjectOutputStream(clienteAsesor.getOutputStream());

            MensajeChat objetoMensajeSalida = new MensajeChat(this.txtMiNick.getText(),this.txtIpReceptor.getText(),this.txtMensaje.getText());

            this.txtAreaChat.appendText("\n" + "Yo: " + this.txtMensaje.getText());

            this.txtMensaje.clear();

            flujoSalida.writeObject(objetoMensajeSalida);

            flujoSalida.close();                    //Cliente cierra el flujo de salida, el socket lo cierra el server

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

        try{

            ServerSocket servidorAsesor = new ServerSocket(9191);

            Socket clienteSocket = servidorAsesor.accept();

            ObjectInputStream flujoEntrada = new ObjectInputStream(clienteSocket.getInputStream());

            MensajeChat objetoMensajeEntrada = (MensajeChat) flujoEntrada.readObject();

            this.txtAreaChat.appendText("\n"+objetoMensajeEntrada.getNickRemitente() + ": " + objetoMensajeEntrada.getCuerpoMensaje());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Thread hiloAsesor = new Thread(this);
        hiloAsesor.start();

    }
}
