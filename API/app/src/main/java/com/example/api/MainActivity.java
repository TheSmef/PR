package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
TextView txtId;
TextView txtAt;
TextView txtUrl;
TextView txtUpd;
TextView txtIcon;
TextView txtValue;
Button btn;
    String Id = "";
    String At= "";
    String Url= "";
    String Upd= "";
    String Icon= "";
    String Value= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtAt = findViewById(R.id.txtAt);
        txtValue = findViewById(R.id.txtValue);
        txtUpd = findViewById(R.id.txtUpd);
        txtIcon = findViewById(R.id.txtIcon);
        txtUrl = findViewById(R.id.txtUrl);

        btn=findViewById(R.id.btnClick);

        btn.setOnClickListener(view->new JokeLoader().execute());
    }
    private class JokeLoader extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            String jsonString = getJson("https://api.chucknorris.io/jokes/random"); //так как с моим api не заработало
            try
            {
                JSONObject jsonObject=new JSONObject(jsonString);
                Id=jsonObject.getString("id");
                At= jsonObject.getString("created_at");
                Url= jsonObject.getString("url");
                Upd= jsonObject.getString("updated_at");
                Icon= jsonObject.getString("icon_url");
                Value= jsonObject.getString("value");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Id = "";
            At= "";
            Url= "";
            Upd= "";
            Icon= "";
            Value= "";
            txtId.setText("Loading...");
            txtAt.setText("Loading...");
            txtIcon.setText("Loading...");
            txtUpd.setText("Loading...");
            txtUrl.setText("Loading...");
            txtValue.setText("Loading...");
        }
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            //if(txtId.equals(""))
            {
                txtId.setText(Id);
                txtAt.setText(At);
                txtIcon.setText(Icon);
                txtUpd.setText(Upd);
                txtUrl.setText(Url);
                txtValue.setText(Value);
            }
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