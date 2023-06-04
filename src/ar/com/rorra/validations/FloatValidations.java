package ar.com.rorra.validations;

public class FloatValidations {
    public static boolean isPositiveFloat(float number) {
        if (number <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveOrZeroFloat(float number) {
        if (number < 0) {
            return false;
        }
        return true;
    }

    public static boolean isNegativeFloat(float number) {
        if (number >= 0) {
            return false;
        }
        return true;
    }

    public static boolean isNegativeOrZeroFloat(float number) {
        if (number > 0) {
            return false;
        }
        return true;
    }

    public static boolean isInRangeFloat(float number, float min, float max) {
        if (number < min || number > max) {
            return false;
        }
        return true;
    }

    public static boolean isMinFloat(float number, float min) {
        if (number < min) {
            return false;
        }
        return true;
    }

    public static boolean isMaxFloat(float number, float max) {
        if (number > max) {
            return false;
        }
        return true;
    }
}