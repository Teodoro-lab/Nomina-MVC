package org.example.nominamvc.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.nominamvc.database.NominaDBCRUD;
import org.example.nominamvc.model.Administrador;
import org.example.nominamvc.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class AdministradorController {
    NominaDBCRUD nominaDBCRUD;
    ArrayList<Administrador> empleados = new ArrayList<>();

    public AdministradorController(){
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
}
