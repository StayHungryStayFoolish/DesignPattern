/**
 * Created by bonismo@hotmail.com
 * 上午1:03 on 17/11/21.
 * <p>
 * 推荐使用
 * 4、双重校验锁 -- 模式
 * 5、静态内部类 -- 模式
 * <p>
 * 保证只创建一次类实例
 * 延迟加载
 * 线程安全
 * <p>
 * 推荐使用
 * 4、双重校验锁 -- 模式
 * 5、静态内部类 -- 模式
 * <p>
 * 保证只创建一次类实例
 * 延迟加载
 * 线程安全
 */
/**
 *  推荐使用
 *  4、双重校验锁 -- 模式
 *  5、静态内部类 -- 模式
 *
 *  保证只创建一次类实例
 *  延迟加载
 *  线程安全
 */

/**
 * 1、饿汉模式
 * 定义 构造函数为 private ，保证其他类不能实例化该类。
 * 饿汉模式在类加载时候对实例进行创建，实例存在于整个程序周期。
 * <p>
 * 优点：
 * 只在类加载时，创建一次实例，不会存在多个线程创建多个实例情况，避免多线程同步问题。
 * <p>
 * 缺点：
 * 不管有没有用到，类加载之后都被创建，占用内存。
 * <p>
 * 适合场景：
 * 经常需要使用单例，如果特定场合才使用则不适合。
 */
public class Singleton {

    private final static Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    ;

    public static Singleton newInstance() {
        return INSTANCE;
    }
}

/**
 * 2、懒汉模式
 * 懒汉模式的单例是在需要时才会被创建的（初始赋值为 Null），如果单例已经创建,再次调用获取接口，不会重新创建新的对象，而是直接返回之前创建的饿。
 * <p>
 * 优点：
 * 单例使用次数少，并且创建单例小号资源较多，可以按需创建。选择懒汉模式。
 * <p>
 * 缺点：
 * 没有考虑多线程安全问题，多线程可能会调用，导致创建多个实例。需要枷锁解决线程同步。
 */
class SingletonTwo {

    private static SingletonTwo instance;

    private SingletonTwo() {
    }

    public static SingletonTwo newInstance() {
        if (null == instance) {
            instance = new SingletonTwo();
        }
        return instance;
    }
}

/**
 * 3、懒汉加锁模式、解决线程同步问题
 * 解决了线程并发的问题，实现了燕池加载，但是性能存在问题，由 synchronized 修饰的同步方法比一般方法要慢很多
 * 如果多次调用 累积的消耗太大。
 */
class SingletonThree {

    private static SingletonThree instance;

    private SingletonThree() {

    }

    public static synchronized SingletonThree newInstance() {
        if (null == instance) {
            instance = new SingletonThree();
        }
        return instance;
    }
}

/**
 * 4、懒汉模式、双重检验锁
 * 由于单例对象只需创建一次，所以调用 方法是，不会执行到 同步块 synchronized，从而提高了程序性能
 * 当实例存在时，直接返回 单例创建的实例，只有不存在时，才会进入同步块，进行创建。而以后多次调用，依然不会进入同步块。所以程序性能得到很大提高。
 * <p>
 * 优点：
 * 既解决了延迟加载，效率慢的问题
 * 又解决了线程并发的问题，提高了执行效率
 * <p>
 * 由于域前边有 volatile [ /'vɒlətaɪl/ (不稳定) ] 修饰，所以禁止了 初始化 SingletonFour 和将对象地址复制给 instance 的不确定性。 ******
 * <p>
 * JVM 的指令重排优化，在不改变原语义的情况下，通过调整命令的执行顺序，让程序运行的更快。JVM 没有规定编译器优化相关内容，也就是 JVM 会自由进行指令重排优化。
 * 所以 volatile 就是解决 指令重排优化导致的 双重校验锁失效问题。
 */
class SingletonFour {

    private static volatile SingletonFour instance;

    private SingletonFour() {

    }

    public static SingletonFour newInstance() {
        if (null == instance) {
            synchronized (SingletonFour.class) {  // 1、null 时，同步
                if (null == instance) {           // 2、null时，创建实例
                    instance = new SingletonFour();
                }
            }
        }
        return instance;
    }
}

/**
 * 5、静态内部类
 * <p>
 * 优点：
 * 保证类加载机制只创建一个实例。类似饿汉模式，利用类加载机制，不存在多线程并发问题。
 * 区别是，在内部类里面创建了对象实例，保证了应用中只要不适用内部类，JVM 就不会去加载这个单例类，
 * 也不会去创建单例对象，从而又实现了懒汉模式的延迟加载。
 * <p>
 * 保证了延迟加载，又维护了多线程的安全问题。
 */
class SingletonFive {

    private SingletonFive() {
    }

    private static class SingletonHolder {
        public static SingletonFive instance = new SingletonFive();
    }

    public static SingletonFive newInstance() {
        return SingletonHolder.instance;
    }

    ;
}

class Test {
    public static void main(String[] args) {
        System.out.println(Singleton.newInstance());
        System.out.println(SingletonTwo.newInstance());
        System.out.println(SingletonThree.newInstance());
        System.out.println(SingletonFour.newInstance());
    }
}