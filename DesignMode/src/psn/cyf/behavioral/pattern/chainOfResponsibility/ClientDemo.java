package psn.cyf.behavioral.pattern.chainOfResponsibility;

import java.util.concurrent.TimeUnit;

/********************
 * 责任链模式********
 * ******************
 * 使 多个 对象 都有 机会 处理 请求， 从而 避免 了 请求 的 发送者 和 接收者 之间 的 耦合 关系。
 * 将 这些 对象 连成 一条 链， 并 沿着 这条 链 传递 该 请求， 直到 有 对象 处理 它 为止。
 * 责任 链 模式 的 重点 是在“ 链” 上， 由 一条 链 去处 理 相似 的 请求， 在 链 中 决定
 * 谁 来处 理 这个 请求， 并 返回 相应的结果。
 *责任 链 模式 涉及 以下 两个 角色。
 * ■ 　 抽象 处理者（ Handler） 角色： 该 角色 对 请求 进行 抽象， 并 定义 一个 方法
 * 以 设定 和 返回 对下 一个 处理者 的 引用。
 * ■ 　 具体 处理者（ Concrete Handler） 角色： 该 角色 接到 请求 后， 可以选 择
 * 将 请求 处理 掉， 或者 将 请求 传给 下一个 处理者。 由于 具体 处理者持有 对下 一个 处理者 的 引用，
 * 因此， 如果 需要， 具体 处理者 可以 访问 下一个 处理者。
 *1． 责任 链 模式 的 优缺点 责任 链 模式 的 优点 如下。
 * ■ 　 责任 链 模式 将 请求 和 处理 分开， 请求者 不知道 是 谁 处理 的，
 * 处理者 可以 不用 知道 请求 的 全貌。
 * ■ 　 提高 系统 的 灵活性。 责任 链 模式 的 缺点 如下。
 * ■ 　 降低 程序 的 性能， 每个 请求 都是 从 链 头 遍历 到 链 尾，
 * 当 链 比较 长的 时候， 性能 会 大幅 下降。
 * ■ 　 不易 于 调试， 由于 采用 了 类似类似 递归 的 方式， 调试 的 时候 逻辑 比较 复杂。
 * 注意 　 责任 链 中的 节点 数量 需要 控制， 避免 出现 超 长 链 的 情况，
 * 这就 需要 设置 一个 最大 的 节点 数量， 超过 则 不允许 增加 节点，
 * 避免 无意识 地 破坏 系统 性能。
 *
 * 2． 责任 链 模式 的 应用 场景 责任 链 模式 是一 种 常见 的 模式，
 * Struts2 的 核心 控 件 FilterDispatcher 是 一个 Servlet 过滤器，
 * 该 控 件 就是 采用 责任 链 模式， 可以 对 用户 请求 进行 层层 过滤 处理。
 * 责任 链 模式 在 实际 的 项目 中 使用 的 比较 多， 其 典型的 应用场景 如下。
 * ■ 　 一个 请求 需要 一系列 的 处理 工作。
 * ■ 　 业务 流的 处理， 例如， 文件 审批。
 * ■ 　 对 系统 进行 补充 扩展。
 *
 *
 */
public class ClientDemo {
    static Boolean isStop=false;
    public static void main(String[] args) throws InterruptedException {
        //初始化玩家
        Player a=new PlayerA();
        Player b=new PlayerB();
        Player c=new PlayerC();
        //围成一圈
        a.setNextPlayer(b);
        b.setNextPlayer(c);
        c.setNextPlayer(a);
        //开始游戏 击鼓人停下则游戏停止
        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(205);
                ClientDemo.isStop=true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //睡一下确保线程启动了
        TimeUnit.MILLISECONDS.sleep(10);
            a.handle();
        System.out.printf("游戏结束");
        System.out.println(a.hasFlower?"A持球":(b.hasFlower?"B持球":(c.hasFlower?"C持球":"无人")));
    }
}
