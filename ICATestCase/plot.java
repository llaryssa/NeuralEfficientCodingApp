package com.blogspot.shulgadim.ica;

import java.awt.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class MultiPlot {
    private String name;
    private JFrame frame;
    public MultiPlot(String name, double y[][]) {
        this.name = name;
        frame = new JFrame("MuliPlot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(int row=0; row< y.length; row++){
            XYDataset dataset = createDataset(y,row);
            JFreeChart chart = createChart(dataset, row);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(1400,100));
            panel.add(chartPanel);
        }
        frame.getContentPane().add(new JScrollPane(panel));
        frame.setSize(1200, 600);
        frame.setVisible(true);
    }

    public MultiPlot(String name, double y[][][]) {
        this.name = name;
        frame = new JFrame("MuliPlot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        int M = y.length;
        int N = y[0].length;
        int row = 0;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                XYDataset dataset = createDataset(y,i,j);
                JFreeChart chart = createChart(dataset, row);
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(1400,100));
                panel.add(chartPanel);
                row++;
            }
        }
        frame.getContentPane().add(new JScrollPane(panel));
        frame.setSize(1200, 600);
        frame.setVisible(true);
    }


    private XYDataset createDataset(double[][] y, int row) {
        XYSeries series = new XYSeries("");
        for(int i=0; i<y[0].length;i++){
            series.add(i,y[row][i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private XYDataset createDataset(double[][][] y, int i, int j) {
        XYSeries series = new XYSeries("");
        int K = y[0][0].length;
        for(int k=0; k<K;k++){
            series.add(k,y[i][j][k]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset, int row) {

        final JFreeChart chart = ChartFactory.createXYLineChart(
            name + String.valueOf(row),                         //chart title
            "",                        // x axis label
            "",        // y axis label
            dataset,                    // data
            PlotOrientation.VERTICAL,
            false,                      // include legend
            false,                      // tooltips
            false                       // urls
        );
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        return chart;
    }
}
