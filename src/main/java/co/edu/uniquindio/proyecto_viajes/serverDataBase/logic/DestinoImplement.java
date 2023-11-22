package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Destino;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DestinoImplement implements CRUD{
    @Override
    public Object crear(Object objeto){

        Response response = null;

        try{

            if(existeDestino((Destino) objeto)){

                response = new Response("existe",objeto);

            }else{

                ObjectInputStream objetoPersistido = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
                ArrayList<Destino> destinosPersistidos = (ArrayList<Destino>) objetoPersistido.readObject();
                destinosPersistidos.add((Destino) objeto);

                ObjectOutputStream listaDestinosParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
                listaDestinosParaGuardar.writeObject(destinosPersistidos);

                response = new Response("guardado",destinosPersistidos);
            }


            return response;

        }catch (Exception e ){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Object eliminar(Object objeto) {

        ArrayList<Destino> listaDestinos;

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
            listaDestinos = (ArrayList<Destino>) objetoListado.readObject();
            boolean existe = existeDestino((Destino) objeto);
            objetoListado.close();

            if(existe){

                int indiceDestino = match((Destino) objeto);
                listaDestinos.remove(indiceDestino);
                ObjectOutputStream listaDestinosParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
                listaDestinosParaGuardar.writeObject(listaDestinos);
                listaDestinosParaGuardar.flush();
                listaDestinosParaGuardar.close();
                return new Response("eliminado",objeto);
            }else {

                return new Response("no existe",objeto);

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public Object editar(Object objeto) {


        return false;
    }

    @Override
    public Object listar(Object objeto) {

        ArrayList<Destino> listaDestinos = null;

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
            listaDestinos = (ArrayList<Destino>) objetoListado.readObject();
            return listaDestinos;
        }catch (Exception e){
            e.printStackTrace();
        }


        return listaDestinos;
    }

    public boolean existeDestino(Destino destino){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
            ArrayList<Destino> listaDestinos = (ArrayList<Destino>) objeto.readObject();

            for(Destino destinoPersistido: listaDestinos){

                if(destinoPersistido.getNombre().equalsIgnoreCase(destino.getNombre())){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }

    public int match(Destino destino){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
            ArrayList<Destino> listaDestinos = (ArrayList<Destino>) objeto.readObject();

            for(Destino destinoPersistido: listaDestinos){

                if(destinoPersistido.getNombre().equalsIgnoreCase(destino.getNombre())){
                    return listaDestinos.indexOf(destinoPersistido);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return -1;

    }


}
