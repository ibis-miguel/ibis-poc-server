package com.ibis.quickquid.person;

import java.util.List;

public interface PersonService {

    Person savePerson(Person person);

    List<Person> findAllPerson();
}
