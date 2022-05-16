package com.example.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.squareup.picasso.Picasso;

import javax.security.auth.login.LoginException;


public class WidgetProvider extends android.appwidget.AppWidgetProvider{
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
    String joke = "";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            Intent active = new Intent(context, WidgetProvider.class);
            active.setAction(ACTION_WIDGET_RECEIVER);
            PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
            remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);
            Log.e("Tag", appWidgetIds.toString());
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (ACTION_WIDGET_RECEIVER.equals(action)) {
            String pastjoke = "";
            new JokeLoader().execute();
            while (joke == pastjoke){ }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), WidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            remoteViews.setTextViewText(R.id.textJoke, joke);
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
            joke = "";
        }
        super.onReceive(context, intent);
    }

    private class JokeLoader extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            String jsonString = getJson("https://api.chucknorris.io/jokes/random");
            try
            {
                JSONObject jsonObject=new JSONObject(jsonString);
                joke=jsonObject.getString("value");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

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

