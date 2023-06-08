module odx.lotto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens odx.lotto to javafx.fxml;
    exports odx.lotto;
    exports odx.lotto.database;
    opens odx.lotto.database to javafx.fxml;
}