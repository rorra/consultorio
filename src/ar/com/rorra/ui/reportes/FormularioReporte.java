package ar.com.rorra.ui.reportes;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioReporte extends JPanel {
  private Controlador controlador;
  private JLabel lblFechaDesde;
  private JLabel lblFechaHasta;
  private JTextField txtFechaDesde;
  private JTextField txtFechaHasta;
  private JPanel botones;
  private JPanel form;
  private JPanel reporte;

  /**
   * Constructor
   * @param controlador
   */
  public FormularioReporte(Controlador controlador) {
    this.controlador = controlador;

    controlador.getFramePrincipal().setTitulo("Reportes");

    setLayout(new BorderLayout());

    construirFormulario();
    construirBotones();

    JPanel formularioYBotones = new JPanel(new BorderLayout());
    formularioYBotones.add(form, BorderLayout.CENTER);
    formularioYBotones.add(botones, BorderLayout.SOUTH);

    this.add(formularioYBotones, BorderLayout.NORTH);
  }

  /**
   * Construye el formulario para generar el reporte
   * @return
   */
  protected JPanel construirFormulario() {
    form = new JPanel();
    form.setLayout(new GridLayout(0, 2, 10, 10));

    lblFechaDesde = UI.crearLabel("Fecha desde: ");
    lblFechaHasta = UI.crearLabel("Fecha hasta: ");

    txtFechaDesde = UI.construirTextField("");
    txtFechaHasta = UI.construirTextField("");

    form.add(lblFechaDesde);
    form.add(txtFechaDesde);
    form.add(lblFechaHasta);
    form.add(txtFechaHasta);

    return form;
  }

  protected JPanel construirReporte() {
    JPanel reporte = new JPanel();

    return reporte;
  }

  /**
   * Construye los botones de la pantalla
   * @return panel con los botones
   */
  protected void construirBotones() {
    botones = new JPanel();

    JButton btnGuardar = new JButton("Generar");
    btnGuardar.addActionListener(e -> accionGenerar(e));
    botones.add(btnGuardar);

    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(e -> accionCancelar(e));
    botones.add(btnCancelar);
  }

  /**
   * Acción a ejecutar cuando se presiona el botón generar
   * @param _event evento
   */
  private void accionGenerar(ActionEvent _event) {

  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  private void accionCancelar(ActionEvent _event) {
    controlador.visualizarMenuPrincipal();
  }
}
