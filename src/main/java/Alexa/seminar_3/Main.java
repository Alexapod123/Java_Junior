package Alexa.seminar_3;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person1 = new Person("Alexa", 25);
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/Alexa/seminar_3/Persons.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(person1);
            System.out.println("Успешная сериализация");
        }
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/Alexa/seminar_3/Persons.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            person1 = (Person) objectInputStream.readObject();
            System.out.println("Успешная дессериализация");
        }



    }

}
