package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedb.R;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_text, lbl_title, lbl_description, lbl_release_date;
    private String movie_id = "";
    private ImageView img_poster;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");

        lbl_title = findViewById(R.id.lbl_movie_detail_title);
        lbl_text = findViewById(R.id.lbl_movie_details);
        lbl_description = findViewById(R.id.lbl_movie_detail_desciption);
        lbl_release_date = findViewById(R.id.lbl_movie_detail_release_date);

        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this,);

        lbl_title.setText(original_title;
        lbl_release_date.setText(release_date);
        lbl_description.setText(overview);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}