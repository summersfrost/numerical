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
public class CalcSecant {
  public void CalcFalsi() {}
  //See CalcBisect Class for walkthrough since the process of this program is almost identical but it just differ on its rules and executions
  public void errorFalsi(String terms, BigDecimal[] coefficients, int[] degree, BigDecimal tempLower1, BigDecimal tempUpper1, BigDecimal marginError, int noIter) {

    ArrayList < BigDecimal > lowerBound = new ArrayList < > ();
    ArrayList < BigDecimal > upperBound = new ArrayList < > ();
    ArrayList < BigDecimal > middleBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxLowerBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxUpperBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxMiddleBound = new ArrayList < > ();
    ArrayList < BigDecimal > subtractError = new ArrayList < > (); // storage for XU-XL 
    ArrayList < Integer > TruthTable = new ArrayList < > ();

    int iterate = 0;

    BigDecimal tempLower = tempLower1;
    BigDecimal tempUpper = tempUpper1;
    BigDecimal tempMiddle;
    // 

    do {

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
      BigDecimal resultLower;
      BigDecimal resultUpper;
      BigDecimal resultMiddle;

      evaluateFx calcFx = new evaluateFx();

      resultLower = calcFx.ComputeFx(tempLower, coefficients, degree);

      resultUpper = calcFx.ComputeFx(tempUpper, coefficients, degree);

      fxLowerBound.add(resultLower);

      fxUpperBound.add(resultUpper);
    
      openMethodFormula falsePos = new openMethodFormula();
      tempMiddle = falsePos.calcSecantMethod(tempLower, tempUpper, resultLower, resultUpper);
      
      middleBound.add(tempMiddle);

      resultMiddle = calcFx.ComputeFx(tempMiddle, coefficients, degree);
      fxMiddleBound.add(resultMiddle);
      //newUpperLower newBounds = new newUpperLower(resultMiddle, tempMiddle, tempLower, tempUpper);
      //newBounds.setBoundaries(resultLower, resultUpper);
      //BigDecimal passtempLower = newBounds.getNewLower();
      //BigDecimal passtempHigher = newBounds.getNewUpper();
      //tempLower = passtempLower;
      //tempUpper = passtempHigher;
       tempLower = tempUpper;
      tempUpper = tempMiddle;

    } while (iterate != 1);

    TabularDisplaySecantMargError table = new TabularDisplaySecantMargError(terms, lowerBound, upperBound, fxLowerBound, fxUpperBound, middleBound, fxMiddleBound, TruthTable, marginError, subtractError);

  }

  public void noIterSecant(String terms, BigDecimal[] coefficients, int[] degree, BigDecimal tempLower1, BigDecimal tempUpper1, int noIter) {

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

      openMethodFormula falsePos = new openMethodFormula();

      tempMiddle = falsePos.calcSecantMethod(tempLower, tempUpper, resultLower, resultUpper);
      middleBound.add(tempMiddle);

      resultMiddle = calcFx.ComputeFx(tempMiddle, coefficients, degree);
      fxMiddleBound.add(resultMiddle);
    //  newUpperLower newBounds = new newUpperLower(resultMiddle, tempMiddle, tempLower, tempUpper);
   //   newBounds.setBoundaries(resultLower, resultUpper);
    //  BigDecimal passtempLower = newBounds.getNewLower();
    //  BigDecimal passtempHigher = newBounds.getNewUpper();
      tempLower = tempUpper;
      tempUpper = tempMiddle;

    }
    tabularDisplaySecantMaxIter secantMaxIter = new tabularDisplaySecantMaxIter(terms, lowerBound, upperBound, fxLowerBound, fxUpperBound, middleBound, fxMiddleBound);

  }

}