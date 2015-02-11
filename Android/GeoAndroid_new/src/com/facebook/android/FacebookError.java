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

/**
 * Encapsulation of a Facebook Error: a Facebook request that could not be
 * fulfilled.
 *
 * @author ssoneff@facebook.com
 */
public class FacebookError extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private int mErrorCode = 0;
    /**
     *
     */
    private String mErrorType;

    /**
     * @param message
     */
    public FacebookError(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param type
     * @param code
     */
    public FacebookError(final String message, final String type,
            final int code) {
        super(message);
        mErrorType = type;
        mErrorCode = code;
    }

    /**
     * @return
     */
    public final int getErrorCode() {
        return mErrorCode;
    }

    /**
     * @return
     */
    public final String getErrorType() {
        return mErrorType;
    }

}
