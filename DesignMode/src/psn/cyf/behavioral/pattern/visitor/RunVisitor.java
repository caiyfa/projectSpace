package psn.cyf.behavioral.pattern.visitor;

import psn.cyf.behavioral.pattern.visitor.hardware.CPU;
import psn.cyf.behavioral.pattern.visitor.hardware.HardDisk;

/**
 * 硬件运转访问者
 */
public class RunVisitor implements CompoterVisitor {
    @Override
    public void visitCPU( CPU cpu) {
        cpu.run();
    }

    @Override
    public void visitHardDisk(HardDisk hardDisk) {
        hardDisk.run();
    }
}
