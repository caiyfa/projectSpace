package psn.cyf.buddhist.decorator;

public class SortDecorator extends Decorator {
    public SortDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportSort(){
        System.out.println("我的排名是38。。");
    }

    @Override
    public void report() {
        super.report();
        this.reportSort();
    }
}
