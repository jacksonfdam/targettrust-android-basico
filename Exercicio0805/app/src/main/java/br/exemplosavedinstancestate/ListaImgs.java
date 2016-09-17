package br.exemplosavedinstancestate;

import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Bitmap;

public class ListaImgs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 56464546546546L;
	public ArrayList<Img> imgs;
	public static final String KEY = "imagens";
	
	public ListaImgs(ArrayList<Img> imgs){
		this.imgs = imgs;
	}
}
