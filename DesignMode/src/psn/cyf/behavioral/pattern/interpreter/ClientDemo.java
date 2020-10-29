package psn.cyf.behavioral.pattern.interpreter;

/**
 * 解释器 模式（ Interpreter Pattern） 是一 种 按照 规定 语法 对 表达式 进行 解析 的 方案， 在 项目 中 使用 较少。
 *给定 一门 语言， 定义 它的 文法 的 一种 表示， 并 定义 一个 解释器， 该 解释器 使用 该 表示 来 解释 语言 中的 句子。
 *解释器 有 以下 5 个 角色。
 * ■ 　 抽象 表达式（ AbstractExpression） 角色： 该 角色 声明 一个 所有 的 具体 表达式
 * 角色 都 需要 实现 的 抽象 接口， 该 接口 主要 是一 个 解释 操作 interpret（） 方法。
 * ■ 　 终结 符 表达式（ Terminal Expression） 角色： 该 角色 实现 了 抽象 表达式 角色
 * 所 要求 的 接口， 文法 中的 每一个 终结 符 都有 一个 具体 终结 表达式 与之 对应。
 * ■ 　 非 终结 符 表达式（ Nonterminal Expression） 角色： 该 角色 是一 个 具体 角色，
 * 文法 中的 每一 条规 则 都对 应 一个 非 终结 符 表达式 类。
 *■ 　 环境（ Context） 角色： 该 角色 提供 解释器 之外 的 一些 全局 信息。
 * ■ 　 客户 端（ Client） 角色： 该 角色 创建 一个 抽象 语法树， 调用 解释 操作。
 * 解释器 是一 个 比较 少用 的 模式，
 * 1． 解释器 模式 的 优缺点 解释器 模式 的 优点 有 以下 几个 方面。
 * ■ 　 简单 的 语法 分析 工具。
 * ■ 　 扩展性， 修改 语法 规则 只要 修改 相应 的 非 终结 符 表达式 即可，
 * 若 扩展 语法， 则 只要 增加 非 终结 符 类 即可。
 * 解释器 模式 的 缺点 如下。
 * ■ 　 解释器 模式 会 引起 类 膨胀。每个 语法 都要 产生 一个 非 终结 符 表达式，
 * 语法 比较 复杂 时 就可能 产生 大量 的 类 文件， 不易 维护。
 * ■ 　 采用 递归 调用 方法。 每个 非 终结 符 表达式 只 关心 与 自己 有关 的 表达式，
 * 每个 表达式 需要 知道 最终 的 结果， 必须 一层 一层 地 剥 茧， 无论是 面向 过程 的
 * 语言 还是 面向 对象 的 语言， 递归 都是 在 必要条件 下 使用 的， 不易 调试 且 影响 效率。
 * 2． 解释器 模式 的 使用 场景 使用 解释器 模式 的 典型 场景 如下。
 *■ 　 重复 发生 的 问题 可以 使用 解释器 模式。 例如， 多个 应用 服务器， 每天 产生 大量 的 日志，
 * 需要 对 日志 文件 进行 分析 处理， 由于 各个 服务器 的 日志 格式 不同， 但是 数据 要素 是 相同 的，
 * 按照 解释器 的 说法 就是 终结 符 表达式 都是 相同 的， 非 终结 符 表达式 就 需要 制定。
 * ■ 　 一个 简单 语法 需要 解释 的 场景。
 */
public class ClientDemo {
    public static void main(String[] args) {
        Variables v=new Variables();
        Variable x=new Variable();
        Variable y=new Variable();
        Variable z=new Variable();
        v.put(x,10);
        v.put(y,20);
        v.put(z,30);
        //计算x*(y+z/x)-x
        ArithmeticExpression e=new Subtract(new Multiply(x,new Plus(y,new Division(z,x))),x);
        System.out.println(e.interpret(v));
    }
}
