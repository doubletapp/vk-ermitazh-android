package com.doubletapp.hermitage.hermitage.ui.state;

import com.doubletapp.hermitage.hermitage.model.map.Path;

/**
 * Created by navi9 on 20.10.2017.
 */

public class PathState {
    private Path mPath;

    public PathState() {

    }

    public void setPath(Path path) {
        mPath = path;
    }

    public Path getPath() {
        return mPath;
    }
}
