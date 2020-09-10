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
import com.charles.gads2020leaderboard.adapters.TopIQSkillAdapter;
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
public class SkillIQLeadersFragment extends Fragment {

    private View mSkillFragment;
    private List<LearnerInfo> mTopScorerInfoList;
    private RecyclerView mTopScoreRecyclerView;
    private TopIQSkillAdapter mTopIQSkillAdapter;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillIQLeadersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillIQLeadersFragment newInstance(String param1, String param2) {
        SkillIQLeadersFragment fragment = new SkillIQLeadersFragment();
        Bundle args = new Bundle();
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
        mSkillFragment = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);

        final Context context = inflater.getContext();

        mTopScoreRecyclerView = mSkillFragment.findViewById(R.id.iqskill_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mTopScoreRecyclerView.setLayoutManager(layoutManager);

        mTopIQSkillAdapter = new TopIQSkillAdapter();

        getAllLearners();

        return mSkillFragment;
    }

    private void getAllLearners() {
        LearnersApi learnersApi = ApiBuilder.buildApi(LearnersApi.class);
        Call<List<LearnerInfo>> call = learnersApi.getTopScorersApi();

        call.enqueue(new Callback<List<LearnerInfo>>() {
            @Override
            public void onResponse(Call<List<LearnerInfo>> call, Response<List<LearnerInfo>> response) {
                if (response.isSuccessful()){
                    List<LearnerInfo> learnerInfoList = response.body();
                    mTopIQSkillAdapter.setLearnersLeaderData(learnerInfoList);
                    mTopScoreRecyclerView.setAdapter(mTopIQSkillAdapter);
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