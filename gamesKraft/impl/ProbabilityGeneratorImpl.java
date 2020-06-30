package gamesKraft.impl;

import gamesKraft.ProbabilityGenerator;

import java.util.Map;
import java.util.Random;


public class ProbabilityGeneratorImpl implements ProbabilityGenerator {

    private Map<String,Double> probMap;

    @Override
    public String getNextString() {

        Random random = new Random();
        Double randomD = random.nextDouble();

        for(String str: probMap.keySet()){

            if(probMap.get(str) > randomD){
                return str;
            }
        }

        return null;
    }

    public ProbabilityGeneratorImpl(Map<String, Double> probMap) {
        this.probMap = probMap;
    }
}
