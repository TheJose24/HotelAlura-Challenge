package challengehotelalura;

import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistroReservas extends javax.swing.JFrame {

    private JDateChooser dateChooserIngreso;
    private JDateChooser dateChooserSalida;
    // Objeto para gestionar la conexión a la base de datos
    ConexionDB conexion;

    public RegistroReservas() {
        initComponents();

        dateChooserIngreso = new JDateChooser();
        dateChooserIngreso.setDateFormatString("dd/MM/yyyy");
        lblFechaIngreso.add(dateChooserIngreso);
        dateChooserIngreso.setBounds(0, 0, 213, 30);

        dateChooserSalida = new JDateChooser();
        dateChooserSalida.setDateFormatString("dd/MM/yyyy");
        lblFechaSalida.add(dateChooserSalida);
        dateChooserSalida.setBounds(0, 0, 213, 30);

        // Agregar un PropertyChangeListener al dateChooserSalida
        dateChooserSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    // Lógica de cálculo cuando se selecciona una fecha de salida
                    calcularCostoEstadia();
                }
            }
        });

        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/reservaLogo.png"));
        lblLogo.setIcon(new ImageIcon(img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));

        // Inicialización del objeto de conexión a la base de datos y establecimiento de la conexión
        conexion = new ConexionDB();
        //conexion = new ConexionSQLServer();
        conexion.conectar();
    }

    double valorFinal;

    private void calcularCostoEstadia() {
        java.util.Date fechaUtilIngreso = dateChooserIngreso.getDate();
        java.sql.Date fechaIngreso = new java.sql.Date(fechaUtilIngreso.getTime());

        java.util.Date fechaUtilSalida = dateChooserSalida.getDate();
        fechaUtilSalida = fechaUtilSalida != null ? fechaUtilSalida : fechaUtilIngreso;

        java.sql.Date fechaSalida = new java.sql.Date(fechaUtilSalida.getTime());

        // Convertir las fechas a objetos LocalDate
        LocalDate fechaLocalIngreso = fechaIngreso.toLocalDate();
        LocalDate fechaLocalSalida = fechaSalida.toLocalDate();

        // Calcular la diferencia en días
        long diasDeEstadia = ChronoUnit.DAYS.between(fechaLocalIngreso, fechaLocalSalida);

        // Definir la tarifa diaria
        double tarifaDiaria = 50.0;

        // Calcular el costo total
        valorFinal = diasDeEstadia * tarifaDiaria;

        // Convertir el valor a una cadena (String)
        String valorTexto = String.valueOf(valorFinal);

        txtValorReserva.setText(valorTexto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFechaIngreso = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFechaSalida = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtValorReserva = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        tipoPago = new javax.swing.JComboBox<>();
        btnSiguienteRegistro = new javax.swing.JButton();
        imgPanel = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("SISTEMA DE RESERVAS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("FECHA DE CHECK IN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("FECHA DE CHECK OUT");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("VALOR DE LA RESERVA");

        txtValorReserva.setEditable(false);
        txtValorReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorReserva.setFocusable(false);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("FORMA DE PAGO");

        tipoPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tarjeta de credito", "Tarjeta de debito", "Dinero en efectivo" }));

        btnSiguienteRegistro.setText("SIGUIENTE");
        btnSiguienteRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSiguienteRegistro)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(lblFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(lblFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(txtValorReserva)
                                .addComponent(jSeparator1)
                                .addComponent(tipoPago, 0, 213, Short.MAX_VALUE)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnSiguienteRegistro)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        imgPanel.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout imgPanelLayout = new javax.swing.GroupLayout(imgPanel);
        imgPanel.setLayout(imgPanelLayout);
        imgPanelLayout.setHorizontalGroup(
            imgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
        );
        imgPanelLayout.setVerticalGroup(
            imgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(imgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteRegistroActionPerformed
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtener la fecha de ingreso
            java.util.Date fechaUtilIngreso = dateChooserIngreso.getDate();
            java.util.Date fechaUtilSalida = dateChooserSalida.getDate();
            if (fechaUtilIngreso == null || fechaUtilSalida == null) {
                JOptionPane.showMessageDialog(this, "Por favor complete los datos.");
                return; // Salir del método si la fecha de ingreso es nula
            }
            java.sql.Date fechaIngreso = new java.sql.Date(fechaUtilIngreso.getTime());

            // Obtener la fecha de salida
            
            java.sql.Date fechaSalida = new java.sql.Date(fechaUtilSalida.getTime());

            // obtener la forma de pago
            Object pagoSelecionado = tipoPago.getSelectedItem();
            String formaPago = pagoSelecionado.toString();

            // Obtener el valor
            double valor = valorFinal;

            // Preparar la consulta SQL con parámetros para evitar SQL injection
            String query = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?,?,?,?)";
            connection = conexion.conectar();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, fechaIngreso);
            preparedStatement.setDate(2, fechaSalida);
            preparedStatement.setDouble(3, valor);
            preparedStatement.setString(4, formaPago);

            // Ejecutar la consulta SQL
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) { // Mover el cursor al primer registro
                    int idGenerado = generatedKeys.getInt(1);
                    System.out.println(idGenerado);
                    // Cerrar la conexión y el PreparedStatement
                    generatedKeys.close();
                    preparedStatement.close();

                    JOptionPane.showMessageDialog(rootPane, "Reserva Exitosa");
                    this.setVisible(false);
                    RegistroHuesped registroHuesped = new RegistroHuesped(idGenerado);
                    registroHuesped.setVisible(true);
                } else {
                    // Mostrar mensaje de error si no se encontró el ID generado
                    JOptionPane.showMessageDialog(this, "No se pudo obtener el ID generado.");
                }
            } else {
                // Mostrar mensaje de error si no se insertaron filas
                JOptionPane.showMessageDialog(this, "Ocurrió un error al insertar la reserva.");
            }
        } catch (SQLException ex) {
            // Manejar errores de base de datos y mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage());
        } finally {
            // Cerrar la conexión, el PreparedStatement y el ResultSet en un bloque finally
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnSiguienteRegistroActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguienteRegistro;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFechaSalida;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JComboBox<String> tipoPago;
    private javax.swing.JTextField txtValorReserva;
    // End of variables declaration//GEN-END:variables
}
