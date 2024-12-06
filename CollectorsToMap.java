import java.util.*;
import java.util.stream.Collectors;

public class ToMapExample {
    public static void main(String[] args) {
        // A list of people with the same name but different ages
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Alice", 35),
            new Person("Charlie", 40)
        );

        // Collecting into a Map where names are the key
        // If two people have the same name, keep the person with the higher age
        Map<String, Person> peopleMap = people.stream()
            .collect(Collectors.toMap(
                Person::getName, // Key: the person's name
                person -> person, // Value: the person itself
                (person1, person2) -> person1.getAge() > person2.getAge() ? person1 : person2 // Binary operator
            ));

        // Print the resulting map
        peopleMap.forEach((name, person) -> System.out.println(name + ": " + person));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
