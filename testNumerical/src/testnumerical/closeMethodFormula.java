/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testnumerical;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Frost
 */
public class closeMethodFormula {

    public closeMethodFormula() {

    }

    public BigDecimal Bisection(BigDecimal lower, BigDecimal upper) {

        BigDecimal result = lower.add(upper).divide(new BigDecimal("2"));
        return result;

    }

    public BigDecimal FalseMethod(BigDecimal xl, BigDecimal xu, BigDecimal fxl, BigDecimal fxu) {
        BigDecimal result = xu.subtract(fxu.multiply(xu.subtract(xl)).divide(fxu.subtract(fxl), MathContext.DECIMAL128));

        return result;
    }

}