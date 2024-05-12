/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Mahasiswa.ModelMahasiswa;
import Model.Mahasiswa.ModelTableMahasiswa;
import View.Mahasiswa.EditDataMahasiswa;
import View.Mahasiswa.ViewDataMahasiswa;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Mahasiswa.InterfaceDAOMahasiswa;
import View.Mahasiswa.InputDataMahasiswa;

/**
 *
 * @author febri
 */
public class ControllerMahasiswa {

    InputDataMahasiswa inputmhs;
    EditDataMahasiswa editmhs;
    ViewDataMahasiswa viewmhs;

    InterfaceDAOMahasiswa daoMahasiswa;

    List<ModelMahasiswa> daftarMahasiswa;

    public ControllerMahasiswa(InputDataMahasiswa inputmhs) {
        this.inputmhs = inputmhs;
        this.daoMahasiswa = new Model.Mahasiswa.DAOMahasiswa();
    }

    public ControllerMahasiswa(EditDataMahasiswa editmhs) {
        this.editmhs = editmhs;
        this.daoMahasiswa = new Model.Mahasiswa.DAOMahasiswa();
    }

    public ControllerMahasiswa(ViewDataMahasiswa viewmhs) {
        this.viewmhs = viewmhs;
        this.daoMahasiswa = new Model.Mahasiswa.DAOMahasiswa();
    }

    public void showAllMahasiswa() {
        daftarMahasiswa = daoMahasiswa.getAll();

        ModelTableMahasiswa table = new ModelTableMahasiswa(daftarMahasiswa);
        
        viewmhs.getTable().setModel(table);
    }

    public void insertMahasiswa() {
        try {
            ModelMahasiswa inputMahasiswa = new ModelMahasiswa();

            String nama = inputmhs.getInputNama();
            String nim = inputmhs.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }

            inputMahasiswa.setNama(nama);
            inputMahasiswa.setNim(nim);

            daoMahasiswa.insert(inputMahasiswa);

            JOptionPane.showMessageDialog(null, "Data Mahasiswa telah ditambahkan");

            inputmhs.dispose();
            new ViewDataMahasiswa();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa editMahasiswa = new ModelMahasiswa();

            String nama = editmhs.getInputNama();
            String nim = editmhs.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }

            editMahasiswa.setId(id);
            editMahasiswa.setNama(nama);
            editMahasiswa.setNim(nim);

            daoMahasiswa.update(editMahasiswa);

            JOptionPane.showMessageDialog(null, "Data Mahasiswa Berhasil diedit");

            editmhs.dispose();
            new ViewDataMahasiswa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) viewmhs.getTable().getValueAt(baris, 0);
        String nama = viewmhs.getTable().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoMahasiswa.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllMahasiswa();
        }
    }

}
