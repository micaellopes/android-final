package br.unipe.borracheiro.borracheiro.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unipe.borracheiro.borracheiro.R;

/**
 * Created by Andrey on 05/06/2017.
 */

public class MainFragment {

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
