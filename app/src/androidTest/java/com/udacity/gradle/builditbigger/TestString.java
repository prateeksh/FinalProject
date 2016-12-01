package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Prateek on 28-11-2016.
 */

@RunWith(AndroidJUnit4.class)
public class TestString {
    EndpointsAsyncTask task;
    String mResult;

    @Test
    public void returnStringValid() throws Exception{
        task = (EndpointsAsyncTask) new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
            @Override
            public void onResponse(String result) {
                mResult = result;

                Assert.assertNotNull("Test Failed", mResult);
                Assert.assertTrue("Test Failed", mResult.length()>0);
            }
        }).execute();
    }

}

