package Alexa.seminar_1;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

/*
Напишите программу, которая использует Stream API для обработки списка чисел.
 Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Task1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        createList(list);
       OptionalDouble average =  list.stream().filter(value -> value % 2 == 0).mapToDouble(d -> d).average();
        System.out.println("Список: "+ list + "\n" + "Среднее значение четных: " + average);
    }

    public static List<Integer> createList(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            list.add(random.nextInt(20));
        }
        return list;
    }


}
