package ar.com.rorra.ui.administradores;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioAdministrador extends PanelFormulario {
  private JLabel lblId;
  private JLabel lblEmail;
  private JLabel lblPassword;
  private JLabel lblLegajo;
  private JLabel lblNombre;
  private JLabel lblTelefono;

  private JTextField txtId;
  private JTextField txtEmail;
  private JTextField txtPassword;
  private JTextField txtLegajo;
  private JTextField txtNombre;
  private JTextField txtTelefono;

  public PanelFormularioAdministrador(Controlador controlador, IEntidad entidad) {
    super(controlador, entidad);
  }

  /**
   * Construye el formulario para el administrador
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

    Administrador administrador = (Administrador)entidad;

    txtId = UI.construirTextField(String.valueOf(administrador.getId()));
    txtId.setEditable(false);
    txtEmail = UI.construirTextField(administrador.getEmail());
    txtPassword = UI.construirTextField(administrador.getPassword());
    txtLegajo = UI.construirTextField(String.valueOf(administrador.getLegajo()));
    txtNombre = UI.construirTextField(administrador.getNombre());
    txtTelefono = UI.construirTextField(administrador.getTelefono());

    if (!administrador.isNew()) {
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

    return form;
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected void accionGuardar(ActionEvent _event) {
    Administrador administrador = (Administrador)entidad;
    try {
      administrador.setLegajo(Integer.parseInt(txtLegajo.getText()));
    } catch (NumberFormatException _e) {
      controlador.getFramePrincipal().visualizarError("El legajo debe ser un numero entero");
      return;
    }
    administrador.setEmail(txtEmail.getText());
    administrador.setPassword(txtPassword.getText());
    administrador.setNombre(txtNombre.getText());
    administrador.setTelefono(txtTelefono.getText());

    if (administrador.isNew()) {
      if (controlador.insertarEntidad(administrador)) {
        controlador.visualizarAdministradores();
      }
    } else {
      if (controlador.modificarEntidad(administrador)) {
        controlador.visualizarAdministradores();
      }
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    controlador.visualizarAdministradores();
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
