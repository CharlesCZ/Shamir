public class Main {

    public static void main(String[] args) {
        Shamir shamir=new Shamir();
        shamir.setSecret(800);
        shamir.setT(4);
        shamir.setN(6);
        shamir.setP(1523);
       long[][] tab= shamir.encode();
        System.out.println(shamir.decode(tab,4));

     //   System.out.println(-8%3);
      //  System.out.println(shamir.decode(new long[][]{{2,383},{3,1045},{4,308}},3));

    }
}
