package challengehotelalura;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SistemaBusqueda extends javax.swing.JFrame {

    // Objeto para gestionar la conexión a la base de datos
    ConexionDB conexion;

    public SistemaBusqueda() {
        initComponents();

        // Inicialización del objeto de conexión a la base de datos
        conexion = new ConexionDB();

        // Establecimiento de la conexión
        conexion.conectar();

        // Cargar los datos en la tabla de huéspedes
        mostrarDatosEnTabla(tblHuespedes, "huespedes");

        // Cargar los datos en la tabla de reservas (agregar una nueva pestaña)
        mostrarDatosEnTabla(tblReservas, "reservas");
    }

    private void mostrarDatosEnTabla(javax.swing.JTable tabla, String nombreTabla) {
        // Crear un DefaultTableModel para almacenar los datos de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        if (nombreTabla.equals("huespedes")) {
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Fecha de Nacimiento");
            modelo.addColumn("Nacionalidad");
            modelo.addColumn("Telefono");
            modelo.addColumn("Numero de Reserva");
        } else if (nombreTabla.equals("reservas")) {
            modelo.addColumn("ID");
            modelo.addColumn("Fecha de Entrada");
            modelo.addColumn("Fecha de Salida");
            modelo.addColumn("Valor");
            modelo.addColumn("Forma de Pago");
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = conexion.conectar();
            statement = connection.createStatement();
            // Preparar la consulta SQL con parámetros para evitar SQL injection
            String query = "SELECT * FROM " + nombreTabla;
            resultSet = statement.executeQuery(query); // Ejecutar la consulta SQL

            // Recorrer los resultados y agregarlos al modelo de la tabla
            while (resultSet.next()) {
                if (nombreTabla.equals("huespedes")) {
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                    String nacionalidad = resultSet.getString("nacionalidad");
                    String telefono = resultSet.getString("telefono");
                    int numeroReserva = resultSet.getInt("id_reserva"); // Cambiar el nombre de la columna según corresponda

                    modelo.addRow(new Object[]{nombre, apellido, fechaNacimiento, nacionalidad, telefono, numeroReserva});
                } else if (nombreTabla.equals("reservas")) {
                    int id = resultSet.getInt("id");
                    String fechaEntrada = resultSet.getString("fecha_entrada");
                    String fechaSalida = resultSet.getString("fecha_salida");
                    double valor = resultSet.getDouble("valor");
                    String formaPago = resultSet.getString("forma_pago");

                    modelo.addRow(new Object[]{id, fechaEntrada, fechaSalida, valor, formaPago});
                }
            }

            // Asignar el modelo a la tabla correspondiente
            tabla.setModel(modelo);
        } catch (SQLException e) {
            // Manejar errores de base de datos
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y los recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHuespedes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("SISTEMA DE BUSQUEDA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 32, -1, 35));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoLogin.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        tblHuespedes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHuespedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Telefono", "Numero de Reserva"
            }
        ));
        jScrollPane1.setViewportView(tblHuespedes);

        jTabbedPane1.addTab("Huespedes", jScrollPane1);

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblReservas);

        jTabbedPane1.addTab("Reservas", jScrollPane2);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 680, 260));

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 423, 80, 30));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 423, 90, 30));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 423, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tblHuespedes.getSelectedRow();
        int filaSeleccionada2 = tblReservas.getSelectedRow();
        if (filaSeleccionada != -1) {

            // Obtener los datos de la fila seleccionada y abrir un formulario de edición
            String nombre = tblHuespedes.getValueAt(filaSeleccionada, 0).toString();
            String apellido = tblHuespedes.getValueAt(filaSeleccionada, 1).toString();

            // Obtener la fecha de la fila seleccionada
            String fechaString = tblHuespedes.getValueAt(filaSeleccionada, 2).toString();
            java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaString);

            String nacionalidad = tblHuespedes.getValueAt(filaSeleccionada, 3).toString();
            String telefono = tblHuespedes.getValueAt(filaSeleccionada, 4).toString();
            String reserva = tblHuespedes.getValueAt(filaSeleccionada, 5).toString();
            int numeroReserva = Integer.parseInt(reserva);
            System.out.println(nombre + " " + apellido + " " + fechaNacimiento + " " + nacionalidad
                    + " " + telefono + " " + reserva);

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = conexion.conectar();

                // Preparar la sentencia SQL de actualización
                String query = "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ? WHERE id_reserva = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, apellido);
                preparedStatement.setDate(3, fechaNacimiento);
                preparedStatement.setString(4, nacionalidad);
                preparedStatement.setString(5, telefono);
                preparedStatement.setInt(6, numeroReserva);

                // Ejecutar la sentencia SQL de actualización
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    // Éxito: se actualizó al menos una fila
                    JOptionPane.showMessageDialog(null, "Registro actualizado con éxito.");

                    // Después de actualizar, puedes volver a cargar los datos en la tabla para reflejar los cambios
                    mostrarDatosEnTabla(tblHuespedes, "huespedes");
                } else {
                    // No se actualizó ninguna fila
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro.");
                }
            } catch (SQLException e) {
                // Manejar errores de base de datos
                e.printStackTrace();
            } finally {
                // Cerrar la conexión y los recursos
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (filaSeleccionada2 != -1) {

            // Obtener los datos de la fila seleccionada y abrir un formulario de edición
            String idString = tblReservas.getValueAt(filaSeleccionada2, 0).toString();
            int id = Integer.parseInt(idString);

            // Obtener la fecha de entrada
            String fechaStringEntrada = tblReservas.getValueAt(filaSeleccionada2, 1).toString();
            java.sql.Date fechaEntrada = java.sql.Date.valueOf(fechaStringEntrada);

            // obtener la fecha de salida
            String fechaStringSalida = tblReservas.getValueAt(filaSeleccionada2, 2).toString();
            java.sql.Date fechaSalida = java.sql.Date.valueOf(fechaStringSalida);

            String valorString = tblReservas.getValueAt(filaSeleccionada2, 3).toString();
            double valor = Double.parseDouble(valorString);

            String formaPago = tblReservas.getValueAt(filaSeleccionada2, 4).toString();
            System.out.println(fechaEntrada + " " + fechaSalida + " " + valor + " " + formaPago);

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = conexion.conectar();

                // Preparar la sentencia SQL de actualización
                String query = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, fechaEntrada);
                preparedStatement.setDate(2, fechaSalida);
                preparedStatement.setDouble(3, valor);
                preparedStatement.setString(4, formaPago);
                preparedStatement.setInt(5, id);

                // Ejecutar la sentencia SQL de actualización
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    // Éxito: se actualizó al menos una fila
                    JOptionPane.showMessageDialog(null, "Registro actualizado con éxito.");

                    // Después de actualizar, puedes volver a cargar los datos en la tabla para reflejar los cambios
                    mostrarDatosEnTabla(tblReservas, "reservas");
                } else {
                    // No se actualizó ninguna fila
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro.");
                }
            } catch (SQLException e) {
                // Manejar errores de base de datos
                e.printStackTrace();
            } finally {
                // Cerrar la conexión y los recursos
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblHuespedes.getSelectedRow();
        int filaSeleccionada2 = tblReservas.getSelectedRow();

        if (filaSeleccionada != -1) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar esta fila de huéspedes?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtener clave
                String reserva = tblHuespedes.getValueAt(filaSeleccionada, 5).toString();
                int numeroReserva = Integer.parseInt(reserva);

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    connection = conexion.conectar();

                    // Preparar la sentencia SQL de eliminación
                    String query = "DELETE FROM huespedes WHERE id_reserva = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, numeroReserva);

                    // Ejecutar la sentencia SQL de eliminación
                    int filasEliminadas = preparedStatement.executeUpdate();

                    if (filasEliminadas > 0) {
                        // Éxito: se eliminó al menos una fila
                        JOptionPane.showMessageDialog(null, "Registro de huéspedes eliminado con éxito.");

                        // Después de eliminar, puedes volver a cargar los datos en la tabla para reflejar los cambios
                        mostrarDatosEnTabla(tblHuespedes, "huespedes");
                    } else {
                        // No se eliminó ninguna fila
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro de huéspedes.");
                    }
                } catch (SQLException e) {
                    // Manejar errores de base de datos
                    e.printStackTrace();
                } finally {
                    // Cerrar la conexión y los recursos
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (filaSeleccionada2 != -1) {
            int confirmacion2 = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar esta fila de reservas?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion2 == JOptionPane.YES_OPTION) {
                // Obtener clave
                String idString = tblReservas.getValueAt(filaSeleccionada2, 0).toString();
                int id = Integer.parseInt(idString);

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    connection = conexion.conectar();

                    // Preparar la sentencia SQL de eliminación
                    String query = "DELETE FROM reservas WHERE id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id);

                    // Ejecutar la sentencia SQL de eliminación
                    int filasEliminadas = preparedStatement.executeUpdate();

                    if (filasEliminadas > 0) {
                        // Éxito: se eliminó al menos una fila
                        JOptionPane.showMessageDialog(null, "Registro de reservas eliminado con éxito.");

                        // Después de eliminar, puedes volver a cargar los datos en la tabla para reflejar los cambios
                        mostrarDatosEnTabla(tblReservas, "reservas");
                    } else {
                        // No se eliminó ninguna fila
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro de reservas.");
                    }
                } catch (SQLException e) {
                    // Manejar errores de base de datos
                    e.printStackTrace();
                } finally {
                    // Cerrar la conexión y los recursos
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
        MenuUsuario menu = new MenuUsuario();
        menu.setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SistemaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaBusqueda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblHuespedes;
    private javax.swing.JTable tblReservas;
    // End of variables declaration//GEN-END:variables
}
