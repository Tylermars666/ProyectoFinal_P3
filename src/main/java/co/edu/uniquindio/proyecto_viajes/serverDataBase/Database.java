package co.edu.uniquindio.proyecto_viajes.serverDataBase;

/**
 * Clase que va a ser manipulada por el ServidorDB, y basicamente lo que hará será gestionar la información
 * es decir, va a serializar objetos para almacenarlos o tomar los objetos serializados para enviarlos
 * básicamente tendrá los métodos para obtener la información
 */
public class Database {                           //OBJETO SINGLETON

    public static Database instance;
    private Database(){}
    public static Database getInstance(){
        if(instance==null){
            instance = new Database();
        }
        return instance;
    }

    



}
