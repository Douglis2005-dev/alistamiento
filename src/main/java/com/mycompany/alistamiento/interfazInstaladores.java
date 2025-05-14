package com.mycompany.alistamiento;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentChange;
import static com.google.cloud.firestore.DocumentChange.Type.ADDED;
import static com.google.cloud.firestore.DocumentChange.Type.MODIFIED;
import static com.google.cloud.firestore.DocumentChange.Type.REMOVED;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import static com.mycompany.alistamiento.Alistamiento.ajustarAnchoColumnas;
import static com.mycompany.alistamiento.Alistamiento.ejecutarManuales;
import static com.mycompany.alistamiento.interfazLogin.interfaz;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

;

public class interfazInstaladores {

    public static void interfazInstalador() {
        JFrame frame = new JFrame("Instalador de programas");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        componenetesInstaladores(panel, frame);
        frame.setVisible(true);
    }

    public static void componenetesInstaladores(JPanel panel, JFrame frame) {
        String[] Certificados = {"Fortinet_CA.cer", "Fortinet_CA_SSL.cer"};
        String usuario = "tecnico.it";
        String contraseñaCarpeta = "Tecnologia1603";

        panel.setLayout(null);

        String[] Datos = {"Fecha y Hora", "Programa", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, Datos);
        JTable tabla = new JTable(modelo);

        JLabel VNC = new JLabel("instalador de VNC ");
        VNC.setBounds(10, 20, 300, 25);
        panel.add(VNC);

        JButton instalarVnc = new JButton("Instalar");
        instalarVnc.setBounds(310, 20, 100, 25);
        panel.add(instalarVnc);

        JLabel Google = new JLabel("instalador de Google");
        Google.setBounds(10, 50, 300, 25);
        panel.add(Google);

        JButton instalarChrome = new JButton("Instalar");
        instalarChrome.setBounds(310, 50, 100, 25);
        panel.add(instalarChrome);

        JLabel Firefox1 = new JLabel("instalador de Firefox");
        Firefox1.setBounds(10, 80, 300, 25);
        panel.add(Firefox1);

        JButton instalarFirefox = new JButton("Instalar");
        instalarFirefox.setBounds(310, 80, 100, 25);
        panel.add(instalarFirefox);

        JLabel Zip7 = new JLabel("instalador de Zip");
        Zip7.setBounds(10, 110, 300, 25);
        panel.add(Zip7);

        JButton instalarZip = new JButton("Instalar");
        instalarZip.setBounds(310, 110, 100, 25);
        panel.add(instalarZip);

        JLabel Qsync1 = new JLabel("instalador de Qsync");
        Qsync1.setBounds(10, 140, 300, 25);
        panel.add(Qsync1);

        JButton instalarQsync = new JButton("Instalar");
        instalarQsync.setBounds(310, 140, 100, 25);
        panel.add(instalarQsync);

        JLabel Cobian1 = new JLabel("instalador de Cobian");
        Cobian1.setBounds(10, 170, 300, 25);
        panel.add(Cobian1);

        JButton instalarCobian = new JButton("Instalar");
        instalarCobian.setBounds(310, 170, 100, 25);
        panel.add(instalarCobian);

        JLabel Certificado = new JLabel("elija los certificados a instalar:");
        Certificado.setBounds(10, 200, 300, 25);
        panel.add(Certificado);

        JComboBox<String> CertificadosFortinet = new JComboBox<>(Certificados);
        CertificadosFortinet.setBounds(310, 200, 300, 25);
        panel.add(CertificadosFortinet);

        JButton instalarCertificados = new JButton("Instalar");
        instalarCertificados.setBounds(620, 200, 100, 25);
        panel.add(instalarCertificados);

        JLabel Kaspersky = new JLabel("instalador de kaspersky");
        Kaspersky.setBounds(10, 230, 300, 25);
        panel.add(Kaspersky);

        JButton instalarKaspersky = new JButton("Instalar");
        instalarKaspersky.setBounds(310, 230, 100, 25);
        panel.add(instalarKaspersky);

        JLabel Adobe1 = new JLabel("instalador de Adobe");
        Adobe1.setBounds(10, 260, 300, 25);
        panel.add(Adobe1);

        JButton instalarAdobe = new JButton("Instalar");
        instalarAdobe.setBounds(310, 260, 100, 25);
        panel.add(instalarAdobe);

        JButton cerrarSesion = new JButton("Cerrar sesion");
        cerrarSesion.setBounds(600, 30, 150, 25);
        panel.add(cerrarSesion);

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE,dd 'de' MMM 'de' yyyy ", new Locale("es", "ES"));
        //cambia el formato de la fecha a uno mas legible por usuario
        String fechaHoraFormateada = ahora.format(formatter);

        JLabel fecha = new JLabel("Fecha y hora: " + fechaHoraFormateada);
        fecha.setBounds(600, 10, 300, 25);
        panel.add(fecha);

        JLabel hora = new JLabel();
        hora.setBounds(900, 10, 500, 25);
        panel.add(hora);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 300, 960, 250);
        tabla.setBounds(10, 300, 960, 250);
        tabla.setDefaultEditor(Object.class, null);
        panel.add(scroll);
        panel.revalidate();
        panel.repaint();

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        Timer timer = new Timer(1000, e -> {
            String horaAhora = formatoHora.format(new Date());
            hora.setText(horaAhora);
        });
        timer.start();

        String[] certificadosForti = new String[Certificados.length];
        for (int i = 0; i < Certificados.length; i++) {
            certificadosForti[i] = Paths.get(Certificados[i]).getFileName().toString();
        }
        cerrarSesion.addActionListener(e -> {
            frame.dispose();
            interfaz();
        });
        instalarCertificados.addActionListener(e -> {
            // Obtener el índice del programa seleccionado
            int selectedIndex = CertificadosFortinet.getSelectedIndex();
            // Usar la ruta completa desde el arreglo original
            String programaSeleccionado = Certificados[selectedIndex];
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            CollectionReference manual = db.collection("manuales");
            Query Programas = prog.whereEqualTo("Programa", programaSeleccionado);
            Query Manuales = manual.whereEqualTo("manual", "FortinetManu");
            ApiFuture<QuerySnapshot> Prog = Programas.get();
            ApiFuture<QuerySnapshot> Manu = Manuales.get();
            int manu = JOptionPane.showConfirmDialog(panel, "¿Desea consultar el manual?");
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documentsProg = Prog.get().getDocuments();
                        List<QueryDocumentSnapshot> documentsManu = Manu.get().getDocuments();
                        if (documentsProg.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        } else {
                            if (documentsManu.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "El manual no existe buscado en la base de datos");
                            } else {
                                if (manu == JOptionPane.YES_OPTION) {
                                    if (documentsManu.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el manual en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        for (QueryDocumentSnapshot document : documentsManu) {
                                            if (document.exists()) {
                                                String ruta = document.getString("ruta");
                                                JOptionPane.showMessageDialog(null, "Se esta ejecutando el manual de Fortinet");
                                                ejecutarManuales(ruta, usuario);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "El manual no existe");
                                                instalarCertificados.setEnabled(true);
                                                instalarCertificados.setText("Instalar");
                                            }
                                        }
                                    }
                                    if (documentsProg.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        try {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                if (document.exists()) {
                                                    String ruta = document.getString("Ruta");
                                                    String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                    Process proceso = Runtime.getRuntime().exec(comando);
                                                    try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                                        writer.write(contraseñaCarpeta + "\n");
                                                        writer.flush();
                                                    }
                                                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                    BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                    String linea;
                                                    while ((linea = stdInput.readLine()) != null) {
                                                        System.out.println(linea);
                                                    }
                                                    while ((linea = stdError.readLine()) != null) {
                                                        JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                    int exitVal = proceso.waitFor();
                                                    if (exitVal != 0) {
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, Programas, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        instalarCertificados.setEnabled(false);
                                                        instalarCertificados.setText("Instalado");
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], "Instalado", false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                } else {
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    instalarCertificados.setEnabled(true);
                                                    instalarCertificados.setText("Instalar");
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], "Instalado", false});
                                                    ajustarAnchoColumnas(tabla);
                                                }
                                            }
                                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                                switch (dc.getType()) {
                                                    case ADDED:
                                                        JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                                        break;
                                                    case MODIFIED:
                                                        JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                                        break;
                                                    case REMOVED:
                                                        JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                                        break;
                                                    default:
                                                        throw new AssertionError(dc.getType().name());
                                                }
                                            }
                                        } catch (HeadlessException | IOException | InterruptedException ex) {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                String programa = document.getString("Programa");
                                                String horaAhora = formatoHora.format(new Date());
                                                hora.setText(horaAhora);
                                                instalarCertificados.setEnabled(true);
                                                instalarCertificados.setText("Instalar");
                                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                                ajustarAnchoColumnas(tabla);
                                            }
                                        }
                                    }
                                }
                                if (manu == JOptionPane.NO_OPTION) {
                                    JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de " + certificadosForti[selectedIndex]);
                                    if (documentsProg.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        try {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                                                if (document.exists()) {
                                                    String ruta = document.getString("Ruta");
                                                    String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                    Process proceso = Runtime.getRuntime().exec(comando);
                                                    System.out.println("se esta ejecutando el instalador " + comando);
                                                    try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                                        writer.write(contraseñaCarpeta + "\n");
                                                        writer.flush();
                                                    }
                                                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                    BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                    String linea;
                                                    while ((linea = stdInput.readLine()) != null) {
                                                        System.out.println(linea);
                                                    }
                                                    while ((linea = stdError.readLine()) != null) {
                                                        JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                    int exitVal = proceso.waitFor();
                                                    if (exitVal != 0) {
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, Programas, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        instalarCertificados.setEnabled(false);
                                                        instalarCertificados.setText("Instalado");
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], "Instalado", false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "El programa " + certificadosForti[selectedIndex] + " no existe");
                                                    instalarCertificados.setEnabled(true);
                                                    instalarCertificados.setText("Instalar");
                                                }
                                            }
                                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                                switch (dc.getType()) {
                                                    case ADDED :
                                                        JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                                        break;
                                                    case MODIFIED :
                                                        JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                                        break;
                                                    case REMOVED :
                                                        JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                                        break;
                                                    default :
                                                        throw new AssertionError(dc.getType().name());
                                                }
                                            }
                                        } catch (HeadlessException | IOException | InterruptedException ex) {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                String programa = document.getString("Programa");
                                                String horaAhora = formatoHora.format(new Date());
                                                hora.setText(horaAhora);
                                                instalarCertificados.setEnabled(true);
                                                instalarCertificados.setText("Instalar");
                                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                                ajustarAnchoColumnas(tabla);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        String horaAhora = formatoHora.format(new Date());
                        Logger.getLogger(Alistamiento.class.getName()).log(Level.SEVERE, null, ex);
                        instalarCertificados.setEnabled(true);
                        instalarCertificados.setText("Instalar");
                        hora.setText(horaAhora);
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, certificadosForti[selectedIndex], ex.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
        instalarVnc.addActionListener(e -> {
            int manu = JOptionPane.showConfirmDialog(panel, "¿Desea consultar el manual?");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            CollectionReference manual = db.collection("manuales");
            Query Programas = prog.whereEqualTo("Programa", "Vnc");
            Query Manuales = manual.whereEqualTo("manual", "VncManu");
            ApiFuture<QuerySnapshot> Prog = Programas.get();
            ApiFuture<QuerySnapshot> Manu = Manuales.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documentsProg = Prog.get().getDocuments();
                        List<QueryDocumentSnapshot> documentsManu = Manu.get().getDocuments();
                        if (documentsProg.isEmpty()) {
                            for (QueryDocumentSnapshot document : documentsProg) {
                                String programa = document.getString("Programa");
                                JOptionPane.showMessageDialog(null, "El programa " + programa + " no existe buscado en la base de datos");
                            }
                        } else {
                            if (documentsManu.isEmpty()) {
                                for (QueryDocumentSnapshot document : documentsManu) {
                                    String programa = document.getString("Programa");
                                    JOptionPane.showMessageDialog(null, "El manual " + programa + " no existe buscado en la base de datos");
                                }
                            } else {
                                if (manu == JOptionPane.YES_OPTION) {
                                    if (documentsManu.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el manual en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        for (QueryDocumentSnapshot document : documentsManu) {
                                            if (document.exists()) {
                                                String ruta = document.getString("ruta");
                                                ejecutarManuales(ruta, usuario);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "El programa no existe");
                                                instalarVnc.setEnabled(true);
                                                instalarVnc.setText("Instalar");
                                            }
                                        }
                                    }
                                    for (QueryDocumentSnapshot document : documentsProg) {
                                        String programa = document.getString("Programa");
                                        if (documentsProg.isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                            String horaAhora = formatoHora.format(new Date());
                                            hora.setText(horaAhora);
                                            modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                            ajustarAnchoColumnas(tabla);
                                        } else {
                                            try {
                                                if (document.exists()) {
                                                    JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de " + programa);
                                                    String ruta = document.getString("Ruta");
                                                    String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                    Process proceso = Runtime.getRuntime().exec(comando);
                                                    System.out.println("se esta ejecutando el instalador " + comando);
                                                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                    BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                    String linea;
                                                    while ((linea = stdInput.readLine()) != null) {
                                                        System.out.println(linea);
                                                    }
                                                    while ((linea = stdError.readLine()) != null) {
                                                        JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                    int exitVal = proceso.waitFor();
                                                    if (exitVal != 0) {
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        instalarVnc.setEnabled(false);
                                                        instalarVnc.setText("Instalado");
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "El programa no existe");
                                                    instalarVnc.setEnabled(true);
                                                    instalarVnc.setText("Instalar");
                                                }

                                            } catch (HeadlessException | IOException | InterruptedException ex) {
                                                String horaAhora = formatoHora.format(new Date());
                                                hora.setText(horaAhora);
                                                instalarVnc.setEnabled(true);
                                                instalarVnc.setText("Instalar");
                                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                                ajustarAnchoColumnas(tabla);
                                            }
                                        }
                                    }
                                    for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                        switch (dc.getType()) {
                                            case ADDED :
                                                JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                                break;
                                            case MODIFIED :
                                                JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                                break;
                                            case REMOVED :
                                                JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                                break;
                                            default :
                                                throw new AssertionError(dc.getType().name());
                                        }
                                    }
                                }
                                if (manu == JOptionPane.NO_OPTION) {
                                    for (QueryDocumentSnapshot document : documentsProg) {
                                        if (documentsProg.isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                            String horaAhora = formatoHora.format(new Date());
                                            hora.setText(horaAhora);
                                            modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                            ajustarAnchoColumnas(tabla);
                                        } else {
                                            try {
                                                if (document.exists()) {
                                                    String programa = document.getString("Programa");
                                                    String ruta = document.getString("Ruta");
                                                    String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                    Process proceso = Runtime.getRuntime().exec(comando);
                                                    System.out.println("se esta ejecutando el instalador " + comando);
                                                    try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                                        writer.write(contraseñaCarpeta + "\n");
                                                        writer.flush();
                                                    }
                                                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                    BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                    String linea;
                                                    while ((linea = stdInput.readLine()) != null) {
                                                        System.out.println(linea);
                                                    }
                                                    while ((linea = stdError.readLine()) != null) {
                                                        JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                    int exitVal = proceso.waitFor();
                                                    if (exitVal != 0) {
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        instalarVnc.setEnabled(false);
                                                        instalarVnc.setText("Instalado");
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "El programa Vnc no existe");
                                                    instalarVnc.setEnabled(true);
                                                    instalarVnc.setText("Instalar");
                                                }
                                            } catch (HeadlessException | IOException | InterruptedException ex) {
                                                String programa = document.getString("Programa");
                                                String horaAhora = formatoHora.format(new Date());
                                                hora.setText(horaAhora);
                                                instalarVnc.setEnabled(true);
                                                instalarVnc.setText("Instalar");
                                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                                ajustarAnchoColumnas(tabla);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(Alistamiento.class.getName()).log(Level.SEVERE, null, ex);
                        instalarVnc.setEnabled(true);
                        instalarVnc.setText("Instalar");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Vnc", ex.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });

        });
        instalarChrome.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de Chrome");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            Query query = prog.whereEqualTo("Programa", "Chrome");
            ApiFuture<QuerySnapshot> future = query.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                        if (documents.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        }
                        for (QueryDocumentSnapshot document : documents) {
                            System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                            if (document.exists()) {
                                String programa = document.getString("Programa");
                                String ruta = document.getString("Ruta");
                                String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                Process proceso = Runtime.getRuntime().exec(comando);
                                System.out.println("se esta ejecutando el instalador " + comando);
                                try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInput.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                while ((linea = stdError.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                }
                                int exitVal = proceso.waitFor();
                                if (exitVal != 0) {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    instalarChrome.setEnabled(false);
                                    instalarChrome.setText("Instalado");
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                    ajustarAnchoColumnas(tabla);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El programa no existe");
                            }
                        }
                        for (DocumentChange dc : snapshot.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED :
                                    JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                case MODIFIED :
                                    JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                case REMOVED :
                                    JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                default :
                                    throw new AssertionError(dc.getType().name());
                            }
                        }
                    } catch (IOException | InterruptedException | ExecutionException err) {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        instalarChrome.setEnabled(true);
                        instalarChrome.setText("Instalar");
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", err.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
        instalarFirefox.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de Firefox");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            Query query = prog.whereEqualTo("Programa", "Firefox");
            ApiFuture<QuerySnapshot> future = query.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                        if (documents.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        }
                        for (QueryDocumentSnapshot document : documents) {
                            System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                            if (document.exists()) {
                                String programa = document.getString("Programa");
                                String ruta = document.getString("Ruta");
                                String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                Process proceso = Runtime.getRuntime().exec(comando);
                                System.out.println("se esta ejecutando el instalador " + comando);
                                try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInput.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                while ((linea = stdError.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                }
                                int exitVal = proceso.waitFor();
                                if (exitVal != 0) {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    instalarFirefox.setEnabled(false);
                                    instalarFirefox.setText("Instalado");
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                    ajustarAnchoColumnas(tabla);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El programa no existe");
                            }
                        }
                        for (DocumentChange dc : snapshot.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED :
                                    JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                case MODIFIED :
                                    JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                case REMOVED :
                                    JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                default :
                                    throw new AssertionError(dc.getType().name());
                            }
                        }
                    } catch (IOException | InterruptedException | ExecutionException err) {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        instalarFirefox.setEnabled(true);
                        instalarFirefox.setText("Instalar");
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", err.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
        instalarZip.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de 7Zip");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            Query query = prog.whereEqualTo("Programa", "Zip");
            ApiFuture<QuerySnapshot> future = query.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                        if (documents.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        }
                        for (QueryDocumentSnapshot document : documents) {
                            System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                            if (document.exists()) {
                                String programa = document.getString("Programa");
                                String ruta = document.getString("Ruta");
                                String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                Process proceso = Runtime.getRuntime().exec(comando);
                                System.out.println("se esta ejecutando el instalador " + comando);
                                try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInput.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                while ((linea = stdError.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                }
                                int exitVal = proceso.waitFor();
                                if (exitVal != 0) {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    instalarZip.setEnabled(false);
                                    instalarZip.setText("Instalado");
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                    ajustarAnchoColumnas(tabla);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El programa no existe");
                            }
                        }
                        for (DocumentChange dc : snapshot.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED :
                                    JOptionPane.showMessageDialog(null, "📌 Nueva rutao: " + dc.getDocument().getData());
                                case MODIFIED :
                                    JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                case REMOVED :
                                    JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                default :
                                    throw new AssertionError(dc.getType().name());
                            }
                        }
                    } catch (IOException | InterruptedException | ExecutionException err) {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        instalarZip.setEnabled(true);
                        instalarZip.setText("Instalar");
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", err.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
        instalarQsync.addActionListener(e -> {
            int manu = JOptionPane.showConfirmDialog(panel, "¿Desea consultar el manual?");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            CollectionReference manual = db.collection("manuales");
            Query Programas = prog.whereEqualTo("Programa", "Qsync");
            Query Manuales = manual.whereEqualTo("manual", "QsyncManu");
            ApiFuture<QuerySnapshot> Prog = Programas.get();
            ApiFuture<QuerySnapshot> Manu = Manuales.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documentsProg = Prog.get().getDocuments();
                        List<QueryDocumentSnapshot> documentsManu = Manu.get().getDocuments();
                        if (documentsProg.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        } else {
                            if (documentsManu.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "El manual no existe buscado en la base de datos");
                            } else {
                                if (manu == JOptionPane.YES_OPTION) {
                                    if (documentsManu.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el manual en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        for (QueryDocumentSnapshot document : documentsManu) {
                                            if (document.exists()) {
                                                String ruta = document.getString("ruta");
                                                ejecutarManuales(ruta, usuario);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "El programa no existe");
                                                instalarQsync.setEnabled(true);
                                                instalarQsync.setText("Instalar");
                                            }
                                        }
                                    }
                                    if (documentsProg.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        try {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                if (document.exists()) {
                                                    String programa = document.getString("Programa");
                                                    String ruta = document.getString("Ruta");
                                                    String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                    Process proceso = Runtime.getRuntime().exec(comando);
                                                    System.out.println("se esta ejecutando el instalador " + comando);
                                                    try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                                        writer.write(contraseñaCarpeta + "\n");
                                                        writer.flush();
                                                    }
                                                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                    BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                    String linea;
                                                    while ((linea = stdInput.readLine()) != null) {
                                                        System.out.println(linea);
                                                    }
                                                    while ((linea = stdError.readLine()) != null) {
                                                        JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                    int exitVal = proceso.waitFor();
                                                    if (exitVal != 0) {
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        instalarQsync.setEnabled(false);
                                                        instalarQsync.setText("Instalado");
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "El programa no existe");
                                                    instalarQsync.setEnabled(true);
                                                    instalarQsync.setText("Instalar");
                                                }
                                            }
                                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                                switch (dc.getType()) {
                                                    case ADDED :
                                                        JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                                    case MODIFIED :
                                                        JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                                    case REMOVED :
                                                        JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                                    default :
                                                        throw new AssertionError(dc.getType().name());
                                                }
                                            }
                                        } catch (HeadlessException | IOException | InterruptedException ex) {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                String programa = document.getString("Programa");
                                                String horaAhora = formatoHora.format(new Date());
                                                hora.setText(horaAhora);
                                                instalarQsync.setEnabled(true);
                                                instalarQsync.setText("Instalar");
                                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                                ajustarAnchoColumnas(tabla);
                                            }
                                        }
                                    }
                                }
                                if (manu == JOptionPane.NO_OPTION) {
                                    JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de Qsync");
                                    if (documentsProg.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        try {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                                                if (document.exists()) {
                                                    String programa = document.getString("Programa");
                                                    String ruta = document.getString("Ruta");
                                                    String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                    Process proceso = Runtime.getRuntime().exec(comando);
                                                    System.out.println("se esta ejecutando el instalador " + comando);
                                                    try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                                        writer.write(contraseñaCarpeta + "\n");
                                                        writer.flush();
                                                    }
                                                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                    BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                    String linea;
                                                    while ((linea = stdInput.readLine()) != null) {
                                                        System.out.println(linea);
                                                    }
                                                    while ((linea = stdError.readLine()) != null) {
                                                        JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                    int exitVal = proceso.waitFor();
                                                    if (exitVal != 0) {
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                        ajustarAnchoColumnas(tabla);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "El programa fue instalado");
                                                        String horaAhora = formatoHora.format(new Date());
                                                        hora.setText(horaAhora);
                                                        instalarQsync.setEnabled(false);
                                                        instalarQsync.setText("Instalado");
                                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                                        ajustarAnchoColumnas(tabla);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "El programa Vnc no existe");
                                                    instalarQsync.setEnabled(true);
                                                    instalarQsync.setText("Instalar");
                                                }
                                            }
                                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                                switch (dc.getType()) {
                                                    case ADDED :
                                                        JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                                    case MODIFIED :
                                                        JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                                    case REMOVED :
                                                        JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                                    default :
                                                        throw new AssertionError(dc.getType().name());
                                                }
                                            }
                                        } catch (HeadlessException | IOException | InterruptedException ex) {
                                            for (QueryDocumentSnapshot document : documentsProg) {
                                                String programa = document.getString("Programa");
                                                String horaAhora = formatoHora.format(new Date());
                                                hora.setText(horaAhora);
                                                instalarQsync.setEnabled(true);
                                                instalarQsync.setText("Instalar");
                                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                                ajustarAnchoColumnas(tabla);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(Alistamiento.class.getName()).log(Level.SEVERE, null, ex);
                        instalarQsync.setEnabled(true);
                        instalarQsync.setText("Instalar");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Vnc", ex.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
        instalarCobian.addActionListener(e -> {
            int manu = JOptionPane.showConfirmDialog(panel, "¿Desea consultar el manual?");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            CollectionReference manual = db.collection("manuales");
            Query Programas = prog.whereEqualTo("Programa", "Cobian");
            Query Manuales = manual.whereEqualTo("manual", "CobianManu");
            ApiFuture<QuerySnapshot> Prog = Programas.get();
            ApiFuture<QuerySnapshot> Manu = Manuales.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documentsProg = Prog.get().getDocuments();
                        List<QueryDocumentSnapshot> documentsManu = Manu.get().getDocuments();
                        if (documentsProg.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        } else {
                            if (manu == JOptionPane.YES_OPTION) {
                                if (documentsManu.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "No se encontro el manual en la base de datos.");
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    for (QueryDocumentSnapshot document : documentsManu) {
                                        System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                                        if (document.exists()) {
                                            String ruta = document.getString("ruta");
                                            ejecutarManuales(ruta, usuario);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "El programa no existe");
                                            instalarCobian.setEnabled(true);
                                            instalarCobian.setText("Instalar");
                                        }
                                    }
                                }
                                if (documentsProg.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    try {
                                        for (QueryDocumentSnapshot document : documentsProg) {
                                            if (document.exists()) {
                                                String programa = document.getString("Programa");
                                                String ruta = document.getString("Ruta");
                                                String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                Process proceso = Runtime.getRuntime().exec(comando);
                                                System.out.println("se esta ejecutando el instalador " + comando);
                                                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                String linea;
                                                while ((linea = stdInput.readLine()) != null) {
                                                    System.out.println(linea);
                                                }
                                                while ((linea = stdError.readLine()) != null) {
                                                    JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                    ajustarAnchoColumnas(tabla);
                                                }
                                                int exitVal = proceso.waitFor();
                                                if (exitVal != 0) {
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                    ajustarAnchoColumnas(tabla);
                                                } else {
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    instalarCobian.setEnabled(false);
                                                    instalarCobian.setText("Instalado");
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                                    ajustarAnchoColumnas(tabla);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "El programa no existe");
                                                instalarCobian.setEnabled(true);
                                                instalarCobian.setText("Instalar");
                                            }
                                        }
                                    } catch (HeadlessException | IOException | InterruptedException ex) {
                                        for (QueryDocumentSnapshot document : documentsProg) {
                                            String programa = document.getString("Programa");
                                            String horaAhora = formatoHora.format(new Date());
                                            hora.setText(horaAhora);
                                            instalarCobian.setEnabled(true);
                                            instalarCobian.setText("Instalar");
                                            modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                            ajustarAnchoColumnas(tabla);
                                        }
                                    }
                                }
                            }
                            if (manu == JOptionPane.NO_OPTION) {
                                JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de Cobian");
                                if (documentsProg.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "No se encontro el programa en la base de datos.");
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    try {
                                        for (QueryDocumentSnapshot document : documentsProg) {
                                            System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                                            if (document.exists()) {
                                                String programa = document.getString("Programa");
                                                String ruta = document.getString("Ruta");
                                                String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                                Process proceso = Runtime.getRuntime().exec(comando);
                                                System.out.println("se esta ejecutando el instalador " + comando);
                                                try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                                    writer.write(contraseñaCarpeta + "\n");
                                                    writer.flush();
                                                }
                                                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                                BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                                String linea;
                                                while ((linea = stdInput.readLine()) != null) {
                                                    System.out.println(linea);
                                                }
                                                while ((linea = stdError.readLine()) != null) {
                                                    JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                    ajustarAnchoColumnas(tabla);
                                                }
                                                int exitVal = proceso.waitFor();
                                                if (exitVal != 0) {
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                                    ajustarAnchoColumnas(tabla);
                                                } else {
                                                    String horaAhora = formatoHora.format(new Date());
                                                    hora.setText(horaAhora);
                                                    instalarCobian.setEnabled(false);
                                                    instalarCobian.setText("Instalado");
                                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                                    ajustarAnchoColumnas(tabla);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "El programa Vnc no existe");
                                                instalarCobian.setEnabled(true);
                                                instalarCobian.setText("Instalar");
                                            }
                                        }
                                        for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                            switch (dc.getType()) {
                                                case ADDED :
                                                    JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                                case MODIFIED :
                                                    JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                                case REMOVED :
                                                    JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                                default :
                                                    throw new AssertionError(dc.getType().name());
                                            }
                                        }
                                    } catch (HeadlessException | IOException | InterruptedException ex) {
                                        for (QueryDocumentSnapshot document : documentsProg) {
                                            String programa = document.getString("Programa");
                                            String horaAhora = formatoHora.format(new Date());
                                            hora.setText(horaAhora);
                                            instalarCobian.setEnabled(true);
                                            instalarCobian.setText("Instalar");
                                            modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, ex.getMessage(), false});
                                            ajustarAnchoColumnas(tabla);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(Alistamiento.class.getName()).log(Level.SEVERE, null, ex);
                        instalarCobian.setEnabled(true);
                        instalarCobian.setText("Instalar");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Vnc", ex.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });

        });
        instalarKaspersky.addActionListener(e -> {
            int manu = JOptionPane.showConfirmDialog(panel, "¿Desea consultar el manual?");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            CollectionReference manual = db.collection("manuales");
            Query NetConsulta = prog.whereEqualTo("Programa", "Net");
            Query KesConsulta = prog.whereEqualTo("Programa", "Kes");
            Query ManuConsulta = manual.whereEqualTo("manual", "KasperskyManu");
            ApiFuture<QuerySnapshot> future1 = NetConsulta.get();
            ApiFuture<QuerySnapshot> future2 = KesConsulta.get();
            ApiFuture<QuerySnapshot> future3 = ManuConsulta.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documentsNet = future1.get().getDocuments();
                        List<QueryDocumentSnapshot> documentsKes = future2.get().getDocuments();
                        List<QueryDocumentSnapshot> documentsManu = future3.get().getDocuments();
                        if (documentsNet.isEmpty() || documentsKes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        }
                        if (manu == JOptionPane.YES_OPTION) {
                            if (documentsManu.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "No se encontro el manual en la base de datos.");
                                String horaAhora = formatoHora.format(new Date());
                                hora.setText(horaAhora);
                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", "Introdujo valores no validos", false});
                                ajustarAnchoColumnas(tabla);
                            } else {
                                for (QueryDocumentSnapshot document : documentsManu) {
                                    if (document.exists()) {
                                        String ruta = document.getString("ruta");
                                        ejecutarManuales(ruta, usuario);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El programa no existe");
                                        instalarKaspersky.setEnabled(true);
                                        instalarKaspersky.setText("Instalar");
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de Kaspesrky");
                            for (QueryDocumentSnapshot documentNet : documentsNet) {
                                File Net1 = new File("C:\\Users\\.\\administrador\\Desktop\\1_Net_Kaspersky");
                                if (documentNet.exists()) {
                                    String rutaNet = documentNet.getString("Ruta");
                                    File Net = new File(rutaNet);
                                    try {
                                        FileUtils.copyDirectory(Net, Net1);
                                        JOptionPane.showMessageDialog(panel, "La carpeta ha sido enviada a la ruta:" + Net1);
                                    } catch (IOException ex) {
                                        String horaAhora = formatoHora.format(new Date());
                                        JOptionPane.showMessageDialog(panel, "No se pudo enviar la carpeta " + Net + ex.getMessage());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, Net1, ex.getMessage(), false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                }
                            }
                            for (QueryDocumentSnapshot documentKes : documentsKes) {
                                File Kes2 = new File("C:\\Users\\.\\administrador\\Desktop\\2_Kes_Kaspersky");
                                if (documentKes.exists()) {
                                    String rutaNet = documentKes.getString("Ruta");
                                    File Kes = new File(rutaNet);
                                    try {
                                        FileUtils.copyDirectory(Kes, Kes2);
                                        JOptionPane.showMessageDialog(panel, "La carpeta ha sido enviada a la ruta:" + Kes2);
                                    } catch (IOException ex) {
                                        String horaAhora = formatoHora.format(new Date());
                                        JOptionPane.showMessageDialog(panel, "No se pudo enviar la carpeta " + Kes2 + ex.getMessage());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, Kes2, ex.getMessage(), false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                }
                            }
                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                switch (dc.getType()) {
                                    case ADDED :
                                        JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                    case MODIFIED :
                                        JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                    case REMOVED :
                                        JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                    default :
                                        throw new AssertionError(dc.getType().name());
                                }
                            }
                            try {
                                String NetInstalar = "C:\\Users\\.\\administrador\\Desktop\\1_Net_Kaspersky\\setup";
                                String Netcomando = String.format("cmd.exe /c \"%s\"", NetInstalar);
                                Process netProceso = Runtime.getRuntime().exec(Netcomando);
                                System.out.println("se esta ejecutando el instalador " + NetInstalar);
                                try (Writer writer = new OutputStreamWriter(netProceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInputNet = new BufferedReader(new InputStreamReader(netProceso.getInputStream()));
                                BufferedReader stdErrorNet = new BufferedReader(new InputStreamReader(netProceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInputNet.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                while ((linea = stdErrorNet.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, "ups,hubo un error aqui:" + linea);
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, NetInstalar, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                }
                                int exitVal = netProceso.waitFor();
                                if (exitVal != 0) {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Kaspersky", linea, false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, NetInstalar, "Instalado", false});
                                    ajustarAnchoColumnas(tabla);
                                }
                            } catch (HeadlessException | IOException | InterruptedException ex) {
                                String horaAhora = formatoHora.format(new Date());
                                hora.setText(horaAhora);
                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", ex.getMessage(), false});
                                ajustarAnchoColumnas(tabla);
                            }
                            try {
                                String KesInstalar = "C:\\Users\\.\\administrador\\Desktop\\2_Kes_Kaspersky\\setup";
                                String Kescomando = String.format("cmd.exe /c \"%s\"", KesInstalar);
                                Process kesProceso = Runtime.getRuntime().exec(Kescomando);
                                System.out.println("se esta ejecutando el instalador " + KesInstalar);
                                try (Writer writer = new OutputStreamWriter(kesProceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInputKes = new BufferedReader(new InputStreamReader(kesProceso.getInputStream()));
                                BufferedReader stdErrorKes = new BufferedReader(new InputStreamReader(kesProceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInputKes.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, linea);
                                }
                                while ((linea = stdErrorKes.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, "ups,hubo un erro aqui:" + linea);
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, KesInstalar, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                }
                                int exitVal = kesProceso.waitFor();
                                if (exitVal != 0) {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Kaspersky", linea, false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    instalarKaspersky.setEnabled(false);
                                    instalarKaspersky.setText("Instalado");
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, KesInstalar, "Instalado", false});
                                    ajustarAnchoColumnas(tabla);
                                }
                            } catch (HeadlessException | IOException | InterruptedException ex) {
                                String horaAhora = formatoHora.format(new Date());
                                hora.setText(horaAhora);
                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", ex.getMessage(), false});
                                ajustarAnchoColumnas(tabla);
                            }
                        }
                        if (manu == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(panel, "se esta iniciando la transferencia de carpetas necesarias para la instalacion de Kaspesrky");
                            for (QueryDocumentSnapshot documentNet : documentsNet) {
                                File Net1 = new File("C:\\Users\\.\\administrador\\Desktop\\1_Net_Kaspersky");
                                if (documentNet.exists()) {
                                    String rutaNet = documentNet.getString("Ruta");
                                    File Net = new File(rutaNet);
                                    try {
                                        FileUtils.copyDirectory(Net, Net1);
                                        JOptionPane.showMessageDialog(panel, "La carpeta ha sido enviada a la ruta:" + Net1);
                                    } catch (IOException ex) {
                                        String horaAhora = formatoHora.format(new Date());
                                        JOptionPane.showMessageDialog(panel, "No se pudo enviar la carpeta " + Net + ex.getMessage());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, Net1, ex.getMessage(), false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                }
                            }
                            for (QueryDocumentSnapshot documentKes : documentsKes) {
                                File Kes2 = new File("C:\\Users\\.\\administrador\\Desktop\\2_Kes_Kaspersky");
                                if (documentKes.exists()) {
                                    String rutaNet = documentKes.getString("Ruta");
                                    File Kes = new File(rutaNet);
                                    try {
                                        FileUtils.copyDirectory(Kes, Kes2);
                                        JOptionPane.showMessageDialog(panel, "La carpeta ha sido enviada a la ruta:" + Kes2);
                                    } catch (IOException ex) {
                                        String horaAhora = formatoHora.format(new Date());
                                        JOptionPane.showMessageDialog(panel, "No se pudo enviar la carpeta " + Kes2 + ex.getMessage());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, Kes2, ex.getMessage(), false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                }
                            }
                            for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                switch (dc.getType()) {
                                    case ADDED :
                                        JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                    case MODIFIED :
                                        JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                    case REMOVED :
                                        JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                    default :
                                        throw new AssertionError(dc.getType().name());
                                }
                            }
                            JOptionPane.showMessageDialog(null, "La Transferencia de archivos a terminado.");
                            try {
                                String NetInstalar = "C:\\Users\\.\\administrador\\Desktop\\1_Net_Kaspersky\\setup";
                                String Netcomando = String.format("cmd.exe /c \"%s\"", NetInstalar);
                                Process netProceso = Runtime.getRuntime().exec(Netcomando);
                                System.out.println("se esta ejecutando el instalador " + NetInstalar);
                                try (Writer writer = new OutputStreamWriter(netProceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInputNet = new BufferedReader(new InputStreamReader(netProceso.getInputStream()));
                                BufferedReader stdErrorNet = new BufferedReader(new InputStreamReader(netProceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInputNet.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                for (QueryDocumentSnapshot documentNet : documentsNet) {
                                    while ((linea = stdErrorNet.readLine()) != null) {
                                        String programaNet = documentNet.getString("Programa");
                                        String horaAhora = formatoHora.format(new Date());
                                        JOptionPane.showMessageDialog(null, "ups,hubo un error aqui:" + linea);
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programaNet, linea, false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                    int exitVal = netProceso.waitFor();
                                    if (exitVal != 0) {
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Kaspersky", linea, false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, NetInstalar, "Instalado", false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                }
                                for (DocumentChange dc : snapshot.getDocumentChanges()) {
                                    switch (dc.getType()) {
                                        case ADDED :
                                            JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                        case MODIFIED :
                                            JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                        case REMOVED :
                                            JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                        default :
                                            throw new AssertionError(dc.getType().name());
                                    }
                                }
                            } catch (HeadlessException | IOException | InterruptedException ex) {
                                String horaAhora = formatoHora.format(new Date());
                                hora.setText(horaAhora);
                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", ex.getMessage(), false});
                                ajustarAnchoColumnas(tabla);
                            }
                            try {
                                String KesInstalar = "C:\\Users\\.\\administrador\\Desktop\\2_Kes_Kaspersky\\setup";
                                String Kescomando = String.format("cmd.exe /c \"%s\"", KesInstalar);
                                Process kesProceso = Runtime.getRuntime().exec(Kescomando);
                                System.out.println("se esta ejecutando el instalador " + KesInstalar);
                                BufferedReader stdInputKes = new BufferedReader(new InputStreamReader(kesProceso.getInputStream()));
                                BufferedReader stdErrorKes = new BufferedReader(new InputStreamReader(kesProceso.getErrorStream()));
                                String linea;
                                for (QueryDocumentSnapshot documentKes : documentsKes) {
                                    while ((linea = stdInputKes.readLine()) != null) {
                                        System.out.println(linea);
                                    }
                                    while ((linea = stdErrorKes.readLine()) != null) {
                                        JOptionPane.showMessageDialog(null, "Ups, hubo un error:" + linea);
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        String programaKes = documentKes.getString("Programa");
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programaKes, linea, false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                    int exitVal = kesProceso.waitFor();
                                    if (exitVal != 0) {
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "Kaspersky", linea, false});
                                        ajustarAnchoColumnas(tabla);
                                    } else {
                                        String programaKes = documentKes.getString("Programa");
                                        String horaAhora = formatoHora.format(new Date());
                                        hora.setText(horaAhora);
                                        instalarKaspersky.setEnabled(false);
                                        instalarKaspersky.setText("Instalado");
                                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programaKes, "Instalado", false});
                                        ajustarAnchoColumnas(tabla);
                                    }
                                }
                            } catch (HeadlessException | IOException | InterruptedException ex) {
                                String horaAhora = formatoHora.format(new Date());
                                hora.setText(horaAhora);
                                modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", ex.getMessage(), false});
                                ajustarAnchoColumnas(tabla);
                            }
                        }
                    } catch (InterruptedException | ExecutionException err) {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", err.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
        instalarAdobe.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "se esta iniciando instalador de Adobe");
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference prog = db.collection("rutas");
            Query query = prog.whereEqualTo("Programa", "Adobe");
            ApiFuture<QuerySnapshot> future = query.get();
            prog.addSnapshotListener((snapshot, error) -> {
                if (error != null) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + error.getMessage());
                    return;
                }
                if (snapshot != null && !snapshot.isEmpty()) {
                    try {
                        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                        if (documents.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El programa no existe buscado en la base de datos");
                        }
                        for (QueryDocumentSnapshot document : documents) {
                            System.out.println("Documento encontrado: " + document.getId() + " -> " + document.getData());
                            if (document.exists()) {
                                String programa = document.getString("Programa");
                                String ruta = document.getString("Ruta");
                                String comando = String.format("cmd.exe /c \"%s\"", ruta);
                                Process proceso = Runtime.getRuntime().exec(comando);
                                System.out.println("se esta ejecutando el instalador " + comando);
                                try (Writer writer = new OutputStreamWriter(proceso.getOutputStream())) {
                                    writer.write(contraseñaCarpeta + "\n");
                                    writer.flush();
                                }
                                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                                BufferedReader stdError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                                String linea;
                                while ((linea = stdInput.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                while ((linea = stdError.readLine()) != null) {
                                    JOptionPane.showMessageDialog(null, "Ups, hubo un erorr:" + linea);
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                }
                                int exitVal = proceso.waitFor();
                                if (exitVal != 0) {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, linea, false});
                                    ajustarAnchoColumnas(tabla);
                                } else {
                                    String horaAhora = formatoHora.format(new Date());
                                    hora.setText(horaAhora);
                                    instalarAdobe.setEnabled(false);
                                    instalarAdobe.setText("Instalado");
                                    modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, programa, "Instalado", false});
                                    ajustarAnchoColumnas(tabla);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El programa no existe");
                            }
                        }
                        for (DocumentChange dc : snapshot.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED :
                                    JOptionPane.showMessageDialog(null, "📌 Nueva ruta: " + dc.getDocument().getData());
                                case MODIFIED :
                                    JOptionPane.showMessageDialog(null, "✏ Ruta modificada: " + dc.getDocument().getData());
                                case REMOVED :
                                    JOptionPane.showMessageDialog(null, "🗑 Ruta eliminada: " + dc.getDocument().getId());
                                default :
                                    throw new AssertionError(dc.getType().name());
                            }
                        }
                    } catch (IOException | InterruptedException | ExecutionException err) {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                        String horaAhora = formatoHora.format(new Date());
                        hora.setText(horaAhora);
                        instalarAdobe.setEnabled(true);
                        instalarAdobe.setText("Instalar");
                        modelo.insertRow(0, new Object[]{fechaHoraFormateada + " " + horaAhora, "", err.getMessage(), false});
                        ajustarAnchoColumnas(tabla);
                    }
                }
            });
        });
    }

}
