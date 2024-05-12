package View.Mahasiswa;


import Controller.ControllerMahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.Mahasiswa.ModelMahasiswa;

public class ViewDataMahasiswa extends JFrame {
    Integer baris;
    
    ControllerMahasiswa controller;

    
    List<ModelMahasiswa> daftarMahasiswa = new ArrayList<>();

    
    JLabel header = new JLabel("Selamat Datang!");
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolHapus = new JButton("Hapus Mahasiswa");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Nama", "NIM"};

    public ViewDataMahasiswa() {
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(552, 540);
        setLocationRelativeTo(null);
        setLayout(null);

        add(header);
        add(scrollPane);
        add(tombolTambah);
        add(tombolEdit);
        add(tombolHapus);

        header.setBounds(20, 8, 440, 24);
        scrollPane.setBounds(20, 36, 512, 320);
        tombolTambah.setBounds(20, 370, 512, 40);
        tombolEdit.setBounds(20, 414, 512, 40);
        tombolHapus.setBounds(20, 456, 512, 40);

        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        
        controller = new ControllerMahasiswa(this);
        controller.showAllMahasiswa();

     
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                baris = table.getSelectedRow();
            }
        });

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputDataMahasiswa();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (baris != null) {
                    
                    ModelMahasiswa pilih = new ModelMahasiswa();
                    
                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String nim = table.getValueAt(baris, 2).toString();
                    
                    pilih.setId(id);
                    pilih.setNama(nama);
                    pilih.setNim(nim);
         
                    dispose();
                    new EditDataMahasiswa(pilih);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteMahasiswa(baris);
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });
    }

    public JTable getTable() {
        return table;
    }
}
