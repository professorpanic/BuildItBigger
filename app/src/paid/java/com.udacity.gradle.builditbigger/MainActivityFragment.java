package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.professorpanic.builditbigger.jokebackend.myApi.MyApi;
import com.udacity.professorpanic.jokepasser.JokeActivity;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
Button jokeActivityButton;
private static MyApi myApiService;
private String jokeString;
private ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.display_fragment, container, false);

        //AdView mAdView = (AdView) root.findViewById(R.id.adView);
        spinner = (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(spinner.GONE);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//        mAdView.loadAd(adRequest);
        jokeActivityButton = (Button) root.findViewById(R.id.jokeButton);
        jokeActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(spinner.VISIBLE);
                new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "joke"));


            }
        });
        return root;
    }

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private Context context;


        @Override
        protected String doInBackground(Pair<Context, String>... params) {

            if (!Utility.isNetworkAvailable(getActivity()))
            {
                return getString(R.string.no_connection);
            }
            
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://builditbigger-1137.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }


            context = params[0].first;
            String name = params[0].second;
            try {

                return myApiService.sayHi(name).execute().getData();

            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (spinner != null)
            {
                spinner.setVisibility(spinner.GONE);
            }
            jokeString = result;

            //these if checks are here just to make it easier to test this class. There has to be a better way to have handled this.
            if (getActivity()!= null)
            {
                Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
                jokeIntent.putExtra(JokeActivity.JOKE_KEY, jokeString);

                startActivity(jokeIntent);
            }
        }
    }
}
