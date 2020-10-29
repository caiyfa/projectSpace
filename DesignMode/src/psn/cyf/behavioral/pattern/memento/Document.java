package psn.cyf.behavioral.pattern.memento;

public class Document {
    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    /**
     * 需要备份的状态
     */
    public String content;
    /**
     * 不需要备份的状态
     */
    public String otherContet;
    //确认哪些内容需要保存备份
    public Backup save(){
        System.out.println("保存备份");
        return new Backup(content);
    }
    //描述了需要恢复哪些内容
    public void resume(Backup backup){
        System.out.println("恢复备份");
        content=backup.content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOtherContet() {
        return otherContet;
    }

    public void setOtherContet(String otherContet) {
        this.otherContet = otherContet;
    }

    @Override
    public String toString() {
        return "Document{" +
                "content='" + content + '\'' +
                ", otherContet='" + otherContet + '\'' +
                '}';
    }
}
