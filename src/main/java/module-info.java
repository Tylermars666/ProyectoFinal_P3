module co.edu.uniquindio.proyecto_viajes {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens co.edu.uniquindio.proyecto_viajes to javafx.fxml;
    exports co.edu.uniquindio.proyecto_viajes;
    exports co.edu.uniquindio.proyecto_viajes.client.controller;
    opens co.edu.uniquindio.proyecto_viajes.client.controller to javafx.fxml;
    exports co.edu.uniquindio.proyecto_viajes.clienteAsesor.controller;
    opens co.edu.uniquindio.proyecto_viajes.clienteAsesor.controller to javafx.fxml;
    exports co.edu.uniquindio.proyecto_viajes.admin.controller;
    opens co.edu.uniquindio.proyecto_viajes.admin.controller to javafx.fxml;
    exports co.edu.uniquindio.proyecto_viajes.client.model;
    opens co.edu.uniquindio.proyecto_viajes.client.model to javafx.base;
}