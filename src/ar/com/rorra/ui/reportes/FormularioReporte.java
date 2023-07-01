package ar.com.rorra.ui.reportes;

import ar.com.rorra.bo.PacienteBO;
import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.*;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.util.UI;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class FormularioReporte extends JPanel {
  private Controlador controlador;
  private JLabel lblFechaDesde;
  private JLabel lblFechaHasta;
  private JTextField txtFechaDesde;
  private JTextField txtFechaHasta;
  private JPanel botones;
  private JPanel form;
  JTable tabla;
  JScrollPane scrollPane;
  TableModel dataModel;

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
    this.add(construirTabla(), BorderLayout.CENTER);
  }

  /**
   * Construye el formulario para generar el reporte
   * @return
   */
  protected JPanel construirFormulario() {
    form = new JPanel();
    form.setLayout(new GridLayout(0, 2, 10, 10));

    lblFechaDesde = UI.crearLabel("Fecha desde (yyyy/MM/dd HH:mm): ");
    lblFechaHasta = UI.crearLabel("Fecha hasta (yyyy/MM/dd HH:mm): ");

    txtFechaDesde = UI.construirTextField("");
    txtFechaHasta = UI.construirTextField("");

    form.add(lblFechaDesde);
    form.add(txtFechaDesde);
    form.add(lblFechaHasta);
    form.add(txtFechaHasta);

    return form;
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
   * Genera el reporte con los datos ingresados
   * @param desde fecha desde
   * @param hasta fecha hasta
   */
  private void generarReporte(LocalDateTime desde, LocalDateTime hasta) {
    dataModel = new javax.swing.table.DefaultTableModel(
      obtenerFilas(desde, hasta),
      new String [] {
        "ID", "Nombre", "Tarifa", "Recaudación"
      }
    );
    tabla.setModel(dataModel);
  }

  /**
   * Obtiene las filas del reporte, indicando los diferentes doctores, su costo y su recaudación entre dos fechas
   * @param desde fecha desde
   * @param hasta fecha hasta
   * @return filas del reporte
   */
  private String[][] obtenerFilas(LocalDateTime desde, LocalDateTime hasta) {
    ArrayList<String[]> filas = controlador.ingresosPorMedico(desde, hasta);
    return filas.toArray(new String[filas.size()][]);
  }

  /**
   * Construye una tabla con las filas indicadas
   * @return
   */
  private JScrollPane construirTabla() {
    tabla = new JTable();

    dataModel = new javax.swing.table.DefaultTableModel(
      new Object [][]{},
      new String [] {
        "ID", "Nombre", "Tarifa", "Recaudación"
      }
    );

    tabla.setModel(dataModel);

    scrollPane = new JScrollPane(tabla);
    tabla.setFillsViewportHeight(true);

    return scrollPane;
  }

  /**
   * Acción a ejecutar cuando se presiona el botón generar
   * @param _event evento
   */
  private void accionGenerar(ActionEvent _event) {
    try {
      LocalDateTime desde = parseFecha(txtFechaDesde.getText(), "Fecha desde");
      LocalDateTime hasta = parseFecha(txtFechaHasta.getText(), "Fecha hasta");
      if (desde.isAfter(hasta)) {
        throw new RuntimeException("La fecha hasta debe ser mayor a la fecha desde");
      }
      generarReporte(desde, hasta);
    } catch (RuntimeException e) {
      controlador.getFramePrincipal().visualizarError(e.getMessage());
    }
  }

  /**
   * Acción a ejecutar cuando se presiona el botón cancelar
   * @param _event evento
   */
  private void accionCancelar(ActionEvent _event) {
    controlador.visualizarMenuPrincipal();
  }

  private LocalDateTime parseFecha(String fecha, String campo) throws RuntimeException {
    try {
      return LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern(Turno.FORMATO_FECHA));
    } catch (Exception e) {
      throw new RuntimeException("El campo " + campo + " tiene una fecha inválida");
    }
  }
}
