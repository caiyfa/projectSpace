package psn.cyf.behavioral.pattern.visitor;

import psn.cyf.behavioral.pattern.visitor.hardware.CPU;
import psn.cyf.behavioral.pattern.visitor.hardware.HardDisk;
import psn.cyf.behavioral.pattern.visitor.hardware.HardWare;

public class Computer {
    private HardWare cpu;
    private HardWare harddisk;

    public Computer() {
        this.cpu = new CPU("Intel Core 19-9700");
        this.harddisk = new HardDisk("Seagate 500G 7200转");
    }
    public void accept(CompoterVisitor visitor){
        cpu.accept(visitor);
        harddisk.accept(visitor);
    }


}
