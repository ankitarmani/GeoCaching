package com.geoquest.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * .
 * @author Beatriz .
 */
public class MainScreenActivity extends Activity {

    /**
     * .
     */
    private Button btnViewTours;
    /**
     * .
     */
    private MySQLiteHelper db;

    /**
     * .
     * @param savedInstanceState .
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        db = new MySQLiteHelper(this);
        // Buttons
        btnViewTours = (Button) findViewById(R.id.btnViewTours);

        // view tours click event
        btnViewTours.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {
                // Launching All tours Activity
                Intent i = new Intent(
                        getApplicationContext(), AllToursActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
