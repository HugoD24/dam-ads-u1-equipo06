package vista.views;

import javafx.scene.control.*;
import modelo.*;
import servicio.ClubDeportivo;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaFormView extends GridPane {
    public ReservaFormView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8);
        setVgap(8);

        TextField id = new TextField();
        ComboBox<Socio> idSocio = new ComboBox<>();
        ComboBox<Pista> idPista = new ComboBox<>();
        DatePicker fecha = new DatePicker(LocalDate.now());
        TextField hora = new TextField("10:00");
        Spinner<Integer> duracion = new Spinner<>(30, 300, 60, 30);
        TextField precio = new TextField("10.0");
        Button crear = new Button("Reservar");

        // Cargar datos en ComboBox
        idSocio.getItems().addAll(club.getSocios());
        idPista.getItems().addAll(club.getPistas());

        addRow(0, new Label("ID Reserva*"), id);
        addRow(1, new Label("Socio*"), idSocio);
        addRow(2, new Label("Pista*"), idPista);
        addRow(3, new Label("Fecha*"), fecha);
        addRow(4, new Label("Hora inicio* (HH:mm)"), hora);
        addRow(5, new Label("Duración (min)"), duracion);
        addRow(6, new Label("Precio (€)"), precio);
        add(crear, 1, 7);

        crear.setOnAction(e -> {
            try {
                // Validación de campos
                if (id.getText().isEmpty() || idSocio.getValue() == null || idPista.getValue() == null || fecha.getValue() == null || hora.getText().isEmpty()) {
                    showError("Todos los campos con * son obligatorios.");
                    return;
                }

                if (!idPista.getValue().isDisponible()) { //para comprobar si esta disponible la pista
                    showError("La pista seleccionada no está disponible.");
                    return;
                }

                LocalTime t = LocalTime.parse(hora.getText());

                Reserva r = new Reserva(
                        id.getText(),
                        idSocio.getValue().getIdSocio(),
                        idPista.getValue().getIdPista(),
                        fecha.getValue(),
                        t,
                        duracion.getValue(),
                        Double.parseDouble(precio.getText())
                );

                boolean ok = club.crearReserva(r);
                if (ok) {
                    club.escribirFicheroReserva();  // Guardar al fichero
                    showInfo("Reserva creada correctamente.");
                    clearForm(id, hora, precio, duracion); // Limpia campos
                } else {
                    showError("No se pudo crear la reserva.");
                }

            } catch (Exception ex) {
                showError("Error al crear la reserva: " + ex.getMessage());
            }
        });
    }

    private void clearForm(TextField id, TextField hora, TextField precio, Spinner<Integer> duracion) {
        id.clear();
        hora.setText("10:00");
        precio.setText("10.0");
        duracion.getValueFactory().setValue(60);
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
