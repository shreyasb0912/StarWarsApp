package com.shreyasbhondve.starwarsapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shreyasbhondve.starwarsapp.MyApplication;
import com.shreyasbhondve.starwarsapp.R;
import com.shreyasbhondve.starwarsapp.adapter.RecyclerViewAdapter;
import com.shreyasbhondve.starwarsapp.di.component.ApplicationComponent;
import com.shreyasbhondve.starwarsapp.di.component.DaggerMainActivityComponent;
import com.shreyasbhondve.starwarsapp.di.component.MainActivityComponent;
import com.shreyasbhondve.starwarsapp.di.module.MainActivityContextModule;
import com.shreyasbhondve.starwarsapp.di.qualifier.ActivityContext;
import com.shreyasbhondve.starwarsapp.di.qualifier.ApplicationContext;
import com.shreyasbhondve.starwarsapp.pojo.APIResponse;
import com.shreyasbhondve.starwarsapp.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Callback;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener, InternetConnectivityReceiver.ConnectivityReceiverListener {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    private ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        // Manually checking internet connection
        checkConnection();

    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = InternetConnectivityReceiver.isConnected();
        showSnack(isConnected);

        //If internet is present call API to load the data
        if ((isConnected)) {
            callAPI();
        }
    }

    private void callAPI() {

        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getCharaters("https://swapi.co/api/people/").enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse> call, retrofit2.Response<APIResponse> response) {
                progressBar.setVisibility(View.GONE);
                List<APIResponse.StarWarCharacter> starWarCharacterList = response.body().results;
                populateRecyclerView(starWarCharacterList);
            }

            @Override
            public void onFailure(retrofit2.Call<APIResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.v("APIResponse", "" + t.getMessage());
            }
        });

    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.recyclerView), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }


    /**
     * Function to populate the StarWars characters data into Recyclerview
     *
     * @param starWarCharacterList
     */
    private void populateRecyclerView(List<APIResponse.StarWarCharacter> starWarCharacterList) {
        recyclerViewAdapter.setData(starWarCharacterList);
    }


    /**
     * Function to call the details activity
     * @param starWarCharacter
     */
    @Override
    public void launchIntent(APIResponse.StarWarCharacter starWarCharacter) {

        Data data = new Data(
                starWarCharacter.name,
                starWarCharacter.height,
                starWarCharacter.mass,
                starWarCharacter.created
        );
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("data", data));
    }


    /**
     * Callback function called when network state is changed
     * @param isConnected
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
