package ar.com.rorra.ui.pacientes;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioPaciente extends JPanel {
  private Controlador controlador;
  private Paciente paciente;
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

  public PanelFormularioPaciente(Controlador controlador, Paciente paciente) {
    this.controlador = controlador;
    this.paciente = paciente;

    String titulo = paciente.isNew() ? "Nuevo Paciente" : "Modificar Paciente";
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);
    add(construirBotones(), BorderLayout.SOUTH);
  }

  private JPanel construirFormulario() {
    JPanel form = new JPanel();

    int filas = paciente.isNew() ? 7 : 8;
    form.setLayout(new GridLayout(filas, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblEmail = UI.crearLabel("Email: ");
    lblPassword = UI.crearLabel("Password: ");
    lblDNI = UI.crearLabel("DNI: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblTelefono = UI.crearLabel("Telefono: ");
    lblObraSocial = UI.crearLabel("Obra Social: ");

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

  private void construirListaObrasSociales() {
    lstObrasSociales = new JList();

    listModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(ObraSocial.class)) {
      listModel.addElement((ObraSocial) entidad);
    }
    lstObrasSociales.setModel(listModel);

    if (paciente.getObraSocial() != null) {
      lstObrasSociales.setSelectedValue(paciente.getObraSocial(), true);
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

  private void accionCancelar(ActionEvent _event) {
    controlador.visualizarPacientes();
  }
}
