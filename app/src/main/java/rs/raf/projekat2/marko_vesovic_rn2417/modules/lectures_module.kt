package rs.raf.projekat2.marko_vesovic_rn2417.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.marko_vesovic_rn2417.data.datasources.local.Database
import rs.raf.projekat2.marko_vesovic_rn2417.data.datasources.remote.LectureService
import rs.raf.projekat2.marko_vesovic_rn2417.data.repositories.lectures.LectureRepository
import rs.raf.projekat2.marko_vesovic_rn2417.data.repositories.lectures.LectureRepositoryImplementation
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodels.TimetableViewModel

val lecturesModule = module {

    viewModel { TimetableViewModel(lectureRepository = get()) }

    single<LectureRepository> { LectureRepositoryImplementation(localDataSource = get(), remoteDataSource = get()) }

    single { get<Database>().getLectureDao() }

    single<LectureService> { create(retrofit = get()) }

}