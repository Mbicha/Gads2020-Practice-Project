package com.charles.gads2020leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.charles.gads2020leaderboard.R;
import com.charles.gads2020leaderboard.modal.LearnerInfo;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.LearnerLeaderViewHolder> {

    List<LearnerInfo> mLearnersLeader;
    private Context mContext;

    public LearningLeadersAdapter() {
    }

    public void setLearnersLeaderData(List<LearnerInfo> learnersLeader) {
        mLearnersLeader = learnersLeader;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LearnerLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.learning_hours_cardview, parent, false);
        return new LearnerLeaderViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerLeaderViewHolder holder, int position) {
        LearnerInfo learnerInfo = mLearnersLeader.get(position);

        String learnerName = learnerInfo.getName();
        String learnerHours = String.valueOf(learnerInfo.getHours());
        String learnerCountry = learnerInfo.getCountry();

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(mLearnersLeader.get(position).getBadgeUrl())
                .into(holder.imgBadge);
        //Glide.with(mContext).load(R.drawable.top_learner).into(holder.imgBadge);
        holder.txtName.setText(learnerName);
        holder.txtHours.setText(learnerHours);
        holder.txtCountry.setText(learnerCountry);
    }

    @Override
    public int getItemCount() {
        return mLearnersLeader.size();
    }

    public class LearnerLeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtHours, txtDesc, txtCountry;
        ImageView imgBadge;
        public LearnerLeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBadge = itemView.findViewById(R.id.img_badge);
            txtName = itemView.findViewById(R.id.text_name);
            txtHours = itemView.findViewById(R.id.text_hours);
            txtDesc = itemView.findViewById(R.id.text_description);
            txtCountry = itemView.findViewById(R.id.text_country);
        }
    }
}
