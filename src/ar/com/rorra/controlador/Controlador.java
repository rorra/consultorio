package ar.com.rorra.controlador;

import ar.com.rorra.bo.*;
import ar.com.rorra.dao.BaseDAO;
import ar.com.rorra.entidad.*;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.ui.FramePrincipal;
import ar.com.rorra.ui.administradores.PanelAdministradores;
import ar.com.rorra.ui.administradores.PanelFormularioAdministrador;
import ar.com.rorra.ui.consultorios.PanelConsultorios;
import ar.com.rorra.ui.consultorios.PanelFormularioConsultorio;
import ar.com.rorra.ui.doctores.PanelDoctores;
import ar.com.rorra.ui.doctores.PanelFormularioDoctor;
import ar.com.rorra.ui.login.PanelFormularioLogin;
import ar.com.rorra.ui.obrasSociales.PanelFormularioObraSocial;
import ar.com.rorra.ui.obrasSociales.PanelObrasSociales;
import ar.com.rorra.ui.pacientes.PanelFormularioPaciente;
import ar.com.rorra.ui.pacientes.PanelPacientes;
import ar.com.rorra.ui.reportes.FormularioReporte;
import ar.com.rorra.ui.turnos.PanelFormularioTurno;
import ar.com.rorra.ui.turnos.PanelTurnos;

import javax.swing.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Controlador principal de la aplicación.
 * <p>
 * Siguiendo la metodología de BaseDAO, definimos funciones parametrizadas para las acciones de insertar, modificar,
 * listar y eliminar las diferentes entidades.
 * <p>
 * Las Entidades, que es el modelo puro, implementa la interfaza IEntidad.
 * <p>
 * Los DAOs, que son los que se comunican con la base de datos, implementan la interfaz BaseDAO<ENTIDAD>.
 * <p>
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
  private Usuario usuario; // Usuario logueado

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
      visualizarLogin();
    });
  }

  /**
   * Devuelve el frame principal de la aplicación.
   *
   * @return
   */
  public FramePrincipal getFramePrincipal() {
    return framePrincipal;
  }

  /**
   * Devuelve una lista de entidadedes de la base de datos
   *
   * @param bo        BO que se encarga de la entidad
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO>     El BO implementa la interfaz DOA
   * @return Lista de entidades
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
   *
   * @param bo        BO que se encarga de la entidad
   * @param sortField Campo por el que se ordena
   * @param <ENTIDAD> El BO implementa la interfaz IEntidad
   * @param <DAO>     El BO implementa la interfaz DOA
   * @return Lista de entidades
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
   *
   * @param bo         BO que se encarga de la entidad
   * @param conditions Condiciones que deben cumplir las entidades
   * @param <ENTIDAD>  El BO implementa la interfaz IEntidad
   * @param <DAO>      El BO implementa la interfaz DOA
   * @return Lista de entidades
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
   *
   * @param bo             BO que se encarga de la entidad
   * @param entity         Entidad que se quiere guardar
   * @param successMessage Mensaje de éxito
   * @param <ENTIDAD>      El BO implementa la interfaz IEntidad
   * @param <DAO>          El BO implementa la interfaz DOA
   * @return true si se guardó correctamente, false si no
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
   *
   * @param bo             BO que se encarga de la entidad
   * @param entity         Entidad que se quiere modificar
   * @param successMessage Mensaje de éxito
   * @param <ENTIDAD>      El BO implementa la interfaz IEntidad
   * @param <DAO>          El BO implementa la interfaz DOA
   * @return
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
   *
   * @param bo             BO que se encarga de la entidad
   * @param entity         Entidad que se quiere eliminar
   * @param confirmMessage Mensaje de confirmación
   * @param successMessage Mensaje de éxito
   * @param <ENTIDAD>      El BO implementa la interfaz IEntidad
   * @param <DAO>          El BO implementa la interfaz DOA
   * @return
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
   *
   * @param klass     Clase de la entidad a listar.
   * @param sortField Campo por el cual se ordenará la lista.
   * @param <T>       Clase de la entidad a listar.
   * @return Lista de entidades.
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
   *
   * @param klass  Clase de la entidad a listar.
   * @param filter Filtro para la lista.
   * @param <T>    Clase de la entidad a listar.
   * @return Lista de entidades.
   */
  public <T extends IEntidad> List<T> listarEntidades(Class klass, IEntidad filter) {
    // Filtros para doctores
    if (klass == Doctor.class) {
      if (filter.getClass() == Consultorio.class) {
        Map<String, String> conditions = Map.of("consultorio_id", Integer.toString(filter.getId()));
        return (List<T>) getEntidades(doctorBO, conditions);
      }
    }
    // Filtro para turnos
    if (klass == Turno.class) {
      if (filter.getClass() == Doctor.class) {
        Map<String, String> conditions = Map.of("doctor_id", Integer.toString(filter.getId()));
        return (List<T>) getEntidades(turnoBO, conditions);
      }
    }
    return null;
  }

  /**
   * Función parametrizada para listar las entidades.
   *
   * @param klass Clase de la entidad a listar.
   * @param <T>   Clase de la entidad a listar.
   * @return Lista de entidades.
   */
  public <T extends IEntidad> List<T> listarEntidades(Class klass) {
    return listarEntidades(klass, (String) null);
  }

  /**
   * Inserta una entidad en la base de datos
   *
   * @param entidad Entidad a insertar
   * @return true si se insertó correctamente, false en caso contrario
   */
  public boolean insertarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      return saveEntidad(administradorBO, (Administrador) entidad, "Se creó el administrador.");
    } else if (entidad instanceof Consultorio) {
      return saveEntidad(consultorioBO, (Consultorio) entidad, "Se creó el consultorio.");
    } else if (entidad instanceof Doctor) {
      return saveEntidad(doctorBO, (Doctor) entidad, "Se creó el doctor.");
    } else if (entidad instanceof ObraSocial) {
      return saveEntidad(obraSocialBO, (ObraSocial) entidad, "Se creó la obra social.");
    } else if (entidad instanceof Paciente) {
      return saveEntidad(pacienteBO, (Paciente) entidad, "Se creó el paciente.");
    } else if (entidad instanceof Turno) {
      return saveEntidad(turnoBO, (Turno) entidad, "Se creó el turno.");
    }
    return false;
  }

  /**
   * Modifica una entidad en la base de datos
   *
   * @param entidad Entidad a modificar
   * @return true si se modificó correctamente, false en caso contrario
   */
  public boolean modificarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      return updateEntidad(administradorBO, (Administrador) entidad, "Se modificó el administrador.");
    } else if (entidad instanceof Consultorio) {
      return updateEntidad(consultorioBO, (Consultorio) entidad, "Se modificó el consultorio.");
    } else if (entidad instanceof Doctor) {
      return updateEntidad(doctorBO, (Doctor) entidad, "Se modificó el doctor.");
    } else if (entidad instanceof ObraSocial) {
      return updateEntidad(obraSocialBO, (ObraSocial) entidad, "Se modificó la obra social.");
    } else if (entidad instanceof Paciente) {
      return updateEntidad(pacienteBO, (Paciente) entidad, "Se modificó el paciente.");
    } else if (entidad instanceof Turno) {
      return updateEntidad(turnoBO, (Turno) entidad, "Se modificó el turno.");
    }
    return false;
  }

  /**
   * Elimina una entidad en la base de datos
   *
   * @param entidad Entidad a eliminar
   * @return true si se eliminó correctamente, false en caso contrario
   */
  public boolean eliminarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      return deleteEntidad(administradorBO, (Administrador) entidad, "Eliminar el administrador ", "Se eliminó el administrador.");
    } else if (entidad instanceof Consultorio) {
      return deleteEntidad(consultorioBO, (Consultorio) entidad, "Eliminar el consultorio ", "Se eliminó el consultorio.");
    } else if (entidad instanceof Doctor) {
      return deleteEntidad(doctorBO, (Doctor) entidad, "Eliminar el doctor ", "Se eliminó el doctor.");
    } else if (entidad instanceof ObraSocial) {
      return deleteEntidad(obraSocialBO, (ObraSocial) entidad, "Eliminar la obra social ", "Se eliminó la obra social.");
    } else if (entidad instanceof Paciente) {
      return deleteEntidad(pacienteBO, (Paciente) entidad, "Eliminar el paciente ", "Se eliminó el paciente.");
    } else if (entidad instanceof Turno) {
      return deleteEntidad(turnoBO, (Turno) entidad, "Eliminar el turno ", "Se eliminó el turno.");
    }
    return false;
  }

  /**
   * Función parametrizada para mostrar un panel para una entidad que se esta creando
   *
   * @param klass
   */
  public void panelNuevaEntidad(Class klass) {
    if (klass == Administrador.class) {
      getFramePrincipal().setPanel(new PanelFormularioAdministrador(this, new Administrador()));
    } else if (klass == Consultorio.class) {
      getFramePrincipal().setPanel(new PanelFormularioConsultorio(this, new Consultorio()));
    } else if (klass == Doctor.class) {
      getFramePrincipal().setPanel(new PanelFormularioDoctor(this, new Doctor()));
    } else if (klass == ObraSocial.class) {
      getFramePrincipal().setPanel(new PanelFormularioObraSocial(this, new ObraSocial()));
    } else if (klass == Paciente.class) {
      getFramePrincipal().setPanel(new PanelFormularioPaciente(this, new Paciente()));
    } else if (klass == Turno.class) {
      getFramePrincipal().setPanel(new PanelFormularioTurno(this, new Turno()));
    }
  }

  /**
   * Función parametrizada para mostrar un panel para una entidad que se esta modificando
   *
   * @param entidad Entidad a modificar
   */
  public void panelModificarEntidad(IEntidad entidad) {
    if (entidad instanceof Administrador) {
      getFramePrincipal().setPanel(new PanelFormularioAdministrador(this, (Administrador) entidad));
    } else if (entidad instanceof Consultorio) {
      getFramePrincipal().setPanel(new PanelFormularioConsultorio(this, (Consultorio) entidad));
    } else if (entidad instanceof Doctor) {
      getFramePrincipal().setPanel(new PanelFormularioDoctor(this, (Doctor) entidad));
    } else if (entidad instanceof ObraSocial) {
      getFramePrincipal().setPanel(new PanelFormularioObraSocial(this, (ObraSocial) entidad));
    } else if (entidad instanceof Paciente) {
      getFramePrincipal().setPanel(new PanelFormularioPaciente(this, (Paciente) entidad));
    } else if (entidad instanceof Turno) {
      getFramePrincipal().setPanel(new PanelFormularioTurno(this, (Turno) entidad));
    }
  }

  /**
   * Muestra la pantalla principal
   */
  public void visualizarMenuPrincipal() {
    getFramePrincipal().setSize(1920, 1080);
    getFramePrincipal().pantallaPrincipal();
  }

  /**
   * Muestra el login
   */
  public void visualizarLogin() {
    framePrincipal.setSize(1920, 150);
    framePrincipal.visualizarPanel(new PanelFormularioLogin(this, null));
  }

  /**
   * Muestra el ABM de administradores
   */
  public void visualizarAdministradores() {
    framePrincipal.visualizarPanel(new PanelAdministradores(this));
  }

  /**
   * Muestra el ABM de Consultorios
   */
  public void visualizarConsultorios() {
    framePrincipal.visualizarPanel(new PanelConsultorios(this));
  }

  /**
   * Muestra el ABM de doctores
   */
  public void visualizarDoctores() {
    framePrincipal.visualizarPanel(new PanelDoctores(this));
  }

  /**
   * Muestra el ABM de Obras Sociales
   */
  public void visualizarObrasSociales() {
    framePrincipal.visualizarPanel(new PanelObrasSociales(this));
  }

  /**
   * Muestra el ABM de pacientes
   */
  public void visualizarPacientes() {
    framePrincipal.visualizarPanel(new PanelPacientes(this));
  }

  /**
   * Muestra el ABM de turnos
   */
  public void visualizarTurnos() {
    framePrincipal.visualizarPanel(new PanelTurnos(this));
  }

  /**
   * Visualiza el formulario de reportes
   */
  public void visualizarReportes() {
    framePrincipal.visualizarPanel(new FormularioReporte(this));
  }

  /**
   * Obtiene una lista con los medicos y sus recaudaciones entre dos fechas
   * @param desde Fecha desde
   * @param hasta Fecha hasta
   * @return Lista de medicos y sus recaudaciones
   */
  public ArrayList<String[]> ingresosPorMedico(LocalDateTime desde, LocalDateTime hasta) {
    ArrayList<String[]> filas = new ArrayList<>();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es_AR"));

    // Para cada doctor
    for (IEntidad entidadDoctor: listarEntidades(Doctor.class)) {
      BigDecimal total = BigDecimal.ZERO;
      Doctor doctor = (Doctor) entidadDoctor;
      // Para cada turno del doctor en esas fechas
      for (Turno turno: turnoBO.getAllBetweenDates(doctor, desde, hasta)) {
        // Obtengo el descuento de la obra social y adicion el total menos el descuento
        ObraSocial obraSocial = turno.getPaciente().getObraSocial();
        int descuento = obraSocial.getDescuento(); // El descuento va de 0 a 100
        if (descuento > 0) {
          total = total.add(doctor.getTarifa().multiply(new BigDecimal(100 - descuento).divide(new BigDecimal(100))));
        } else {
          total = total.add(doctor.getTarifa());
        }
      }
      // Filas formateadas para el reporte
      filas.add(new String[] {
        Integer.toString(doctor.getId()),
        doctor.getNombre(),
        currencyFormatter.format(doctor.getTarifa()),
        currencyFormatter.format(total.floatValue())
      });
    }

    return filas;
  }

  /**
   * Valida el login de un administrador
   * @param email email del administrador
   * @param password password del administrador
   * @return true si el login es correcto, false en caso contrario
   */
  public boolean validarLogin(String email, String password) {
    Map<String, String> conditions = Map.of("email", email, "password", password);

    try {
      if (validarLoginHlp(administradorBO, conditions) != null ||
        validarLoginHlp(doctorBO, conditions) != null ||
        validarLoginHlp(pacienteBO, conditions) != null) {
        return true;
      }
    } catch (BOException ex) {
      return false;
    }
    return false;
  }

  /**
   * Helper de validarLogin, para evitar repetir codigo
   * @param bo BO a utilizar
   * @param conditions condiciones a utilizar
   * @return Usuario si el login es correcto, null en caso contrario
   * @throws BOException
   */
  private Usuario validarLoginHlp(BaseBO bo, Map<String, String> conditions) throws BOException {
    Usuario usuario = (Usuario)bo.getByConditions(conditions);
    if (usuario != null) {
      this.usuario = usuario;
      return usuario;
    }
    return null;
  }
}