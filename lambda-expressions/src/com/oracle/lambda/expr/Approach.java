package com.oracle.lambda.expr;

import java.util.List;

/**
 * Created by smdaziz on 4/20/2017.
 */
public class Approach {

    //Approach # 1
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person person : roster) {
            if(person.getAge() >= age) {
                person.printPerson();
            }
        }
    }

    //Approach # 2
    public static void printPersonsWithinAgeRange(List<Person> roster, int minAge, int maxAge) {
        for (Person person : roster) {
            if(minAge <= person.getAge() && person.getAge() < maxAge) {
                person.printPerson();
            }
        }
    }

    //Approach # 3
    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person person : roster) {
            if (tester.test(person)) {
                person.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for(Person person : roster) {
            if(tester.test(person)) {
                person.printPerson();
            }
        }
    }

}
