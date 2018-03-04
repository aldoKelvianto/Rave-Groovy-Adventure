package com.aldoapps.ravegroovyadventure.characterlist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aldoapps.ravegroovyadventure.R
import com.aldoapps.ravegroovyadventure.characterlist.data.pogo.CharacterEntity
import com.aldoapps.ravegroovyadventure.characterlist.ui.pogo.CharacterModel
import groovy.transform.CompileStatic

@CompileStatic
class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<CharacterModel> characterList = new ArrayList<>()

    @Override
    CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        new CharacterViewHolder(view)
    }

    void setCharacterList(List<CharacterModel> characterList) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    @Override
    void onBindViewHolder(CharacterViewHolder holder, int position) {
        CharacterModel character = characterList.get(position)
        holder.bind(character)
    }

    @Override
    int getItemCount() {
        characterList.size()
    }
}