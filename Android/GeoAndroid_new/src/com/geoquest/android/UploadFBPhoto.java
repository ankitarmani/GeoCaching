package com.geoquest.android;

import java.io.ByteArrayOutputStream;
import java.io.File;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.BaseRequestListener;
import com.facebook.android.Facebook;
import com.facebook.android.SessionStore;

/**
 * @author Fani 
 *
 */
public class UploadFBPhoto extends Activity {

    /**
    *
    */
   private Facebook mFacebook;
   /**
    *
    **/
   private CheckBox mFacebookCb;
   /**
    *
    */
   private ProgressDialog mProgress;
   /**
    *
    */
   private Handler mRunOnUi = new Handler();
   /**
    * .
    */
	private static final int IMAGE_LENGTH = 100;
   /**
    *
    */
   private static final String APP_ID = "371672369554557";
   /** .
   * this is global variable of Bitmap
   */
   private static String path = "/sdcard/testfull.jpg";
   /** .
   *setting path
   */
   private static File imageFile = new File(path);;
   /** .
   *convert image into bitmap
   */
   private static Bitmap bitmap = BitmapFactory.decodeFile(
           imageFile.getAbsolutePath());

   @Override
   protected final void onCreate(final Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       setContentView(R.layout.post);

       final EditText reviewEdit = (EditText) findViewById(R.id.revieew);
       mFacebookCb = (CheckBox) findViewById(R.id.cb_facebook);

       mProgress = new ProgressDialog(this);

       mFacebook = new Facebook(APP_ID);

     //  final ImageView imgVi = (ImageView) findViewById(R.id.ImageView01);
    //   Bitmap storeImg = null;

           ImageView jpgView = (ImageView) findViewById(R.id.ImageView01);
           jpgView.setImageBitmap(bitmap);

 /*          URL url = new URL("http://twibbon.s3.amazonaws.com/2011/75/"
                 +  "f0319869-6e2b-4dbb-b9b7-8be12aa2d2a9_Twitter.png");
           URLConnection connection = url.openConnection();
           HttpURLConnection hCon = (HttpURLConnection) connection;
           int resCode = hCon.getResponseCode();
           System.out.println("Responce Code is = " + resCode);

           if (resCode == HttpURLConnection.HTTP_OK) {
               InputStream ins = ((URLConnection) hCon).getInputStream();
               storeImg = BitmapFactory.decodeStream(ins);
              }*/
          /*catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
          }*/

   //    imgVi.setImageBitmap(storeImg);

       SessionStore.restore(mFacebook, this);

       if (mFacebook.isSessionValid()) {
           mFacebookCb.setChecked(true);

           String name = SessionStore.getName(this);

           if (name.equals("")) {
               name = "Unknown";
           }
           //name = (name.equals("")) ? "Unknown" : name;

           mFacebookCb.setText("  Facebook  (" + name + ")");
       }

       ((Button) findViewById(R.id.button1)).setOnClickListener(
               new OnClickListener() {
           @Override
           public void onClick(final View v) {
           String review = reviewEdit.getText().toString();

           if (review.equals("")) {
              return;
           }

           if (mFacebookCb.isChecked()) {
               postToFacebook(review);
           }
         }
   });
}

   /**
    *
    * @param review string
    */
      private void postToFacebook(final String review) {
       mProgress.setMessage("Posting ...");
       mProgress.show();

       byte[] data = null;

       Bitmap bi = BitmapFactory.decodeFile(path);
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       bi.compress(Bitmap.CompressFormat.JPEG, IMAGE_LENGTH, baos);
       data = baos.toByteArray();

      AsyncFacebookRunner mAsyncFbRunner = new AsyncFacebookRunner(mFacebook);

       Bundle params = new Bundle();

       //ByteArrayOutputStream bs = new ByteArrayOutputStream();
       // convert picture to byte array

       //bitmap.compress(CompressFormat.JPEG, 0 /*ignored for PNG*/, bs);
       //byte[] bitmapdata = bs.toByteArray();

       params.putString("name", review);
       //params.putString("name", "GeoCaching");
       params.putString("description",
              "This is the test for geocaching facebook application");
       params.putByteArray("picture", data);

       mAsyncFbRunner.request("me/photos", params,
             "POST", new WallPostListener());
   }

      /**
       *
       * @author ankit
       *
       */
      private final class WallPostListener extends BaseRequestListener {
      /**
      *@param response string
      */
       public void onComplete(final String response) {
       mRunOnUi.post(new Runnable() {
               @Override
               public void run() {
                   mProgress.cancel();
                   Intent i = new Intent(getApplicationContext(),
                           MainScreenActivity.class);
                   startActivity(i);
                   finish();

                Toast.makeText(UploadFBPhoto.this, "Posted to Facebook",
                           Toast.LENGTH_SHORT).show();
              }
          });
       }
   }
}
