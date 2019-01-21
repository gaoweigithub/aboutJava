package g.model;

import java.util.List;

public class userInfo {
    private String id;
    private String phono;
    private List<String> recList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhono() {
        return phono;
    }

    public void setPhono(String phono) {
        this.phono = phono;
    }

    public List<String> getRecList() {
        return recList;
    }

    public void setRecList(List<String> recList) {
        this.recList = recList;
    }
}
