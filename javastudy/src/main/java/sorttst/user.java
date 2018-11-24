package sorttst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class user {
    private int sortValue;

    public user(int sortValue) {
        this.sortValue = sortValue;
    }

    public int getSortValue() {
        return sortValue;
    }

    public void setSortValue(int sortValue) {
        this.sortValue = sortValue;
    }


    public static void main(String[] args) {
        System.out.println(IntegerUtils.StringToInt("11").isPresent());
    }
}
