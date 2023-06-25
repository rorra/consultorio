package ar.com.rorra.ui.doctores;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PanelFormularioDoctor extends JPanel {
  private Controlador controlador;
  private Doctor doctor;
  private JLabel lblId;
  private JLabel lblEmail;
  private JLabel lblPassword;
  private JLabel lblLegajo;
  private JLabel lblNombre;
  private JLabel lblTelefono;
  private JLabel lblTarifa;
  private JLabel lblConsultorio;

  private JTextField txtId;
  private JTextField txtEmail;
  private JTextField txtPassword;
  private JTextField txtLegajo;
  private JTextField txtNombre;
  private JTextField txtTelefono;
  private JTextField txtTarifa;
  private JList<Consultorio> lstConsultorios;
  private DefaultListModel<Consultorio> listModel;


  public PanelFormularioDoctor(Controlador controlador, Doctor doctor) {
    this.controlador = controlador;
    this.doctor = doctor;

    String titulo = doctor.isNew() ? "Nuevo Doctor" : "Modificar Doctor";
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);

    add(construirBotones(), BorderLayout.SOUTH);
  }

  private JPanel construirFormulario() {
    JPanel form = new JPanel();
    int filas = doctor.isNew() ? 7 : 8;
    form.setLayout(new GridLayout(filas, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblEmail = UI.crearLabel("Email: ");
    lblPassword = UI.crearLabel("Password: ");
    lblLegajo = UI.crearLabel("Legajo: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblTelefono = UI.crearLabel("Telefono: ");
    lblTarifa = UI.crearLabel("Tarifa: ");
    lblConsultorio = UI.crearLabel("Consultorio: ");

    txtId = UI.construirTextField(String.valueOf(doctor.getId()));
    txtId.setEditable(false);
    txtEmail = UI.construirTextField(doctor.getEmail());
    txtPassword = UI.construirTextField(doctor.getPassword());
    txtLegajo = UI.construirTextField(String.valueOf(doctor.getLegajo()));
    txtNombre = UI.construirTextField(doctor.getNombre());
    txtTelefono = UI.construirTextField(doctor.getTelefono());
    construirListaConsultorios();

    DecimalFormat df = new DecimalFormat("###.00");
    df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    if (doctor.getTarifa() != null) {
      txtTarifa = UI.construirTextField(df.format(doctor.getTarifa()));
    } else {
      txtTarifa = UI.construirTextField(df.format(Doctor.getTarifaPorDefecto()));
    }

    if (!doctor.isNew()) {
      form.add(lblId);
      form.add(txtId);
    }
    form.add(lblEmail);
    form.add(txtEmail);
    form.add(lblPassword);
    form.add(txtPassword);
    form.add(lblLegajo);
    form.add(txtLegajo);
    form.add(lblNombre);
    form.add(txtNombre);
    form.add(lblTelefono);
    form.add(txtTelefono);
    form.add(lblTarifa);
    form.add(txtTarifa);
    form.add(lblConsultorio);
    form.add(lstConsultorios);

    return form;
  }

  private void construirListaConsultorios() {
    lstConsultorios = new JList();

    listModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(Consultorio.class)) {
      listModel.addElement((Consultorio) entidad);
    }
    lstConsultorios.setModel(listModel);

    if (doctor.getConsultorio() != null) {
      lstConsultorios.setSelectedValue(doctor.getConsultorio(), true);
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
    try {
      doctor.setLegajo(Integer.parseInt(txtLegajo.getText()));
    } catch (NumberFormatException _e) {
      controlador.getFramePrincipal().visualizarError("El DNI es inválido.");
      return;
    }
    doctor.setEmail(txtEmail.getText());
    doctor.setPassword(txtPassword.getText());
    doctor.setNombre(txtNombre.getText());
    doctor.setTelefono(txtTelefono.getText());
    try {
      doctor.setTarifa(new BigDecimal(txtTarifa.getText()));
    } catch (NumberFormatException _e) {
      controlador.getFramePrincipal().visualizarError("La tarifa debe ser un importe válido.");
      return;
    }
    if (lstConsultorios.getSelectedValue() == null) {
      controlador.getFramePrincipal().visualizarError("Debe seleccionar un consultorio.");
      return;
    } else {
      doctor.setConsultorio(lstConsultorios.getSelectedValue());
    }

    if (doctor.isNew()) {
      if (controlador.insertarEntidad(doctor)) {
        controlador.getFramePrincipal().visualizarDoctores();
      }
    } else {
      if (controlador.modificarEntidad(doctor)) {
        controlador.getFramePrincipal().visualizarDoctores();
      }
    }
  }

  private void accionCancelar(ActionEvent _event) {
    controlador.getFramePrincipal().visualizarDoctores();
  }
}
