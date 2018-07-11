package com.shreyasbhondve.starwarsapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.shreyasbhondve.starwarsapp.R;
import com.shreyasbhondve.starwarsapp.di.component.DetailActivityComponent;
import com.shreyasbhondve.starwarsapp.di.qualifier.ApplicationContext;
import com.shreyasbhondve.starwarsapp.pojo.APIResponse;
import com.shreyasbhondve.starwarsapp.retrofit.APIInterface;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    TextView textView;
    TextView selectSizeTextView;
    TextView selectColorTextView;
    TextView priceTextView;
    Button addToCartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        Data starWarCharacter = (Data) data.getParcelable("data");
        ((TextView)findViewById(R.id.name)).setText(starWarCharacter.name);
        ((TextView)findViewById(R.id.height)).setText(starWarCharacter.height + " meters");
        ((TextView)findViewById(R.id.mass)).setText(starWarCharacter.mass + " kg");
        ((TextView)findViewById(R.id.created)).setText(starWarCharacter.created);

    }


}
