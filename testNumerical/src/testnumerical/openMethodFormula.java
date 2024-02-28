/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testnumerical;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Frost
 */
public class openMethodFormula {
    public openMethodFormula() {

    }

    public BigDecimal calcNewtonRhapson(BigDecimal bound, BigDecimal fxdx) {
        BigDecimal result;

        result = bound.subtract(fxdx);

        return result;

    }

    public BigDecimal divideFxFdx(BigDecimal fx, BigDecimal fdx) {
        BigDecimal result;

        result = fx.divide(fdx, MathContext.DECIMAL128);

        return result;

    }
    

    public BigDecimal calcSecantMethod(BigDecimal x0, BigDecimal x1, BigDecimal fx0, BigDecimal fx1) {
        BigDecimal numerator = fx1.multiply(x0.subtract(x1));
        BigDecimal denominator = fx0.subtract(fx1);
        BigDecimal x2 = x1.subtract(numerator.divide(denominator, 10, BigDecimal.ROUND_HALF_UP));
        return x2;
    }


}