package std.guedes.jdbctest;

import std.guedes.jdbctest.database.Database;
import std.guedes.jdbctest.database.DatabaseException;

/**
 * @author João Guedes.
 */
public class JDBCTestRun {

    /**
     * Verifica a conexão com o banco de dados.
     */
    private static void verifyConnection() {
        try {
            Database.getConnection();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws DatabaseException {

    }

}
