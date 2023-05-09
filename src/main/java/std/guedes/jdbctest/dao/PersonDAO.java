package std.guedes.jdbctest.dao;

import std.guedes.jdbctest.database.DatabaseException;
import std.guedes.jdbctest.model.Person;
import java.util.List;

/**
 * Objeto de acesso a dados para a entidade pessoa (Person).
 */
public interface PersonDAO {

    /**
     * Insere ou atualiza uma pessoa (Person) no banco de dados.
     *
     * @param person Pessoa (Person) que será persistida.
     *
     * @throws DatabaseException Falha na persistência do registro.
     */
    void save(Person person) throws DatabaseException;

    /**
     * Retorna um registro de uma pessoa (Person) do banco de dados por meio do ‘id’.
     *
     * @param id ‘id’ da pessoa (Person) que está inserida no banco de dados.
     *
     * @return Retorna um registro de uma pessoa (Person) do banco de dados.
     *
     * @throws DatabaseException Falha na busca no banco de dados.
     */
    Person findById(Long id) throws DatabaseException;

    /**
     * Retorna um registro de uma pessoa (Person) do banco de dados por meio do nome (name).
     *
     * @param name Nome (name) da pessoa (Person) que está inserida no banco de dados.
     *
     * @return Retorna um registro de uma pessoa (Person) do banco de dados.
     *
     * @throws DatabaseException Falha na busca no banco de dados.
     */
    Person findByName(String name) throws DatabaseException;

    /**
     * Retorna todos os registros que contenham o mesmo primeiro nome.
     *
     * @param firstName Primeiro nome que será buscado.
     *
     * @return Retorna todos os registros que contenham o mesmo primeiro nome.
     *
     * @throws DatabaseException Falha na busca no banco de dados
     */
    List<Person> findAllByFirstName(String firstName) throws DatabaseException;

    /**
     * Retorna um registro de uma pessoa (Person) do banco de dados por meio do último nome (lastName).
     *
     * @param lastName Último nome (lastName) da pessoa (Person) que está inserida no banco de dados.
     *
     * @return Retorna um registro de uma pessoa (Person) do banco de dados.
     *
     * @throws DatabaseException Falha na busca no banco de dados.
     */
    Person findByLastName(String lastName) throws DatabaseException;

    /**
     * Retorna todos os registros.
     *
     * @return Retorna todos os registros.
     *
     * @throws DatabaseException Falha na busca no banco de dados
     */
    List<Person> findAll() throws DatabaseException;

    /**
     * Remove um registro do banco de dados.
     *
     * @param person Pessoa (person) que será removida.
     *
     * @throws DatabaseException Falha na remoção do registro.
     */
    void delete(Person person) throws DatabaseException;

}
