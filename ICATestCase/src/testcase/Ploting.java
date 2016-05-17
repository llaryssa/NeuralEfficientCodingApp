//package testcase;
//
//import javax.swing.BoxLayout;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//public class Ploting {
//	private String name;
//	private JFrame frame;
//	
//	public Ploting(String name, double[][] matrix) {
//		this.name = name;
//		frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JPanel panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//		
//		for (int i = 0; i < matrix.length; i ++) {
//			XYDataset dataset = createDataset(matrix, i);
//		}
//		
//		
//	}
//	
//	
//	private XYDataset createDataset(double[][] matrix, int row) {
//		XYSeries series = new XYSeries("");
//		for (int n = 0; n < matrix[0].length; n ++) {
//			series.add(n, matrix[row][n]);
//		}
//		
//		XYSeriesCollection dataset = new XYSeriesCollection();
//		dataset.addSeries(series);
//		return dataset;
//	}
//}
