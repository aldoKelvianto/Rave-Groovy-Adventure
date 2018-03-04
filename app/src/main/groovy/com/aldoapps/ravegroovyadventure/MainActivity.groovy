package com.aldoapps.ravegroovyadventure

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.aldoapps.ravegroovyadventure.base.BaseActivity
import com.aldoapps.ravegroovyadventure.characterlist.data.CharacterRepository
import com.aldoapps.ravegroovyadventure.characterlist.ui.CharacterAdapter
import com.aldoapps.ravegroovyadventure.characterlist.ui.CharacterListPresenter
import com.aldoapps.ravegroovyadventure.characterlist.ui.CharacterListView
import com.aldoapps.ravegroovyadventure.characterlist.ui.pogo.CharacterModel
import com.aldoapps.ravegroovyadventure.playsound.SoundPlayer
import com.aldoapps.ravegroovyadventure.util.DrawableRepository
import com.aldoapps.ravegroovyadventure.util.Vaporize
import com.arasthel.swissknife.annotations.InjectView
import com.arasthel.swissknife.annotations.OnClick
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends BaseActivity implements CharacterListView {

    @InjectView(R.id.rv_character_list)
    RecyclerView rvList

    @InjectView(R.id.iv_placeholder)
    ImageView ivPlaceHolder

    @InjectView(R.id.tv_windows_title)
    TextView tvWindowsTitle

    private CharacterListPresenter presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)

        initWindowsTitle()
        initRvList()
        initPresenter()
        initPlaceHolder()
    }

    private void initWindowsTitle() {
        tvWindowsTitle.text = Vaporize.vaporize(resources.getString(R.string.app_name))
        tvWindowsTitle.setOnClickListener { finish() }
    }

    private void initPlaceHolder() {
        ivPlaceHolder?.setImageResource(DrawableRepository.newInstance().getDefaultDrawable())
    }

    @OnClick(R.id.iv_bust_mac)
    void loadCharList() {
        SoundPlayer.INSTANCE.play()

        rvList?.visibility = View.GONE
        ivPlaceHolder?.visibility = View.VISIBLE
        ivPlaceHolder?.setImageResource(DrawableRepository.getWaitingDrawable())
        presenter?.getCharacterList()
    }

    private void initPresenter() {
        CharacterRepository repository = new CharacterRepository(this)
        presenter = new CharacterListPresenter(repository)
        presenter.setView(this)
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main
    }

    private void initRvList() {
        rvList?.layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        CharacterAdapter adapter = new CharacterAdapter()
        rvList?.adapter = adapter
    }

    @Override
    void showCharacterList(List<CharacterModel> characterList) {
        ivPlaceHolder?.visibility = View.GONE
        rvList?.visibility = View.VISIBLE

        (rvList?.adapter as CharacterAdapter).characterList = characterList
    }

    @Override
    void showError(String errorMessage) {
        ivPlaceHolder?.visibility = View.VISIBLE
        rvList?.visibility = View.GONE

        ivPlaceHolder?.setImageResource(DrawableRepository.getErrorDrawable())
    }
}
