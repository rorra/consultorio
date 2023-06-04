package ar.com.rorra.ui.doctores;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelDoctores extends JPanel {
  private Controlador controlador;
  private JButton btnNuevo;
  private JButton btnModificar;
  private JButton btnEliminar;
  private JButton btnVolver;
  private JList entityList;
  DefaultListModel<Doctor> listModel;

  public PanelDoctores(Controlador controlador) {
    this.controlador = controlador;
    controlador.getFramePrincipal().setTitulo("Gestión de doctores");

    setLayout(new BorderLayout());

    construirLista();
    JPanel botones = construirBotones();

    add(entityList, BorderLayout.CENTER);
    add(botones, BorderLayout.SOUTH);
  }

  private void construirLista() {
    entityList = new JList();

    listModel = new DefaultListModel<>();
    for (Doctor entidad : controlador.listarDoctores()) {
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

    btnNuevo = new JButton("Nuevo Doctor");
    btnNuevo.addActionListener(e -> accionNuevo(e));
    botones.add(btnNuevo);

    btnModificar = new JButton("Modificar Doctor");
    btnModificar.setEnabled(false);
    btnModificar.addActionListener(e -> accionModificar(e));
    botones.add(btnModificar);

    btnEliminar = new JButton("Eliminar Doctor");
    btnEliminar.setEnabled(false);
    btnEliminar.addActionListener(e -> accionEliminar(e));
    botones.add(btnEliminar);

    btnVolver = new JButton("Volver");
    btnVolver.addActionListener(e -> accionVolver(e));
    botones.add(btnVolver);

    return botones;
  }

  private void accionNuevo(ActionEvent _event) {
    controlador.getFramePrincipal().setPanel(new PanelFormularioDoctor(controlador, new Doctor()));
  }

  private void accionModificar(ActionEvent _event) {
    controlador.getFramePrincipal().setPanel(new PanelFormularioDoctor(controlador, (Doctor) entityList.getSelectedValue()));
  }

  private void accionEliminar(ActionEvent _event) {
    if (controlador.eliminarDoctor((Doctor) entityList.getSelectedValue())) {
      listModel.removeElement(entityList.getSelectedValue());
    }
  }

  private void accionVolver(ActionEvent _event) {
    controlador.getFramePrincipal().pantallaPrincipal();
  }
}
