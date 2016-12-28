/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyphongmach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author Quang Khanh
 */
public class ConnectToDatabase {
    private String url = "jdbc:mysql://localhost/phongkhamuit?verifyServerCertificate=false&useSSL=false";
    private String username = "root";
    private String password = "admin";
    private RowSetFactory aFactory;
    private CachedRowSet crs;
    public ConnectToDatabase() {
        try {
            aFactory = RowSetProvider.newFactory();
            crs = aFactory.createCachedRowSet();
            crs.setUrl(url);
            crs.setUsername(username);
            crs.setPassword(password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public CachedRowSet getCRS(String query) {
        try {
            crs.setCommand(query);
            crs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crs;
    }
    public void update(String query){
        try {
            crs.setCommand(query);
            crs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int getPS(String sql) {
        int result = 0;
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = conn.prepareStatement(sql);
            result = ps.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
