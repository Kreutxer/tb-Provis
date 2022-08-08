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

    public String[][] cariBayar(String kata_kunci) {
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

    public void insert(String id_bayar_dt, String spp_id_dt, String nim_dt, String tanggal_bayar_dt, String bulan_bayar_dt, String tahun_bayar_dt, String status_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
            st = db.con.createStatement();
            query = "INSERT INTO pembayaran (id_bayar, spp_id, nim, tanggal_bayar, bulan_bayar, tahun_bayar, status) VALUES (NULL, '" + spp_id_dt + "', '" + nim_dt + "', '" + tanggal_bayar_dt + "', '" + bulan_bayar_dt + "', '" + tahun_bayar_dt + "', '" + status_dt + "');";
            st.executeUpdate(query);
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                JOptionPane.showMessageDialog(null, "Masukkan Data Dengan Benar!");
            }
        }
    }

    //INSERT INTO pembayaran (id_bayar, spp_id, nim, tanggal_bayar, bulan_bayar, tahun_bayar, status) VALUES (NULL, '5', '10120067', '2022-07-14', 'August', '2022', 'lunas');

}
