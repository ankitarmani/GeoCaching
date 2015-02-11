package com.geoquest.android;

/**
 * .
 * @author Beatriz .
 */
public class Tour {

    /**
     * .
     */
    private int idtour;
    /**
     * .
     */
    private String name;
    /**
     * .
     */
    private String description;
    /**
     * .
     */
    private String difficulty;
    /**
     * .
     */
    private String badge;
    /**
     * .
     */
    private float distance;
    /**
     * .
     */
    private int userid;

    /**
     * Empty constructor.
     */
    public Tour() {
    }

    /**
     * constructor.
     * @param idtour1 .
     * @param name1 .
     * @param description1 .
     */
    public Tour(final int idtour1, final String name1,
            final String description1) {
        this.idtour = idtour1;
        this.name = name1;
        this.description = description1;
    }

    /**
     * constructor.
     * @param idtour1 .
     * @param name1 .
     * @param description1 .
     * @param difficulty1 .
     * @param distance1 .
     */
    public Tour(final int idtour1, final String name1,
            final String description1, final String difficulty1,
            final float distance1) {
        this.idtour = idtour1;
        this.name = name1;
        this.description = description1;
        this.difficulty = difficulty1;
        this.distance = distance1;
        //this.userid = userid;
    }

    /**
     * constructor.
     * @param idtour1 .
     * @param name1 .
     * @param description1 .
     * @param difficulty1 .
     * @param badge1 .
     * @param distance1 .
     */
    public Tour(final int idtour1, final String name1,
            final String description1, final String difficulty1,
            final String badge1, final float distance1) {
        this.idtour = idtour1;
        this.name = name1;
        this.description = description1;
        this.difficulty = difficulty1;
        this.badge = badge1;
        this.distance = distance1;
        //this.userid = userid;
    }
    /**
     * constructor.
     * @param name1 .
     * @param description1 .
     * @param difficulty1 .
     * @param distance1 .
     * @param userid1 .
     */
    public Tour(final String name1, final String description1,
            final String difficulty1, final int distance1, final int userid1) {
        this.name = name1;
        this.description = description1;
        this.difficulty = difficulty1;
        this.distance = distance1;
        this.userid = userid1;
    }

    /**
     * getting ID.
     * @return .
     */
    public final int getIdTour() {
        return this.idtour;
    }

    /**
     * setting id.
     * @param idtour1 .
     */
    public final void setIdTour(final int idtour1) {
        this.idtour = idtour1;
    }

    /**
     * getting name.
     * @return .
     */
    public final String getTourName() {
        return this.name;
    }

    /**
     * setting name.
     * @param name1 .
     */
    public final void setTourName(final String name1) {
        this.name = name1;
    }

    /**
     * getting phone number.
     * @return .
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * setting phone number.
     * @param description1 .
     */
    public final void setDescription(final String description1) {
        this.description = description1;
    }

    /**
     * getting phone number.
     * @return .
     */
    public final String getDifficulty() {
        return this.difficulty;
    }

    /**
     * setting phone number.
     * @param difficulty1 .
     */
    public final void setDifficulty(final String difficulty1) {
        this.difficulty = difficulty1;
    }

    /**
     * getting phone number.
     * @return .
     */
    public final String getBadge() {
        return this.badge;
    }

    /**
     * setting phone number.
     * @param badge1 .
     */
    public final void setBadge(final String badge1) {
        this.difficulty = badge1;
    }
    /**
     * .
     * @return .
     */
    public final float getDistance() {
        return this.distance;
    }

    /**
     * setting phone number.
     * @param distance1 .
     */
    public final void setDistance(final float distance1) {
        this.distance = distance1;
    }

    /**
     * .
     * @return .
     */
    public final int getUserId() {
        return this.userid;
    }

    /**
     * setting id.
     * @param userid1 .
     */
    public final void setUserId(final int userid1) {
        this.userid = userid1;
    }
}
