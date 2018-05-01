package Reflection;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Repeatable(MyAnnotationList.class)
public @interface MyAnnotation {
    int a();
    String b();
    long clock();
    int[] values() default {};
    String value() default "hi";
}

@Inherited
@Target({ElementType.ANNOTATION_TYPE})
@interface MyAnnotationList {
    MyAnnotation[] value();
}

@interface SingleFieldAnnotaion{
    int value();
}