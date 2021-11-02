package com.example.moviedb.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_movie_detail_title, lbl_movie_detail_description, lbl_movie_detail_release_date;
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

        lbl_movie_detail_title = findViewById(R.id.lbl_movie_detail_title);
        lbl_movie_detail_description = findViewById(R.id.lbl_movie_detail_desciption);
        lbl_movie_detail_release_date = findViewById(R.id.lbl_movie_detail_release_date);
        img_poster = findViewById(R.id.poster_detail);

        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showMovieResult);

    }

    private final Observer<Movies> showMovieResult = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String posterPath = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(MovieDetailsActivity.this).load(posterPath).into(img_poster);
            lbl_movie_detail_title.setText(movies.getTitle());
            lbl_movie_detail_description.setText(movies.getOverview());
            lbl_movie_detail_release_date.setText(movies.getRelease_date());

        }
    };

    @Override
    public void onBackPressed(){finish();}

}

