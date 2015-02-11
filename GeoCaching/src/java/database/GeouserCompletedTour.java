/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * .
 * @author Beatriz .
 */
@Entity
@Table(name = "geouser_completed_tour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeouserCompletedTour.findAll",
        query = "SELECT g FROM GeouserCompletedTour g"),
    @NamedQuery(name = "GeouserCompletedTour.findByGeoUseridUser",
        query = "SELECT g FROM GeouserCompletedTour g "
        + "WHERE g.geouserCompletedTourPK.geoUseridUser = :geoUseridUser"),
    @NamedQuery(name = "GeouserCompletedTour.findByTourIdtour",
        query = "SELECT g FROM GeouserCompletedTour g "
        + "WHERE g.geouserCompletedTourPK.tourIdtour = :tourIdtour"),
    @NamedQuery(name = "GeouserCompletedTour.findByLastCompletionDate",
        query = "SELECT g FROM GeouserCompletedTour g "
        + "WHERE g.lastCompletionDate = :lastCompletionDate") })
public class GeouserCompletedTour implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Primary Key.
     */
    @EmbeddedId
    private GeouserCompletedTourPK geouserCompletedTourPK;
    /**
     * Last completion data of this tour by this user.
     */
    @Column(name = "lastCompletionDate")
    @Temporal(TemporalType.DATE)
    private Date lastCompletionDate;
    /**
     * Associated tour that was completed.
     */
    @JoinColumn(name = "tour_idtour", referencedColumnName = "idtour",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tour tour;
    /**
     * Associated user that completed a tour.
     */
    @JoinColumn(name = "GeoUser_idUser", referencedColumnName = "idUser",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Geouser geouser;

    /**
     * Empty constructor.
     */
    public GeouserCompletedTour() {
    }

    /**
     * Constructor.
     * @param geouserCompletedTourPK1 primary key.
     */
    public GeouserCompletedTour(
            final GeouserCompletedTourPK geouserCompletedTourPK1) {
        this.geouserCompletedTourPK = geouserCompletedTourPK1;
    }

    /**
     * Constructor.
     * @param geoUseridUser used id.
     * @param tourIdtour tour id.
     */
    public GeouserCompletedTour(final int geoUseridUser, final int tourIdtour) {
        this.geouserCompletedTourPK =
                new GeouserCompletedTourPK(geoUseridUser, tourIdtour);
    }

    /**
     * Get primary key.
     * @return key.
     */
    public final GeouserCompletedTourPK getGeouserCompletedTourPK() {
        return geouserCompletedTourPK;
    }

    /**
     * Set primary key.
     * @param geouserCompletedTourPK1 key.
     */
    public final void setGeouserCompletedTourPK(
            final GeouserCompletedTourPK geouserCompletedTourPK1) {
        this.geouserCompletedTourPK = geouserCompletedTourPK1;
    }

    /**
     * Get last completion date.
     * @return date.
     */
    public final Date getLastCompletionDate() {
        return lastCompletionDate;
    }

    /**
     * Set last completion date.
     * @param lastCompletionDate1 date.
     */
    public final void setLastCompletionDate(final Date lastCompletionDate1) {
        this.lastCompletionDate = lastCompletionDate1;
    }

    /**
     * Get tour.
     * @return tour.
     */
    public final Tour getTour() {
        return tour;
    }

    /**
     * Set tour.
     * @param tour1 tour.
     */
    public final void setTour(final Tour tour1) {
        this.tour = tour1;
    }

    /**
     * Get user.
     * @return user.
     */
    public final Geouser getGeouser() {
        return geouser;
    }

    /**
     * Set user.
     * @param geouser1 user.
     */
    public final void setGeouser(final Geouser geouser1) {
        this.geouser = geouser1;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        if (geouserCompletedTourPK != null) {
            hash += geouserCompletedTourPK.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    /**
     * Equals.
     * @param object this.
     * @return equals.
     */
    @Override
    public final boolean equals(final Object object) {
        // Warning- this method won't work in the case the id fields are not set
        if (!(object instanceof GeouserCompletedTour)) {
            return false;
        }
        GeouserCompletedTour other = (GeouserCompletedTour) object;
        if ((this.geouserCompletedTourPK == null
                && other.geouserCompletedTourPK != null)
                || (this.geouserCompletedTourPK != null
                && !this.geouserCompletedTourPK.
                equals(other.geouserCompletedTourPK))) {
            return false;
        }
        return true;
    }

    /**
     * To string.
     * @return string.
     */
    @Override
    public final String toString() {
        return "database.GeouserCompletedTour[ geouserCompletedTourPK="
                + geouserCompletedTourPK + " ]";
    }
}
