package com.aldoapps.ravegroovyadventure.characterlist.ui

import com.aldoapps.ravegroovyadventure.characterlist.data.CharacterListInteractor
import com.aldoapps.ravegroovyadventure.characterlist.data.CharacterRepository
import com.aldoapps.ravegroovyadventure.characterlist.data.pogo.CharacterEntity
import com.aldoapps.ravegroovyadventure.characterlist.data.util.CharacterEntityMapper
import groovy.transform.CompileStatic
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver

@CompileStatic
class CharacterListPresenter {

    private CharacterListView view

    private CharacterListInteractor characterListInteractor

    CharacterListPresenter(CharacterRepository repository) {
        characterListInteractor = new CharacterListInteractor(repository)
    }

    void setView(CharacterListView view) {
        this.view = view
    }

    void getCharacterList() {
        characterListInteractor.execute(new CharacterListObserver(), null)
    }

    class CharacterListObserver extends DisposableObserver<List<CharacterEntity>> {

        @Override
        void onNext(@NonNull List<CharacterEntity> raveCharacters) {
            def models = CharacterEntityMapper.toCharacterModels(raveCharacters)
            view?.showCharacterList(models)
        }

        @Override
        void onError(@NonNull Throwable e) {
            view?.showError(e.toString())
        }

        @Override
        void onComplete() {

        }
    }

}