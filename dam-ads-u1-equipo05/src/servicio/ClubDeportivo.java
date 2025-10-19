package servicio;

import java.io.*;
import java.util.ArrayList;
import modelo.*;

/**
 * @author ignacio
 */


/**
 *Gestiona los socios, pistas y reservas
 * Nos permite  resgistrar, buscar y eliminar socios.
 * Nos permite  resgistrar, buscar y eliminar pistas.
 * Nos permite crear y cancelar reservas.
 * Nos permite guardar y cargar datos desde archibes binarios.
 */
public class ClubDeportivo {

    private ArrayList<Socio> socios;
    private ArrayList<Pista> pistas;
    private ArrayList<Reserva> reservas;

    public final String FICHERO_DATOS_SOCIOS = "socios.dat";
    public final String FICHERO_DATOS_PISTA = "pistas.dat";
    public final String FICHERO_DATOS_RESERVA = "reservas.dat";

    /**
     * Contructor princial.
     * Inicia las listas de socios, pistas y resevas.
     */
    public ClubDeportivo() {
        socios = new ArrayList<>();
        pistas = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de socios del club.
     * @return Lista de socios.
     */

    public ArrayList<Socio> getSocios() {
        return socios;
    }

    /**
     * Obtiene la lista de pistas del club.
     * @return Lista de pistas.
     */
    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    /**
     * Obtiene la lista de reservas del club.
     * @return Lista de reservas.
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }


    /**
     * guarda la lista de socios
     * @throws IOException
     */
    public void escribirFicheroSocio() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_DATOS_SOCIOS))) {
            oos.writeObject(socios);
        }
    }

    /**
     * guarda la lista de pistas
     * @throws IOException
     */

    public void escribirFicheroPistas() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_DATOS_PISTA))) {
            oos.writeObject(pistas);
        }
    }

    /**
     * guarda la lista de Reserva
     * @throws IOException
     */
    public void escribirFicheroReserva() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_DATOS_RESERVA))) {
            oos.writeObject(reservas);
        }
    }

    /**
     * lee el fichero socios
     * @throws IOException
     * @throws ClassNotFoundException
     */

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

    /**
     * lee el fichero pistas
     * @throws IOException
     * @throws ClassNotFoundException
     */

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

    /**
     * lee el fichero Resevas
     * @throws IOException
     * @throws ClassNotFoundException
     */

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

    /**
     * busca socio por ID.
     * @param idSocio
     * @return si encuentra socio, da null si no.
     */

    public Socio buscarSocioPorId(String idSocio) {
        for (Socio socio : socios) {
            if (socio.getIdSocio().equals(idSocio)) {
                return socio;
            }
        }
        return null;
    }

    /**
     * busca Pista por Id.
     * @param idPista
     * @return si encuentra pista, da null si no.
     */

    public Pista buscarPistaPorId(String idPista) {
        for (Pista pista : pistas) {
            if (pista.getIdPista().equals(idPista)) {
                return pista;
            }
        }
        return null;
    }

    /**
     * Busca reserva por ID.
     * @param idReserva
     * @return si encuentra reserva, da null si no.
     */

    public Reserva buscarReservaPorId(String idReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                return reserva;
            }
        }
        return null;
    }

    /**
     * da de alta a un nuevo socio
     * @param socio
     * @return true si registra correctamente, false si ya existe.
     */

    public boolean altaSocio(Socio socio) {
        if (buscarSocioPorId(socio.getIdSocio()) != null) {
            return false; // Ya existe un socio con ese ID
        }
        socios.add(socio);
        return true;
    }

    /**
     * da de baja un socio pero solo si no tiene reservas activas
     * @param idSocio
     * @return true si elimina correctamente, false si tiene reservas activa con su id
     */

    public boolean bajaSocio(String idSocio) {
        Socio socio = buscarSocioPorId(idSocio);
        if (socio == null){
            return false;
        }
        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva().equals(idSocio)) {
            return false;
            }
        }
            socios.remove(socio);
            return true;
    }

    /**
     * da de alta una pista
     * @param pista
     * @return true si la registra correctamente, false si ya existe una pista con el mismo id.
     */

    public boolean altaPista(Pista pista) {

        if(buscarPistaPorId(pista.getIdPista()) != null) {
            return false; // Si ya existe.
        }
        pistas.add(pista);
        return true;
    }

    /**
     * Cambia la dispònibilidad de la pista
     * @param idPista
     * @param disponible
     * @return true si la cambio correctamente, false si no encontro la pista
     */

    public boolean cambiarDisponibilidadPista(String idPista, boolean disponible) {
        for (Pista pista : pistas) {
            if (pista.getIdPista().equals(idPista)) {
                pista.setDisponible(disponible);
                return true;
            }
        }
        return false;
    }

    /**
     * crea una reserva. Comprueba por Id que ya no exista
     * @param reserva
     * @return true si se crea correctamente, false si ya existe.
     */

    public boolean crearReserva(Reserva reserva) {
        if (buscarReservaPorId(reserva.getIdReserva()) != null) {
            return false; //lo mismo si ya existe.
        }
        reservas.add(reserva);
        return true;
    }

    /**
     * cancela una reserva existente
     * @param reserva
     * @return true si se elimino bien, false si exite.
     */

    public boolean cancelarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }

}
