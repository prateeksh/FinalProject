package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
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

    EndpointsAsyncTask.EndpointResponseInterface responseInterface;
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



}
