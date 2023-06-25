package ar.com.rorra.ui.obrasSociales;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.ui.PanelFormulario;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioObraSocial extends PanelFormulario {
  private JLabel lblId;
  private JLabel lblNombre;
  private JLabel lblDescuento;

  private JTextField txtId;
  private JTextField txtNombre;
  private JTextField txtDescuento;

  /**
   * Constructor
   * @param controlador controlador principal
   * @param entidad entidad referente al formulario
   */
  public PanelFormularioObraSocial(Controlador controlador, IEntidad entidad) {
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
    lblDescuento = UI.crearLabel("Descuento (%, 0-100): ");

    ObraSocial obraSocial = (ObraSocial)entidad;

    txtId = UI.construirTextField(String.valueOf(obraSocial.getId()));
    txtId.setEditable(false);
    txtNombre = UI.construirTextField(obraSocial.getNombre());
    txtDescuento = UI.construirTextField(String.valueOf(obraSocial.getDescuento()));

    if (!obraSocial.isNew()) {
      form.add(lblId);
      form.add(txtId);
    }
    form.add(lblNombre);
    form.add(txtNombre);
    form.add(lblDescuento);
    form.add(txtDescuento);

    return form;
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected void accionGuardar(ActionEvent _event) {
    ObraSocial obraSocial = (ObraSocial)entidad;
    obraSocial.setNombre(txtNombre.getText());
    try {
      obraSocial.setDescuento(Integer.parseInt(txtDescuento.getText()));
    } catch (NumberFormatException _e) {
      controlador.getFramePrincipal().visualizarError("El descuento debe ser un numero entero entre 0 y 100");
      return;
    }


    if (obraSocial.isNew()) {
      if (controlador.insertarEntidad(obraSocial)) {
        controlador.visualizarObrasSociales();
      }
    } else {
      if (controlador.modificarEntidad(obraSocial)) {
        controlador.visualizarObrasSociales();
      }
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected void accionCancelar(ActionEvent _event) {
    controlador.visualizarObrasSociales();
  }

  /**
   * Devuelve la clase de la entidad referente al formulario
   * @return clase de la entidad
   */
  @Override
  public Class getEntityClass() {
    return ObraSocial.class;
  }
}
