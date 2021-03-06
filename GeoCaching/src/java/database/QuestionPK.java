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
public class QuestionPK implements Serializable {
    /**
     * Position of the digit.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "digitPosition")
    private int digitPosition;
    /**
     * Waypoint id.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "waypoint_idwaypoint")
    private int waypointIdwaypoint;

    /**
     * Empty constructor.
     */
    public QuestionPK() {
    }

    /**
     * Constructor.
     * @param digitPosition1 position.
     * @param waypointIdwaypoint1 waypoint id.
     */
    public QuestionPK(final int digitPosition1, final int waypointIdwaypoint1) {
        this.digitPosition = digitPosition1;
        this.waypointIdwaypoint = waypointIdwaypoint1;
    }

    /**
     * Get position.
     * @return position.
     */
    public final int getDigitPosition() {
        return digitPosition;
    }

    /**
     * Set position.
     * @param digitPosition1 position.
     */
    public final void setDigitPosition(final int digitPosition1) {
        this.digitPosition = digitPosition1;
    }

    /**
     * Get waypoint id.
     * @return id.
     */
    public final int getWaypointIdwaypoint() {
        return waypointIdwaypoint;
    }

    /**
     * Set waypoint id.
     * @param waypointIdwaypoint1 id.
     */
    public final void setWaypointIdwaypoint(final int waypointIdwaypoint1) {
        this.waypointIdwaypoint = waypointIdwaypoint1;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        hash += (int) digitPosition;
        hash += (int) waypointIdwaypoint;
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
        if (!(object instanceof QuestionPK)) {
            return false;
        }
        QuestionPK other = (QuestionPK) object;
        if (this.digitPosition != other.digitPosition) {
            return false;
        }
        if (this.waypointIdwaypoint != other.waypointIdwaypoint) {
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
        return "database.QuestionPK[ digitPosition=" + digitPosition
                + ", waypointIdwaypoint=" + waypointIdwaypoint + " ]";
    }

}
