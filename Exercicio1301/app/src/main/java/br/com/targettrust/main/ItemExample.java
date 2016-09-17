package br.com.targettrust.main;

import java.util.ArrayList;
import java.util.List;

public class ItemExample {

	private int id;
	private String name;
	private String description;
	
	public ItemExample (int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	
	public static List<ItemExample> getListExamples() {
		List<ItemExample> listExamples = new ArrayList<ItemExample>();
		ItemExample itemExample;

		itemExample = new ItemExample(0, "Exemplo 0", "1 Activity com 3 partes (sem fragments)");
		listExamples.add(itemExample);
		
		itemExample = new ItemExample(1, "Exemplo 0", "1 Activity com 3 partes (com fragments)");
		listExamples.add(itemExample);
		
		itemExample = new ItemExample(2, "Exemplo 2", "1 Fragment de menu e outro para detalhes, layout em portrait e landscape");
		listExamples.add(itemExample);

		return listExamples; 
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}
	
	
	
	
}
