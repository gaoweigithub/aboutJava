package 枚举;

public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private Size(String addreviation) {
        this.addrevition = addreviation;
    }

    String addrevition;

    public String getAddrevition() {
        return addrevition;
    }
}
