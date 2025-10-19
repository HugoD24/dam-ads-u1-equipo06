package servicio;

import java.io.*;
import java.util.ArrayList;
import modelo.*;

public class ClubDeportivo {
    private ArrayList<Socio> socios;
    private ArrayList<Pista> pistas;
    private ArrayList<Reserva> reservas;

    public final String FICHERO_DATOS_SOCIOS = "socios.dat";
    public final String FICHERO_DATOS_PISTA = "pistas.dat";
    public final String FICHERO_DATOS_RESERVA = "reservas.dat";

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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_DATOS_SOCIOS))) {
            oos.writeObject(socios);
        }
    }

    public void escribirFicheroPistas() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_DATOS_PISTA))) {
            oos.writeObject(pistas);
        }
    }

    public void escribirFicheroReserva() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_DATOS_RESERVA))) {
            oos.writeObject(reservas);
        }
    }

    public void leerFicheroSocios() throws IOException, ClassNotFoundException {
        File f = new File(FICHERO_DATOS_SOCIOS);
        if (!f.exists()) {
            socios = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            socios = (ArrayList<Socio>) ois.readObject();
        }
    }

    public void leerFicheroPistas() throws IOException, ClassNotFoundException {
        File f = new File(FICHERO_DATOS_PISTA);
        if (!f.exists()) {
            pistas = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            pistas = (ArrayList<Pista>) ois.readObject();
        }
    }

    public void leerFicheroReservas() throws IOException, ClassNotFoundException {
        File f = new File(FICHERO_DATOS_RESERVA);
        if (!f.exists()) {
            reservas = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            reservas = (ArrayList<Reserva>) ois.readObject();
        }
    }

    public Socio buscarSocioPorId(String idSocio) {
        for (Socio socio : socios) {
            if (socio.getIdSocio().equals(idSocio)) {
                return socio;
            }
        }
        return null;
    }

    public Pista buscarPistaPorId(String idPista) {
        for (Pista pista : pistas) {
            if (pista.getIdPista().equals(idPista)) {
                return pista;
            }
        }
        return null;
    }

    public boolean altaSocio(Socio socio) {
        if (buscarSocioPorId(socio.getIdSocio()) != null) {
            return false; // Ya existe un socio con ese ID
        }
        socios.add(socio);
        return true;
    }

    public boolean bajaSocio(String idSocio) {
        Socio socio = buscarSocioPorId(idSocio);
        if (socio != null) {
            socios.remove(socio);
            return true;
        }
        return false;
    }


    public boolean altaPista(Pista pista) {

        if(buscarPistaPorId(pista.getIdPista()) != null) {
            return false; // Si ya existe.
        }
        pistas.add(pista);
        return true;
    }
}
