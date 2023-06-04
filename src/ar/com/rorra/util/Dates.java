package ar.com.rorra.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Dates {
  public static LocalDateTime parse(String fecha) {
    String[] parts = fecha.split(" ");
    String[] dateParts = parts[0].split("-");
    String[] timeParts = parts[1].split(":");
    return LocalDateTime.of(
      Integer.parseInt(dateParts[0]),
      Integer.parseInt(dateParts[1]),
      Integer.parseInt(dateParts[2]),
      Integer.parseInt(timeParts[0]),
      Integer.parseInt(timeParts[1]),
      Integer.parseInt(timeParts[2])
    );
  }

  public static String format(LocalDateTime fecha) {
    return fecha.getYear() + "-" +
      String.format("%02d", fecha.getMonthValue()) + "-" +
      String.format("%02d", fecha.getDayOfMonth()) + " " +
      String.format("%02d", fecha.getHour()) + ":" +
      String.format("%02d", fecha.getMinute());
  }

  /**
   * Obtiene el proximo dia laboral (L-V) a partir de la fecha actual, a las 9 AM
   *
   * @return Fecha del proximo dia laboral
   */
  public static LocalDateTime getNextWorkDay() {
    return getNextWorkDay(null);
  }

  /**
   * Obtiene el proximo dia laboral (L-V) a partir de la fecha pasada por parametro, a las 9 AM
   *
   * @param fecha Fecha a partir de la cual se busca el proximo dia laboral
   * @return Fecha del proximo dia laboral
   */
  public static LocalDateTime getNextWorkDay(LocalDateTime fecha) {
    if (fecha == null) {
      fecha = LocalDateTime.now();
    }

    fecha.plusDays(1);
    fecha = fecha.withHour(9).withMinute(0).withSecond(0).withNano(0);

    while (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
      fecha = fecha.plusDays(1);
    }

    return fecha;
  }
}
