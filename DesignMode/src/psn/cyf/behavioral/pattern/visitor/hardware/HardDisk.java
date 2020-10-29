package psn.cyf.behavioral.pattern.visitor.hardware;

import psn.cyf.behavioral.pattern.visitor.CompoterVisitor;

public class HardDisk extends HardWare {
    public HardDisk(String type) {
        super(type);
    }

    @Override
    public void run() {
        System.out.println("型号为"+type+"的硬盘正在运转");
    }

    @Override
    public void accept(CompoterVisitor compoterVisitor) {
        compoterVisitor.visitHardDisk(this);
    }
}
