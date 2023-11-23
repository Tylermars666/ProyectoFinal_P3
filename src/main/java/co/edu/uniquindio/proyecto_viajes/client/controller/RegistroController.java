package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Cliente;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.exception.RegistroExistenteException;
import co.edu.uniquindio.proyecto_viajes.serverDataBase.logic.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RegistroController {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumTel;

    @FXML
    private TextField txtResidencia;

    private String nombre, identificacion, email, numTel, residencia;


    @FXML
    void registrarCliente(ActionEvent event) {

        try{           //SERVIDOR EN LOCALHOST EN ESTE CASO
            Socket socket = new Socket("localhost",9595);


            nombre = this.txtNombre.getText();
            identificacion = this.txtIdentificacion.getText();
            email = this.txtEmail.getText();
            numTel = this.txtNumTel.getText();
            residencia = this.txtResidencia.getText();

            if(nombre.isBlank()||identificacion.isBlank()||email.isBlank()||numTel.isBlank()){
                new Alert(Alert.AlertType.ERROR,"Asegurese de llenar todos los campos", ButtonType.OK).showAndWait();
            }else{

                Cliente clienteCreado = new Cliente(nombre,identificacion,email,numTel,residencia);
                DataPaquete paqueteDatos = new DataPaquete("cliente","crear",clienteCreado);


                ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                flujoSalida.writeObject(paqueteDatos);
                flujoSalida.flush();


                ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) flujoEntrada.readObject();

                flujoSalida.close();
                flujoEntrada.close();
                socket.close();

                if(response.getMensaje().equalsIgnoreCase("registrado")){
                    new Alert(Alert.AlertType.CONFIRMATION,"Cliente registrado con éxito").showAndWait();
                    this.txtNombre.clear();
                    this.txtIdentificacion.clear();
                    this.txtEmail.clear();
                    this.txtNumTel.clear();
                    this.txtResidencia.clear();

                }else{
                    throw new RegistroExistenteException();
                }
            }



        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (RegistroExistenteException e){
            e.getAlert().showAndWait();
        }

    }

}
