/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
            query = "INSERT INTO t_mahasiswa(nim, nama, kelas, spp_id) VALUES ('" + nim_dt + "','" + nama_dt + "','" + kelas_dt + "','" + spp_id_dt + "')";
            st.executeUpdate(query);
            db.con.close();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "NIM Sudah Ada");
            }
            if (e.getErrorCode() == 1264){
                JOptionPane.showMessageDialog(null, "Masukkan NIM dengan benar!");
            }
            if (e.getErrorCode() == 1452) {
                JOptionPane.showMessageDialog(null, "ID SPP tidak ada!");
            }
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubah(String nama_dt, String kelas_dt, String spp_id_dt, String nim_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
//            query = "UPDATE t_mahasiswa SET nim='"+nim_dt+"',nama='"+nama_dt+"',kelas='"+kelas_dt+"',spp_id='"+spp_id_dt+"'";
            //
            query = "UPDATE t_mahasiswa SET  nama=?, kelas=?, spp_id=? WHERE nim=?;";
            PreparedStatement update = db.con.prepareStatement(query);
            update.setString(1, nama_dt);
            update.setString(2, kelas_dt);
            update.setString(3, spp_id_dt);
            update.setString(4, nim_dt);
//            
            update.executeUpdate();
            update.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Berhasil Merubah Data");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                JOptionPane.showMessageDialog(null, "ID SPP tidak ada!");
            }
        }
    }
    //pusg
    
        public void ubah_nis(String nim_dt) {
        db = new koneksi();
        db.KoneksiDatabase();
        try {
//            query = "UPDATE t_mahasiswa SET nim='"+nim_dt+"',nama='"+nama_dt+"',kelas='"+kelas_dt+"',spp_id='"+spp_id_dt+"'";
            //
            query = "UPDATE t_mahasiswa SET  nama=?, kelas=?, spp_id=? WHERE nim=?;";
            PreparedStatement update = db.con.prepareStatement(query);
            update.setString(1, nim_dt);
//            
            update.executeUpdate();
            update.close();
            db.con.close();
            JOptionPane.showMessageDialog(null, "Berhasil Merubah Data");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "NIM Sudah Ada");
            }
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
            query = "SELECT * FROM t_mahasiswa WHERE nim='" + kata_kunci + "' OR nama='" + kata_kunci + "' OR kelas='" + kata_kunci + "'";
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

    public void filterhuruf(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "Isi dengan nim bukan string");
        }
    }

    public void ubah(String nim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
