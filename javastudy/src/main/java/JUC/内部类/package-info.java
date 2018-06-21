package JUC.内部类;
/**
 * 根据Oracle官方的说法：
 * Nested classes are divided into two categories: static and non-static.
 * Nested classes that are declared static are called static nested classes.
 * Non-static nested classes are called inner classes.
 * 从字面上看，一个被称为静态嵌套类，一个被称为内部类。
 * 从字面的角度解释是这样的：什么是嵌套？嵌套就是我跟你没关系，自己可以完全独立存在，
 * 但是我就想借你的壳用一下，来隐藏一下我自己（真TM猥琐）。
 * 什么是内部？内部就是我是你的一部分，我了解你，我知道你的全部，没有你就没有我。
 * （所以内部类对象是以外部类对象存在为前提的）至于具体的使用场景，我就不当翻译工了，有兴趣的直接去官网看吧。
 * 传送门：http://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
 * 作者：昭言
 * 链接：https://www.zhihu.com/question/28197253/answer/39814613
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */