package com.example.prateek.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.joke_textView);

        Intent intent = getActivity().getIntent();
        if(intent != null && intent.hasExtra("Display")){
            String joke = intent.getStringExtra("Display");
            textView.setText(joke);
        }
        else {
            textView.setText("Error either in intent or Joke");
        }
        /*Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JokeActivity.JOKE_KEY);
        TextView textView = (TextView) rootView.findViewById(R.id.joke_textView);
        if(joke != null && joke.length() != 0){
            textView.setText(joke);
        }*/
        return rootView;
    }
}
