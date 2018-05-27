package JUC.Condition控制线程通信;
/**
 * Condition 接口描述了可能会与锁有关联的条件变量。
 * 这些变量在用法上与使用Object.wait 访问的隐式监视器类似，但提供了更强大的功能。
 * 需要特别指出的是，单个Lock 可能与多个Condition 对象关联。
 * 为了避免兼容性问题，Condition 方法的名称与对应的Object 版本中的不同。
 * 在Condition 对象中，与wait、notify 和notifyAll 方法对应的分别是await、signal 和signalAll。
 * Condition 实例实质上被绑定到一个锁上。要为特定Lock 实例获得Condition 实例，请使用其newCondition() 方法。
 * */