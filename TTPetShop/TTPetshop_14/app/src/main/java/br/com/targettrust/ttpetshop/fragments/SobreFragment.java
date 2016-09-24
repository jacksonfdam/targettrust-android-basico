/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */


package br.com.targettrust.ttpetshop.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

import br.com.targettrust.ttpetshop.R;
import br.com.targettrust.ttpetshop.utils.DebugFragment;
import br.com.targettrust.ttpetshop.utils.Utils;

public class SobreFragment extends DebugFragment {
    WebView wv;
    View v;

    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return v = inflater.inflate(R.layout.fragment_sobre, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        wv = (WebView) v.findViewById(R.id.webView1);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        wv.setLayoutParams(lp);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        //wv.loadUrl("http://www.targettrust.com.br/");

        String html = "<html>";
        html += "<body>";
        html += "<img src=\"http://www.targettrust.com.br/img/header-logo_v2.png\" style=\"float: left; display: block; margin-right: 10px;\" />";
        html += "<h3 id=\"h3\" style=\"float: left;\">Texto auxiliar </h3>";
        html += "<script type=\"text/javascript\">";
        html += "document.getElementById('h3').style.color = '#ff0000';";
        html += "</script></body></html>";

        wv.loadData(html, "text/html", "UTF-8");

    }


}
