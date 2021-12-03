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
}
