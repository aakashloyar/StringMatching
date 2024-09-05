package StringMatching;
import java.util.*;
public class KMP {
    public static void main(String[] args) {
        String txt="aylnlfdxfi";
        String pat="dxfi";
        kmp(txt,pat);
    }
    static void kmp(String txt,String pat) {
        int n=txt.length(),m=pat.length();
        int[] lps=new int[m];
        preprocess(pat,lps,m);
        System.out.println(Arrays.toString(lps));
        ArrayList<Integer> res=matching(txt,pat,lps,n,m);
        System.out.println(res);
    }
    static ArrayList<Integer> matching(String txt,String pat,int[] lps,int n,int m) {
        int i=0,j=0;
        ArrayList<Integer> list=new ArrayList<>();
        while(i<n) {
            if(j==m) {
                list.add(i-m+1);
                j=lps[j-1];
            }
            else if(txt.charAt(i)==pat.charAt(j)) {
                i++;
                j++;
            } else {
                if(j==0) i++;
                else j=lps[j-1];
            }
        }
        if(j==m) list.add(n-m+1);
        return list;
    }
    static void preprocess(String pat,int[] lps,int m) {
        int len=0;
        int i=1;
        while(i<m) {
            if(pat.charAt(i)==pat.charAt(len)) lps[i++]=++len;
            else {
                if(len==0) i++;
                else len=lps[len-1];
            }
        }
    }
}
