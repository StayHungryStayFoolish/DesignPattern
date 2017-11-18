/**
 * Created by bonismo@hotmail.com
 * 下午12:49 on 17/11/18.
 * <p>
 * 工厂方法模式：
 * <p>
 * Product 抽象产品，定义产品接口。是工厂方法模式创建的超类型。是产品对象的公共父类。
 * Factory 抽象工厂，声明的工厂方法，用于返回一个产品。抽象工厂是工厂方法的核心，所有创建对象的工厂都必须实现该接口。
 * ConcreteFactory，是抽象工厂的子类，实现了抽象工厂的工厂方法，由客户端调用，返回产品实例。
 */
public class MethodFactory {
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        List<Integer> link = new LinkedList<>();

        // 添加数据到数组
        for (int i = 0; i < 10; i++) {
            array.add(i);
            link.add(i);
        }

        Iterator<Integer> arrayList = array.iterator();
        Iterator<Integer> linkedList = link.iterator();

        while (arrayList.hasNext()) {
            System.out.print(arrayList.next());
        }
        System.out.println();
        while (linkedList.hasNext()) {
            System.out.print(linkedList.next());
        }
    }
}

/*** Product 抽象产品，遍历数据 *****/
interface Iterator<T> {

    boolean hasNext();

    T next();
}

/*** Factory 抽象工厂  构造者 *****/
interface List<T> {

    // 返回一个便利器
    Iterator<T> iterator();

    // 添加元素到列表
    boolean add(T t);
}


/******** ConcreteFactory 具体工厂类 **********/
class ArrayList<T> implements List<T> {

    // 存放元素个数
    private int size;

    // 使用数组来存放元素
    private Object[] defaultList;

    // 数组初始长度
    private static final int DEFAULT_LENGTH = 10;

    // 无参构造，注入数组初始长度
    public ArrayList() {
        defaultList = new Object[DEFAULT_LENGTH];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    // 添加元素
    @Override
    public boolean add(T t) {
        if (size <= DEFAULT_LENGTH) {
            defaultList[size++] = t;
            return true;
        }
        return false;
    }

    // 私有内部类，具体产品
    private class MyIterator implements Iterator<T> {

        private int next;

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) defaultList[next++];
        }
    }
}

/******** ConcreteFactory 具体工厂类 **********/
class LinkedList<T> implements List<T> {

    private int size;

    private Node<T> first;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean add(T t) {
        if (size == 0) {
            first = new Node<T>(t, null);
            size++;
            return true;
        }
        Node<T> node = first;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<T>(t, null);
        size++;
        return true;
    }

    // 链表节点
    private static class Node<T> {

        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class MyIterator implements Iterator<T> {

        private Node<T> next;

        public MyIterator() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            T data = next.data;
            next = next.next;
            return data;
        }
    }

}
