package Alexa.seminar_4;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class Connector {
    StandardServiceRegistry registry;
    SessionFactory sessionFactory;

    public Connector(StandardServiceRegistry registry, SessionFactory sessionFactory) {
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources()
                .buildMetadata()
                .buildSessionFactory();
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
