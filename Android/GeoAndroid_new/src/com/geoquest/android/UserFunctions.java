package com.geoquest.android;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

/**
 * .
 * @author Beatriz
 */
public class UserFunctions {

    /**
     * .
     */
    private JSONParser jsonParser;

    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    /**
     * .
     */
    private URLaddress urlall = new URLaddress();
    /**
     * .
     */
    private String loginURL = urlall.getUrlAll() + "get_all_users.php";

    //private static String registerURL = "http://10.0.2.2/ah_login_api/";

    /**
     * .
     */
    //private static String loginTag = "login";
    /**
     * .
     */
    //private static String registerTag = "register";

    /**
     * constructor.
     */
    public UserFunctions() {
        jsonParser = new JSONParser();
    }

    /**
     * function make Login Request.
     * @param email .
     * @param password .
     * @return .
     */
    public final JSONObject loginUser(
            final String email, final String password) {
        // Building Parameters
        Log.d("All Lists: ", "123");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.makeHttpRequest(loginURL, "POST", params);
        //Log.d("All Lists: ", json.toString());
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }

    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
   /* public JSONObject registerUser(
        String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        JSONObject json = jsonParser.makeHttpRequest(registerURL,"GET", params);
        // return json
        return json;
    }*/

    /**
     * Function get Login status.
     * @param context .
     * @return .
     */
    public final boolean isUserLoggedIn(final Context context) {
        MySQLiteHelper db = new MySQLiteHelper(context);
        int count = db.getRowCount();
        if (count > 0) {
            // user logged in
            return true;
        }
        return false;
    }

    /**
     * Function to logout user.
     * Reset Database.
     * @param context .
     * @return .
     */
    public final boolean logoutUser(final Context context) {
        MySQLiteHelper db = new MySQLiteHelper(context);
        db.resetTables();
        return true;
    }

}
