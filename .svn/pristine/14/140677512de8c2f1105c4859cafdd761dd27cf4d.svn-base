/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beatriz
 */
public class Waypoint {

    /**
     * Waypoint id.
     */
    private int waypointid;
    /**
     * Tour id.
     */
    private int tourid;
    /**
     * Name.
     */
    private String name;
    /**
     * Latitude.
     */
    private String latitude;
    /**
     * Longitude.
     */
    private String longitude;
    /**
     * Position.
     */
    private int position;
    /**
     * Questions.
     */
    private List<Question> questions = new ArrayList<Question>();

    /**
     * Empty constructor.
     */
    public Waypoint() {
    }

    /**
     * Constructor.
     * @param waypointid1 waypoint id.
     * @param tourid1 tour id.
     * @param name1 name.
     * @param latitude1 lat.
     * @param longitude1 long.
     * @param position1 pos.
     */
    public Waypoint(final int waypointid1, final int tourid1,
            final String name1, final String latitude1, final String longitude1,
            final int position1) {
        this.waypointid = waypointid1;
        this.tourid = tourid1;
        this.name = name1;
        this.latitude = latitude1;
        this.longitude = longitude1;
        this.position = position1;
    }

    /**
     * Get the value of waypointid.
     * @return the value of waypointid
     */
    public final int getWaypointId() {
        return waypointid;
    }

    /**
     * Set the value of waypointid.
     * @param waypointid1 new value of waypointid
     */
    public final void setWaypointId(final int waypointid1) {
        this.waypointid = waypointid1;
    }

    /**
     * Get the value of tourid.
     * @return the value of tourid
     */
    public final int getTourId() {
        return tourid;
    }

    /**
     * Set the value of tourid.
     * @param tourid1 new value of tourid
     */
    public final void setTourId(final int tourid1) {
        this.tourid = tourid1;
    }

    /**
     * Get the value of name.
     * @return the value of name
     */
    public final String getWaypointName() {
        return name;
    }

    /**
     * Set the value of name.
     * @param name1 new value of name
     */
    public final void setWaypointName(final String name1) {
        this.name = name1;
    }

    /**
     * Get the value of latitude.
     * @return the value of latitude
     */
    public final String getWaypointLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude.
     * @param latitude1 new value of latitude
     */
    public final void setWaypointLatitude(final String latitude1) {
        this.latitude = latitude1;
    }

    /**
     * Get the value of longitude.
     * @return the value of longitude
     */
    public final String getWaypointLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude.
     * @param longitude1 new value of longitude
     */
    public final void setWaypointLongitude(final String longitude1) {
        this.longitude = longitude1;
    }

     /**
     * Get the value of position.
     * @return the value of position
     */
    public final int getWaypointPosition() {
        return position;
    }

    /**
     * Set the value of position.
     * @param position1 new value of position
     */
    public final void setWaypointPosition(final int position1) {
        this.position = position1;
    }

    /**
     * Get questions.
     * @return the questions.
     */
    public final List<Question> getQuestions() {
        return questions;
    }

    /**
     * Set questions.
     * @param question1 the questions.
     */
    public final void setQuestions(final List<Question> question1) {
        this.questions = question1;
    }

    @Override
    public final String toString() {
        if (name.equals("")) {
            return latitude + ", " + longitude;
        } else {
            return name + ", " + latitude + ", " + longitude;
        }
    }

}
