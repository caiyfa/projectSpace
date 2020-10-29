package psn.cyf.behavioral.pattern.memento;

/**
 * 备份
 */
public class Backup {
    /**
     * 备份的内容
     */
    String content;
    /**
     * 版本
     */
    int version;

    public Backup(String content) {
        this.content = content;
    }
}
