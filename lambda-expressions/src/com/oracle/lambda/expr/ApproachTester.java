package com.oracle.lambda.expr;

import java.util.List;

/**
 * Created by smdaziz on 4/20/2017.
 */
public class ApproachTester {

    public static void main(String args[]) {

        List<Person> roster = Person.createRoster();
        System.out.println(roster);
        System.out.println("******************************");

        //Approach # 1
        //Filtering logic coupled inside method
        Approach.printPersonsOlderThan(roster, 25);
        System.out.println("******************************");

        //Approach # 2
        //Filtering logic still coupled inside method,
        //but broadened via method parameters
        Approach.printPersonsWithinAgeRange(roster, 25, 30);
        System.out.println("******************************");

        //Approach # 3
        //Filtering logic delegated to a separate class and it's instance is passed
        CheckPerson criteria = new CheckPersonEligibleForSelectiveService();
        Approach.printPersons(roster, criteria);
        System.out.println("******************************");

        //Approach # 4
        //Filtering logic delegated to a separate class
        //and it's passed anonymously
        Approach.printPersons(roster, new CheckPersonEligibleForSelectiveService() {
            @Override
            public boolean test(Person person) {
                return person.gender == Person.Sex.FEMALE &&
                        person.getAge() <= 30;
            }
        });
        System.out.println("******************************");

        //Approach # 5
        //Since there is only one method the anonymous class uses in Approach # 4
        //Using Lambda Expression to simplify that approach
        Approach.printPersons(roster,
                (Person p) -> (p.gender == Person.Sex.MALE ||
                        p.gender == Person.Sex.FEMALE) &&
                        p.getAge() <= 30
                );
        System.out.println("******************************");

        //Approach # 6
        //Standard Functional Interfaces with Lambda Expressions
        Approach.printPersonsWithPredicate(roster,
                person -> (person.gender == Person.Sex.MALE ||
                        person.gender == Person.Sex.FEMALE) &&
                        person.getAge() <= 30
                );
        System.out.println("******************************");
    }

}
