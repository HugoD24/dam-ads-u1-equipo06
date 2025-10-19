package modelo;

/**
 * @author Ignacio
 */

import java.io.Serializable;

public class Pista implements Serializable {
    private final String idPista; // único, inmutable
    private String deporte;
    private String descripcion;
    private boolean disponible;

    /**
     *
     * @param idPista
     * @param deporte
     * @param descripcion
     * @param disponible
     * @throws IdObligatorioException
     */

    public Pista(String idPista, String deporte, String descripcion, boolean disponible) throws IdObligatorioException{
        if (idPista==null ||idPista.isEmpty()){
            throw new IdObligatorioException("El id de la pista no puede ser vacío");
        }
        this.idPista = idPista;
        this.deporte = deporte;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public String getIdPista() {
        return idPista;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "ID: "+idPista;
    }
}
