package psn.cyf.buddhist.decorator;

public class Father {
    public static void main(String[] args) {
        SchoolReport sr=new FouthGradeSchoolReport();
        sr=new HighScoreDecorator(sr  );
        sr=new SortDecorator(sr);
        sr.report();
        sr.sign("CAI");
    }
}
