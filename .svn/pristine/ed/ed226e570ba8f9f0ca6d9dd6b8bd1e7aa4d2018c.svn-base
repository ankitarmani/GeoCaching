/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name = "geouser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geouser.findAll", query = "SELECT g FROM Geouser g"),
    @NamedQuery(name = "Geouser.getLastUserId",
        query = "SELECT max(g.idUser) FROM Geouser g"),
    @NamedQuery(name = "Geouser.findByIdUser",
        query = "SELECT g FROM Geouser g WHERE g.idUser = :idUser"),
    @NamedQuery(name = "Geouser.findByUsername",
        query = "SELECT g FROM Geouser g WHERE g.username = :username"),
    @NamedQuery(name = "Geouser.findByPassword",
        query = "SELECT g FROM Geouser g WHERE g.password = :password"),
    @NamedQuery(name = "Geouser.findByEmail",
        query = "SELECT g FROM Geouser g WHERE g.email = :email") })
public class Geouser implements Serializable {
    /**
     * Max characters of name.
     */
    private static final int MAX_NAME = 100;
    /**
     * Max characters of password.
     */
    private static final int MAX_PASS = 50;
    /**
     * Max characters of email.
     */
    private static final int MAX_EMAIL = 100;
    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private Integer idUser;
    /**
     * Username.
     */
    @Size(max = MAX_NAME)
    @Column(name = "username")
    private String username;
    /**
     * Password.
     */
    @Size(max = MAX_PASS)
    @Column(name = "password")
    private String password;
    /**
     * Email.
     */
    @Size(max = MAX_EMAIL)
    @Column(name = "email")
    private String email;
    /**
     * Collection of completed tours.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geouser")
    private Collection<GeouserCompletedTour> geouserCompletedTourCollection;
    /**
     * Collection of created tours.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useridUser")
    private Collection<Tour> createdTourCollection;

    /**
     * Empty constructor.
     */
    public Geouser() {
    }

    /**
     * Constructor.
     * @param idUser1 id.
     */
    public Geouser(final Integer idUser1) {
        this.idUser = idUser1;
    }

    /**
     * Get id.
     * @return id.
     */
    public final Integer getIdUser() {
        return idUser;
    }

    /**
     * Set id.
     * @param idUser1 id.
     */
    public final void setIdUser(final Integer idUser1) {
        this.idUser = idUser1;
    }

    /**
     * Get username.
     * @return username.
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Set username.
     * @param username1 username.
     */
    public final void setUsername(final String username1) {
        this.username = username1;
    }

    /**
     * Get password.
     * @return password.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Set password.
     * @param password1 password.
     */
    public final void setPassword(final String password1) {
        this.password = password1;
    }

    /**
     * Get email.
     * @return email.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Set email.
     * @param email1 email.
     */
    public final void setEmail(final String email1) {
        this.email = email1;
    }

    /**
     * Get completed tour collection.
     * @return completed tour collection.
     */
    @XmlTransient
    public final Collection<GeouserCompletedTour>
            getGeouserCompletedTourCollection() {
        return geouserCompletedTourCollection;
    }

    /**
     * Set completed tour collection.
     * @param tourCollection completed tour collection.
     */
    public final void setGeouserCompletedTourCollection(
            final Collection<GeouserCompletedTour> tourCollection) {
        this.geouserCompletedTourCollection = tourCollection;
    }

    /**
     * Get created tour collection.
     * @return created tour collection.
     */
    @XmlTransient
    public final Collection<Tour> getCreatedTourCollection() {
        return createdTourCollection;
    }

    /**
     * Set created tour collection.
     * @param tourCollection created tour collection.
     */
    public final void setCreatedTourCollection(
            final Collection<Tour> tourCollection) {
        this.createdTourCollection = tourCollection;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        if (idUser != null) {
            hash += idUser.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    /**
     * Equals.
     * @param object to compare.
     * @return equals.
     */
    @Override
    public final boolean equals(final Object object) {
        // Warning: this method won't work in the case the id fields are not set
        if (!(object instanceof Geouser)) {
            return false;
        }
        Geouser other = (Geouser) object;
        if ((this.idUser == null && other.idUser != null)
                || (this.idUser != null
                && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    /**
     * To string.
     * @return the string.
     */
    @Override
    public final String toString() {
        return "database.Geouser[ idUser=" + idUser + " ]";
    }

}
