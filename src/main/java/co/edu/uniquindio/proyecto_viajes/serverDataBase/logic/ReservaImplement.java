package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Reserva;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReservaImplement implements CRUD{
    @Override
    public Object crear(Object objeto) {

        Response response = null;

        try{

            if(existeReserva((Reserva) objeto)){

                response = new Response("existe",objeto);

            }else{

                ObjectInputStream objetoPersistido = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/reservas/listaReservas"));
                ArrayList<Reserva> reservasPersistidos = (ArrayList<Reserva>) objetoPersistido.readObject();
                reservasPersistidos.add((Reserva) objeto);

                ObjectOutputStream listaReservasParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/reservas/listaReservas"));
                listaReservasParaGuardar.writeObject(reservasPersistidos);

                response = new Response("guardado",reservasPersistidos);
            }


            return response;

        }catch (Exception e ){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Object eliminar(Object objeto) {

        ArrayList<Reserva> listaReservas;

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/reservas/listaReservas"));
            listaReservas = (ArrayList<Reserva>) objetoListado.readObject();
            boolean existe = existeReserva((Reserva) objeto);
            objetoListado.close();

            if(existe){

                int indiceReserva = match((Reserva) objeto);
                listaReservas.remove(indiceReserva);
                ObjectOutputStream listaReservasParaGuardar = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/reservas/listaReservas"));
                listaReservasParaGuardar.writeObject(listaReservas);
                listaReservasParaGuardar.flush();
                listaReservasParaGuardar.close();
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
        return null;
    }

    public boolean existeReserva(Reserva reserva){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/reservas/listaReservas"));
            ArrayList<Reserva> listaReservas = (ArrayList<Reserva>) objeto.readObject();

            for(Reserva reservaPersistida: listaReservas){

                if(reservaPersistida.getPaquete().getNombre().equalsIgnoreCase(reserva.getPaquete().getNombre())){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }

    public int match(Reserva reserva){

        try{

            ObjectInputStream objeto = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/reserva/listaReservas"));
            ArrayList<Reserva> listaReservas = (ArrayList<Reserva>) objeto.readObject();

            for(Reserva reservaPersistida: listaReservas){

                if(reservaPersistida.getPaquete().getNombre().equalsIgnoreCase(reserva.getPaquete().getNombre())){
                    return listaReservas.indexOf(reservaPersistida);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return -1;

    }

}
