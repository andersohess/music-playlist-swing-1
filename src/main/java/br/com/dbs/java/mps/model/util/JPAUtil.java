package br.com.dbs.java.mps.model.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class JPAUtil {
    
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("playlistPU");
    
    private static EntityManager em;
    
    public static EntityManager createEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }
    
    public static boolean closeEntityManager() {
        try {
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static  Connection getConnection() throws SQLException{
    
      createEntityManager();
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi =(SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection conn = cp.getConnection();
        return conn;
    }
}
