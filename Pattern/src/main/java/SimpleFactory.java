/**
 * Created by bonismo@hotmail.com
 * 下午12:17 on 17/11/18.
 * <p>
 * 简单工厂 -- 不属于 GOF(简称四人组) 23种设计模式
 * 客户端通过工厂类来创建一个产品的实例，无需直接使用 new 来创建对象，具体创建过程交由工厂来处理。
 * 但是简单工厂，不能设计一个包罗万象的产品类，而是根据实际需求来设计一个产品层次结构，将所有公共类的公共代码
 * 移至抽象产品类，并在抽象产品类中声明抽象方法。
 * 简单工厂模式不易维护，如果需要添加新的产品，工厂内的产品需要增加类别。
 * <p>
 * 主要优点：
 * 1、工厂类包含必要判断逻辑，可以决定声明时候创建哪一个产品类的实例。客户端免除直接创建产品对象的职责，而仅仅是"消费产品"，
 * 实现了对象创建与使用的分离。
 * 2、客户端无需知道所创建的产品类类名，只需知道对应参数即可，对于复杂类名，减少记忆量。
 * 3、可以通过引入配置文件，在不修改客户端代码情况，增加新产品。
 * <p>
 * 主要缺点：
 * 1、工厂中集中所有产品创建逻辑，职责过重，一旦不能正常工作，整个系统会受影响。
 * 2、增加系统中类的数量，增加系统复杂度和理解难度。
 * 3、扩展困难，一旦添加新产品，必须修改工厂逻辑，在产品类型较多时，可能造成逻辑过于复杂，不利于扩展和维护。
 * <p>
 * 适用场景：
 * 1、工厂创建对象比较少，由于创建对象少，工厂业务逻辑不会太过复杂。
 * 2、客户端只知道传入工厂的阐述，不关心如何创建对象。
 */
public class SimpleFactory {

    public static void main(String[] args) throws Exception {
        Factory factory = new Factory();
        factory.produce("BMW").run();
        factory.produce("BENZ").run();
    }
}


// Factory 简单工厂角色，负责产品的生成，使用 if...else...或者 switch...case...语句，负责产品类别选择
class Factory {
    Car produce(String product) throws Exception {
        if (product.equalsIgnoreCase("BMW")) {
            return new BMW();
        } else if (product.equalsIgnoreCase("BENZ")) {
            return new BENZ();
        }
        throw new Exception("No Such Class");
    }
}

// Product 抽象产品角色，可以使抽象类，也可以是接口，建议使用接口
abstract class Car {
    abstract void run();
}

// ConcreteProduct 具体产品角色
class BMW extends Car {

    @Override
    void run() {
        System.out.println("BMW 已经启动");
    }
}

// ConcreteProduct 具体产品角色
class BENZ extends Car {

    @Override
    void run() {
        System.out.println("BENZ 已经启动");
    }
}