package std.guedes.jdbctest.dao;

import std.guedes.jdbctest.dao.impl.PersonDAOImpl;
import std.guedes.jdbctest.database.Database;
import std.guedes.jdbctest.database.DatabaseException;

/**
 * Classe que disponibiliza métodos que retornam subtipos de interfaces DAO das entidades do banco de dados.
 */
public class FactoryDAO {

    public static PersonDAO createPersonDAO() throws DatabaseException {
        return new PersonDAOImpl(Database.getConnection());
    }

}
