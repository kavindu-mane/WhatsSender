package com.hexa2zero.whatssender;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * Implementation of App Widget functionality.
 */
public class GetNumbetWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context , AppWidgetManager appWidgetManager , int[] appWidgetIds) {

        for (int id : appWidgetIds) {

            Intent intent = new Intent(context , PopUpActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context , 0 , intent , 0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName() , R.layout.get_numbet_widget);
            remoteViews.setOnClickPendingIntent(R.id.widget_btn , pendingIntent);
            appWidgetManager.updateAppWidget(id , remoteViews);

        }
    }

}