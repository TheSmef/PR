package com.example.androidtv;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.Random;

import com.squareup.picasso.Picasso;


/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {
    ImageView txtUrl;
    Button btn;
    String Url= "";
    final Random random = new Random();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUrl = findViewById(R.id.txtUrl);

        btn=findViewById(R.id.btnClick);

        btn.setOnClickListener(view->new JokeLoader().execute());
    }
    private class JokeLoader extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            String jsonString = getJson("https://dog.ceo/api/breeds/image/random");
            try
            {
                JSONObject jsonObject=new JSONObject(jsonString);
                Url=jsonObject.getString("message");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Picasso.with(getApplicationContext())
                    .load(Url)
                    .into(txtUrl);
        }
    }
    private String getJson(String link)
    {
        String data="";
        try
        {
            URL url=new URL(link);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK)
            {
                BufferedReader r =new BufferedReader(new InputStreamReader((urlConnection.getInputStream()),"utf-8"));
                data=r.readLine();
                urlConnection.disconnect();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return data;
    }

}

