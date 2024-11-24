package Alexa.seminar_2;

import java.lang.reflect.Method;

public class Task {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> string = Class.forName("java.lang.String");
        Method[] methods = string.getDeclaredMethods();
        for(Method method: methods){
            System.out.println(method);
        }
    }
}
