package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Created by Prateek on 28-11-2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestString {


    @SmallTest
    public void addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2);
    }

    EndpointsAsyncTask.EndpointResponseInterface responseInterface = null;


    Context mContext;
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){
        mContext = new RenamingDelegatingContext(InstrumentationRegistry.getContext(),"test_");
    }

    @Test
    public void returnStringValid() throws Exception{
        String joke = "";
        try {
            EndpointsAsyncTask task = new EndpointsAsyncTask(responseInterface);
           task.execute();
            joke = task.get(15, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Assert.assertNotNull("not null",joke);
    }


   /* private final CountDownLatch mSignal = new CountDownLatch(1);

    @Test
    public void testJokeRetriever() throws Exception{
        new EndpointsAsyncTask(this).execute();
        try {
            boolean success = mSignal.await(15, TimeUnit.SECONDS);
            if (!success) {
                Assert.fail("Test timed out, make sure the server is actually running.");
            }
        } catch (InterruptedException e) {
            Assert.fail();
        }
    }

    @Override
    public void onResponse(boolean isSuccess, String result) {
        Assert.assertTrue(isSuccess && result != null && result.length() > 0);
        mSignal.countDown();
    }*/
}

