package ar.com.rorra.ui.pacientes;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioPaciente extends PanelFormulario {
  private JLabel lblId;
  private JLabel lblEmail;
  private JLabel lblPassword;
  private JLabel lblDNI;
  private JLabel lblNombre;
  private JLabel lblTelefono;
  private JLabel lblObraSocial;

  private JTextField txtId;
  private JTextField txtEmail;
  private JTextField txtPassword;
  private JTextField txtDNI;
  private JTextField txtNombre;
  private JTextField txtTelefono;
  private JList<ObraSocial> lstObrasSociales;
  private DefaultListModel<ObraSocial> listModel;

  /**
   * Constructor
   * @param controlador controlador principal
   * @param entidad entidad referente al formulario
   */
  public PanelFormularioPaciente(Controlador controlador, IEntidad entidad) {
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
    lblDNI = UI.crearLabel("DNI: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblTelefono = UI.crearLabel("Telefono: ");
    lblObraSocial = UI.crearLabel("Obra Social: ");

    Paciente paciente = (Paciente)entidad;

    txtId = UI.construirTextField(String.valueOf(paciente.getId()));
    txtId.setEditable(false);
    txtDNI = UI.construirTextField(String.valueOf(paciente.getDni()));
    txtEmail = UI.construirTextField(paciente.getEmail());
    txtPassword = UI.construirTextField(paciente.getPassword());
    txtNombre = UI.construirTextField(paciente.getNombre());
    txtTelefono = UI.construirTextField(paciente.getTelefono());
    construirListaObrasSociales();

    if (!paciente.isNew()) {
      form.add(lblId);
      form.add(txtId);
    }
    form.add(lblEmail);
    form.add(txtEmail);
    form.add(lblPassword);
    form.add(txtPassword);
    form.add(lblDNI);
    form.add(txtDNI);
    form.add(lblNombre);
    form.add(txtNombre);
    form.add(lblTelefono);
    form.add(txtTelefono);
    form.add(lblObraSocial);
    form.add(lstObrasSociales);

    return form;
  }

  /**
   * Construye la lista de obras sociales
   */
  private void construirListaObrasSociales() {
    lstObrasSociales = new JList();

    listModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(ObraSocial.class)) {
      listModel.addElement((ObraSocial) entidad);
    }
    lstObrasSociales.setModel(listModel);

    Paciente paciente = (Paciente)entidad;
    if (paciente.getObraSocial() != null) {
      lstObrasSociales.setSelectedValue(paciente.getObraSocial(), true);
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  @Override
  protected void accionGuardar(ActionEvent _event) {
    Paciente paciente = (Paciente)entidad;
    try {
      paciente.setDni(Integer.parseInt(txtDNI.getText()));
    } catch (NumberFormatException _e) {
      controlador.getFramePrincipal().visualizarError("El DNI es inválido.");
      return;
    }
    paciente.setEmail(txtEmail.getText());
    paciente.setPassword(txtPassword.getText());
    paciente.setNombre(txtNombre.getText());
    paciente.setTelefono(txtTelefono.getText());
    if (lstObrasSociales.getSelectedValue() == null) {
      controlador.getFramePrincipal().visualizarError("Debe seleccionar un consultorio.");
      return;
    } else {
      paciente.setObraSocial(lstObrasSociales.getSelectedValue());
    }

    if (paciente.isNew()) {
      if (controlador.insertarEntidad(paciente)) {
        controlador.visualizarPacientes();
      }
    } else {
      if (controlador.modificarEntidad(paciente)) {
        controlador.visualizarPacientes();
      }
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    controlador.visualizarPacientes();
  }

  /**
   * Devuelve la clase de la entidad referente al formulario
   * @return clase de la entidad
   */
  @Override
  public Class getEntityClass() {
    return Administrador.class;
  }
}
