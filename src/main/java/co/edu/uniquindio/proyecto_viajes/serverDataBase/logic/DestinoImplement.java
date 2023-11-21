package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Destino;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DestinoImplement implements CRUD{
    @Override
    public Object crear(Object objeto){

        Response response = null;

        try{

            ArrayList<Destino> destinos = new ArrayList<>();
            destinos.add((Destino) objeto);

            ObjectOutputStream listaDestinosParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
            listaDestinosParaGuardar.writeObject(destinos);

            response = new Response("Guardado",objeto);

            return response;

        }catch (Exception e ){
            e.printStackTrace();
        }

        return response;
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
        return null;
    }
}
