package com.charles.gads2020leaderboard.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private static final String POST_BASE_API = " https://docs.google.com/forms/d/e/";

    private static Retrofit.Builder apiBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit learnerRetrofit = apiBuilder.build();

    public static <S> S buildApi(Class<S> apiType){
        return learnerRetrofit.create(apiType);
    }

    //Post
    private static Retrofit.Builder postApiBuilder = new Retrofit.Builder()
            .baseUrl(POST_BASE_API)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit postRetrofit = postApiBuilder.build();

    public static <Post> Post submitPost(Class<Post> submissionType){
        return postRetrofit.create(submissionType);
    }
}
