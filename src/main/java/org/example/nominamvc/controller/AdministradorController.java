package org.example.nominamvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.nominamvc.database.NominaDBCRUD;
import org.example.nominamvc.model.Administrador;
import org.example.nominamvc.model.Empleado;

import java.util.regex.*;  

import java.util.ArrayList;
import java.util.List;
import org.example.nominamvc.database.Connector;

public class AdministradorController {
    NominaDBCRUD nominaDBCRUD;
    ArrayList<Administrador> empleados = new ArrayList<>();
    
    public AdministradorController(){
        
    }
    
    public boolean validateAdministrador(Administrador administrador) throws Exception{
        if (!Pattern.matches("[a-z]{25}", administrador.getNombre())){
            throw (new Exception ("Cadena <nombre> invaldia"));
        }
        else if (!Pattern.matches("[a-z]{25}", administrador.getApellido())){
            throw (new Exception ("Cadena <apellido> invaldia"));
        }else if (!Pattern.matches("^[a-zA-Z0-9_.+-]+@(yahoo|gmail|outlook)\\.[a-zA-Z0-9-.]+$", administrador.getApellido())){
            throw (new Exception ("Cadena <correo> invaldia"));
        }
        else if (!Pattern.matches("[1-9]{9}", String.valueOf(administrador.getSalario()))){
            throw (new Exception ("cadena <salario> no valida"));
        }
        return true;
    }
    
    public static boolean validateAdministradorFields(Administrador administrador) throws Exception{
        if (!Pattern.matches("[a-z]{25}", administrador.getNombre())){
            throw (new Exception ("Cadena <nombre> invaldia"));
        }
        else if (!Pattern.matches("[a-z]{25}", administrador.getApellido())){
            throw (new Exception ("Cadena <apellido> invaldia"));
        }else if (!Pattern.matches("^[a-zA-Z0-9_.+-]+@(yahoo|gmail|outlook)\\.[a-zA-Z0-9-.]+$", administrador.getApellido())){
            throw (new Exception ("Cadena <correo> invaldia"));
        }
        else if (!Pattern.matches("[1-9]{9}", String.valueOf(administrador.getSalario()))){
            throw (new Exception ("cadena <salario> no valida"));
        }
        return true;
    }

    public void recuperaDatosAdministradores() {
        Administrador administrador;
        ResultSet table;

        nominaDBCRUD = new NominaDBCRUD();
        table = nominaDBCRUD.consultTable("administradores");
        empleados.clear();
        try{
            while (table.next()){
                administrador = new Administrador(
                        table.getInt("id"),
                        table.getString("nombre"),
                        table.getString("apellido"),
                        "",
                        table.getDouble("salario")  
                );
                empleados.add(administrador);
            }

        } catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    public ArrayList<Administrador> getEmpleados() {
        return empleados;
    }

    public String toStringListaEmpleados(){
        String result = "";
        for (Empleado administrador: this.empleados) {
            result += administrador.toString() + "\n";
        }
        return result;
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
        db.close();
        return id;
    }
     
}
