package com.mycompany.alistamiento;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import java.awt.Component;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import static com.mycompany.alistamiento.interfazLogin.interfaz;
import java.io.InputStream;

public class Alistamiento {

    public static void main(String[] args) {
//        String[] claves = {"REG ADD \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Policies\\Microsoft\\Edge\" /v \"InPrivateModeAvailability\" /t REG_DWORD /d 1",
//            "REG ADD \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Policies\\Google\\Chrome\" /v \"IncognitoModeAvailability\" /t REG_DWORD /d 1",
//            "REG ADD \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Policies\\Mozilla\\Firefox\" /v \"DisablePrivateBrowsing\" /t REG_DWORD /d 1"};

        SwingUtilities.invokeLater(() -> {
            try {
                boolean darkMode = isDarkModeEnabled();
                if (darkMode) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } else {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                }
            } catch (UnsupportedLookAndFeelException e) {
            }
        });
        try {
            //archivo en formato json el cual contiene credenciales de conexion a la base de datos
            InputStream serviceAccount = Alistamiento.class.getClassLoader().getResourceAsStream("alistamiento.json");
            if (serviceAccount == null) {
                System.out.println("❌ No se encontró el archivo alistamiento.json en los recursos.");
                return;
            }

            // Configurar Firebase con las credenciales
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://alistamiento-f19f7-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
            JOptionPane.showMessageDialog(null,"✅ Firebase inicializado correctamente.");

            // Verificar si FirebaseApp está en ejecución
            if (!FirebaseApp.getApps().isEmpty()) {
                JOptionPane.showMessageDialog(null,"🔥 Firebase está en ejecución.");
            } else {
                JOptionPane.showMessageDialog(null,"⚠️ Firebase no se inició correctamente.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con Firebase:" + e.getMessage());
        }
        interfaz();
        //for (String clave:claves){
        //ejecutarclaves(clave);
        //}
        //activarNet();
    }

    private static boolean isDarkModeEnabled() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return isWindowsDarkMode();
        } else if (os.contains("mac")) {
            return isMacDarkMode();
        }
        return false;
    }

    private static boolean isWindowsDarkMode() {
        return "dark".equals(System.getProperty("sun.java2d.uiScale"));
    }

    private static boolean isMacDarkMode() {
        return System.getProperty("apple.awt.application.appearance", "").contains("dark");
    }
    
    public static void ajustarAnchoColumnas(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();

        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Ancho mínimo

            // Obtener el ancho del encabezado
            TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
            Component headerComp = headerRenderer.getTableCellRendererComponent(table, table.getColumnName(column), false, false, 0, column);
            width = Math.max(headerComp.getPreferredSize().width + 10, width);

            // Obtener el ancho máximo de las filas
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component cellComp = table.prepareRenderer(cellRenderer, row, column);
                width = Math.max(cellComp.getPreferredSize().width + 10, width);
            }

            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    public static void actualizarRuta(String valor, Object nuevoValor) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference docRef = db.collection("rutas");
        Query query = docRef.whereEqualTo("Programa", valor);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documentos = querySnapshot.get().getDocuments();
        if (!documentos.isEmpty()) {
            for (QueryDocumentSnapshot document : documentos) {
                String docId = document.getId();  // Obtener el ID del documento encontrado
                // 2️⃣ Actualizar el campo "estado" a "Activo"
                DocumentReference Ref = db.collection("rutas").document(docId);
                Ref.update("Ruta", nuevoValor);
                JOptionPane.showMessageDialog(null, "El campo ha sido actualizado: ID - " + docId + " -> " + document.getData());

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un programa con ese nombre.");
        }
    }
    
    public static void activarNet() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("inserte la usb con el sistema operativo");
            System.out.println("ingrese la letra en la cual esta la memoria");
            String usb = scanner.nextLine();
            String netFrame = "Dism /online /enable-feature /featurename:NetFx3 /All /source:" + usb + ":\\sources\\sxs /LimitAccess";
            String comando = String.format("cmd.exe /c \"%s\"", netFrame);
            Process ejecutarNet = Runtime.getRuntime().exec(comando);
            BufferedReader NetInput = new BufferedReader(new InputStreamReader(ejecutarNet.getInputStream()));
            BufferedReader NetError = new BufferedReader(new InputStreamReader(ejecutarNet.getErrorStream()));
            String linea;
            while ((linea = NetInput.readLine()) != null) {
                System.out.println(linea);
            }
            while ((linea = NetError.readLine()) != null) {
                System.out.println(linea);
            }
            int exitVal = ejecutarNet.waitFor();
            System.out.println("Proceso terminado con codigo:" + exitVal);
        } catch (IOException | InterruptedException e) {
        }
    }

    public static void ejecutarclaves(String clave) {
        try {
            String ejecutarClave = String.format("cmd.exe /c \"%s\"", clave);
            Process proceso = Runtime.getRuntime().exec(ejecutarClave);
            System.out.println("se esta ejecutando el instalador" + ejecutarClave);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader Error = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            while ((linea = Error.readLine()) != null) {
                System.out.println(linea);
            }
            int exitVal = proceso.waitFor();
            System.out.println("Proceso terminado con codigo:" + exitVal);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "No se pudo activar el complemento NetrameWork");
        }
    }

    public static void ejecutarManuales(String manual, String contraseña) {
        try {
            String comando = String.format("cmd.exe /c \"%s\"", manual);
            Process ejecutarManual = Runtime.getRuntime().exec(comando);
            System.out.println("se esta ejecutando el instalador " + comando);
            try (Writer writer = new OutputStreamWriter(ejecutarManual.getOutputStream())) {
                writer.write(contraseña + "\n");
                writer.flush();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(ejecutarManual.getInputStream()));
            BufferedReader Error = new BufferedReader(new InputStreamReader(ejecutarManual.getErrorStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                JOptionPane.showMessageDialog(null, "Ejecucion exitosa:" + linea);
            }
            while ((linea = Error.readLine()) != null) {
                JOptionPane.showMessageDialog(null, "Error al ejecutar el manual: " + linea);
            }
            int exitVal = ejecutarManual.waitFor();
            System.out.println("Proceso terminado con codigo:" + exitVal);
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
