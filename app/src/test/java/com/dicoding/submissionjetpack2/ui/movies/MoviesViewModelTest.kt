package com.dicoding.submissionjetpack2.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack2.data.source.local.MovieEntity
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
class MoviesViewModelTest {
    private lateinit var movieViewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var allCatalogueRepository: AllCatalougeRepo

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setAll() {
        movieViewModel = MoviesViewModel(allCatalogueRepository)
    }

    @Test
    fun getDataMovies() {
        val dummyMovie = DataDummy.getMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie

        `when`(allCatalogueRepository.getMovies()).thenReturn(movies)
        val movie = movieViewModel.getMovies().value
        verify(allCatalogueRepository).getMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        movieViewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}