package com.dicoding.submissionjetpack2.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.dicoding.submissionjetpack2.data.source.remote.RemoteAllDataSource
import com.dicoding.submissionjetpack2.utils.DataDummy
import com.dicoding.submissionjetpack2.utils.LiveDataTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class AllCatalougeRepoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteAllDataSource::class.java)
    private val allCatalogueRepository = FakeAllCatalougeRepoTest(remote)

    private val moviesResponse = DataDummy.getRemoteMovies()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = DataDummy.getRemoteDetailMovie()

    private val tvShowResponse = DataDummy.getRemoteTvShows()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteAllDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTest.getValue(allCatalogueRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteAllDataSource.LoadDetailMovieCallback).onDetailMovieLoaded(movieDetail)
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))

        val movieDetailEntity = LiveDataTest.getValue(allCatalogueRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.id)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteAllDataSource.LoadTvShowsCallback).onTvShowsLoaded(tvShowResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTest.getValue(allCatalogueRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteAllDataSource.LoadDetailTvShowCallback).onDetailTvShowLoaded(tvShowDetail)
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvShowId))

        val tvShowDetailEntity = LiveDataTest.getValue(allCatalogueRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(any(), eq(tvShowId))
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.id)
    }
}