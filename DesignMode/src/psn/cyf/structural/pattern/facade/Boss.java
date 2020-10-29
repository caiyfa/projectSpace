package psn.cyf.structural.pattern.facade;

/**
 * 外观 模式（ Facade Pattern） 也叫 门面 模式，
 *外观 模式 注重“ 统一 的 对象”， 即 提供 一个 访问 子系统 的 接口，
 * 只有 通过 该 接口（ Fa ç ade） 才能 允许 访问 子系统 的 行为 发生，
 外观 模式 具有 以下 两个 角色。
 ■ 　 外观（ Facade） 角色： 客户 端 可以 调用 该 角色 的 方法，
 该 角色 知晓 相关 子系统 的 功能 和 责任。 正常 情况下，
 本 角色 会 将 所有 从 客户 端 发来 的 请求 委派 到 相应 的 子系统，
 即 该 角色 没有 实际 的 业务 逻辑， 只是 一个 委托 类。
 ■ 　 子系统（ Subsystem） 角色： 可以 同时 有一个 或 多个 子系统，
 每一个 子系统 都不 是一 个 单独 的 类， 而是 一个 类 的 集合。
 子系统 不知道 外观 角色 的 存在， 对于 子系统 而言，
 外观 角色 仅仅是 另外 一个 客户 端 而已。
 1． 外观 模式 的 优点 外观 模式 的 优点 有 以下 几个 方面。
 ■ 　 减少 系统 的 相互 依赖， 所有 的 依赖 都是 对 Fa ç ade 对象 的 依赖， 与 子系统 无关。
 ■ 　 提高 灵活性， 不管 子系统 内部 如何 变化， 只要 不 影响 Facade 对象， 任何 活动 都是 自由 的。
 ■ 　 提高 安全性， Facade 中 未 提供 的 方法， 外界 就 无法 访问， 提高 系统 的 安全性。
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * *  * * * * * * *
 *  注意 　 外观 模式 最大 的 缺点 是 不符合 开闭 原则， 对 修改 关闭， 对 扩展 开放。*
 * * * * * * *  * * * * * * *  * * * * * * *  * * * * * * *  * * * * * * *  * * * * * * *
 * 2． 外观 模式 的 使用 场景 使用 外观 模式 的 典型 场景 如下。
 * ■ 　 为 一个 复杂 的 模块 或 子系统 提供 一个 供 外界 访问 的 接口。
 * ■ 　 子系统 相对 独立， 外界 对子 系统 的 访问 只要 黑箱 操作 即可。
 * ■ 　 预防 风险 扩散， 使用 Fa ç ade 进行 访问 操作 控制。
 *
 * 青岛东合信息技术有限公司. 设计模式（Java版） (p. 100). 电子工业出版社. Kindle 版本.

 */
public class Boss {
    public static void main(String[] args) {
        Secretary secretary=new Secretary();
        secretary.trip("上海",10);
        secretary.repast(8);
    }
}
