package com.charles.gads2020leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.charles.gads2020leaderboard.api.ApiBuilder;
import com.charles.gads2020leaderboard.api.LearnersApi;
import com.charles.gads2020leaderboard.modal.ProjectSubmissionInfo;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitProjectActivity extends AppCompatActivity {

    private static final int DIALOG_TIMEOUT = 2000;
    private ImageButton mBackArrowButton;
    private Button mSubmit;
    private EditText mEditFirstName;
    private EditText mEditLastName;
    private EditText mEditEmail;
    private EditText mEditGithubLink;
    private AlertDialog.Builder mAlertBuilder;
    private View mView;
    private ApiBuilder mApiBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        mBackArrowButton = findViewById(R.id.btn_back);
        mSubmit = findViewById(R.id.btn_submit_project);
        mEditFirstName = findViewById(R.id.edt_first_name);
        mEditLastName = findViewById(R.id.edt_last_name);
        mEditEmail = findViewById(R.id.edt_email_address);
        mEditGithubLink = findViewById(R.id.edt_github_link);

        mApiBuilder = new ApiBuilder();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String fname = mEditFirstName.getText().toString().trim();
                    String lname = mEditLastName.getText().toString().trim();
                    String emailaddress = mEditEmail.getText().toString();
                    String git = mEditGithubLink.getText().toString();

                    if (mEditFirstName.length() == 0){

                            mEditFirstName.setError("Required");
                    }
                    else if (lname.length() == 0){
                        mEditLastName.setError("Required");
                    }

                    else  if (emailaddress.length() == 0){
                        mEditEmail.setError("Required");
                    }

                    else if (git.length() ==0){
                        mEditGithubLink.setError("Required");
                    }

                    else{
                        subimtProject(fname, lname, emailaddress, git);
                    }
            }
        });

        mBackArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousActivity();
            }
        });
    }

    private void subimtProject(String fname, String lname, String emailaddress, String git) {

        LearnersApi submitProject = ApiBuilder.submitPost(LearnersApi.class);
        Call<ProjectSubmissionInfo> projectSubmissionRequest = submitProject.submitProject(fname, lname, emailaddress, git);

        projectSubmissionRequest.enqueue(new Callback<ProjectSubmissionInfo>() {
            @Override
            public void onResponse(Call<ProjectSubmissionInfo> call, Response<ProjectSubmissionInfo> response) {
                if (response.isSuccessful()){
                    successfulDialog();
                    Toast.makeText(SubmitProjectActivity.this, "Successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProjectSubmissionInfo> call, Throwable t) {
                unsuccessfulDialog();
                Toast.makeText(SubmitProjectActivity.this, "Faield to Submit", Toast.LENGTH_LONG).show();
            }
        });

        clear();
    }

    private void clear() {
        mEditFirstName.setText("");
        mEditLastName.setText("");
        mEditEmail.setText("");
        mEditGithubLink.setText("");
    }

    //method for success in posting the project
    private void successfulDialog() {
        mAlertBuilder = new AlertDialog.Builder(this);
        mView = LayoutInflater.from(this).inflate(R.layout.layout_successful_dialog,
                (ConstraintLayout)findViewById(R.id.successfulDialog)
        );
        mAlertBuilder.setView(mView);

        final AlertDialog alertDialog = mAlertBuilder.create();
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
            }
        }, DIALOG_TIMEOUT);
    }

    //method for unsuccessful posting of the project
    private void unsuccessfulDialog() {
        mAlertBuilder = new AlertDialog.Builder(this);
        mView = LayoutInflater.from(this).inflate(R.layout.layout_unsuccessful_dialog,
                (CardView)findViewById(R.id.unsuccessfulDialog)
        );
        mAlertBuilder.setView(mView);

        final AlertDialog unsuccessfulAlertDialog = mAlertBuilder.create();
        unsuccessfulAlertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                unsuccessfulAlertDialog.dismiss();
            }
        }, DIALOG_TIMEOUT);
    }

    private void previousActivity() {
        Intent intent = new Intent(this, Gads2020Activity.class);
        startActivity(intent);
    }
}
