/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import database.Geouser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * This class is the interface to manage the Database.
 *
 * @author Beatriz
 */
public class ManageToursDatabaseMethods {

    /**
     * This is needed for the Java Persistence.
     */
    private EntityManager em;

    /**
     * Constructor.
     * @param em1 em.
     */
    public ManageToursDatabaseMethods(final EntityManager em1) {
        this.em = em1;
    }

    /////////////////
    // GET Methods //
    /////////////////
    /**
     * Gets all tours.
     * @return all tours.
     */
    public final ArrayList<models.Tour> getAllTours() {
        List<database.Tour> dbTours = (List<database.Tour>) em.
                createNamedQuery("Tour.findAll").getResultList();
        List<models.Tour> mTours = new ArrayList<models.Tour>();

        for (database.Tour dbTour : dbTours) {
            mTours.add(passDBTourToModelTour(dbTour));
        }

        return (ArrayList<models.Tour>) mTours;
    }

    /**
     * Get Tour by Id.
     * @param nTourId toud ID.
     * @return tour.
     */
    public final models.Tour getTourById(final int nTourId) {
        database.Tour dbTour = (database.Tour) em.
                createNamedQuery("Tour.findByIdtour").
                setParameter("idtour", nTourId).getResultList().get(0);
        return passDBTourToModelTour(dbTour);
    }

    /**
     * Get waypoints by tour ID.
     * @param nTourId toud ID.
     * @return waypoints.
     */
    public final ArrayList<models.Waypoint> getAllWaypointsByTourId(
            final int nTourId) {
        List<database.Waypoint> dbWaypoints =
                (List<database.Waypoint>) em.
                createNamedQuery("Waypoint.findByTourId").
                setParameter("tourId", nTourId).getResultList();
        ArrayList<models.Waypoint> mWaypoints =
                new ArrayList<models.Waypoint>();

        for (database.Waypoint dbWaypoint : dbWaypoints) {
            models.Waypoint mWaypoint =
                    passDBWaypointToModelWaypoint(dbWaypoint);
            mWaypoints.add(mWaypoint);
        }

        return mWaypoints;
    }

    /**
     * Get all questions by waypoint id.
     * @param nWaypointId waypoint id.
     * @return coordinate digits.
     */
    public final ArrayList<models.Question> getAllQuestionsByWaypointId(
            final int nWaypointId) {
        List<database.Question> dbQuestions =
                (List<database.Question>) em.
                createNamedQuery("Question.findByWaypointIdwaypoint").
                setParameter("waypointIdwaypoint", nWaypointId).getResultList();
        ArrayList<models.Question> mQuestions =
                new ArrayList<models.Question>();

        for (database.Question dbQuestion : dbQuestions) {
            models.Question mQuestion =
                    passDBQuestionToModelQuestion(dbQuestion);
            mQuestions.add(mQuestion);
        }
        return mQuestions;
    }

    /**
     * Get all souvenirs by tour id.
     * @param nTourId tour id.
     * @return souvenirs.
     */
    public final ArrayList<models.Souvenir> getAllSouvenirsByTourId(
            final int nTourId) {
        List<database.Souvenir> dbSouvenirs =
                (List<database.Souvenir>) em.
                createNamedQuery("Souvenir.findByTourId").
                setParameter("tourId", nTourId).getResultList();
        ArrayList<models.Souvenir> mSouvenirs =
                new ArrayList<models.Souvenir>();

        for (database.Souvenir dbSouvenir : dbSouvenirs) {
            models.Souvenir mSouvenir =
                    passDBSouvenirToModelSouvenir(dbSouvenir);
            mSouvenirs.add(mSouvenir);
        }
        return mSouvenirs;
    }

    ////////////////////
    // INSERT Methods //
    ////////////////////
    /**
     * Insert tour.
     * @param mTour tour.
     * @return tour ID.
     */
    public final int insertTour(final models.Tour mTour) {
        int nLastTourId;
        try {
            nLastTourId = Integer.parseInt((em.createNamedQuery(
                    "Tour.getLastTourId").getResultList().get(0)).toString());
        } catch (Exception e) {
            nLastTourId = 0;
        }

        database.Tour dbTour = new database.Tour(nLastTourId + 1);
        dbTour = takeValuesFromModelTour(dbTour, mTour);

        Geouser dbUser = (Geouser) em.find(Geouser.class, mTour.getnUserId());
        dbTour.setUseridUser(dbUser);

        em.persist(dbTour);

        int nTourId = Integer.parseInt((em.createNamedQuery(
                "Tour.getLastTourId").getResultList().get(0)).toString());

        addOrEditWaypointsToTourId(nTourId, mTour.getTourWaypoints());
        addOrEditSouvenirsToTourId(nTourId, mTour.getTourSouvenirs());

        return nTourId;
    }

    /**
     * Insert waypoint to tour.
     * @param nTourId tour ID.
     * @param mWaypoint model waypoint.
     */
    private void insertWaypointToTour(
            final int nTourId, final models.Waypoint mWaypoint) {
        int nLastWaypointId;
        try {
            nLastWaypointId = Integer.parseInt((em.
                    createNamedQuery("Waypoint.getLastWaypointId").
                    getResultList().get(0)).toString());
        } catch (Exception e) {
            nLastWaypointId = 0;
        }

        database.Waypoint dbWaypoint =
                new database.Waypoint(nLastWaypointId + 1);
        dbWaypoint = takeValuesFromModelWaypoint(dbWaypoint, mWaypoint);

        database.Tour dbTour = (database.Tour)
                em.find(database.Tour.class, nTourId);
        dbWaypoint.setTourIdtour(dbTour);

        em.persist(dbWaypoint);

        int nWaypointId = Integer.parseInt((em.createNamedQuery(
                "Waypoint.getLastWaypointId").getResultList().get(0)).
                toString());

        addOrEditQuestionsToWaypointId(nWaypointId, mWaypoint.getQuestions());
    }

    /**
     * Insert Digit to Waypoint.
     * @param nWaypointId waypoint id.
     * @param mQuestion model mQuestion.
     */
    private void insertQuestionToWaypoint(final int nWaypointId,
            final models.Question mQuestion) {

        database.Question dbQuestion =
                new database.Question(
                mQuestion.getDigitPosition(), nWaypointId);
        dbQuestion = takeValuesFromModelQuestion(dbQuestion, mQuestion);

        database.Waypoint dbWaypoint = (database.Waypoint)
                em.find(database.Waypoint.class, nWaypointId);
        dbQuestion.setWaypoint(dbWaypoint);

        em.persist(dbQuestion);
    }

    /**
     * Insert souvenir to tour.
     * @param nTourId tour id.
     * @param mSouvenir souvenir.
     */
    private void insertSouvenirToTour(
            final int nTourId, final models.Souvenir mSouvenir) {
        int nLastSouvenirId;
        try {
            nLastSouvenirId = Integer.parseInt((em.
                    createNamedQuery("Souvenir.getLastSouvenirId").
                    getResultList().get(0)).toString());
        } catch (Exception e) {
            nLastSouvenirId = 0;
        }

        database.Souvenir dbSouvenir =
                new database.Souvenir(nLastSouvenirId + 1);
        dbSouvenir = takeValuesFromModelSouvernir(dbSouvenir, mSouvenir);

        database.Tour dbTour = (database.Tour)
                em.find(database.Tour.class, nTourId);
        dbSouvenir.setTourIdtour(dbTour);

        em.persist(dbSouvenir);
    }

    //////////////////
    // EDIT Methods //
    //////////////////
    /**
     * Edit tour.
     * @param nTourId tour id.
     * @param mTour model Tour.
     */
    public final void editTour(final int nTourId, final models.Tour mTour) {

        database.Tour dbTour =
                em.find(database.Tour.class, nTourId);

        dbTour = takeValuesFromModelTour(dbTour, mTour);

        addOrEditWaypointsToTourId(nTourId, mTour.getTourWaypoints());
        deleteWaypointsByIdList(mTour.getRemovedWaypoints());
        addOrEditSouvenirsToTourId(nTourId, mTour.getTourSouvenirs());
        deleteSouvenirsByIdList(mTour.getRemovedSouvernirs());
    }

    /**
     * Add or edit waypoints to tour.
     * @param nTourId id.
     * @param mWaypoints waypoints.
     */
    private void addOrEditWaypointsToTourId(
            final int nTourId, final List<models.Waypoint> mWaypoints) {

        for (models.Waypoint mWaypoint : mWaypoints) {
            int nWaypointId = mWaypoint.getWaypointId();

            database.Waypoint dbWaypoint =
                    em.find(database.Waypoint.class, nWaypointId);

            if (dbWaypoint != null) {
                dbWaypoint = takeValuesFromModelWaypoint(dbWaypoint, mWaypoint);
                addOrEditQuestionsToWaypointId(
                        nWaypointId, mWaypoint.getQuestions());
            } else {
                insertWaypointToTour(nTourId, mWaypoint);
            }
        }
    }

    /**
     * Add or edit questions to waypoint.
     * @param nWaypointId id.
     * @param mQuestions questions.
     */
    private void addOrEditQuestionsToWaypointId(
            final int nWaypointId, final List<models.Question> mQuestions) {

        for (models.Question mQuestion : mQuestions) {
            database.Question dbQuestion =
                    em.find(database.Question.class, new database.
                    QuestionPK(mQuestion.getDigitPosition(), nWaypointId));

            if (dbQuestion != null) {
                dbQuestion = takeValuesFromModelQuestion(dbQuestion, mQuestion);
            } else {
                insertQuestionToWaypoint(nWaypointId, mQuestion);
            }
        }
    }

    /**
     * Add or edit souvenirs to tour.
     * @param nTourId id.
     * @param mTourSouvenirs souvenirs.
     */
    private void addOrEditSouvenirsToTourId(
            final int nTourId, final List<models.Souvenir> mTourSouvenirs) {

        for (models.Souvenir mSouv : mTourSouvenirs) {
            database.Souvenir dbSouvenir =
                    em.find(database.Souvenir.class,
                    mSouv.getSouvenirid());

            if (dbSouvenir != null) {
                dbSouvenir = takeValuesFromModelSouvernir(dbSouvenir, mSouv);
            } else {
                insertSouvenirToTour(nTourId, mSouv);
            }
        }
    }

    ////////////////////
    // DELETE Methods //
    ////////////////////
    /**
     * Delete tour automatically deletes waypoints, coordinate digits, and
     * souvenirs related to that tour.
     * @param nTourId tour Id.
     */
    public final void deleteTourById(final int nTourId) {
        database.Tour dbTour =
                em.find(database.Tour.class, nTourId);

        if (dbTour != null) {
            em.remove(dbTour);
        }
    }

    /**
     * Delete waypoints.
     * @param deletedWaypointIDs ids.
     */
    private void deleteWaypointsByIdList(
            final List<Integer> deletedWaypointIDs) {
        for (Integer deletedWaypointID : deletedWaypointIDs) {
            database.Waypoint dbWaypointToDelete =
                    em.find(database.Waypoint.class, deletedWaypointID);

            if (dbWaypointToDelete != null) {
                em.remove(dbWaypointToDelete);
            }
        }
    }

    /**
     * Delete souvenirs.
     * @param removedSouvernirsIDs ids.
     */
    private void deleteSouvenirsByIdList(
            final List<Integer> removedSouvernirsIDs) {
        for (Integer deletedSouvenirsID : removedSouvernirsIDs) {
            database.Souvenir dbSouvenirToDelete =
                    em.find(database.Souvenir.class, deletedSouvenirsID);

            if (deletedSouvenirsID != null) {
                em.remove(dbSouvenirToDelete);
            }
        }
    }

    ///////////////////////
    // Interface Methods //
    ///////////////////////
    /**
     * Pass DB tour to Model tour.
     * @param dbTour DB tour.
     * @return model tour.
     */
    private models.Tour passDBTourToModelTour(
            final database.Tour dbTour) {
        models.Tour mTour = new models.Tour(
                dbTour.getIdtour(),
                dbTour.getName(),
                dbTour.getDescription(),
                dbTour.getDifficulty(),
                dbTour.getBadgeURL(),
                dbTour.getDistance(),
                dbTour.getUseridUser().getIdUser());

        for (database.Waypoint dbWayp : dbTour.getWaypointCollection()) {
            models.Waypoint mWaypoint = passDBWaypointToModelWaypoint(dbWayp);
            mTour.addWaypoint(mWaypoint);
        }

        return mTour;
    }

    /**
     * Take values from Model to DB: Tour.
     * @param dbTour DB tour.
     * @param mTour Model Tour.
     * @return DB tour.
     */
    private database.Tour takeValuesFromModelTour(
            final database.Tour dbTour, final models.Tour mTour) {
        dbTour.setName(mTour.getTourName());
        dbTour.setDescription(mTour.getTourDescription());
        dbTour.setDifficulty(mTour.getTourDifficulty());
        dbTour.setBadgeURL(mTour.getsBadgeURL());
        dbTour.setDistance(mTour.getsDistance());
        return dbTour;
    }

    /**
     * Pass DB waypoint to model waypoint.
     * @param dbWaypoint db waypoint.
     * @return mode waypoint.
     */
    private models.Waypoint passDBWaypointToModelWaypoint(
            final database.Waypoint dbWaypoint) {
        models.Waypoint mWaypoint = new models.Waypoint(
                dbWaypoint.getIdwaypoint(),
                dbWaypoint.getTourIdtour().getIdtour(),
                dbWaypoint.getName(),
                dbWaypoint.getLatitude(),
                dbWaypoint.getLongitude(),
                dbWaypoint.getOrderPosition());
        return mWaypoint;
    }

     /**
     * Take from model to DB: waypoint.
     * @param dbWayp DB waypoint.
     * @param mWaypoint model waypoint.
     * @return DB waypoint.
     */
    private database.Waypoint takeValuesFromModelWaypoint(
            final database.Waypoint dbWayp,
            final models.Waypoint mWaypoint) {
        dbWayp.setName(mWaypoint.getWaypointName());
        dbWayp.setLatitude(mWaypoint.getWaypointLatitude());
        dbWayp.setLongitude(mWaypoint.getWaypointLongitude());
        dbWayp.setOrderPosition(mWaypoint.getWaypointPosition());
        return dbWayp;
    }

    /**
     * Pass DB to Model Question.
     * @param dbQuestion DB Question.
     * @return model Question.
     */
    private models.Question passDBQuestionToModelQuestion(
            final database.Question dbQuestion) {
        models.Question mCoordDigit = new models.Question(
                dbQuestion.getQuestionPK().getDigitPosition(),
                dbQuestion.getQuestion(),
                dbQuestion.getQuestionPK().getWaypointIdwaypoint());
        return mCoordDigit;
    }

    /**
     * Take from model to DB: question.
     * @param dbQuestion db question.
     * @param mQuestion model question.
     * @return db question.
     */
    private database.Question takeValuesFromModelQuestion(
            final database.Question dbQuestion,
            final models.Question mQuestion) {
        dbQuestion.setQuestion(mQuestion.getQuestion());
        return dbQuestion;
    }

    /**
     * Pass DB to Model: Souvenir.
     * @param dbSouvenir DB souvenir.
     * @return model souvenir.
     */
    private models.Souvenir passDBSouvenirToModelSouvenir(
            final database.Souvenir dbSouvenir) {
        models.Souvenir mSouvenir = new models.Souvenir(
                dbSouvenir.getIdsouvenir(),
                dbSouvenir.getTourIdtour().getIdtour(),
                dbSouvenir.getSouvenirURL(),
                dbSouvenir.getDownloads());
        return mSouvenir;
    }

    /**
     * Take values from model to DB: Souvenir.
     * @param dbSouvenir DB souvenir.
     * @param mSouvenir Model souvenir.
     * @return DB souvenir.
     */
    private database.Souvenir takeValuesFromModelSouvernir(
            final database.Souvenir dbSouvenir,
            final models.Souvenir mSouvenir) {
        dbSouvenir.setSouvenirURL(mSouvenir.getSouvenirURL());
        dbSouvenir.setDownloads(mSouvenir.getDownloads());
        return dbSouvenir;
    }
}
