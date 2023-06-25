package ar.com.rorra.ui;

import javax.swing.*;

public abstract class Panel extends JPanel {
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
