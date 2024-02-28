/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testnumerical;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 *
 * @author Frost
 */
public class InitialUpperLower {
    ArrayList < BigDecimal > fxResults = new ArrayList < > ();
    ArrayList < BigDecimal > valOfX = new ArrayList < > ();

    BigDecimal Upper, Lower;
    public InitialUpperLower() {

    }
    //Intended for choosing the lower bound and upper bound which is closest to zero but this is now used to know if the program had a root
    public void autoPick(BigDecimal[] Coefficients, int[] Degree) {

        BigDecimal start = BigDecimal.valueOf(-25);
        int end = 50;
        ArrayList < BigDecimal > fxResults = new ArrayList < > ();
        ArrayList < BigDecimal > valOfX = new ArrayList < > ();
        for (int j = 0; j < end; j++) {
            BigDecimal result;
            valOfX.add(start);
            evaluateFx evaluate = new evaluateFx();
            result = evaluate.ComputeFx(start, Coefficients, Degree);
            //adds 1 to the X 
            start = start.add(BigDecimal.valueOf(1));
            //adds the result to the array
            fxResults.add(result);
        }
        this.fxResults = fxResults;
        this.valOfX = valOfX;
    }

    public ArrayList < BigDecimal > valOfX() {
        return valOfX;
    }

    public ArrayList < BigDecimal > fxResults() {
        return fxResults;
    }

    public BigDecimal getLowerBound() {

        BigDecimal tempLower = BigDecimal.valueOf(Integer.MAX_VALUE);
        BigDecimal compTempLower = BigDecimal.valueOf(Integer.MAX_VALUE);
        MathContext mc = new MathContext(128);
        for (int i = 0; i < fxResults.size(); i++) {
            BigDecimal current = fxResults.get(i);
            BigDecimal currentX = valOfX.get(i);

            if (current.compareTo(BigDecimal.ZERO) < 0) {
                compTempLower = currentX.abs(mc);

                if (tempLower.abs().compareTo(compTempLower) > 0) {

                    tempLower = currentX;

                }

            }

        }
        return tempLower;
    }

    public BigDecimal getUpperBound() {

        BigDecimal tempHigher = BigDecimal.valueOf(Integer.MAX_VALUE);
        BigDecimal compTempHigher = BigDecimal.valueOf(Integer.MAX_VALUE);
        MathContext mc = new MathContext(128);
        for (int i = 0; i < fxResults.size(); i++) {
            BigDecimal current = fxResults.get(i);
            BigDecimal currentX = valOfX.get(i);

            if (current.compareTo(BigDecimal.ZERO) > 0) {
                compTempHigher = currentX.abs(mc);

                if (tempHigher.abs().compareTo(compTempHigher) > 0) {

                    tempHigher = currentX;

                }

            }

        }
        return tempHigher;
    }
    
    //This is for getting all the possible brackets where you can find the root
    public ArrayList < BigDecimal[] > getBracketing() {
        ArrayList < BigDecimal[] > bracketing = new ArrayList < > ();

        for (int i = 0; i < fxResults.size() - 1; i++) {
            BigDecimal current = fxResults.get(i);
            BigDecimal next = fxResults.get(i + 1);
            BigDecimal currentX = valOfX.get(i);
            BigDecimal nextX = valOfX.get(i + 1);

            if (current.signum() != next.signum()) {
                BigDecimal[] bounds = new BigDecimal[2];
                bounds[0] = currentX;
                bounds[1] = nextX;
                bracketing.add(bounds);
            }
        }

        return bracketing;
    }

}