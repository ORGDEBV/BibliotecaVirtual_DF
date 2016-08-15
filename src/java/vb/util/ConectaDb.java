package vb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConectaDb {

    public ConectaDb() {
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            ResourceBundle props = ResourceBundle.getBundle("vb.util.bv");
            Class.forName(props.getString("DRIVER"));

            //cn = DriverManager.getConnection("jdbc:sqlserver://DEV1BNP:1433;databaseName=BVIRTUAL", "devbvirtual", "devbvirtual");
           //cn = DriverManager.getConnection("jdbc:sqlserver://100101060104-32:1433;databaseName=BVIRTUAL_V2", "sa", "123456789");
            cn = DriverManager.getConnection(props.getString("URL"),props.getString("USUARIO"),props.getString("PASSWORD"));
           // cn = DriverManager.getConnection("jdbc:sqlserver://100101060104-29:1433;databaseName=BD_BVIRTUAL", "bvirtual", "123456789");
            //cn = DriverManager.getConnection("jdbc:sqlserver://FEARLESS:1433;databaseName=BVIRTUAL", "sa", "123456789");
            if (cn != null) {
                System.out.println("Conexion realizada satisfactoriamente.");
            }

        } catch (ClassNotFoundException | SQLException objException) {
            System.out.println("Error: " + objException.getMessage());
        }
        return cn;
    }

}
