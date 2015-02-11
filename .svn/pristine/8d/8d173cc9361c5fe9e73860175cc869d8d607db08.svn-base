package com.geoquest.android;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.SessionStore;
import com.facebook.android.Facebook.DialogListener;

/**
 * @author Ming
 *
 */
/**
 * @author Ming
 *
 */
public class ShareFacebook extends Activity {

    /**
     *
     */
    private Facebook mFacebook;
    /**
     *
     */
    private static final String[] PERMISSIONS = new
            String[] {"publish_stream", "read_stream", "offline_access"};
    /**
     *
     */
    private ProgressDialog mProgress;
    /**
     *
     */
    private static final String APP_ID = "143008165836702";
    /** Called when the activity is first created. */
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(this);
        mFacebook = new Facebook(APP_ID);

        SessionStore.restore(mFacebook, this);

        if (mFacebook.isSessionValid()) {

            String name = SessionStore.getName(this);

            if (name.equals("")) {
                   name = "Unknown";
            }
            // name = (name.equals("")) ? "Unknown" : name;

            Toast.makeText(ShareFacebook.this, "login",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), UploadFBPhoto.class);
            startActivity(i);
            finish();
        }

        if (!mFacebook.isSessionValid()) {
            onFacebookClick();
            }
        // TODO Auto-generated method stub
    }
    /**
    *
    */
   private void onFacebookClick() {

         if (mFacebook.isSessionValid()) {
           final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Delete current Facebook connection?")
                  .setCancelable(false)
                  .setPositiveButton("Yes", new
                        DialogInterface.OnClickListener() {
                   public void onClick(final DialogInterface dialog,
                              final int id) {
                         fbLogout();
                    }
                  })
                  .setNegativeButton("No", new DialogInterface
                           .OnClickListener() {
                     public void onClick(final DialogInterface dialog,
                             final int id) {
                         dialog.cancel();
                           //mFacebookBtn.setChecked(true);
                      }
                   });

               final AlertDialog alert = builder.create();

           alert.show();
          } else {
          // mFacebookBtn.setChecked(false);

           mFacebook.authorize(this, PERMISSIONS, -1, new
                     FbLoginDialogListener());
         }
        }

   /**
    *
    * @author ankit
    *
    */
   private final class FbLoginDialogListener implements DialogListener {

        /**
        *@param values it is Parcelable
        */
   public void onComplete(final Bundle values) {
           SessionStore.save(mFacebook, ShareFacebook.this);

           //mFacebookBtn.setText("  Facebook (No Name)");
           //mFacebookBtn.setChecked(true);
           //mFacebookBtn.setTextColor(Color.WHITE);

           getFbName();
       }

   /**
    *@param error throws error
    */
       public void onFacebookError(final FacebookError error) {
           Toast.makeText(ShareFacebook.this, "Facebook connection failed",
           Toast.LENGTH_SHORT).show();

          //mFacebookBtn.setChecked(false);
       }

       /**
        *@param error throws error
        */
       public void onError(final DialogError error) {
              Toast.makeText(ShareFacebook.this, "Facebook connection failed",
                      Toast.LENGTH_SHORT).show();

         // mFacebookBtn.setChecked(false);
       }

       /**
        *
        */
       public void onCancel() {
             //mFacebookBtn.setChecked(false);
       }
   }

       /**
        *
        */
       private void getFbName() {
       mProgress.setMessage("Finalizing ...");
       mProgress.show();

       new Thread() {
           @Override
               public void run() {
               String name = "";
               int what = 1;

               try {
                   String me = mFacebook.request("me");

                   JSONObject jsonObj = (JSONObject) new JSONTokener(me)
                   .nextValue();
                   name = jsonObj.getString("name");
                   what = 0;
                   } catch (Exception ex) {
                   ex.printStackTrace();
               }

            mFbHandler.sendMessage(mFbHandler.obtainMessage(what, name));
           }
       } .start();
    }

    /**
     *
     */
    private void fbLogout() {
       mProgress.setMessage("Disconnecting from Facebook");
       mProgress.show();

       new Thread() {
           @Override
           public void run() {
               SessionStore.clear(ShareFacebook.this);

               int what = 1;

               try {
                    mFacebook.logout(ShareFacebook.this);

                    what = 0;
                 } catch (Exception ex) {
                      ex.printStackTrace();
               }

                mHandler.sendMessage(mHandler.obtainMessage(what));
            }
        } .start();
     }

    /**
     *
     */
    private Handler mFbHandler = new Handler() {
       @Override
        public void handleMessage(final Message msg) {
          mProgress.dismiss();

           if (msg.what == 0) {
               String username = (String) msg.obj;

               if (username.equals("")) {
                    username = "No Name";
               }
               //username = (username.equals("")) ? "No Name" : username;


               SessionStore.saveName(username, ShareFacebook.this);

              // mFacebookBtn.setText("  Facebook (" + username + ")");

               Toast.makeText(ShareFacebook.this, "Connected to Facebook as "
               + username, Toast.LENGTH_SHORT).show();
               Intent i = new Intent(getApplicationContext(),
                       UploadFBPhoto.class);
               startActivity(i);
               finish();
           } else {
                    Toast.makeText(ShareFacebook.this, "Connected to Facebook",
                             Toast.LENGTH_SHORT).show();
          }
          }
   };

   /**
    *
    *
    */
   private Handler mHandler = new Handler() {
      @Override
      public void handleMessage(final Message msg) {
            mProgress.dismiss();

            if (msg.what == 1) {
                 Toast.makeText(ShareFacebook.this, "Facebook logout failed",
                          Toast.LENGTH_SHORT).show();
            } else {
               //mFacebookBtn.setChecked(false);
               //mFacebookBtn.setText("  Facebook (Not connected)");
               //mFacebookBtn.setTextColor(Color.GRAY);

               Toast.makeText(ShareFacebook.this, "LogOut",
                    Toast.LENGTH_SHORT).show();
             }
            }
      };

}
