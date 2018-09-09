package reflecttest;

public class user2 {
    private String name;
    public String id;

    static String dd;
    static {
        System.out.println("init static 2");
        dd = "22";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
