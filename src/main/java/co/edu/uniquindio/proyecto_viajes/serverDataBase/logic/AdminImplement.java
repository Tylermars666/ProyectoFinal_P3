package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;

import co.edu.uniquindio.proyecto_viajes.admin.model.Administrador;
import co.edu.uniquindio.proyecto_viajes.client.model.Cliente;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AdminImplement implements CRUD{
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

            if(!existeAdmin(datosLogin.get(1))){

                return new Response("no existe",null);

            }else{

                Administrador adminValido = match(datosLogin.get(0),datosLogin.get(1));
                if(adminValido!=null){

                    return new Response("valido",adminValido);

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public boolean existeAdmin(String identificacion){

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/administradores/listaAdministradores"));
            ArrayList<Administrador>listaClientes = (ArrayList<Administrador>) objetoListado.readObject();
            objetoListado.close();

            for(Administrador admin: listaClientes){

                if(admin.getIdentificacion().equalsIgnoreCase(identificacion)){
                    return true;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }

    public Administrador match(String correo, String identificacion){

        try{

            ObjectInputStream objetoListado = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/administradores/listaAdministradores"));
            ArrayList<Administrador>listaClientes = (ArrayList<Administrador>) objetoListado.readObject();
            objetoListado.close();

            for(Administrador admin: listaClientes){

                if(admin.getIdentificacion().equalsIgnoreCase(identificacion) && admin.getEmail().equalsIgnoreCase(correo)){
                    return admin;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
