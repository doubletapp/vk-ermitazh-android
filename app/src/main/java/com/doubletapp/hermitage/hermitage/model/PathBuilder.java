package com.doubletapp.hermitage.hermitage.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.doubletapp.hermitage.hermitage.model.map.Pass;
import com.doubletapp.hermitage.hermitage.model.map.Path;
import com.doubletapp.hermitage.hermitage.model.map.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathBuilder {

    private Hall fromHall;
    private Hall toHall;
    private Position fromPosition;
    private List<Hall> allHalls;

    PathBuilder(Hall fromHall, Hall toHall, @Nullable Position fromPosition, List<Hall> allHalls) {
        this.fromHall = fromHall;
        this.toHall = toHall;
        this.allHalls = allHalls;
        if (fromPosition != null) {
            this.fromPosition = fromPosition;
        } else {
            this.fromPosition = fromHall.getRoom().getPosition();
        }
    }

    public @NonNull Path getPath() {
        Map<Pass, List<PassPair>> pathPairsForPasses = getPathPairsForPasses();

        Pass[] allPasses = (Pass[])pathPairsForPasses.keySet().toArray();


        return new Path();
    }

    private Map<Pass, List<PassPair>> getPathPairsForPasses() {
        Map<Pass, List<PassPair>> pathPairsForPasses = new HashMap<>();

        List<PassPair> allPasses = getPathPairs();

        for(PassPair pair: allPasses) {
            addPairToPass(pathPairsForPasses, pair, pair.firstPass);
            addPairToPass(pathPairsForPasses, pair, pair.secondPass);
        }

        return pathPairsForPasses;
    }

    private void addPairToPass(Map<Pass, List<PassPair>> pathPairsForPasses, PassPair pair, Pass pass) {
        if (!pathPairsForPasses.containsKey(pass)) {
            pathPairsForPasses.put(pass, new ArrayList<PassPair>());
        }

        pathPairsForPasses.get(pass).add(pair);
    }

    private List<PassPair> getPathPairs() {
        ArrayList<PassPair> allPasses = new ArrayList<>();
        for(Hall hall: allHalls) {
            List<Pass> passes = hall.getRoom().getPasses();
            for(int i = 0; i < passes.size() - 2; i ++) {
                for(int j = i + 1; j < passes.size() - 1; j++) {
                    allPasses.add(new PassPair(passes.get(i), passes.get(j)));
                }
            }
        }

        return allPasses;
    }








    static class PassPair {
        private Pass firstPass;
        private Pass secondPass;

        PassPair(Pass firstPass, Pass secondPass) {
            this.firstPass = firstPass;
            this.secondPass = secondPass;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PassPair) {
                if (firstPass.equals(((PassPair) obj).getFirstPass()) && secondPass.equals(((PassPair) obj).getSecondPass())) {
                    return true;
                }
                if (firstPass.equals(((PassPair) obj).getSecondPass()) && secondPass.equals(((PassPair) obj).getFirstPass())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return firstPass.hashCode() + secondPass.hashCode();
        }

        Pass getFirstPass() {
            return firstPass;
        }

        Pass getSecondPass() {
            return secondPass;
        }

        double getDistance() {
            return firstPass.getDistanceTo(secondPass);
        }
    }
}
