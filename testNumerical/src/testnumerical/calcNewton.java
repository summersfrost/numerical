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
public class calcNewton {
    public calcNewton() {}

    public void newtonMarginError(String terms, String Derive, int[] degree, BigDecimal[] coefficient, int[] deriveDegree, BigDecimal[] deriveCoefficient, BigDecimal Bounds, BigDecimal marginError) {

ArrayList<BigDecimal>bound=new ArrayList<>();
ArrayList<BigDecimal> fx=new ArrayList<>();
ArrayList<BigDecimal> fdx=new ArrayList<>();
ArrayList<BigDecimal> newBound=new ArrayList<>();
ArrayList<BigDecimal>fxfdx=new ArrayList<>();
ArrayList<Integer> TruthTable= new ArrayList<>();
ArrayList<BigDecimal> subtractError=new ArrayList<>();
  
   int iterate=0;
       BigDecimal tempBound=Bounds;
       do{
           
           BigDecimal noDerive,withDerive;
           
           bound.add(tempBound);
           
           evaluateFx evaluate=new evaluateFx();
           noDerive=evaluate.ComputeFx(tempBound, coefficient, degree);
           withDerive=evaluate.ComputeFx(tempBound,deriveCoefficient,deriveDegree);
           
         openMethodFormula newton=new openMethodFormula();
         
        BigDecimal fxdx=   newton.divideFxFdx(noDerive, withDerive);
        BigDecimal newerbound =newton.calcNewtonRhapson(Bounds, fxdx);
  
        
        fx.add(noDerive);
        fdx.add(withDerive);
        fxfdx.add(fxdx);
        newBound.add(newerbound);
    
        marginOfError error=new marginOfError();
       iterate= error.bisectionError(tempBound,newerbound, marginError);
       BigDecimal subtract=error.UpperSubtractLowerVal(tempBound, newerbound);
       subtractError.add(subtract);
       
        TruthTable.add(iterate);
        
        tempBound=newerbound;
       

        } while (iterate != 1);
        
       System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %-25s %-25s%n", "Iteration #", "Xi", "Fxi",
            "f'xi", "Fx/F'x", "Xi-fx/fdx", "Xprev - Xnew", "Error");
        for (int i = 0; i < TruthTable.size(); i++) {
            BigDecimal boundaries = bound.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal funcFx = fx.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal funcFdx = fdx.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal funcFxdx = fxfdx.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal newBoundaries = newBound.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();

            BigDecimal subError = subtractError.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();

            String boundString = boundaries.toPlainString();
            String funcFxString = funcFx.toPlainString();
            String funcFdxString = funcFdx.toPlainString();
            String funcFxdxString = funcFxdx.toPlainString();
            String newBoundariesString = newBoundaries.toPlainString();

            String margError = TruthTable.get(i).toString();
            String subErrorString = subError.toPlainString();
            System.out.printf("%-15d %-25s  %-25s %-25s %-25s %-25s %-25s %-25s %n", (i + 1), boundString, funcFxString, funcFdxString, funcFxdxString, newBoundariesString, subErrorString, margError);
        }

        TabularDisplayNewtonMargError displayNewton = new TabularDisplayNewtonMargError(terms, Derive, bound, fx, fdx, newBound, fxfdx, subtractError, TruthTable, marginError);
    }

    public void newtonNoIter(String terms, String Derive, int[] degree, BigDecimal[] coefficient, int[] deriveDegree, BigDecimal[] deriveCoefficient, BigDecimal Bounds, int noIter) {

        ArrayList < BigDecimal > bound = new ArrayList < > ();
        ArrayList < BigDecimal > fx = new ArrayList < > ();
        ArrayList < BigDecimal > fdx = new ArrayList < > ();
        ArrayList < BigDecimal > newBound = new ArrayList < > ();
        ArrayList < BigDecimal > fxfdx = new ArrayList < > ();

        BigDecimal tempBound = Bounds;
        for (int i = 0; i < noIter; i++) {

            BigDecimal noDerive, withDerive;

            bound.add(tempBound);

            evaluateFx evaluate = new evaluateFx();
            noDerive = evaluate.ComputeFx(tempBound, coefficient, degree);
            withDerive = evaluate.ComputeFx(tempBound, deriveCoefficient, deriveDegree);

            openMethodFormula newton = new openMethodFormula();

            BigDecimal fxdx = newton.divideFxFdx(noDerive, withDerive);
            BigDecimal newerbound = newton.calcNewtonRhapson(Bounds, fxdx);

            fx.add(noDerive);
            fdx.add(withDerive);
            fxfdx.add(fxdx);
            newBound.add(newerbound);

            tempBound = newerbound;

        }
        System.out.printf("%-15s %-25s %-25s %-25s %-25s  %-25s%n", "Iteration #", "Xi", "Fxi",
            "f'xi", "Fx/F'x", "Xi-fx/fdx");
        for (int i = 0; i < bound.size(); i++) {
            BigDecimal boundaries = bound.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal funcFx = fx.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal funcFdx = fdx.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal funcFxdx = fxfdx.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            BigDecimal newBoundaries = newBound.get(i).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();

            String boundString = boundaries.toPlainString();
            String funcFxString = funcFx.toPlainString();
            String funcFdxString = funcFdx.toPlainString();
            String funcFxdxString = funcFxdx.toPlainString();
            String newBoundariesString = newBoundaries.toPlainString();

            // Fix the format specifiers to match the data types
            System.out.printf("%-15d %-25s  %-25s  %-25s %-25s %-25s %n", (i + 1), boundString, funcFxString, funcFdxString, funcFxdxString, newBoundariesString);
        }

        TabularDisplayNewtonNoIter displayNewton = new TabularDisplayNewtonNoIter(terms, Derive, bound, fx, fdx, newBound, fxfdx);
    }
}