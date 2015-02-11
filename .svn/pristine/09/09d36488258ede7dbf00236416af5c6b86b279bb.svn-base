package com.geoquest.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author Fani
 * This activity will be launch after user finish the tour
 * This activity will show the tour's badges and 2 button
 * One button to going back to main page
 * The other button for sharing badges to Facebook wall
 */
public class FinishBadges extends Activity {
    /**
     * button for going back to main page.
     */
    private Button btnMainPage;
    /**
     * button for sharing badges to Facebook.
     */
    private Button btnFacebook;
    /** Called when the activity is first created. */
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badges);
        btnMainPage = (Button) findViewById(R.id.btnMainPage);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
     
        ImageView jpgView = (ImageView) findViewById(R.id.jpgview);

        //take the badges picture from sdcard
        String myJpgPath = "/sdcard/testfull.JPG";

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(myJpgPath);//, options);
        //viewing picture in android mobile
        jpgView.setImageBitmap(bm);

        btnMainPage.setOnClickListener(new View.OnClickListener() {
            //start main page activity
            public void onClick(final View view) {
                Intent i = new Intent(getApplicationContext(),
                        MainScreenActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            //start share Facebook activity
            public void onClick(final View view) {
                Intent i = new Intent(getApplicationContext(),
                        ShareFacebook.class);
                startActivity(i);
                finish();
            }
        });
    }
}
