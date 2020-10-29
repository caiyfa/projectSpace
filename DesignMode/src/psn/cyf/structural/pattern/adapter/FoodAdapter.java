package psn.cyf.structural.pattern.adapter;

public class FoodAdapter extends ShuiJiao implements Huntun{

    @Override
    public void makeHuntun() {
        super.makeShuiJiao();
        System.out.println("包馄钝");
    }
}
