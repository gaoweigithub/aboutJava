package CglibTest;

public class SampleBean {
    public SampleBean(String value) {
        this.value = value;
    }
    public SampleBean() {
    }
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
