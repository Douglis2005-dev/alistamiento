package com.mycompany.alistamiento;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.FlowLayout;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

public class interfazRestablecerContraseña {
    
    public static void restablecerContraseñas() {
        JFrame frame = new JFrame("Restablecer contraseña");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(panel);
        componentesRestablecer(panel, frame);
        frame.setVisible(true);
    }

    public static void componentesRestablecer(JPanel panel, JFrame frame) {
        JLabel Nombre = new JLabel("Usuario:");
        Nombre.setBounds(10, 20, 110, 25);
        panel.add(Nombre);

        JTextField nombreIT = new JTextField(20);
        nombreIT.setBounds(100, 20, 150, 25);
        panel.add(nombreIT);

        JButton Enviar = new JButton("Enviar");
        Enviar.setBounds(100, 80, 100, 25);
        panel.add(Enviar);

        JButton Cancelar = new JButton("Cerrar");
        Cancelar.setBounds(210, 80, 100, 25);
        panel.add(Cancelar);

        Enviar.addActionListener(e -> {
            String Usuario = nombreIT.getText();
            try {
                restablecerContraseña(Usuario);
            } catch (ExecutionException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        });
        Cancelar.addActionListener(e -> {
            frame.dispose();
        });
    }
    
    public static void restablecerContraseña(String usuario) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference docRef = db.collection("rutas");
        Query query = docRef.whereEqualTo("Programa", usuario);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documentos = querySnapshot.get().getDocuments();
        if (!documentos.isEmpty()) {
            for (QueryDocumentSnapshot document : documentos) {
                String docId = document.getId();  // Obtener el ID del documento encontrado
                // 2️⃣ Actualizar el campo "estado" a "Activo"
                DocumentReference Ref = db.collection("usuarios").document(docId);
                Ref.update("contraseña", "");
                JOptionPane.showMessageDialog(null, "El campo ha sido actualizado: ID - " + docId + " -> " + document.getData());

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un usuario con ese nombre.");
        }
    }
}
