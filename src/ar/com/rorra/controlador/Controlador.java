package ar.com.rorra.controlador;

import ar.com.rorra.bo.*;
import ar.com.rorra.dao.BaseDAO;
import ar.com.rorra.entidad.*;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.ui.FramePrincipal;

import javax.swing.*;
import java.util.List;

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

  public void run() {
    SwingUtilities.invokeLater(() -> {
      framePrincipal = new FramePrincipal(this);
    });
  }

  public FramePrincipal getFramePrincipal() {
    return framePrincipal;
  }

  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> List<ENTIDAD> getEntidades(BaseBO<ENTIDAD, DAO> bo) {
    try {
      return bo.getAll();
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return null;
    }
  }

  public <ENTIDAD extends IEntidad, DAO extends BaseDAO<ENTIDAD>> List<ENTIDAD> getEntidades(BaseBO<ENTIDAD, DAO> bo, String sortField) {
    try {
      return bo.getAll(sortField);
    } catch (BOException e) {
      framePrincipal.visualizarError(e);
      return null;
    }
  }


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

  public List<Administrador> listarAdministradores() {
    return getEntidades(administradorBO);
  }

  public boolean insertarAdministrador(Administrador administrador) {
    return saveEntidad(administradorBO, administrador, "Se creó el administrador.");
  }

  public boolean modificarAdministrador(Administrador administrador) {
    return updateEntidad(administradorBO, administrador, "Se modificó el administrador.");
  }

  public boolean eliminarAdministrador(Administrador administrador) {
    return deleteEntidad(administradorBO, administrador, "Eliminar el administrador ", "Se eliminó el administrador.");
  }

  public List<Consultorio> listarConsultorios() {
    return getEntidades(consultorioBO);
  }

  public boolean insertarConsultorio(Consultorio consultorio) {
    return saveEntidad(consultorioBO, consultorio, "Se creó el consultorio.");
  }

  public boolean modificarConsultorio(Consultorio consultorio) {
    return updateEntidad(consultorioBO, consultorio, "Se modificó el consultorio.");
  }

  public boolean eliminarConsultorio(Consultorio consultorio) {
    return deleteEntidad(consultorioBO, consultorio, "Eliminar el consultorio ", "Se eliminó el consultorio.");
  }

  public List<Doctor> listarDoctores() {
    return getEntidades(doctorBO);
  }

  public boolean insertarDoctor(Doctor doctor) {
    return saveEntidad(doctorBO, doctor, "Se creó el doctor.");
  }

  public boolean modificarDoctor(Doctor doctor) {
    return updateEntidad(doctorBO, doctor, "Se modificó el doctor.");
  }

  public boolean eliminarDoctor(Doctor doctor) {
    return deleteEntidad(doctorBO, doctor, "Eliminar el doctor ", "Se eliminó el doctor.");
  }

  public List<ObraSocial> listarObrasSociales() {
    return getEntidades(obraSocialBO);
  }

  public boolean insertarObraSocial(ObraSocial obraSocial) {
    return saveEntidad(obraSocialBO, obraSocial, "Se creó la obra social.");
  }

  public boolean modificarObraSocial(ObraSocial obraSocial) {
    return updateEntidad(obraSocialBO, obraSocial, "Se modificó la obra social.");
  }

  public boolean eliminarObraSocial(ObraSocial obraSocial) {
    return deleteEntidad(obraSocialBO, obraSocial, "Eliminar la obra social ", "Se eliminó la obra social.");
  }

  public List<Paciente> listarPacientes() {
    return getEntidades(pacienteBO);
  }

  public boolean insertarPaciente(Paciente paciente) {
    return saveEntidad(pacienteBO, paciente, "Se creó el paciente.");
  }

  public boolean modificarPaciente(Paciente paciente) {
    return updateEntidad(pacienteBO, paciente, "Se modificó el paciente.");
  }

  public boolean eliminarPaciente(Paciente paciente) {
    return deleteEntidad(pacienteBO, paciente, "Eliminar el paciente ", "Se eliminó el paciente.");
  }

  public List<Turno> listarTurnos() {
    return getEntidades(turnoBO, "fecha");
  }

  public boolean insertarTurno(Turno turno) {
    return saveEntidad(turnoBO, turno, "Se creó el turno.");
  }

  public boolean modificarTurno(Turno turno) {
    return updateEntidad(turnoBO, turno, "Se modificó el turno.");
  }

  public boolean eliminarTurno(Turno turno) {
    return deleteEntidad(turnoBO, turno, "Eliminar el turno ", "Se eliminó el turno.");
  }
}