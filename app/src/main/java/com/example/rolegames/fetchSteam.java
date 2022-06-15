package com.example.rolegames;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class fetchSteam extends AsyncTaskLoader<String> {
    private String mQueryString;
    fetchSteam(Context context, String queryString){
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground(){
        return networkUtils.searchApp(mQueryString);
    }
}
