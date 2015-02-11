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
@Table(name = "tour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
    @NamedQuery(name = "Tour.getLastTourId",
        query = "SELECT max(t.idtour) FROM Tour t"),
    @NamedQuery(name = "Tour.findByIdtour",
        query = "SELECT t FROM Tour t WHERE t.idtour = :idtour"),
    @NamedQuery(name = "Tour.findByName",
        query = "SELECT t FROM Tour t WHERE t.name = :name"),
    @NamedQuery(name = "Tour.findByDescription",
        query = "SELECT t FROM Tour t WHERE t.description = :description"),
    @NamedQuery(name = "Tour.findByDifficulty",
        query = "SELECT t FROM Tour t WHERE t.difficulty = :difficulty"),
    @NamedQuery(name = "Tour.findByBadgeURL",
        query = "SELECT t FROM Tour t WHERE t.badgeURL = :badgeURL"),
    @NamedQuery(name = "Tour.findByDistance",
        query = "SELECT t FROM Tour t WHERE t.distance = :distance") })
public class Tour implements Serializable {
    /**
     * Max characters of name.
     */
    private static final int MAX_NAME = 100;
    /**
     * Max characters of description.
     */
    private static final int MAX_DESC = 500;
    /**
     * Max characters of difference.
     */
    private static final int MAX_DIFF = 10;
    /**
     * Max characters of url.
     */
    private static final int MAX_URL = 200;
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
    @Column(name = "idtour")
    private Integer idtour;
    /**
     * Name.
     */
    @Size(max = MAX_NAME)
    @Column(name = "name")
    private String name;
    /**
     * Description.
     */
    @Size(max = MAX_DESC)
    @Column(name = "description")
    private String description;
    /**
     * Difficulty.
     */
    @Size(max = MAX_DIFF)
    @Column(name = "difficulty")
    private String difficulty;
    /**
     * Badge URL.
     */
    @Size(max = MAX_URL)
    @Column(name = "badgeURL")
    private String badgeURL;
    /**
     * Distance.
     */
    @Column(name = "distance")
    private Float distance;
    /**
     * Collection of completed tours.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private Collection<GeouserCompletedTour> geouserCompletedTourCollection;
    /**
     * Collection of souvenirs.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourIdtour")
    private Collection<Souvenir> souvenirCollection;
    /**
     * Collection of waypoints.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourIdtour")
    private Collection<Waypoint> waypointCollection;
    /**
     * User id.
     */
    @JoinColumn(name = "User_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private Geouser useridUser;

    /**
     * Empty constructor.
     */
    public Tour() {
    }

    /**
     * Constructor.
     * @param idtour1 id.
     */
    public Tour(final Integer idtour1) {
        this.idtour = idtour1;
    }

    /**
     * Get tour id.
     * @return id.
     */
    public final Integer getIdtour() {
        return idtour;
    }

    /**
     * Set tour id.
     * @param idtour1 id.
     */
    public final void setIdtour(final Integer idtour1) {
        this.idtour = idtour1;
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
     * Get description.
     * @return description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Sets description.
     * @param description1 description.
     */
    public final void setDescription(final String description1) {
        this.description = description1;
    }

    /**
     * Get difficulty.
     * @return difficulty.
     */
    public final String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     * @param difficulty1 difficulty.
     */
    public final void setDifficulty(final String difficulty1) {
        this.difficulty = difficulty1;
    }

    /**
     * Get badge url.
     * @return url.
     */
    public final String getBadgeURL() {
        return badgeURL;
    }

    /**
     * Location of the badge.
     * @param badgeURL1 location.
     */
    public final void setBadgeURL(final String badgeURL1) {
        this.badgeURL = badgeURL1;
    }

    /**
     * Get distance.
     * @return distance.
     */
    public final Float getDistance() {
        return distance;
    }

    /**
     * Set distance.
     * @param distance1 distance.
     */
    public final void setDistance(final Float distance1) {
        this.distance = distance1;
    }

    /**
     * Get user collection.
     * @return user collection.
     */
    @XmlTransient
    public final Collection<GeouserCompletedTour> getGeouserCompletedTourCollection() {
        return geouserCompletedTourCollection;
    }

    /**
     * Set tour collection.
     * @param geouserCollection1 collection.
     */
    public final void setGeouserCompletedTourCollection(
            final Collection<GeouserCompletedTour> geouserCollection1) {
        this.geouserCompletedTourCollection = geouserCollection1;
    }

    /**
     * Get tour collection.
     * @return collection.
     */
    @XmlTransient
    public final Collection<Souvenir> getSouvenirCollection() {
        return souvenirCollection;
    }

    /**
     * Set souvenir collection.
     * @param souvenirCollection1 collection.
     */
    public final void setSouvenirCollection(
            final Collection<Souvenir> souvenirCollection1) {
        this.souvenirCollection = souvenirCollection1;
    }

    /**
     * Get waypoint collection.
     * @return waypoint collection.
     */
    @XmlTransient
    public final Collection<Waypoint> getWaypointCollection() {
        return waypointCollection;
    }

    /**
     * Set waypoint collection.
     * @param waypointCollection1 collection.
     */
    public final void setWaypointCollection(
            final Collection<Waypoint> waypointCollection1) {
        this.waypointCollection = waypointCollection1;
    }

    /**
     * Get user id.
     * @return id.
     */
    public final Geouser getUseridUser() {
        return useridUser;
    }

    /**
     * Set user id.
     * @param useridUser1 id.
     */
    public final void setUseridUser(final Geouser useridUser1) {
        this.useridUser = useridUser1;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        if (idtour != null) {
            hash += idtour.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    /**
     * Equals.
     * @param object .
     * @return equals .
     */
    @Override
    public final boolean equals(final Object object) {
        // Warning: this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.idtour == null && other.idtour != null)
                || (this.idtour != null
                && !this.idtour.equals(other.idtour))) {
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
        return "database.Tour[ idtour=" + idtour + " ]";
    }

}
