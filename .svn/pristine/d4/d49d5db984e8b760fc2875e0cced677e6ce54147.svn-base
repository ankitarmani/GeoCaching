package com.geoquest.android;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

/**
 * .
 */
public class PhoneLocationManager {
    /**
     * .
     */
    private Activity context;
    /**
     * .
     */
    private LocationManager locationManager;
    /**
     * .
     */
    private LocationListener locationListener;
    /**
     * .
     */
    public static final String GPS_LOCATION_ACTION = "GPS_LOCATION_ACTION";
    /**
     * .
     * @param context1 .
     */
    public PhoneLocationManager(final Activity context1) {
        this.context = context1;
    }
    /**
     * .
     */
    private static final int TIME = 1000;
    /**
     * .
     */
    private static final int COUNT = 20;
    /**
     * .
     */
    public final void setUpGPSListener() {
        locationManager = (LocationManager) context.
                getSystemService(Context.LOCATION_SERVICE);
        Location l = locationManager.
                getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (l != null) {
            MLocation mlocation = new MLocation(
                    l.getLatitude(), l.getLongitude());
            sendBroadcast(mlocation);
        } else {
            setUpApnListener();
        }

        locationListener = new LocationListener() {

            @Override
            public void onStatusChanged(final String provider, final int status,
                    final Bundle extras) {
            }

            @Override
            public void onProviderEnabled(final String provider) {
            }

            @Override
            public void onProviderDisabled(final String provider) {
            }

            @Override
            public void onLocationChanged(final Location l) {
                MLocation mlocation = new MLocation(
                        l.getLatitude(), l.getLongitude());
                sendBroadcast(mlocation);
            }
        };
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, TIME,
                0, locationListener);

    }
    /**
     * .
     */
    public final void setUpApnListener() {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        // Mobile Country Code
        final int mcc = Integer
                .valueOf(tm.getNetworkOperator().
                        substring(0, 3));
        // Mobile Network Code
        final int mnc = Integer
                .valueOf(tm.getNetworkOperator().
                        substring(3, 5));
        PhoneStateListener listener = new PhoneStateListener() {
            @Override
            public void onCellLocationChanged(
                    final CellLocation location) {
                super.onCellLocationChanged(location);
                try {
                    GsmCellLocation gcl =
                            (GsmCellLocation) location;
                    JSONObject holder = new JSONObject();
                    holder.put("version", "1.1.0");
                    holder.put("host", "maps.google.com");
                    holder.put("address_language", "en_US");
                    holder.put("request_address", true);
                    int cid = gcl.getCid();
                    int lac = gcl.getLac();
                    JSONArray array = new JSONArray();
                    JSONObject data = new JSONObject();
                    data.put("cell_id", cid);
                    data.put("location_area_code", lac);
                    data.put("mobile_country_code", mcc);
                    data.put("mobile_network_code", mnc);
                    array.put(data);
                    holder.put("cell_towers", array);

                    HttpClient httpClient =
                            new DefaultHttpClient();

                    HttpConnectionParams.
                        setConnectionTimeout(httpClient.getParams(),
                                COUNT * TIME);
                    HttpConnectionParams.
                        setSoTimeout(httpClient.getParams(), COUNT * TIME);

                    HttpPost post = new HttpPost(
                       "http://74.125.71.147/loc/json");
                    // Set proxy
                    // Get default proxy
                    String host =
                       android.net.Proxy.getDefaultHost();
                    int port = android.net.Proxy.getDefaultPort();
                    if (host != null
                        && host.trim().length() > 0) {
                        HttpHost proxy = new HttpHost(host, port);
                        httpClient.getParams().setParameter(ConnRouteParams.
                            DEFAULT_PROXY, proxy);
                    }
                    StringEntity se = new StringEntity(
                            holder.toString());
                    post.setEntity(se);
                    HttpResponse response = httpClient.execute(post);
                    MLocation mlocation = transResponse(response);
                    sendBroadcast(mlocation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        tm.listen(listener, PhoneStateListener.LISTEN_CELL_LOCATION);
    }
    /**
     * .
     */
    public final void release() {
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
    /**
     * .
     */
    private static final int GOOD = 200;
    /**
     * .
     * @param response .
     * @return .
     */
    private MLocation transResponse(final HttpResponse response) {
        MLocation location = null;
        if (response.getStatusLine().getStatusCode() == GOOD) {

            HttpEntity entity = response.getEntity();
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(
                        entity.getContent()));
                StringBuffer sb = new StringBuffer();
                String result = br.readLine();
                while (result != null) {
                    sb.append(result);
                    result = br.readLine();
                }
                JSONObject json = new JSONObject(sb.toString());
                JSONObject lca = json.getJSONObject("location");
                location = new MLocation(
                        lca.getDouble("latitude"),
                        lca.getDouble("longitude"));

            } catch (Exception e) {
                e.printStackTrace();
                location = null;
            }
        }
        return location;
    }
    /**
     * .
     * @param mlocation .
     */
    private void sendBroadcast(final MLocation mlocation) {
        Intent i = new Intent(GPS_LOCATION_ACTION);
        i.putExtra("mlocation", mlocation);
        context.sendBroadcast(i);
    }
    /**
     * .
     */
    public static class MLocation implements Serializable {

        /**
         * .
         */
        private static final long serialVersionUID = 1L;
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
         * @param latitude .
         * @param longitude .
         */
        public MLocation(final double latitude,
                final double longitude) {
            super();
            lat = latitude;
            lng = longitude;
        }
        /**
         * .
         * @return .
         */
        public final double getLatitude() {
            return lat;
        }
        /**
         * .
         * @return .
         */
        public final double getLongitude() {
            return lng;
        }
    }
}