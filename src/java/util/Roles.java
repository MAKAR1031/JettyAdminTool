package util;

public enum Roles {
    ADMIN("admin"), USER("user");
    
    private final String name;

    private Roles(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
