/*
 * Clase donde se ejecutan las diferentes Queries de la BBDD
 */
package database;

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
}
