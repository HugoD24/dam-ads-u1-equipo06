package servicio;

import java.util.ArrayList;

import modelo.*;

public class ClubDeportivo {
    private  ArrayList<Socio> socios ;
    private ArrayList<Pista> pistas ;
    private ArrayList<Reserva> reservas ;
    public final String FICHERO_DATOS_SOCIOS="";
    public final String FICHERO_DATOS_PISTA="";
    public final String FICHERO_DATOS_RESERVA="";

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
}
