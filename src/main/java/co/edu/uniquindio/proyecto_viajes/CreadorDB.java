package co.edu.uniquindio.proyecto_viajes;

import co.edu.uniquindio.proyecto_viajes.admin.model.Administrador;
import co.edu.uniquindio.proyecto_viajes.client.logic.Comentario;
import co.edu.uniquindio.proyecto_viajes.client.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreadorDB {

    public static void main(String[] args) throws Exception{


        ArrayList<Administrador> listaAdministradores = new ArrayList<>();  //YA
        ArrayList<Cliente> listaClientes = new ArrayList<>();               //YA
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        ArrayList<Guia> listaGuias = new ArrayList<>();                    //YA
        ArrayList<Paquete> listaPaquetes = new ArrayList<>();              //YA
        ArrayList<Reserva> listaReservas = new ArrayList<>();              //YA
        ArrayList<Destino> listaDestinos = new ArrayList<>();              //YA
        ArrayList<String> idiomas = new ArrayList<>();                      //YA
        ArrayList<byte[]> imagenes = new ArrayList<>();                     //YA

        listaAdministradores.add(new Administrador("omar@gmail.com","1234"));
        listaAdministradores.add(new Administrador("carlos@gmail.com","1234"));
        listaAdministradores.add(new Administrador("eliana@gmail.com","1234"));

        idiomas.add("Ingles");
        idiomas.add("Español");
        idiomas.add("Portugues");

        Cliente cliente = new Cliente("Jairo Mendez","1094953624","jairo@gmail.com","3207368522","Armenia, cra 14 #10N");
        listaClientes.add(cliente);

        Guia guia = new Guia("Elvio Lao","666",idiomas,5);
        listaGuias.add(guia);



        imagenes.add(Files.readAllBytes(Paths.get("T:/lomas/lomas3.jpg")));
        imagenes.add(Files.readAllBytes(Paths.get("T:/lomas/lomas2.jpg")));
        imagenes.add(Files.readAllBytes(Paths.get("T:/lomas/lomas1.jpg")));

        Destino destino = new Destino("Lomas Turbas","Tangamandapio",imagenes,"Frio","Es una ciudad muy empinada");
        listaDestinos.add(destino);

        Paquete paquete = new Paquete("Destinos Lomudos",5,"Pase en caballo",30000,4, LocalDate.now(),listaDestinos);
        listaPaquetes.add(paquete);

        listaReservas.add(new Reserva(LocalDate.parse("2023-10-30"),LocalDate.parse("2023-11-15"),cliente,4,paquete,guia,"Confirmada"));
        listaComentarios.add(new Comentario("Chimba de destino",cliente,paquete));

        ObjectOutputStream listaPersistida = new ObjectOutputStream(new FileOutputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/paquetes/listaPaquetes"));
        listaPersistida.writeObject(listaPaquetes);
        listaPersistida.flush();


        ObjectInputStream listaObtenida = new ObjectInputStream(new FileInputStream("src/main/java/co/edu/uniquindio/proyecto_viajes/serverDataBase/files/destinos/listaDestinos"));
        ArrayList<Destino> listaLeida = (ArrayList<Destino>) listaObtenida.readObject();
        System.out.println(listaLeida.get(0).getDescripcion());

       // listaComentarios.add(new Comentario("Está muy bueno el destino",cliente,))


    }


}
