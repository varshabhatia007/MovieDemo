package com.varsha.moviedemo.data.di

import com.varsha.moviedemo.data.remote.MoviesRemoteDataSource
import com.varsha.moviedemo.data.remote.MoviesService
import com.varsha.moviedemo.data.remote.MoviesAPIService
import com.varsha.moviedemo.data.remote.MoviesRemoteDataSourceImpl
import com.varsha.moviedemo.data.remote.MoviesServiceImpl
import com.varsha.moviedemo.data.repository.MoviesRepository
import com.varsha.moviedemo.data.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    //Remote
    @Singleton
    @Binds
    abstract fun provideMovieServices(
        moviesServiceImpl: MoviesServiceImpl
    ): MoviesService

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

}


@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesAPIService {
        return retrofit.create(MoviesAPIService::class.java)
    }
}