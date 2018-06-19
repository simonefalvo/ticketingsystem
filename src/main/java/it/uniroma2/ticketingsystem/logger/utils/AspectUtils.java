package it.uniroma2.ticketingsystem.logger.utils;

public abstract class AspectUtils {

    /*
     *  Check if the option value has the default value
     */
    public static boolean defaultOption(Class<?> annotation, String optionName, Object option) {
        Object defaultValue = null;
        try {
            defaultValue = annotation.getDeclaredMethod(optionName).getDefaultValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return option.equals(defaultValue);
    }
}
