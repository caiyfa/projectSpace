package psn.cyf.buddhist.decorator;

public class HighScoreDecorator extends  Decorator {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    /**
     * 汇报最高成绩
     */
    private void reportHighScore(){
        System.out.println(" 本次考试语文最高75，数学78，自然是80");
    }

    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}
