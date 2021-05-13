package com.ldt.main;

import com.ldt.utils.MyUtils;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyMain {
    public static void main(String[] args) throws Exception {
        callMethod();
        callMethodStatic();
        callField();
        callFieldStatic();

        springTestCallMethod();
        springTestCallMethodStatic();
        springTestCallField();
        springTestCallFieldStatic();
    }

    /**
     * Get value of non-static field "var1" using reflection API
     * @throws Exception
     */
    private static void callField() throws Exception{
        Field field = MyUtils.class.getDeclaredField(
                "var1");
        field.setAccessible(true);
        Integer result = (Integer) field.get(new MyUtils());
        System.out.println(result);
    }

    /**
     * Get value of static field "var2" using reflection API
     * @throws Exception
     */
    private static void callFieldStatic() throws Exception{
        Field field = MyUtils.class.getDeclaredField(
                "var2");
        field.setAccessible(true);
        Integer result = (Integer) field.get(MyUtils.class);
        System.out.println(result);
    }

    /**
     * Call non-static method plusNumber using reflection API
     * @throws Exception
     */
    private static void callMethod() throws Exception {
        int a = 1;
        int b = 2;
        Method indexOfMethod = MyUtils.class.getDeclaredMethod(
                "plusNumber", int.class, int.class);
        indexOfMethod.setAccessible(true);
        Integer result = (Integer) indexOfMethod.invoke(new MyUtils(), a, b);
        System.out.println(result);
    }

    /**
     * Call static method subNumber using reflection API
     * @throws Exception
     */
    private static void callMethodStatic() throws Exception{
        int a = 10;
        int b = 2;
        Method indexOfMethod = MyUtils.class.getDeclaredMethod(
                "subNumber", int.class, int.class);
        indexOfMethod.setAccessible(true);
        Integer result = (Integer) indexOfMethod.invoke(MyUtils.class, a, b);
        System.out.println(result);
    }


    /**
     * Call non-static method "plusNumber" using SpringTestUtils library
      */
    private static void springTestCallMethod() {
        Integer value = ReflectionTestUtils.invokeMethod(
                new MyUtils(), "plusNumber", 1, 2 );
        System.out.println(value);
    }

    /**
     * Call static method "subNumber" using SpringTestUtils library
     */
    private static void springTestCallMethodStatic() {
        Integer value = ReflectionTestUtils.invokeMethod(
                MyUtils.class, "subNumber", 10, 2 );
        System.out.println(value);
    }

    /**
     * Call non-static variable "var1" using SpringTestUtils library
     */
    private static void springTestCallField(){
        Integer value = (Integer) ReflectionTestUtils.getField(new MyUtils(), "var1");
        System.out.println(value);
    }

    /**
     * Call static variable "var2" using SpringTestUtils library
     */
    private static void springTestCallFieldStatic(){
        Integer value = (Integer) ReflectionTestUtils.getField(MyUtils.class, "var2");
        System.out.println(value);
    }



}
