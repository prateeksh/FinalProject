package com.example.prateek.androidlibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    public static String JOKE_KEY = "joke_key";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.joke_textView);

        ColorDrawable cd = new ColorDrawable(getActivity().getResources().getColor(R.color.bg));
        getActivity().getWindow().setBackgroundDrawable(cd);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_joke);
        Glide
                .with(this)
                .load(R.drawable.lol)
                .asGif()
                .crossFade()
                .into(imageView);

        Intent intent = getActivity().getIntent();
        if(intent != null && intent.hasExtra(JOKE_KEY)){
            String joke = intent.getStringExtra(JOKE_KEY);
            textView.setText(joke);
        }
        else {
            textView.setText("Error either in intent or Joke");
        }
        return rootView;
    }

    public static Intent getJokeIntent(Context context, String joke){
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE_KEY, joke);
        return intent;
    }

}
