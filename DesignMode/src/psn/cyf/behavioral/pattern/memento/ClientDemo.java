package psn.cyf.behavioral.pattern.memento;

import javax.print.Doc;

/**
 * 备忘录 模式（ Memento Pattern） 又称 为 快照（ Snapshot） 模式 或 Token 模式。
 *在 不破 坏 封装 性的 前提下， 捕获 一个 对象 的 内部 状态， 并在 该 对象 之外 保存 这个 状态。
 *  这样， 以后 就可以 将 该 对象 恢复 到 原先 保存 的 状态。 通俗 地说， 备忘录 模式 就是 将
 *  一个 对象 进行 备份， 提供 一种 程序 数据 的 备份 方法。
 *备忘录 模式 中有 如下 3 个 角色。
 * ■ 　 发起人（ Originator） 角色： 该 角色 记录 当前 时刻 的内部 状态， 负责 定义 哪些
 * 属于 备份 范围 的 状态， 负责 创建 和 恢复 备忘 数据。
 * ■ 　 备忘录（ Memento） 角色： 该 角色 负责 存储 发起人 角色 的 内部 状态， 在 需要 时
 * 提供 发起人 需要 的 内部 状态 数据。
 * ■ 　 负责人（ Caretaker） 角色： 该 角色 对 备忘录 角色 进行 管理、 保存 和 提供 备忘录。
 * 备忘录 模式 具有 以下 几个 使用 场景。
 * ■ 　 需要 保存 和 恢复 数据 的 相关 状态 场景。
 * ■ 　 提供 一个 可回 滚 的 操作。
 * ■ 　 需要 监控 副本 的 场景。 例如， 监控 一个 对象 的 属性， 但是 监控 又不 应该 作为
 * 系统 的 主 业务 来 调用， 它 只是 边缘 应用， 即使 出现 监控 不准、 错误 报警 也 影响 不大，
 * 因此 一般 做法 是 备份 一个 主线 程 中的 对象， 然后 由 分析 程序 来 分析。
 * ■ 　 数据库 连接 的 事务管理 使用的 就是 备忘录 模式。
 * 2． 备忘录 模式 的 注意事项 备忘录 模式 注意事项 如下 所示。
 * ■ 　 备忘录 的 生命 周期， 备忘录 创建 出来 就要 在最 近的 代码 中 使用， 要 主动 管理
 * 它的 生命 周期， 建立 就要 使用， 不使 用 就要 立刻 删除 其 引用， 等待 垃圾 回收 器 对
 * 它的 回收 处理。
 * ■ 　 备忘录 的 性能。 不要 在 频繁 建立 备份 的 场景 中 使用 备忘录 模式， 例如，
 * for 循环 中， 一是 控制 不了 备忘录 建立 的对象 数量； 二 是 大 对象 的 建立 是要 消耗
 * 资源 的， 需要 考虑 系统 的 性能。 因此， 如果 出现 这样 的 代码， 设计师 应该 修改 架构。
 *
 *
 */
public class ClientDemo {
    public static void main(String[] args) {
        VersionControlSystem system=new VersionControlSystem();
        Document document=new Document("content1");
        document.setOtherContet("otherContet1");
        System.out.println(document.toString());
        //保存
        system.add(document.save());
        document=new Document("content2");
        document.setOtherContet("otherContet2");
        System.out.println(document.toString());
        //保存
        system.add(document.save());

        document=new Document("content3");
        document.setOtherContet("otherContet3");
        System.out.println(document.toString());
        //恢复版本1
        document.resume(system.get(1));
        System.out.println(document.toString());
        //恢复最新版本
        document.resume(system.getLastVersion());
        System.out.println(document.toString());





    }
}
