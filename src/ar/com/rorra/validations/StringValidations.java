package ar.com.rorra.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidations {
  public static boolean isEmpty(String value) {
    if (value != null && value.length() > 0) {
      return false;
    }
    return true;
  }

  public static boolean isPresent(String value) {
    if (value == null || value.isEmpty()) {
      return false;
    }
    return true;
  }

  public static boolean isMinLength(String value, int min) {
    if (value == null || value.length() < min) {
      return false;
    }
    return true;
  }

  public static boolean isMaxLength(String value, int max) {
    if (value == null || value.length() > max) {
      return false;
    }
    return true;
  }

  public static boolean isInRangeLength(String value, int min, int max) {
    if (value == null || value.length() < min || value.length() > max) {
      return false;
    }
    return true;
  }

  public static boolean isOnlyNumbers(String value) {
    if (value == null) return false;

    try {
      Double.parseDouble(value);
    } catch (NumberFormatException _nfe) {
      return false;
    }

    return true;
  }

  public static boolean isOnlyChars(String value) {
    if (value == null) return false;

    if (value.matches(".*[0-9].*")) {
      return false;
    }

    return true;
  }

  public static boolean isNumbersAndChars(String value) {
    if (value == null) return false;

    if (!value.matches(".*[0-9].*") || !value.matches(".*[a-zA-Z].*")) {
      return false;
    }

    return true;
  }

  public static boolean validEmail(String email) {
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    if (!matcher.matches()) {
      return false;
    }

    return true;
  }
}
