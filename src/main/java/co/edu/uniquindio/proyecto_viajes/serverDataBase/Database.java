package co.edu.uniquindio.proyecto_viajes.serverDataBase;

import co.edu.uniquindio.proyecto_viajes.serverDataBase.logic.*;

import java.util.ArrayList;

/**
 * Clase que va a ser manipulada por el ServidorDB, y basicamente lo que hará será gestionar la información
 * es decir, va a serializar objetos para almacenarlos o tomar los objetos serializados para enviarlos
 * básicamente tendrá los métodos para obtener la información
 */
public class Database {                           //OBJETO SINGLETON


    private CRUD admin = new AdminImplement();
    private CRUD cliente = new ClienteImplement();
    private CRUD comentario = new ComentarioImplement();
    private CRUD destino = new DestinoImplement();
    private CRUD guia = new GuiaImplement();
    private CRUD paquete = new PaqueteImplement();
    private CRUD reserva = new ReservaImplement();


    private static Database instance;
    private Database(){}
    public static Database getInstance(){
        if(instance==null){
            instance = new Database();
        }
        return instance;
    }

    public boolean crear(String entidad, Object objeto){

        //Implementar switch para ver cual entidad se va a crear y almacenar en la base de datos

        return false;
    }

    public boolean eliminar(String entidad, Object objeto){

        return false;
    }

    public Object editar(String entidad, Object objeto){

        return null;
    }

    public ArrayList<Object> listar(String entidad, Object objeto){

        return null;
    }



    



}
