package ar.com.rorra.ui.doctores;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PanelFormularioDoctor extends PanelFormulario {
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

  /**
   * Constructor
   * @param controlador controlador principal
   * @param entidad entidad referente al formulario
   */
  public PanelFormularioDoctor(Controlador controlador, IEntidad entidad) {
    super(controlador, entidad);
  }

  /**
   * Construye el formulario de la entidad que se esta creando/modificando
   * @return panel con el formulario
   */
  @Override
  protected JPanel construirFormulario() {
    JPanel form = new JPanel();
    form.setLayout(new GridLayout(0, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblEmail = UI.crearLabel("Email: ");
    lblPassword = UI.crearLabel("Password: ");
    lblLegajo = UI.crearLabel("Legajo: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblTelefono = UI.crearLabel("Telefono: ");
    lblTarifa = UI.crearLabel("Tarifa: ");
    lblConsultorio = UI.crearLabel("Consultorio: ");

    Doctor doctor = (Doctor)entidad;

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

    Doctor doctor = (Doctor)entidad;
    if (doctor.getConsultorio() != null) {
      lstConsultorios.setSelectedValue(doctor.getConsultorio(), true);
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected void accionGuardar(ActionEvent _event) {
    Doctor doctor = (Doctor)entidad;
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
        controlador.visualizarDoctores();
      }
    } else {
      if (controlador.modificarEntidad(doctor)) {
        controlador.visualizarDoctores();
      }
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    controlador.visualizarDoctores();
  }

  /**
   * Devuelve la clase de la entidad referente al formulario
   * @return clase de la entidad
   */
  @Override
  public Class getEntityClass() {
    return Doctor.class;
  }
}
