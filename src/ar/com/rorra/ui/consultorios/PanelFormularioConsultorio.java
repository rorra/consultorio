package ar.com.rorra.ui.consultorios;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioConsultorio extends PanelFormulario {
  private JLabel lblId;
  private JLabel lblNombre;
  private JLabel lblDireccion;
  private JTextField txtId;
  private JTextField txtNombre;
  private JTextField txtDireccion;

  /**
   * Constructor
   * @param controlador controlador principal
   * @param entidad entidad referente al formulario
   */
  public PanelFormularioConsultorio(Controlador controlador, IEntidad entidad) {
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
    lblNombre = UI.crearLabel("Nombre: ");
    lblDireccion = UI.crearLabel("Dirección: ");

    Consultorio consultorio = (Consultorio)entidad;

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

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected void accionGuardar(ActionEvent _event) {
    Consultorio consultorio = (Consultorio)entidad;

    consultorio.setNombre(txtNombre.getText());
    consultorio.setDirección(txtDireccion.getText());

    if (consultorio.isNew()) {
      if (controlador.insertarEntidad(consultorio)) {
        controlador.visualizarConsultorios();
      }
    } else {
      if (controlador.modificarEntidad(consultorio)) {
        controlador.visualizarConsultorios();
      }
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    controlador.visualizarConsultorios();
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
