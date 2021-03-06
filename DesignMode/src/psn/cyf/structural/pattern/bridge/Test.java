package psn.cyf.structural.pattern.bridge;

/**
 *桥梁模式
 * 抽象化（ Abstraction） 角色： 该 角色 抽象化 的 给出 定义， 并 保存 一个 对 实现 化 对象 的 引用。
 * 实现 化（ Implementor） 角色： 该 角色 给出 实现 化 角色 的 接口， 但不 给出 具体 的 实现。
 * 修正 抽象化（ RefinedAbstraction） 角色： 该 角色 扩展 抽象化 角色， 它 引用 实现 化 角色 并对 抽象化 角色 进行 修正。
 * 具体 实现 化（ ConcreteImplementor） 角色： 该 角色 对 实现 化 角色 接口 中的 方法 进行
 *
 *1.桥梁 模式 虽然 是一 个 使用 频率 不高 的 模式，
 * 但是 熟悉 该 模式 对于 理解 面向 对象 的 设计 原则， 包括 开闭 原则 都很 有帮助，
 * 有助于 形成 正确 的 设计 思想 和 培养 良好 的 设计 风格。 桥梁 模式 的 优点 有 以下 几个 方面。
 * ■ 　 抽象 和 实现 分离 是 桥梁 模式 的 主要 特点， 是 为了 解决 继承 的 缺点 而 提出 的 设计 模式。
 * 在 该 模式 下， 实现 可以 不受 抽象 的 约束， 不用 绑 定 在 一个 固定 的 抽象 层次 上。
 * ■ 　 实现 对 客户 透明， 客户 端 不用 关心 细节 的 实现， 它 已经 由 抽象 层 通过 聚合 关系 完成 了 封装。
 * ■ 　 提高 灵活性 和 扩展性。
 *
 * 2． 桥梁 模式 的 使用 场合 使用 桥梁 模式 的 场合 如下。
 * ■ 　 如果 一个 系统 需要 在 构件 的 抽象化 角色 和 具体化 角色 之间 增加 更多 的 灵活性， 避免 在 两个 层次 之间 建立 静态 的 联系。
 * ■ 　 设计 要求 实现 化 角色 的 任何 改变 不应 当 影响 客户 端， 或者说 实现 化 角色 的 改变 对 客户 端 是 完全 透明 的。
 * ■ 　 一个 构件 有多 于 一个 的 抽象化 角色 和 实现 化 角色， 系统 需要 它们 之间 进行 动态 耦合。
 * ■ 　 不 希望 或不 适合 使用 继承 的 场合。 继承 具有 强 入侵 性质， 即 父 类有 的 方法， 子类 必须 有；
 * 而 桥梁 模式 是 弱 关联 关系。 因此 对于 比较 明确 不发 生 变化 的， 则 可以 通过 继承 完成；
 * 若不 能 确定 是否 会 发生 变化， 则 通过 桥梁 模式 来 解决。
 * 注意 　 使用 桥梁 模式 时 主要 考虑 如何 拆分 抽象 和 实现， 桥梁 模式 的 意图 还是 对 变化 的 封装，
 * 尽量 把 可能 变化 的 因素 封装 到 最细、 最小 的 逻辑 单元 中， 避免 风险 扩散。
 *
 * 青岛东合信息技术有限公司. 设计模式（Java版） (p. 95). 电子工业出版社. Kindle 版本.
 */
public class Test {
    public static void main(String[] args) {
        //Color 实现化角色 Red具体实现化角色
        Color color=new Red();
        //AbstractShape抽形象化角色 Circle 修正抽象化角色
        AbstractShape shape=new Circle(color);
        shape.draw();
    }
}
