package com.example.ndsp.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ndsp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    LinearLayout tvChangeFont;
    RadioButton rBE,rBZ,rBU;
    public static final String LANGUAGE_PREFERENCE = "lan_pref", PREFERENCE_KEY = "lan_key",PREFERENCE_VALUE = "lan_val";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE);

        tvChangeFont = view.findViewById(R.id.tv_change_font);
        tvChangeFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChangeFontDialog();
            }
        });


    }

    private void displayChangeFontDialog() {


        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.select_language, null);
        dialogBuilder.setView(dialogView);

        final RadioGroup radioGroup = dialogView.findViewById(R.id.rg);
        rBE = dialogView.findViewById(R.id.r_e);
        rBZ = dialogView.findViewById(R.id.r_z);
        rBU = dialogView.findViewById(R.id.r_u);
        final Button btn = dialogView.findViewById(R.id.btn_language_cancel);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(true);
        b.show();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                switch (selectedId){
                    case R.id.r_e:

                        editor = sharedPreferences.edit();
                        editor.putString(PREFERENCE_KEY, "e");
                        editor.apply();
                        break;

                    case R.id.r_z:

                        editor = sharedPreferences.edit();
                        editor.putString(PREFERENCE_KEY, "z");
                        editor.apply();
                        Log.d("Lakhwane","lote tal ha zawgyi"+"z");
                        break;


                    case R.id.r_u:

                        editor = sharedPreferences.edit();
                        editor.putString(PREFERENCE_KEY, "u");
                        editor.apply();
                        break;
                }
                b.dismiss();
            }
        });

    }

}
