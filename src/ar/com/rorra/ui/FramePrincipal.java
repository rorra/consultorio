package ar.com.rorra.ui;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
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

  /**
   * El constructor crea el frame principal y lo muestra en pantalla
   * @param controlador
   */
  public FramePrincipal(Controlador controlador) {
    super("Consultorio V1.0");

    this.controlador = controlador;

    getContentPane().setLayout(new GridLayout(1, 1));
    setSize(1920, 1080);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
  }

  /**
   * Construye el menu, funcion a ser llamada luego del login
   */
  public void construirMenu() {
    setJMenuBar(crearMenu());
  }

  /**
   * Setea el título del frame principal
   * @param titulo El título a setear
   */
  public void setTitulo(String titulo) {
    if (titulo != null) {
      setTitle("Consultorio V1.0 - " + titulo);
    } else {
      setTitle("Consultorio V1.0");
    }
  }

  /**
   * Construye el formulario principal de la aplicación
   */
  public void pantallaPrincipal() {
    setTitle(null);

    JPanel panel = new JPanel();

    ArrayList<JButton> botones = new ArrayList<>();
    if (controlador.getUsuario() instanceof Administrador) {
      JButton btnGestionarAdministradores = new JButton("Gestionar administradores");
      botones.add(btnGestionarAdministradores);
      btnGestionarAdministradores.addActionListener(e -> controlador.visualizarAdministradores());

      JButton btnGestionarConsultorios = new JButton("Gestionar consultorios");
      botones.add(btnGestionarConsultorios);
      btnGestionarConsultorios.addActionListener(e -> controlador.visualizarConsultorios());

      JButton btnGestionarDoctores = new JButton("Gestionar doctores");
      botones.add(btnGestionarDoctores);
      btnGestionarDoctores.addActionListener(e -> controlador.visualizarDoctores());

      JButton btnGestionarObrasSociales = new JButton("Gestionar obras sociales");
      botones.add(btnGestionarObrasSociales);
      btnGestionarObrasSociales.addActionListener(e -> controlador.visualizarObrasSociales());

      JButton btnGestionarPacientes = new JButton("Gestionar pacientes");
      botones.add(btnGestionarPacientes);
      btnGestionarPacientes.addActionListener(e -> controlador.visualizarPacientes());

      JButton btnReportes = new JButton("Reportes");
      botones.add(btnReportes);
      btnReportes.addActionListener(e -> controlador.visualizarReportes());
    }

    JButton btnGestionarTurnos = new JButton("Gestionar turnos");
    botones.add(btnGestionarTurnos);
    btnGestionarTurnos.addActionListener(e -> controlador.visualizarTurnos());

    JButton btnSalir = new JButton("Salir");
    botones.add(btnSalir);
    btnSalir.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

    panel.setLayout(new GridLayout(botones.size(), 1));

    for (JButton boton : botones) {
      boton.setFont(new Font("Arial", Font.PLAIN, 20));
      panel.add(boton);
    }

    setPanel(panel);
  }

  /**
   * Visualiza un pantel en el content pane del frame principal
   * @param panel El panel a visualizar
   */
  public void setPanel(JPanel panel) {
    GridLayout constraints = new GridLayout(1, 1);

    getContentPane().removeAll();
    getContentPane().add(panel, constraints);
    getContentPane().revalidate();
  }

  /**
   * Menú principal de la aplicación
   * @return El menú principal
   */
  private JMenuBar crearMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu mnuArchivo = new JMenu("Archivo");
    mnuArchivo.setMnemonic('A');
    if (controlador.getUsuario() instanceof Administrador) {
      JMenuItem mnuAdministradores = new JMenuItem("Gestionar Administradores", 'A');
      JMenuItem mnuConsultorios = new JMenuItem("Gestionar Consultorios", 'C');
      JMenuItem mnuDoctores = new JMenuItem("Gestionar Doctores", 'D');
      JMenuItem mnuPacientes = new JMenuItem("Gestionar Pacientes", 'P');
      JMenuItem mnuReportes = new JMenuItem("Reportes", 'R');

      mnuAdministradores.addActionListener(e -> controlador.visualizarAdministradores());
      mnuDoctores.addActionListener(e -> controlador.visualizarDoctores());
      mnuPacientes.addActionListener(e -> controlador.visualizarPacientes());
      mnuReportes.addActionListener(e -> controlador.visualizarReportes());

      mnuArchivo.add(mnuDoctores);
      mnuArchivo.add(mnuAdministradores);
      mnuArchivo.add(mnuConsultorios);
      mnuArchivo.add(mnuPacientes);
    }
    JMenuItem mnuTurnos = new JMenuItem("Gestionar Turnos", 'T');
    JMenuItem mnuSalir = new JMenuItem("Salir", 'S');

    mnuTurnos.addActionListener(e -> controlador.visualizarTurnos());
    mnuSalir.addActionListener(e -> salir());

    mnuArchivo.add(mnuTurnos);
    mnuArchivo.addSeparator();
    mnuArchivo.add(mnuSalir);

    menuBar.add(mnuArchivo);

    return menuBar;
  }

  /**
   * Muestra un mensaje de información en pantalla
   * @param mensaje El mensaje de información
   */
  public void visualizarInformacion(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Muestra un mensaje de error en pantalla
   * @param error El mensaje de error
   */
  public void visualizarError(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Muestra un mensaje de error en pantalla
   * @param error La excepción que se produjo
   */
  public void visualizarError(Exception error) {
    JOptionPane.showMessageDialog(this, error.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Visualiza un panel en el content pane del frame principal
   * @param panel El panel a visualizar
   * @param <T> El tipo de panel a visualizar
   */
  public<T extends JPanel> void visualizarPanel(T panel) {
    try {
      setPanel(panel);
    } catch (RuntimeException e) {
      visualizarError(e);
    }
  }

  /**
   * Cierra la aplicación
   */
  public void salir() {
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}
