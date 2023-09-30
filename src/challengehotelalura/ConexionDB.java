package challengehotelalura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
    // Nombre de la base de datos que se va a utilizar
    String bd = "hotelAlura";

    // URL de conexión a MySQL con el puerto estándar 3306
    String url = "jdbc:mysql://localhost:3306/";

    // Nombre de usuario y contraseña para acceder a la base de datos
    String user = "root";
    String password = "j0s33nr1qu3";

    // Driver de MySQL para la conexión
    String driver = "com.mysql.cj.jdbc.Driver";

    // Objeto de conexión
    Connection cx;

    // Constructor de la clase
    public ConexionDB() {
    }

    // Método para establecer una conexión a la base de datos
    public Connection conectar() {
        try {
            // Cargar el driver de MySQL
            Class.forName(driver);
            
            // Establecer la conexión utilizando la URL, usuario y contraseña
            cx = DriverManager.getConnection(url + bd, user, password);
            
            // Imprimir mensaje de conexión exitosa
            System.out.println("CONEXION CORRECTA A LA BASE DE DATOS " + bd);
            
            // Devolver la conexión para su uso
            return cx;
        } catch (ClassNotFoundException | SQLException ex) {
            // En caso de error, imprimir mensaje de error y registrar la excepción
            System.out.println("NO SE PUDO CONECTAR A LA BASE DE DATOS " + bd);
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            
            // Devolver null para indicar que no se pudo establecer la conexión
            return null;
        }
    }

    // Método principal para probar la conexión
//    
//    public static void main(String[] args) {
//        ConexionDB conexion = new ConexionDB();
//        
//        // Llamar al método conectar para establecer la conexión
//        conexion.conectar();
//    }
}
