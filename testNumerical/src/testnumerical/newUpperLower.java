/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testnumerical;

import java.math.BigDecimal;

/**
 *
 * @author Frost
 */
public class newUpperLower {
    BigDecimal resultMiddle, tempMiddle, tempLower, tempHigher;

    public newUpperLower() {}

    public newUpperLower(BigDecimal resultMiddle, BigDecimal tempMiddle, BigDecimal tempLower, BigDecimal tempHigher) {
        this.resultMiddle = resultMiddle;
        this.tempMiddle = tempMiddle;
        this.tempLower = tempLower;
        this.tempHigher = tempHigher;

    }

    public void setBoundaries(BigDecimal resultLower, BigDecimal resultUpper) {
        BigDecimal zero = new BigDecimal("0");
        if (resultLower.compareTo(zero) < 0 && resultMiddle.compareTo(zero) < 0) {
            tempLower = tempMiddle;
        } else if (resultUpper.compareTo(zero) < 0 && resultMiddle.compareTo(zero) < 0) {
            tempHigher = tempMiddle;
        } else if (resultLower.compareTo(zero) > 0 && resultMiddle.compareTo(zero) > 0) {
            tempLower = tempMiddle;
        } else if (resultUpper.compareTo(zero) > 0 && resultMiddle.compareTo(zero) > 0) {
            tempHigher = tempMiddle;
        }
    }

    public BigDecimal getNewUpper() {
        return tempHigher;
    }
    public BigDecimal getNewLower() {
        return tempLower;
    }
}