import com.gw.annotations.NettyController;
import com.gw.pojo.User;
import org.junit.Test;

import javax.swing.text.Document;
import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.regex.Pattern;

public class test {
    @Test
    public void testAnnotation(){
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5,a-zA-Z0-9]+$");
        System.out.println(pattern.matcher("高威").matches());
        System.out.println(pattern.matcher("高威1").matches());
        System.out.println(pattern.matcher("高威g").matches());
        System.out.println(pattern.matcher("高威,").matches());
        System.out.println(pattern.matcher("高威，").matches());
    }
}
