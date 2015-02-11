package com.facebook.android;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import android.util.Log;

import com.facebook.android.AsyncFacebookRunner.RequestListener;

/**
 * Skeleton base class for RequestListeners, providing default error
 * handling. Applications should handle these error conditions.
 *
 */
public abstract class BaseRequestListener implements RequestListener {

     /**
     *@param e error
      */
    public final void onFacebookError(final FacebookError e) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    /**
     *@param e exception
     */
    public final void onFileNotFoundException(final FileNotFoundException e) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    /**
     *@param e exception
     */
    public final void onIOException(final IOException e) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    /**
     *@param e exception
     */
    public final void onMalformedURLException(final MalformedURLException e) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

}
