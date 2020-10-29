package psn.cyf.behavioral.pattern.command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *命令 模式（ Command Pattern） 又称 为 行动（ Action） 模式 或 交易（ Transaction） 模式。
 *将 一个 请求 封装 成 一个 对象， 从而 让你 使用 不同 的 请求 把 客户 端 参数 化，
 * 对 请求 排队 或者 记录 请求 日志， 可以 提供 命令 的 撤销 和 恢复 功能。
 *命令 模式 中有 如下 4 个 角色。
 * ■ 　 命令（ Command） 角色： 该 角色 声明 一个 给 所有 具体 命令 类 的 抽象 接口， 定义 需要 执行 的 命令。
 * ■ 　 具体 命令（ Concrete Command） 角色： 该 角色 定义 一个 接收者 和 行为 之 间的 弱 耦合， 实现 命令 方法，
 * 并 调用调用 接收者 的 相应 操作。
 * ■ 　 调用 者（ Invoker） 角色： 该 角色 负责 调用 命令 对象 执行 请求。
 * ■ 　 接收者（ Receiver） 角色： 该 角色 负责 具体 实施 和 执行 一个 请求。
 *
 * 1． 命令 模式 的 优点 命令 模式 的 优点 有 以下 几个 方面。
 * ■ 　 类 间 解 耦。 调用 者 角色 与 接收者 角色 之间 没有 任何 依赖 关系，
 * 调用 者 实现 功能 时 只需 要 调用 Command 中的 execute（） 方法 即可， 不需要 了解 是 哪个 接收者 执行。
 * ■ 　 可 扩展性。 Command 的 子类 可以 非常 容易 地 扩展， 而 调用 者 Invoker
 * 和 高层次 的 模块Client 不产 生 严重 的 代码 耦合。
 * ■ 　 命令 模式 结合 其他 模式 会 更 优秀。 命令 模式 可以 结合 责任 链 模式，
 * 实现 命令 族 解析 任务， 结合 模板 方法 模式， 则 可以 减少 Command 子类 的 膨胀 问题。
 * 命令 模式 的 缺点 如下。
 * ■ 　 使用 命令 模式 可能 会 导致 系统 中 出现 过多 的 具体 命令 类， 因此 需要 在 项目 中 慎重考虑 使用。
 *使用 命令 模式 的 典型 场景 如下。
 *  ■ 　 使用 命令 模式 作为“ 回 调” 在 面向 对象 系统 中的 替代。
 *  “ 回 调” 讲的 便是 将 一个 函数 登记 上， 然后 在 以后 调用 此 函数。
 *  ■ 　 需要 在 不同 的 时间 指定 请求、 将 请求 排队。
 *  ■ 　 系统 需要 支持 命令 的 撤销（ undo）。 命令 对象 可以 把 状态 存储 起来，
 *  等到 客户 端 需要 撤销 时， 可以 调用 undo（） 方法， 将 命令 所 产生 的 效果 撤销。
 *  ■ 　 需要 将 系统 中 所有 的 数据 更新 操作 保存 到 日志 里， 以便在 系统 崩溃 时，
 *  可以 根据 日志 读 回 所有 的 数据 更新 命令， 重新 调用 execute（） 方法 一条 一条 执行 这些 命令，
 *  从而 恢复 系统 在 崩溃 前 所做 的 数据 更新。
 *  ■ 　 一个 系统 需要 支持 交易（ transaction）。 一个 交易 结构 封装 了 一组 数据 更新 命令。
 *  使用 命令 模式 来 实现 交易 结构 可以 使 系统 增加 新的 交易 类型。
 *
 */
public class ClientDemo  extends JFrame implements ActionListener {
   private JPanel panel;
   private YellowCommand yellowCommand;
   private RedCommand redCommand;
   private ExitCommand exitCommand;

    public ClientDemo( ) throws HeadlessException {
        super("命令模式");
        panel=new JPanel();
        panel.setPreferredSize(new Dimension(100,100));

        this.add(panel);
        yellowCommand=new YellowCommand("黄色",panel);
        redCommand=new RedCommand("红色",panel);
        exitCommand=new ExitCommand("退出");
        panel.add(yellowCommand);
        panel.add(redCommand);
        panel.add(exitCommand);
        yellowCommand.addActionListener(this);
        redCommand.addActionListener(this);
        exitCommand.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ICommand)e.getSource()).execute();
    }

    public static void main(String[] args) {
        ClientDemo clientDemo=new ClientDemo() ;

        clientDemo.setSize(new Dimension(500,500));
        //设置窗体居中显示
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        int x = (int)(toolkit.getScreenSize().getWidth()-clientDemo.getWidth())/2;

        int y = (int)(toolkit.getScreenSize().getHeight()-clientDemo.getHeight())/2;
        clientDemo.setLocation(x,y);
        clientDemo.setVisible(true);
        clientDemo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
