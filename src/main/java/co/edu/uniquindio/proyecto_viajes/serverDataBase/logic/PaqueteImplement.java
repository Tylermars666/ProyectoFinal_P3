package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Cliente;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PaqueteImplement implements CRUD{
    @Override
    public Object crear(Object objeto) {

        Response response = null;
        Paquete paquete = (Paquete) objeto;

        try{

            if(existePaquete(paquete.getNombre())){

                response = new Response("existe",objeto);

            }else{

                ObjectInputStream objetoPersistido = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
                ArrayList<Paquete> paquetesPersistidos = (ArrayList<Paquete>) objetoPersistido.readObject();
                paquetesPersistidos.add((Paquete) objeto);

                ObjectOutputStream listaDestinosParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
                listaDestinosParaGuardar.writeObject(paquetesPersistidos);

                response = new Response("guardado",paquetesPersistidos);
            }


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


        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            Object listaPaquetes = objetoListado.readObject();
            return listaPaquetes;

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    public boolean existePaquete(String nombre){

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            ArrayList<Paquete>listaPaquetes = (ArrayList<Paquete>) objetoListado.readObject();
            objetoListado.close();

            for(Paquete paquete: listaPaquetes){

                if(paquete.getNombre().equalsIgnoreCase(nombre)){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }
}
