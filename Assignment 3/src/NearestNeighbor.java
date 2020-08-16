/* Programming Fundamentals
 * NAME: Michael Carrasco
 * 8/16/2020
 * PROGRAMMING ASSIGNMENT 3
 * Summer 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighbor {

	public static void main(String[] args) throws FileNotFoundException {
		heading();

		
		//creates empty values for our arrays
	    double [][] testingVal= new double[75][4]; 
	    double [][] trainingVal= new double[75][4];
	    String trainingClassLabel [] = new String [75];
	    String testingClassLabel [] = new String [75];
	    String predictedClassLabel [] =new String [75];
		
		//User inputs for file names
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the name of the training file: "); 
		String training=input.nextLine();
		System.out.print("Enter the name of the testing file: ");
		String testing=input.nextLine();
		
		
		//creates array for our training values
		File trainingFile = new File(training);
	    Scanner fileScanTraining = new Scanner(trainingFile);
	    for (int i = 0; fileScanTraining.hasNextLine(); i++) { 
	        String str = fileScanTraining.nextLine();
	        String[] numbers = str.split(",", 5);
	        trainingClassLabel[i]=numbers[4];
	        for (int j = 0; j < 4; j++) { 
	            trainingVal[i][j] = Double.parseDouble(numbers[j]);
	            }
	    }
	        
	    
	    //creates array for our testing values
		File testingFile = new File(testing);
	    Scanner fileScanTesting = new Scanner(testingFile);
	    for (int i = 0; fileScanTesting.hasNextLine(); i++) { 
	        String str = fileScanTesting.nextLine();
	        String[] numbers = str.split(",", 5); 
	        testingClassLabel[i]=numbers[4];
	        for (int j = 0; j < 4; j++) { 
	            testingVal[i][j] = Double.parseDouble(numbers[j]);
	        }
	    }
	    //prints out our accuracy
	    accuracy(testingClassLabel, prediction(testingVal,trainingVal,trainingClassLabel,predictedClassLabel));
	    
	    //Finally we close our scanners
	    fileScanTraining.close();
	    fileScanTesting.close();
	    input.close();
	}
	
	
	//Uses our distance formula to find the shortest distance between our value and our test values.
	public static int leastdistance(double sly, double swy, double ply, double pwy, double[][] trainingVal) {
		
		int rowNum = 0;
		double slTotal=Math.pow((trainingVal[0][0]-sly),2); 
		double swTotal=Math.pow((trainingVal[0][1]-swy),2);
		double plTotal=Math.pow((trainingVal[0][2]-ply),2);
		double pwTotal=Math.pow((trainingVal[0][3]-pwy),2);
		double distance=Math.sqrt(slTotal+swTotal+plTotal+pwTotal);
		for(int i = 0; i < 75; i++) {
			double slTest=Math.pow((trainingVal[i][0]-sly),2); 
			double swTest=Math.pow((trainingVal[i][1]-swy),2);
			double plTest=Math.pow((trainingVal[i][2]-ply),2);
			double pwTest=Math.pow((trainingVal[i][3]-pwy),2);
			double testDistance=Math.sqrt(slTest+swTest+plTest+pwTest);
				if (testDistance < distance) {
					rowNum = i;
					distance = testDistance;
	
			}
		}
		
		return rowNum;
	}
	
	//This method calculates our closes training label from the given testing values
    private static String[] prediction(double testingVal[][],double trainingVal[][],String [] trainingClassLabel,String [] predictedClassLabel) {
    	for (int i = 0; i < 75; i++) {
    		int closestPrediction = leastdistance(testingVal[i][0], testingVal[i][1],testingVal[i][2], testingVal[i][3],trainingVal);
    		predictedClassLabel[i] = trainingClassLabel[closestPrediction];
    	}
		return predictedClassLabel;
    }
	
	
	//This method finds the accuracy of our correct values from our total values
	public static void accuracy(String[]trueLabel, String[]predictedLabel) {
			int count=0;
			double correctVal = 0;
			double accurate = 0;
			System.out.println("\nEX#: TRUE LABEL, PREDICTED LABEL");
			for (int i = 0; i < 75; i++) {
				count++;
				System.out.println(count+": "+trueLabel[i] + " " + predictedLabel[i]);
				if (trueLabel[i].equals(predictedLabel[i])) {
				correctVal++;
				accurate=(correctVal/75);
				}
			}
		System.out.print("ACCURACY: "+  accurate);
	}
	
	
	//this method creates a print out for our heading
	public static String heading() {
	String output=("Programming Fundamentals\n"+"NAME: Michael Carrasco\n"+"PROGRAMMING ASSIGNMENT 3\n");
	System.out.println(output);
	return output;
	}
}


