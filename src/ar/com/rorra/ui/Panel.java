package ar.com.rorra.ui;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.IEntidad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel genérico para la gestión de entidades.
 */
public abstract class Panel extends JPanel {
  private Controlador controlador;
  private JButton btnNuevo;
  private JButton btnModificar;
  private JButton btnEliminar;
  private JButton btnVolver;
  private JList entityList;
  DefaultListModel<IEntidad> listModel;

  /**
   * Constructor
   * @param controlador controlador principal de la aplicación
   */
  public Panel(Controlador controlador) {
    this.controlador = controlador;
    controlador.getFramePrincipal().setTitulo("Gestión de " + this.getEntityNamePlural());

    setLayout(new BorderLayout());

    construirLista();
    JPanel botones = construirBotones();

    add(entityList, BorderLayout.CENTER);
    add(botones, BorderLayout.SOUTH);
  }

  private void construirLista() {
    entityList = new JList();

    listModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(this.getEntityClass())) {
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
  }

  private JPanel construirBotones() {
    JPanel botones = new JPanel();

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

    return botones;
  }

  private void accionNuevo(ActionEvent _event) {
    controlador.panelNuevaEntidad(getEntityClass());
  }

  private void accionModificar(ActionEvent _event) {
    controlador.panelModificarEntidad((IEntidad) entityList.getSelectedValue());
  }

  private void accionEliminar(ActionEvent _event) {
    if (controlador.eliminarEntidad((IEntidad) entityList.getSelectedValue())) {
      listModel.removeElement(entityList.getSelectedValue());
    }
  }

  private void accionVolver(ActionEvent _event) {
    controlador.visualizarPantallaPrincipal();
  }

  /**
   * Devuelve la clase que el panel gestiona
   *
   */
  public abstract Class getEntityClass();

  /**
   * Devuelve la entidad del panel
   * @return entidad del panel
   */
  protected String getEntityName() {
    // Java metaprogramming, obtengo el método de la clase si existe, y en dicho caso lo llamo
    try {
      java.lang.reflect.Method method = getEntityClass().getMethod("getEntityName");
      return (String) method.invoke(null);
    } catch (Exception e) {
      // Si no existe el método, devuelvo nada
      return "";
    }
  }

  /**
   * Devuelove la entidad del panel en plural
   * @return entidad del panel en plural
   */
  protected String getEntityNamePlural() {
    // Java metaprogramming, obtengo el método de la clase si existe, y en dicho caso lo llamo
    try {
      java.lang.reflect.Method method = getEntityClass().getMethod("getEntityNamePlural");
      return (String) method.invoke(null);
    } catch (Exception e) {
      // Si no existe el método, devuelvo nada
      return "";
    }
  }
}
