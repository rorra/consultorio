package ar.com.rorra.ui.turnos;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.*;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PanelFormularioTurno extends PanelFormulario {
  private JLabel lblId;
  private JLabel lblPaciente;
  private JLabel lblConsultorio;
  private JLabel lblDoctor;
  private JLabel lblFecha;

  private JTextField txtId;
  private JList<Paciente> lstPacientes;
  private DefaultListModel<Paciente> listPacientesModel;
  private JList<Consultorio> lstConsultorios;
  private DefaultListModel<Consultorio> listConsultoriosModel;
  private JList<Doctor> lstDoctores;
  private DefaultListModel<Doctor> listDoctoresModel;
  private JTextField txtFecha;

  /**
   * Constructor
   * @param controlador controlador principal
   * @param entidad entidad referente al formulario
   */
  public PanelFormularioTurno(Controlador controlador, IEntidad entidad) {
    super(controlador, entidad);
  }

  /**
   * Construye el formulario de la entidad que se esta creando/modificando
   * @return panel con el formulario
   */
  protected JPanel construirFormulario() {
    JPanel form = new JPanel();
    form.setLayout(new GridLayout(0, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblPaciente = UI.crearLabel("Paciente: ");
    lblConsultorio = UI.crearLabel("Consultorio: ");
    lblDoctor = UI.crearLabel("Doctor: ");
    lblFecha = UI.crearLabel("Fecha: (yyyy/mm/dd hh:mm) ");

    Turno turno = (Turno)entidad;

    txtId = UI.construirTextField(String.valueOf(turno.getId()));
    txtId.setEditable(false);
    construirListaPacientes();
    construirListaConsultorios();
    construirListaDoctores();
    if (turno.getFecha() != null) {
      txtFecha = UI.construirTextField(turno.getFecha().format(DateTimeFormatter.ofPattern(Turno.FORMATO_FECHA)));
    } else {
      txtFecha = UI.construirTextField("");
    }

    if (!turno.isNew()) {
      form.add(lblId);
      form.add(txtId);
    }
    form.add(lblPaciente);
    form.add(lstPacientes);
    form.add(lblConsultorio);
    form.add(lstConsultorios);
    form.add(lblDoctor);
    form.add(lstDoctores);
    form.add(lblFecha);
    form.add(txtFecha);

    return form;
  }

  /**
   * Construye la lista de pacientes
   */
  private void construirListaPacientes() {
    lstPacientes = new JList();

    listPacientesModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(Paciente.class)) {
      listPacientesModel.addElement((Paciente) entidad);
    }
    lstPacientes.setModel(listPacientesModel);

    Turno turno = (Turno)entidad;
    if (turno.getPaciente() != null) {
      lstPacientes.setSelectedValue(turno.getPaciente(), true);
    }
  }

  /**
   * Construye la lista de consultorios
   */
  private void construirListaConsultorios() {
    lstConsultorios = new JList();

    listConsultoriosModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(Consultorio.class)) {
      listConsultoriosModel.addElement((Consultorio) entidad);
    }
    lstConsultorios.setModel(listConsultoriosModel);
    lstConsultorios.addListSelectionListener(e -> actualizarListaDoctores());

    Turno turno = (Turno)entidad;
    if (turno.getConsultorio() != null) {
      lstConsultorios.setSelectedValue(turno.getConsultorio(), true);
    }
  }

  /**
   * Construye la lista de doctores
   */
  public void construirListaDoctores() {
    lstDoctores = new JList();

    actualizarListaDoctores();
  }

  /**
   * Actualiza la lista de doctores
   */
  public void actualizarListaDoctores() {
    if (lstDoctores == null) return;

    listDoctoresModel = new DefaultListModel<>();

    if (lstConsultorios.getSelectedValue() != null) {
      for (IEntidad entidad : controlador.listarEntidades(Doctor.class, lstConsultorios.getSelectedValue())) {
        listDoctoresModel.addElement((Doctor) entidad);
      }
    }
    lstDoctores.setModel(listDoctoresModel);

    Turno turno = (Turno)entidad;
    if (turno.getDoctor() != null) {
      lstDoctores.setSelectedValue(turno.getDoctor(), true);
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected void accionGuardar(ActionEvent _event) {
    Turno turno = (Turno)entidad;
    turno.setDoctor(lstDoctores.getSelectedValue());
    turno.setPaciente(lstPacientes.getSelectedValue());
    try {
      turno.setFecha(LocalDateTime.parse(txtFecha.getText(), DateTimeFormatter.ofPattern(Turno.FORMATO_FECHA)));
    } catch (Exception e) {
      controlador.getFramePrincipal().visualizarError("Fecha inválida: " + e.getMessage());
      return;
    }

    if (turno.isNew()) {
      if (controlador.insertarEntidad(turno)) {
        controlador.visualizarTurnos();
      }
    } else {
      if (controlador.modificarEntidad(turno)) {
        controlador.visualizarTurnos();
      }
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    controlador.visualizarTurnos();
  }

  /**
   * Devuelve la clase de la entidad referente al formulario
   * @return clase de la entidad
   */
  @Override
  public Class getEntityClass() {
    return Turno.class;
  }
}
