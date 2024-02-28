/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testnumerical;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Frost
 */
public class CalcBisect {
    //This class contains the process that calculates the Bisection this comments are applicable to regula falsi since it just have minor revisions for its condition and its formula
  public void CalcBisect() {}
  
  //This method is executed when margin of error or the epsilon is used rather than just having the desired number of iteration
  public void errorBisect(String terms, BigDecimal[] coefficients, int[] degree, BigDecimal tempLower1, BigDecimal tempUpper1, BigDecimal marginError, int noIter) {

    ArrayList < BigDecimal > lowerBound = new ArrayList < > (); //storage for lowerbound
    ArrayList < BigDecimal > upperBound = new ArrayList < > (); // storage for upperbound
    ArrayList < BigDecimal > middleBound = new ArrayList < > (); // storage for middlebound
    ArrayList < BigDecimal > fxLowerBound = new ArrayList < > (); // storage for the result of polynomial when substituted by the lowerbound
    ArrayList < BigDecimal > fxUpperBound = new ArrayList < > (); // storage for the result of polynomial when substituted by the upperbound
    ArrayList < BigDecimal > fxMiddleBound = new ArrayList < > (); // storage for the result of polynomial when substituted by the middlebound

    ArrayList < Integer > TruthTable = new ArrayList < > (); //storage for the conditions in iteration if its meet or not
    ArrayList < BigDecimal > subtractError = new ArrayList < > (); // storage for XU-XL 

    int iterate = 0;
    // gets the value that is stored in the method this is for having the first iteration
    BigDecimal tempLower = tempLower1; 
    BigDecimal tempUpper = tempUpper1;
    // initializes middleBound
    BigDecimal tempMiddle;
    // Do while is used since we dont know until when the condition is meet
    do {
        
      //store the temporary upper and lower bounds use for table display later on
      upperBound.add(tempUpper);
      lowerBound.add(tempLower);
      
      //Function for recognizing if the loop will stop
      marginOfError margError = new marginOfError();
      BigDecimal subtract = margError.UpperSubtractLowerVal(tempUpper, tempLower);
      //stores it in the arraylist for table purposes later on 
      //ArrayList is used since we dont know the number of iteration that will happen this will prevent having out of bounds error 
      subtractError.add(subtract);
      iterate = margError.bisectionError(tempUpper, tempLower, marginError);
      TruthTable.add(iterate);

        //For Fx Function which the bounds will be substituted
      BigDecimal resultLower;
      BigDecimal resultUpper;
      BigDecimal resultMiddle;
      // class holding the algorithm in solving polynomial
      evaluateFx calcFx = new evaluateFx();
      
      //calculate and store it in the table
      resultLower = calcFx.ComputeFx(tempLower, coefficients, degree);

      resultUpper = calcFx.ComputeFx(tempUpper, coefficients, degree);

      fxLowerBound.add(resultLower);

      fxUpperBound.add(resultUpper);
      //Initializes the class that holds the bisection formula and stores value
      closeMethodFormula bisect = new closeMethodFormula();

      tempMiddle = bisect.Bisection(tempLower, tempUpper);
      middleBound.add(tempMiddle);

      resultMiddle = calcFx.ComputeFx(tempMiddle, coefficients, degree);
      fxMiddleBound.add(resultMiddle);
      newUpperLower newBounds = new newUpperLower(resultMiddle, tempMiddle, tempLower, tempUpper);
      newBounds.setBoundaries(resultLower, resultUpper);
      BigDecimal passtempLower = newBounds.getNewLower();
      BigDecimal passtempHigher = newBounds.getNewUpper();
      tempLower = passtempLower;
      tempUpper = passtempHigher;

    } while (iterate != 1); //The loop will stop if it satisfies the condition
    //Display the table
    TabularDisplayBisectionMargError table = new TabularDisplayBisectionMargError(terms, lowerBound, upperBound, fxLowerBound, fxUpperBound, middleBound, fxMiddleBound, TruthTable, marginError, subtractError);

  }
  //Same process is used but this time instead of do while loop we're now using for loop since we know the number of iteration when will the program will stop
  public void noIterBisect(String terms, BigDecimal[] coefficients, int[] degree, BigDecimal tempLower1, BigDecimal tempUpper1, int noIter) {

    ArrayList < BigDecimal > lowerBound = new ArrayList < > ();
    ArrayList < BigDecimal > upperBound = new ArrayList < > ();
    ArrayList < BigDecimal > middleBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxLowerBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxUpperBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxMiddleBound = new ArrayList < > ();

    BigDecimal tempLower = tempLower1;
    BigDecimal tempUpper = tempUpper1;
    BigDecimal tempMiddle;

    for (int i = 0; i < noIter; i++) {

      upperBound.add(tempUpper);
      lowerBound.add(tempLower);

      BigDecimal resultLower;
      BigDecimal resultUpper;
      BigDecimal resultMiddle;

      evaluateFx calcFx = new evaluateFx();

      resultLower = calcFx.ComputeFx(tempLower, coefficients, degree);

      resultUpper = calcFx.ComputeFx(tempUpper, coefficients, degree);

      fxLowerBound.add(resultLower);

      fxUpperBound.add(resultUpper);

      closeMethodFormula bisect = new closeMethodFormula();

      tempMiddle = bisect.Bisection(tempLower, tempUpper);
      middleBound.add(tempMiddle);

      resultMiddle = calcFx.ComputeFx(tempMiddle, coefficients, degree);
      fxMiddleBound.add(resultMiddle);
      newUpperLower newBounds = new newUpperLower(resultMiddle, tempMiddle, tempLower, tempUpper);
      newBounds.setBoundaries(resultLower, resultUpper);
      BigDecimal passtempLower = newBounds.getNewLower();
      BigDecimal passtempHigher = newBounds.getNewUpper();
      tempLower = passtempLower;
      tempUpper = passtempHigher;

    }
    tabularDisplayBisectionMaxIter bisectionMaxIter = new tabularDisplayBisectionMaxIter(terms, lowerBound, upperBound, fxLowerBound, fxUpperBound, middleBound, fxMiddleBound);

  }

}