module com.example.tetrisgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tetrisgame to javafx.fxml;
    exports com.example.tetrisgame;
}