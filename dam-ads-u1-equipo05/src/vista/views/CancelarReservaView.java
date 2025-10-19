package vista.views;
import modelo.*;
import servicio.ClubDeportivo;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.function.Consumer;



/**
 * Vista que permite cancelar la reserva.
 *  La vista solicita el ID de reserva
 * Muestra mensajes informativos o de error según el resultado de la operación.
 * @author ignacio
 */
public class CancelarReservaView extends GridPane {
    public CancelarReservaView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        ComboBox<Reserva> id = new ComboBox();
        id.getItems().addAll(club.getReservas());
        Button cancelar = new Button("Cancelar reserva");

        addRow(0, new Label("Reserva"), id);
        add(cancelar, 1, 1);

        cancelar.setOnAction(e -> {
            try {
                Reserva reservaSeleccionada = id.getValue();
                if (reservaSeleccionada == null) {
                    showError("Debes seleccionar una reserva.");
                    return;
                }

                boolean ok = club.cancelarReserva(reservaSeleccionada);
                if (ok) {
                    club.escribirFicheroReserva();
                    showInfo("Reserva cancelada correctamente.");
                    id.getItems().remove(reservaSeleccionada);
                } else {
                    showError("No se pudo cancelar la reserva.");
                }
            } catch (Exception ex) {
                showError("Error al cancelar: " + ex.getMessage());
            }
        });
    }

    /**
     * Muestra la informacion
     * @param msg
     */

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Error");
        a.showAndWait();
    }

    /**
     * Muestra el mensaje de error
     * @param msg "Error"
     */
    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
