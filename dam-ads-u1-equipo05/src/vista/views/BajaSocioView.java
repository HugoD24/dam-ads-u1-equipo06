package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import servicio.ClubDeportivo;

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

            boolean eliminado = club.bajaSocio(id);
            if (eliminado) {
                showInfo("Socio eliminado correctamente.");
            } else {
                showError("No se encontró ningún socio con ese ID.");
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
