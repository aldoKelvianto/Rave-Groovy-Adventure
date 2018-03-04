package com.aldoapps.ravegroovyadventure.characterlist.ui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.aldoapps.ravegroovyadventure.R
import com.aldoapps.ravegroovyadventure.characterlist.ui.pogo.CharacterModel
import com.aldoapps.ravegroovyadventure.util.Vaporize
import com.arasthel.swissknife.SwissKnife
import com.arasthel.swissknife.annotations.InjectView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation

class CharacterViewHolder extends RecyclerView.ViewHolder {

    final static String TAG = "characterviewholder"

    @InjectView(R.id.iv_char)
    ImageView ivCharacter

    @InjectView(R.id.tv_name)
    TextView tvName

    @InjectView(R.id.tv_alias)
    TextView tvAlias

    CharacterViewHolder(View itemView) {
        super(itemView)
        SwissKnife.inject(this, itemView)
    }

    void bind(CharacterModel character) {
        Log.d(TAG, "character" + character.toString())
        Picasso.with(itemView.context)
                .load(character.imageUrl)
                .transform(new ToonFilterTransformation(itemView.context))
                .into(ivCharacter)
        tvName.text = character.kanji
        tvAlias.text = Vaporize.vaporize(character.name)
    }

}
