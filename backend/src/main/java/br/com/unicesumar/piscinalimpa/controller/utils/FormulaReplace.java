package br.com.unicesumar.piscinalimpa.controller.utils;

public class FormulaReplace {

    public static String replaceMultiplierAndVolumeIn(String formula, Integer multiplier, Double volume) {
        formula = formula.replace("m", multiplier.toString());
        formula = formula.replace("v", volume.toString());
        return formula;
    }
}
