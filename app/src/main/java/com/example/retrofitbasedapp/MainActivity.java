package com.example.retrofitbasedapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.retrofitbasedapp.Adapters.RssAdapter;
import com.example.retrofitbasedapp.Adapters.SynchronousCallAdapterFactory;
import com.example.retrofitbasedapp.Services.Rss.Item;
import com.example.retrofitbasedapp.Services.Rss.Rss;
import com.example.retrofitbasedapp.api.rssApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RssFeedTask().execute();
    }

    class RssFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;
        private RssAdapter adapter;
        private RecyclerView recycler;
        private LinearLayoutManager lManager;
        private List<Item> items ;

        @Override
        protected String doInBackground(String... urls) {
            try {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl("https://news.google.com/");
                builder.addConverterFactory(SimpleXmlConverterFactory.create());
                builder.addCallAdapterFactory(SynchronousCallAdapterFactory.create());
                Retrofit retrofit = builder.build();


                rssApi apiService = retrofit.create(rssApi.class);

                Rss rss = apiService.getRss();
                items = rss.getChannel().getItemList();
                // Obtener el Recycler


                adapter = new RssAdapter( new ArrayList<Item>(items),
                        new RssAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Item item) {
                                Intent intent= new Intent(MainActivity.this,webViewActivity.class);
                                intent.putExtra("url",item.getLink());
                                startActivity(intent);
                            }
                        });


            } catch (Exception e) {
                this.exception = e;

            }
         return null;

        }
        @Override
        protected void onPostExecute (String param) {
            recycler = findViewById(R.id.rss_recycle_view);
            recycler.setHasFixedSize(true);
            lManager = new LinearLayoutManager(getApplicationContext());
            recycler.setLayoutManager(lManager);
            recycler.setAdapter(adapter );

        }


}}

