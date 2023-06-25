package ar.com.rorra.ui.turnos;

import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelTurnos extends JPanel {
  private Controlador controlador;
  private JButton btnNuevo;
  private JButton btnModificar;
  private JButton btnEliminar;
  private JButton btnVolver;
  private JList entityList;
  DefaultListModel<Turno> listModel;

  public PanelTurnos(Controlador controlador) {
    this.controlador = controlador;
    controlador.getFramePrincipal().setTitulo("Gestión de turnos");

    setLayout(new BorderLayout());

    construirLista();
    JPanel botones = construirBotones();

    add(entityList, BorderLayout.CENTER);
    add(botones, BorderLayout.SOUTH);
  }

  private void construirLista() {
    entityList = new JList();

    listModel = new DefaultListModel<>();
    for (IEntidad entidad : controlador.listarEntidades(Turno.class)) {
      listModel.addElement((Turno) entidad);
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

    btnNuevo = new JButton("Nuevo Turno");
    btnNuevo.addActionListener(e -> accionNuevo(e));
    botones.add(btnNuevo);

    btnModificar = new JButton("Modificar Turno");
    btnModificar.setEnabled(false);
    btnModificar.addActionListener(e -> accionModificar(e));
    botones.add(btnModificar);

    btnEliminar = new JButton("Eliminar Turno");
    btnEliminar.setEnabled(false);
    btnEliminar.addActionListener(e -> accionEliminar(e));
    botones.add(btnEliminar);

    btnVolver = new JButton("Volver");
    btnVolver.addActionListener(e -> accionVolver(e));
    botones.add(btnVolver);

    return botones;
  }

  private void accionNuevo(ActionEvent _event) {
    controlador.getFramePrincipal().setPanel(new PanelFormularioTurno(controlador, new Turno()));
  }

  private void accionModificar(ActionEvent _event) {
    controlador.getFramePrincipal().setPanel(new PanelFormularioTurno(controlador, (Turno) entityList.getSelectedValue()));
  }

  private void accionEliminar(ActionEvent _event) {
    if (controlador.eliminarEntidad((Turno) entityList.getSelectedValue())) {
      listModel.removeElement(entityList.getSelectedValue());
    }
  }

  private void accionVolver(ActionEvent _event) {
    controlador.getFramePrincipal().pantallaPrincipal();
  }
}
