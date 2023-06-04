package ar.com.rorra.ui.administradores;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioAdministrador extends JPanel {
  private Controlador controlador;
  private Administrador administrador;
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

  public PanelFormularioAdministrador(Controlador controlador, Administrador administrador) {
    this.controlador = controlador;
    this.administrador = administrador;

    String titulo = administrador.isNew() ? "Nuevo Administrador" : "Modificar Administrador";
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);

    add(construirBotones(), BorderLayout.SOUTH);
  }

  private JPanel construirFormulario() {
    JPanel form = new JPanel();
    int filas = administrador.isNew() ? 6 : 7;
    form.setLayout(new GridLayout(filas, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblEmail = UI.crearLabel("Email: ");
    lblPassword = UI.crearLabel("Password: ");
    lblLegajo = UI.crearLabel("Legajo: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblTelefono = UI.crearLabel("Telefono: ");

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
      if (controlador.insertarAdministrador(administrador)) {
        controlador.getFramePrincipal().visualizarAdministradores();
      }
    } else {
      if (controlador.modificarAdministrador(administrador)) {
        controlador.getFramePrincipal().visualizarAdministradores();
      }
    }
  }

  private void accionCancelar(ActionEvent _event) {
    controlador.getFramePrincipal().visualizarAdministradores();
  }
}
