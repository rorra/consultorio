package ar.com.rorra.validations;

public class IntegerValidations {
    public static boolean isPositiveInteger(int number) {
        if (number <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveOrZeroInteger(int number) {
        if (number < 0) {
            return false;
        }
        return true;
    }

    public static boolean isNegativeInteger(int number) {
        if (number >= 0) {
            return false;
        }
        return true;
    }

    public static boolean isNegativeOrZeroInteger(int number) {
        if (number > 0) {
            return false;
        }
        return true;
    }

    public static boolean isInRangeInteger(int number, int min, int max) {
        if (number < min || number > max) {
            return false;
        }
        return true;
    }

    public static boolean isMinInteger(int number, int min) {
        if (number < min) {
            return false;
        }
        return true;
    }

    public static boolean isMaxInteger(int number, int max) {
        if (number > max) {
            return false;
        }
        return true;
    }
}
