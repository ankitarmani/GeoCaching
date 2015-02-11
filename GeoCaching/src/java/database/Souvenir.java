/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name = "souvenir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Souvenir.findAll", query = "SELECT s FROM Souvenir s"),
    @NamedQuery(name = "Souvenir.getLastSouvenirId",
        query = "SELECT max(s.idsouvenir) FROM Souvenir s"),
    @NamedQuery(name = "Souvenir.findByIdsouvenir",
        query = "SELECT s FROM Souvenir s WHERE s.idsouvenir = :idsouvenir"),
    @NamedQuery(name = "Souvenir.findBySouvenirURL",
        query = "SELECT s FROM Souvenir s WHERE s.souvenirURL = :souvenirURL"),
    @NamedQuery(name = "Souvenir.findByDownloads",
        query = "SELECT s FROM Souvenir s WHERE s.downloads = :downloads"),
    @NamedQuery(name = "Souvenir.findByTourId",
        query = "SELECT s FROM Souvenir s WHERE s.tourIdtour.idtour = "
        + ":tourId") })
public class Souvenir implements Serializable {
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
    @Column(name = "idsouvenir")
    private Integer idsouvenir;
    /**
     * Url.
     */
    @Size(max = MAX_URL)
    @Column(name = "souvenirURL")
    private String souvenirURL;
    /**
     * Number of downloads.
     */
    @Column(name = "downloads")
    private Integer downloads;
    /**
     * Tour.
     */
    @JoinColumn(name = "tour_idtour", referencedColumnName = "idtour")
    @ManyToOne(optional = false)
    private Tour tourIdtour;

    /**
     * Empty constructor.
     */
    public Souvenir() {
    }

    /**
     * Constructor.
     * @param idsouvenir1 souvenir id.
     */
    public Souvenir(final Integer idsouvenir1) {
        this.idsouvenir = idsouvenir1;
    }

    /**
     * Get souvenir id.
     * @return id.
     */
    public final Integer getIdsouvenir() {
        return idsouvenir;
    }

    /**
     * Set souvenir id.
     * @param idsouvenir1 id.
     */
    public final void setIdsouvenir(final Integer idsouvenir1) {
        this.idsouvenir = idsouvenir1;
    }

    /**
     * Get souvenir url.
     * @return url.
     */
    public final String getSouvenirURL() {
        return souvenirURL;
    }

    /**
     * Set souvenir url.
     * @param souvenirURL1 url.
     */
    public final void setSouvenirURL(final String souvenirURL1) {
        this.souvenirURL = souvenirURL1;
    }

    /**
     * Get number of downloads.
     * @return number of downloads.
     */
    public final Integer getDownloads() {
        return downloads;
    }

    /**
     * Set number of downloads.
     * @param downloads1 number.
     */
    public final void setDownloads(final Integer downloads1) {
        this.downloads = downloads1;
    }

    /**
     * Get tour id.
     * @return id.
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
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        if (idsouvenir != null) {
            hash += idsouvenir.hashCode();
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
        if (!(object instanceof Souvenir)) {
            return false;
        }
        Souvenir other = (Souvenir) object;
        if ((this.idsouvenir == null && other.idsouvenir != null)
                || (this.idsouvenir != null
                && !this.idsouvenir.equals(other.idsouvenir))) {
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
        return "database.Souvenir[ idsouvenir=" + idsouvenir + " ]";
    }

}
