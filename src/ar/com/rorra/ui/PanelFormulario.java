package ar.com.rorra.ui;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.IEntidad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class PanelFormulario extends Panel {
  protected Controlador controlador;
  protected IEntidad entidad;

  public PanelFormulario() {}

  /**
   * Constructor
   * @param controlador controlador
   * @param entidad entidad
   */
  public PanelFormulario(Controlador controlador, IEntidad entidad) {
    this.controlador = controlador;
    this.entidad = entidad;

    String titulo = entidad.isNew() ? "Nuevo " + this.getEntityName() : "Modificar " + this.getEntityName();
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);

    add(construirBotones(), BorderLayout.SOUTH);
  }

  protected abstract JPanel construirFormulario();

  /**
   * Construye el panel de botones, que incluye el panel guardar y cancelar
   * @return panel con los botones
   */
  protected JPanel construirBotones() {
    JPanel botones = new JPanel();

    JButton btnGuardar = new JButton(labelBotonGuardar());
    btnGuardar.addActionListener(e -> accionGuardar(e));
    botones.add(btnGuardar);

    JButton btnCancelar = new JButton(labelBotonCancelar());
    btnCancelar.addActionListener(e -> accionCancelar(e));
    botones.add(btnCancelar);

    return botones;
  }

  /**
   * Etiqueta par ael boton guardar
   * @return etiqueta
   */
  protected String labelBotonGuardar() {
    return "Guardar";
  }

  /**
   * Etiqueta para el boton cancelar
   * @return label
   */
  protected String labelBotonCancelar() {
    return "Cancelar";
  }

  /**
   * Acción a ejecutar cuando se presiona el botón guardar
   * @param _event evento
   */
  protected abstract void accionGuardar(ActionEvent _event);

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  protected abstract void accionCancelar(ActionEvent _event);
}
