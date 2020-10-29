package psn.cyf.structural.pattern.decorator;

public class ConcreteCarDecorator extends CarDecorator{
    public ConcreteCarDecorator(ICar car) {
        super(car);
    }
    private void print(){
        System.out.println("增加新手标志，车漆颜色改为蓝色霞光.");
    }
    private void  setGps(){
        System.out.println("安装GPS");
    }

    @Override
    public void show() {
        super.show();
        this.print();
        this.setGps();
    }
}
