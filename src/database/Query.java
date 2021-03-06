/*
 * Clase donde se ejecutan las diferentes Queries de la BBDD
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel Esquinas
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
            JOptionPane.showMessageDialog(null, "No se ha podido mostrar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "No se ha podido mostrar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "No se ha podido mostrar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la busqueda.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la busqueda.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la busqueda.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
        
        return text;
    }

    public void addFitment(int modelo, String nombre, int precio, int paquetes) { // añado tuplas en las tablas
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
            JOptionPane.showMessageDialog(null, "No se ha podido añadir los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addSize(int ancho, int fondo, int altura, int pesoBalda, int mueble) { // añado tuplas en las tablas
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
            JOptionPane.showMessageDialog(null, "No se ha podido añadir los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addMaterial(String principal, String secundario, int mueble) { // añado tuplas en las tablas
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
            JOptionPane.showMessageDialog(null, "No se ha podido añadir los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteFitment(int model) { // elimino una tupla de la tabla
        String query = "DELETE FROM mueble WHERE modelo = ?";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, model);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSize(int id) { // elimino una tupla de la tabla
        String query = "DELETE FROM tamano WHERE id = ?";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteMaterial(int id) { // elimino una tupla de la tabla
        String query = "DELETE FROM material WHERE id = ?";
        
        try{
            PreparedStatement stmt = dataBase.con.prepareStatement(query);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateFitment(int col, String edit, int edit2, int model) {
        String query = "";
        
        try{
            Statement stmt = dataBase.con.createStatement();
            switch(col){ // segun el numero de la columna hace una cosa u otra
                case 1: query = String.format("UPDATE mueble SET nombre = '%s' WHERE modelo = %s", edit, model); break;
                case 2: query = String.format("UPDATE mueble SET precio = '%s' WHERE modelo = %s", edit2, model); break;
                case 3: query = String.format("UPDATE mueble SET paquetes = %s WHERE modelo = %s", edit2, model); break;
            }        
            
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateSize(int col, int edit2, int id) {
       String query = "";
        
        try{
            Statement stmt = dataBase.con.createStatement();
            switch(col){ // segun el numero de la columna hace una cosa u otra
                case 1: query = String.format("UPDATE tamano SET ancho = '%s' WHERE id = %s", edit2, id); break;
                case 2: query = String.format("UPDATE tamano SET fondo = '%s' WHERE id = %s", edit2, id); break;
                case 3: query = String.format("UPDATE tamano SET altura = %s WHERE id = %s", edit2, id); break;
                case 4: query = String.format("UPDATE tamano SET peso_balda = '%s' WHERE id = %s", edit2, id); break;
                case 5: query = String.format("UPDATE tamano SET mueble = %s WHERE id = %s", edit2, id); break;
            }        
            
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateMaterial(int col, String edit, int edit2, int id) {
        String query = "";
        
        try{
            Statement stmt = dataBase.con.createStatement();
            switch(col){ // segun el numero de la columna hace una cosa u otra
                case 1: query = String.format("UPDATE material SET principal = '%s' WHERE id = %s", edit, id); break;
                case 2: query = String.format("UPDATE material SET secundario = '%s' WHERE id = %s", edit, id); break;
                case 3: query = String.format("UPDATE material SET mueble = %s WHERE id = %s", edit2, id); break;
            }        
            
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar los datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}