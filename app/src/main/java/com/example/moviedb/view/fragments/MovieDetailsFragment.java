package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_title_movie_detail_fragment, lbl_description_movie_detail_fragment, lbl_release_date_movie_detail_fragment;
    private String movie_id = "";
    private MovieViewModel viewModel;
    private ImageView img_poster_movie_details_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_details, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

        movie_id = getArguments().getString("movieId");
        lbl_title_movie_detail_fragment = view.findViewById(R.id.lbl_title_movie_detail_fragment);
        lbl_description_movie_detail_fragment = view.findViewById(R.id.lbl_description_movie_detail_fragment);
        lbl_release_date_movie_detail_fragment = view.findViewById(R.id.lbl_release_date_movie_detail_fragment);
        img_poster_movie_details_fragment = view.findViewById(R.id.img_poster_movie_details_fragment);

        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovie);
        String movieId = getArguments().getString("movieID");

        return view;
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(MovieDetailsFragment.this).load(img_path).into(img_poster_movie_details_fragment);
            lbl_title_movie_detail_fragment.setText(movies.getTitle());
            lbl_description_movie_detail_fragment.setText(movies.getOverview());
            lbl_release_date_movie_detail_fragment.setText(movies.getRelease_date());

        }
    };
















}