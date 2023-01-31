module com.example.course_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.course_fx to javafx.fxml;
    exports com.example.course_fx;
    exports com.example.course_fx.Controllers;
    opens com.example.course_fx.Controllers to javafx.fxml;
}