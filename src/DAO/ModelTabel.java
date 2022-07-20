/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ModelTabel {

    public void SetTabel(JTable tabel, String[][] data, String[] namaKolom, int jmlKolom, int[] lebar) {
        tabel.setModel(new DefaultTableModel(data, namaKolom));
        for (int i = 0; i < jmlKolom; i++) {
            tabel.getColumnModel().getColumn(1).setPreferredWidth(lebar[1]);
        }
    }
}
