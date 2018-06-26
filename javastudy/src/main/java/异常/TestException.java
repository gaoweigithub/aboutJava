package 异常;


import java.io.File;

public class TestException {
    public static void main(String[] args) throws Throwable {
        try {
            File fi = new File("12");
            fi.delete();
            fi.getName();

        } catch (Exception e) {
            e.printStackTrace();
            Throwable newexception = new Exception();
            /**
             * 获取原始异常
             */
            e.getCause();
            newexception.initCause(e);
            throw newexception;
        } catch (Error error) {
            error.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
