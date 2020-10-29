package psn.cyf.behavioral.pattern.state;

/**
 * 电视 播放各个频道的节目
 */
public class TV {
    public final static Channel CCTV1 = new CCTV1();
    public final static Channel CCTV2 = new CCTV2();
    public final static Channel CCTV3 = new CCTV3();
    private Channel currentChannel;

    public void setCurrentChannel(Channel currentChannel) {
        this.currentChannel = currentChannel;
    }
    private void display(){
        this.currentChannel.display();
    }

    public void disCCTV1(){
        this.setCurrentChannel(CCTV1);
        display();
    }
    public void disCCTV2(){
        this.setCurrentChannel(CCTV2);
        display();
    }
    public void disCCTV3(){
        this.setCurrentChannel(CCTV3);
        display();
    }
}
