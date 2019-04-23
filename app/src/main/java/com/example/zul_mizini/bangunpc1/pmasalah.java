package com.example.zul_mizini.bangunpc1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class pmasalah extends AppCompatActivity {
    private String TAG = pmasalah.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String url = "https://raw.githubusercontent.com/zulkifli-mizini/kelompok2/master/pmasalah.json";

    ArrayList<HashMap<String, String>> ttList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipsandtrick2);
        pmasalah.this.setTitle("Penyelesaian Masalah");
        ttList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new Gettnt().execute();

    }

    private class Gettnt extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(pmasalah.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray tnts = jsonObj.getJSONArray("pmasalah");

                    // looping through All Contacts
                    for (int i = 0; i < tnts.length(); i++) {
                        JSONObject c = tnts.getJSONObject(i);

                        String judul = c.getString("judul");
                        String isi = c.getString("isi");



                        // tmp hash map for single contact
                        HashMap<String, String> tnt = new HashMap<>();

                        // adding each child node to HashMap key => value
                        tnt.put("judul", judul);
                        tnt.put("isi", isi);


                        // adding contact to contact list
                        ttList.add(tnt);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    pmasalah.this, ttList,
                    R.layout.list_item, new String[]{"judul", "isi"}, new int[]{R.id.judul,
                    R.id.isi});

            lv.setAdapter(adapter);
        }

    }


}
