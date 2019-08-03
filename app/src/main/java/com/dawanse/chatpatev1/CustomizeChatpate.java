package com.dawanse.chatpatev1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dawanse.chatpatev1.model.MotorController;
import com.dawanse.chatpatev1.variables.MotorVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CustomizeChatpate extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar chanaSeekbar, chauchauSeekbar, chillySeekbar, cucumberSeekbar,
            kagatiSeekbar, muraiSeekbar, oilSeekbar, onionSeekbar, potatoSeekbar, tomatoSeekbar;

    private TextView chanaTV, chauchauTV, chillyTV, cucumberTV, kagatiTV,
            muraiTV, oilTV, onionTV, potatoTV, tomatoTV;

    private Button makeChatpateBtn;

    private HashMap<String, Integer> hashMapChatpate = new HashMap<String, Integer>();

    private ArrayList<MotorController> chatpateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_chatpate);

        chanaSeekbar = findViewById(R.id.seekbar_chana);
        chauchauSeekbar = findViewById(R.id.seekbar_chauchau);
        chillySeekbar = findViewById(R.id.seekbar_chilly);
        cucumberSeekbar = findViewById(R.id.seekbar_cucumber);
        kagatiSeekbar = findViewById(R.id.seekbar_kagati);
        muraiSeekbar = findViewById(R.id.seekbar_murai);
        oilSeekbar = findViewById(R.id.seekbar_oil);
        onionSeekbar = findViewById(R.id.seekbar_onion);
        potatoSeekbar = findViewById(R.id.seekbar_potato);
        tomatoSeekbar = findViewById(R.id.seekbar_tomato);

        chanaTV = findViewById(R.id.tv_chana);
        chauchauTV = findViewById(R.id.tv_chauchau);
        chillyTV = findViewById(R.id.tv_chilly);
        cucumberTV = findViewById(R.id.tv_cucumber);
        kagatiTV = findViewById(R.id.tv_kagati);
        muraiTV = findViewById(R.id.tv_murai);
        oilTV = findViewById(R.id.tv_oil);
        onionTV = findViewById(R.id.tv_onion);
        potatoTV = findViewById(R.id.tv_potato);
        tomatoTV = findViewById(R.id.tv_tomato);

        //button
        makeChatpateBtn = findViewById(R.id.customized_makechatpate);

        //set seekbar progress to 1 and put values in hashmap
        setSeekValue();

        //
        chanaSeekbar.setOnSeekBarChangeListener(this);
        chauchauSeekbar.setOnSeekBarChangeListener(this);
        chillySeekbar.setOnSeekBarChangeListener(this);
        cucumberSeekbar.setOnSeekBarChangeListener(this);
        kagatiSeekbar.setOnSeekBarChangeListener(this);
        muraiSeekbar.setOnSeekBarChangeListener(this);
        oilSeekbar.setOnSeekBarChangeListener(this);
        onionSeekbar.setOnSeekBarChangeListener(this);
        potatoSeekbar.setOnSeekBarChangeListener(this);
        tomatoSeekbar.setOnSeekBarChangeListener(this);

        //make chatpate button listener
        makeChatpateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueChatpateItem();
            }
        });
    }

    private void setSeekValue() {
        chanaSeekbar.setProgress(1);
        chanaTV.setText("1");
        chauchauSeekbar.setProgress(1);
        chauchauTV.setText("1");
        chillySeekbar.setProgress(1);
        chillyTV.setText("1");
        cucumberSeekbar.setProgress(1);
        cucumberTV.setText("1");
        kagatiSeekbar.setProgress(1);
        kagatiTV.setText("1");
        muraiSeekbar.setProgress(1);
        muraiTV.setText("1");
        oilSeekbar.setProgress(1);
        oilTV.setText("1");
        onionSeekbar.setProgress(1);
        onionTV.setText("1");
        potatoSeekbar.setProgress(1);
        potatoTV.setText("1");
        tomatoSeekbar.setProgress(1);
        tomatoTV.setText("1");

        hashMapChatpate.put("chana", 1);
        hashMapChatpate.put("chauchau", 1);
        hashMapChatpate.put("chilly", 1);
        hashMapChatpate.put("cucumber", 1);
        hashMapChatpate.put("kagati", 1);
        hashMapChatpate.put("murai", 1);
        hashMapChatpate.put("oil", 1);
        hashMapChatpate.put("onion", 1);
        hashMapChatpate.put("potato", 1);
        hashMapChatpate.put("tomato", 1);

    }

    private void setValueChatpateItem() {

        chatpateList.add(new MotorController(MotorVariables.NAME_CHANA,
                MotorVariables.PWM_CHANA, MotorVariables.ACW_CHANA, MotorVariables.TIME_CHANA));

        chatpateList.add(new MotorController(MotorVariables.NAME_CHAUCHAU,
                MotorVariables.PWM_CHAUCHAU, MotorVariables.ACW_CHAUCHAU, MotorVariables.TIME_CHAUCHAU));

        chatpateList.add(new MotorController(MotorVariables.NAME_CHILLY,
                MotorVariables.PWM_CHILLY, MotorVariables.ACW_CHILLY, MotorVariables.TIME_CHILLY));

        chatpateList.add(new MotorController(MotorVariables.NAME_CUCUMBER,
                MotorVariables.PWM_CUCUMBER, MotorVariables.ACW_CUCUMBER, MotorVariables.TIME_CUCUMBER));

        chatpateList.add(new MotorController(MotorVariables.NAME_KAGATI,
                MotorVariables.PWM_KAGATI, MotorVariables.ACW_KAGATI, MotorVariables.TIME_KAGATI));

        chatpateList.add(new MotorController(MotorVariables.NAME_MURAI,
                MotorVariables.PWM_MURAI, MotorVariables.ACW_MURAI, MotorVariables.TIME_MURAI));

        chatpateList.add(new MotorController(MotorVariables.NAME_OIL,
                MotorVariables.PWM_OIL, MotorVariables.ACW_OIL, MotorVariables.TIME_OIL));

        chatpateList.add(new MotorController(MotorVariables.NAME_ONION,
                MotorVariables.PWM_ONION, MotorVariables.ACW_ONION, MotorVariables.TIME_ONION));

        chatpateList.add(new MotorController(MotorVariables.NAME_POTATO,
                MotorVariables.PWM_POTATO, MotorVariables.ACW_POTATO, MotorVariables.TIME_POTATO));

        chatpateList.add(new MotorController(MotorVariables.NAME_TOMATO,
                MotorVariables.PWM_TOMATO, MotorVariables.ACW_TOMATO, MotorVariables.TIME_TOMATO));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        switch (seekBar.getId()) {
            case R.id.seekbar_chana:
                hashMapChatpate.put("chana", chanaSeekbar.getProgress());
                chanaTV.setText(String.valueOf(chanaSeekbar.getProgress()));
            case R.id.seekbar_chauchau:
                hashMapChatpate.put("chauchau", chauchauSeekbar.getProgress());
                chauchauTV.setText(String.valueOf(chauchauSeekbar.getProgress()));
            case R.id.seekbar_chilly:
                hashMapChatpate.put("chilly", chillySeekbar.getProgress());
                chillyTV.setText(String.valueOf(chillySeekbar.getProgress()));
            case R.id.seekbar_cucumber:
                hashMapChatpate.put("cucumber", cucumberSeekbar.getProgress());
                cucumberTV.setText(String.valueOf(cucumberSeekbar.getProgress()));
            case R.id.seekbar_kagati:
                hashMapChatpate.put("kagati", kagatiSeekbar.getProgress());
                kagatiTV.setText(String.valueOf(kagatiSeekbar.getProgress()));
            case R.id.seekbar_murai:
                hashMapChatpate.put("murai", muraiSeekbar.getProgress());
                muraiTV.setText(String.valueOf(muraiSeekbar.getProgress()));
            case R.id.seekbar_oil:
                hashMapChatpate.put("oil", oilSeekbar.getProgress());
                oilTV.setText(String.valueOf(oilSeekbar.getProgress()));
            case R.id.seekbar_onion:
                hashMapChatpate.put("onion", onionSeekbar.getProgress());
                onionTV.setText(String.valueOf(onionSeekbar.getProgress()));
            case R.id.seekbar_potato:
                hashMapChatpate.put("potato", potatoSeekbar.getProgress());
                potatoTV.setText(String.valueOf(potatoSeekbar.getProgress()));
            case R.id.seekbar_tomato:
                hashMapChatpate.put("tomato", tomatoSeekbar.getProgress());
                tomatoTV.setText(String.valueOf(tomatoSeekbar.getProgress()));

        }
    }
}
