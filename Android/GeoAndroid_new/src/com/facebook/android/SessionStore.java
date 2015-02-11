/*
 * Copyright 2010 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.android;

import com.facebook.android.Facebook;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Ming
 *
 */
public class SessionStore {

    /**
     *
     */
    private static final String TOKEN = "access_token";
    /**
     *
     */
    private static final String EXPIRES = "expires_in";
    /**
     *
     */
    private static final String NAME = "fb_name";
    /**
     *
     */
    private static final String KEY = "facebook-session";

    /**
     * @param session
     * @param context
     * @return
     */
    public static boolean save(final Facebook session, final Context context) {
        Editor editor =
            context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putString(TOKEN, session.getAccessToken());
        editor.putLong(EXPIRES, session.getAccessExpires());
        return editor.commit();
    }

    /**
     * @param session
     * @param context
     * @return
     */
    public static boolean restore(final Facebook session,
            final Context context) {
        SharedPreferences savedSession =
            context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        session.setAccessToken(savedSession.getString(TOKEN, null));
        session.setAccessExpires(savedSession.getLong(EXPIRES, 0));
        return session.isSessionValid();
    }

    /**
     * @param name
     * @param context
     * @return
     */
    public static boolean saveName(final String name, final Context context) {
        Editor editor =
            context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putString(NAME, name);

        return editor.commit();
    }

    /**
     * @param context
     * @return
     */
    public static String getName(final Context context) {
        SharedPreferences savedSession =
            context.getSharedPreferences(KEY, Context.MODE_PRIVATE);

        return savedSession.getString(NAME, "Unknown");
    }

    /**
     * @param context
     */
    public static void clear(final Context context) {
        Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

}
