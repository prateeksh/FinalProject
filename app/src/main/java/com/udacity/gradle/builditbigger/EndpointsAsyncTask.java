package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.prateek.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Prateek on 10-11-2016.
 */

public class EndpointsAsyncTask extends AsyncTask <Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;



    @Override
    protected String doInBackground(Pair<Context,String>... params){
     if(myApiService == null){
         MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                 new AndroidJsonFactory(), null)
                 .setRootUrl("http://127.0.0.1:8092/_ah/api")
                 .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                     @Override
                     public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                         request.setDisableGZipContent(true);
                     }
                 });
         myApiService = builder.build();
     }
        context = params[0].first;
        String name = params[0].second;

        try{
            return myApiService.sayHi(name).execute().getData();
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
