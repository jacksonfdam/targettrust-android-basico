/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.fragments;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.targettrust.ttpetshop.R;
import br.com.targettrust.ttpetshop.Recievers.ScreenReceiver;

/*
 * Como lidar com o Intent.ACTION_SCREEN_OFF e o Intent.ACTION_SCREEN_ON,
 * será bacana se você estiver fazendo uma aplicação que pode precisar
 * salvar o estado ou responder a tela do usuário de ir dormir / acordar, etc.
 * Em primeiro lugar, ao contrário de outros broadcasted intents
 * (intenções transmitidos), por Intent.ACTION_SCREEN_OFF e
 * Intent.ACTION_SCREEN_ON você não pode declará-las no seu manifesto Android!
 * Eu não sei exatamente o porquê, mas eles devem ser registrados em um
 * IntentFilter no seu código Java. E assim, para este exemplo, vamos ter um
 * receptor chamado ScreenReceiver, e eu vou levá-lo através das diferenças
 * entre a sua aplicação em um serviço versus atividade.
 *
 * origem: http://thinkandroid.wordpress.com/2010/01/24/handling-screen-off-and-screen-on-intents/
 *
 */

public class ContatoFragment extends Fragment {


    public ContatoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contato, container, false);
    }

    @Override
    public void onStart() {
        //INICIALIZAR RECEPTOR
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onPause() {
        // Quando a tela está prestes a ser desligada
        if (ScreenReceiver.wasScreenOn) {
            // Este é o caso quando OnPause () é chamado pelo sistema devido a uma mudança de estado TELA
            System.out.println("SCREEN TURNED OFF");
        } else {
            // Isto é, quando OnPause () é chamado quando o estado de tela não foi alterado.
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        // Somente quando a tela se acende
        if (!ScreenReceiver.wasScreenOn) {
            // Isto é, quando onResume () é chamado devido a uma mudança ESTADO DA TELA
            System.out.println("SCREEN TURNED ON");
        } else {
            // Isto é, quando onResume () é chamado quando o estado de tela não foi alterado
        }
        super.onResume();
    }

}
