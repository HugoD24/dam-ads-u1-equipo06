package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import modelo.Reserva;
import servicio.ClubDeportivo;

/**
 * Vista que permite eliminar un socio del sistema.
 *  La vista solicita el ID del socio a eliminar y verifica si tiene reservas activas
 *  antes de permitir su eliminación.
 * Muestra mensajes informativos o de error según el resultado de la operación.
 * @author ignacio
 */
public class BajaSocioView extends GridPane {

    public BajaSocioView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8);
        setVgap(8);

        Label label = new Label("ID del Socio a eliminar:");
        TextField idField = new TextField();
        Button eliminarBtn = new Button("Eliminar");

        add(label, 0, 0);
        add(idField, 1, 0);
        add(eliminarBtn, 1, 1);

        eliminarBtn.setOnAction(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                showError("Debes ingresar un ID.");
                return;
            }

            boolean tieneReservas = false;
            for (Reserva reserva : club.getReservas()) {
                if (reserva.getIdSocio().equals(id)) {
                    tieneReservas = true;
                    break;
                }
            }

            if (tieneReservas) {
                showError("No se puede eliminar el socio. Tiene reservas activas.");
                return;
            }

            boolean eliminado = club.bajaSocio(id);
            if (eliminado) {
                showInfo("Socio eliminado correctamente.");
            } else {
                showError("No se encontró ningún socio con ese ID.");
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
