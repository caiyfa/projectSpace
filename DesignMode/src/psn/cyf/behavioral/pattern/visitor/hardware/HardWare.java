package psn.cyf.behavioral.pattern.visitor.hardware;

import psn.cyf.behavioral.pattern.visitor.CompoterVisitor;

public abstract class HardWare {
    /**
     * 型号
     */
    String type;

    public HardWare(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * 运行
     */
    public abstract void run();

    /**
     * 接受计算机访问者
     */
    public abstract void accept(CompoterVisitor compoterVisitor);
}
