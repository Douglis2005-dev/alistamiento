package com.mycompany.alistamiento;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class interfazEliminarUsuario {
    public static void EliminarUsuario() {
        JFrame frame = new JFrame("Eliminar Usuario");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(panel);
        componentesEliminar(panel, frame);
        frame.setVisible(true);
    }

    public static void componentesEliminar(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel Nombre = new JLabel("Usuario:");
        Nombre.setBounds(10, 20, 110, 25);
        panel.add(Nombre);

        JTextField nombreIT = new JTextField(20);
        nombreIT.setBounds(100, 20, 150, 25);
        panel.add(nombreIT);

        JButton Eliminar = new JButton("Enviar");
        Eliminar.setBounds(100, 80, 100, 25);
        panel.add(Eliminar);

        JButton Cancelar = new JButton("Cerrar");
        Cancelar.setBounds(210, 80, 100, 25);
        panel.add(Cancelar);

        Eliminar.addActionListener(e -> {
            String Usuario = nombreIT.getText();
            try {
                eliminaUsuario(Usuario);
            } catch (ExecutionException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        });
        Cancelar.addActionListener(e -> {
            frame.dispose();
        });
    }
    
    public static void eliminaUsuario(String usuario) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference user = db.collection("usuarios");
        Query Programas = user.whereEqualTo("usuario", usuario);
        ApiFuture<QuerySnapshot> future = Programas.get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (documents.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El usuario '" + usuario + "' no existe.");
                return; // Salimos de la función para no crear duplicados
            }
            ApiFuture<WriteResult> result = user.document(usuario).delete();
            JOptionPane.showMessageDialog(null, "Usuario '" + usuario + "' eliminado correctamente.\n" + "Usuario elimindado en: " + result.get().getUpdateTime());
        } catch (HeadlessException | InterruptedException | ExecutionException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


}
