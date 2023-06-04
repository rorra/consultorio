package ar.com.rorra.ui.consultorios;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioConsultorio extends JPanel {
  private Controlador controlador;
  private Consultorio consultorio;
  private JLabel lblId;
  private JLabel lblNombre;
  private JLabel lblDireccion;
  private JTextField txtId;
  private JTextField txtNombre;
  private JTextField txtDireccion;

  public PanelFormularioConsultorio(Controlador controlador, Consultorio consultorio) {
    this.controlador = controlador;
    this.consultorio = consultorio;

    String titulo = consultorio.isNew() ? "Nuevo Consultorio" : "Modificar Consultorio";
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);

    add(construirBotones(), BorderLayout.SOUTH);
  }

  private JPanel construirFormulario() {
    JPanel form = new JPanel();
    int filas = consultorio.isNew() ? 3 : 4;
    form.setLayout(new GridLayout(filas, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblDireccion = UI.crearLabel("Dirección: ");

    txtId = UI.construirTextField(String.valueOf(consultorio.getId()));
    txtId.setEditable(false);
    txtNombre = UI.construirTextField(consultorio.getNombre());
    txtDireccion = UI.construirTextField(consultorio.getDirección());

    if (!consultorio.isNew()) {
      form.add(lblId);
      form.add(txtId);
    }
    form.add(lblNombre);
    form.add(txtNombre);
    form.add(lblDireccion);
    form.add(txtDireccion);

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
    consultorio.setNombre(txtNombre.getText());
    consultorio.setDirección(txtDireccion.getText());

    if (consultorio.isNew()) {
      if (controlador.insertarConsultorio(consultorio)) {
        controlador.getFramePrincipal().visualizarConsultorios();
      }
    } else {
      if (controlador.modificarConsultorio(consultorio)) {
        controlador.getFramePrincipal().visualizarConsultorios();
      }
    }
  }

  private void accionCancelar(ActionEvent _event) {
    controlador.getFramePrincipal().visualizarConsultorios();
  }
}
