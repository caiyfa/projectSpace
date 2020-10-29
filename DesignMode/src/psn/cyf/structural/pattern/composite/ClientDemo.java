package psn.cyf.structural.pattern.composite;

/**
 * 组合模式
 * 将对 象 组 合成 树 形 结构 以 表示“ 部分— 整体” 的 层次 结构， 使得 用户 对 单个 对象 和 组合 对象 的 使用 具有 一致性。
 *组合 模式 提供 以下 3 个 角色。
 *  ■ 　 抽象 构件（ Component） 角色： 该 角色 定义 参加 组合 对象 的 共有 方法 和 属性，
 *  规范 一些 默认 的 行为 接口。
 *  ■ 　 叶子 构件（ Leaf） 角色： 该 角色 是 叶子 对象， 其下 没有 其他 的 分支，
 *  定义 出 参加 组合 的 原始 对象 的 行为。
 *  ■ 　 树枝 构件（ Composite） 角色： 该 角色 代表 参加 组合 的、
 *  其下 有 分支 的 树枝 对象， 它的 作用 是将 树枝 和 叶子 组合 成 一个 树 形 结构， 并 定义 出 管理 子 对象
 *的 方法， 如 add（）、 remove（） 等。
 *1． 组合 模式 的 优缺点
 *组合 模式 的 优点 有 以下 几个 方面。
 *  ■ 　 高层 模块 调用 简单。 一 棵树 形 机构 中的 所有 节点 都是 Component，
 *  局部 和 整体 对调 用者 来说 没有 任何 区别， 即 高层 模块 不必 关心 自己 处理 的 是 单个 对象 还是 整个 组合 结构，
 *  简化 了 高层 模块 的 代码。
 *  ■ 　 节点 自由 增加。 使用 组合 模式 后， 如果 想 增加 一个 树枝 节点、 树叶 节点 只需 要找 到 其父 节点 即可。
 *  组合 模式 的 缺点 如下。
 *  ■ 　 不易 控制 树枝 构件 的 类型；
 *  ■ 　 不易 使用 继承 的 方法 来 增加 新的 行为。
 *2． 组合 模式 的 使用 场景 使用 组合 模式 的 典型 场景 如下。
 * ■ 　 需要 描述 对象 的 部分 和 整体 的 等级 结构， 如 树 形 菜单、 文件 和 文件夹 管理。
 * ■ 　 需要 客户 端 忽略 个体 构件 和 组合 构件 的 区别， 平等 对待 所有 的 构件。
 * 组合 模式 也是 应用 广泛 的 一种 设计 模式， 例如， Java 基础 类 库 的swing 部分 中就 大量 使用 了 组合 模式， 大部分 控 件 都是 JComponent 的 子类， 同时 其 add（） 方法 又可 向 界面 添加 JComponent 类型 的 控 件， 从而 使得 使用者 可以 以 统一 的 方式 操作 各种 控 件。
 *
 * 青岛东合信息技术有限公司. 设计模式（Java版） (p. 89). 电子工业出版社. Kindle 版本.
 */
public class ClientDemo {
    public static void main(String[] args) {
        // CEO
        ConcreteCompany root = new ConcreteCompany(" 张三"," CEO",100000);
        // 部门 经理
        ConcreteCompany developDep = new ConcreteCompany(" 李四"," 研发 部 经理", 12000);
        ConcreteCompany salesDep = new
                ConcreteCompany(" 王 五"," 销售部 经理", 15000);
        ConcreteCompany financeDep = new ConcreteCompany(" 马 六"," 财务 部 经理", 10000);
        // 部门 员工
        Employee e1 = new Employee(" A"," 研发 部", 3000);
        Employee e2 = new Employee(" B"," 研发 部", 2500);
        Employee e3 = new Employee(" C"," 研发 部", 4000);
        Employee e4 = new Employee(" D"," 研发 部", 6000);
        Employee e5 = new Employee(" E"," 销售部", 3500);
        Employee e6 = new Employee(" F"," 销售部", 2300);
        Employee e7 = new Employee(" G"," 销售部", 5000);
        Employee e8 = new Employee(" H"," 财务 部", 3800);
        Employee e9 = new Employee(" I"," 财务 部", 4300);
        // 生成 树
        root. add( developDep);
        root. add( salesDep);
        root. add( financeDep);
        developDep. add( e1);
        developDep. add( e2);
        developDep. add( e3);
        developDep. add( e4);
        salesDep. add( e5);
        salesDep. add( e6);
        salesDep. add( e7);
        financeDep. add( e8);
        financeDep. add( e9);
        //显示 公司 层次

        System.out.println(root.getInfo());
        display(root);
    }
    static void display(ConcreteCompany root){
        for(ICompany c:root.getChild()){
            if(c instanceof Employee){
                //叶子
                System.out.println(c.getInfo());
            }else {
                //枝干
                System.out.println(c.getInfo());
                display((ConcreteCompany) c);
            }
        }
    }
}
