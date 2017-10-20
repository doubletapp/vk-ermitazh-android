package com.doubletapp.hermitage.hermitage.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Path {
    private List<Pass> mPasses;

    public Path() {
        mPasses = new ArrayList<>();
    }

    public Path addPass(Pass pass) {
        mPasses.add(pass);
        return this;
    }

    public List<Pass> getPasses() {
        return mPasses;
    }
}
