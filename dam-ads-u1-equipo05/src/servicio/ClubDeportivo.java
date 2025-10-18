package servicio;

import java.io.*;
import java.util.ArrayList;

import modelo.*;

public class ClubDeportivo {
    private  ArrayList<Socio> socios ;
    private ArrayList<Pista> pistas ;
    private ArrayList<Reserva> reservas ;
    public final String FICHERO_DATOS_SOCIOS="socios.dat";
    public final String FICHERO_DATOS_PISTA="pista.dat";
    public final String FICHERO_DATOS_RESERVA="reservas.dat";

    public ClubDeportivo() {
         socios = new ArrayList<>();
         pistas = new ArrayList<>();
         reservas = new ArrayList<>();
    }

    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }


    public void escribirFicheroSocio() throws IOException {
        FileOutputStream fos=new FileOutputStream(FICHERO_DATOS_SOCIOS);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(socios);

        oos.close();
        fos.close();

    }

    public void escribirFicheroPistas() throws IOException {
        FileOutputStream fos=new FileOutputStream(FICHERO_DATOS_PISTA);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(pistas);

        oos.close();
        fos.close();

    }

    public void escribirFicheroReserva() throws IOException {
        FileOutputStream fos=new FileOutputStream(FICHERO_DATOS_RESERVA);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(reservas);

        oos.close();
        fos.close();

    }


    public void leerFicheroSocios() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream(FICHERO_DATOS_SOCIOS);
        ObjectInputStream ois=new ObjectInputStream(fis);
        socios=(ArrayList<Socio>) ois.readObject();

        ois.close();
        fis.close();
    }

    public void leerFicheroPistas() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream(FICHERO_DATOS_PISTA);
        ObjectInputStream ois=new ObjectInputStream(fis);
        pistas=(ArrayList<Pista>) ois.readObject();

        ois.close();
        fis.close();
    }

    public void leerFicheroReservas() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream(FICHERO_DATOS_RESERVA);
        ObjectInputStream ois=new ObjectInputStream(fis);
        reservas=(ArrayList<Reserva>) ois.readObject();

        ois.close();
        fis.close();
    }


    public boolean altaSocio(Socio socio) {

        return socios.add(socio);
    }
}
