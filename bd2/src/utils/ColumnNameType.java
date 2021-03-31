package utils;

public class ColumnNameType {

    private final String name;
    private final Integer type;

    public ColumnNameType(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
