package com.java8.reference;

import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.function.Supplier;

/**
 * 官方方法引用示例：https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 * @author wanchongyang
 * @date 2018/4/10 下午7:05
 */
public class MethodReferencesTest {

    // The method transferElements copies elements from one collection to
    // another

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(
            SOURCE sourceCollection,
            Supplier<DEST> collectionFactory) {

        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }

    public static void main(String... args) {

        List<Person> roster = Person.createRoster();

        for (Person p : roster) {
            p.printPerson();
        }

        Person[] rosterAsArray =
                roster.toArray(new Person[roster.size()]);


        class PersonAgeComparator implements Comparator<Person> {
            @Override
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        // Without method reference
        Arrays.sort(rosterAsArray, new PersonAgeComparator());

        // With lambda expression
        Arrays.sort(rosterAsArray,
                (Person a, Person b) -> {
                    return a.getBirthday().compareTo(b.getBirthday());
                }
        );

        Arrays.sort(rosterAsArray, (a, b) -> a.getBirthday().compareTo(b.getBirthday()));

        // With method reference
        Arrays.sort(rosterAsArray, Person::compareByAge);
        System.out.println("==========compareByAge============");
        Arrays.stream(rosterAsArray).forEach(Person::printPerson);

        // Reference to an instance method of a particular object
        class ComparisonProvider {
            public int compareByName(Person a,
                                     Person b) {
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(Person a,
                                    Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);

        System.out.println("==========compareByName============");
        Arrays.stream(rosterAsArray).forEach(Person::printPerson);

        // Reference to an instance method
        // of an arbitrary object of a particular type

//        String[] stringArray = { "Barbara", "James", "Mary", "John",
//                "Patricia", "Robert", "Michael", "Linda" };
//        Arrays.sort(stringArray, String::compareToIgnoreCase);
        // 类名:实例方法名
        Arrays.sort(rosterAsArray, Person::comparePersonByAge);
        System.out.println("==========comparePersonByAge============");
        Arrays.stream(rosterAsArray).forEach(Person::printPerson);

        System.out.println("===rosterSetLambda(LinkedHashSet)===");
        Set<Person> rosterSetLambda =
                transferElements(roster, () -> { return new LinkedHashSet<>(); });
        rosterSetLambda.stream().forEach(p -> p.printPerson());

        Set<Person> rosterSet = transferElements(
                roster, HashSet::new);
        System.out.println("=========rosterSet(HashSet)========");
        rosterSet.stream().forEach(p -> p.printPerson());
        
        Supplier<HashSet<Person>> supplier = HashSet::new;
        Supplier<HashSet> supplier2 = () -> new HashSet<>();

        // element => Person
        HashSet<Person> hashSet = supplier.get();
        hashSet.add(new Person(
                "Jane",
                IsoChronology.INSTANCE.date(1990, 7, 15),
                Person.Sex.FEMALE, "jane@example.com"));
        HashSet hashSet2 = supplier2.get();

        // element => Object
        hashSet2.add(new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fred@example.com"));
        hashSet2.add("angel");
        System.out.println(hashSet);
        System.out.println(hashSet2);
    }

}

