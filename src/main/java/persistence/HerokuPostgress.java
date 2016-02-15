package persistence;

import exception.HerokuDatabaseUrlException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HerokuPostgress implements IPersistence {

    private EntityManagerFactory emFactory = null;

    @Override
    public void close() {
        emFactory.close();
    }

    @Override
    public EntityManager createEntityManager() {
        if (null == emFactory || !emFactory.isOpen()) {
            initializeFactory();
        }

        return emFactory.createEntityManager();
    }

    private void initializeFactory() {
        
        // TODO maybe this can be done with this
        // Connection con = DatabaseUrl.extract().getConnection();
        
        URI dbUri;

        String env = System.getenv("DATABASE_URL");
        
        try {
            dbUri = new URI(env);
        } catch (URISyntaxException e) {
            String s = String.format("Invalid environment variable DATABASE_URL: ''", env);
            throw new HerokuDatabaseUrlException(s, e);
        }
        
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];

        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url", dbUrl);
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        // properties.put( "hibernate.hbm2ddl.auto", "create-drop");

        //if(CustomLogger.LOG_LEVEL.intValue() <= Level.FINE.intValue()){
        //	properties.put("hibernate.show_sql", "true");
        //}
        
        emFactory = Persistence.createEntityManagerFactory("default", properties);
    }

}
