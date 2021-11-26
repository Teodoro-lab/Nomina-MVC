package org.example.nominamvc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.nominamvc.model.Administrador;

public class NominaDBCRUD {
    Connector db;
    String query;

    public NominaDBCRUD(){
    }

    public ResultSet consultTable(String tableName){
        ResultSet resultSet = null;
        try{
            db = Connector.getConnector();
            db.connect();
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            query =  "SELECT * FROM " + tableName;
            resultSet = stmt.executeQuery(query);
        } catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
        return resultSet;
    }
    
    
    public int insertAdministrador(Administrador administrador) {
        String SQL = "INSERT INTO administradores(id, nombre, apellido, salario, horas_laboradas, rango) "
                + "VALUES(NULL,?,?,?,?,?)";

        int id = 0;
        Connector db;
        db = Connector.getConnector();
        db.connect();
        try (   
                Connection con = db.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            //pstmt.setInt(1, administrador.getId());
            pstmt.setString(1, administrador.getNombre());
            pstmt.setString(2, administrador.getApellido());
            pstmt.setDouble(3, administrador.getSalario());
            pstmt.setDouble(4, administrador.getNumHorasTrabajadas());
            pstmt.setInt(5, administrador.getLevel());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //db.close();
        return id;
    }
    
    //UPDATE `administradores` SET `salario` = '132' WHERE `administradores`.`id` = 2;
    public int updateAdministrador(Administrador administrador) {
        String SQL = "UPDATE `administradores` "
                + "SET `nombre` = ?,"
                + "`apellido` = ?,"
                + "`salario` = ?,"
                + "`horas_laboradas` = ?,"
                + "`rango` = ?"
                + " WHERE `administradores`.`id` = ?;";

        int id = 0;
        Connector db;
        db = Connector.getConnector();
        db.connect();
        try (   
                Connection con = db.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            
            pstmt.setString(1, administrador.getNombre());
            pstmt.setString(2, administrador.getApellido());
            pstmt.setDouble(3, administrador.getSalario());
            pstmt.setDouble(4, administrador.getNumHorasTrabajadas());
            pstmt.setInt(5, administrador.getLevel());
            pstmt.setInt(6, administrador.getId());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        db.close();
        return id;
    }
     
     //
     public int deleteAdministrador(int id) {
        String SQL = "DELETE FROM `administradores` WHERE `administradores`.`id` = " + id;
        Connector db;
        db = Connector.getConnector();
        db.connect();
        try (   
                Connection con = db.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
     
     
}
