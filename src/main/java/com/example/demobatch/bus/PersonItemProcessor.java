package com.example.demobatch.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {

        final String firstName = person.firstName();
        final String lastName = person.lastName();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Convertion (" + person + ") into ( " + transformedPerson + " )");
        return transformedPerson;
    }
}
