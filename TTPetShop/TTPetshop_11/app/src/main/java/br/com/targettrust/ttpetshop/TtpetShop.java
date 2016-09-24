/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

/*
Sobre o Ciclo de Vida da Aplicação e Processos
http://developer.android.com/guide/topics/processes/process-lifecycle.html
http://developer.android.com/reference/android/app/Application.html

Sobre os Componentes da Aplicação
http://developer.android.com/guide/components/fundamentals.html

Sobre os Requisitos da Apliação
http://developer.android.com/guide/topics/manifest/uses-feature-element.html#required

 */
package br.com.targettrust.ttpetshop;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import br.com.targettrust.ttpetshop.utils.DatabaseHelper;
import br.com.targettrust.ttpetshop.utils.Utils;

public class TtpetShop extends Application {
    // sensible place to declare a log tag for the application
    public static final String TAG = TtpetShop.class.getSimpleName();
    private static Context context;
    // instance
    private static TtpetShop instance = null;
    private static String deviceType = null;
    public DatabaseHelper db;
    Utils util;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String s) {
        deviceType = s;
    }

    private static void checkInstance() {
        if (instance == null)
            throw new IllegalStateException("Application not created yet!");
    }

    /**
     * Convenient accessor, saves having to call and cast
     * getApplicationContext()
     */
    public static TtpetShop getInstance() {
        checkInstance();
        return instance;
    }

    public void onCreate() {
        Log.v(TAG, this.getClass().getSimpleName() + " - onCreate()");
        instance = this;
        db = new DatabaseHelper(this);
        util = new Utils(this);

        // Device model
        String PhoneModel = android.os.Build.MODEL;

        // Android version
        String AndroidVersion = android.os.Build.VERSION.RELEASE;

        Log.v(TAG, "Detalhes --> PhoneModel " + PhoneModel + " - AndroidVersion  " + AndroidVersion);
        util.createDirIfNotExists(getPackageName());
    }

    public static Context getAppContext() {
        return TtpetShop.context;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.v(TAG, this.getClass().getSimpleName() + " - onLowMemory()");
    }

    @Override
    public void onTerminate() {
        Log.v(TAG, this.getClass().getSimpleName() + " - onTerminate()");
        super.onTerminate();
        db.close();
        System.out.println("ContextApplication.onTerminate()");
    }
}