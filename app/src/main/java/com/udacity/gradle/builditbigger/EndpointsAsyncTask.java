package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.prateek.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Prateek on 10-11-2016.
 */

public class EndpointsAsyncTask extends AsyncTask <String, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private boolean isSuccess;
    private EndpointResponseInterface responseInterface;

    EndpointsAsyncTask(EndpointResponseInterface responseInterface){
        this.responseInterface = responseInterface;
    }

    @Override
    protected String doInBackground(String... params){
     if(myApiService == null){
         MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                 new AndroidJsonFactory(), null)
                 .setRootUrl("http://192.168.1.4:1234/_ah/api")
                 .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                     @Override
                     public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                         request.setDisableGZipContent(true);
                     }
                 });
         myApiService = builder.build();
     }

        try{
            isSuccess = true;
            return myApiService.displayJoke().execute().getJoke();
        }catch (IOException e){
            isSuccess = false;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result){
        responseInterface.onResponse(isSuccess, result);
    }


    public interface EndpointResponseInterface{
        void onResponse(boolean isSuccess, String result);
    }
}
