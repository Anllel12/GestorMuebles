/*
 * Clase creada para manejar la creacion de la Base de Datos
 * de forma automatica cuando se enciende el programa
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angel
 */
public class CreateBBDD {
    
    public static Connection con = null;
    
    public static void connect(String name){ // conexion a la base de datos.
        
        String user= "root";
        String pass = "";
        
        try {          
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC", user, pass);  // conexion a la URL.
            
            createDataBase(name); // creao la base.
            
            createTables(); // creo las tablas.
           
            if(isEmpty() <= 0){
                insertTables(); // inserto datos.
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(CreateBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public static void disconnect() { // desconexion de la base de datos.
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

    
     private static void createDataBase(String name) throws SQLException { // creo la BBDD
        Statement stmt = con.createStatement();
            
        String data = String.format("CREATE DATABASE IF NOT EXISTS %s;", name);           
        stmt.executeUpdate(data);
        
        String base = String.format("USE %s;", name); 
        stmt.executeUpdate(base);
        
        stmt.close();
    }
     
     private static void createTables() throws SQLException {  // contiene toda la creación de tablas.
        Statement stmt = con.createStatement();

        String muebleTable = "CREATE TABLE IF NOT EXISTS `mueble` (\n" + // creación de Mueble.
            "`modelo` INT(10) NOT NULL,\n" +
            "`nombre` VARCHAR(100) NOT NULL,\n" +
            "`precio` INT(10) NOT NULL,\n" +
            "`paquetes` INT(5) NOT NULL,\n" +
            "PRIMARY KEY (`modelo`)\n" + 
            ");";
        
        stmt.executeUpdate(muebleTable);
        
        String tamañoTable = "CREATE TABLE IF NOT EXISTS `tamano` (\n" + // creación de Tamaño.
        "`id` INT(10) AUTO_INCREMENT,\n" +
        "`ancho` INT(10),\n" +
        "`fondo` INT(10),\n" +
        "`altura` INT(10),\n" +
        "`peso_balda` INT(10),\n" +
        "`mueble` INT(10) NOT NULL,\n" +
        "PRIMARY KEY (`id`),\n" +
        "FOREIGN KEY(`mueble`) REFERENCES mueble(modelo) ON DELETE CASCADE \n" +
        ");";
        
        stmt.executeUpdate(tamañoTable);
        
        String materialesTable = "CREATE TABLE IF NOT EXISTS `material` (\n" + // creación de Materiales.
        "`id` INT(10) AUTO_INCREMENT,\n" +
        "`principal` VARCHAR(100),\n" +
        "`secundario` VARCHAR(100),\n" +
        "`mueble` INT(10) NOT NULL,\n" +
        "PRIMARY KEY (`id`),\n" +
        "FOREIGN KEY(`mueble`) REFERENCES mueble(modelo) ON DELETE CASCADE \n" +
        ");";
        
        stmt.executeUpdate(materialesTable);
 
        stmt.close();
    }
     
    private static void insertTables() throws SQLException {  // insertamos valores a las diferentes tablas.
        Statement stmt = con.createStatement();

        String muebleTable = "INSERT IGNORE INTO `mueble` VALUES \n" + // inserto de datos de Mueble.
        "('00278578', 'HYLLIS', '10', '1'),\n" +
        "('60333850', 'BROR', '99', '1'),\n" +
        "('30409295', 'KOLBJÖRN', '69', '1');";
        
        stmt.executeUpdate(muebleTable);
        
        String tamañoTable = "INSERT IGNORE INTO `tamano` VALUES \n" + // inserto de datos de Tamaño.
        "(0, '60', '27', '140', '25', '00278578'),\n" +
        "(0, '85', '55', '88', '50', '60333850'),\n" +
        "(0, '80', '35', '81', '55', '30409295');";
        
        stmt.executeUpdate(tamañoTable);
        
        String materialesTable = "INSERT IGNORE INTO `material` VALUES \n" + // inserto de datos de Materiales.
        "(0, 'Acero galvanizado', 'Plástico amídico', '00278578'),\n" +
        "(0, 'Acero', 'Contrachapado de pino', '60333850'),\n" +
        "(0, 'Acero galvanizado', 'Revestimiento en polvo de poliéster', '30409295');";
        
        stmt.executeUpdate(materialesTable);
               
        stmt.close();
    }
     
    public static int isEmpty() throws SQLException {  // Comprueba si esta vacía la Base de Datos.            
        int model = 0 ;
        
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS muebles FROM mueble;");  // Le pedimos la cantidad de muebeles que hay.
        
        if (rs.next()) {
            model = rs.getInt("muebles");
        }else {
            model = -1;
        }

        rs.close();
        stmt.close();
        
        return model;
    }
}
