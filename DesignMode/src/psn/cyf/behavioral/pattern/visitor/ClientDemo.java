package psn.cyf.behavioral.pattern.visitor;

/**
 *访问者 模式（ Visitor Pattern） 的 目的 是 封装 一些 施加 于 某种
 * 数据 结构 元素 之上 的 操作， 一旦 这些 操作 需要 修改， 接受
 * 这个 操作 的 数据 结构 则 可以 保持 不变。
 * 定义
 *封装 一些 作用于 某种 数据 结构 中的 各 元素 的 操作， 它可 以在
 * 不改 变数 据 结构 的 前提 下定义 作用于 这些 元素 的 新的 操作。
 *访问者 模式 涉及 以下 5 个 角色。
 *■ 　 抽象 访问者（ Visitor） 角色： 该 角色 声明 一个 或 多个
 * 访问 操作， 定义 访问者 可以 访问 哪些 元素。
 * ■ 　 具体 访问者（ Concrete Visitor） 角色： 该 角色 实现
 * 抽象 访问者 角色 中的 各个 访问 操作。
 * ■ 　 抽象 元素（ Element） 角色： 该 角色 声明 一个 接受
 * 操作， 接受 一个 访问者 对象。
 * ■ 　 具体 元素（ Concrete Element） 角色： 该 角色 实现
 * 抽象 元素 中的 接受 操作。
 * ■ 　 结构 对象（ ObjectStructure） 角色： 该 角色 有 以下 责任，
 * 可以 遍历 结构 中的 所有 元素； 如果 需要， 提供 一个 高层次 的
 * 接口 让 访问者 对象 可以 访问 每一个 元素， 也可以 设计 一个
 * 复合 对象 或者 一个 集合， 如 List 或 Set。
 *1． 访问者 模式 的 优缺点 访问者 模式 的 优点 有 以下 几个 方面。
 * ■ 　 访问者 模式 使得 增加 新的 操作 变得 很容易， 增加 新的 操作
 * 只需 要 增加 新的 访问者 类。
 *■ 　 访问者 模式 将 有关 的 行为 集中 到 一个 访问者 对象 中，
 * 而 不是 分散 到 一个 个 元素 类 中。
 * ■ 　 访问者 模式 可以 跨过 几个 类 的 等级 结构 访问 属于
 * 不同 等级 结构 的 成员 类。
 * ■ 　 累积 状态。 每一个 单独 的 访问者 对象 都 集中 了 相关
 * 的 行为， 从而 也就 可以 在 访问 的 过程 中将 执行 操作 的
 * 状态 积累 在 自己 内部， 而 不是 分散 到 很多 的 元素 对象
 * 中， 益于 系统 的 维护。
 *访问者 模式 的 缺点 如下。
 * ■ 　 增加 新的 元素 类 变得 很 困难。每 增加 一个 新的
 * 元素 类 都 意味着 要在 抽象 访问者 角色 中 增加 一个 新的
 * 抽象 操作， 并在 每一个 具体 访问者 类 中 增加 相应 的 具体 操作。
 * ■ 　 破坏 封装。 访问者 模式 要求 访问者 对象 访问 并 调
 * 用 每一个 元素 对象 的 操作， 这 隐含 了 一个 对 所有 元素
 * 对象 的 要求， 即 必须 暴露 一些 自己的 操作 和 内部 状态，
 * 否则 访问者 的 访问 就 变得 没有 意义。 由于 访问者 对象
 * 自己 会 积累 访问 操作 所需 的 状态， 从而 使得 这些 状态
 * 不再 存储 在 元素 对象 中， 破 坏了 类 的 封装 性。
 *■ 　 违背 了 依赖 倒置 原则。 访问者 依赖 的 是 具体 的 元素，
 *  而 不是 抽象 的 元素， 这 破坏 了 依赖 倒置 的 原则，
 *  特别 是在 面向 对象 的 编程 中， 抛弃 了 对 接口 的 依赖，
 *  而 直接 依赖 实现 类， 扩展 比 较难。
 *访问者 模式 的 应用 场景 使用 访问者 模式 的 典型 场景 如下。
 * ■ 　 一个 对象 结构 包含 很多 类 对象， 它们 有 不同 的
 * 接口， 当 对这 些 对象 实施 依赖于 具体 类 的 操作 时，
 * 即使 用 迭代 器 模式 不能 胜任 的 场景 下， 可以 采用 访问者 模式。
 *■ 　 需要 对 一个 对象 结构 中的 对象 进行 很多 不同 并且 不相关
 * 的 操作， 避免 操作 污染 类。
 * ■ 　 业务 规则 要求 遍历 多个 不同 的 对象， 这本 身 也是
 * 访问者 模式 的 出发点， 迭代 器 模式 只能 访问 同类 或 同 接口
 * 的 数据， 而 访问者 模式 是对 迭代 器 模式 的 扩充， 可以
 * 遍历 不同 的 对象， 执行 不同 的 操作。
 *
 * 青岛东合信息技术有限公司. 设计模式（Java版） (p. 166). 电子工业出版社. Kindle 版本.
 * 青岛东合信息技术有限公司. 设计模式（Java版） (p. 166). 电子工业出版社. Kindle 版本.
 */
public class ClientDemo {
    public static void main(String[] args) {
        Computer computer=new Computer();
        CompoterVisitor runVisitor=new RunVisitor();
        computer.accept(runVisitor);
        CompoterVisitor typeVisitor=new TypeVisitor();
        computer.accept(typeVisitor);

    }
}
