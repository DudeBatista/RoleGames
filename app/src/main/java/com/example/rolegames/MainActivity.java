package com.example.rolegames;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private LinearLayout lineNoticias;
    String query = "";
    EditText txtSearch;

    TextView txtTitle1;
    TextView txtAutor1;
    TextView txtContent1;

    TextView txtTitle2;
    TextView txtAutor2;
    TextView txtContent2;

    TextView txtTitle3;
    TextView txtAutor3;
    TextView txtContent3;

    TextView txtErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle1 = (TextView) findViewById(R.id.txtNotTitle1);
        txtAutor1 = (TextView) findViewById(R.id.txtNotAutor1);
        txtContent1 = (TextView) findViewById(R.id.txtNotText1);

        txtTitle2 = (TextView) findViewById(R.id.txtNotTitle2);
        txtAutor2 = (TextView) findViewById(R.id.txtNotAutor2);
        txtContent2 = (TextView) findViewById(R.id.txtNotText2);

        txtTitle3 = (TextView) findViewById(R.id.txtNotTitle3);
        txtAutor3 = (TextView) findViewById(R.id.txtNotAutor3);
        txtContent3 = (TextView) findViewById(R.id.txtNotText3);

        txtSearch = (EditText) findViewById(R.id.txtSearch);

        txtErro = (TextView) findViewById(R.id.txtError);

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                                @Override
                                                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                                                            || actionId == EditorInfo.IME_ACTION_DONE
                                                            || event.getAction() == KeyEvent.ACTION_DOWN
                                                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        intent.putExtra("query", txtSearch.getText().toString());
                                                        intent.putExtra("enterType","itself");
                                                        startActivity(intent);
                                                        return true;
                                                    }
                                                    return false;
                                                }
                                            });
        lineNoticias = (LinearLayout) findViewById(R.id.LinearNoticias);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        String whereItComesFrom = data.getString("enterType");

        if(whereItComesFrom.equals("login")){

        }
        else if(whereItComesFrom.equals("itself")){
            query = intent.getStringExtra("query");
            Bundle queryBundle = new Bundle();
            queryBundle.putString("query", query);

            if(getSupportLoaderManager().getLoader(0) != null){
                getSupportLoaderManager().initLoader(0, null, this);
            }
            fetchSteam();
        }
    }

    public void fetchSteam(){
        ConnectivityManager checkConnection = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = null;
        if(checkConnection != null){
            net = checkConnection.getActiveNetworkInfo();
        }
        if(net != null && net.isConnected()){
            if(query.length() == 0){
                txtErro.setVisibility(View.VISIBLE);
                txtErro.setText("Erro:\n" + getString(R.string.erroQuery));
            }
            else{
                Bundle queryBundle = new Bundle();
                queryBundle.putString("query", query);
                getSupportLoaderManager().restartLoader(0, queryBundle, this);
            }
        }
        else{
            txtErro.setVisibility(View.VISIBLE);
            txtErro.setText("Erro:\n" + getString(R.string.errorNet));
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String query = "";
        if(args != null){
            query = args.getString("query");
        }
        return new fetchSteam(this, query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONObject itemsObjeto = new JSONObject(data);
                JSONObject news = itemsObjeto.getJSONObject("appnews");
                    JSONArray allNews = news.getJSONArray("newsitems");
            int i = 0;
            int appid = 0;
            String contents =  null;
            String title = null;
            String author = null;

            if(allNews.length() == 0){
                txtErro.setVisibility(View.VISIBLE);
                txtErro.setText("Erro:\n" + getString(R.string.erroInfo));
            }
            else {
                while (i < allNews.length()) {
                    JSONObject indNew = allNews.getJSONObject(i);
                    try {
                        title = indNew.getString("title");
                        contents = indNew.getString("contents");
                        author = indNew.getString("feedlabel");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (contents != null && title != null && author != null) {
                        switch (i) {
                            case 0:
                                txtTitle1.setText(title);
                                txtAutor1.setText(author);
                                txtContent1.setText(contents);
                                //WebView web = (WebView) findViewById(R.id.webView1);
                                //web.loadDataWithBaseURL("google.com",contents, "text/html", "UTF-8", null);
                                break;
                            case 1:
                                txtTitle2.setText(title);
                                txtAutor2.setText(author);
                                txtContent2.setText(contents);
                                break;
                            case 2:
                                txtTitle3.setText(title);
                                txtAutor3.setText(author);
                                txtContent3.setText(contents);
                                break;
                        }
                    } else {
                        txtErro.setVisibility(View.VISIBLE);
                        txtErro.setText("Erro:\n" + getString(R.string.erroInfo));
                    }
                    i++;
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            txtErro.setVisibility(View.VISIBLE);
            txtErro.setText("Erro:\n" + getString(R.string.erroQuery));
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {


    }
}