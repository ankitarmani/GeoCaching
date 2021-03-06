/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import database.Geouser;
import models.User;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

/**
 *
 * @author Beatriz
 */
public class UserManagementMethods {

    /**
     * This is needed for the Java Persistence.
     */
    private EntityManager em;

    /**
     * Constructor.
     * @param em1 entity manager for JPA.
     */
    public UserManagementMethods(final EntityManager em1) {
        this.em = em1;
    }

    /**
     * Authentication.
     * @param mUser user.
     * @return used id.
     */
    public final int authenticate(final models.User mUser) {
        Object[] dbUsers = em.createNamedQuery("Geouser.findByUsername").
                setParameter("username", mUser.getUsername()).
                getResultList().toArray();
        if (dbUsers.length == 0) {
            return -1;
        }

        Geouser dbUser = (Geouser) dbUsers[0];
        if (dbUser.getPassword().equals(mUser.getPassword())) {
            return dbUser.getIdUser();
        }
        return -1;
    }

    /**
     * Registration.
     * @param mUser user.
     * @return success.
     */
    public final boolean register(final User mUser) {
        int nLastUserId;
        try {
            nLastUserId = Integer.parseInt((
                    em.createNamedQuery("Geouser.getLastUserId").
                    getResultList().get(0)).toString());
        } catch (Exception e) {
            nLastUserId = 0;
        }

        Geouser dbUser = new Geouser(nLastUserId + 1);
        dbUser = takeValuesFromModelUser(dbUser, mUser);

        try {
            em.persist(dbUser);
        } catch (EntityExistsException e) {
            return false;
        }
        return true;
    }

    /**
     * Take values from user to DB.
     * @param dbUser db user.
     * @param mUser model user.
     * @return db user.
     */
    private database.Geouser takeValuesFromModelUser(
            final database.Geouser dbUser, final models.User mUser) {
        dbUser.setUsername(mUser.getUsername());
        dbUser.setPassword(mUser.getPassword());
        dbUser.setEmail(mUser.getEmail());
        return dbUser;
    }
}