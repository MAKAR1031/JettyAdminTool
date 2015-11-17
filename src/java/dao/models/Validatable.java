package dao.models;

public interface Validatable {
    boolean isValid();
    
    default boolean isValidString(String string) {
        return (string != null && !string.isEmpty());
    }
}