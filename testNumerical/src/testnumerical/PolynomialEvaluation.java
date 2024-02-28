/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testnumerical;

/**
 *
 * @author Frost
 */
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class PolynomialEvaluation {
    private ArrayList < BigDecimal > fxResults;
    private ArrayList < BigDecimal > valOfX;

    //This is the algorithm where you compute the polynomial function by substituting x
    //its just the same with evaluateFx i just accidentally duplicate it XD
    private BigDecimal computeFx(BigDecimal x, BigDecimal[] coefficients, int[] degrees) {
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < coefficients.length; i++) {
            BigDecimal term = coefficients[i].multiply(x.pow(degrees[i++]));
            result = result.add(term);
        }
        return result;
    }

    public ArrayList < BigDecimal > getValOfX() {
        return valOfX;
    }

    public ArrayList < BigDecimal > getFxResults() {
        return fxResults;
    }

}