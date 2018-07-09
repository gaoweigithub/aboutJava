package reflecttest;

public class user {
    private String name;
    public String id;

    static String dd;
    static {
        System.out.println("init static");
        dd = "11";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
