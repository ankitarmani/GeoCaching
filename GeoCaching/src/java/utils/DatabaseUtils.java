/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Beatriz
 */
public final class DatabaseUtils {

    /**
     * Private constructor.
     */
    private DatabaseUtils() { }

    /**
     * Begins a transaction using utx because we are using JTA EM.
     * @param emf .
     * @param utx .
     * @return .
     * @throws ServletException .
     */
    public static EntityManager startDBTransaction(
            final EntityManagerFactory emf, final UserTransaction utx)
            throws ServletException {
        try {
            utx.begin();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        EntityManager em = emf.createEntityManager();
        return em;
    }

    /**
     * Makes effective the change in the DB.
     * @param utx .
     * @param em .
     */
    public static void endDBTransaction(final UserTransaction utx,
            final EntityManager em) {
        try {
            utx.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }
}
