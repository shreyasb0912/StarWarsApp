package com.shreyasbhondve.starwarsapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.shreyasbhondve.starwarsapp.pojo.Characters;
import com.shreyasbhondve.starwarsapp.retrofit.APIInterface;

import java.util.ArrayList;
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

    private CardView searchByCategoryCardView;

    private EditText searchEditText;

    private ProgressBar progressBar = null;

    private int REQUEST_CODE = 1;


    enum Filter{
        MOST_VIEWED,MOST_ORDERED,MOST_SHARED
    }

    Filter filter = Filter.MOST_VIEWED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchByCategoryCardView = findViewById(R.id.searchByCategory);
        searchEditText = findViewById(R.id.searchEdtTxt);
        progressBar = findViewById(R.id.progressBar);



        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getCharaters("https://swapi.co/api/people/").enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(retrofit2.Call<Characters> call, retrofit2.Response<Characters> response) {

                List<Characters.StarWarCharacter> categoryList = response.body().results;
                //new ParseData().execute(categoryList);

            }

            @Override
            public void onFailure(retrofit2.Call<Characters> call, Throwable t) {

            }
        });



    }




    /**
     * Function to parse the Categorioes data fetched from the server and populate it in the local database
     */
//    public class ParseData extends AsyncTask<List<ProductCatalog.Category>, Void, Void> {
//
//        ArrayList<ProductCatalog.Category> newCategoryList = new ArrayList<>();
//        ArrayList<ProductCatalog.Category.Product> newProductList = new ArrayList<>();
//        ArrayList<ProductCatalog.Category.Product.Variants> newVariantsList = new ArrayList<>();
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(List<ProductCatalog.Category>... categoryLists) {
//
//            List<ProductCatalog.Category> categoryList = categoryLists[0];
//
//            for (int i = 0; i < categoryList.size(); i++) {
//                ProductCatalog.Category newCategory = new ProductCatalog.Category();
//                ProductCatalog.Category category = categoryList.get(i);
//                String cat_id = category.getId();
//                newCategory.setId(cat_id);
//                newCategory.setName(category.getName());
//
//                List<ProductCatalog.Category.Product> productList = category.getProducts();
//                for (int j = 0; j < productList.size(); j++) {
//                    ProductCatalog.Category.Product newProduct = new ProductCatalog.Category.Product();
//                    ProductCatalog.Category.Product product = productList.get(j);
//                    String prod_id = product.getId();
//                    newProduct.setCat_id(cat_id);
//                    newProduct.setId(prod_id);
//                    newProduct.setName(product.getName());
//                    newProduct.setDate_added(product.getDate_added());
//
//                    List<ProductCatalog.Category.Product.Variants> variantsList = product.getVariants();
//                    for (int k = 0; k < variantsList.size(); k++) {
//                        ProductCatalog.Category.Product.Variants newVariants = new ProductCatalog.Category.Product.Variants();
//                        ProductCatalog.Category.Product.Variants variants = variantsList.get(k);
//                        String var_id = variants.getId();
//                        newVariants.setId(var_id);
//                        newVariants.setColor(variants.getColor());
//                        newVariants.setPrice(variants.getPrice());
//                        newVariants.setSize(variants.getSize());
//                        newVariants.setProd_id(prod_id);
//                        newVariantsList.add(newVariants);
//                    }
//
//                    newProductList.add(newProduct);
//                }
//
//                newCategoryList.add(newCategory);
//            }
//
//            insertCategories();
//            insertProducts();
//            insertVariants();
//
//            return null;
//        }
//
//        private void insertProducts() {
//
//            for (int i = 0; i < newProductList.size(); i++) {
//                try {
//                    dataManager.insertProduct(newProductList.get(i));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//
//        private void insertCategories() {
//
//            for (int i = 0; i < newCategoryList.size(); i++) {
//                Log.v("database", "calling insertCategories");
//                try {
//
//                    dataManager.insertCategory(newCategoryList.get(i));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//
//        private void insertVariants() {
//
//            for (int i = 0; i < newVariantsList.size(); i++) {
//                try {
//                    dataManager.insertVariant(newVariantsList.get(i));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            /**
//             * Function to parse the Ranking data fetched from the server and populate it in the local database
//             */
//            apiInterface.getRankings("https://stark-spire-93433.herokuapp.com/json/").enqueue(new Callback<Ranking>() {
//                @Override
//                public void onResponse(retrofit2.Call<Ranking> call, retrofit2.Response<Ranking> response) {
//
//                    List<Ranking.ProductRanking> rankingList = response.body().productRankings;
//
//                        for (int i = 0; i < rankingList.size(); i++) {
//
//                            String ranking = rankingList.get(i).getRanking();
//                            List<Ranking.ProductRanking.Products> rankingProducts = rankingList.get(i).getProducts();
//                            if(ranking.equals("Most Viewed Products")){
//
//                                for(int j=0;j<rankingProducts.size();j++){
//                                    Ranking.ProductRanking.Products product = new Ranking.ProductRanking.Products();
//                                    product.setId(rankingProducts.get(j).getId());
//                                    product.setView_count(rankingProducts.get(j).getView_count());
//                                    try {
//                                        dataManager.insertViewRankings(product);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//
//                            }
//                            else if(ranking.equals("Most OrdeRed Products")){
//
//                                for(int j=0;j<rankingProducts.size();j++){
//                                    Ranking.ProductRanking.Products product = new Ranking.ProductRanking.Products();
//                                    product.setId(rankingProducts.get(j).getId());
//                                    product.setOrder_count(rankingProducts.get(j).getOrder_count());
//                                    try {
//                                        dataManager.insertOrderRankings(product);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                            else if(ranking.equals("Most ShaRed Products")){
//
//                                for(int j=0;j<rankingProducts.size();j++){
//                                    Ranking.ProductRanking.Products product = new Ranking.ProductRanking.Products();
//                                    product.setId(rankingProducts.get(j).getId());
//                                    product.setShare_count(rankingProducts.get(j).getShare_count());
//                                    try {
//                                        dataManager.insertShareRankings(product);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                        }
//                    List<ProductCatalog.Category.Product> productList = dataManager.getProducts();
//                    populateRecyclerView(productList);
//
//                    dataManager.setFirstRun("first_run", false);
//
//                    progressBar.setVisibility(View.GONE);
//
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call<Ranking> call, Throwable t) {
//
//                }
//            });
//        }
//    }

    /**
     * Function to populate the Products data into local database
     * @param starWarCharacterList
     */
    private void populateRecyclerView(List<Characters.StarWarCharacter> starWarCharacterList){
        recyclerViewAdapter.setData(starWarCharacterList);
    }




    @Override
    public void launchIntent() {
        //Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        //List<ProductCatalog.Category.Product.Variants> variantsList = dataManager.getVariants("1");
        startActivity(new Intent(activityContext, DetailActivity.class));
    }
}
