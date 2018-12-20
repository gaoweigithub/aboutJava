package demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class FirstAgent implements ClassFileTransformer {
    public final String injectedClassName = "agentDemo.App";
    public final String methodName = "hello";

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
//        System.out.println(className);
//        if (className.equals(injectedClassName)) {
        CtClass ctclass = null;
        try {
            ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
            CtMethod[] ctMethods = ctclass.getDeclaredMethods();
            if (ctMethods != null) {
                for (CtMethod method : ctMethods) {
                    if (method.getLongName().contains(injectedClassName)) {
                        method.insertBefore("System.out.println(\"正要执行的方法是:" + method.getName() + "\");");
                    }
                }
            }
//            CtMethod ctmethod = ctclass.getDeclaredMethod(methodName);// 得到这方法实例
//            ctmethod.insertBefore("System.out.println(11111111);");
            return ctclass.toBytecode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        }
        return null;
    }
}