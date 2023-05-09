package std.guedes.jdbctest.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe de acesso ao banco de dados.
 */
public class Database {

    private static Connection connection;

    /**
     * Retorna a conexão com o banco de dados.
     *
     * @return Retorna uma conexão com o banco de dados.
     *
     * @throws DatabaseException Falha na tentativa de conexão.
     */
    public static Connection getConnection() throws DatabaseException {
        try {
            if(Database.connection == null) {
                try {
                    Properties properties = Database.loadProperties();
                    final String URL = properties.getProperty("url");
                    Database.connection = DriverManager.getConnection(URL, properties);
                    return Database.connection;
                } catch (PropertiesException e) {
                    throw new RuntimeException("exception: .properties file read failed");
                }
            }
            else if(Database.connection.isClosed()) {
                Database.connection = null;
                return Database.getConnection();
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return Database.connection;
    }

    /**
     * Retorna um properties contendo todas as informações de acesso ao banco de dados.
     *
     * @return Retorna um properties contendo todas as informações de acesso ao banco de dados.
     *
     * @throws PropertiesException Falha na leitura do arquivo.
     */
    private static Properties loadProperties() throws PropertiesException {
        try {
            String propertiesPath = System.getProperty("user.dir").concat("\\src\\main\\resources\\database.properties");
            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }
    }

}