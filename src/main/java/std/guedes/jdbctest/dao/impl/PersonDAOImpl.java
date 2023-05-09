package std.guedes.jdbctest.dao.impl;

import std.guedes.jdbctest.dao.PersonDAO;
import std.guedes.jdbctest.database.DatabaseException;
import std.guedes.jdbctest.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da ‘interface’ PersonDAO.
 */
public class PersonDAOImpl implements PersonDAO {

    private final Connection CONNECTION;

    public PersonDAOImpl(Connection connection) throws DatabaseException  {
        this.CONNECTION = connection;
    }

    @Override
    public void save(Person person) throws DatabaseException {
        if (person.getId() == null) {
            this.insert(person);
        }
        else {
            this.update(person);
        }
    }

    /**
     * Insere uma Person (Pessoa) no banco de dados.
     *
     * @param person Person (Pessoa) que será inserida.
     *
     * @throws DatabaseException Falha na inserção do registro.
     */
    private void insert(Person person) throws DatabaseException {
        String query = "INSERT INTO `people` (`name`) VALUES (?)";
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            if (statement.executeUpdate() > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    person.setId(resultSet.getLong(1));
                    resultSet.close();
                }
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("error: person not inserted!");
        }
    }

    /**
     * Atualiza uma Person (Pessoa) do banco de dados.
     *
     * @param person Person (Pessoa) que será atualizado.
     *
     * @throws DatabaseException Falha na atualização do registro.
     */
    private void update(Person person) throws DatabaseException {
        String query = "UPDATE `people` SET `name` = ? WHERE `id` = ?";
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            statement.setString(1, person.getName());
            statement.setLong(2, person.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DatabaseException("error: person not updated!");
        }
    }

    @Override
    public Person findById(Long id) throws DatabaseException {
        String query = "SELECT * FROM `people` WHERE `id` = ?";
        Person person = null;
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                resultSet.close();
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("error: could not find the person!");
        }
        return person;
    }

    @Override
    public Person findByName(String name) throws DatabaseException {
        String query = "SELECT * FROM `people` WHERE `name` = ? GROUP BY `name`";
        Person person = null;
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                resultSet.close();
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("error: could not find the person!");
        }
        return person;
    }

    @Override
    public List<Person> findAllByFirstName(String firstName) throws DatabaseException {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM people WHERE `name` LIKE CONCAT(?, '%')";
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            Person person;
            while (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                people.add(person);
            }
            resultSet.close();
            return people;
        }
        catch (SQLException e) {
            throw new DatabaseException("ERROR: Products not found!");
        }
    }

    @Override
    public Person findByLastName(String lastName) throws DatabaseException {
        String query = "SELECT * FROM `people` WHERE `name` LIKE CONCAT('%', ?) GROUP BY `name`";
        Person person = null;
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                resultSet.close();
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("error: could not find the person!");
        }
        return person;
    }

    @Override
    public List<Person> findAll() throws DatabaseException {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM people";
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            Person person;
            while (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                people.add(person);
            }
            resultSet.close();
            return people;
        }
        catch (SQLException e) {
            throw new DatabaseException("ERROR: Products not found!");
        }
    }

    @Override
    public void delete(Person person) throws DatabaseException {
        String query = "DELETE FROM `people` WHERE `id` = ?";
        try (PreparedStatement statement = this.CONNECTION.prepareStatement(query)) {
            statement.setLong(1,person.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new DatabaseException("error: people not deleted!");
        }
    }

}
