/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */


package br.com.targettrust.ttpetshop.utils;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.targettrust.ttpetshop.R;

public class DebugFragment extends Fragment {
    private static final String TAG = DebugFragment.class.getSimpleName();

    public DebugFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onCreateView(): ");
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onCreateView(): ");
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "checkpoint on onActivityCreated(): ");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onViewCreated(): ");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, "checkpoint on onActivityResult(): ");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.v(TAG, "checkpoint on onAttach(): ");
        super.onAttach(activity);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.v(TAG, "checkpoint on onConfigurationChanged(): ");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onCreate(): ");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        Log.v(TAG, "checkpoint on onCreateOptionsMenu(): ");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "checkpoint on onDestroy(): ");
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "checkpoint on onDestroyView(): ");
        super.onDestroyView();
    }

    @Override
    public void onDestroyOptionsMenu() {
        Log.v(TAG, "checkpoint on onDestroyOptionsMenu(): ");
        super.onDestroyOptionsMenu();
    }

    @Override
    public void onDetach() {
        Log.v(TAG, "checkpoint on onDetach(): ");
        super.onDetach();
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onInflate(): ");
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        Log.v(TAG, "checkpoint on onPrepareOptionsMenu(): ");
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPause() {
        Log.v(TAG, "checkpoint on onPause(): ");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.v(TAG, "checkpoint on onResume(): ");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "checkpoint on onSaveInstanceState(): ");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        Log.v(TAG, "checkpoint on onStart(): ");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.v(TAG, "checkpoint on onStop(): ");
        super.onStop();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onViewStateRestored(): ");
        super.onViewStateRestored(savedInstanceState);
    }
}
