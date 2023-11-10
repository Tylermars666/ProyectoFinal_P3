module co.edu.uniquindio.proyecto_viajes {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyecto_viajes to javafx.fxml;
    exports co.edu.uniquindio.proyecto_viajes;
}