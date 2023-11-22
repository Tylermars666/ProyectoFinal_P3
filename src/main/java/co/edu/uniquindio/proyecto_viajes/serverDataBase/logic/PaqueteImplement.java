package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class PaqueteImplement implements CRUD{
    @Override
    public Object crear(Object objeto) {
        return false;
    }

    @Override
    public Object eliminar(Object objeto) {
        return false;
    }

    @Override
    public Object editar(Object objeto) {
        return false;
    }

    @Override
    public Object listar(Object objeto) {


        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            Object listaPaquetes = objetoListado.readObject();
            return listaPaquetes;

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
