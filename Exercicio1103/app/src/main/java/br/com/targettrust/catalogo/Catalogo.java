package br.com.targettrust.catalogo;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.targettrust.catalogo.utils.Util;


public class Catalogo  extends Application {
	// sensible place to declare a log tag for the application
		public static final String LOG_TAG = "Catalogo";
		private static Context context;
		// instance
		private static Catalogo instance = null;
		private static String deviceType = null;
		SQLiteDatabase db;

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
		public static Catalogo getInstance() {
			checkInstance();
			return instance;
		}

		public void onCreate() {
			instance = this;
			// Device model
			String PhoneModel = android.os.Build.MODEL;

			// Android version
			String AndroidVersion = android.os.Build.VERSION.RELEASE;

			Log.v(LOG_TAG, "Detalhes --> PhoneModel " + PhoneModel + " - AndroidVersion  " + AndroidVersion);
			Util.createDirIfNotExists(getPackageName());
			
			db = openOrCreateDatabase("Catalogo.db", MODE_PRIVATE, null);
	        
	        StringBuilder strb = new StringBuilder();
	        
	        strb.append(" CREATE TABLE IF NOT EXISTS PRODUTOS (        ");
	        strb.append(" _id         integer primary key autoincrement, ");
	        strb.append(" descricao  varchar(30),                       ");
	        strb.append(" codigo     varchar(100),                      ");
	        strb.append(" preco      double )                           ");
	        
	        db.execSQL(strb.toString());

		}

		public static Context getAppContext() {
			return Catalogo.context;
		}

		@Override
		public void onTerminate() {
			super.onTerminate();
			db.close();
			System.out.println("ContextApplication.onTerminate()");
		}
}
