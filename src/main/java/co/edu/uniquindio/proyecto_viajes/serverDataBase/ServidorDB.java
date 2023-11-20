package co.edu.uniquindio.proyecto_viajes.serverDataBase;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Esta clase será el programa servidor de base de datos, que estará al pendiente de
 * cualquier solicitud para obtener información de la base de datos.
 * Y básicamente va a implementar sockets para estar a la escucha de las peticiones, y devolver la información
 * manipulando los métodos de la clase Database
 */
public class ServidorDB implements Runnable{

    public static void main(String[] args) {

        ServidorDB servidor = new ServidorDB();                  //TODO
        servidor.init();

    }

    @Override
    public void run() {

        try{

            ServerSocket serverSocket = new ServerSocket(9595);
            while(true){

                Socket cliente = serverSocket.accept();
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                DataPaquete paqueteEntrada = (DataPaquete) flujoEntrada.readObject();
                Object response;



                ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());

                if(paqueteEntrada.getRequest().equalsIgnoreCase("crear")){
                    response = Database.getInstance().crear(paqueteEntrada.getNombreEntidad(),paqueteEntrada);
                    flujoSalida.writeObject(response);
                } else if (paqueteEntrada.getRequest().equalsIgnoreCase("editar")) {
                    response = Database.getInstance().editar(paqueteEntrada.getNombreEntidad(),paqueteEntrada);
                    flujoSalida.writeObject(response);
                } else if (paqueteEntrada.getRequest().equalsIgnoreCase("listar")) {
                    response = Database.getInstance().listar(paqueteEntrada.getRequest(),paqueteEntrada);
                    flujoSalida.writeObject(response);
                } else if (paqueteEntrada.getRequest().equalsIgnoreCase("eliminar")) {
                    response = Database.getInstance().eliminar(paqueteEntrada.getNombreEntidad(),paqueteEntrada);
                    flujoSalida.writeObject(response);
                }

                flujoEntrada.close();
                flujoSalida.close();
                cliente.close();





            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void init (){

        Thread hiloServidor = new Thread(this);
        hiloServidor.start();

    }
}
