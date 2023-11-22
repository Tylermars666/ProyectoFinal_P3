package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Cliente;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ClienteImplement implements CRUD {
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

        ArrayList<String> datosLogin = (ArrayList<String>) objeto;

        try{

            if(!existeCliente(datosLogin.get(1))){

                return new Response("no existe",null);

            }else{

                Cliente clienteValido = match(datosLogin.get(0),datosLogin.get(1));
                if(clienteValido!=null){

                    return new Response("valido",clienteValido);

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean existeCliente(String identificacion){

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/clientes/listaClientes"));
            ArrayList<Cliente>listaClientes = (ArrayList<Cliente>) objetoListado.readObject();
            objetoListado.close();

            for(Cliente cliente: listaClientes){

                if(cliente.getIdentificacion().equalsIgnoreCase(identificacion)){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }

    public Cliente match(String correo, String identificacion){

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/clientes/listaClientes"));
            ArrayList<Cliente>listaClientes = (ArrayList<Cliente>) objetoListado.readObject();
            objetoListado.close();

            for(Cliente cliente: listaClientes){

                if(cliente.getIdentificacion().equalsIgnoreCase(identificacion) && cliente.getEmail().equalsIgnoreCase(correo)){
                    return cliente;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
