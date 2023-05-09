package std.guedes.jdbctest.model.builder;

import std.guedes.jdbctest.model.Person;

/**
 * Builder para a classe Person (Pessoa)
 */
public class PersonBuilder {

    private final Person PERSON;

    public PersonBuilder() {
        this.PERSON = new Person();
    }

    public PersonBuilder id(Long id) {
        this.PERSON.setId(id);
        return this;
    }

    public PersonBuilder name(String name) {
        this.PERSON.setName(name);
        return this;
    }

    public Person build() {
        return this.PERSON;
    }

}
