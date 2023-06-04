package ar.com.rorra.dao;

import ar.com.rorra.db.DBConnection;
import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.*;
import java.util.*;

/**
 * Implementación del patrón DAO genérico
 *
 * @param <T>
 */
public abstract class BaseDAO<T extends IEntidad> implements IBaseDAO<T> {
  /**
   * Retorna el nombre de la entidad en la base de datos
   *
   * @return Nombre de la entidad en la base de datos
   */
  protected abstract String getEntidad();

  /**
   * Query para obtener una entidad de la base de datos a partir de su id.
   *
   * @return Query para obtener una entidad de la base de datos a partir de su id
   */
  protected String selectById(int id) {
    return "SELECT * FROM " + getEntidad() + " WHERE id = '" + id + "'";
  }

  /**
   * Query para obtener todas las entidades de la base de datos.
   *
   * @return Query para obtener todas las entidades de la base de datos
   */
  protected String selectAll() {
    return "SELECT * FROM " + getEntidad();
  }

  /**
   * Query para obtener todas las entidades de la base de datos.
   *
   * @return Query para obtener todas las entidades de la base de datos
   */
  protected String selectAll(String sortField) {
    return "SELECT * FROM " + getEntidad() + " ORDER BY " + sortField;
  }

  /**
   * Query para insertar una entidad en la base de datos.
   *
   * @param t Entidad a insertar
   * @return Query para insertar una entidad en la base de datos
   */
  protected abstract String saveSQL(T t);

  /**
   * Query para actualizar una entidad en la base de datos.
   *
   * @param t Entidad a actualizar
   * @return Query para actualizar una entidad en la base de datos
   */
  protected abstract String updateSQL(T t);

  /**
   * Query para eliminar una entidad de la base de datos.
   *
   * @param t Entidad a eliminar
   * @return Query para eliminar una entidad de la base de datos
   */
  protected String deleteSQL(T t) {
    return "DELETE FROM " + getEntidad() + " WHERE id = " + t.getId();
  }

  /**
   * Transforma un RecordSet en una entidad
   *
   * @param rs RecordSet a transformar
   * @return Objecto de tipo T
   * @throws DBException
   */
  protected abstract T objectFromRS(ResultSet rs) throws DBException;

  /**
   * Obtiene una entidad de la base de datos a partir de su id.
   *
   * @param id Id de la entidad a obtener
   * @return Entidad de la base de datos
   * @throws DBException
   */
  @Override
  public T get(int id) throws DBException {
    String sql = this.selectById(id);

    Connection connection = DBConnection.getInstance().getConnection();
    T entidad = null;
    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        entidad = objectFromRS(rs);
      }
    } catch (SQLException ex) {
      try {
        connection.rollback();
      } catch (SQLException exx) {
      }
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }

    return entidad;
  }

  /**
   * Obtiene todas las entidades de la base de datos.
   *
   * @return Lista de entidades
   * @throws DBException
   */
  @Override
  public ArrayList<T> getAll() throws DBException {
    String sql = this.selectAll();
    return fetchAllByQuery(sql);
  }

  /**
   * Obtiene todas las entidades de la base de datos ordenados por un campo.
   *
   * @return Lista de entidades
   * @throws DBException
   */
  @Override
  public ArrayList<T> getAll(String sortField) throws DBException {
    String sql = this.selectAll(sortField);
    return fetchAllByQuery(sql);
  }

  /**
   * Obtiene todas las entidades de la base de datos ordenados por un campo.
   *
   * @return Lista de entidades
   * @throws DBException
   */
  @Override
  public ArrayList<T> getAll(Map<String, String> fields) throws DBException {
    String sql = this.selectAll();

    if (fields != null) {
      sql += " WHERE ";
      for (Map.Entry<String,String> entry : fields.entrySet()) {
        sql += entry.getKey() + " = '" + entry.getValue() + "' AND ";
      }
      sql = sql.substring(0, sql.length() - 5);
    }

    return fetchAllByQuery(sql);
  }

  /**
   * Guarda una entidad en la base de datos.
   *
   * @param entidad Entidad a guardar
   * @throws DBException
   */
  @Override
  public T save(T entidad) throws DBException {
    String sql = saveSQL(entidad);
    return store(sql);
  }

  /**
   * Actualiza una entidad en la base de datos.
   *
   * @param entidad Entidad a actualizar
   * @throws DBException
   */
  @Override
  public T update(T entidad) throws DBException {
    String sql = updateSQL(entidad);
    return store(sql);
  }

  /**
   * Ejecuta una query de insert o update y retorna la entidad actualizada
   *
   * @param sql query para insertar/updatear datos
   * @return Entidad creada o actualizada
   * @throws DBException
   */
  protected T store(String sql) throws DBException {
    Connection connection = DBConnection.getInstance().getConnection();
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.executeUpdate();
      connection.commit();
      ResultSet generatedKeys = stmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        int key = generatedKeys.getInt(1);
        return get(key);
      }
      return null;
    } catch (SQLException ex) {
      try {
        connection.rollback();
      } catch (SQLException exx) {
      }
      throw new DBException(DBExceptionType.TRANSACTION_ERROR, ex.getMessage());
    }
  }

  /**
   * Elimina una entidad de la base de datos.
   *
   * @param entidad Entidad a eliminar
   * @throws DBException
   */
  @Override
  public void delete(T entidad) throws DBException {
    String sql = deleteSQL(entidad);

    Connection connection = DBConnection.getInstance().getConnection();
    try {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate(sql);
      connection.commit();
    } catch (SQLException ex) {
      try {
        connection.rollback();
      } catch (SQLException exx) {
      }
      throw new DBException(DBExceptionType.TRANSACTION_ERROR, ex.getMessage());
    }
  }

  /**
   * Busca una entidad en la base de datos a partir de un campo y un valor.
   *
   * @param field Campo a buscar
   * @param value Valor a buscar
   * @return Entidad de la base de datos
   * @throws DBException
   */
  public T getByField(String field, String value) throws DBException {
    String sql = "SELECT * FROM " + this.getEntidad() + " WHERE " + field + " = '" + value + "'";

    return fetchOneByQuery(sql);
  }

  /**
   * Busca una entidad en la base de datos a partir de varios campos con valores.
   *
   * @param fields Diccionario de campos y valores a buscar
   * @return Entidad de la base de datos
   * @throws DBException
   */
  public T getByField(Map<String, String> fields) throws DBException {
    String sql = "SELECT * FROM " + this.getEntidad() + " WHERE ";
    for (Map.Entry<String,String> entry : fields.entrySet()) {
      sql += entry.getKey() + " = '" + entry.getValue() + "' AND ";
    }
    sql = sql.substring(0, sql.length() - 5);

    return fetchOneByQuery(sql);
  }

  protected T fetchOneByQuery(String sql) {
    Connection connection = DBConnection.getInstance().getConnection();
    T entidad = null;
    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        entidad = objectFromRS(rs);
      }
    } catch (SQLException ex) {
      try {
        connection.rollback();
      } catch (SQLException exx) {
      }
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }

    return entidad;
  }

  protected ArrayList<T> fetchAllByQuery(String sql) {
    Connection connection = DBConnection.getInstance().getConnection();
    ArrayList<T> lista = new ArrayList<>();
    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        lista.add(objectFromRS(rs));
      }
    } catch (SQLException ex) {
      try {
        connection.rollback();
      } catch (SQLException exx) {
      }
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }

    return lista;
  }
}
