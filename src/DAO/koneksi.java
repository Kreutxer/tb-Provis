/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class koneksi {
        public Connection con;

    public boolean KoneksiDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3080/db_spp", "root", ""); //gantiport
            con = conn;
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e);
            return false;
        }
    }
}
