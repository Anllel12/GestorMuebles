/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public static void conect(String name){ // me conecto a la base de datos 
        
        String user= "root";
        String pass = "";
        
        try {          
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC", user, pass);  // Creamos la Base de Datos.
            
            createDataBase(name); // creao la base
            
            createTables(); // creo las tablas
            
            insertTables();
           
        } catch (SQLException ex) {
            Logger.getLogger(CreateBBDD.class.getName()).log(Level.SEVERE, null, ex);
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
     
     private static void createTables() throws SQLException {  // Esto contiene toda la creación de tablas.
        Statement stmt = con.createStatement();

        String muebleTable = "CREATE TABLE IF NOT EXISTS `mueble` (\n" +
            "`modelo` INT(10) NOT NULL,\n" +
            "`nombre` VARCHAR(100) NOT NULL,\n" +
            "`precio` INT(10) NOT NULL,\n" +
            "`paquetes` INT(5) NOT NULL,\n" +
            "PRIMARY KEY (`modelo`)\n" + 
            ");";
        
        stmt.executeUpdate(muebleTable);
        
        String tamañoTable = "CREATE TABLE IF NOT EXISTS `tamano` (\n" +
        "`id` INT(10) AUTO_INCREMENT,\n" +
        "`ancho` INT(10),\n" +
        "`fondo` INT(10),\n" +
        "`altura` INT(10),\n" +
        "`peso_balda` INT(10),\n" +
        "`mueble` INT(10) NOT NULL,\n" +
        "PRIMARY KEY (`id`) \n" +
        ");";
        
        stmt.executeUpdate(tamañoTable);
        
        String materialesTable = "CREATE TABLE IF NOT EXISTS `materiales` (\n" +
        "`id` INT(10) AUTO_INCREMENT,\n" +
        "`principal` VARCHAR(100),\n" +
        "`secundario` VARCHAR(100),\n" +
        "`mueble` INT(10) NOT NULL,\n" +
        "PRIMARY KEY (`id`)\n" +
        ");";
        
        stmt.executeUpdate(materialesTable);
 
        stmt.close();
    }
     
     private static void insertTables() throws SQLException {  // insertamos valores a la tabla Album.
        Statement stmt = con.createStatement();

        String muebleTable = "INSERT IGNORE INTO `mueble` VALUES \n" +
        "('00278578', 'HYLLIS', '10', '1'),\n" +
        "('60333850', 'BROR', '99', '1'),\n" +
        "('30409295', 'KOLBJÖRN', '69', '1');";
        
        stmt.executeUpdate(muebleTable);
        
        String tamañoTable = "INSERT IGNORE INTO `tamano` VALUES \n" +
        "(0, '60', '27', '140', '25', '00278578'),\n" +
        "(0, '85', '55', '88', '50', '60333850'),\n" +
        "(0, '80', '35', '81', '55', '30409295');";
        
        stmt.executeUpdate(tamañoTable);
        
        String materialesTable = "INSERT IGNORE INTO `materiales` VALUES \n" +
        "(0, 'Acero galvanizado', 'Plástico amídico', '00278578'),\n" +
        "(0, 'Acero', 'Contrachapado de pino', '60333850'),\n" +
        "(0, 'Acero galvanizado', 'Revestimiento en polvo de poliéster', '30409295');";
        
        stmt.executeUpdate(materialesTable);
               
        stmt.close();
    }
}
