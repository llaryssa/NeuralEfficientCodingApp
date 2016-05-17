package testcase;

public class MainEntry {
	public static void main(String[] args) throws Exception {
		TestCase tc = new TestCase();
		
		tc.setUpSource();
		tc.setUpMixingMatrix();
		tc.testFastICA();
	}

}
