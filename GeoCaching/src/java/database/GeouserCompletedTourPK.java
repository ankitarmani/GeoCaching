/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Beatriz
 */
@Embeddable
public class GeouserCompletedTourPK implements Serializable {
    /**
     * User id.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "GeoUser_idUser")
    private int geoUseridUser;
    /**
     * Tour id.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "tour_idtour")
    private int tourIdtour;

    /**
     * Empty constructor.
     */
    public GeouserCompletedTourPK() {
    }

    /**
     * Constructor.
     * @param geoUseridUser1 used id.
     * @param tourIdtour1 tour id.
     */
    public GeouserCompletedTourPK(
            final int geoUseridUser1, final int tourIdtour1) {
        this.geoUseridUser = geoUseridUser1;
        this.tourIdtour = tourIdtour1;
    }

    /**
     * Get user id.
     * @return user id.
     */
    public final int getGeoUseridUser() {
        return geoUseridUser;
    }

    /**
     * Set user id.
     * @param geoUseridUser1 user id.
     */
    public final void setGeoUseridUser(final int geoUseridUser1) {
        this.geoUseridUser = geoUseridUser1;
    }

    /**
     * Get tour id.
     * @return tour id.
     */
    public final int getTourIdtour() {
        return tourIdtour;
    }

    /**
     * Set tour id.
     * @param tourIdtour1 tour id.
     */
    public final void setTourIdtour(final int tourIdtour1) {
        this.tourIdtour = tourIdtour1;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        hash += (int) geoUseridUser;
        hash += (int) tourIdtour;
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
        if (!(object instanceof GeouserCompletedTourPK)) {
            return false;
        }
        GeouserCompletedTourPK other = (GeouserCompletedTourPK) object;
        if (this.geoUseridUser != other.geoUseridUser) {
            return false;
        }
        if (this.tourIdtour != other.tourIdtour) {
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
        return "database.GeouserCompletedTourPK[ geoUseridUser="
                + geoUseridUser + ", tourIdtour=" + tourIdtour + " ]";
    }
}
