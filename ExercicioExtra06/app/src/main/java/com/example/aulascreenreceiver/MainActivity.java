package com.example.aulascreenreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

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
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//INICIALIZAR RECEPTOR
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
	}
	@Override
    protected void onPause() {
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
    protected void onResume() {
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
