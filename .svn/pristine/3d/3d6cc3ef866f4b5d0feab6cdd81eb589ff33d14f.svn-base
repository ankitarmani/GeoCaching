package com.geoquest.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
/**
 * .
 * @author Beatriz .
 */
public class TourInfoActivity extends MapActivity {

    /**
     * .
     */
    private String idtour;
    /**
     * .
     */
    private int counterway;
    /**
     * .
     */
    private GeoPoint p;
    /**
     * .
     */
    private MapView mapView;
    /**
     * .
     */
    private MapController mapController;
    /**
     * .
     */
    private double lat;
    /**
     * .
     */
    private double lng;
    /**
     * .
     */
    private ListView lv;
    /**
     * .
     */
    private Button btnTourInfo;
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
    private ArrayList<HashMap<String, String>> detailtourslist;
    /**
     * url to get all tours list.
     */
    private static String urlAllWaypoint;
    /**
     * .
     */
    private static String urlDetailsTour;
    /**
     * .
     */
    private static String urlQuestion;
    /**
     * .
     */
    private static String imageURL;
    /**
     * .
     */
    //private static String geoUser;
    /**
     * JSON Node names.
     */
    private static final String TAG_SUCCESS = "success";
    /**
     * .
     */
    private static final String TAG_TOUR = "waypoints";
    /**
     * .
     */
    private static final String TAG_IDW = "idwaypoint";
    /**
     * .
     */
    private static final String TAG_NAME = "name";
    /**
     * .
     */
    private static final String TAG_ID = "idtour";
    /**
     * .
     */
    //private static final String TAG_ORDER = "orderPosition";
    /**
     * .
     */
    //private static final String TAG_COUNTER = "rows";
    /**
     * .
     */
    private static final String TAG_TOURDETAIL = "tour";
    /**
     * .
     */
    private static final String TAG_DESCRIPTION = "description";
    /**
     * .
     */
    private static final String TAG_DIFFICULTY = "difficulty";
    /**
     * .
     */
    private static final String TAG_LATITUDE = "latitude";
    /**
     * .
     */
    private static final String TAG_LONGITUDE = "longitude";
    /**
     * .
     */
    private static final String TAG_DISTANCE = "distance";
    /**
     * .
     */
    private static final String TAG_ORDERPOSITION = "orderPosition";
    /**
     * .
     */
    private static final String TAG_TOUR_IDTOUR = "tour_idtour";
    /**
     * .
     */
    private static final String TAG_LAT = "resultLat";
    /**
     * .
     */
    private static final String TAG_LNG = "resultLng";
    /**
     * .
     */
    private MySQLiteHelper db;
    /**
     * tours JSONArray.
     */
    private JSONArray waypoint = null;
    /**
     * .
     */
    private JSONArray detailtours = null;

    /**
     * .
     */
    private JSONArray questions = null;

    /**
     * .
     */
    private String extStorageDirectory;

    /**
     * .
     */
    private Bitmap bm;
    /**
     * .
     */
    private static final int CONST = 100;
    /**
     * .
     * @param savedInstanceState .
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_info);



        // Buttons
        btnTourInfo = (Button) findViewById(R.id.btnTourInfo);

        Bundle extras = getIntent().getExtras();
        idtour = extras.getString(TAG_ID);


        // Hashmap for ListView
        detailtourslist = new ArrayList<HashMap<String, String>>();

        URLaddress urlall = new URLaddress();
        urlAllWaypoint = urlall.getUrlAll() + "get_all_lists.php";
        urlDetailsTour = urlall.getUrlAll() + "get_detail_tours.php";
        urlQuestion = urlall.getUrlAll() + "get_all_question.php";
        //image_URL=urlall.getUrlAll() + "number_0.png";


        db = new MySQLiteHelper(this);

        // Loading tours in Background Thread
        new LoadAllWaypoints().execute();
        /*counterway=db.getWaypointCount();
        Log.d("counterway", Integer.toString(counterway));
        counterway-=1;
        Log.d("counterway", Integer.toString(counterway));*/

        // Set the text and call the connect function.
        //txt.setText(idtour1);
        //txt.setText(getServerData(KEY_121));

        // view tours click event
        btnTourInfo.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {
                Intent i =
                        new Intent(getApplicationContext(), NaviActivity.class);

                //i.putExtra(TAG_ID, idtour1);
                i.putExtra("counter", counterway);
                i.putExtra(TAG_LAT, "0");
                i.putExtra(TAG_LNG, "0");
                i.putExtra("orderPosition", 0);
                //i.putExtra(TAG_ORDER, 0);

                startActivity(i);
                finish();
                //finish();
            }
        });

    }

    /**
     * Background Async Task to Load all tour by making HTTP Request.
     */
    class LoadAllWaypoints extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TourInfoActivity.this);
            pDialog.setMessage("Loading tour information...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All tours from url.
         * @param args .
         * @return .
         */
        protected String doInBackground(final String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("idtour", idtour));
            // getting JSON string from URL
            JSONObject json =
                    jParser.makeHttpRequest(urlAllWaypoint, "POST", params);
            JSONObject json1 =
                    jParser.makeHttpRequest(urlDetailsTour, "POST", params);
            JSONObject json2 =
                    jParser.makeHttpRequest(urlQuestion, "POST", params);
            db.deleteData();

            // Check your log cat for JSON reponse
            Log.d("All Lists: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
                //int success1=json1.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // tours found
                    // Getting Array of Tours
                    waypoint = json.getJSONArray(TAG_TOUR);
                    for (int i = 0; i < waypoint.length(); i++) {
                        JSONObject c = waypoint.getJSONObject(i);

                        // Storing each json item in variable
                        int idway = c.getInt(TAG_IDW);
                        String nameway = c.getString(TAG_NAME);
                        String longitude = c.getString(TAG_LONGITUDE);
                        String latitude = c.getString(TAG_LATITUDE);
                        int orderposition = c.getInt(TAG_ORDERPOSITION);
                        int tourid = c.getInt(TAG_TOUR_IDTOUR);

                        db.addWaypoint(new Waypoint(idway, nameway, longitude,
                                latitude, orderposition, tourid));

                    }
                    counterway = db.getWaypointCount();
                    Log.d("counterway", Integer.toString(counterway));
                    counterway -= 1;
                    Log.d("counterway", Integer.toString(counterway));

                    //counterway=db.getWaypointCount();
                    //Log.d("counterway", Integer.toString(counterway));
                    JSONObject test = waypoint.getJSONObject(0);
                    //counterway = test.getString(TAG_COUNTER);
                    lat = test.getDouble(TAG_LATITUDE);
                    lng = test.getDouble(TAG_LONGITUDE);

                    detailtours = json1.getJSONArray(TAG_TOURDETAIL);
                    // looping through All Tours
                    for (int i = 0; i < detailtours.length(); i++) {
                        JSONObject d = detailtours.getJSONObject(i);

                        // Storing each json item in variable
                        String id = d.getString(TAG_ID);
                        int idtour1 = d.getInt(TAG_ID);
                        String name = d.getString(TAG_NAME);
                        String description = d.getString(TAG_DESCRIPTION);
                        String difficulty = d.getString(TAG_DIFFICULTY);
                        String badge = d.getString("badge");
                        String distance = d.getString(TAG_DISTANCE);
                        float dist = Float.valueOf(d.getString(TAG_DISTANCE)).
                                floatValue();
                        db.addTour(new Tour(idtour1, name, description,
                                difficulty, badge, dist));
                        URLaddress urlall = new URLaddress();
                        imageURL = urlall.getUrlAll() + "uploads/" + badge; 
                        // + ".png";

                        // creating new HashMap
                        HashMap<String, String> map =
                                new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_NAME, "Name: " + name);
                        map.put(TAG_DESCRIPTION, "Description: " + description);
                        map.put(TAG_DIFFICULTY, "Difficulty: " + difficulty);
                        map.put(TAG_DISTANCE, "Distance: " + distance + " m");

                        // adding HashList to ArrayList
                        detailtourslist.add(map);
                    }

                    questions = json2.getJSONArray("question");
                    for (int i = 0; i < questions.length(); i++) {
                        JSONObject a = questions.getJSONObject(i);

                        // Storing each json item in variable
                        int idwaypoint = a.getInt("idwaypoint");
                        int orderposition = a.getInt("orderposition");
                        int digitposition = a.getInt("digitposition");
                        String question = a.getString("question");
                        db.addQuestion(idwaypoint, orderposition,
                                digitposition, question);

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
         * .
         */
        private static final int NUM = 1000000;
        /**
         * .
         */
        private static final int ZOOM = 20;

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

                    ImageView bmImage = (ImageView) findViewById(R.id.img);
                    BitmapFactory.Options bmOptions;
                    bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
                    bm = loadImage(imageURL, bmOptions);
                    bmImage.setImageBitmap(bm);
                    extStorageDirectory = Environment.
                            getExternalStorageDirectory().toString();
                    //+"/DCIM/Camera/";

                    OutputStream outStream = null;
                    File file = new File(extStorageDirectory, "testfull.JPG");
                    try {
                        outStream = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG,
                                CONST, outStream);
                        outStream.flush();
                        outStream.close();

                        //Toast.makeText(ImageTestActivity.this,
                        //"Saved", Toast.LENGTH_LONG).show();

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        //Toast.makeText(ImageTestActivity.this,
                        //e.toString(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        //Toast.makeText(ImageTestActivity.this,
                        //e.toString(), Toast.LENGTH_LONG).show();
                    }
                    /**
                     * Updating parsed JSON data into ListView.
                     */
                    lv = (ListView) findViewById(R.id.listView);
                    ListAdapter adapter = new SimpleAdapter(
                            TourInfoActivity.this, detailtourslist,
                            R.layout.list_item_1, new String[]{TAG_ID, TAG_NAME,
                                    TAG_DESCRIPTION, TAG_DIFFICULTY,
                                    TAG_DISTANCE},
                                    new int[]{R.id.idtour1, R.id.name1,
                                    R.id.description1, R.id.difficulty1,
                                    R.id.distance1});
                    // updating listview
                    lv.setAdapter(adapter);

                    mapView = (MapView) findViewById(R.id.mapView);
                    mapView.setSatellite(true);
                    mapController = mapView.getController();

                    p = new GeoPoint((int) (lat * NUM), (int) (lng * NUM));
                    mapController.setCenter(p);
                    mapController.setZoom(ZOOM);
                    mapView.setBuiltInZoomControls(true);
                    mapController.animateTo(p);
                    MapOverlay mapOverlay = new MapOverlay();
                    List<Overlay> listOfOverlays = mapView.getOverlays();
                    listOfOverlays.clear();
                    listOfOverlays.add(mapOverlay);

                }
            });

        }
    }

    /**
     * .
     * @return .
     */
    @Override
    protected final boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    /**
     * .
     */
    private static final int ARG = 50;

    /**
     * .
     */
    class MapOverlay extends com.google.android.maps.Overlay {

        @Override
        public boolean draw(final Canvas canvas, final MapView mapView1,
                final boolean shadow, final long when) {
            super.draw(canvas, mapView1, shadow);

            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView1.getProjection().toPixels(p, screenPts);

            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(
                    getResources(), R.drawable.start);
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y - ARG, null);
            return true;
        }
    }

    /**
     * .
     * @param url .
     * @param options .
     * @return .
     */
    private Bitmap loadImage(final String url,
            final BitmapFactory.Options options) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = openHttpConnection(url);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
            System.out.println(e1);
        }
        return bitmap;
    }

    /**
     * .
     * @param strURL .
     * @return .
     * @throws IOException .
     */
    private InputStream openHttpConnection(
            final String strURL) throws IOException {
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return inputStream;
    }
}
