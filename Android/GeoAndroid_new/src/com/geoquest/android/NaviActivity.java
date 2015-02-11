package com.geoquest.android;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geoquest.android.PhoneLocationManager.MLocation;

/**
 * .
 * @author Beatriz .
 */
public class NaviActivity extends Activity {

    /**
     * .
     */
    private static final String TAG = "NaviActivity";
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
    private static final String TAG_COUNTER = "counter";
    /**
     * .
     * When player reach the ACCURACY meters around the waypoint,
     * the question will show.
     */
    private static final double ACCURACY = 10.0;
    /**
     * .
     */
    private static final double LIMIT_01_LOW = 70.0;
    /**
     * .
     */
    private static final double LIMIT_01_HIGH = 80.0;
    /**
     * .
     */
    private static final double LIMIT_02_LOW = 100.0;
    /**
     * .
     */
    private static final double LIMIT_02_HIGH = 110.0;
    /**
     * .
     */
    private static final double LIMIT_03_LOW = 130.0;
    /**
     * .
     */
    private static final double LIMIT_03_HIGH = 140.0;
    /**
     * .
     */
    private String resultLat = "";
    /**
     * .
     */
    private String resultLng = "";
    /**
     * .
     */
    private int counter = 0;
    /**
     * .
     */
    private int orderPosition = 0;
    /**
     * .
     */
    private SensorManager mSensorManager;
    /**
     * .
     */
    private ArrowView mView;
    /**
     * .
     */
    private float[] mValues;
    /**
     * .
     */
    private LinearLayout mainLayout;
    /**
     * .
     */
    private Sensor mSensor;
    /**
     * My current location.
     */
    private MLocation mlocaiton;
    /**
     * .
     */
    private double targetLatitude;
    /**
     * .
     */
    private double targetLongitude;
    /**
     * .
     */
    private double realTargetLatitude;
    /**
     * .
     */
    private double realTargetLongitude;
    /**
     * .
     */
    private double directionAngle;
    /**
     * .
     */
    private EditText editTargetLatitude;
    /**
     * .
     */
    private EditText editTargetLongitude;
    /**
     * .
     */
    private PhoneLocationManager locationManager;
    /**
     * .
     */
    private Broadcastreceiver receiver;
    /**
     * .
     */
    private TextView positionText;
    /**
     * .
     */
    private TextView distanceText;
    /**
     * .
     */
    private Button btnQuestion;
    /**
     * .
     */
    private MySQLiteHelper db = new MySQLiteHelper(this);
    /**
     * .
     */
    private static String geoUser;
    /**
     * .
     */
    private JSONParser jParser = new JSONParser();

    /**
     * .
     */
    private final SensorEventListener mListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(final SensorEvent event) {
            mValues = event.values;
            if (mView != null) {
                mView.setValues(mValues);
                mView.invalidate();
            }
        }

        @Override
        public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
        }
    };

    /**
     * Class.
     */
    class Coordinate {

        /**
         * .
         * @param longitude1 .
         * @param latitude1 .
         */
        public Coordinate(final double longitude1, final double latitude1) {
            super();
        }
    }

    /**
     * .
     * @param icicle .
     */
    @Override
    protected final void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        new ProgressDialog(this);

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        setContentView(R.layout.navi_arrow);

        mainLayout = (LinearLayout) findViewById(R.id.arrow);
        mView = new ArrowView(this);
        mainLayout.addView(mView);

        editTargetLatitude = (EditText) findViewById(R.id.latitude);
        editTargetLongitude = (EditText) findViewById(R.id.longitude);

        Bundle extras = getIntent().getExtras();
        resultLat = extras.getString(TAG_LAT);
        resultLng = extras.getString(TAG_LNG);
        counter = extras.getInt("counter");
        orderPosition = extras.getInt("orderPosition");

        Log.d("result_lat_navi", resultLat);
        Log.d("result_lng_navi", resultLng);
        Log.d("counter in navi", Integer.toString(counter));

        String latsql = db.getLatitude(orderPosition);
        String lngsql = db.getLongitude(orderPosition);
        realTargetLatitude = Double.parseDouble(latsql);
        realTargetLongitude = Double.parseDouble(lngsql);

        if (orderPosition == 0) {

            editTargetLatitude.setText(realTargetLatitude + "");
            editTargetLongitude.setText(realTargetLongitude + "");

        } else {

            editTargetLatitude.setText(resultLat + "");
            editTargetLongitude.setText(resultLng + "");
        }

        Log.d("orderposition in question", Integer.toString(orderPosition));


        btnQuestion = (Button) findViewById(R.id.btnQuestion);

        if (counter != 0) {
            btnQuestion.setOnClickListener(new View.OnClickListener() {

                public void onClick(final View view) {

                    Intent i = new Intent(getApplicationContext(),
                            QuestionActivity.class);
                    i.putExtra(TAG_COUNTER, counter);
                    i.putExtra("orderPosition", orderPosition);
                    startActivity(i);
                }
            });
        } else {
            btnQuestion.setOnClickListener(new View.OnClickListener() {

                public void onClick(final View view) {

                    //will move this code if success implement the question
                    String idtour = Integer.toString(db.getTourId());
                    String iduser = Integer.toString(db.getUserId());
                    URLaddress urlall = new URLaddress();
                    Log.d("All idtour: ", idtour);
                    geoUser = urlall.getUrlAll() + "insert_geouser_tour.php";
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("idtour", idtour));
                    params.add(new BasicNameValuePair("iduser", iduser));
                    JSONObject json =
                            jParser.makeHttpRequest(geoUser, "POST", params);
                    Log.d("All Lists: ", "123");
                    Toast.makeText(NaviActivity.this, "Congratulations!"
                    + "You've completed the tour!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(),
                            AllToursActivity.class);
                    startActivity(i);
                }
            });
        }

        btnQuestion = (Button) findViewById(R.id.btnQuestion);

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

                if (counter != 0) {

                    Intent i = new Intent(getApplicationContext(),
                            QuestionActivity.class);
                    i.putExtra(TAG_COUNTER, counter);
                    i.putExtra("orderPosition", orderPosition);
                    startActivity(i);

                } else {

                    String currentDateTimeString =
                            DateFormat.getDateTimeInstance().format(new Date());
                    Log.d("date", currentDateTimeString);
                    String idtour = Integer.toString(db.getTourId());
                    String iduser = Integer.toString(db.getUserId());
                    URLaddress urlall = new URLaddress();
                    Log.d("All idtour: ", idtour);
                    geoUser = urlall.getUrlAll() + "insert_geouser_tour.php";
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("iduser", iduser));
                    params.add(new BasicNameValuePair("idtour", idtour));
                    params.add(new BasicNameValuePair("iddate",
                            currentDateTimeString));
                    JSONObject json =
                            jParser.makeHttpRequest(geoUser, "POST", params);
                    Log.d("All Lists: ", "123");
                    Toast.makeText(NaviActivity.this, "Congratulations!"
                    + "You've completed the tour!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(),
                            FinishBadges.class);
                    startActivity(i);
                    finish();
                }
            }
        });


        Button buttonOk = (Button) findViewById(R.id.buttonOk); // ȷ��
        buttonOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                targetLatitude = Double.parseDouble(
                        editTargetLatitude.getText().toString());
                targetLongitude = Double.parseDouble(
                        editTargetLongitude.getText().toString());
                pointToTarget();
            }
        });

        Button btnReAnswer = (Button) findViewById(R.id.btnReAnswer);
        Button btnLastWP = (Button) findViewById(R.id.btnLastWP);

        if (orderPosition > 0) {

            btnReAnswer.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    Intent i = new Intent(getApplicationContext(),
                            QuestionActivity.class);
                    i.putExtra(TAG_COUNTER, ++counter);
                    i.putExtra("orderPosition", --orderPosition);
                    startActivity(i);
                }
            });

            btnLastWP.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    ++counter;
                    --orderPosition;
                    String lastLatsql = db.getLatitude(orderPosition);
                    String lastLngsql = db.getLongitude(orderPosition);
                    realTargetLatitude = Double.parseDouble(lastLatsql);
                    realTargetLongitude = Double.parseDouble(lastLngsql);

                    editTargetLatitude.setText(realTargetLatitude + "");
                    editTargetLongitude.setText(realTargetLongitude + "");

                    targetLatitude = realTargetLatitude;
                    targetLongitude = realTargetLongitude;
                    pointToTarget();
                }
            });
        }

        distanceText = (TextView) findViewById(R.id.distance);
        positionText = (TextView) findViewById(R.id.currentposition);

        locationManager = new PhoneLocationManager(this);

        // pointToTarget();
    }

    /**
     * .
     */
    @Override
    protected final void onPause() {
        super.onPause();
        locationManager.release();
        unregisterReceiver(receiver);
    }

    /**
     * .
     */
    @Override
    protected final void onResume() {
        super.onResume();
        if (Config.LOGD) {
            Log.d(TAG, "onResume");
        }

        IntentFilter filter = new IntentFilter();
        receiver = new Broadcastreceiver();
        filter.addAction(PhoneLocationManager.GPS_LOCATION_ACTION);
        registerReceiver(receiver, filter);

        mSensorManager.registerListener(mListener, mSensor,
                SensorManager.SENSOR_DELAY_GAME);
        //locationManager.setUpApnListener();
        locationManager.setUpGPSListener();
    }
    /**
     * .
     */
    private static final double HALF = 0.50;
    /**
     * .
     */
    private static final double AZIMUTH = 360000.0;
    /**
     * .
     */
    private static final double RES = 180.0;
    /**
     * .
     */
    private static final double DOUB = 360.0;

    /**
     * .
     * @param lat1 .
     * @param lon1 .
     * @param lat2 .
     * @param lon2 .
     * @return .
     */
    private double computeAzimuth( double lat1,  double lon1,
             double lat2,  double lon2) {
        double result = 0.0;

        int ilat1 = (int) (HALF + lat1 * AZIMUTH);
        int ilat2 = (int) (HALF + lat2 * AZIMUTH);
        int ilon1 = (int) (HALF + lon1 * AZIMUTH);
        int ilon2 = (int) (HALF + lon2 * AZIMUTH);

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        if ((ilat1 == ilat2) && (ilon1 == ilon2)) {
            return result;
        } else if (ilon1 == ilon2) {
            if (ilat1 > ilat2) {
                result = RES;
            }
        } else {
            double c = Math.acos(Math.sin(lat2) * Math.sin(lat1)
                    + Math.cos(lat2)
                    * Math.cos(lat1) * Math.cos((lon2 - lon1)));
            double aa = Math.asin(Math.cos(lat2) * Math.sin((lon2 - lon1))
                    / Math.sin(c));
            result = Math.toDegrees(aa);
            if ((ilat2 > ilat1) && (ilon2 > ilon1)) {
                // MUST HAVE AT LEAST ONE STATEMENT HERE!!!!
            } else if ((ilat2 < ilat1) && (ilon2 < ilon1)) {
                result = RES - result;
            } else if ((ilat2 < ilat1) && (ilon2 > ilon1)) {
                result = RES - result;
            } else if ((ilat2 > ilat1) && (ilon2 < ilon1)) {
                result += DOUB;
            }
        }
        return result;
    }

    /**
     * earth radius in meters.
     */
    private static final double EARTH_RADIUS = 6371004.0;

    /**
     * .
     * @param lat1 .
     * @param lon1 .
     * @param lat2 .
     * @param lon2 .
     * @return .
     */
    final double computeDistance(double lat1, double lon1, double lat2,
            double lon2) {

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double d = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)
                * Math.cos(lat2) * Math.cos(lon1 - lon2);
        return (EARTH_RADIUS * Math.acos(d));
    }

    /**
     * .
     */
    public class Broadcastreceiver extends BroadcastReceiver {

        /**
         * .
         * @param context .
         * @param intent .
         */
        @Override
        public final void onReceive(final Context context,
                final Intent intent) {
            mlocaiton = (MLocation) intent.getSerializableExtra("mlocation");

            pointToTarget();
        }
    }

    /**
     * .
     */
    private void pointToTarget() {

        if (mlocaiton != null) {

            directionAngle = computeAzimuth(mlocaiton.getLatitude(),
                    mlocaiton.getLongitude(), targetLatitude, targetLongitude);
            double distance = computeDistance(mlocaiton.getLatitude(),
             mlocaiton.getLongitude(), realTargetLatitude, realTargetLongitude);
            mView.setDirectionAngle(directionAngle);
            DecimalFormat df = new DecimalFormat("#.00");
            positionText.setText("Current coordinates: lat "
                    + mlocaiton.getLatitude() + " lng "
                    + mlocaiton.getLongitude());
            distanceText.setText("Distance: " + df.format(distance)
                    + " Meters");
            Log.d("directionAngle", directionAngle + "");

            if (distance < ACCURACY) {

                if (counter != 0) {

                    Intent i = new Intent(getApplicationContext(),
                            QuestionActivity.class);
                    i.putExtra(TAG_COUNTER, counter);
                    i.putExtra("orderPosition", orderPosition);
                    startActivity(i);

                } else {

                    String currentDateTimeString = DateFormat.
                            getDateTimeInstance().format(new Date());
                    Log.d("date", currentDateTimeString);
                    String idtour = Integer.toString(db.getTourId());
                    String iduser = Integer.toString(db.getUserId());
                    URLaddress urlall = new URLaddress();
                    Log.d("All idtour: ", idtour);
                    geoUser = urlall.getUrlAll() + "insert_geouser_tour.php";
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("iduser", iduser));
                    params.add(new BasicNameValuePair("idtour", idtour));
                    params.add(new BasicNameValuePair("iddate",
                            currentDateTimeString));
                    JSONObject json =
                            jParser.makeHttpRequest(geoUser, "POST", params);
                    Log.d("All Lists: ", "123");
                    Toast.makeText(NaviActivity.this, "Congratulations!"
                    + "You've completed the tour!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(),
                            FinishBadges.class);
                    startActivity(i);
                    finish();
                }
            } else if ((distance > LIMIT_01_LOW) && (distance < LIMIT_01_HIGH))
            {
                Toast.makeText(NaviActivity.this,
                        "70 meters away from the right next waypoint. "
                + "You can re-answer the question.", Toast.LENGTH_SHORT).show();

                if ((distance > LIMIT_02_LOW) && (distance < LIMIT_02_HIGH)) {
                    Toast.makeText(NaviActivity.this,
                            "100 meters away from the right next waypoint. "
                    + "You can re-answer the question.",
                    Toast.LENGTH_SHORT).show();

                    if ((distance > LIMIT_03_LOW) && (distance < LIMIT_03_HIGH))
                    {
                        Toast.makeText(NaviActivity.this,
                                "130 meters away from the right next waypoint. "
                        + "You can re-answer the question.",
                        Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    /**
     * .
     */
    @Override
    protected final void onStop() {
        if (Config.LOGD) {
            Log.d(TAG, "onStop");
        }
        mSensorManager.unregisterListener(mListener);
        super.onStop();
    }
}
