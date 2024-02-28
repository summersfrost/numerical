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
public class CalcFalsi {
  public void CalcFalsi() {}
  //See CalcBisect Class for walkthrough since the process of this program is almost identical but it just differ on its rules and executions
    public void errorFalsi(String terms, BigDecimal[] coefficients, int[] degree, BigDecimal tempLower1, BigDecimal tempUpper1, BigDecimal marginError, int noIter) {

    ArrayList < BigDecimal > lowerBound = new ArrayList < > ();
    ArrayList < BigDecimal > upperBound = new ArrayList < > ();
    ArrayList < BigDecimal > middleBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxLowerBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxUpperBound = new ArrayList < > ();
    ArrayList < BigDecimal > fxMiddleBound = new ArrayList < > ();

    ArrayList < Integer > TruthTable = new ArrayList < > ();

    int iterate = 0;

    BigDecimal tempLower = tempLower1;
    BigDecimal tempUpper = tempUpper1;
    BigDecimal tempMiddle;
    // 

    do {
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

      closeMethodFormula falsePos = new closeMethodFormula();

      tempMiddle = falsePos.FalseMethod(tempLower, tempUpper, resultLower, resultUpper);
      middleBound.add(tempMiddle);

      resultMiddle = calcFx.ComputeFx(tempMiddle, coefficients, degree);
      fxMiddleBound.add(resultMiddle);
      newUpperLower newBounds = new newUpperLower(resultMiddle, tempMiddle, tempLower, tempUpper);
      newBounds.setBoundaries(resultLower, resultUpper);
      BigDecimal passtempLower = newBounds.getNewLower();
      BigDecimal passtempHigher = newBounds.getNewUpper();
      tempLower = passtempLower;
      tempUpper = passtempHigher;
      marginOfError margError = new marginOfError();

      iterate = margError.truthValRegula(resultMiddle, marginError);
      TruthTable.add(iterate);
      System.out.println(iterate);
    } while (iterate != 1);

    TabularDisplayRegulaMargError table = new TabularDisplayRegulaMargError(terms, lowerBound, upperBound, fxLowerBound, fxUpperBound, middleBound, fxMiddleBound, TruthTable, marginError);

  }

  public void noIterFalsi(String terms, BigDecimal[] coefficients, int[] degree, BigDecimal tempLower1, BigDecimal tempUpper1, int noIter) {

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

      closeMethodFormula falsePos = new closeMethodFormula();

      tempMiddle = falsePos.FalseMethod(tempLower, tempUpper, resultLower, resultUpper);
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
    tabularDisplayRegulaMaxIter regulaMaxIter = new tabularDisplayRegulaMaxIter(terms, lowerBound, upperBound, fxLowerBound, fxUpperBound, middleBound, fxMiddleBound);

  }

}