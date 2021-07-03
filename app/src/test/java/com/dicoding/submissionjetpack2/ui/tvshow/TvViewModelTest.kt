package com.dicoding.submissionjetpack2.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submissionjetpack2.data.source.AllCatalougeRepo
import com.dicoding.submissionjetpack2.data.source.local.TvEntity
import com.dicoding.submissionjetpack2.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest{
    private lateinit var tvViewModel: TvViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var allCatalogueRepository: AllCatalougeRepo

    @Mock
    private lateinit var observer: Observer<List<TvEntity>>
    @Before
    fun setAll(){
        tvViewModel = TvViewModel(allCatalogueRepository)
    }
    @Test
    fun getDataTv(){
        val dummyTvShow = DataDummy.getTvShows()
        val tvShows = MutableLiveData<List<TvEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(allCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = tvViewModel.getTv().value
        verify(allCatalogueRepository).getTvShows()
        assertNotNull(tvShow)
        assertEquals(3,tvShow?.size)

        tvViewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}