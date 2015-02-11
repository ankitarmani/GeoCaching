package com.geoquest.android;

/**
 * 
 * @author Beatriz
 */
public class Question {

    /**
     * .
     */
    private int idwaypoint;
    /**
     * .
     */
    private int orderposition;
    /**
     * .
     */
    private int digitposition;
    /**
     * .
     */
    private String question;

    /**
     * Empty constructor.
     */
    public Question() {
    }

    /**
     * constructor.
     * @param idwaypoint1 .
     * @param orderposition1 .
     * @param digitposition1 .
     * @param question1 .
     */
    public Question(final int idwaypoint1, final int orderposition1,
            final int digitposition1, final String question1) {
        this.idwaypoint = idwaypoint1;
        this.orderposition = orderposition1;
        this.digitposition = digitposition1;
        this.question = question1;
    }

    /**
     * constructor.
     * @param digitposition1 .
     * @param question1 .
     */
    public Question(final int digitposition1, final String question1) {
        this.digitposition = digitposition1;
        this.question = question1;
    }

    /**
     * getting ID.
     * @return .
     */
    public final int getIdWaypoint() {
        return this.idwaypoint;
    }

    /**
     * setting id.
     * @param idwaypoint1 .
     */
    public final void setIdWaypoint(final int idwaypoint1) {
        this.idwaypoint = idwaypoint1;
    }

    /**
     * getting name.
     * @return .
     */
    public final int getOrderPosition() {
        return this.orderposition;
    }

    /**
     * setting name.
     * @param orderposition1 .
     */
    public final void setOrderPosition(final int orderposition1) {
        this.orderposition = orderposition1;
    }

    /**
     * getting phone number.
     * @return .
     */
    public final int getDigitPosition() {
        return this.digitposition;
    }

    /**
     * setting phone number.
     * @param digitposition1 .
     */
    public final void setDigitPosition(final int digitposition1) {
        this.digitposition = digitposition1;
    }

    /**
     * getting phone number.
     * @return .
     */
    public final String getQuestion() {
        return this.question;
    }

    /**
     * setting phone number.
     * @param question1 .
     */
    public final void setQuestion(final String question1) {
        this.question = question1;
    }
}
