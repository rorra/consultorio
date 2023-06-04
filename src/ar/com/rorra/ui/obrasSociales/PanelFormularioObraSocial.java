package ar.com.rorra.ui.obrasSociales;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFormularioObraSocial extends JPanel {
  private Controlador controlador;
  private ObraSocial obraSocial;
  private JLabel lblId;
  private JLabel lblNombre;
  private JLabel lblDescuento;

  private JTextField txtId;
  private JTextField txtNombre;
  private JTextField txtDescuento;

  public PanelFormularioObraSocial(Controlador controlador, ObraSocial obraSocial) {
    this.controlador = controlador;
    this.obraSocial = obraSocial;

    String titulo = obraSocial.isNew() ? "Nuevo Obra Social" : "Modificar Obra Social";
    controlador.getFramePrincipal().setTitulo(titulo);

    setLayout(new BorderLayout());

    add(construirFormulario(), BorderLayout.CENTER);

    add(construirBotones(), BorderLayout.SOUTH);
  }

  private JPanel construirFormulario() {
    JPanel form = new JPanel();
    int filas = obraSocial.isNew() ? 3 : 4;
    form.setLayout(new GridLayout(filas, 2, 10, 10));

    lblId = UI.crearLabel("ID: ");
    lblNombre = UI.crearLabel("Nombre: ");
    lblDescuento = UI.crearLabel("Descuento: ");

    txtId = UI.construirTextField(String.valueOf(obraSocial.getId()));
    txtId.setEditable(false);
    txtNombre = UI.construirTextField(obraSocial.getNombre());
    txtDescuento = UI.construirTextField(String.valueOf(obraSocial.getDescuento()));

    if (!obraSocial.isNew()) {
      form.add(lblId);
      form.add(txtId);
    }
    form.add(lblNombre);
    form.add(txtNombre);
    form.add(lblDescuento);
    form.add(txtDescuento);

    return form;
  }

  private JPanel construirBotones() {
    JPanel botones = new JPanel();

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.addActionListener(e -> accionGuardar(e));
    botones.add(btnGuardar);

    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(e -> accionCancelar(e));
    botones.add(btnCancelar);

    return botones;
  }

  private void accionGuardar(ActionEvent _event) {
    obraSocial.setNombre(txtNombre.getText());
    try {
      obraSocial.setDescuento(Integer.parseInt(txtDescuento.getText()));
    } catch (NumberFormatException _e) {
      controlador.getFramePrincipal().visualizarError("El descuento debe ser un numero entero entre 0 y 100");
      return;
    }


    if (obraSocial.isNew()) {
      if (controlador.insertarObraSocial(obraSocial)) {
        controlador.getFramePrincipal().visualizarObrasSociales();
      }
    } else {
      if (controlador.modificarObraSocial(obraSocial)) {
        controlador.getFramePrincipal().visualizarObrasSociales();
      }
    }
  }

  private void accionCancelar(ActionEvent _event) {
    controlador.getFramePrincipal().visualizarObrasSociales();
  }
}
