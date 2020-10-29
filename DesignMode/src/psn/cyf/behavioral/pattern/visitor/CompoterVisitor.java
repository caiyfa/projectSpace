package psn.cyf.behavioral.pattern.visitor;

import psn.cyf.behavioral.pattern.visitor.hardware.CPU;
import psn.cyf.behavioral.pattern.visitor.hardware.HardDisk;

public interface CompoterVisitor {
    /**
     * 访问CPU
     */
    void visitCPU( CPU cpu);

    /**
     * 访问硬盘
     */
    void visitHardDisk(HardDisk hardDisk);
}
