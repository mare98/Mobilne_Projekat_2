package rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.marko_vesovic_rn2417.data.models.Note
import rs.raf.projekat2.marko_vesovic_rn2417.data.repositories.notes.NotesRepository
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract.NotesContract
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.states.NotesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NotesViewModel(
    private val notesRepository: NotesRepository
) : ViewModel(), NotesContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val notesState: MutableLiveData<NotesState> = MutableLiveData()


    override fun getAllNotes() {
        val subscription = notesRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNonArchivedNotes() {
        val subscription = notesRepository
            .getAllNonArchived()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching non archived data from db")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByFilter(filter: String) {
        val subscription = notesRepository
            .getAllByFilter(filter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching filtered data from db")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNonArchivedNotesByFilter(filter: String) {
        val subscription = notesRepository
            .getAllNonArchivedByFilter(filter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while filtering data")
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertNote(note: Note) {
        val subscription = notesRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.SuccessMessage("Successfully inserted note")
                },
                {

                    notesState.value = NotesState.Error("Error happened while inserting note")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateNote(note: Note) {
        val subscription = notesRepository
            .update(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.SuccessMessage("Successfully updated note")
                },
                {
                    notesState.value = NotesState.Error("Error happened while updating note")
                }
            )
        subscriptions.add(subscription)
    }

    override fun changeArchivedStatus(note: Note) {
        val subscription = notesRepository
            .changeArchivedStatus(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.SuccessMessage("Successfully changed archived status")
                },
                {
                    notesState.value = NotesState.Error("Error happened while changing archived status")
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(note: Note) {
        val subscription = notesRepository
            .delete(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.SuccessMessage("Successfully deleted note")
                },
                {
                    notesState.value = NotesState.Error("Error happened while deleting note")
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}