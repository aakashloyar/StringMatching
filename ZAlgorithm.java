package StringMatching;

import java.util.Arrays;

public class ZAlgorithm {
    public static void main(String[] args) {
        StringBuilder pat=new StringBuilder("My");
        StringBuilder txt=new StringBuilder("My name is Aakash My parents areMy favourite");
        ZF z=new ZF();
        int c=z.func1(txt,pat);
        System.out.println(c);
    }

}
class ZF {
    static int func1(StringBuilder txt,StringBuilder pat) {
        int n=txt.length(),m=pat.length();
        StringBuilder zs=new StringBuilder();
        zs.append(pat);
        zs.append('$');
        zs.append(txt);
        int[] z=new int[n+m+1];
        fill(z,zs,n+m+1);
        //System.out.println(Arrays.toString(z));
        int c=0,i=0;
        while(i<n+m+1) {
            if(z[i]==m) {
                c++;
                i+=m;
            } else i++;
        }
        return c;
    }
    static void fill(int[] z,StringBuilder sb,int n) {
        for(int i=1,l=1,r=1;i<n;i++) {
            if(i<l) continue;
            if(i>r) {
                r=i;
                l=i;
            }
            while(r<n&&sb.charAt(r-l)==sb.charAt(r)) r++;
            z[i]=r-l;
            l++;
            while(l+z[l-i]<r) {
                z[l]=z[l-i];
                l++;
            }
        }
    }
}
