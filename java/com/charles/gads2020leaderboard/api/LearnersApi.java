package com.charles.gads2020leaderboard.api;


import com.charles.gads2020leaderboard.modal.LearnerInfo;
import com.charles.gads2020leaderboard.modal.ProjectSubmissionInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LearnersApi {
    @GET("api/hours")
    Call<List<LearnerInfo>> getTopLearnersApi();

    @GET("api/skilliq")
    Call<List<LearnerInfo>> getTopScorersApi();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ProjectSubmissionInfo> submitProject(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.284483984") String githubUrl
    );
}
