package StringMatching;
import java.util.*;
public class StringHashing {
    public static void main(String[] args) {
        StringBuilder pat=new StringBuilder("My");
        StringBuilder txt=new StringBuilder("My name is Aakash My parents areMy favourite");
        int c1=solve(pat,txt);
        int c2=solve2(pat,txt);
        System.out.println(c1+" "+c2);
    }
    static int solve2(StringBuilder pat,StringBuilder txt) {
        int n=txt.length(),m=pat.length();
        DHash h1=new DHash(pat,m);
        long[] hash=h1.hashing(0);
        int i=0;
        long[] temp=new long[2];
        boolean is=true;
        int c=0;
        DHash h2=new DHash(txt,m);
        while(i<=n-m) {
            if(is) temp=h2.hashing(i);
            else temp=h2.update(temp,i,i+m-1);
            if(DHash.equal(hash,temp)) {
                is=true;
                i+=m;
                c++;
            } else {
                i++;
                is=false;
            }
        }
        return c;
    }
    static int solve(StringBuilder pat,StringBuilder txt) {
        int n=txt.length(),m=pat.length();
        Hash h1=new Hash(pat,m);
        long hash=h1.hashing(0);
        int i=0;
        long temp=0;
        boolean is=true;
        int c=0;
        Hash h2=new Hash(txt,m);
        while(i<=n-m) {
            if(is) temp=h2.hashing(i);
            else temp=h2.update(temp,i,i+m-1);
            if(temp==hash) {
                is=true;
                i+=m;
                c++;
            } else {
                i++;
                is=false;
            }
        }
        return c;
    }
}
//Karp Rabin
class Hash {
    private final int prime=101,mod=(int)1e9+7,n;
    long[] pow;
    final long denpow;
    final StringBuilder sb;
    Hash(StringBuilder temp,int n) {
        this.n=n;
        this.pow=new long[n+1];
        long m=1;
        for(int i=0;i<=n;i++) {
            pow[i]=m;
            m*=prime;
            m%=mod;
        }
        this.sb=temp;
        this.denpow=deninv(prime,mod-2,mod);
    }
    long hashing(int s) {
        long hash=0;
        long mul=1;
        for(int i=0;i<n;i++) {
            hash+=(mul*(sb.charAt(s+i)))%mod;
            hash%=mod;
            mul*=prime;
            mul%=mod;
        }
        return hash;
    }

    long update(long ph,int s,int e) {
        char prev=sb.charAt(s-1),curr=sb.charAt(e);
        ph=(ph-prev+mod)%mod;
        ph*=denpow;
        ph%=mod;
        ph+=(curr*pow[n-1])%mod;
        ph%=mod;
        return ph;
    }
    static long deninv(long base,int pow,int mod) {
        //long deninverse =  deninv((int)den, mod - 2, mod);
        long res=base;
        long ans=1;
        while(pow!=0) {
            int bit=pow&1;
            if(bit==1) {
                ans=ans*res;
                ans%=mod;
            }
            res=res*res;
            res%=mod;
            pow=pow>>1;
        }
        return ans;
    }
}
//Double Hashing
class DHash {
    private final int p1=31,p2=101,mod1=(int)1e9+7,mod2=(int)1e9+9,n;
    long[] pow1,pow2;
    final long denpow1,denpow2;
    final StringBuilder sb;
    DHash(StringBuilder temp,int n) {
        this.n=n;
        this.pow1=new long[n+1];
        this.pow2=new long[n+1];
        long m1=1,m2=1;
        for(int i=0;i<=n;i++) {
            pow1[i]=m1;
            m1*=p1;
            m1%=mod1;
            pow2[i]=m2;
            m2*=p2;
            m2%=mod2;
        }
        this.sb=temp;
        this.denpow1=deninv(p1,mod1-2,mod1);
        this.denpow2=deninv(p2,mod2-2,mod2);
    }
    long[] hashing(int s) {
        long hash1=0,hash2=0;
        long m1=1,m2=1;
        for(int i=0;i<n;i++) {
            hash1+=(m1*(sb.charAt(s+i)))%mod1;
            hash1%=mod1;
            m1*=p1;
            m1%=mod1;
            hash2+=(m2*(sb.charAt(s+i)))%mod2;
            hash2%=mod2;
            m2*=p2;
            m2%=mod2;
        }
        return new long[]{hash1,hash2};
    }

    long[] update(long[] ph,int s,int e) {
        char prev=sb.charAt(s-1),curr=sb.charAt(e);
        long ph1=ph[0],ph2=ph[1];
        ph1=(ph1-prev+mod1)%mod1;
        ph1*=denpow1;
        ph1%=mod1;
        ph1+=(pow1[n-1]*curr)%mod1;
        ph1%=mod1;
        ph2=(ph2-prev+mod2)%mod2;
        ph2*=denpow2;
        ph2%=mod2;
        ph2+=(pow2[n-1]*curr)%mod2;
        ph2%=mod2;
        return new long[]{ph1,ph2};
    }
    static boolean equal(long[] arr1,long[] arr2) {
        return Arrays.equals(arr1,arr2);
    }
    static long deninv(long base,int pow,int mod) {
        //long deninverse =  deninv((int)den, mod - 2, mod);
        long res=base;
        long ans=1;
        while(pow!=0) {
            int bit=pow&1;
            if(bit==1) {
                ans=ans*res;
                ans%=mod;
            }
            res=res*res;
            res%=mod;
            pow=pow>>1;
        }
        return ans;
    }
}
