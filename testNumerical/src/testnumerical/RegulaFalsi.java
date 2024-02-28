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
public class RegulaFalsi {
   public RegulaFalsi(){
       
   }
    public static void falsePos(){
          PolynomialGUI gui= new PolynomialGUI();
      
           while (gui.isVisible()) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
       BigDecimal[] coefficients = gui.getCoefficients();
    int[] degree= gui.getPowers();
    String terms=gui.getPolynomial();
    
    
    // Print coefficients and powers to console
    for (int i = 0; i < coefficients.length; i++) {
      //  System.out.println("Coefficient for x^" + powers[i] + " = " + coefficients[i]);
       System.out.println("Coefficient i=" +coefficients[i]);
       System.out.println("Degree i= " + degree[i]);
       
      
    }
        InitialBounds upLow=new InitialBounds(terms,coefficients,degree);
           while (upLow.isVisible()) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      
    }
        BigDecimal tempLower= upLow.getLowerBound();
        BigDecimal tempUpper= upLow.getUpperBound();
         
        int noIter =upLow.getNoIter();
        BigDecimal marginError = upLow.getError();
        int modeIterRegula=upLow.getModeIter();
        
        System.out.println("LowerBound: "+ tempLower);
        System.out.println("UpperBound: "+ tempUpper);
        System.out.println("NoIter: " + noIter);
        System.out.println("Margin of Error " + marginError );
        System.out.println("Mode of Iter:"+ modeIterRegula);
        CalcFalsi regula=new CalcFalsi();
        if(modeIterRegula==1){
        regula.errorFalsi(terms,coefficients, degree, tempLower, tempUpper, marginError, noIter);
    }else{regula.noIterFalsi(terms, coefficients, degree, tempLower, tempUpper, noIter);}
    }
    
}
