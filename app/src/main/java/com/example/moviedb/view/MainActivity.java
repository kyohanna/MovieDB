package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button btn_hit;
    private TextView txt_show;
    private TextInputLayout till_movie_id;
    private ImageView img_poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_show = findViewById(R.id.txt_show_main);
        till_movie_id = findViewById(R.id.til_movie_id);
        img_poster = findViewById(R.id.img_poster_main);
        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit = findViewById(R.id.btn_hit_main);
        btn_hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = till_movie_id.getEditText().getText().toString().trim();
                if(movieId.isEmpty()){
                    till_movie_id.setError("Isien he");
                }else{
                    till_movie_id.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovies);
                }

            }
        });
    }

    private Observer<Movies> showResultMovies = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies == null){
                txt_show.setText("Ga onok he");
            }
            String title = movies.getTitle();
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(MainActivity.this).load(img_path).into(img_poster);
            txt_show.setText(title);
        }
    };


}