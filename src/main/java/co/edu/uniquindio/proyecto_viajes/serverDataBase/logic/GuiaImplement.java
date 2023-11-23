package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Guia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GuiaImplement implements CRUD{
    @Override
    public Object crear(Object objeto) {

        Response response = null;

        try{

            if(existeGuia((Guia) objeto)){

                response = new Response("existe",objeto);

            }else{

                ObjectInputStream objetoPersistido = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
                ArrayList<Guia> guiasPersistidos = (ArrayList<Guia>) objetoPersistido.readObject();
                guiasPersistidos.add((Guia) objeto);

                ObjectOutputStream listaDestinosParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
                listaDestinosParaGuardar.writeObject(guiasPersistidos);

                response = new Response("guardado",guiasPersistidos);
            }


            return response;

        }catch (Exception e ){
            e.printStackTrace();
        }

        return response;

    }

    @Override
    public Object eliminar(Object objeto) {


        ArrayList<Guia> listaGuias;

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            listaGuias = (ArrayList<Guia>) objetoListado.readObject();
            boolean existe = existeGuia((Guia) objeto);
            objetoListado.close();

            if(existe){

                int indiceDestino = match((Guia) objeto);
                listaGuias.remove(indiceDestino);
                ObjectOutputStream listaGuiasParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
                listaGuiasParaGuardar.writeObject(listaGuias);
                listaGuiasParaGuardar.flush();
                listaGuiasParaGuardar.close();
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
        ArrayList<Guia> listaGuias = null;

        try{

            ArrayList<Object> idViejoGuiaNuevo = (ArrayList<Object>) objeto;
            String idViejo = (String) idViejoGuiaNuevo.get(0);
            Guia guiaNuevo = (Guia) idViejoGuiaNuevo.get(1);

            int indexGuiaViejo = match(idViejo);
            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            listaGuias = (ArrayList<Guia>) objetoListado.readObject();
            listaGuias.remove(indexGuiaViejo);
            listaGuias.add(guiaNuevo);
            objetoListado.close();
            ObjectOutputStream listaGuiasParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            listaGuiasParaGuardar.writeObject(listaGuias);
            listaGuiasParaGuardar.flush();
            listaGuiasParaGuardar.close();

            response = new Response("editado",listaGuias);

            return response;


        }catch (Exception e ){
            e.printStackTrace();
        }



        return response;
    }

    @Override
    public Object listar(Object objeto) {

        ArrayList<Guia> listaGuias = null;

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            listaGuias = (ArrayList<Guia>) objetoListado.readObject();
            return listaGuias;
        }catch (Exception e){
            e.printStackTrace();
        }


        return listaGuias;


    }



    public boolean existeGuia(Guia guia){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            ArrayList<Guia> listaGuias = (ArrayList<Guia>) objeto.readObject();

            for(Guia guiaPersistido: listaGuias){

                if(guiaPersistido.getIdentificacion().equalsIgnoreCase(guia.getIdentificacion())){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }

    public int match(Guia guia){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            ArrayList<Guia> listaGuias = (ArrayList<Guia>) objeto.readObject();

            for(Guia guiaPersistido: listaGuias){

                if(guiaPersistido.getIdentificacion().equalsIgnoreCase(guia.getIdentificacion())){
                    return listaGuias.indexOf(guiaPersistido);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return -1;

    }

    public int match(String identificacion){
        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/guias/listaGuias"));
            ArrayList<Guia> listaGuias = (ArrayList<Guia>) objeto.readObject();

            for(Guia guiaPersistido: listaGuias){

                if(guiaPersistido.getIdentificacion().equalsIgnoreCase(identificacion)){
                    return listaGuias.indexOf(guiaPersistido);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return -1;

    }
}
