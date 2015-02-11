package com.geoquest.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for creating SQLite database.
 * @author Fani .
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // All Static variables
    /**
     * Database Version.
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Database Name.
     */
    private static final String DATABASE_NAME = "geoquest";
    /**
     * Table tour.
     */
    private static final String TABLE_TOUR = "tour";
    /**
     * Table geouser.
     */
    private static final String TABLE_GEOUSER = "geouser";
    /**
     * Table waypoint.
     */
    private static final String TABLE_WAYPOINT = "waypoint";
    /**
     * Table question.
     */
    private static final String TABLE_QUESTION = "question";
    /**
     * column idtour.
     */
    private static final String KEY_ID = "idtour";
    /**
     * column name of tour.
     */
    private static final String KEY_NAME = "name";
    /**
     * column description.
     */
    private static final String KEY_DESCRIPTION = "description";
    /**
     * column difficulty.
     */
    private static final String KEY_DIFFICULTY = "difficulty";
    /**
     * column badge.
     */
    private static final String KEY_BADGE = "badge";
    /**
     * column distance.
     */
    private static final String KEY_DISTANCE = "distance";
    /**
     * column userid.
     */
    private static final String KEY_USERID = "userid";
    /**
     * column id waypoint.
     */
    private static final String KEY_IDWAY = "idwaypoint";
    /**
     * column name.
     */
    private static final String KEY_NAMEWAY = "name";
    /**
     * column longitude coordinate.
     */
    private static final String KEY_LONGITUDE = "longitude";
    /**
     * column latitude coordinate.
     */
    private static final String KEY_LATITUDE = "latitude";
    /**
     * column order position.
     */
    private static final String KEY_ORDERPOSITION = "orderposition";
    /**
     * foreign key id tour.
     */
    private static final String KEY_IDTOUR = "tour_idtour";
    /**
     * column id user.
     */
    private static final String KEY_IDUSER = "iduser";
    /**
     * column username.
     */
    private static final String KEY_USERNAME = "username";
    /**
     * column password.
     */
    private static final String KEY_PASSWORD = "password";
    /**
     * column id waypoint.
     */
    private static final String KEY_IDWAYPOINT = "idwaypoint";
    /**
     * column order position.
     */
    private static final String KEY_ORDER = "orderposition";
    /**
     * column digit position.
     */
    private static final String KEY_DIGITPOSITION = "digitposition";
    /**
     * column question.
     */
    private static final String KEY_QUESTION = "question";

    /**
     * creating table tour.
     */
    private static final String CREATE_TOUR_TABLE = "CREATE TABLE "
            + TABLE_TOUR + "("
            + KEY_ID + " INTEGER," + KEY_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_DIFFICULTY + " TEXT,"
            + KEY_BADGE + " TEXT,"
            + KEY_DISTANCE + " FLOAT,"
            + KEY_USERID + " INTEGER" + ")";
    /**
     * creating table waypoint.
     */
    private static final String CREATE_WAYPOINT_TABLE = "CREATE TABLE "
            + TABLE_WAYPOINT
            + "("
            + KEY_IDWAY + " INTEGER," + KEY_NAMEWAY + " TEXT,"
            + KEY_LONGITUDE + " TEXT,"
            + KEY_LATITUDE + " TEXT,"
            + KEY_ORDERPOSITION + " INTEGER,"
            + KEY_IDTOUR + " INTEGER" + ")";

    /**
     * creating table geouser.
     */
    private static final String CREATE_GEOUSER_TABLE = "CREATE TABLE "
            + TABLE_GEOUSER + "("
            + KEY_IDUSER + " INTEGER," + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT" + ")";

    /**
     * creating table question.
     */
    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE "
            + TABLE_QUESTION + "("
            + KEY_IDWAYPOINT + " INTEGER," + KEY_ORDER + " INTEGER,"
            + KEY_DIGITPOSITION + " INTEGER,"
            + KEY_QUESTION + " TEXT" + ")";

    /**
     * .
     * @param context .
     */
    public MySQLiteHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creating Tables.
     * @param db .
     */
    @Override
    public final void onCreate(final SQLiteDatabase db) {

        db.execSQL(CREATE_TOUR_TABLE);
        db.execSQL(CREATE_WAYPOINT_TABLE);
        db.execSQL(CREATE_GEOUSER_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);
    }

    /**
     * Upgrading database.
     * @param db .
     * @param oldVersion .
     * @param newVersion .
     */
    @Override
    public final void onUpgrade(final SQLiteDatabase db,
            final int oldVersion, final int newVersion) {
        // Drop older table if existed
        db.execSQL(TABLE_TOUR);
        db.execSQL(TABLE_GEOUSER);
        db.execSQL(TABLE_WAYPOINT);
        db.execSQL(TABLE_QUESTION);
        //"DROP TABLE IF EXISTS " +
        // Create tables again
        onCreate(db);
    }

    /**
     * adding data to table geouser.
     * @param id .
     * @param email .
     * @param password .
     */
    public final void addUser(final int id,
            final String email, final String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDUSER, id); // Name
        values.put(KEY_USERNAME, email); // Email
        values.put(KEY_PASSWORD, password); // Email
        //values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        db.insert(TABLE_GEOUSER, null, values);
        db.close(); // Closing database connection
    }

    /**
     * adding data to table question.
     * @param idwaypoint .
     * @param orderposition .
     * @param digitposition .
     * @param question .
     */
    public final void addQuestion(
            final int idwaypoint, final int orderposition,
            final int digitposition, final String question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDWAYPOINT, idwaypoint); // Name
        values.put(KEY_ORDER, orderposition); // Email
        values.put(KEY_DIGITPOSITION, digitposition); // Email
        values.put(KEY_QUESTION, question); // Email
        //values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        db.insert(TABLE_QUESTION, null, values);
        db.close(); // Closing database connection
    }

    /**
     * get the detail of user.
     * @return .
     */
    public final HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_GEOUSER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(KEY_IDUSER, cursor.getString(1));
            user.put(KEY_USERNAME, cursor.getString(2));
            user.put(KEY_PASSWORD, cursor.getString(1 + 2));
            //user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }

    /**
     * count row of table user.
     * @return .
     */
    public final int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GEOUSER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * getting longitude based on order position.
     * @param orderposition .
     * @return .
     */
    public final int getLongitudeDigitCount(final int orderposition) {
        String countQuery = "SELECT  * FROM " + TABLE_QUESTION
                + " WHERE ORDERPOSITION= " + orderposition
                + " AND DIGITPOSITION<100";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int longiCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return longiCount;
    }

    /**
     * getting latitude based on order position.
     * @param orderposition .
     * @return .
     */
    public final int getLatitudeDigitCount(final int orderposition) {
        String countQuery = "SELECT  * FROM " + TABLE_QUESTION
                + " WHERE ORDERPOSITION= " + orderposition
                + " AND DIGITPOSITION>=100";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int latiCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return latiCount;
    }

    /**
     * getting question and digit where the question is.
     * @param orderposition .
     * @return .
     */
    public final List<Question> getLongitudeQuestion(
            final int orderposition) {
        List<Question> questionlistlongitude = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  digitposition, question FROM "
                + TABLE_QUESTION + " WHERE ORDERPOSITION= " + orderposition
                + " AND DIGITPOSITION>=0 AND DIGITPOSITION<=8";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setDigitPosition(Integer.
                        parseInt(cursor.getString(0)));
                question.setQuestion(cursor.getString(1));
                //tour.setDistance(Float.valueOf(
                //cursor.getString(4)).floatValue());
                // Adding contact to list
                questionlistlongitude.add(question);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        // return contact list
        return questionlistlongitude;
    }

    /**
     * get question for latitude.
     * @param orderposition .
     * @return .
     */
    public final List<Question> getLatitudeQuestion(
            final int orderposition) {
        List<Question> questionlistlatitude = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  digitposition, question FROM "
                + TABLE_QUESTION + " WHERE ORDERPOSITION= " + orderposition
                + " AND DIGITPOSITION>=100 AND DIGITPOSITION<=108";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setDigitPosition(Integer.
                        parseInt(cursor.getString(0)));
                question.setQuestion(cursor.getString(1));
                //tour.setDistance(Float.valueOf(
                //cursor.getString(4)).floatValue());
                // Adding contact to list
                questionlistlatitude.add(question);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        // return contact list
        return questionlistlatitude;
    }

    /**
     * reset table.
     */
    public final void resetTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_GEOUSER, null, null);
        db.close();
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations.
     */
    /**
     * Adding new tour.
     * @param tour .
     */
    final void addTour(final Tour tour) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, tour.getIdTour());
        values.put(KEY_NAME, tour.getTourName()); // Contact Name
        values.put(KEY_DESCRIPTION, tour.getDescription()); // Contact Phone
        values.put(KEY_DIFFICULTY, tour.getDifficulty());
        values.put(KEY_BADGE, tour.getBadge());
        values.put(KEY_DISTANCE, tour.getDistance()); // Contact Name
        /*
         * values.put(KEY_USERID, tour.getUserId()); // Contact Phone
         */

        // Inserting Row
        db.insert(TABLE_TOUR, null, values);
        db.close(); // Closing database connection
    }

    /**
     * insert data to table waypoint.
     * @param waypoint .
     */
    final void addWaypoint(final Waypoint waypoint) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDWAY, waypoint.getIdWaypoint());
        values.put(KEY_NAMEWAY, waypoint.getWaypointName()); // Contact Name
        values.put(KEY_LONGITUDE, waypoint.getLongitude()); // Contact Phone
        values.put(KEY_LATITUDE, waypoint.getLatitude());
        values.put(KEY_ORDERPOSITION, waypoint.getOrderPosition());
        // Contact Name
        values.put(KEY_IDTOUR, waypoint.getTourId()); // Contact Phone*/

        // Inserting Row
        db.insert(TABLE_WAYPOINT, null, values);
        db.close(); // Closing database connection
    }

    /**
     * get one tour.
     * @param id .
     * @return .
     */
    final Tour getTour(final int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TOUR, new String[]{KEY_ID,
                    KEY_NAME, KEY_DESCRIPTION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Tour tour = new Tour(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        // return contact
        return tour;
    }

    /**
     * getting latitude.
     * @param orderposition .
     * @return .
     */
    final String getLatitude(final int orderposition) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WAYPOINT, new String[]{KEY_LATITUDE},
                KEY_ORDERPOSITION + "=?",
                new String[]{String.valueOf(orderposition)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        String latitude = cursor.getString(0);

        cursor.close();
        db.close();

        return latitude;

    }

    /**
     * getting longitude.
     * @param orderposition .
     * @return .
     */
    final String getLongitude(final int orderposition) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WAYPOINT, new String[]{KEY_LONGITUDE},
                KEY_ORDERPOSITION + "=?",
                new String[]{String.valueOf(orderposition)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        String longitude = cursor.getString(0);

        cursor.close();
        db.close();

        return longitude;

    }

    /**
     * getting user id.
     * @return .
     */
    final int getUserId() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GEOUSER, new String[]{KEY_IDUSER},
                null,
                null,
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int iduser = cursor.getInt(0);
        cursor.close();
        db.close();

        return iduser;

    }

    /**
     * getting tour id.
     * @return .
     */
    final int getTourId() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TOUR, new String[]{KEY_ID},
                null,
                null,
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int idtour = cursor.getInt(0);
        cursor.close();
        db.close();

        return idtour;

    }
    /**
     * delete all data in column.
     */
    public final void deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOUR, null,
                null);
        db.delete(TABLE_WAYPOINT, null,
                null);
        db.delete(TABLE_QUESTION, null,
                null);
        db.close();
    }

    /**
     * Getting All tours.
     * @return .
     */
    public final List<Tour> getTour() {
        List<Tour> userlist = new ArrayList<Tour>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TOUR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tour tour = new Tour();
                tour.setIdTour(Integer.parseInt(cursor.getString(0)));
                tour.setTourName(cursor.getString(1));
                tour.setDescription(cursor.getString(2));
                tour.setDifficulty(cursor.getString(1 + 2));
                //tour.setDistance(Float.valueOf(
                //cursor.getString(4)).floatValue());
                // Adding contact to list
                userlist.add(tour);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return userlist;
    }

    /**
     * Getting waypoint Count.
     * @return .
     */
    public final int getWaypointCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WAYPOINT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int a = cursor.getCount();
        Log.d("counterway", Integer.toString(a));
        cursor.close();

        // return count
        return a;
    }
}
