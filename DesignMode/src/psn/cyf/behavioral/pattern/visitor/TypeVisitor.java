package psn.cyf.behavioral.pattern.visitor;

import psn.cyf.behavioral.pattern.visitor.hardware.CPU;
import psn.cyf.behavioral.pattern.visitor.hardware.HardDisk;

/**
 * 计算机硬件型号访问者
 */
public class TypeVisitor implements CompoterVisitor{
    @Override
    public void visitCPU( CPU cpu) {
        System.out.println("CPU型号"+cpu.getType());
    }

    @Override
    public void visitHardDisk(HardDisk hardDisk) {
        System.out.println("硬盘型号"+hardDisk.getType());
    }
}
