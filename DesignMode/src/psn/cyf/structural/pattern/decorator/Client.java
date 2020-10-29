package psn.cyf.structural.pattern.decorator;

/**
 * 装饰模式
 * 动态 地 给 一个 对象 添加 一些 额外 的 职责。 就 增加 功能 来说， 装饰 模式 比 生成 子类 更为 灵活。
 *装饰 模式 有 以下 4 个 角色。
 * ■ 　 抽象 构件（ Component） 角色： 该 角色 用于 规范 需要 装饰 的 对象（ 原始 对象）。
 * ■ 　 具体 构件（ Concrete Component） 角色： 该 角色 实现 抽象 构件 接口， 定义 一个 需要 装饰 的 原始 类。
 * ■ 　 装饰（ Decorator） 角色： 该 角色 持有 一个 构件 对象 的 实例， 并 定义 一个 与 抽象 构件 接口 一致 的 接口。
 * ■ 　 具体 装饰（ Concrete Decorator） 角色： 该 角色 负责 对 构件 对象 进行 装饰。
 *
 * 装饰 模式 的 优点 有 以下 几个 方面。
 * ■ 　 装饰 类 和 被 装饰 类 可以 独立 发展， 而 不会 相互 耦合。
 * 即 Component 类 无须 知道 Decorator 类， Decorator 类 是 从外部 来 扩展 Component 类 的 功能，
 * 而 Decorator 也不 用 知道 具体 的 构件。
 * ■ 　 装饰 模式 是 继承 关系 的 一个 替代 方案。
 * 装饰 类 Decorator， 不管 装饰 多少 层， 返回 的 对象 还是 Component。
 * ■ 　 装饰 模式 可以 动态 地 扩展 一个 实现 类 的 功能。 装饰 模式 的 缺点： 多层 的 装饰 是 比较 复杂 的。
 *
 * 装饰 模式 的 使用 场景 使用 装饰 模式 的 典型 场景 如下 所示。
 * ■ 　 需要 扩展 一个 类 的 功能， 或 给 一个 类 增加 附加 功能。
 * ■ 　 需要 动态 地 给 一个 对象 增加 功能， 这些 功能 可以 再 动态 地 撤销。
 * ■ 　 需要 为 一批 类 进行 改装 或 加装 功能。
 *装饰 模式 是对 继承 的 有力 补充。 单纯 使用 继承 时， 在 一些 情况下 就会 增加 很多 子类
 * ， 而且 灵活性 差， 维护 也不 容易。 装饰 模式 可以 替代 继承， 解决 类 膨胀 的 问题，
 * 如 Java 基础 类 库 中的 输入 输出 流 相关 的 类 大量 使用 了 装饰 模式。
 *
 *
 */
public class Client {

    public static void main(String[] args) {
        ICar car=new Benz();
        //装饰模式就是在现有基础上增加类的功能且不改动原有类的功能
        CarDecorator decorator=new ConcreteCarDecorator(car);
        decorator.show();
    }
}
