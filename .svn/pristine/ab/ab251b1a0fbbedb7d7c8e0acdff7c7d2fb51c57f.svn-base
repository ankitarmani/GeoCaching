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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "waypoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Waypoint.findAll", query = "SELECT w FROM Waypoint w"),
    @NamedQuery(name = "Waypoint.getLastWaypointId",
        query = "SELECT max(w.idwaypoint) FROM Waypoint w"),
    @NamedQuery(name = "Waypoint.findByIdwaypoint",
        query = "SELECT w FROM Waypoint w WHERE w.idwaypoint = :idwaypoint"),
    @NamedQuery(name = "Waypoint.findByName",
        query = "SELECT w FROM Waypoint w WHERE w.name = :name"),
    @NamedQuery(name = "Waypoint.findByLongitude",
        query = "SELECT w FROM Waypoint w WHERE w.longitude = :longitude"),
    @NamedQuery(name = "Waypoint.findByLatitude",
        query = "SELECT w FROM Waypoint w WHERE w.latitude = :latitude"),
    @NamedQuery(name = "Waypoint.findByOrderPosition",
        query = "SELECT w FROM Waypoint w WHERE w.orderPosition = "
        + ":orderPosition"),
    @NamedQuery(name = "Waypoint.findByTourId",
        query = "SELECT w FROM Waypoint w WHERE w.tourIdtour.idtour = "
        + ":tourId") })
public class Waypoint implements Serializable {
    /**
     * Max characters of name.
     */
    private static final int MAX_NAME = 100;
    /**
     * Max characters of longitude.
     */
    private static final int MAX_LONG = 30;
    /**
     * Max characters of latitude.
     */
    private static final int MAX_LAT = 30;
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
    @Column(name = "idwaypoint")
    private Integer idwaypoint;
    /**
     * Name.
     */
    @Size(max = MAX_NAME)
    @Column(name = "name")
    private String name;
    /**
     * Longitude.
     */
    @Size(max = MAX_LONG)
    @Column(name = "longitude")
    private String longitude;
    /**
     * Latitude.
     */
    @Size(max = MAX_LAT)
    @Column(name = "latitude")
    private String latitude;
    /**
     * Position.
     */
    @Column(name = "orderPosition")
    private Integer orderPosition;
    /**
     * Tour id.
     */
    @JoinColumn(name = "tour_idtour", referencedColumnName = "idtour")
    @ManyToOne(optional = false)
    private Tour tourIdtour;
    /**
     * Question collection.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "waypoint")
    private Collection<Question> questionCollection;

    /**
     * Empty constructor.
     */
    public Waypoint() {
    }

    /**
     * Constructor.
     * @param idwaypoint1 waypoint id.
     */
    public Waypoint(final Integer idwaypoint1) {
        this.idwaypoint = idwaypoint1;
    }

    /**
     * Get waypoint id.
     * @return waypoint id.
     */
    public final Integer getIdwaypoint() {
        return idwaypoint;
    }

    /**
     * Set waypoint id.
     * @param idwaypoint1 id.
     */
    public final void setIdwaypoint(final Integer idwaypoint1) {
        this.idwaypoint = idwaypoint1;
    }

    /**
     * Get name.
     * @return name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Set name.
     * @param name1 name.
     */
    public final void setName(final String name1) {
        this.name = name1;
    }

    /**
     * Get longitude.
     * @return longitude.
     */
    public final String getLongitude() {
        return longitude;
    }

    /**
     * Set longitude.
     * @param longitude1 long.
     */
    public final void setLongitude(final String longitude1) {
        this.longitude = longitude1;
    }

    /**
     * Get latitude.
     * @return latitude.
     */
    public final String getLatitude() {
        return latitude;
    }

    /**
     * Set latitude.
     * @param latitude1 latitude.
     */
    public final void setLatitude(final String latitude1) {
        this.latitude = latitude1;
    }

    /**
     * Get position.
     * @return position.
     */
    public final Integer getOrderPosition() {
        return orderPosition;
    }

    /**
     * Set position.
     * @param orderPosition1 position.
     */
    public final void setOrderPosition(final Integer orderPosition1) {
        this.orderPosition = orderPosition1;
    }

    /**
     * Get tour id.
     * @return tour id.
     */
    public final Tour getTourIdtour() {
        return tourIdtour;
    }

    /**
     * Set tour id.
     * @param tourIdtour1 id.
     */
    public final void setTourIdtour(final Tour tourIdtour1) {
        this.tourIdtour = tourIdtour1;
    }

    /**
     * Get question collection.
     * @return question collection.
     */
    @XmlTransient
    public final Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    /**
     * Set question collection.
     * @param questionCollection1 collection.
     */
    public final void setQuestionCollection(
            final Collection<Question> questionCollection1) {
        this.questionCollection = questionCollection1;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        if (idwaypoint != null) {
            hash += idwaypoint.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    /**
     * Equals.
     * @param object .
     * @return equals.
     */
    @Override
    public final boolean equals(final Object object) {
        // Warning: this method won't work in the case the id fields are not set
        if (!(object instanceof Waypoint)) {
            return false;
        }
        Waypoint other = (Waypoint) object;
        if ((this.idwaypoint == null && other.idwaypoint != null)
                || (this.idwaypoint != null
                && !this.idwaypoint.equals(other.idwaypoint))) {
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
        return "database.Waypoint[ idwaypoint=" + idwaypoint + " ]";
    }

}
