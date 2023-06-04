package ar.com.rorra.ui.pacientes;

import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelPacientes extends JPanel {
  private Controlador controlador;
  private JButton btnNuevo;
  private JButton btnModificar;
  private JButton btnEliminar;
  private JButton btnVolver;
  private JList entityList;
  DefaultListModel<Paciente> listModel;

  public PanelPacientes(Controlador controlador) {
    this.controlador = controlador;
    controlador.getFramePrincipal().setTitulo("Gestión de pacientes");

    setLayout(new BorderLayout());

    construirLista();
    JPanel botones = construirBotones();

    add(entityList, BorderLayout.CENTER);
    add(botones, BorderLayout.SOUTH);
  }

  private void construirLista() {
    entityList = new JList();

    listModel = new DefaultListModel<>();
    for (Paciente entidad : controlador.listarPacientes()) {
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

    btnNuevo = new JButton("Nuevo Paciente");
    btnNuevo.addActionListener(e -> accionNuevo(e));
    botones.add(btnNuevo);

    btnModificar = new JButton("Modificar Paciente");
    btnModificar.setEnabled(false);
    btnModificar.addActionListener(e -> accionModificar(e));
    botones.add(btnModificar);

    btnEliminar = new JButton("Eliminar Paciente");
    btnEliminar.setEnabled(false);
    btnEliminar.addActionListener(e -> accionEliminar(e));
    botones.add(btnEliminar);

    btnVolver = new JButton("Volver");
    btnVolver.addActionListener(e -> accionVolver(e));
    botones.add(btnVolver);

    return botones;
  }

  private void accionNuevo(ActionEvent _event) {
    controlador.getFramePrincipal().setPanel(new PanelFormularioPaciente(controlador, new Paciente()));
  }

  private void accionModificar(ActionEvent _event) {
    controlador.getFramePrincipal().setPanel(new PanelFormularioPaciente(controlador, (Paciente) entityList.getSelectedValue()));
  }

  private void accionEliminar(ActionEvent _event) {
    if (controlador.eliminarPaciente((Paciente) entityList.getSelectedValue())) {
      listModel.removeElement(entityList.getSelectedValue());
    }
  }

  private void accionVolver(ActionEvent _event) {
    controlador.getFramePrincipal().pantallaPrincipal();
  }
}
