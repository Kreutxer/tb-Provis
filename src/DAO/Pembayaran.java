/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Pembayaran {

    private koneksi db;
    private Statement st;
    private String query;
    private ResultSet rs;

    public String[][] Tampil_Pembayaran() {
        rs = null;
        String[][] data = null;
        db = new koneksi();
        db.KoneksiDatabase();
        int jumlah_baris = 0;

        try {
            st = db.con.createStatement();
            query = "select count(id_bayar) as jum from pembayaran";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "select * from pembayaran";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][5];

            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("id_bayar");
                data[r][1] = rs.getString("spp_id");
                data[r][2] = rs.getString("nim");
                data[r][3] = rs.getString("tanggal_bayar");
                data[r][4] = rs.getString("bulan_bayar");
                data[r][5] = rs.getString("tahun_bayar");
                data[r][6] = rs.getString("status");
                r++;
            }
            st.close();
            db.con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }

    public String[][] cariSiswa(String kata_kunci) {
        rs = null;
        String[][] data = null;
        db = new koneksi();
        db.KoneksiDatabase();
        int jumlah_baris = 0;
        try {
            st = db.con.createStatement();
            query = "SELECT COUNT(id_bayar) AS jum FROM pembayaran";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "SELECT * FROM pembayaran WHERE nim='" + kata_kunci + "'";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][7];

            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("id_bayar");
                data[r][1] = rs.getString("spp_id");
                data[r][2] = rs.getString("nim");
                data[r][3] = rs.getString("tanggal_bayar");
                data[r][4] = rs.getString("bulan_bayar");
                data[r][5] = rs.getString("tahun_bayar");
                data[r][6] = rs.getString("status");
                r++;
            }
            st.close();
            db.con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
}
