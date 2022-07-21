/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class siswa {

    private koneksi db;
    private Statement st;
    private String query;
    private ResultSet rs;

    public boolean cek_siswa(String nim_dt) {
        rs = null;
        db = new koneksi();
        db.KoneksiDatabase();
        boolean data = false;
        try {
            st = db.con.createStatement();
            query = "SELECT nim FROM t_mahasiswa WHERE nim='" + nim_dt + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, " NIM " + nim_dt + " TIDAK ADA DALAM DATABASE!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    public String[][] Tampil_Semua_Siswa() {
        rs = null;
        String[][] data = null;
        db = new koneksi();
        db.KoneksiDatabase();
        int jumlah_baris = 0;

        try {
            st = db.con.createStatement();
            query = "select count(nim) as jum from t_mahasiswa";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "select * from t_mahasiswa";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][5];

            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("nim");
                data[r][1] = rs.getString("nama");
                data[r][2] = rs.getString("kelas");
                data[r][3] = rs.getString("spp_id");
                r++;
            }
            st.close();
            db.con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }
    
    public void insert(String nim_dt, String nama_dt, String kelas_dt, String spp_id_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
            st = db.con.createStatement();
            query = "INSERT INTO t_mahasiswa(nim, nama, kelas, spp_id) VALUES ('" + nim_dt + "','" + nama_dt + "','" + kelas_dt + "','" + spp_id_dt +"')";
            st.executeUpdate(query);
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void ubah(String nim_dt, String nama_dt, String kelas_dt, String spp_id_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
            query = "UPDATE t_mahasiswa SET nama=?, nama=?, kelas=?, spp_id=? WHERE nim=?;";
            PreparedStatement update = db.con.prepareStatement(query);
            update.setString(1, nim_dt);
            update.setString(1, nim_dt);
            update.setString(2, nama_dt);
            update.setString(3, kelas_dt);
            update.setString(4, spp_id_dt);
            
            update.executeUpdate();
            update.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Berhasil Merubah Data : " + nim_dt);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapus(String nim_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
            query = "DELETE FROM t_mahasiswa WHERE nim=?";
            PreparedStatement hapus_data = db.con.prepareStatement(query);
            hapus_data.setString(1, nim_dt);
            hapus_data.executeUpdate();
            hapus_data.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data : " + nim_dt);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public String[][] cari_siswa(String kata_kunci) {
        rs = null;
        String[][] data = null;
        db = new koneksi();
        db.KoneksiDatabase();
        int jumlah_baris = 0;
        try {
            st = db.con.createStatement();
            query = "SELECT COUNT(nim) AS jum FROM t_mahasiswa";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "SELECT * FROM t_mahasiswa WHERE nim='" + kata_kunci + "'";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][5];

            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("nim");
                data[r][1] = rs.getString("nama");
                data[r][2] = rs.getString("kelas");
                data[r][3] = rs.getString("spp_id");
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
