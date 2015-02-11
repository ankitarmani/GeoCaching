/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores information about a tour.
 *
 * Stores information
 * about a tour and provides
 * methods to work with it.
 *
 * @author Beatriz
 */
public class Tour {
    /**
     * Unique identification number of the corresponding tour object.
     */
    private int nTourId;
    /**
     * The name of the tour.
     */
    private String sName;
    /**
     * The description of the tour.
     */
    private String sDescription;
    /**
     * Level of difficulty of the tour.
     * Possible values: low, high and medium.
     */
    private String sDifficulty;
    /**
     * Store url on the server for the badge picture of the tour.
     */
    private String sBadgeURL;
    /**
     * Distance in meters of the tour.
     * Counter as a sum of distances between adjacent points.
     */
    private Float sDistance;
    /**
     * List of way points of the corresponding tour.
     */
    private List<Waypoint> pWaypoints = new ArrayList<Waypoint>();
    /**
     * List of souvenirs  of the corresponding tour.
     */
    private List<Souvenir> pSouvenirs = new ArrayList<Souvenir>();
    /**
     * Contains a list of removed way points of the tour after the editing.
     * It's required for deleting them from the database.
     */
    private List<Integer> removedWaypoints = new ArrayList<Integer>();
    /**
     * Contains a list of removed souvenirs of the tour after the editing.
     * It's required for deleting them from the database.
     */
    private List<Integer> removedSouvenirs = new ArrayList<Integer>();
    /**
     * Contains the UserId of the user who created this tour.
     */
    private int nUserId;
    /**
     * Contains the users who have completed this tour.
     */
    private List<User> usersWhoCompletedThisTour;
    /**
     * Creates an empty Tour object.
     */
    public Tour() {
    }

    /**
     * Creates a tour object with specified initial values.
     * @param nTourId1 tour id.
     * @param sName1 name.
     * @param sDescription1 des.
     * @param sDifficulty1 diff.
     * @param sBadgeURL1 url.
     * @param sDistance1 distance.
     * @param nUserId1 id.
     */
    public Tour(final int nTourId1, final String sName1,
            final String sDescription1, final String sDifficulty1,
            final String sBadgeURL1, final Float sDistance1,
            final int nUserId1) {
        this.nTourId = nTourId1;
        this.sName = sName1;
        this.sDescription = sDescription1;
        this.sDifficulty = sDifficulty1;
        this.sBadgeURL = sBadgeURL1;
        this.sDistance = sDistance1;
        this.nUserId = nUserId1;
    }

    /**
     * Get the value of idtour.
     *
     * @return the value of idtour
     */
    public final int getTourId() {
        return nTourId;
    }

    /**
     * Set the value of idtour.
     *
     * @param idtour new value of idtour
     */
    public final void setTourId(final int idtour) {
        this.nTourId = idtour;
    }

    /**
     * Get the value of name.
     * @return the value of name
     */
    public final String getTourName() {
        return sName;
    }

    /**
     * Set the value of name.
     * @param name new value of name
     */
    public final void setTourName(final String name) {
        this.sName = name;
    }

    /**
     * Get the value of description.
     * @return the value of description
     */
    public final String getTourDescription() {
        return sDescription;
    }

    /**
     * Set the value of description.
     * @param description new value of description
     */
    public final void setTourDescription(final String description) {
        this.sDescription = description;
    }

    /**
     * Get the value of difficulty.
     * @return the value of difficulty
     */
    public final String getTourDifficulty() {
        return sDifficulty;
    }

    /**
     * Set the value of difficulty.
     * @param difficulty new value of difficulty
     */
    public final void setTourDifficulty(final String difficulty) {
        this.sDifficulty = difficulty;
    }

    /**
     * Get badge url.
     * @return url.
     */
    public final String getsBadgeURL() {
        return sBadgeURL;
    }

    /**
     * Set url.
     * @param sBadgeURL1 url.
     */
    public final void setsBadgeURL(final String sBadgeURL1) {
        this.sBadgeURL = sBadgeURL1;
    }

    /**
     * Get distance.
     * @return distance.
     */
    public final Float getsDistance() {
        return sDistance;
    }

    /**
     * Set distance.
     * @param sDistance1 distance.
     */
    public final void setsDistance(final Float sDistance1) {
        this.sDistance = sDistance1;
    }

    /**
     * Get user id.
     * @return id.
     */
    public final int getnUserId() {
        return nUserId;
    }

    /**
     * Set used id.
     * @param nUserId1 id.
     */
    public final void setnUserId(final int nUserId1) {
        this.nUserId = nUserId1;
    }

    /**
     * Get the value of waypoints.
     * @return the value of waypoints
     */
    public final List<Waypoint> getTourWaypoints() {
        restoreOrdering();
        return pWaypoints;
    }

    /**
     * Set the value of waypoints.
     * @param waypoints new value of waypoints
     */
    public final void setTourWaypoints(final List<Waypoint> waypoints) {
        this.pWaypoints = waypoints;
    }

    /**
     * Get tour souvenirs.
     * @return souvenirs.
     */
    public final List<Souvenir> getTourSouvenirs() {
        return pSouvenirs;
    }

    /**
     * Set tour souvenirs.
     * @param pSouvenirs1 souvenirs.
     */
    public final void setTourSouvenirs(final List<Souvenir> pSouvenirs1) {
        this.pSouvenirs = pSouvenirs1;
    }

    /**
     * Get who completed this tour.
     * @return collection.
     */
    public final List<User> getUsersWhoCompletedThisTour() {
        return usersWhoCompletedThisTour;
    }

    /**
     * Set who completed this tour.
     * @param users collection.
     */
    public final void setUsersWhoCompletedThisTour(final List<User> users) {
        this.usersWhoCompletedThisTour = users;
    }

    /**
     * Add waypoint to tour.
     * @param waypoint waypoint.
     */
    public final void addWaypoint(final Waypoint waypoint) {
        pWaypoints.add(waypoint);
    }

    /**
     * Removes a way point from the tour.
     *
     * Removes a way point from the list of the way points
     * and adds the corresponding id to the list of removed way points ids.
     *
     * @param index - position of the way point in the list of way points.
     */
    public final void removeWaypoint(final int index) {
        if (pWaypoints.get(index).getWaypointId() != -1) {
            removedWaypoints.add(pWaypoints.get(index).getWaypointId());
        }
        pWaypoints.remove(index);
    }

    /**
     * The list of ids of the waypoints that were removed.
     *
     * @return list of ids of removed waypoints.
     */
    public final List<Integer> getRemovedWaypoints() {
        return this.removedWaypoints;
    }

    /**
     * Get waypoint at position.
     * @param nPos position.
     * @return waypoint.
     */
    public final Waypoint getWaypointByPosition(final int nPos) {
        return pWaypoints.get(nPos);
    }

    /**
     * Get waypoint count.
     * @return count.
     */
    public final int getWaypointCount() {
        return pWaypoints.size();
    }

    /**
     * Orders list of the way points.
     *
     * Orders list of the way points according to their new order.
     *
     * @param order an array that contains position
     * of nth element in the new order.
     * For example. order[i] - position
     * of the ith element in the new arrangement.
     */
    public final void setOrder(final int[] order) {
        List<Waypoint> newWaypoints = new ArrayList<Waypoint>(this.pWaypoints);

        Collections.copy(newWaypoints, this.pWaypoints);

        for (int i = 0; i < this.getWaypointCount(); i++) {
            newWaypoints.set(order[i], this.pWaypoints.get(i));
        }

        this.setTourWaypoints(newWaypoints);
    }

    /**
     * Sets the correct positions to way points in the list.
     *
     * After some operations the order of the elements
     * in the way points list might be changed
     * however we store the index of each way point
     * in the corresponding object explicitly.
     */
    private void restoreOrdering() {
        for (int i = 0; i < this.getWaypointCount(); i++) {
            pWaypoints.get(i).setWaypointPosition(i);
        }
    }

    /**
     * Equals.
     * @param obj object to compare.
     * @return equals.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tour other = (Tour) obj;
        if (this.nTourId != other.nTourId) {
            return false;
        }
        if (equalNames(other)) {
            return false;
        }
        if (equalDescriptions(other)) {
            return false;
        }
        if (equalDifficulties(other)) {
            return false;
        }
        return true;
    }

    /**
     * Hashcode.
     * @return hashcode.
     */
    @Override
    public final int hashCode() {
        if (this.nTourId != -1) {
            return nTourId;
        } else {
            String contentString = this.sName
                    + this.sDifficulty
                    + this.sDescription
                    + this.sBadgeURL;

            for (int i = 0; i < this.getTourSouvenirs().size(); i++) {
                contentString += this.getTourSouvenirs().get(i);
            }

            return contentString.hashCode();
        }

    }

    /**
     * Adds an id of the removed souvenirs.
     *
     * So we could remove it from the database later.
     *
     * @param souvenir - souvenir that has to be deleted.
     */
    public final void addRemovedSouvernirs(final Souvenir souvenir) {
        this.removedSouvenirs.add(souvenir.getSouvenirid());
    }

    /**
     * Returns the list of removed souvenirs.
     *
     * @return - returns the list of souvenirs ids
     * that should to be deleted from the database.
     */
    public final List<Integer> getRemovedSouvernirs() {
        return this.removedSouvenirs;
    }

    /**
     * Auxiliary.
     * @param other tour.
     * @return equal names.
     */
    private boolean equalNames(final Tour other) {
        if (this.sName == null) {
            return (other.sName != null);
        } else {
             return !this.sName.equals(other.sName);
        }
    }

    /**
     * Auxiliary.
     * @param other tour.
     * @return equal descriptions.
     */
    private boolean equalDescriptions(final Tour other) {
        if (this.sDescription == null) {
            return (other.sDescription != null);
        } else {
            return !this.sDescription.equals(other.sDescription);
        }
    }

    /**
     * Auxiliary.
     * @param other tour.
     * @return equal difficulties.
     */
    private boolean equalDifficulties(final Tour other) {
        if (this.sDifficulty == null) {
            return (other.sDifficulty != null);
        } else {
            return !this.sDifficulty.equals(other.sDifficulty);
        }
    }
}
