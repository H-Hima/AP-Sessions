package Reflection;

import drawing.PaintPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Main {
    @Deprecated
    @SuppressWarnings("all")
    @MyAnnotation(value = "a",b="lasjkhnfdl",clock=10,a=5)
    @SingleFieldAnnotaion(234)
    public static void main(String args[]) {
        String str="test string";
        PaintPanel panel=new PaintPanel(new Dimension(10,10));
        Class type=panel.getClass();
        System.out.println(type.toString());
        System.out.println(type.getCanonicalName());

        Annotation[] annotations = type.getAnnotations();
        //type.getDeclaredAnnotations();
        for(Annotation annotation:annotations) {
            System.out.println(annotation);
            if (annotation instanceof MyAnnotation) {
                MyAnnotation a=(MyAnnotation) annotation;
                System.out.println("A");
            }
            else if(annotation instanceof Deprecated) {
                Deprecated a=(Deprecated)annotation;
                System.out.println("B");
            }
        }

        if(true) {
            return;
        }

        PaintPanel panel2=new PaintPanel(new Dimension(10,10));
        System.out.println(panel.getClass().toString());
        type=panel.getClass();
        Method methods[]= type.getMethods();
        //type.getDeclaredMethods(); //To get all methods not only public ones

        for(Method method: methods) {
            annotations = method.getAnnotations();
            method.getDeclaredAnnotations();

            Annotation[][] methodAnnotations = method.getParameterAnnotations();
            method.getParameters();

            try {
                method.getParameterTypes();
                method.setAccessible(true);
                method.invoke(panel,10,20);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            System.out.println(method.toString());
            Parameter parameters[] = method.getParameters();
            System.out.println("==================");
            for(Parameter var: parameters) {
                System.out.println(var.toString());
            }
            Class exceptions[] = method.getExceptionTypes();
            System.out.println("==================");
            for(Class exType: exceptions) {
                System.out.println(exType.getCanonicalName());
            }
            System.out.println("==================");
            System.out.println(method.getReturnType());
            System.out.println(method.getModifiers());
        }

        Field[] fields = type.getFields();
        //type.getDeclaredFields(); //to get all fields even private ones
        for(Field field:fields) {
            System.out.println(field.toString());
            System.out.println(field.getModifiers());
            System.out.println(field.getType());
            try {
                field.setAccessible(true);
                field.get(panel);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Constructor constructors[] = type.getConstructors();
        for(Constructor constructor:constructors) {
            System.out.println(constructor.toString());
            constructor.getParameters();
            Parameter parameters[] = constructor.getParameters();
            System.out.println("==================");
            for(Parameter var: parameters) {
                System.out.println(var.toString());
            }
        }

        try {
            type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
