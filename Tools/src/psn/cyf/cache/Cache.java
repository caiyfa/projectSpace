package psn.cyf.cache;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    /**
     * 界面缓存
     */
    public static Map<String, Stage>    STAGE=new HashMap<>();

    /**
     * 控制类缓存
     */
    public static Map<String, Object>    CONTROLLER=new HashMap<>();

    public static class Tab{
        public static String Main="mainPage";
        public static String Project="projectPage";
        public static String Patch="patchPage";
        public static String Demand="demandPage";
        public static String ProjectEdit="projectEdit";
        public static String LoginHelper="loginHelper";
        public static String DbConfigBtn="dbConfigBtn";
        public static String QueryGlobal="queryGlobal";
        public static String WebServiceTest="WebServiceTest";
        public static String JavaProject="JavaProject";

    }
    public static class Ctrl{
        /**
         * 全局查询控制器缓存
         */
        public static String QueryFromGlobalDBCtrl="QueryFromGlobalDBCtrl";
    }
    public   static class Name{
        public static String WholePage="WholePage";
        /**
         * 多页签控制器
         */
        public static String MultiCtrl="multiCtrl";

        /**
         * 主界面
         */
        public static String MainPage="mainPage";
        /**
         * 主界面控制器
         */
        public static String MainCtrl="mainCtrl";
        public static String LoginHelperCtrl="loginHelperCtrl";
        /**
         * 项目界面
         */
        public static String ProjectPage="projectPage";
        /**
         * 项目界面控制器
         */
        public static String PojectCtrl="projectCtrl";
        /**
         * 补丁界面
         */
        public static String PatchPage="patchPage";

        /**
         * 补丁界面控制器
         */
        public static String PatchCtrl="patchCtrl";

        /**
         * 项目维护界面
         */
        public static String ProjectEditPage="pageEditPage";

        /**
         * 项目维护控制器
         */
        public static String ProjectEditCtrl="pageEditCtrl";

        /**
         * 需求界面
         */
        public static String DemandPage="demandPage";
        /**
         * 需求控制器
         */
        public static String DemandCtrl="demandPage";
        public static String DataBaseCtrl="DataBaseCtrl";

    }

}
