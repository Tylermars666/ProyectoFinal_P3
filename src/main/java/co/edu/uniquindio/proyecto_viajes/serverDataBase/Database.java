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

    public Object crear(String entidad, Object objeto){

        //Implementar switch para ver cual entidad se va a crear y almacenar en la base de datos

        Object response = null;

        switch (entidad){
            case "admin":
                response = admin.crear(objeto);
                break;

            case "cliente":
                response = cliente.crear(objeto);
                break;

            case "comentario":
                response = comentario.crear(objeto);
                break;

            case "destino":
                response = destino.crear(objeto);
                break;

            case "guia":
                response = guia.crear(objeto);
                break;
            case "paquete":
                response = paquete.crear(objeto);
                break;
            case "reserva":
                response = reserva.crear(objeto);
                break;
        }

        return response;
    }

    public Object eliminar(String entidad, Object objeto){

        Object response = null;

        switch (entidad){
            case "admin":
                response = admin.eliminar(objeto);
                break;

            case "cliente":
                response = cliente.eliminar(objeto);
                break;

            case "comentario":
                response = comentario.eliminar(objeto);
                break;

            case "destino":
                response = destino.eliminar(objeto);
                break;

            case "guia":
                response = guia.eliminar(objeto);
                break;
            case "paquete":
                response = paquete.eliminar(objeto);
                break;
            case "reserva":
                response = reserva.eliminar(objeto);
                break;
        }
        return response;
    }

    public Object editar(String entidad, Object objeto){

        Object response = null;

        switch (entidad){
            case "admin":
                response = admin.editar(objeto);
                break;

            case "cliente":
                response = cliente.editar(objeto);
                break;

            case "comentario":
                response = comentario.editar(objeto);
                break;

            case "destino":
                response = destino.editar(objeto);
                break;

            case "guia":
                response = guia.editar(objeto);
                break;
            case "paquete":
                response = paquete.editar(objeto);
                break;
            case "reserva":
                response = reserva.editar(objeto);
                break;
        }
        return response;
    }

    public Object listar(String entidad, Object objeto){

        Object response = null;

        switch (entidad){
            case "admin":
                response = admin.listar(objeto);
                break;

            case "cliente":
                response = cliente.listar(objeto);
                break;

            case "comentario":
                response = comentario.listar(objeto);
                break;

            case "destino":
                response = destino.listar(objeto);
                break;

            case "guia":
                response = guia.listar(objeto);
                break;
            case "paquete":
                response = paquete.listar(objeto);
                break;
            case "reserva":
                response = reserva.listar(objeto);
                break;
        }
        return response;
    }



    



}
