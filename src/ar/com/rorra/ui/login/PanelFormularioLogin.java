package ar.com.rorra.ui.login;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioLogin extends PanelFormulario {
  private JLabel lblEmail;
  private JLabel lblPassword;
  private JTextField txtEmail;
  private JPasswordField txtPassword;

  /**
   * Constructor
   * @param controlador controlador principal
   * @param entidad entidad referente al formulario
   */
  public PanelFormularioLogin(Controlador controlador, IEntidad entidad) {
    this.controlador = controlador;
    this.entidad = entidad;

    controlador.getFramePrincipal().setTitulo("Login");

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);

    add(construirBotones(), BorderLayout.SOUTH);
  }

  /**
   * Construye el formulario de login
   * @return panel con el formulario
   */
  @Override
  protected JPanel construirFormulario() {
    JPanel form = new JPanel();
    form.setLayout(new GridLayout(0, 2, 10, 10));

    lblEmail = UI.crearLabel("Email: ");
    lblPassword = UI.crearLabel("Password: ");

    txtEmail = UI.construirTextField("");
    txtPassword = new JPasswordField();

    form.add(lblEmail);
    form.add(txtEmail);
    form.add(lblPassword);
    form.add(txtPassword);

    return form;
  }

  /**
   * Sobreescribir el label del boton guardar
   * @return etiqueta
   */
  @Override
  protected String labelBotonGuardar() {
    return "Ingresar";
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected void accionGuardar(ActionEvent _event) {
    if (controlador.validarLogin(txtEmail.getText(), txtPassword.getText())) {
      controlador.visualizarMenuPrincipal();
    } else {
      controlador.getFramePrincipal().visualizarError("Email y/o pasword inválidos");
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    System.exit(1); // Salir de la aplicacion
  }

  /**
   * Devuelve la clase de la entidad referente al formulario
   * @return clase de la entidad
   */
  @Override
  public Class getEntityClass() {
    return Consultorio.class;
  }
}
