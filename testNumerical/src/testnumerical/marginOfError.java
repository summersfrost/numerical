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
public class marginOfError {

    public marginOfError() {}
    //This is for condition if its in margoferror mode iteration
    public int bisectionError(BigDecimal upper, BigDecimal lower, BigDecimal marginOfError) {
    	int truthval;
		BigDecimal result =upper.subtract(lower).abs();
		if(marginOfError.compareTo(result)<0||marginOfError.compareTo(result)==0){
			truthval=0;
		}
		else{
			truthval=1;
		}
		return truthval;

    }

    public BigDecimal UpperSubtractLowerVal(BigDecimal xu, BigDecimal xl) {

        MathContext mc = new MathContext(128);
        BigDecimal difference = xu.subtract(xl, mc);
        BigDecimal absoluteError = difference.abs(mc);
        BigDecimal rounded = absoluteError.setScale(10, BigDecimal.ROUND_HALF_UP);
        BigDecimal stripped = rounded.stripTrailingZeros();
        return stripped;
    }

    public int truthValRegula(BigDecimal fxm, BigDecimal marginOfError) {
          int truthVal = 0;
                System.out.println(fxm);
        if (marginOfError.compareTo(fxm.abs()) < 0 || marginOfError.compareTo(fxm.abs()) == 0) {
            truthVal = 0;
        } else {
            truthVal = 1;
        }

        return truthVal;
    }

}