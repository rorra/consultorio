package ar.com.rorra.bo;

import ar.com.rorra.dao.BaseDAO;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.BOException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Clase genérica para implementar la lógica de negocios para las diferentes entidades
 * El tipo ENTIDAD tiene que implementar la interfaz IEntidad
 * El tipo DAO implementa la class BaseDAO para la ENTIDAD con la que se trabaja
 * Es decir, si se define una clase para la ENTIDAD Doctor, entonces el DAO tiene que implementar
 * la interfaz BaseDAO<Doctor>, es decir, la interfaz IDoctorDAO, que en definitiva es la clase
 * DoctorDAO (o alguna otra que implemente BaseDAO<Doctor>, si la hubiese.
 * <p>
 * El controlador va a trabajar contra los business objects, que encapsula el comportamiento de las
 * entidades y de sus respectivos DAO.
 *
 * @param <ENTIDAD> Entidad a utilizar
 * @param <DAO>     DAO a utilizar
 */
public abstract class BaseBO<ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> {
  protected DAO dao;

  /**
   * Constructor
   *
   * @param dao DAO a utilizar
   */
  public BaseBO(DAO dao) {
    this.dao = dao;
  }

  /**
   * Inserta una entidad en el dispositivo de almacenamiento
   *
   * @param entidad Entidad a insertar
   * @return Entidad insertada
   * @throws Exception
   */
  public ENTIDAD save(ENTIDAD entidad) throws BOException {
    validar(entidad);
    try {
      return dao.save(entidad);
    } catch (Exception ex) {
      throw new BOException("Error al insertar la entidad en la base de datos: " + ex.getMessage());
    }
  }

  public ENTIDAD update(ENTIDAD entidad) throws BOException {
    validar(entidad);
    try {
      return dao.update(entidad);
    } catch (DBException e) {
      throw new BOException("Error al insertar la entidad en la base de datos: " + e.getMessage());
    }
  }

  public void delete(ENTIDAD entidad) throws BOException {
    try {
      dao.delete(entidad);
    } catch (DBException e) {
      throw new BOException("Error al eliminar la entidad en la base de datos: " + e.getMessage());
    }
  }

  public ENTIDAD getById(int id) throws BOException {
    try {
      return dao.get(id);
    } catch (DBException e) {
      throw new BOException("Error al obtener la entidad de base de datos: " + e.getMessage());
    }
  }

  public ArrayList<ENTIDAD> getAll() throws BOException {
    try {
      return dao.getAll();
    } catch (DBException e) {
      throw new BOException("Error al obtener las entidades de base de datos: " + e.getMessage());
    }
  }

  public ArrayList<ENTIDAD> getAll(String sortField) throws BOException {
    try {
      return dao.getAll(sortField);
    } catch (DBException e) {
      throw new BOException("Error al obtener las entidades de base de datos: " + e.getMessage());
    }
  }

  public ArrayList<ENTIDAD> getAll(Map<String, String> conditions) throws BOException {
    try {
      return dao.getAll(conditions);
    } catch (DBException e) {
      throw new BOException("Error al obtener las entidades de base de datos: " + e.getMessage());
    }
  }

  /**
   * Valida la entidad
   *
   * @param entidad Entidad a validar
   * @throws Exception
   */
  public abstract void validar(ENTIDAD entidad) throws BOException;
}
