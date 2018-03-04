package com.aldoapps.ravegroovyadventure.characterlist.data.util

import com.aldoapps.ravegroovyadventure.characterlist.data.pogo.CharacterEntity
import com.aldoapps.ravegroovyadventure.characterlist.ui.pogo.CharacterModel

class CharacterEntityMapper {

    private static CharacterModel toCharacterModel(CharacterEntity entity) {
        def model = new CharacterModel()
        model.imageUrl = entity.imageUrl
        model.kanji = entity.kanji
        model.name = entity.name
        model
    }

    static List<CharacterModel> toCharacterModels(List<CharacterEntity> entities) {
        def models = new ArrayList()
        for (entity in entities) {
            def model = toCharacterModel(entity)
            models.add(model)
        }
        models
    }

}