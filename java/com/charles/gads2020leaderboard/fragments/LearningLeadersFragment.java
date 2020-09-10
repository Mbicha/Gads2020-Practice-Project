package com.charles.gads2020leaderboard.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charles.gads2020leaderboard.R;
import com.charles.gads2020leaderboard.adapters.LearningLeadersAdapter;
import com.charles.gads2020leaderboard.api.ApiBuilder;
import com.charles.gads2020leaderboard.api.LearnersApi;
import com.charles.gads2020leaderboard.modal.LearnerInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LearningLeadersFragment extends Fragment {
    private View mLearnerLeaderFrag;
    private List<LearnerInfo> mLearnerInfoList;
    private RecyclerView mLeadersRecyclerView;
    private LearningLeadersAdapter mLearningLeadersAdapter;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    public static LearningLeadersFragment newInstance() {
        LearningLeadersFragment fragment = new LearningLeadersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLearnerLeaderFrag = inflater.inflate(R.layout.fragment_learning_learders, container, false);

        final Context context = inflater.getContext();

        mLeadersRecyclerView = mLearnerLeaderFrag.findViewById(R.id.hours_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mLeadersRecyclerView.setLayoutManager(layoutManager);

        mLearningLeadersAdapter = new LearningLeadersAdapter();

        getAllLearners();


        return mLearnerLeaderFrag;
    }

    private void getAllLearners() {
        LearnersApi learnersApi = ApiBuilder.buildApi(LearnersApi.class);
        Call<List<LearnerInfo>> call = learnersApi.getTopLearnersApi();

        call.enqueue(new Callback<List<LearnerInfo>>() {
            @Override
            public void onResponse(Call<List<LearnerInfo>> call, Response<List<LearnerInfo>> response) {
                if (response.isSuccessful()){
                    List<LearnerInfo> learnerInfoList = response.body();
                    mLearningLeadersAdapter.setLearnersLeaderData(learnerInfoList);
                    mLeadersRecyclerView.setAdapter(mLearningLeadersAdapter);
                    return;
                }

            }

            @Override
            public void onFailure(Call<List<LearnerInfo>> call, Throwable t) {
                Log.e("Failure: ", t.getLocalizedMessage());
            }
        });

    }
}