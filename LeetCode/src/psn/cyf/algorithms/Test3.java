package psn.cyf.algorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 示例 1:

 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 优雅解答
 public int lengthOfLongestSubstring(String s) {
 // 记录字符上一次出现的位置
 int[] last = new int[128];
 for(int i = 0; i < 128; i++) {
 last[i] = -1;
 }
 int n = s.length();

 int res = 0;
 int start = 0; // 窗口开始位置
 for(int i = 0; i < n; i++) {
 int index = s.charAt(i);
 start = Math.max(start, last[index] + 1);
 res   = Math.max(res, i - start + 1);
 last[index] = i;
 }

 return res;
 }



 */
public class Test3 {
    public static void main(String[] args) {
        System.out.println("我".charAt(0));
        long t1=System.currentTimeMillis();
        String s="pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    private static  java.util.Hashtable<Character,Integer>  remove( java.util.Hashtable<Character,Integer> tab,Integer integer){
        java.util.Hashtable<Character,Integer>  tmp=new java.util.Hashtable<Character,Integer> ();
         for(Character character:tab.keySet()){
            if(tab.get(character)>integer){
                tmp.put(character,tab.get(character));
            }
        }
          return tmp;
    }

    public static int lengthOfLongestSubstring(String s) {
            char[] chars=s.toCharArray();
            //char 字符,int 位置
        java.util.Hashtable<Character,Integer> tab=new  java.util.Hashtable<>();
            int res=0;
            for(int i=0;i<chars.length;i++){
                if(tab.containsKey(chars[i])){
                    tab=  remove(tab,tab.get(chars[i]));
                    tab.put(chars[i],i);
                }else {
                    tab.put(chars[i],i);
                    if(res<tab.size()){
                        res=tab.size();
                    }
                }
            }

        return res;



    /*    if(s==null){
            return 0;
        }else if(s.length()==1){
            return 1;
        }
        char [] chars=s.toCharArray();
        char[] tmpChar=null;
        int res=0;//结果
        for(int i=0;i<chars.length-1;i++){
            for(int j=i+1;j<=chars.length;j++){
                char[] tmpCh=subCharArr(chars,i,j);
                boolean isRepeat=isRepeat(tmpCh);
                if(!isRepeat&&tmpCh.length>=res){
                    tmpChar=tmpCh;
                    res=tmpCh.length;
                }
            }


        }
        System.out.println(tmpChar);
        return res;*/
    }

    private static char[] subCharArr(char[] chars,int from,int to){
         char[] res=new char[to-from];
         for (int i=0;i<chars.length;i++){
             if(i>=from&&i<to){
                 res[i-from]=chars[i];
             }
         }
         return res;
    }
    private  static boolean isRepeat(char[] chars){
        if(chars==null||chars.length==0){
            return false;
        }
        return isRepeat(chars,chars[0],0);
    }
    private static boolean isRepeat(char[] chars,char ch,int index){
        if(index==chars.length){
            return false;
        }
         for(int i=0;i<chars.length;i++){
             if(i!=index&&ch!='\u0000'&&ch==chars[i]){
                 return true;
             }
         }
         if(index+1==chars.length){
             return false;
         }
        return isRepeat(chars,chars[index+1],index+1);

    }


}
