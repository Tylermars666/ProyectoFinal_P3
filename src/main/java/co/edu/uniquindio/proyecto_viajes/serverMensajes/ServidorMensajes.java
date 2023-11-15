package co.edu.uniquindio.proyecto_viajes.serverMensajes;


import co.edu.uniquindio.proyecto_viajes.client.logic.MensajeChat;
import co.edu.uniquindio.proyecto_viajes.serverDataBase.ServidorDB;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Esta clase será el servidor de mensajes, que básicamente cumple la función de ser el intermediario
 * entre el cliente que se quiere comunicar y el asesor que va a responderle.
 */
public class ServidorMensajes implements Runnable {

    private MensajeChat objetoMensaje;

    public static void main(String[] args) {

        ServidorDB servidor = new ServidorDB();
        servidor.init();

    }

    @Override
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(9090);     //Este socket es el que recibe mensajes de los clientes

            while (true){

                Socket cliente = serverSocket.accept();

                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());

                objetoMensaje = (MensajeChat) flujoEntrada.readObject();

                System.out.printf("%s : %s | para: %s", objetoMensaje.getNickRemitente(),objetoMensaje.getCuerpoMensaje(),objetoMensaje.getIpReceptor());

                flujoEntrada.close();
                cliente.close();

                Socket servidorSocket = new Socket(objetoMensaje.getIpReceptor(),9191);     //Este socket es el que redirige los mensajes a cada cliente

                ObjectOutputStream flujoSalida = new ObjectOutputStream(servidorSocket.getOutputStream());

                flujoSalida.writeObject(objetoMensaje);

                flujoSalida.close();


            }




        }catch (Exception e){

            e.printStackTrace();

        }

    }

    public void init (){

        Thread hiloServidorMensajes = new Thread(this);
        hiloServidorMensajes.start();

    }


}
