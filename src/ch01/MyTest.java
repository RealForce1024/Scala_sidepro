package ch01;

import java.math.BigInteger;

/**
 * Created by fqc on 2016/7/12.
 */
public class MyTest {
    public static void main(String[] args) {
        int a = 1;
        int result =0;
        result = a*a*a;
        System.out.println(result);

        BigInteger x = new BigInteger("3");
        BigInteger result2 = new BigInteger("0");
        result2 = x.multiply(x).multiply(x);  //且不说声明的麻烦，就这里的调用方式就相当的不优雅。
        //result2 = x*x*x;
        System.out.println(result2);
    }
}
