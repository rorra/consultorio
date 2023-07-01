package ar.com.rorra.ui.turnos;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.ui.PanelLista;

import javax.swing.*;
import java.util.List;

public class PanelTurnos extends PanelLista {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelTurnos(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la lista de entidades desde el almacenamiento de datos
   * @return lista de entidades
   * @param <T> tipo de entidad
   */
  protected <T extends IEntidad> List<T> listarEntidades() {
    if (controlador.getUsuario() instanceof Administrador) {
      return controlador.listarEntidades(this.getEntityClass(), "fecha");
    } else {
      return controlador.listarEntidades(this.getEntityClass(), controlador.getUsuario());
    }
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return Turno.class;
  }

  /**
   * Construye los botones de la pantalla, si el usuario logueado es un paciente o doctor, los limita
   * @return panel con los botones
   */
  @Override
  protected void construirBotones() {
    if (controlador.getUsuario() instanceof Administrador) {
      super.construirBotones();
    } else {
      botones = new JPanel();

      btnVolver = new JButton("Volver");
      btnVolver.addActionListener(e -> accionVolver(e));
      botones.add(btnVolver);
    }
  }
}
