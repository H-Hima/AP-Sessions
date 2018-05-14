package Reflection;

import drawing.Point2;

import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class MainInvocation {
    private int privateField;
    public int publicField;

    public MainInvocation(int value) {
        System.out.println("Constructor with an integer argument");
        privateField = value;
    }

    MainInvocation() {
        System.out.println("Constructor without any argument");
        privateField = 0;
    }

    public static ArrayList<String> staticFunction(int a) {
        System.out.println("Invocation Test.");
        return new ArrayList<>();
    }

    private ArrayList<Integer> function() {
        System.out.println("Invocation public Test: " + publicField);
        System.out.println("Invocation private Test: " + privateField);
        return new ArrayList<>();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException, MalformedURLException {

        Method staticMethod = MainInvocation.class.getMethod("staticFunction", int.class);
        Method method = MainInvocation.class.getDeclaredMethod("function", null);

        Field publicF = MainInvocation.class.getField("publicField");
        Field privateF = Point2.class.getDeclaredField("x");

        staticMethod.invoke(null, 10);
        MainInvocation object = new MainInvocation();
        method.invoke(object, null);

        Point2 p = new Point2(10, 10);
        privateF.setAccessible(true);
        privateF.set(p, 123);
        Object o = privateF.get(p);
        method.invoke(object, null);

        Field f = ArrayList.class.getDeclaredField("serialVersionUID");
        ArrayList test = new ArrayList();
        f.setAccessible(true);
        f.get(null);

        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            Type[] generics = ptype.getActualTypeArguments();
            for (Type gtype : generics) {
                System.out.println(gtype);
            }
        }

        Class clas = MainInvocation.class;
        Class classByName = Class.forName("java.lang.String");
        Class classArrayByName = Class.forName("[Ljava.lang.String;");//identical with String[].class
        Class intClassByName = Class.forName("[I");//identical with int[].class

        Array.newInstance(int.class, 10);//int[]
        Array.newInstance(intClassByName, 12);//int[][]

        Array.newInstance(String.class, 100);//String[]
        Array.newInstance(classByName, 100);//String[]

        Array.newInstance(String[].class, 100);//String[][]
        Array.newInstance(classArrayByName, 100);//String[][]

        Array.newInstance(clas, 10);//MainInvocation[]

        System.out.println(clas.getConstructors().length);
        System.out.println(clas.getDeclaredConstructors().length);
        clas.newInstance();
        clas.getDeclaredConstructors()[0].newInstance(102);
        clas.getDeclaredConstructors()[1].newInstance();

        URLClassLoader loader = new URLClassLoader(new URL[]{new URL("file:///C:/Users/Hima/Desktop/GitFolder/")});

        Class pointClass = loader.loadClass("drawing.Point");
        System.out.println(pointClass.getDeclaredFields().length);
        Object point=pointClass.getConstructor(double.class,double.class).newInstance(20,40);
        System.out.println(point);

        Class rectClass = loader.loadClass("drawing.Rectangle");
        Object rectangle=rectClass.getConstructor(double.class,double.class).newInstance(10.0,20.0);
        System.out.println(rectangle.toString());
    }
}
