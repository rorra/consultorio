package ar.com.rorra.ui.turnos;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.*;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioTurno extends JPanel {
  private Controlador controlador;
  private Turno turno;
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

  public PanelFormularioTurno(Controlador controlador, Turno turno) {
    this.controlador = controlador;
    this.turno = turno;

    String titulo = turno.isNew() ? "Nuevo Turno" : "Modificar Turno";
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);
    add(construirBotones(), BorderLayout.SOUTH);
  }

  private JPanel construirFormulario() {
    JPanel form = new JPanel();

    int filas = turno.isNew() ? 5 : 6;
    form.setLayout(new GridLayout(filas, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblPaciente = UI.crearLabel("Paciente: ");
    lblConsultorio = UI.crearLabel("Consultorio: ");
    lblDoctor = UI.crearLabel("Doctor: ");
    lblFecha = UI.crearLabel("Fecha: ");

    txtId = UI.construirTextField(String.valueOf(turno.getId()));
    txtId.setEditable(false);
    construirListaPacientes();
    construirListaConsultorios();
    construirListaDoctores();
    txtFecha = UI.construirTextField(String.valueOf(turno.getFecha()));

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

  private void construirListaPacientes() {
    lstPacientes = new JList();

    listPacientesModel = new DefaultListModel<>();
    for (Paciente entidad : controlador.listarPacientes()) {
      listPacientesModel.addElement(entidad);
    }
    lstPacientes.setModel(listPacientesModel);

    if (turno.getPaciente() != null) {
      lstPacientes.setSelectedValue(turno.getPaciente(), true);
    }
  }

  private void construirListaConsultorios() {
    lstConsultorios = new JList();

    listConsultoriosModel = new DefaultListModel<>();
    for (Consultorio entidad : controlador.listarConsultorios()) {
      listConsultoriosModel.addElement(entidad);
    }
    lstConsultorios.setModel(listConsultoriosModel);

    if (turno.getConsultorio() != null) {
      lstConsultorios.setSelectedValue(turno.getConsultorio(), true);
    }
  }

  public void construirListaDoctores() {
    lstDoctores = new JList();

    listDoctoresModel = new DefaultListModel<>();
    for (Doctor entidad : controlador.listarDoctores()) {
      listDoctoresModel.addElement(entidad);
    }
    lstDoctores.setModel(listDoctoresModel);

    if (turno.getDoctor() != null) {
      lstDoctores.setSelectedValue(turno.getDoctor(), true);
    }
  }

  private JPanel construirBotones() {
    JPanel botones = new JPanel();

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.addActionListener(e -> accionGuardar(e));
    botones.add(btnGuardar);

    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(e -> accionCancelar(e));
    botones.add(btnCancelar);

    return botones;
  }

  private void accionGuardar(ActionEvent _event) {
    // TODO: Agregar doctor, paciente y fecha

    if (turno.isNew()) {
      if (controlador.insertarTurno(turno)) {
        controlador.getFramePrincipal().visualizarTurnos();
      }
    } else {
      if (controlador.modificarTurno(turno)) {
        controlador.getFramePrincipal().visualizarTurnos();
      }
    }
  }

  private void accionCancelar(ActionEvent _event) {
    controlador.getFramePrincipal().visualizarTurnos();
  }
}
