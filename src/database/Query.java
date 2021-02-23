/*
 * Clase donde se ejecutan las diferentes Queries de la BBDD
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author angel
 */
public class Query {
    
    CreateBBDD dataBase = new CreateBBDD();
    
    public void tableFitment (JTable table){ // selecciono todo de la tabla para poner en la UI
        String query = "SELECT * FROM mueble;";
        
        try{
            Statement stmt = dataBase.con.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                DefaultTableModel model = (DefaultTableModel) table.getModel(); // añade filas a la tabla automaticamente
                model.addRow(new Object[]{rs.getInt("modelo"), rs.getString("nombre"), rs.getInt("precio"), rs.getInt("paquetes")});                
            }
            
            rs.close();
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }        
    }
    
    public void tableSize (JTable table){ // selecciono todo de la tabla para poner en la UI
        String query = "SELECT * FROM tamano;";
        
        try{
            Statement stmt = dataBase.con.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                DefaultTableModel model = (DefaultTableModel) table.getModel(); // añade filas a la tabla automaticamente
                model.addRow(new Object[]{rs.getInt("id"), rs.getInt("ancho"), rs.getInt("fondo"), rs.getInt("altura"), rs.getInt("peso_balda"), rs.getInt("mueble")});                
            }
            
            rs.close();
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }        
    }
    
    public void tableMaterial (JTable table){ // selecciono todo de la tabla para poner en la UI
        String query = "SELECT * FROM material;";
        
        try{
            Statement stmt = dataBase.con.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                DefaultTableModel model = (DefaultTableModel) table.getModel(); // añade filas a la tabla automaticamente
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("principal"), rs.getString("secundario"), rs.getInt("mueble")});                
            }
            
            rs.close();
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }        
    }

    public String findFitment(int column, String value) {
        String text = "";
        String query = "";
        switch(column){ // segun lo elegido en el comboBox entra en un lado o en otro
            case 0: query = "SELECT * FROM mueble WHERE modelo = ?;"; break;
            case 1: query = "SELECT * FROM mueble WHERE nombre = ?;"; break;
            case 2: query = "SELECT * FROM mueble WHERE precio = ?;"; break;
            case 3: query = "SELECT * FROM mueble WHERE paquetes = ?;"; break;
        }

        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setString(1, value);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) { // lo pongo en un Jtext
                text += String.format("Modelo: %s \nNombre: %s\n Precio: %s\n Paquetes: %s \n ----------- \n", rs.getInt("modelo"), rs.getString("nombre"), rs.getString("precio"), rs.getString("paquetes"));
            }
            
            rs.close();
            stmt.close();          
        }
        catch (Exception ex){
            System.out.println(ex);
        } 
        
        return text;
    }
    
    public String findSize(int column, String value) {
        String text = "";
        String query = "";
        switch(column){ // segun lo elegido en el comboBox entra en un lado o en otro
            case 0: query = "SELECT * FROM tamano WHERE id = ?;"; break;
            case 1: query = "SELECT * FROM tamano WHERE ancho = ?;"; break;
            case 2: query = "SELECT * FROM tamano WHERE fondo = ?;"; break;
            case 3: query = "SELECT * FROM tamano WHERE altura = ?;"; break;
            case 4: query = "SELECT * FROM tamano WHERE peso_balda = ?;"; break;
            case 5: query = "SELECT * FROM tamano WHERE mueble = ?;"; break;
        }

        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setString(1, value);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) { // lo pongo en un Jtext
                text += String.format(" Id: %s \n Ancho: %s\n Fondo: %s\n Altura: %s\n Peso Balda: %s\n Mueble: %s \n ----------- \n", rs.getInt("id"), rs.getInt("ancho"), rs.getInt("fondo"), rs.getInt("altura"), rs.getInt("peso_balda"), rs.getInt("mueble"));
            }
            
            rs.close();
            stmt.close();          
        }
        catch (Exception ex){
            System.out.println(ex);
        } 
        
        return text;
    }
    
    public String findMaterial(int column, String value) {
        String text = "";
        String query = "";
        switch(column){ // segun lo elegido en el comboBox entra en un lado o en otro
            case 0: query = "SELECT * FROM material WHERE id = ?;"; break;
            case 1: query = "SELECT * FROM material WHERE principal = ?;"; break;
            case 2: query = "SELECT * FROM material WHERE secundario = ?;"; break;
            case 3: query = "SELECT * FROM material WHERE mueble = ?;"; break;
        }

        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setString(1, value);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) { // lo pongo en un Jtext
                text += String.format("Id: %s \n Principal: %s\n Secundario: %s\n Mueble: %s \n ----------- \n", rs.getInt("id"), rs.getString("principal"), rs.getString("secundario"), rs.getString("mueble"));
            }
            
            rs.close();
            stmt.close();          
        }
        catch (Exception ex){
            System.out.println(ex);
        } 
        
        return text;
    }

    public void addFitment(int modelo, String nombre, int precio, int paquetes) {
        String query = "INSERT INTO mueble(modelo,nombre,precio,paquetes) VALUES (?, ?, ?, ?);";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, modelo);
            stmt.setString(2, nombre);
            stmt.setInt(3, precio);
            stmt.setInt(4, paquetes);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public void addSize(int ancho, int fondo, int altura, int pesoBalda, int mueble) {
        String query = "INSERT INTO tamano(id,ancho,fondo,altura,peso_balda,mueble) VALUES (?, ?, ?, ?, ?, ?);";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, 0);
            stmt.setInt(2, ancho);
            stmt.setInt(3, fondo);
            stmt.setInt(4, altura);
            stmt.setInt(5, pesoBalda);
            stmt.setInt(6, mueble);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public void addMaterial(String principal, String secundario, int mueble) {
        String query = "INSERT INTO material(id,principal,secundario,mueble) VALUES (?, ?, ?, ?);";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, 0);
            stmt.setString(2, principal);
            stmt.setString(3, secundario);
            stmt.setInt(4, mueble);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public void deleteFitment(int model) {
        String query = "DELETE FROM mueble WHERE modelo = ?";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, model);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public void deleteSize(int id) {
        String query = "DELETE FROM tamano WHERE id = ?";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public void deleteMaterial(int id) {
        String query = "DELETE FROM material WHERE id = ?";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
}
