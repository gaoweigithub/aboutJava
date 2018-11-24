package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;

public class test {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("ggww");
        String json = JSON.toJSONString(s1);

        FastJsonConfig config = new FastJsonConfig();

        NewStudent s2 = JSON.parseObject(json,NewStudent.class);

        System.out.println(json);
        System.out.println(s2.getNaMe());
    }
}
