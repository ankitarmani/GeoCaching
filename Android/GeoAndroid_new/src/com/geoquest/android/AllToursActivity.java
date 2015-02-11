package com.geoquest.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * .
 * @author Beatriz
 */
public class AllToursActivity extends ListActivity {

    /**
     * Progress Dialog.
     */
    private ProgressDialog pDialog;
    /**
     * Creating JSON Parser object.
     */
    private JSONParser jParser = new JSONParser();
    /**
     * .
     */
    private ArrayList<HashMap<String, String>> toursList;
    //URLaddress appState = ((URLaddress)getApplicationContext());
    //private String urlAll=appState.getSomeVariable();
    // url to get all tours list
    /**
     * .
     */
    private static String urlAllTours;
    //= "http://10.0.2.2/get_all_tours.php"
    // JSON Node names
    /**
     * Constants.
     */
    private static final String TAG_SUCCESS = "success";
    /**
     * Constants.
     */
    private static final String TAG_TOUR = "tour";
    /**
     * Constants.
     */
    private static final String TAG_ID = "idtour";
    /**
     * Constants.
     */
    private static final String TAG_NAME = "name";
    // tours JSONArray
    /**
     * Tour.
     */
    private JSONArray tour = null;

    /**
     * .
     * @param savedInstanceState .
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_tours);

        // Hashmap for ListView
        toursList = new ArrayList<HashMap<String, String>>();

        URLaddress urlall = new URLaddress();
        urlAllTours = urlall.getUrlAll() + "get_all_tours.php";

        // Loading tours in Background Thread
        new LoadAllTours().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single tour
        // launching Edit Tour Screen
        lv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(final AdapterView<?> parent,
                    final View view, final int position, final long id) {
                // getting values from selected ListItem
                String idtour = ((TextView) view.findViewById(R.id.idtour)).
                        getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        TourInfoActivity.class);

                // sending idtour to next activity
                in.putExtra(TAG_ID, idtour);

                // starting new activity and expecting some response back
                startActivity(in);
                //finish();
            }
        });

    }

    /**
     * Background Async Task to Load all tour by making HTTP Request.
     */
    class LoadAllTours extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllToursActivity.this);
            pDialog.setMessage("Loading tours...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All tours from url.
         * @param args .
         * @return .
         */
        protected final String doInBackground(final String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json =
                    jParser.makeHttpRequest(urlAllTours, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Tours: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // tours found
                    // Getting Array of Tours
                    tour = json.getJSONArray(TAG_TOUR);

                    // looping through All Tours
                    for (int i = 0; i < tour.length(); i++) {
                        JSONObject c = tour.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map =
                                new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        toursList.add(map);
                    }
                } //else {
                // no tours found
                // Launch Add New tour Activity
                //Intent i = new Intent(getApplicationContext(),
                //NewTourActivity.class);
                // Closing all previous activities
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(i);
                //}
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog.
         * @param fileUrl .
         */
        protected void onPostExecute(final String fileUrl) {
            // dismiss the dialog after getting all tours
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {

                public void run() {
                    /**
                     * Updating parsed JSON data into ListView.
                     */
                    ListAdapter adapter = new SimpleAdapter(
                            AllToursActivity.this, toursList,
                            R.layout.list_item, new String[]{TAG_ID,
                                TAG_NAME},
                            new int[]{R.id.idtour, R.id.name});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }
    }
}
