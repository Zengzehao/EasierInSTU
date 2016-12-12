package com.example.zengzehao.messageshare.tools;

import java.util.Random;

/**
 * Created by zengzehao on 16-12-12.
 */

public class RandomStringUtil {
    public static String getString(int length){

    char[] charArray = new char[length];

    for(int i=0;i<length;i++){

        Random r = new Random();
        int n = r.nextInt(123);
        while(n<48 || (n>57 && n<65) || (n>90 && n <97) || n>122){//(!((n>=48 && n<=57) || (n>=65 && n<=90) && (n>=97 && n<=122))){
            n = r.nextInt(123);
        }
        charArray[i] = (char)n ;
    }

    return String.valueOf(charArray) ;

}
}