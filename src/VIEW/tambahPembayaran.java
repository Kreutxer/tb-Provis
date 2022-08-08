/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.Pembayaran;
import DAO.ModelTabel;
import DAO.koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class tambahPembayaran extends javax.swing.JFrame {

    private String bulan_bayar;
    public String pBulan;
    private String namaBulan;

    /**
     * Creates new form tambahPembayaran
     */
    public tambahPembayaran() {
        initComponents();
        tglskrg();
        tampil_combobox_nama();
    }

    public void tampil_combobox_nama() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3080/db_spp", "root", ""); // rendy
//            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_spp", "root", ""); // port bagas
            Statement smt = cn.createStatement();
            String sql = "SELECT id_spp FROM spp";
            ResultSet res = smt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[1];
                ob[0] = res.getString((1));
                jComboBox3.addItem((String) ob[0]);
            }
            res.close();
            smt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }

//
//    public void cek_bulan() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3080/db_spp", "root", "");
//            Statement smt = cn.createStatement();
//            String sql = "SELECT * FROM pembayaran WHERE bulan_bayar='" + bulan_bayar + "'";
//            ResultSet res = smt.executeQuery(sql);
//
//        } catch (Exception e) {
//
//        }
//    }
    public void cek_lunas(String namaBulan) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3080/db_spp", "root", ""); // rendy
//            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_spp", "root", ""); // port bagas

            Pembayaran p = new Pembayaran();
            String id_spp = (String) jComboBox3.getSelectedItem();
            String nim = isisNIM.getText();
            String tanggal = jTextField3.getText();
            String bulan = jComboBox2.getSelectedItem().toString();
            String tahun = jTextField4.getText();
            String status = "lunas";

            switch (bulan) {
                case "Januari":
                    this.namaBulan = "January";
                    try {
                        int countJanStatus = 0;
                        Statement stJanStatus = cn.createStatement();
                        ResultSet checkJanStatus = stJanStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Januari' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkJanStatus.next()) {
                            countJanStatus = countJanStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countJanStatus == 0) {
                            int payJan = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payJan == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Januari");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payJan == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payJan == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Februari":
                    this.namaBulan = "February";
                    try {
                        int countFebStatus = 0;
                        Statement stFebStatus = cn.createStatement();
                        ResultSet checkFebStatus = stFebStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Februari' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkFebStatus.next()) {
                            countFebStatus = countFebStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countFebStatus == 0) {
                            int payFeb = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payFeb == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Februari");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payFeb == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payFeb == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Maret":
                    this.namaBulan = "March";
                    try {
                        int countMarStatus = 0;
                        Statement stMarStatus = cn.createStatement();
                        ResultSet checkMarStatus = stMarStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Maret' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkMarStatus.next()) {
                            countMarStatus = countMarStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countMarStatus == 0) {
                            int payMar = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payMar == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Maret");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payMar == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payMar == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "April":
                    this.namaBulan = "April";
                    try {
                        int countAprStatus = 0;
                        Statement stAprStatus = cn.createStatement();
                        ResultSet checkAprStatus = stAprStatus.executeQuery("select * from pembayaran where bulan_bayar = 'April' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkAprStatus.next()) {
                            countAprStatus = countAprStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countAprStatus == 0) {
                            int payApr = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payApr == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "April");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payApr == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payApr == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Mei":
                    this.namaBulan = "Mei";
                    try {
                        int countMeiStatus = 0;
                        Statement stMeiStatus = cn.createStatement();
                        ResultSet checkMeiStatus = stMeiStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Mei' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkMeiStatus.next()) {
                            countMeiStatus = countMeiStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countMeiStatus == 0) {
                            int payMei = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payMei == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Mei");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payMei == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payMei == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Juni":
                    this.namaBulan = "Mei";
                    try {
                        int countJunStatus = 0;
                        Statement stJunStatus = cn.createStatement();
                        ResultSet checkJunStatus = stJunStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Juni' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkJunStatus.next()) {
                            countJunStatus = countJunStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countJunStatus == 0) {
                            int payJun = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payJun == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Juni");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payJun == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payJun == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Juli":
                    this.namaBulan = "Mei";
                    try {
                        int countJulStatus = 0;
                        Statement stJulStatus = cn.createStatement();
                        ResultSet checkJulStatus = stJulStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Juli' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkJulStatus.next()) {
                            countJulStatus = countJulStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countJulStatus == 0) {
                            int payJul = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payJul == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Juli");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payJul == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payJul == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Agustus":
                    this.namaBulan = "Mei";
                    try {
                        int countAguStatus = 0;
                        Statement stAguStatus = cn.createStatement();
                        ResultSet checkAguStatus = stAguStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Agustus' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkAguStatus.next()) {
                            countAguStatus = countAguStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countAguStatus == 0) {
                            int payAgu = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payAgu == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Agustus");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payAgu == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payAgu == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "September":
                    this.namaBulan = "September";
                    try {
                        int countSepStatus = 0;
                        Statement stSepStatus = cn.createStatement();
                        ResultSet checkSepStatus = stSepStatus.executeQuery("select * from pembayaran where bulan_bayar = 'September' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkSepStatus.next()) {
                            countSepStatus = countSepStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countSepStatus == 0) {
                            int paySep = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (paySep == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "September");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (paySep == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (paySep == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Oktober":
                    this.namaBulan = "Oktober";
                    try {
                        int countOktStatus = 0;
                        Statement stOktStatus = cn.createStatement();
                        ResultSet checkOktStatus = stOktStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Oktober' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkOktStatus.next()) {
                            countOktStatus = countOktStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countOktStatus == 0) {
                            int payOkt = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payOkt == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Oktober");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payOkt == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payOkt == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "November":
                    this.namaBulan = "November";
                    try {
                        int countNovStatus = 0;
                        Statement stNovStatus = cn.createStatement();
                        ResultSet checkNovStatus = stNovStatus.executeQuery("select * from pembayaran where bulan_bayar = 'November' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkNovStatus.next()) {
                            countNovStatus = countNovStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countNovStatus == 0) {
                            int payNov = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payNov == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "November");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payNov == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payNov == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
                case "Desember":
                    this.namaBulan = "Desember";
                    try {
                        int countDesStatus = 0;
                        Statement stDesStatus = cn.createStatement();
                        ResultSet checkDesStatus = stDesStatus.executeQuery("select * from pembayaran where bulan_bayar = 'Desember' AND nim = '" + nim + "' AND spp_id = '" + id_spp + "'");
                        PreparedStatement pStatement = null;
                        while (checkDesStatus.next()) {
                            countDesStatus = countDesStatus + 1;
                            JOptionPane.showMessageDialog(null, "SPP SUDAH DIBAYAR");
                        }

                        if (countDesStatus == 0) {
                            int payDes = JOptionPane.showOptionDialog(new JFrame(), "Apakah benar anda akan melakukan pembayaran SPP pada bulan " + bulan + " dengan nim " + nim + "?", "Notification",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (payDes == JOptionPane.YES_OPTION) {
                                Class.forName("com.mysql.jdbc.Driver");
                                // "jdbc:mysql://localhost:3080/db_spp", "root", ""; // rendy
//                            urlValue = "jdbc:mysql://localhost:3307/db_spp", "root", "";

                                String sql = "INSERT INTO pembayaran (`spp_id`, `nim`, `tanggal_bayar`, `bulan_bayar`, `tahun_bayar`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
                                pStatement = cn.prepareStatement(sql);

                                pStatement.setString(1, id_spp);
                                pStatement.setString(2, nim);
                                pStatement.setString(3, tanggal);
                                pStatement.setString(4, "Desember");
                                pStatement.setString(5, tahun);
                                pStatement.setString(6, "1");

                                int intBaris = pStatement.executeUpdate();
                                if (intBaris > 0) {
                                    JOptionPane.showMessageDialog(null, "Penambahan data berhasil");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Penambahan data gagal");
                                }
                                pStatement.close();
                            } else if (payDes == JOptionPane.NO_OPTION) {
                                this.toBack();
                                tambahPembayaran tP = new tambahPembayaran();
                                tP.setVisible(true);
                            } else if (payDes == JOptionPane.CLOSED_OPTION) {
                                System.out.println("Window closed without selecting!");
                            }

                        }
                    } catch (SQLException checkJanStatus) {
                        System.out.println("Koneksi Gagal" + checkJanStatus.toString());
                    }
                    break;
            }

        } catch (Exception e) {
        }
    }

    public void tglskrg() {
        Date skrg = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd dd-MM-yyyy

        jTextField3.setText(format.format(skrg));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        isisNIM = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BAYAR SPP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ID SPP");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("NIM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tanggal");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Bulan");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tahun");

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih ID SPP--", " " }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Bulan--", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isisNIM)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jComboBox3, 0, 270, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(isisNIM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Pembayaran p = new Pembayaran();

        String id_spp = (String) jComboBox3.getSelectedItem();
        String nim = isisNIM.getText();
        String tanggal = jTextField3.getText();
        String bulan = jComboBox2.getSelectedItem().toString();
        String tahun = jTextField4.getText();
        String status = "lunas";

        cek_lunas(namaBulan);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.toBack();
        tampilPembayaran tS = new tampilPembayaran();
        setVisible(false);
        new mainFrm().toFront();
        new mainFrm().setState(java.awt.Frame.NORMAL);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tambahPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambahPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambahPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambahPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambahPembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField isisNIM;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
