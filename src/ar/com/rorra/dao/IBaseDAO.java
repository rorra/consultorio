package ar.com.rorra.dao;

import ar.com.rorra.exceptions.DBException;

import java.util.ArrayList;
import java.util.Map;

public interface IBaseDAO<T> {
  /**
   * Obtiene un objeto de la base de datos a partir de su id.
   *
   * @param id Id del objeto a obtener
   * @return Objeto de la base de datos
   * @throws DBException
   */
  T get(int id) throws DBException;

  /**
   * Obtiene todos los objetos de la base de datos.
   *
   * @return Lista de objetos de la base de datos
   * @throws DBException
   */
  ArrayList<T> getAll() throws DBException;

  /**
   * Obtiene todos los objetos de la base de datos ordenados por un campo.
   *
   * @return Lista de objetos de la base de datos ordenados por un campo
   * @throws DBException
   */
  ArrayList<T> getAll(String sortField) throws DBException;

  /**
   * Obtiene todos los objetos de la base de datos que cumplien con las condiciones fields.
   *
   * @return Mapa de condiciones para la busqueda
   * @throws DBException
   */
  public ArrayList<T> getAll(Map<String, String> fields) throws DBException;

  /**
   * Guarda un objeto en la base de datos.
   *
   * @param t Objeto a guardar
   * @throws DBException
   */
  T save(T t) throws DBException;

  /**
   * Actualiza un objeto en la base de datos.
   *
   * @param t Objeto a actualizar
   * @throws DBException
   */
  T update(T t) throws DBException;

  /**
   * Elimina un objeto de la base de datos.
   *
   * @param t Objeto a eliminar
   * @throws DBException
   */
  void delete(T t) throws DBException;
}
