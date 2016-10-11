package com.oleksandr.cookingassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public SearchView searchView;
    public FloatingActionButton fab;
    public ProgressBar progressBar;
    public String receivedContent = "nothing yet";
    public String taskMSG = "";
    public List<MyTask> tasks;
    public List<Recipe> recipeList;
    private String query;
    final String CSE_URL ="https://www.googleapis.com/customsearch/v1";
    final String CX_CODE ="?cx=011945877123661636102:yklf7-t8kqo";
    final String API_KEY ="&key=AIzaSyCmqLJLcjFGBuDzZsmUWc8OEUnDqwknNmc";
    final String Q_PARAM ="&q=";
    final String SUGGEST =" recipe meal";

    //"Cooking Assistant Google Custom Search Engine uses http GET query sample:"
    //https://www.googleapis.com/customsearch/v1?cx=011945877123661636102:yklf7-t8kqo&key=AIzaSyCmqLJLcjFGBuDzZsmUWc8OEUnDqwknNmc&q=SEARCH_ITEM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        searchView = (SearchView)findViewById(R.id.searchView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        tasks = new ArrayList<>();

        //****** USE appicon in action bar
//        ActionBar abar = getSupportActionBar();
//        abar.setLogo(R.drawable.cooker_app_icon_a);
//        abar.setHomeButtonEnabled(true);
//        abar.setDisplayHomeAsUpEnabled(true);
//        abar.setDisplayUseLogoEnabled(true);
//        abar.setDisplayShowHomeEnabled(true);


        //******* START view ********
        Bitmap b1= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.salad);
        Bitmap b2= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.crepe);
        Bitmap b3= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.gulash);
        Bitmap b4= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.omlette);
        Recipe r0 = new Recipe(); r0.setTitle("SEARCH SUGGESTIONS:");
        Recipe r1 = new Recipe(); r1.setTitle("Pepper & noodle salad"); r1.setBitmap(b1);
        Recipe r2 = new Recipe(); r2.setTitle("Crêpes with banana"); r2.setBitmap(b2);
        Recipe r3 = new Recipe(); r3.setTitle("Goulash soup"); r3.setBitmap(b3);
        Recipe r4 = new Recipe(); r4.setTitle("Omelette aux fines herbes"); r4.setBitmap(b4);

        recipeList = new ArrayList<>();
        recipeList.add(r0);
        recipeList.add(r1);
        recipeList.add(r2);
        recipeList.add(r3);
        recipeList.add(r4);

        updateDisplay();

        //*******ListView listener*****

        //final Intent displayWeb = new Intent(this,WebViewActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                String url = "https://www.google.com/search?q=";
                Recipe r = (Recipe)listView.getAdapter().getItem(position);

                if (r.getLink()!=null) url = r.getLink();
                else if(r.getTitle()!=null) {
                    try {
                        url = url + URLEncoder.encode(r.getTitle(),"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                Intent displayWeb = new Intent(MainActivity.this, WebViewActivity.class);
                displayWeb.putExtra("link",url);
                startActivity(displayWeb);
            }
        });


        //******* searchVIEW USE *********
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String inputStr) {

                if (isOnline()) {
                    if (inputStr.length() != 0) {
                        try { query = CSE_URL+CX_CODE+API_KEY+Q_PARAM+ URLEncoder.encode(inputStr+SUGGEST,"UTF-8");
                        } catch (UnsupportedEncodingException e) {e.printStackTrace();}
                        requestData(query);
                        searchView.clearFocus();
                        return true;
                    }else{
                        Snackbar.make(searchView, "nothing to search... try again...", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(searchView, "sorry, no network connection...", Snackbar.LENGTH_LONG).show();
                }
                    searchView.clearFocus();
                    return false;
            }
        });

    //floating abtn:
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void requestData(String query) {
        MyTask task = new MyTask();
        task.execute(query);
    }

    /// MENU USE ************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {

            AlertDialog.Builder abuilder = new AlertDialog.Builder(this);
            abuilder.setMessage("Cooking Assistant v1. \n" +
                    " Helps to find interesting recipes");
            abuilder.setPositiveButton("OK",null);
            AlertDialog adialog = abuilder.show();

            //center text
            TextView msgView = (TextView)adialog.findViewById(android.R.id.message);
            msgView.setGravity(Gravity.CENTER);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    ////////////// ***************** functional

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void showToast(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    protected void updateDisplay() {

        RecipeAdapter ra = new RecipeAdapter(this,R.layout.item_recipe,recipeList);
        listView.setAdapter(ra);
    }

    private class MyTask extends AsyncTask<String, String, List<Recipe>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<Recipe> doInBackground(String... params) {
            receivedContent="";
            try {
                receivedContent += HttpManager.getData(params[0]);
                taskMSG="got data!";
            }catch (Exception e){
                taskMSG="exception from Http.getData()";
                e.printStackTrace();
            }
            ///PARSE JSON
            recipeList = CseJsonParser.parseJson(receivedContent);
            return recipeList;
        }

        @Override
        protected void onPostExecute(List<Recipe> resultList) {

            tasks.remove(this);
            showToast(taskMSG);

            if (tasks.size() == 0) {
                // CHECK HERE !!!
                progressBar.setVisibility(View.INVISIBLE);
            }

            if (resultList == null) {
               showToast("resulting list = null");
                return;
            }

            recipeList = resultList;
            updateDisplay();
        }

    }


}
