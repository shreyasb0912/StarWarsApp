package com.shreyasbhondve.starwarsapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {

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
        //GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

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
                Log.v("APIResponse",""+t.getMessage());
            }
        });



    }


    /**
     * Function to populate the Products data into local database
     * @param starWarCharacterList
     */
    private void populateRecyclerView(List<APIResponse.StarWarCharacter> starWarCharacterList){
        recyclerViewAdapter.setData(starWarCharacterList);
    }




    @Override
    public void launchIntent(APIResponse.StarWarCharacter starWarCharacter) {
        //Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        //List<ProductCatalog.Category.Product.Variants> variantsList = dataManager.getVariants("1");
        Data data = new Data(
                starWarCharacter.name,
                starWarCharacter.height,
                starWarCharacter.mass,
                starWarCharacter.created
        );
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("data",data));
    }



}
