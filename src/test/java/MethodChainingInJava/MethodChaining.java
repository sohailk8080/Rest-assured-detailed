package MethodChainingInJava;

public class MethodChaining {

    public static MethodChaining m1(){
        System.out.println("This is M1");
        return new MethodChaining();
    }
    public static MethodChaining m2(){
        System.out.println("This is M2");
        return new MethodChaining();
    }
    public static MethodChaining m3(){
        System.out.println("This is M3");
        return new MethodChaining();
    }

    public static void main(String[] args){
        m1().m2().m3();
    }
}
