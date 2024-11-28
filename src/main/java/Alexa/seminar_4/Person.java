package Alexa.seminar_4;
import javax.persistence.*;
@Entity
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="Имя")
    private String name;
    @Column(name="Фамилия")
    private String lastname;
    @Column(name="Возраст")
    private int age;

    public Person(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Person() {}
}
