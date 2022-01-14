// Import data
import java.util.Scanner;
import java.io.File;

// class
class Main {
  public static void main(String[] args) {
    // using training file
     Scanner file = null;
    try {
      file = new Scanner(new File("Training"));
    } catch (Exception e){
      System.out.println("File did not open.");
      System.exit(-1);
    }
    // array list creation
      int[] Red = new int[1000];
      int[] Blue = new int[1000];
      int[] Green = new int[1000];
      int[] correct = new int[1000];
      
      
      for(int i = 0; i < 1000; i++){
        Red[i] = file.nextInt();
        Blue[i] = file.nextInt();
        Green[i] = file.nextInt();
        correct[i] = file.nextInt();
  
      }

      file.close();
      // weights from math.random
      double weight1 = Math.random();
      double weight2 = Math.random();
      double weight3 = Math.random();
      double threshold = Math.random() * 1000;

// nested loop for learning rates

for(int j = 0; j < 1000; j++){
  int count = 0;
      for(int i = 0; i< 1000; i++){
        int predictedOutput = 1;
        double learningRate = 0.0001;
        double weightedSum = (Red[i] * weight1) + (Blue[i]* weight2) + (Green[i] * weight3);
        if(weightedSum < threshold)
        {
          predictedOutput = -1;
        }
  double error = (correct[i] - predictedOutput);

  if(error == 0)
  {
    count++;
  }
        if(error != 0)
        {
          weight1 = (weight1 + error * learningRate * Red[i]);
          weight2 = (weight2 + error * learningRate * Blue[i]);
          weight3 = (weight3 + error * learningRate * Green[i]);
          threshold = (threshold + error * learningRate * 1);
        }
        else
        {
          continue;
        }
      }
 }


// Testing code below
// used the test file to test my perceptron
    Scanner file1 = null;
    try {
      file1 = new Scanner(new File("Test"));
    } catch (Exception e){
      System.out.println("File did not open.");
      System.exit(-1);
    }
// created arrays for "Test" File
      int[] redTest = new int[100];
      int[] blueTest = new int[100];
      int[] greenTest = new int[100];
      int[] correctTest = new int[100];

      int[] predictedTest = new int[100];

      // looped the array 100 times
      for(int z = 0; z < 100; z++){
        redTest[z] = file1.nextInt();
        blueTest[z] = file1.nextInt();
        greenTest[z] = file1.nextInt();
        correctTest[z] = file1.nextInt();
      }
      
      // variable for printing the guessed and actually guessed
      int outputTest = 0;
      int num_Black = 0;
      int num_White = 0;
      int num_acc_White = 0;
      int num_acc_Black = 0;
      
      // put in for loop for 100 cases
      for(int x = 0; x < 100; x++){
        double testCalculation = (redTest[x] * weight1) + (blueTest[x]* weight2) + (greenTest[x] * weight3);
      // statements to find the number of each variable
      // using different instances where each variable should increase by
        if(testCalculation < threshold){
          outputTest = -1;
          num_White++;
        
          if(outputTest != correctTest[x]){
            num_acc_White++;
          }
        }
        else{
          outputTest = 1;
          num_Black++;
          if(outputTest != correctTest[x]){
            num_acc_Black++;
          }
        }
      }

      int totalBW = num_White + num_Black;
// printing all variables above testing data
      System.out.println("___________________________________________________________________");
      // printing confusion matrix using the variables above
      System.out.println("                        CONFUSION MATRIX");
      System.out.println("___________________________________________________________________");
      System.out.println("Guessed Better on Black that is actually Black: " + num_Black);
      System.out.println("Guessed Better on White that is actually White: " + num_White);
      System.out.println("Guessed Better on Black that is actually White: " + num_acc_Black);
      System.out.println("Guessed Better on White that is actually Black: " + num_acc_White);
      System.out.println("Total: " + totalBW);
      
      // code below is the precision and recall which will be printed
      int guessedRightPercentage = totalBW / 100 * 100;

      double precisionForWhite = Math.round((num_White / (double)(num_White + num_acc_White) * 100)* 100)/100.0;

      double precisionForBlack = Math.round((num_Black / (double)(num_Black + num_acc_Black) * 100)* 100)/100.0;

      double recallForWhite = Math.round((num_White / (double)(num_White + num_acc_Black) * 100)* 100)/100.0;

      double recallForBlack = Math.round((num_Black / (double)(num_Black + num_acc_White) * 100)* 100)/100.0;

      // printing of the code above
      System.out.println("");
      System.out.println("The percentage of guesses the perception guessed right are " + guessedRightPercentage + "%"); 
      System.out.println("The precision for White " + precisionForWhite + "%"); 
      System.out.println("The precision for Black " + precisionForBlack + "%"); 
      System.out.println("The recall for White " + recallForWhite + "%"); 
      System.out.println("The recall for Black " + recallForBlack + "%"); 
    
      file1.close();
  }
}




