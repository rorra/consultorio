package ar.com.rorra.controlador;

import ar.com.rorra.bo.*;
import ar.com.rorra.dao.BaseDAO;
import ar.com.rorra.entidad.*;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.ui.FramePrincipal;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Controlador principal de la aplicación.
 *
 * Siguiendo la metodología de BaseDAO, definimos funciones parametrizadas para las acciones de insertar, modificar,
 * listar y eliminar las diferentes entidades.
 *
 * Las Entidades, que es el modelo puro, implementa la interfaza IEntidad.
 *
 * Los DAOs, que son los que se comunican con la base de datos, implementan la interfaz BaseDAO<ENTIDAD>.
 *
 * Los BOs, que son los que encapsulan la lógica de negocios, implementan la clase BaseBO<ENTIDAD, DAO>.
 */
public class Controlador {
  private FramePrincipal framePrincipal;
  private AdministradorBO administradorBO;
  private ConsultorioBO consultorioBO;
  private DoctorBO doctorBO;
  private ObraSocialBO obraSocialBO;
  private PacienteBO pacienteBO;
  private TurnoBO turnoBO;

  public Controlador() {
    administradorBO = new AdministradorBO();
    consultorioBO = new ConsultorioBO();
    doctorBO = new DoctorBO();
    obraSocialBO = new ObraSocialBO();
    pacienteBO = new PacienteBO();
    turnoBO = new TurnoBO();
  }

  /**
   * Inicia la aplicación.
   */
  public void run() {
    SwingUtilities.invokeLater(() -> {
      framePrincipal = new FramePrincipal(this);
    });
  }

  /**
   * Devuelve el frame principal de la aplicación.
   * @return
   */
  public FramePrincipal getFramePrincipal() {
    return framePrincipal;
  }

  /**
   * Devuelve una lista de entidadedes de la base de datos
   * @param bo BO que se encarga de la entidad
   * @return Lista de entidades
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO> El BO implementa la interfaz DOA
   */
  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> List<ENTIDAD> getEntidades(BaseBO<ENTIDAD, DAO> bo) {
    try {
      return bo.getAll();
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return null;
    }
  }

  /**
   * Devuelve una lista de entidadedes de la base de datos ordenadas por un campo
   * @param bo BO que se encarga de la entidad
   * @param sortField Campo por el que se ordena
   * @return Lista de entidades
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO> El BO implementa la interfaz DOA
   */
  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> List<ENTIDAD> getEntidades(BaseBO<ENTIDAD, DAO> bo, String sortField) {
    try {
      return bo.getAll(sortField);
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return null;
    }
  }

  /**
   * Devuelve una lista de entidadedes de la base de datos que cumplen con las condiciones
   * @param bo BO que se encarga de la entidad
   * @param conditions Condiciones que deben cumplir las entidades
   * @return Lista de entidades
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO> El BO implementa la interfaz DOA
   */
  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> List<ENTIDAD> getEntidades(BaseBO<ENTIDAD, DAO> bo, Map<String, String> conditions) {
    try {
      return bo.getAll(conditions);
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return null;
    }
  }

  /**
   * Guarda una entidad en la base de datos
   * @param bo BO que se encarga de la entidad
   * @param entity Entidad que se quiere guardar
   * @param successMessage Mensaje de éxito
   * @return true si se guardó correctamente, false si no
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO> El BO implementa la interfaz DOA
   */
  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> boolean saveEntidad(BaseBO<ENTIDAD, DAO> bo, ENTIDAD entity, String successMessage) {
    try {
      bo.save(entity);
      framePrincipal.visualizarInformacion(successMessage);
      return true;
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return false;
    }
  }

  /**
   * Modifica una entidad en la base de datos
   * @param bo BO que se encarga de la entidad
   * @param entity Entidad que se quiere modificar
   * @param successMessage Mensaje de éxito
   * @return
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO> El BO implementa la interfaz DOA
   */
  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> boolean updateEntidad(BaseBO<ENTIDAD, DAO> bo, ENTIDAD entity, String successMessage) {
    try {
      bo.update(entity);
      framePrincipal.visualizarInformacion(successMessage);
      return true;
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return false;
    }
  }

  /**
   * Elimina una entidad de la base de datos
   * @param bo BO que se encarga de la entidad
   * @param entity Entidad que se quiere eliminar
   * @param confirmMessage Mensaje de confirmación
   * @param successMessage Mensaje de éxito
   * @return
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO> El BO implementa la interfaz DOA
   */
  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> boolean deleteEntidad(BaseBO<ENTIDAD, DAO> bo, ENTIDAD entity, String confirmMessage, String successMessage) {
    try {
      int opcion = JOptionPane.showConfirmDialog(framePrincipal, confirmMessage + entity.toString() + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
      if (opcion == JOptionPane.YES_OPTION) {
        bo.delete(entity);
        framePrincipal.visualizarInformacion(successMessage);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      framePrincipal.visualizarError(e);
      return false;
    }
  }

  /**
   * Función parametrizada para listar las entidades.
   * @param klass Clase de la entidad a listar.
   * @param sortField Campo por el cual se ordenará la lista.
   * @return Lista de entidades.
   * @param <T> Clase de la entidad a listar.
   */
  public <T extends IEntidad> List<T> listarEntidades(Class klass, String sortField) {
    if (klass == Administrador.class) {
      return (List<T>) getEntidades(administradorBO);
    } else if (klass == Consultorio.class) {
      return (List<T>) getEntidades(consultorioBO);
    } else if (klass == Doctor.class) {
      return (List<T>) getEntidades(doctorBO);
    } else if (klass == ObraSocial.class) {
      return (List<T>) getEntidades(obraSocialBO);
    } else if (klass == Paciente.class) {
      return (List<T>) getEntidades(pacienteBO);
    } else if (klass == Turno.class) {
      return (List<T>) getEntidades(turnoBO, sortField);
    }

    return null;
  }

  /**
   * Función parametrizada para listar las entidades.
   * @param klass Clase de la entidad a listar.
   * @param filter Filtro para la lista.
   * @return Lista de entidades.
   * @param <T> Clase de la entidad a listar.
   */
  public <T extends IEntidad> List<T> listarEntidades(Class klass, IEntidad filter) {
    if (klass == Doctor.class) {
      if (filter.getClass() == Consultorio.class) {
        Map<String, String> conditions = Map.of("consultorio_id", Integer.toString(filter.getId()));
        return (List<T>) getEntidades(doctorBO, conditions);
      }
    }
    return null;
  }

  /**
   * Función parametrizada para listar las entidades.
   * @param klass Clase de la entidad a listar.
   * @return Lista de entidades.
   * @param <T> Clase de la entidad a listar.
   */
  public <T extends IEntidad> List<T> listarEntidades(Class klass) {
    return listarEntidades(klass, (String) null);
  }

  /**
   * Inserta una entidad en la base de datos
   * @param entidad Entidad a insertar
   * @return true si se insertó correctamente, false en caso contrario
   */
  public boolean insertarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      return saveEntidad(administradorBO, (Administrador)entidad, "Se creó el administrador.");
    } else if (entidad instanceof Consultorio) {
      return saveEntidad(consultorioBO, (Consultorio)entidad, "Se creó el consultorio.");
    } else if (entidad instanceof Doctor) {
      return saveEntidad(doctorBO, (Doctor)entidad, "Se creó el doctor.");
    } else if (entidad instanceof ObraSocial) {
      return saveEntidad(obraSocialBO, (ObraSocial)entidad, "Se creó la obra social.");
    } else if (entidad instanceof Paciente) {
      return saveEntidad(pacienteBO, (Paciente)entidad, "Se creó el paciente.");
    } else if (entidad instanceof Turno) {
      return saveEntidad(turnoBO, (Turno)entidad, "Se creó el turno.");
    }
    return false;
  }

  /**
   * Modifica una entidad en la base de datos
   * @param entidad Entidad a modificar
   * @return true si se modificó correctamente, false en caso contrario
   */
  public boolean modificarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      return updateEntidad(administradorBO, (Administrador)entidad, "Se modificó el administrador.");
    } else if (entidad instanceof Consultorio) {
      return updateEntidad(consultorioBO, (Consultorio)entidad, "Se modificó el consultorio.");
    } else if (entidad instanceof Doctor) {
      return updateEntidad(doctorBO, (Doctor)entidad, "Se modificó el doctor.");
    } else if (entidad instanceof ObraSocial) {
      return updateEntidad(obraSocialBO, (ObraSocial)entidad, "Se modificó la obra social.");
    } else if (entidad instanceof Paciente) {
      return updateEntidad(pacienteBO, (Paciente)entidad, "Se modificó el paciente.");
    } else if (entidad instanceof Turno) {
      return updateEntidad(turnoBO, (Turno)entidad, "Se modificó el turno.");
    }
    return false;
  }

  /**
   * Elimina una entidad en la base de datos
   * @param entidad Entidad a eliminar
   * @return true si se eliminó correctamente, false en caso contrario
   */
  public boolean eliminarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      return deleteEntidad(administradorBO, (Administrador)entidad, "Eliminar el administrador ", "Se eliminó el administrador.");
    } else if (entidad instanceof Consultorio) {
      return deleteEntidad(consultorioBO, (Consultorio)entidad, "Eliminar el consultorio ", "Se eliminó el consultorio.");
    } else if (entidad instanceof Doctor) {
      return deleteEntidad(doctorBO, (Doctor)entidad, "Eliminar el doctor ", "Se eliminó el doctor.");
    } else if (entidad instanceof ObraSocial) {
      return deleteEntidad(obraSocialBO, (ObraSocial)entidad, "Eliminar la obra social ", "Se eliminó la obra social.");
    } else if (entidad instanceof Paciente) {
      return deleteEntidad(pacienteBO, (Paciente)entidad, "Eliminar el paciente ", "Se eliminó el paciente.");
    } else if (entidad instanceof Turno) {
      return deleteEntidad(turnoBO, (Turno)entidad, "Eliminar el turno ", "Se eliminó el turno.");
    }
    return false;
  }
}