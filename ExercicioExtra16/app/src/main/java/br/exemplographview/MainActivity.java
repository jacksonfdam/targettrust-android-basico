package br.exemplographview;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int i, tam = 200;
		double v = 0;
		
		// SENO
		GraphViewData[] data = new GraphViewData[tam];
		for(i = 0; i < tam; i++){
			v += 0.3;
			data[i] = new GraphViewData(i, Math.sin(v));
		}
		GraphViewSeries seriesSeno = new GraphViewSeries("Seno", new GraphViewSeriesStyle(Color.BLUE, 3), data);
		
		// COSSENO
		data = new GraphViewData[tam];
		for(i = 0; i < tam; i++){
			v += 0.3;
			data[i] = new GraphViewData(i, Math.cos(v));
		}
		GraphViewSeries seriesCosseno = new GraphViewSeries("Seno", new GraphViewSeriesStyle(Color.GREEN, 3), data);
		
		
		LineGraphView graph = new LineGraphView(this, "Exemplo GraphView");
		graph.addSeries(seriesSeno);
		graph.addSeries(seriesCosseno);
		
		graph.setShowLegend(true);
		graph.setLegendAlign(LegendAlign.BOTTOM);
		
		graph.getGraphViewStyle().setGridColor(Color.GRAY);
		graph.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
		graph.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		graph.getGraphViewStyle().setTextSize(15);
		
		/*graph.setVerticalLabels(new String[]{"y1", "y2", "y3", "y4"});
		graph.setHorizontalLabels(new String[]{"x1", "x2", "x3", "x4"});*/
		
		/*graph.setCustomLabelFormatter(new CustomLabelFormatter(){
			@Override
			public String formatLabel(double value, boolean isValueX) {
				// TODO Auto-generated method stub
				if(isValueX){
					if(value > 2){
						return("x1");
					}
					return("x2");
				}
				else{
					if(value > 2){
						return("y1");
					}
					return("y2");
				}
		}});*/
		
		graph.setViewPort(10, 30);
		graph.setScrollable(true);
		graph.setScalable(true);
		
		/*graph.setDrawBackground(true);
		graph.setBackgroundColor(Color.BLUE);*/
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.graph);
		ll.addView(graph);
	}

}
