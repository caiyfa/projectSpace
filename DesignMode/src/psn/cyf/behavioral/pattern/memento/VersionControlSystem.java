package psn.cyf.behavioral.pattern.memento;

import java.util.LinkedList;

/**
 * 版本控制器
 */
public class VersionControlSystem {

    //保存的备份
    LinkedList<Backup> backups=new LinkedList<>();
    /**
     * 下一版本
     */
    int nextVersion=0;
    public void add(Backup backup){
        //先进行版本自增
        backup.version=++nextVersion;
        backups.add(backup);
    }
    public Backup  get(int version){
        for(Backup backup:backups){
            if(backup.version==version){
                return backup;
            }
        }
        return null;
    }
    public Backup getLastVersion(){
        return backups.getLast();
    }
}
