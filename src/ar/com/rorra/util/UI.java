package ar.com.rorra.util;

import javax.swing.*;

/**
 * Utilidades para la interfaz grafica
 */
public class UI {
  /**
   * Construye un JLabel con el texto pasado por parametro
   * @param texto texto del JLabel
   * @return
   */
  public static JLabel crearLabel(String texto) {
    JLabel label = new JLabel(texto);
    label.setHorizontalAlignment(JLabel.CENTER);
    return label;
  }

  /**
   * Construye un JTextField con el texto pasado por parametro
   * @param texto texto del JTextField
   * @return
   */
  public static JTextField construirTextField(String texto) {
    JTextField textField = new JTextField();
    textField.setText(texto);
    return textField;
  }
}
