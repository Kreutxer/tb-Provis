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
public class SPP {

    private koneksi db;
    private Statement st;
    private String query;
    private ResultSet rs;

    public String[][] Tampil_spp() {
        rs = null;
        String[][] data = null;
        db = new koneksi();
        db.KoneksiDatabase();
        int jumlah_baris = 0;

        try {
            st = db.con.createStatement();
            query = "select count(id_spp) as jum from spp";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlah_baris = rs.getInt("jum");
            }
            query = "select * from spp";
            rs = st.executeQuery(query);
            data = new String[jumlah_baris][5];

            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("id_spp");
                data[r][1] = rs.getString("tahun");
                data[r][2] = rs.getString("nominal");
                r++;
            }
            st.close();
            db.con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return data;
    }

    public void insertSPP(String id_spp_dt, String tahun_spp, String nominal_spp) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
            st = db.con.createStatement();
            query = "INSERT INTO spp(id_spp, tahun, nominal) VALUES ('" + id_spp_dt + "','" + tahun_spp + "','" + nominal_spp + "')";
            st.executeUpdate(query);
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
        public void ubahSPP(String spp_id_dt, String tahun_dt, String nominal_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
//            query = "UPDATE t_mahasiswa SET nim='"+nim_dt+"',nama='"+nama_dt+"',kelas='"+kelas_dt+"',spp_id='"+spp_id_dt+"'";
            //
            query = "UPDATE spp SET  tahun=?, nominal=? WHERE id_spp=?;";
            PreparedStatement update = db.con.prepareStatement(query);
            update.setString(1, tahun_dt);
            update.setString(2, nominal_dt);
            update.setString(3, spp_id_dt);
//            
            update.executeUpdate();
            update.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Berhasil Merubah Data : " + spp_id_dt);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        public void hapus(String spp_id_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
            query = "DELETE FROM spp WHERE id_spp=?";
            PreparedStatement hapus_data = db.con.prepareStatement(query);
            hapus_data.setString(1, spp_id_dt);
            hapus_data.executeUpdate();
            hapus_data.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data : " + spp_id_dt);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
