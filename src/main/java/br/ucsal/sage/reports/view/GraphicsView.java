package br.ucsal.sage.reports.view;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;

public class GraphicsView extends JFrame{
	
	private static ChartPanel chart2;
	
	public static void setChart(ChartPanel cp) {
		chart2 = cp;
	}
	
	public GraphicsView() {
		
		this.add(chart2);
		
		this.pack();
		
		//setExtendedState(MAXIMIZED_BOTH);
	}

	public static void main(String[] args) {
		new GraphicsView().setVisible(true);

	}

}
