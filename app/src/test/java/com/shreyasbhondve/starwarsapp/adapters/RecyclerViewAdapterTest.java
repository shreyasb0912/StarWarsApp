package com.shreyasbhondve.starwarsapp.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shreyasbhondve.starwarsapp.BuildConfig;
import com.shreyasbhondve.starwarsapp.adapter.RecyclerViewAdapter;
import com.shreyasbhondve.starwarsapp.pojo.APIResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class RecyclerViewAdapterTest {
    private Context context;

    @Before
    public void setup(){
        context = RuntimeEnvironment.application;
    }


    @Test
    public void recyclerViewAdapterItem(){
        List<APIResponse.StarWarCharacter> starWarCharacterList = Arrays.asList(
                new APIResponse.StarWarCharacter("Luke Skywalker","172","77","2014-12-20T21:17:50.309000Z")
        );

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(starWarCharacterList);
        RecyclerView rvParent = new RecyclerView(context);
        rvParent.setLayoutManager(new LinearLayoutManager(context));


        //Run test
        RecyclerViewAdapter.ViewHolder viewHolder =
                adapter.onCreateViewHolder(rvParent, 0);

        adapter.onBindViewHolder(viewHolder, 0);

        //adapter.onBindViewHolder(viewHolder, 1);

        // JUnit Assertion
        assertEquals("Luke Skywalker", viewHolder.txtName.getText().toString());

        assertTrue(adapter.getItemCount() == 1);


    }




}
