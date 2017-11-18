/**
 * Created by bonismo@hotmail.com
 * 下午12:17 on 17/11/18.
 *
 * 简单工厂 -- 不属于 GOF(简称四人组) 23种设计模式
 * 简单工厂模式不易维护，如果需要添加新的产品，工厂内的产品需要增加类别。
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