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
public class evaluateFx {

    ArrayList < BigDecimal > fxResults = new ArrayList < BigDecimal > ();
    ArrayList < BigDecimal > valOfX = new ArrayList < BigDecimal > ();

    BigDecimal Upper, Lower;
    public evaluateFx() {}

    public BigDecimal ComputeFx(BigDecimal x, BigDecimal[] coefficients, int[] degree) {

        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < coefficients.length; i++) {

            BigDecimal pow = x.pow(degree[i]);
            BigDecimal term = coefficients[i].multiply(pow);
            result = result.add(term);
        }
        BigDecimal rounded = result.setScale(10, BigDecimal.ROUND_HALF_UP);
        BigDecimal strippedNumber = rounded.stripTrailingZeros();

        return strippedNumber;

    }

}