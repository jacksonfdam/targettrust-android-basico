package br.com.targettrust.otempo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	ListView lv;
	String codigo = "0";
	String streamTitle = "";
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	public static final String TAG = "MainActivity";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.listView1);
		adapterSimple();

	}

	private void verificar() {
		Log.d(TAG,"Verificar()");
		
		try {
			URL rssUrl = new URL("http://servicos.cptec.inpe.br/XML/cidade/"
					+ codigo + "/previsao.xml");
			SAXParserFactory mySAXParserFactory = SAXParserFactory
					.newInstance();
			SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
			XMLReader myXMLReader = mySAXParser.getXMLReader();
			RSSHandler myRSSHandler = new RSSHandler();
			myXMLReader.setContentHandler(myRSSHandler);
			InputSource myInputSource = new InputSource(rssUrl.openStream());
			myXMLReader.parse(myInputSource);

			Log.v("OTempo", streamTitle);
			
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date d = c.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String hoje = sdf.format(d);
			
			ArrayList<ContentValues> lista = myRSSHandler.getPrevisao();
			
			int l = lista.size();
			
			Log.d(TAG,"Lista XML: "+l+" itens");
			
			for(int i = 0;i<l;i++) {
				ContentValues cv = lista.get(i);
				if(cv.getAsString("dia").equals(hoje)) {
					Intent in = new Intent(getApplicationContext(),DetailActivity.class);
					in.putExtra("dia", cv.getAsString("dia"));
					in.putExtra("tempo", cv.getAsString("tempo"));
					in.putExtra("maxima", cv.getAsString("maxima"));
					in.putExtra("minima", cv.getAsString("minima"));
					in.putExtra("iuv", cv.getAsString("iuv"));
					startActivity(in);
					break;
				}
 			}
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("OTempo", "Cannot connect RSS!");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("OTempo", "Cannot connect RSS!");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("OTempo", "Cannot connect RSS!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("OTempo", "Cannot connect RSS!");
		}
		
		
		
		
	}

	private class RSSHandler extends DefaultHandler {
		final int stateUnknown = 0;
		final int stateTitle = 1;
		int state = stateUnknown;

		int numberOfPrevisao = 0;
		String strTitle = "";
		String strElement = "";
		String dia = "";
		public ArrayList<ContentValues> lista ;
		
		public RSSHandler() {
			// TODO Auto-generated constructor stub
			lista = new ArrayList<ContentValues>();
		}
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			strTitle = "--- Start Document ---\n";
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			strTitle += "--- End Document ---";
			
		}

		boolean entrouDia = false;
		boolean entrouTempo = false;
		boolean entrouMaxima = false;
		boolean entrouMinima = false;
		boolean entrouiuv = false;
		
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			if (localName.equalsIgnoreCase("dia")) {
				entrouDia = true;
			} else if(localName.equalsIgnoreCase("tempo") ){
				entrouTempo = true;
			} else if(localName.equalsIgnoreCase("maxima") ){
				entrouMaxima = true;
			} else if(localName.equalsIgnoreCase("minima") ){
				entrouMinima = true;
			} else if(localName.equalsIgnoreCase("iuv") ){
				entrouiuv = true;
			}
		}

		
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			
			
		}
		
		public ContentValues previsao;

		public ArrayList<ContentValues> getPrevisao() {
			return lista;
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			if(entrouDia) {
				previsao = new ContentValues();
				previsao.put("dia", new String(ch, start, length));
				entrouDia = false;
			} else if(entrouTempo) {
				previsao.put("tempo", new String(ch, start, length));
				entrouTempo = false;
			} else if(entrouMaxima) {
				previsao.put("maxima", new String(ch, start, length));
				entrouMaxima = false;
			} else if(entrouMinima) {
				previsao.put("minima", new String(ch, start, length));
				entrouMinima = false;
			} else if(entrouiuv) {
				previsao.put("iuv", new String(ch, start, length));
				entrouiuv = false;
				lista.add(previsao);
			}
		}

	}

	private void adapterSimple() {

		

		Map<String, String> map = new HashMap<String, String>();
		map.put("cidade", "Bagé - RS");
		map.put("codigo", "694");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Caxias do Sul - RS");
		map.put("codigo", "1431");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Cruz Alta - RS");
		map.put("codigo", "1695");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Erechim - RS");
		map.put("codigo", "1899");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Lajeado - RS");
		map.put("codigo", "2922");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Passo Fundo - RS");
		map.put("codigo", "3825");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Pelotas - RS");
		map.put("codigo", "3914");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Porto Alegre - RS");
		map.put("codigo", "237");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Rio Grande - RS");
		map.put("codigo", "4397");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Santa Maria - RS");
		map.put("codigo", "4599");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Santa Rosa - RS");
		map.put("codigo", "4634");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("cidade", "Uruguaiana - RS");
		map.put("codigo", "5565");
		list.add(map);

		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), list,
				R.layout.list_item, new String[] { "cidade", "codigo" },
				new int[] { R.id.textView1, R.id.dataText });

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Map<String, String> map = list.get(arg2);
				codigo = map.get("codigo");
				
				new Thread() {
					public void run() {
						verificar();
					};
				}.start();
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
