package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.admin.controller.AdministradorController;
import co.edu.uniquindio.proyecto_viajes.admin.model.AdminLogeado;
import co.edu.uniquindio.proyecto_viajes.client.logic.ClienteLogeado;
import co.edu.uniquindio.proyecto_viajes.serverDataBase.logic.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ComboBox<String> cmbRol;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtIdentificacion;

    private ObservableList<String> opcionesCombo;

    private String rolSeleccionado;

    public static ClienteLogeado clienteLogeado;
    public static AdminLogeado adminLogeado;

    @FXML
    void abrirVentanaRegistro(MouseEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/registro-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro");
        stage.setScene(scene);

        RegistroController controller = loader.getController();
        stage.show();

    }

    @FXML
    void elegirRol(ActionEvent event) {

        rolSeleccionado = cmbRol.getSelectionModel().getSelectedItem();

    }

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {

        String correo, identificacion;
        correo = this.txtCorreo.getText();
        identificacion = this.txtIdentificacion.getText();

        if(correo.isBlank() || identificacion.isBlank()){
            new Alert(Alert.AlertType.ERROR,"Valide los datos ingresados", ButtonType.OK).showAndWait();
        }else{

            if(!rolSeleccionado.isBlank()){


                if(rolSeleccionado.equalsIgnoreCase("cliente")){

                    Socket socket = new Socket("localhost",9595);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                    DataPaquete paquete = new DataPaquete("cliente","listar",identificacion);
                    objectOutputStream.writeObject(paquete);
                    objectOutputStream.flush();

                    Response response = (Response) objectInputStream.readObject();
                    objectInputStream.close();
                    objectOutputStream.close();
                    socket.close();

                    if(response.getMensaje().equalsIgnoreCase("valido")){

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/principal-view.fxml"));

                        Scene scene = new Scene(loader.load());
                        Stage stage = new Stage();
                        stage.setTitle("Principal");
                        stage.setScene(scene);

                        PrincipalController controller = loader.getController();
                        stage.show();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"El cliente ingresado no existe", ButtonType.OK).showAndWait();
                    }


                }else{

                    Socket socket = new Socket("localhost",9595);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                    DataPaquete paquete = new DataPaquete("admin","listar",identificacion);
                    objectOutputStream.writeObject(paquete);
                    objectOutputStream.flush();

                    Response response = (Response) objectInputStream.readObject();
                    objectInputStream.close();
                    objectOutputStream.close();
                    socket.close();

                    if(response.getMensaje().equalsIgnoreCase("valido")){

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/admin-view.fxml"));

                        Scene scene = new Scene(loader.load());
                        Stage stage = new Stage();
                        stage.setTitle("Administrador");
                        stage.setScene(scene);

                        AdministradorController controller = loader.getController();
                        stage.show();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"El admin ingresado no existe", ButtonType.OK).showAndWait();
                    }

                }

            }else{

                new Alert(Alert.AlertType.ERROR,"Debe seleccionar un rol", ButtonType.OK).showAndWait();
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        opcionesCombo = FXCollections.observableArrayList("Cliente","Administrador");
        this.cmbRol.setItems(opcionesCombo);


    }
}
