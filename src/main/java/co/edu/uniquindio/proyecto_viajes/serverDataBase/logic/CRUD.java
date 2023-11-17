package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import java.util.ArrayList;

public interface CRUD {

    boolean crear();
    boolean eliminar();
    Object editar();
    ArrayList<Object> listar();



}
