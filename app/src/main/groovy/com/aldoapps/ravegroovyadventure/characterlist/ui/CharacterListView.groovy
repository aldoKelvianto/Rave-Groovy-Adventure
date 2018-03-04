package com.aldoapps.ravegroovyadventure.characterlist.ui

import com.aldoapps.ravegroovyadventure.characterlist.ui.pogo.CharacterModel

interface CharacterListView {

    void showCharacterList(List<CharacterModel> characterList)

    void showError(String errorMessage)

}