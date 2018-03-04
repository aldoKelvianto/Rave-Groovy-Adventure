package com.aldoapps.ravegroovyadventure.characterlist.data;

import android.content.Context;

import com.aldoapps.ravegroovyadventure.characterlist.data.pogo.CharacterEntity;
import com.aldoapps.ravegroovyadventure.characterlist.data.pogo.CharacterListResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CharacterRepository implements Response.Listener<String>, ObservableOnSubscribe<List<CharacterEntity>>, Response.ErrorListener {

    private Context context;

    private Observable<List<CharacterEntity>> characterList;

    private ObservableEmitter<List<CharacterEntity>> emitter;

    private StringRequest getCharacterListRequest;

    public CharacterRepository(Context context) {
        this.context = context;
        characterList = Observable.create(this);
    }

    private StringRequest initGetCharacterListRequest() {
        if (getCharacterListRequest == null) {
            String characterListUrl = "https://private-c0294-ravecharactersapi.apiary-mock.com/characters";
            getCharacterListRequest = new StringRequest(Request.Method.GET,
                    characterListUrl, this, this);
        }
        return getCharacterListRequest;
    }

    private void doGetCharacterList() {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = initGetCharacterListRequest();
        queue.add(request);
    }

    public Observable<List<CharacterEntity>> getCharacterList() {
        doGetCharacterList();
        return characterList;
    }

    @Override
    public void onResponse(String response) {
        CharacterListResponse parsedResponse = new Gson().fromJson(response, CharacterListResponse.class);
        if (parsedResponse.getCharList() != null) {
            emitter.onNext(parsedResponse.getCharList());
        } else {
            emitter.onError(new Throwable("System error"));
        }
    }

    @Override
    public void subscribe(ObservableEmitter<List<CharacterEntity>> observableEmitter) throws Exception {
        this.emitter = observableEmitter;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        emitter.onError(error);
    }
}
