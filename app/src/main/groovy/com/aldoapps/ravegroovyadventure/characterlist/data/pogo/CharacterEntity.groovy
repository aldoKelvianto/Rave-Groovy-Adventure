package com.aldoapps.ravegroovyadventure.characterlist.data.pogo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import groovy.transform.ToString

@ToString
class CharacterEntity {

    @SerializedName("id")
    @Expose
    Integer id

    @SerializedName("imageUrl")
    @Expose
    String imageUrl

    @SerializedName("name")
    @Expose
    String name

    @SerializedName("kanji")
    @Expose
    String kanji

    @SerializedName("alias")
    @Expose
    List<String> alias

    @SerializedName("birthday")
    @Expose
    String birthday

    @SerializedName("gender")
    @Expose
    String gender

    @SerializedName("description")
    @Expose
    String description
}