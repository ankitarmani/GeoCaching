/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Beatriz
 */
public class Question {

    /**
     * Position of the digit that this question corresponds to.
     */
    private int digitPosition;
    /**
     * The question of the digit, which can be null.
     */
    private String question;
    /**
     * The id of the waypoint that this question belongs to.
     */
    private int waypointIdwaypoint;

    /**
     * Constructor.
     * @param digitPosition1 digit position.
     * @param question1 question or null.
     * @param waypointIdwaypoint1 waypoint id.
     */
    public Question(final int digitPosition1,
            final String question1, final int waypointIdwaypoint1) {
        this.digitPosition = digitPosition1;
        this.question = question1;
        this.waypointIdwaypoint = waypointIdwaypoint1;
    }

    /**
     * Get the value of digitPosition.
     * @return the value of digitPosition
     */
    public final int getDigitPosition() {
        return digitPosition;
    }

    /**
     * Set the value of digitPosition.
     * @param digitPosition1 new value of digitPosition
     */
    public final void setDigitPosition(final int digitPosition1) {
        this.digitPosition = digitPosition1;
    }

    /**
     * Get the value of question.
     * @return the value of question
     */
    public final String getQuestion() {
        return question;
    }

    /**
     * Set the value of question.
     * @param question1 new value of question
     */
    public final void setQuestion(final String question1) {
        this.question = question1;
    }

    /**
     * Get the value of waypointIdwaypoint.
     * @return the value of waypointIdwaypoint
     */
    public final int getWaypointIdwaypoint() {
        return waypointIdwaypoint;
    }

    /**
     * Set the value of waypointIdwaypoint.
     * @param waypointIdwaypoint1 new value of waypointIdwaypoint
     */
    public final void setWaypointIdwaypoint(final int waypointIdwaypoint1) {
        this.waypointIdwaypoint = waypointIdwaypoint1;
    }

}
