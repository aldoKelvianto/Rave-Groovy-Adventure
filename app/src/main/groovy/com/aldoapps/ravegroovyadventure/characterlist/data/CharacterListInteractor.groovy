package com.aldoapps.ravegroovyadventure.characterlist.data

import com.aldoapps.ravegroovyadventure.base.BaseInteractor
import com.aldoapps.ravegroovyadventure.characterlist.data.pogo.CharacterEntity
import groovy.transform.CompileStatic
import io.reactivex.Observable

@CompileStatic
class CharacterListInteractor extends BaseInteractor<List<CharacterEntity>, Void> {

    private CharacterRepository repository

    CharacterListInteractor(CharacterRepository repository) {
        this.repository = repository
    }

    @Override
    Observable<List<CharacterEntity>> buildInteractorObservable(Void param) {
        return repository.getCharacterList()
    }
}