package com.facebook.android;

import com.facebook.android.Facebook.DialogListener;

/**
 * Skeleton base class for RequestListeners, providing default error
 * handling. Applications should handle these error conditions.
 *
 */
public abstract class BaseDialogListener implements DialogListener {

    /**
    *@param e error
     */
    public final void onFacebookError(final FacebookError e) {
        e.printStackTrace();
    }

    /**
     *@param e dialogerror
     */
    public final void onError(final DialogError e) {
        e.printStackTrace();
    }

   /** .
   *Cancel event
   */
    public void onCancel() {
    }

}
