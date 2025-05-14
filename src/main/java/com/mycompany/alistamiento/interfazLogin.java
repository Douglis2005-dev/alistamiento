package com.mycompany.alistamiento;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import static com.mycompany.alistamiento.interfazAdministradores.interfazAdmin;
import static com.mycompany.alistamiento.interfazInstaladores.interfazInstalador;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

public class interfazLogin {

    public static void interfaz() {
        JFrame frame = new JFrame("Instalador de programas");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(panel);
        componenetesLogin(panel, frame);
        frame.setVisible(true);
    }

    public static void componenetesLogin(JPanel panel, JFrame frame) {
        JLabel Nombre = new JLabel("Usuario:");
        Nombre.setBounds(10, 20, 150, 25);
        panel.add(Nombre);
        panel.setLayout(null);

        JTextField nombreIT = new JTextField(20);
        nombreIT.setBounds(160, 20, 150, 25);
        panel.add(nombreIT);

        JLabel contraseña = new JLabel("Contraseña:");
        contraseña.setBounds(10, 50, 150, 25);
        panel.add(contraseña);

        JPasswordField Contraseña = new JPasswordField(20);
        Contraseña.setBounds(160, 50, 150, 25);
        Contraseña.setEchoChar('*');
        panel.add(Contraseña);

        JCheckBox mostrarContraseña = new JCheckBox("Mostrar");
        mostrarContraseña.setBounds(310, 50, 150, 25);
        mostrarContraseña.addActionListener(e -> {
            if (mostrarContraseña.isSelected()) {
                Contraseña.setEchoChar((char) 0);
                mostrarContraseña.setText("Ocultar");
            } else {
                Contraseña.setEchoChar('*');
                mostrarContraseña.setText("Mostrar");
            }
        });
        panel.add(mostrarContraseña);

        JLabel ContraseñaIT = new JLabel("");
        ContraseñaIT.setBounds(10, 50, 150, 25);
        panel.add(ContraseñaIT);

        JButton iniciarSesion = new JButton("iniciar sesion");
        iniciarSesion.setBounds(10, 80, 110, 25);
        panel.add(iniciarSesion);

        iniciarSesion.addActionListener(e -> {
            String Usuario = nombreIT.getText();
            String ContraseñaTec = Contraseña.getText();
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference ref = db.collection("usuarios");
            Query query = ref.whereEqualTo("usuario", Usuario);
            ApiFuture<QuerySnapshot> future = query.get();
            frame.dispose();
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    String pass = document.getString("contraseña");
                    if (pass == null || pass.isEmpty()) {
                        autenticarUsuario(Usuario, ContraseñaTec);
                        enviarDatos(Usuario);
                    } else {
                        if (Usuario == null || Usuario.isEmpty() || ContraseñaTec == null || ContraseñaTec.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Error, Los campos estan vacios");
                            interfaz();
                        } else {
                            autenticarUsuario(Usuario, ContraseñaTec);
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "❌ Error al obtener documentos: " + ex.getMessage());
            }
        });
    }

    public static void enviarDatos(String usuario) throws UnknownHostException {
        String nombreEquipo = InetAddress.getLocalHost().getHostName();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaAhora = formatoHora.format(new Date());
        LocalDateTime rutaAlist = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'-'MMM'-'yyyy ", new Locale("es", "ES"));
        String fecha = rutaAlist.format(formatter);
        Firestore db1 = FirestoreClient.getFirestore();
        CollectionReference Alist = db1.collection("alistamientos");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            InetAddress Mac = InetAddress.getLocalHost(); // Obtener IP local
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(Mac); // Obtener la interfaz de red

            if (networkInterface != null) {
                byte[] macBytes = networkInterface.getHardwareAddress(); // Obtener la MAC

                if (macBytes != null) {
                    StringBuilder macAddress = new StringBuilder();
                    for (byte b : macBytes) {
                        macAddress.append(String.format("%02X:", b)); // Convertir a formato XX:XX:XX:XX:XX:XX
                    }
                    String mac = macAddress.substring(0, macAddress.length() - 1);
                    String ipLocal = ip.getHostAddress();
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("equipo", nombreEquipo);
                    datos.put("fechayhora", fecha + " " + horaAhora);
                    datos.put("ip", ipLocal);
                    datos.put("mac", mac);
                    datos.put("tecnico", usuario);

                    ApiFuture<WriteResult> result = Alist.document(nombreEquipo + " " + fecha + " " + horaAhora).set(datos);
                    System.out.println("elemento creado en: " + result.toString());

                } else {
                    System.out.println("No se pudo obtener la dirección MAC.");
                }
            } else {
                System.out.println("No se encontró la interfaz de red.");
            }
        } catch (SocketException | UnknownHostException e) {
        }
    }

    public static void autenticarUsuario(String usuario, String contraseña) throws UnknownHostException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("usuarios");
        Query query = ref.whereEqualTo("usuario", usuario);
        ApiFuture<QuerySnapshot> future = query.get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (documents.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron los valores");
                interfaz();
            } else {
                for (QueryDocumentSnapshot document : documents) {
                    if (document.exists()) {
                        String user = document.getString("usuario");
                        String pass = document.getString("contraseña");
                        String rol = document.getString("rol");
                        if (usuario.equals(user) && contraseña.equals(pass)) {
                            if ("".equals(pass)) {
                                String cambioPass = JOptionPane.showInputDialog(null, "Ingrese su contraseña");
                                CollectionReference docRef = db.collection("usuarios");
                                Query usuarios = docRef.whereEqualTo("usuario", user);
                                ApiFuture<QuerySnapshot> querySnapshot = usuarios.get();
                                List<QueryDocumentSnapshot> documentos = querySnapshot.get().getDocuments();
                                if (!documentos.isEmpty()) {
                                    for (QueryDocumentSnapshot documento : documentos) {
                                        String docId = documento.getId();
                                        DocumentReference Ref = db.collection("usuarios").document(docId);
                                        Ref.update("contraseña", cambioPass);
                                        JOptionPane.showMessageDialog(null, "El campo ha sido actualizado: ID - " + docId + " -> " + document.getData());
                                        JOptionPane.showMessageDialog(null, "Su contraseña a sido restablecida");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se encontró el usuario.");
                                }
                            }
                            switch (rol) {
                                case "Admin":
                                    JOptionPane.showMessageDialog(null, "!Bienvenido, " + user + "¡");
                                    interfazAdmin();
                                    break;
                                case "Jefe":
                                    JOptionPane.showMessageDialog(null, "!Bienvenido, " + user + "¡");
                                    interfazAdmin();
                                    break;
                                case "Analista":
                                    JOptionPane.showMessageDialog(null, "!Bienvenido, " + user + "¡");
                                    interfazAdmin();
                                    break;
                                case "Tecnico":
                                    JOptionPane.showMessageDialog(null, "!Bienvenido, " + user + "¡");
                                    interfazInstalador();
                                    enviarDatos(usuario);
                                    break;
                                case "Junior":
                                    JOptionPane.showMessageDialog(null, "!Bienvenido, " + user + "¡");
                                    interfazInstalador();
                                    enviarDatos(usuario);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Contacte al Administrador para asignarle un rol.");
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                            interfaz();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario " + usuario + " no existe");
                    }
                }
            }
        } catch (HeadlessException | InterruptedException | ExecutionException e) {
            String errorMessage = e.toString(); // Captura el mensaje del error
            if (errorMessage != null && (errorMessage.contains("Host desconocido") || errorMessage.contains("UnknownHostException"))) {
                // Mensaje directo si hay un problema con el host
                JOptionPane.showMessageDialog(null, "Error: Host desconocido o no encontrado");
                interfaz();
            } else {
                // Mensaje genérico para otros errores
                JOptionPane.showMessageDialog(null, "Error: Ocurrió un problema desconocido.");
                interfaz();
            }
        }
    }

}
