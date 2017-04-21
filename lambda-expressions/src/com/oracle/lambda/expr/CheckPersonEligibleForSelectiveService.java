package com.oracle.lambda.expr;

/**
 * Created by 847557 on 4/20/2017.
 */
public class CheckPersonEligibleForSelectiveService implements CheckPerson {

    public boolean test(Person person) {
        return person.gender == Person.Sex.MALE &&
                person.getAge() >= 18 &&
                person.getAge() <= 25;
    }

}
