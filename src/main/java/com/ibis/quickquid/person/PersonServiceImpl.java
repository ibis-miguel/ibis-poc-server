package com.ibis.quickquid.person;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonServiceImpl implements  PersonService{

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    @Override
    public Person savePerson(Person person) {
//        if (personRepository.findById(person.getId()).isPresent() && person.getId() !=  null ) {
//            throw new IllegalArgumentException("Person already exists with ID: " + person.getId());
//        }
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

}
