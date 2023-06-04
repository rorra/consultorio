package ar.com.rorra.db;

import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manager para la conexión compartida a la base de datos mediante implementación del patrón Singleton
 */
public class DBConnection {
  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./consultorio";
  private static final String DB_USERNAME = "sa";
  private static final String DB_PASSWORD = "";

  private static DBConnection instance;
  private static Connection connection;

  /**
   * Abre una conexión compartida a la base de datos. Método privado del patrón Singleton
   *
   * @throws SQLException
   */
  private DBConnection() throws DBException {
    try {
      Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
      throw new DBException(DBExceptionType.DRIVER_NOT_FOUND, "Error al registrar el driver de SQL Server: " + e);
    }

    try {
      connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      connection.setAutoCommit(false);
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.CONNECTION_ERROR, "Error al conectar con la base de datos: " + ex);
    }
  }

  /**
   * Retorna la conexión a la base de datos
   *
   * @return Conexión a la base de datos
   */
  public Connection getConnection() {
    return connection;
  }

  /**
   * Implementador del patrón Singleton.
   * Construye una única instancia para todo el sistema, que representa una conexión única a la base de datos.
   *
   * @return Única instancia con una conexión a la base de datos
   * @throws SQLException
   */
  public static DBConnection getInstance() throws DBException {
    try {
      if (instance == null) {
        instance = new DBConnection();
      } else if (instance.getConnection().isClosed()) {
        instance = new DBConnection();
      }
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.CONNECTION_ERROR, "Error al obtener la conexión con la base de datos: " + ex);
    }

    return instance;
  }

  /**
   * Cierra la conexión a la base de datos
   *
   * @throws DBException
   */
  public static void close() throws DBException {
    if (instance == null) {
      return;
    }

    try {
      connection.close();
    } catch (SQLException e) {
      throw new DBException(DBExceptionType.CONNECTION_ERROR, "Error al cerrar la conexión: " + e);
    }
  }
}
