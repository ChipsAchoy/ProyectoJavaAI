/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.GraphGeneration;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
public class PieChartGenerator extends JFrame {

    public PieChartGenerator(String title, java.util.List<String> categories, java.util.List<Integer> values) {
        super(title);

        // Crear el conjunto de datos (dataset)
        PieDataset dataset = createDataset(categories, values);

        // Crear el gráfico
        JFreeChart chart = createChart(dataset);

        // Crear el panel de gráficos y agregarlo al JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private PieDataset createDataset(java.util.List<String> categories, java.util.List<Integer> values) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        for (int i=0; i<categories.size(); i++){
            
            dataset.setValue(categories.get(i), values.get(i));
        }

        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Ejemplo de Gráfico de Pastel", // Título del gráfico
                dataset, // Datos
                true, // Incluye leyenda
                true,
                false);

        // Personalizar el gráfico de pastel
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Categoría A", new Color(0xFF9999));
        plot.setSectionPaint("Categoría B", new Color(0x99FF99));
        plot.setSectionPaint("Categoría C", new Color(0x9999FF));
        plot.setSectionPaint("Categoría D", new Color(0xFFFF99));
        plot.setExplodePercent("Categoría A", 0.10);
        plot.setSimpleLabels(true);
        plot.setLabelBackgroundPaint(Color.WHITE);

        return chart;
    }

}