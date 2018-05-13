package com.guillaume.libraryapi;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Lambda {

    public static void main(String[] args) {

      //  System.out.println(apply(n -> n > 10, 5));
        String s = "";
        System.out.println(s.split(" ").length);

    }

    private static <T>  boolean apply(Predicate<T> c, T e1){
        return c.test(e1);
    }

    private interface Calcul<X, Y, Z>{

        public Z calcul(X x, Y y);
    }
}
