package vista.views;

import servicio.ClubDeportivo;
import modelo.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CambiarDisponibilidadView extends GridPane {

    public CambiarDisponibilidadView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8);
        setVgap(8);

        ComboBox<Pista> idCombo = new ComboBox<>();
        idCombo.getItems().addAll(club.getPistas()); // Cargar pistas

        CheckBox disponibleCheck = new CheckBox("Disponible");
        Button cambiarBtn = new Button("Aplicar");

        addRow(0, new Label("Pista"), idCombo);
        addRow(1, new Label("Estado"), disponibleCheck);
        add(cambiarBtn, 1, 2);

        // Mostrar disponibilidad actual al seleccionar pista
        idCombo.setOnAction(e -> {
            Pista seleccionada = idCombo.getValue();
            if (seleccionada != null) {
                disponibleCheck.setSelected(seleccionada.isDisponible());
            }
        });

        cambiarBtn.setOnAction(e -> {
            Pista seleccionada = idCombo.getValue();
            if (seleccionada == null) {
                showError("Debes seleccionar una pista.");
                return;
            }

            boolean nuevaDisponibilidad = disponibleCheck.isSelected();
            boolean cambiado = club.cambiarDisponibilidadPista(seleccionada.getIdPista(), nuevaDisponibilidad);

            if (cambiado) {
                try {
                    club.escribirFicheroPistas(); // Guardar cambios
                    showInfo("Disponibilidad actualizada correctamente.");
                } catch (Exception ex) {
                    showError("Error al guardar cambios: " + ex.getMessage());
                }
            } else {
                showError("No se pudo cambiar la disponibilidad.");
            }
        });
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Error");
        a.showAndWait();
    }

    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
