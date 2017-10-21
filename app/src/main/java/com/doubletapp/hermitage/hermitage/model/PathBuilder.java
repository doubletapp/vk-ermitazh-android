package com.doubletapp.hermitage.hermitage.model;

import android.support.annotation.NonNull;

import com.doubletapp.hermitage.hermitage.model.map.Pass;
import com.doubletapp.hermitage.hermitage.model.map.Path;
import com.doubletapp.hermitage.hermitage.model.map.Position;
import com.doubletapp.hermitage.hermitage.model.map.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PathBuilder {

    private List<Room> allRooms;
    private Map<Pass, List<Pass>> pathRelations;

    public PathBuilder(List<Room> allRooms) {
        this.allRooms = allRooms;

        initializePathRelations();
    }

    public PathBuilder(Room... rooms) {
        this.allRooms = Arrays.asList(rooms);

        initializePathRelations();
    }

    private void initializePathRelations() {
        pathRelations = new HashMap<>();

        for (Room room: allRooms) {
            List<Pass> passes = room.getPasses();

            for (int i = 0; i < passes.size(); i++) {
                for (int j = 0; j < passes.size(); j++) {
                    if (i != j) {
                        addPassRelation(pathRelations, passes.get(i), passes.get(j));
                    }
                }
            }
        }
    }

    private void addPassRelation(Map<Pass, List<Pass>> roomRelations, Pass passKey, Pass passRelation) {
        if (!roomRelations.containsKey(passKey)) {
            roomRelations.put(passKey, new ArrayList<Pass>());
        }

        roomRelations.get(passKey).add(passRelation);
    }

    public @NonNull Path getPath(Hall fromHall, List<Hall> halls, Position fromPosition) {

        double minDistance = Double.MAX_VALUE;
        List<Pass> minDistancePlasses = null;

        for (Pass pass: fromHall.getRoom().getPasses()) {
            List<Pass> listOfPasses = pathFromPass(pass, halls);
            double distance = 0;
            for(int i = 0; i < listOfPasses.size() - 1; i++) {
                distance += listOfPasses.get(i).getDistanceTo(listOfPasses.get(i + 1));
            }
            if (distance < minDistance) {
                minDistance = distance;
                minDistancePlasses = listOfPasses;
            }
        }

        Path path = new Path();
        path.addPasses(minDistancePlasses);

        return path;
    }

    private List<Pass> pathFromPass(Pass rootPass, List<Hall> halls) {
        Set<Pass> keyset = pathRelations.keySet();
        Pass[] passes = keyset.toArray(new Pass[keyset.size()]);
        int[] previousPasses = getPreviouses(rootPass, passes);

        List<Pass> listOfPasses = Arrays.asList(passes);

        Pass minPass = null;
        double minDistance = Double.MAX_VALUE;

        for (Hall hall: halls) {
            for (Pass pass: hall.getRoom().getPasses()) {

                double distance = getDistance(pass, listOfPasses, previousPasses);

                if (distance < minDistance) {
                    minDistance = distance;
                    minPass = pass;
                }
            }
        }

        List<Pass> path = new LinkedList<>();

        int indexOfPass = listOfPasses.indexOf(minPass);

        while (previousPasses[indexOfPass] != -1) {
            path.add(0, passes[indexOfPass]);
            indexOfPass = previousPasses[indexOfPass];
        }

        path.add(0, rootPass);

        return path;
    }

    private double getDistance(Pass fromPass, List<Pass> listOfPasses, int[] previousPasses) {
        int indexOfPass = listOfPasses.indexOf(fromPass);

        double distance = 0.0;

        while (previousPasses[indexOfPass] != -1) {
            distance += listOfPasses.get(indexOfPass).getDistanceTo(listOfPasses.get(previousPasses[indexOfPass]));
            indexOfPass = previousPasses[indexOfPass];
        }

        return distance;
    }

    private int[] getPreviouses(Pass rootPass, Pass[] passes) {

        int capacity = passes.length;

        double[][] connections = new double[capacity][capacity];

        for (int i = 0; i < capacity; i++) {
            Pass pass = passes[i];
            List<Pass> connectedPasses = pathRelations.get(pass);
            for (int j = 0; j < capacity; j++) {
                if(connectedPasses.contains(passes[j])) {
                    connections[i][j] = pass.getDistanceTo(passes[j]);
                } else {
                    connections[i][j] = Double.MAX_VALUE;
                }
            }
        }

        double[] distances = new double[capacity];
        int[] previous = new int[capacity];
        boolean[] used = new boolean[capacity];

        int indexOfRootPath = Arrays.asList(passes).indexOf(rootPass);

        Arrays.fill(distances, Double.MAX_VALUE);
        Arrays.fill(previous, -1);
        Arrays.fill(used, false);

        distances[indexOfRootPath] = 0;
        previous[indexOfRootPath] = -1;

        int minIndex;
        double temp;

        do {
            minIndex = -1;
            double min = Double.MAX_VALUE;

            for (int i = 0; i < capacity; i++) {
                if (!used[i] && distances[i] < min) {
                    min = distances[i];
                    minIndex = i;
                }
            }

            if (minIndex != -1) {
                for (int i = 0; i < capacity; i++) {
                    if (connections[minIndex][i] < Double.MAX_VALUE) {
                        temp = min + connections[minIndex][i];
                        if (temp < distances[i]) {
                            distances[i] = temp;
                            previous[i] = minIndex;
                        }
                    }
                }

                used[minIndex] = true;
            }
        } while (minIndex != -1);

        return previous;
    }
}
