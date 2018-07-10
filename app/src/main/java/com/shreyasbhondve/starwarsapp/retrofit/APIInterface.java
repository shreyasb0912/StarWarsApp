package com.shreyasbhondve.starwarsapp.retrofit;

import com.shreyasbhondve.starwarsapp.pojo.Characters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {

//    @GET
//    Call<ProductCatalog> getCategories(@Url String url);
//
//    @GET
//    Call<Ranking> getRankings(@Url String url);

    @GET
    Call<Characters> getCharaters(@Url String url);
}
