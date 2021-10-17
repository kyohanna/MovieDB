package com.example.moviedb.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.respitories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application){
        super(application);
        repository = MovieRepository.getInstance();
    }

    // BEGIN OF VIEWMODEL GET MOVIE by ID

    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }
    // End OF VIEWMODEL GET MOVIE by ID

    // BEGIN OF VIEWMODEL GET Now Playing

    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }

    // End OF VIEWMODEL GET Now Playing





}
