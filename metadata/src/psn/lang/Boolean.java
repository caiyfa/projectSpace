package psn.lang;

public  class Boolean {
    private final boolean value;
    public static Boolean True=new Boolean(true);
    public static Boolean False=new Boolean(false);
    public Boolean(boolean value) {
        this.value = value;
    }

    public Boolean(char ch) {
            this.value=(ch=='Y'||ch=='y');
    }
    public Boolean(String str){
        this.value=("Y".equals(str)||"TRUE".equals(str));
    }
    public boolean booleanValue(){
        return value;
    }

    @Override
    public String toString() {
        return value?"Y":"N";
    }

    /*public static void main(String[] args) {
        System.out.println("y".equals("Y"));
    }*/
}
