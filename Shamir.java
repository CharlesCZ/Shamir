
import java.util.LinkedList;
import java.util.Random;

public class Shamir {

    private long secret;
private long p;
private long n;
private long t;
private long[] si;
private long[] parts;
    public long getSecret() {
        return secret;
    }

    public void setSecret(long secret) {
        this.secret = secret;
    }

    public long getP() {
        return p;
    }

    public void setRandomP(long p) {
       Random rand=new Random();
       this.p= rand.nextInt(10000)+secret+1+n;

    }

    public void setP(long p) {

         this.p = p;
    }
    public long getN() {
        return n;
    }

    public void setN(int n) {
        parts=new long[n];
        this.n = n;
    }

    public long getT() {
        return t;
    }

    public void setT(int t) {
        si=new long[t];
        this.t = t;
    }

    long[][] encode(){


        si[0]=secret;
        Random rand=new Random();
//obliczamy sama funkcje f(x)
        for(int i=1;i<t;++i)
        {

            si[i]=rand.nextInt((int)secret-1)+10;

        }

        System.out.println("czesci wielomianu"+" "+si[2]+" "+si[1]+" "+si[0]);
//liczymy fragmenty
        for(int i=0;i<n;++i)
        {
            parts[i]=si[0];
            for(int j=1;j<t;++j)
            parts[i]+=si[j]*(long)Math.pow(i+1,j);
         //   parts[i]+=(si[1]*(long)Math.pow(i+1,1)+si[2]*(long)Math.pow(i+1,2));


            parts[i]=parts[i]%p;
        }

    long[][] temp=new long[parts.length][2];


        for(int i=0;i<parts.length;++i) {
            temp[i][0] = i+1;
            temp[i][1] = parts[i];

        }
return temp;
    }


    long decode(long[][] czesci,int k){

        //Computationally Efficient Approach  Lagrange polynomials method
double[] li=new double[k];

   /* li[0]=(czesci[1][0]*czesci[2][0]*czesci[0][1]/((double) (czesci[0][0]-czesci[1][0])*(czesci[0][0]-czesci[2][0])  ))%p;

    li[1]=(czesci[0][0]*czesci[2][0]*czesci[1][1]/((double) (czesci[1][0]-czesci[0][0])*(czesci[1][0]-czesci[2][0])  ))%p;

    li[2]=(czesci[0][0]*czesci[1][0]*czesci[2][1]/((double) (czesci[2][0]-czesci[0][0])*(czesci[2][0]-czesci[1][0])  ))%p;


        System.out.println(li[0]+li[1]+li[2]);*/


        for(int j=0;j<t;++j) {
li[j]=1;
         for(int m=0;m<t;++m)
             if(j!=m)
            li[j] *= (czesci[m][0]/(double)(czesci[m][0]-czesci[j][0]));

            li[j]=(li[j]*czesci[j][1])%p;

        }

        return (long) Math.round(li[0]+li[1]+li[2]);
    }
}

