package ar.com.rorra.ui;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.ui.administradores.PanelAdministradores;
import ar.com.rorra.ui.consultorios.PanelConsultorios;
import ar.com.rorra.ui.doctores.PanelDoctores;
import ar.com.rorra.ui.obrasSociales.PanelObrasSociales;
import ar.com.rorra.ui.pacientes.PanelPacientes;
import ar.com.rorra.ui.turnos.PanelTurnos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class FramePrincipal extends JFrame {
  private Controlador controlador;

  public FramePrincipal(Controlador controlador) {
    super("Consultorio V1.0");

    this.controlador = controlador;

    getContentPane().setLayout(new GridLayout(1, 1));
    setSize(1920, 1080);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setJMenuBar(crearMenu());

    pantallaPrincipal();

    setVisible(true);
  }

  public void setTitulo(String titulo) {
    if (titulo != null) {
      setTitle("Consultorio V1.0 - " + titulo);
    } else {
      setTitle("Consultorio V1.0");
    }
  }

  public void pantallaPrincipal() {
    JPanel panel = new JPanel();

    ArrayList<JButton> botones = new ArrayList<>();
    JButton btnGestionarAdministradores = new JButton("Gestionar administradores");
    botones.add(btnGestionarAdministradores);
    JButton btnGestionarConsultorios = new JButton("Gestionar consultorios");
    botones.add(btnGestionarConsultorios);
    JButton btnGestionarDoctores = new JButton("Gestionar doctores");
    botones.add(btnGestionarDoctores);
    JButton btnGestionarObrasSociales = new JButton("Gestionar obras sociales");
    botones.add(btnGestionarObrasSociales);
    JButton btnGestionarPacientes = new JButton("Gestionar pacientes");
    botones.add(btnGestionarPacientes);
    JButton btnGestionarTurnos = new JButton("Gestionar turnos");
    botones.add(btnGestionarTurnos);
    JButton btnSalir = new JButton("Salir");
    botones.add(btnSalir);

    btnGestionarAdministradores.addActionListener(e -> visualizarAdministradores());
    btnGestionarConsultorios.addActionListener(e -> visualizarConsultorios());
    btnGestionarDoctores.addActionListener(e -> visualizarDoctores());
    btnGestionarObrasSociales.addActionListener(e -> visualizarObrasSociales());
    btnGestionarPacientes.addActionListener(e -> visualizarPacientes());
    btnGestionarTurnos.addActionListener(e -> visualizarTurnos());
    btnSalir.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

    panel.setLayout(new GridLayout(botones.size(), 1));

    for (JButton boton : botones) {
      boton.setFont(new Font("Arial", Font.PLAIN, 20));
      panel.add(boton);
    }

    setPanel(panel);
  }

  public void setPanel(JPanel panel) {
    GridLayout constraints = new GridLayout(1, 1);

    getContentPane().removeAll();
    getContentPane().add(panel, constraints);
    getContentPane().revalidate();
  }

  private JMenuBar crearMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu mnuArchivo = new JMenu("Archivo");
    mnuArchivo.setMnemonic('A');
    JMenuItem mnuAdministradores = new JMenuItem("Gestionar Administradores", 'A');
    JMenuItem mnuConsultorios = new JMenuItem("Gestionar Consultorios", 'C');
    JMenuItem mnuDoctores = new JMenuItem("Gestionar Doctores", 'D');
    JMenuItem mnuPacientes = new JMenuItem("Gestionar Pacientes", 'P');
    JMenuItem mnuTurnos = new JMenuItem("Gestionar Turnos", 'T');
    JMenuItem mnuSalir = new JMenuItem("Salir", 'S');

    mnuAdministradores.addActionListener(e -> visualizarAdministradores());
    mnuDoctores.addActionListener(e -> visualizarDoctores());
    mnuPacientes.addActionListener(e -> visualizarPacientes());
    mnuTurnos.addActionListener(e -> visualizarTurnos());
    mnuSalir.addActionListener(e -> salir());

    mnuArchivo.add(mnuDoctores);
    mnuArchivo.add(mnuAdministradores);
    mnuArchivo.add(mnuConsultorios);
    mnuArchivo.add(mnuPacientes);
    mnuArchivo.add(mnuTurnos);
    mnuArchivo.addSeparator();
    mnuArchivo.add(mnuSalir);

    menuBar.add(mnuArchivo);

    return menuBar;
  }

  public void visualizarInformacion(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
  }

  public void visualizarError(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public void visualizarError(Exception error) {
    JOptionPane.showMessageDialog(this, error.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }

  public<T extends JPanel> void visualizarPanel(T panel) {
    try {
      setPanel(panel);
    } catch (RuntimeException e) {
      visualizarError(e);
    }
  }

  public void visualizarAdministradores() {
    visualizarPanel(new PanelAdministradores(controlador));
  }

  public void visualizarConsultorios() {
    visualizarPanel(new PanelConsultorios(controlador));
  }

  public void visualizarDoctores() {
    visualizarPanel(new PanelDoctores(controlador));
  }

  public void visualizarObrasSociales() {
    visualizarPanel(new PanelObrasSociales(controlador));
  }

  public void visualizarPacientes() {
    visualizarPanel(new PanelPacientes(controlador));
  }

  public void visualizarTurnos() {
    visualizarPanel(new PanelTurnos(controlador));
  }

  public void salir() {
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}
