package tec.triviaid.proyectoaitrivia.GraphGeneration;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ChartUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PieChartGenerator extends JFrame {

    private JFreeChart chart;
    private String titleS;

    public PieChartGenerator(String title, java.util.List<String> categories, java.util.List<Integer> values) {
        super(title);
        this.titleS = title;
        // Crear el conjunto de datos (dataset)
        PieDataset dataset = createDataset(categories, values);

        // Crear el gráfico
        chart = createChart(dataset);

        // Crear el panel de gráficos y agregarlo al JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        setContentPane(chartPanel);

        
        
    }

    private PieDataset createDataset(java.util.List<String> categories, java.util.List<Integer> values) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < categories.size(); i++) {
            dataset.setValue(categories.get(i), values.get(i));
        }

        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                this.titleS, // Título del gráfico
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

    public void saveChartAsImage(File file, int width, int height) {
        try {
            ChartUtils.saveChartAsPNG(file, chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            java.util.List<String> categories = java.util.Arrays.asList("Categoría A", "Categoría B", "Categoría C", "Categoría D");
            java.util.List<Integer> values = java.util.Arrays.asList(50, 30, 10, 10);

            PieChartGenerator example = new PieChartGenerator("Gráfico de Pastel Ejemplo", categories, values);
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);

            // Guardar el gráfico como imagen
            File imageFile = new File("piechart.png");
            example.saveChartAsImage(imageFile, 800, 600);
        });
    }
*/
}
