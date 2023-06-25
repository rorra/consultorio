package ar.com.rorra.ui;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.IEntidad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Panel genérico para la gestión de entidades.
 */
public abstract class PanelLista extends Panel {
  protected Controlador controlador;
  protected JButton btnNuevo;
  protected JButton btnModificar;
  protected JButton btnEliminar;
  protected JButton btnVolver;
  protected JPanel botones;
  protected JList entityList;
  protected JScrollPane scrollPane;
  protected DefaultListModel<IEntidad> listModel;

  /**
   * Constructor
   * @param controlador controlador principal de la aplicación
   */
  public PanelLista(Controlador controlador) {
    this.controlador = controlador;
    controlador.getFramePrincipal().setTitulo("Gestión de " + this.getEntityNamePlural());

    setLayout(new BorderLayout());

    construirLista();
    construirBotones();

    add(scrollPane, BorderLayout.CENTER);
    add(botones, BorderLayout.SOUTH);
  }

  /**
   * Construye la lista de entidades.
   */
  protected void construirLista() {
    entityList = new JList();

    listModel = new DefaultListModel<>();
    for (IEntidad entidad : this.listarEntidades()) {
      listModel.addElement(entidad);
    }
    entityList.setModel(listModel);

    entityList.addListSelectionListener(e -> {
      if (entityList.getSelectedValue() == null) {
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
      } else {
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
      }
    });

    scrollPane = new JScrollPane(entityList);
  }

  /**
   * Devuelve la lista de entidades desde el almacenamiento de datos
   * @return lista de entidades
   * @param <T> tipo de entidad
   */
  protected <T extends IEntidad> List<T> listarEntidades() {
    return controlador.listarEntidades(this.getEntityClass());
  }

  /**
   * Construye los botones de la pantalla
   * @return panel con los botones
   */
  protected void construirBotones() {
    botones = new JPanel();

    btnNuevo = new JButton("Nuevo " + this.getEntityName());
    btnNuevo.addActionListener(e -> accionNuevo(e));
    botones.add(btnNuevo);

    btnModificar = new JButton("Modificar " + this.getEntityName());
    btnModificar.setEnabled(false);
    btnModificar.addActionListener(e -> accionModificar(e));
    botones.add(btnModificar);

    btnEliminar = new JButton("Eliminar " + this.getEntityName());
    btnEliminar.setEnabled(false);
    btnEliminar.addActionListener(e -> accionEliminar(e));
    botones.add(btnEliminar);

    btnVolver = new JButton("Volver");
    btnVolver.addActionListener(e -> accionVolver(e));
    botones.add(btnVolver);
  }

  /**
   * Acción del botón nuevo, que crea una nueva entidad
   * @param _event evento
   */
  protected void accionNuevo(ActionEvent _event) {
    controlador.panelNuevaEntidad(getEntityClass());
  }

  /**
   * Acción del botón modificar, que modifica la entidad seleccionada
   * @param _event evento
   */
  protected void accionModificar(ActionEvent _event) {
    controlador.panelModificarEntidad((IEntidad) entityList.getSelectedValue());
  }

  /**
   * Acción del botón eliminar, que elimina la entidad seleccionada
   * @param _event evento
   */
  protected void accionEliminar(ActionEvent _event) {
    if (controlador.eliminarEntidad((IEntidad) entityList.getSelectedValue())) {
      listModel.removeElement(entityList.getSelectedValue());
    }
  }

  /**
   * Acción del botón volver, que vuelve a la pantalla principal
   * @param _event
   */
  protected void accionVolver(ActionEvent _event) {
    controlador.visualizarMenuPrincipal();
  }
}
