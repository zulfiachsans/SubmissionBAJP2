package com.dicoding.submissionjetpack2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack2.data.source.local.DetailEntity
import com.dicoding.submissionjetpack2.ui.detail.DetailViewModel.Companion.MOVIES
import com.dicoding.submissionjetpack2.ui.detail.DetailViewModel.Companion.TV_SHOWS
import com.dicoding.submissionjetpack2.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.getDetailMovie()
    private val dummyId = dummyMovies.id.toString()

    private val dummyTv = DataDummy.getDetailTvShow()
    private val dummyTvId = dummyTv.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var allCatalougeRepo : AllCatalougeRepo

    @Mock
    private lateinit var allObserver: Observer<DetailEntity>

    @Before
    fun setMovie() {
        viewModel = DetailViewModel(allCatalougeRepo)
    }

    @Test
    fun getDetailMovie() {
        val movies = MutableLiveData<DetailEntity>()
        movies.value = dummyMovies

        `when`(allCatalougeRepo.getDetailMovie(dummyId)).thenReturn(movies)
        viewModel.setAll(dummyId, MOVIES)
        val movie = viewModel.getAllDetail().value as DetailEntity
        verify(allCatalougeRepo).getDetailMovie(dummyId)
        assertNotNull(movie)
        assertEquals(dummyMovies.backdropPath,movie.backdropPath)
        assertEquals(dummyMovies.title,movie.title)
        assertEquals(dummyMovies.releaseDate, movie.releaseDate)
        assertEquals(dummyMovies.genres, movie.genres)
        assertEquals(dummyMovies.runtime, movie.runtime)
        assertEquals(dummyMovies.posterPath, movie.posterPath)
        assertEquals(dummyMovies.overview, movie.overview)
    }

    //Get Tv data Testing
    @Before
    fun setTv() {
        viewModel = DetailViewModel(allCatalougeRepo)
    }

    @Test
    fun getTvDetail() {
        val tvShows = MutableLiveData<DetailEntity>()
        tvShows.value = dummyTv

        `when`(allCatalougeRepo.getDetailTvShow(dummyTvId)).thenReturn(tvShows)
        viewModel.setAll(dummyTvId, TV_SHOWS)
        val tv = viewModel.getAllDetail().value as DetailEntity
        verify(allCatalougeRepo).getDetailTvShow(dummyTvId)
        assertNotNull(tv)
        assertEquals(dummyTv.backdropPath,tv.backdropPath)
        assertEquals(dummyTv.title,tv.title)
        assertEquals(dummyTv.releaseDate, tv.releaseDate)
        assertEquals(dummyTv.genres, tv.genres)
        assertEquals(dummyTv.runtime, tv.runtime)
        assertEquals(dummyTv.posterPath, tv.posterPath)
        assertEquals(dummyTv.overview, tv.overview)

        viewModel.getAllDetail().observeForever(allObserver)
        verify(allObserver).onChanged(dummyTv)
    }
}
