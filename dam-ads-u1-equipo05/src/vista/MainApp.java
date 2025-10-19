package vista;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import modelo.*;
import servicio.ClubDeportivo;
import vista.views.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.lang.classfile.Label;
import java.nio.file.Path;

public class MainApp extends Application {

    private ClubDeportivo club;
    private BorderPane root;
    private Label status;





    @Override
    public void start(Stage stage) throws Exception {

        club = new ClubDeportivo();
        File fp = new File(club.FICHERO_DATOS_PISTA);
        File fs = new File(club.FICHERO_DATOS_SOCIOS);
        File fr = new File(club.FICHERO_DATOS_RESERVA);
        try {

            //LLamo al métoodo de la lógica para cargar los datos del fichero

            if(fp.exists()){
                club.leerFicheroPistas();
            }
            else{
                club.escribirFicheroPistas();
            }

            if(fs.exists()){
                club.leerFicheroSocios();
            }
            else{
                club.escribirFicheroSocio();
            }

            if(fr.exists()){
                club.leerFicheroReservas();
            }
            else{

                club.escribirFicheroReserva();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error al cargar datos de pistas: " + e.getMessage());
        }


        root = new BorderPane();
        root.setTop(buildMenuBar());

        // Vista por defecto
        root.setCenter(new DashboardView(club));

        Scene scene = new Scene(root, 960, 640);
        stage.setTitle("Club DAMA Sports");
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar buildMenuBar() {
        MenuBar mb = new MenuBar();

        Menu socios = new Menu("Socios");
        MenuItem altaSocio = new MenuItem("Alta socio");
        altaSocio.setOnAction(e -> root.setCenter(new SocioFormView(club)));
        MenuItem bajaSocio = new MenuItem("Baja socio");
        bajaSocio.setOnAction(e -> root.setCenter(new BajaSocioView(club)));
        socios.getItems().addAll(altaSocio, bajaSocio);

        Menu pistas = new Menu("Pistas");
        MenuItem altaPista = new MenuItem("Alta pista");
        altaPista.setOnAction(e -> root.setCenter(new PistaFormView(club)));
        MenuItem cambiarDisp = new MenuItem("Cambiar disponibilidad");
        cambiarDisp.setOnAction(e -> root.setCenter(new CambiarDisponibilidadView(club)));
        pistas.getItems().addAll(altaPista, cambiarDisp);

        Menu reservas = new Menu("Reservas");
        MenuItem crearReserva = new MenuItem("Crear reserva");
        crearReserva.setOnAction(e -> root.setCenter(new ReservaFormView(club)));
        MenuItem cancelarReserva = new MenuItem("Cancelar reserva");
        cancelarReserva.setOnAction(e -> root.setCenter(new CancelarReservaView(club)));
        reservas.getItems().addAll(crearReserva, cancelarReserva);

        Menu ver = new Menu("Ver");
        MenuItem dashboard = new MenuItem("Dashboard");
        dashboard.setOnAction(e -> root.setCenter(new DashboardView(club)));
        ver.getItems().addAll(dashboard);

        Menu archivo = new Menu("Archivo");
        MenuItem guardar = new MenuItem("Guardar");
        guardar.setOnAction(e -> {
            try {
                //    LLamo al metodo del modelo para guardar los datos en fichero


                club.escribirFicheroPistas();
                club.escribirFicheroSocio();
                club.escribirFicheroReserva();
                showInfo("Datos guardados correctamente.");
            } catch (Exception ex) {
                showError("Error guardando: " + ex.getMessage());
            }
        });


        MenuItem salir = new MenuItem("Salir");
        salir.setOnAction(e -> {
            try {
                //Lammo al metodo del modelo para guardar antes de salir

                club.escribirFicheroPistas();
                club.escribirFicheroSocio();
                club.escribirFicheroReserva();
            } catch (Exception ignored) {}
            Platform.exit();
        });
        archivo.getItems().addAll(guardar, new SeparatorMenuItem(), salir);

        mb.getMenus().addAll(archivo, socios, pistas, reservas, ver);
        return mb;
    }



    public void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }

    public void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Error");
        a.showAndWait();
    }

    @Override
    public void stop() throws Exception {
        try {
            //   LLamo al metodo del modelo para guardar los datos

            club.escribirFicheroPistas();
            club.escribirFicheroSocio();
            club.escribirFicheroReserva();

        } catch (Exception ignored) {}
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
