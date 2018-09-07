package knn;

import java.util.ArrayList;
import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.DistanceFunction;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;
import weka.core.neighboursearch.BallTree;
import weka.core.neighboursearch.PerformanceStats;

public class KNN {

	public static void main(String[] args) throws Exception {
		Attribute d1 = new Attribute("d1");
		Attribute d2 = new Attribute("d2");
		Attribute d3 = new Attribute("d3");
		Attribute d4 = new Attribute("d4");
		Attribute d5 = new Attribute("d5");
		Attribute d6 = new Attribute("d6");
		
		ArrayList<Attribute> attrs = new ArrayList<Attribute>();
		attrs.add(d1);
		attrs.add(d2);
		attrs.add(d3);
		attrs.add(d4);
		attrs.add(d5);
		attrs.add(d6);
		
		Instances dataset = new Instances("Attributes", attrs, 0);
		
		Instance c0 = new DenseInstance(6);
		c0.setValue(d1, 1.0);
		c0.setValue(d2, 1.0);
		c0.setValue(d3, 1.0);
		c0.setValue(d4, 1.0);
		c0.setValue(d5, 1.0);
		c0.setValue(d6, 1.0);
		
		Instance c1 = new DenseInstance(6);
		c1.setValue(d1, 1.0);
		c1.setValue(d2, 0.0);
		c1.setValue(d3, 0.0);
		c1.setValue(d4, 1.0);
		c1.setValue(d5, 0.0);
		c1.setValue(d6, 0.0);
		
		Instance c2 = new DenseInstance(6);
		c2.setValue(d1, 0.0);
		c2.setValue(d2, 1.0);
		c2.setValue(d3, 0.0);
		c2.setValue(d4, 0.0);
		c2.setValue(d5, 1.0);
		c2.setValue(d6, 1.0);
		
		dataset.add(c1);
		dataset.add(c2);

		c0.setDataset(dataset);
		
		BallTree cover = new BallTree();
		cover.setInstances(dataset);
		cover.setDistanceFunction(new DistanceFunction() {
			
			public void setOptions(String[] arg0) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			public Enumeration<Option> listOptions() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String[] getOptions() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void update(Instance arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setInvertSelection(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setInstances(Instances arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setAttributeIndices(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void postProcessDistances(double[] arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean getInvertSelection() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Instances getInstances() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getAttributeIndices() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public double distance(Instance arg0, Instance arg1, double arg2, PerformanceStats arg3) {
				// TODO Auto-generated method stub
				return distance(arg0, arg1);
			}
			
			public double distance(Instance arg0, Instance arg1, double arg2) {
				// TODO Auto-generated method stub
				return distance(arg0, arg1);
			}
			
			public double distance(Instance arg0, Instance arg1, PerformanceStats arg2) throws Exception {
				// TODO Auto-generated method stub
				return distance(arg0, arg1);
			}
			
			public double distance(Instance arg0, Instance arg1) {
				double sum = 0.0;
				for (int i=0; i< arg0.numAttributes();i++) {
					double sq = Math.pow((arg0.value(i) - arg1.value(i)),2);
					sum += sq;
				}
				
				return Math.sqrt(sum);
			}
			
			public void clean() {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Instance nearest = cover.nearestNeighbour(c0);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Nearest neighbour is: ");
		for (Attribute d : attrs) {
			sb.append(nearest.value(d) + "/");
		}
		System.out.println(sb.toString());
		double[] dist = cover.getDistances();
		System.out.println("Distance is:"+dist[0]);
		
		double similarity = calculateSimilarity(dist[0], c0.numAttributes());
		System.out.printf("Similarity is: %.2f", similarity);
		
	}

	private static double calculateSimilarity(double distance, int numAttributes) {
		return (numAttributes - (distance*distance)) / numAttributes;
	}

}
