module co.edu.uniquindio.proyecto_viajes {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyecto_viajes to javafx.fxml;
    exports co.edu.uniquindio.proyecto_viajes;
    exports co.edu.uniquindio.proyecto_viajes.client.controller;
    opens co.edu.uniquindio.proyecto_viajes.client.controller to javafx.fxml;
}