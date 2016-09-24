package br.com.targettrust.aulaintegracao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;

public class ServerDelegate {

	private static String urlServidor = "http://10.0.10.135:8080/target/produto/";
	
	public static void setProduto(ProdutoVO produto)  {
		
		try {
			Gson gson = new Gson();
			String json = gson.toJson(produto);
			
			InputStream is = postWebData(urlServidor + "set", json);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null ) {
				str.append(line);
			}
			
			Log.d("ServerDelegate", str.toString());
			
			
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
		}
		
	}

	
	
	public static ProdutoVO getProduto()  {
		
		try {
			InputStream is = getWebData(urlServidor + "get");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null ) {
				str.append(line);
			}
			
			//Log.d("ServerDelegate", str.toString());
			
			// maneira CLÁSSICA
//			JSONObject obj = new JSONObject(str.toString());
//			
//			ProdutoVO produto = new ProdutoVO();
//			produto.setId(obj.getInt("id"));
//			produto.setDescricao(obj.getString("descricao"));
//			produto.setCodigo(obj.getString("codigo"));
//			produto.setPreco(obj.getDouble("preco"));
			
			// alternativa...
			Gson gson = new Gson();
			ProdutoVO produto = gson.fromJson(str.toString(), 
					ProdutoVO.class);
			
			return produto;
			
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
		}
		
		return null;
	}
	

	public static List<ProdutoVO> getProdutos()  {
		
		try {
			InputStream is = getWebData(urlServidor + "all");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null ) {
				str.append(line);
			}
			Gson gson = new Gson();
			List<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
			
			JSONArray array = new JSONArray(str.toString());

			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				ProdutoVO produto = gson.fromJson(obj.toString(), 
						ProdutoVO.class);
				produtos.add(produto);
			}
			
			return produtos;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	private static InputStream getWebData(String url) {

		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		try {
			HttpGet get = new HttpGet(url);

			response = client.execute(get);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				return in;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static InputStream postWebData(String url, String json) {

		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		try {
			HttpPost post = new HttpPost(url);

			StringEntity se = new StringEntity(json);
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, 
					"application/json"));
			
			post.setEntity(se);
			
			response = client.execute(post);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				return in;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
