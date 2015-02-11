package com.geoquest.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity will launch when user start the application.
 * @author Fani
 */
public class LoginActivity extends Activity {
    /**
     * button for login to application.
     */
    private Button btnLogin;
    /**
     * text box for input username.
     */
    private EditText inputEmail;
    /**
     * text box for input password.
     */
    private EditText inputPassword;
    /**
     * text box for login error.
     */
    private TextView loginErrorMsg;
    /**
     * tag success.
     */
    private static final String KEY_SUCCESS = "success";
    /**
     * tag for iduser.
     */
    private static final String KEY_ID = "iduser";
    /**
     * tag for username.
     */
    private static final String KEY_NAME = "username";
    /**
     * tag for password.
     */
    private static final String KEY_PASSWORD = "password";
    /**
     * JSONArray for user.
     */
    private JSONArray user = null;
    /**
     * db object.
     */
    private MySQLiteHelper db;

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //create database
        db = new MySQLiteHelper(this);

        // Importing all assets like buttons, text fields
        inputEmail = (EditText) findViewById(R.id.loginEmail);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        //btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);

        // login button clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

            	//get username that user entered
                String email = inputEmail.getText().toString();
                //get password that user entered
                String password = inputPassword.getText().toString();
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.loginUser(email, password);

                // check for login response
                try {

                    int success = json.getInt(KEY_SUCCESS);
                    if (success == 1) {
                        loginErrorMsg.setText("");
                        String res = json.getString(KEY_SUCCESS);
                        if (Integer.parseInt(res) == 1) {
                            // user successfully logged in
                            // Store user details in SQLite Database
                            Toast.makeText(LoginActivity.this,
                                    "Login Successful", Toast.LENGTH_SHORT).
                        	        show();
                        	user = json.getJSONArray("user");
                        	//MySQLiteHelper db = new
                        	//MySQLiteHelper(getApplicationContext());
                            JSONObject jsonUser = user.getJSONObject(0);

                            // Clear all previous data in database
                            userFunction.logoutUser(getApplicationContext());
                            //adding user to sqlite database
                            db.addUser(jsonUser.getInt(KEY_ID),
                                    jsonUser.getString(KEY_NAME),
                                    jsonUser.getString(KEY_PASSWORD));

                            // Launch Dashboard Screen
                            Intent dashboard = new Intent(
                                    getApplicationContext(),
                                    MainScreenActivity.class);

                            // Close all views before launching Dashboard
                            dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(dashboard);

                            // Close Login Screen
                            finish();
                        } else if (success == 0) {
                        	Toast.makeText(LoginActivity.this,
                        	        "Incorrect username/password",
                        	        Toast.LENGTH_LONG).show();
                            // Error in login
                            //loginErrorMsg.setText
                        	//("Incorrect username/password");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
