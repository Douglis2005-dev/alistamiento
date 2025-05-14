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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class interfazCrearUsuario {
    public static void CrearUsuario() {
        JFrame frame = new JFrame("Crear Usuario");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(panel);
        componentesCrear(panel, frame);
        frame.setVisible(true);
    }
    
    public static void componentesCrear(JPanel panel, JFrame frame) {
        String[] Roles = {"Admin", "Jefe", "Analista", "Tecnico", "Junior"};
        panel.setLayout(null);

        JLabel Nombre = new JLabel("Usuario:");
        Nombre.setBounds(10, 20, 110, 25);
        panel.add(Nombre);

        JTextField nombreIT = new JTextField(20);
        nombreIT.setBounds(100, 20, 150, 25);
        panel.add(nombreIT);

        JLabel cargo = new JLabel("Rol:");
        cargo.setBounds(10, 50, 100, 25);
        panel.add(cargo);

        JComboBox<String> roles = new JComboBox<>(Roles);
        roles.setBounds(100, 50, 150, 25);
        panel.add(roles);

        JButton Enviar = new JButton("Enviar");
        Enviar.setBounds(100, 80, 100, 25);
        panel.add(Enviar);

        JButton Cancelar = new JButton("Cerrar");
        Cancelar.setBounds(210, 80, 100, 25);
        panel.add(Cancelar);

        Enviar.addActionListener(e -> {
            int selectedIndex = roles.getSelectedIndex();
            String role = Roles[selectedIndex];
            String Usuario = nombreIT.getText();
            try {
                crearUsuario(Usuario, role);
            } catch (ExecutionException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
            }
        });

        Cancelar.addActionListener(e -> {
            frame.dispose();
        });
    }
    public static void crearUsuario(String usuario, String rol) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference user = db.collection("usuarios");
        Query usuarios = user.whereEqualTo("usuario", usuario);
        ApiFuture<QuerySnapshot> future = usuarios.get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El usuario '" + usuario + "' ya existe.");
                return; // Salimos de la función para no crear duplicados
            }

            // Si el usuario no existe, lo creamos
            Map<String, Object> datos = new HashMap<>();
            datos.put("usuario", usuario);
            datos.put("contraseña", ""); // Se puede cambiar por una contraseña predeterminada
            datos.put("rol", rol);

            // Usamos `set` en lugar de `add` para que cada usuario tenga su ID único basado en su nombre
            ApiFuture<WriteResult> result = user.document(usuario).set(datos);

            JOptionPane.showMessageDialog(null, "Usuario '" + usuario + "' creado correctamente.");
            System.out.println("Usuario creado en: " + result.get().getUpdateTime());

        } catch (HeadlessException | InterruptedException | ExecutionException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
