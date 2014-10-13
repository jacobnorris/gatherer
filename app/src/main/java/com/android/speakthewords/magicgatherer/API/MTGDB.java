package com.android.speakthewords.magicgatherer.API;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import mtgdb_java_api.src.info.mtgdb.api.Db;
import mtgdb_java_api.src.info.mtgdb.api.Card;

/**
 * Created by jacobnorris on 7/15/14.
 *
 * This whole class could probably be generalized.
 */
public class MTGDB {
    public void search(String searchTerms, TextView textView) {
        MTGDBSearchTask task = new MTGDBSearchTask(textView);
        task.execute(searchTerms);
    }


    class MTGDBSearchTask extends AsyncTask<String, Void, ArrayList<Card>> {
        private String searchTerms;
        private final WeakReference<TextView> textViewReference;

        public MTGDBSearchTask(TextView textView) {
            textViewReference = new WeakReference<TextView>(textView);
        }

        @Override
        protected ArrayList<Card> doInBackground(String... params) {
            return Db.searchCards(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Card> searchResults) {
            if (isCancelled()) {
                searchResults = null;
            }

            if (textViewReference != null) {
                TextView textView = textViewReference.get();
                if (textView != null) {
                    String searchResultsStr = "";

                    for(int i = 0; i < searchResults.size(); i++) {
                        searchResultsStr = searchResultsStr + searchResults.get(i) + ", ";
                    }

                    textView.setText(searchResultsStr);
                }
            }
        }
    }
}