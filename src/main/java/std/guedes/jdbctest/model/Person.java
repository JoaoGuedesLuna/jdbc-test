package std.guedes.jdbctest.model;

import std.guedes.jdbctest.model.builder.PersonBuilder;
import java.util.Objects;

/**
 * Essa classe representa uma entidade do banco de dados.
 */
public class Person {

    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(this.id, person.id) && Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Person{id=" + this.id + ", name='" + this.name + "'}";
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

}