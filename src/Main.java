import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Long peopleUpTo18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.printf("Number of minor residents of Silent hill - %,d.%n", peopleUpTo18);

        List<String> recruits = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(person -> person.getFamily()).toList();
        System.out.printf("Number of conscripts on the list - %,d.%n", recruits.size());

        List<Person> humanResources = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> {
                    if (person.getSex() == Sex.MAN) {
                        return person.getAge() >= 18 && person.getAge() <= 65;
                    } else {
                        return person.getAge() >= 18 && person.getAge() <= 60;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.printf("The number of able-bodied citizens with higher" +
                "education according to the list - %,d.%n", humanResources.size());

    }
}