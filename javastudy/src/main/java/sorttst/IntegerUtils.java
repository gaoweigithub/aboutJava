package sorttst;

import com.google.common.base.Optional;

public class IntegerUtils {
    public static Optional<Integer> StringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.absent();
        }
    }

}
