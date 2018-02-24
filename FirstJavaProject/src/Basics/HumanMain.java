package Basics;

import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HumanMain {

    public static void myFunc(ArrayList<Object> list) {
        list.add("String");
        list.add(34543534);
        //ArrayList<Integer> intArray=list;
    }

    public static void append2(ArrayList<? super Integer> list) {
        list.add(new Integer(435));
    }

    public static<T extends Number> void append(ArrayList<T> list, T element) {
        element.floatValue();
        list.add(element);
    }

    public static void append3(ArrayList<Number> list, Number element) {
        element.floatValue();
        list.add(element);
    }

    public static <T extends Number> void myFunc(T arg1,T arg2) {
        LinkedList<Integer> intList=new LinkedList<>();
        LinkedList<Double> doubleList=new LinkedList<>();
        LinkedList<Number> numbetList=new LinkedList<>();

        //intList=doubleList;

        ArrayList<T> a[]=new ArrayList[100];

        //arg.toString();
        //Object o=new T();
        //T[] a=new T[100];
        //Integer a=arg;
    }

    public static void main(String args[]) {
        Human human=new Human();
        Human.Eye eye=human.new Eye();
        Human.HumanStatistics humanStats=new Human.HumanStatistics();

        LinkedList<String> myList=new LinkedList<>();

        ArrayList generalList=new ArrayList<>();
        ArrayList<Object> objectList=new ArrayList<>();
        ArrayList stringList=new ArrayList<>();

        ArrayList<Number> numberList=new ArrayList<>();
        ArrayList<Integer> intList=new ArrayList<>();
        ArrayList<Double> doubleList=new ArrayList<>();
        //numberList=intList;

        //append3(doubleList,new Integer(324));

        objectList.add(345345);

        for (Object obj:stringList) {
            System.out.println(obj.toString());
        }

        Object a=new Human(){
          int a;
          String b;
        };

        //myFunc(a);
        //myFunc(eye);
        //myFunc(humanStats);

        Object aa=new Object(){

        };

        ArrayList<Integer> intArray=new ArrayList<>();
        ArrayList<Double> doubleArray=new ArrayList<>();
        ArrayList<Human> humanArray=new ArrayList<>();

        Number myNum=new Number() {
            String name;
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        };
    }
}
