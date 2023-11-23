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
        ArrayList<Paquete> listaPaquetes;

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            listaPaquetes = (ArrayList<Paquete>) objetoListado.readObject();
            boolean existe = existePaquete((Paquete) objeto);
            objetoListado.close();

            if(existe){

                int indicePaquete = match((Paquete) objeto);
                listaPaquetes.remove(indicePaquete);
                ObjectOutputStream listaPaquetesParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
                listaPaquetesParaGuardar.writeObject(listaPaquetes);
                listaPaquetesParaGuardar.flush();
                listaPaquetesParaGuardar.close();
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
        Response response = null;
        ArrayList<Paquete> listaPaquetes = null;

        try{

            ArrayList<Object> nombreViejoPaqueteNuevo = (ArrayList<Object>) objeto;
            String nombreViejo = (String) nombreViejoPaqueteNuevo.get(0);
            Paquete paqueteNuevo = (Paquete) nombreViejoPaqueteNuevo.get(1);

            int indexPaqueteViejo = match(nombreViejo);
            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            listaPaquetes = (ArrayList<Paquete>) objetoListado.readObject();
            listaPaquetes.remove(indexPaqueteViejo);
            listaPaquetes.add(paqueteNuevo);
            objetoListado.close();
            ObjectOutputStream listaPaquetesParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            listaPaquetesParaGuardar.writeObject(listaPaquetes);
            listaPaquetesParaGuardar.flush();
            listaPaquetesParaGuardar.close();

            response = new Response("editado",listaPaquetes);

            return response;


        }catch (Exception e ){
            e.printStackTrace();
        }



        return response;
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

    public boolean existePaquete(Paquete paquete){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            ArrayList<Paquete> listaPaquetes = (ArrayList<Paquete>) objeto.readObject();

            for(Paquete paquetePersistido: listaPaquetes){

                if(paquetePersistido.getNombre().equalsIgnoreCase(paquete.getNombre())){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }

    public int match(Paquete paquete){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            ArrayList<Paquete> listaPaquetes = (ArrayList<Paquete>) objeto.readObject();

            for( Paquete paquetePersistido: listaPaquetes){

                if(paquetePersistido.getNombre().equalsIgnoreCase(paquete.getNombre())){
                    return listaPaquetes.indexOf(paquetePersistido);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return -1;

    }

    public int match(String nombre){
        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
            ArrayList<Paquete> listaPaquetes = (ArrayList<Paquete>) objeto.readObject();

            for(Paquete paquetePersistido: listaPaquetes){

                if(paquetePersistido.getNombre().equalsIgnoreCase(nombre)){
                    return listaPaquetes.indexOf(paquetePersistido);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return -1;

    }
}
