package com.doubletapp.hermitage.hermitage.ui.map;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Hall;
import com.doubletapp.hermitage.hermitage.model.Intensity;
import com.doubletapp.hermitage.hermitage.model.PathBuilder;
import com.doubletapp.hermitage.hermitage.model.map.Pass;
import com.doubletapp.hermitage.hermitage.model.map.Path;
import com.doubletapp.hermitage.hermitage.model.map.Position;
import com.doubletapp.hermitage.hermitage.model.map.Room;
import com.doubletapp.hermitage.hermitage.model.map.User;
import com.doubletapp.hermitage.hermitage.ui.map.mark.HallMarker;
import com.doubletapp.hermitage.hermitage.ui.map.mark.MapMark;
import com.doubletapp.hermitage.hermitage.ui.map.mark.RoomMarker;
import com.doubletapp.hermitage.hermitage.ui.map.mark.UserMark;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;
import com.qozix.tileview.paths.CompositePathView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

public class MapFragment extends Fragment implements MarkerLayout.MarkerTapListener {

    public static final String TAG = "MapFragment";
    Pass[] allPasses;
    Room[] allRooms;
    Hall[] allHalls;
    User user;

    List<RoomMarker> roomMarkers = new ArrayList<>();
    List<HallMarker> hallMarks = new ArrayList<>();
    List<MapMark> mapMarks = new ArrayList<>();
    UserMark userMark;

    List<CompositePathView.DrawablePath> activePaths = new ArrayList<>();

    final int width = 4972;
    final int height = 2568;

    private TileView tileView;
    private MapHelper helper;
    private Subscription updateSubscription;

    String hallId;

    public static MapFragment newInstance() {

        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        tileView = new TileView(getActivity());
//        int width = MetricsConverter.convertDpToPixel(1127, getActivity());
//        int height = MetricsConverter.convertDpToPixel(542, getActivity());


        Bundle bundle = getArguments();
        if (bundle.getString("hallId") != null) {
            hallId = bundle.getString("hallId");
        }

        tileView.setSize(width, height);
        tileView.setScaleLimits(0, 4);
        tileView.setScale(0);
        tileView.setShouldRenderWhilePanning(true);

        tileView.addDetailLevel(1f, "tiles/125/tile-%d-%d.png");

        initData();
//        drawRooms();
        addPasses();

        tileView.post(new Runnable() {
            @Override
            public void run() {
                helper = new MapHelper(tileView, width, height);

                helper.getUpdateObservable().doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                }).subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("event_trace", "compete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("event_trace", "error");
                    }

                    @Override
                    public void onNext(Boolean aVoid) {
                        Log.d("event_trace", "update");
                        updateMapViewState();
                    }
                });

                tileView.setMarkerTapListener(MapFragment.this);
            }
        });

        return tileView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tileView.resume();

        tileView.post(new Runnable() {
            @Override
            public void run() {
                onUserRoomChanged(userRoom());
                focusOnUser();
                Log.d("event_trace", "doOnSubscribe " + tileView.getScale());
            }
        });
    }

    private void updateMapViewState() {
        for (MapMark mapMark : mapMarks) {
            updateMapMarkAttachment(mapMark);
        }
    }

    @Override
    public void onMarkerTap(View view, int x, int y) {
        for (MapMark mapMark : mapMarks) {
            if (mapMark.getView() != null && mapMark.getView().equals(view)) {
                if(mapMark instanceof  HallMarker) {
                    for(CompositePathView.DrawablePath path: activePaths) {
                        tileView.removePath(path);
                    }

                    PathBuilder pathBuilder = new PathBuilder(allRooms);

                    drawPaths(pathBuilder.getPaths(userRoom(), Arrays.asList(
                            ((HallMarker)mapMark).getHall().getMainRoom()
                    ), null));
                }
                Log.d("event_trace", "Click by ");
            }
        }
    }

    private void updateMapMarkAttachment(MapMark mark) {
        if (helper.isMapMarkVisible(mark) && !mark.isAttached() && !isHallWithUser(mark)) {
            mark.attachMark(tileView);
        } else if (!helper.isMapMarkVisible(mark) && mark.isAttached() || isHallWithUser(mark)) {
            mark.detachMark(tileView);
        } else {
            mark.invalidate(tileView);
        }
    }

    private boolean isHallWithUser(MapMark mark) {
        if (mark instanceof HallMarker) {
            HallMarker hallMarker = (HallMarker) mark;

            for (Room room : hallMarker.getHall().getRooms()) {
                if (room.equals(user.getRoom())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void onPause() {
        super.onPause();

        tileView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        tileView.destroy();
        tileView = null;
    }

    private void onUserRoomChanged(Room room) {
        user.setRoom(room);
        userMark.detachMark(tileView);
        userMark.attachMark(tileView);
    }

    private Room userRoom() {
        return roomWithId("14");
    }

    private void focusOnUser() {
        frameTo(userMark.getMarkPosition().getX(), userMark.getMarkPosition().getY());
//        tileView.setScale(2f);
//        updateMapViewState();
    }

    private void addPasses() {
        PathBuilder pathBuilder = new PathBuilder(allRooms);

        if (hallId != null) {
            drawPaths(pathBuilder.getPaths(userRoom(), Arrays.asList(
                    roomWithId(hallId)
            ), null));
        } else {
            drawPaths(pathBuilder.getPaths(userRoom(), Arrays.asList(
                    roomWithId("29"),
                    roomWithId("57"),
                    roomWithId("50"),
                    roomWithId("40"),
                    roomWithId("18"),
                    roomWithId("11")
            ), null));
        }
    }

    private Room roomWithId(String id) {
        for (Hall hall : allHalls) {
            if (hall.getId().equals(id)) {
                return hall.getRooms().get(0);
            }
        }
        return null;
    }

    private void drawPaths(List<Path> paths) {
        for(Path path: paths) {
            List<Pass> passes = path.getPasses();
            android.graphics.Path pathForDrawing = new android.graphics.Path();
            Paint paint = new Paint();

            paint.setStrokeWidth(9);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(ResourcesCompat.getColor(getResources(), R.color.column_blue_with_alpha, null));

            Pass previousPass = passes.get(0);

            if (passes.size() > 0) {
                pathForDrawing.moveTo((float) previousPass.getPosition().getX(), (float) previousPass.getPosition().getY());
            }

            for (int i = 1; i < passes.size(); i++) {
                Pass pass = passes.get(i);
                makeLineBetweenPasses(pathForDrawing, previousPass, pass);
                previousPass = pass;
            }

            CompositePathView.DrawablePath drawablePath = new CompositePathView.DrawablePath();
            drawablePath.path = pathForDrawing;
            drawablePath.paint = paint;
            activePaths.add(drawablePath);
            tileView.drawPath(drawablePath);
        }
    }

    private void makeLineBetweenPasses(android.graphics.Path pathForDrawing, Pass previousPass, Pass nextPass) {

        Room room = previousPass.getCommonRoom(nextPass);
        if (room != null) {
            Double eps = 10.0;

            if (room.getPosition() == nextPass.getPosition()) {
                if (Math.abs(previousPass.getPosition().getX() - room.getStartX()) < eps || Math.abs(previousPass.getPosition().getX() - (room.getStartX() + room.getWidth())) < eps ) {
                    pathForDrawing.cubicTo(
                            (float) nextPass.getPosition().getX(), (float) previousPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) previousPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY()
                    );
                } else {
                    pathForDrawing.cubicTo(
                            (float) previousPass.getPosition().getX(), (float) nextPass.getPosition().getY(),
                            (float) previousPass.getPosition().getX(), (float) nextPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY()
                    );
                }
            } else if (room.getPosition() == previousPass.getPosition()) {
                if (Math.abs(nextPass.getPosition().getX() - room.getStartX()) < eps || Math.abs(nextPass.getPosition().getX() - (room.getStartX() + room.getWidth())) < eps ) {
                    pathForDrawing.cubicTo(
                            (float) previousPass.getPosition().getX(), (float) nextPass.getPosition().getY(),
                            (float) previousPass.getPosition().getX(), (float) nextPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY()
                    );
                } else {
                    pathForDrawing.cubicTo(
                            (float) nextPass.getPosition().getX(), (float) previousPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) previousPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY()
                    );
                }
            } else if (Math.abs(room.getStartY() - previousPass.getPosition().getY()) < eps
                    && Math.abs(room.getStartY() + room.getHeight() - nextPass.getPosition().getY()) < eps) {
                Log.i("DRAWING LINE", "На противоположной стене горизонтально");
                pathForDrawing.cubicTo(
                        (float)previousPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)previousPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)(nextPass.getPosition().getX() + previousPass.getPosition().getX()) / 2, (float)(nextPass.getPosition().getY() + previousPass.getPosition().getY()) / 2
                );
                pathForDrawing.cubicTo(
                        (float)nextPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)nextPass.getPosition().getY()
                );
            } else if (Math.abs(room.getStartY() - nextPass.getPosition().getY()) < eps
                    && Math.abs(room.getStartY() + room.getHeight() - previousPass.getPosition().getY()) < eps) {
                Log.i("DRAWING LINE", "На противоположной стене горизонтально");
                pathForDrawing.cubicTo(
                        (float)previousPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)previousPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)(nextPass.getPosition().getX() + previousPass.getPosition().getX()) / 2, (float)(nextPass.getPosition().getY() + previousPass.getPosition().getY()) / 2
                );
                pathForDrawing.cubicTo(
                        (float)nextPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)room.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)nextPass.getPosition().getY()
                );
            } else if (Math.abs(room.getStartX() - previousPass.getPosition().getX()) < eps
                    && Math.abs(room.getStartX() + room.getWidth() - nextPass.getPosition().getX()) < eps) {
                Log.i("DRAWING LINE", "На противоположной стене вертикально");
                pathForDrawing.cubicTo(
                        (float)room.getPosition().getX(), (float)previousPass.getPosition().getY(),
                        (float)room.getPosition().getX(), (float)previousPass.getPosition().getY(),
                        (float)(nextPass.getPosition().getX() + previousPass.getPosition().getX()) / 2, (float)(nextPass.getPosition().getY() + previousPass.getPosition().getY()) / 2
                );
                pathForDrawing.cubicTo(
                        (float)room.getPosition().getX(), (float)nextPass.getPosition().getY(),
                        (float)room.getPosition().getX(), (float)nextPass.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)nextPass.getPosition().getY()
                );
            } else if (Math.abs(room.getStartX() - nextPass.getPosition().getX()) < eps
                    && Math.abs(room.getStartX() + room.getWidth() - previousPass.getPosition().getX()) < eps) {
                Log.i("DRAWING LINE", "На противоположной стене вертикально");
                pathForDrawing.cubicTo(
                        (float)room.getPosition().getX(), (float)previousPass.getPosition().getY(),
                        (float)room.getPosition().getX(), (float)previousPass.getPosition().getY(),
                        (float)(nextPass.getPosition().getX() + previousPass.getPosition().getX()) / 2, (float)(nextPass.getPosition().getY() + previousPass.getPosition().getY()) / 2
                );
                pathForDrawing.cubicTo(
                        (float)room.getPosition().getX(), (float)nextPass.getPosition().getY(),
                        (float)room.getPosition().getX(), (float)nextPass.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)nextPass.getPosition().getY()
                );
            } else if (Math.abs(previousPass.getPosition().getX() - nextPass.getPosition().getX()) < 10) {
                Log.i("DRAWING LINE", "На одной стене вертикально");
                pathForDrawing.cubicTo(
                        (float)(previousPass.getPosition().getX() + room.getPosition().getX())/2, (float)previousPass.getPosition().getY(),
                        (float)(previousPass.getPosition().getX() + room.getPosition().getX())/2, (float)previousPass.getPosition().getY(),
                        (float)(previousPass.getPosition().getX() + room.getPosition().getX())/2, (float)(previousPass.getPosition().getY() + nextPass.getPosition().getY())/2
                );
                pathForDrawing.cubicTo(
                        (float)(nextPass.getPosition().getX() + room.getPosition().getX())/2, (float)nextPass.getPosition().getY(),
                        (float)(nextPass.getPosition().getX() + room.getPosition().getX())/2, (float)nextPass.getPosition().getY(),
                        (float)nextPass.getPosition().getX(), (float)nextPass.getPosition().getY()
                );
            } else if (Math.abs(previousPass.getPosition().getY() - nextPass.getPosition().getY()) < 10) {
                Log.i("DRAWING LINE", "На одной стене горизонтально");
                pathForDrawing.cubicTo(
                        (float)previousPass.getPosition().getX(), (float)(previousPass.getPosition().getY() + room.getPosition().getY())/2,
                        (float)previousPass.getPosition().getX(), (float)(previousPass.getPosition().getY() + room.getPosition().getY())/2,
                        (float)(previousPass.getPosition().getX() + nextPass.getPosition().getX())/2, (float)(previousPass.getPosition().getY() + room.getPosition().getY())/2
                );
                pathForDrawing.cubicTo(
                        (float)nextPass.getPosition().getX(), (float)(nextPass.getPosition().getY() + room.getPosition().getY())/2,
                        (float)nextPass.getPosition().getX(), (float)(nextPass.getPosition().getY() + room.getPosition().getY())/2,
                        (float)nextPass.getPosition().getX(), (float)nextPass.getPosition().getY()
                );
            } else {
                Log.i("DRAWING LINE", "На смежных стенах");
                if (room.getPosition().getDistanceTo(new Position(nextPass.getPosition().getX(), previousPass.getPosition().getY()))
                        > room.getPosition().getDistanceTo(new Position(previousPass.getPosition().getX(), nextPass.getPosition().getY()))) {
                    pathForDrawing.cubicTo(
                            (float) previousPass.getPosition().getX(), (float) nextPass.getPosition().getY(),
                            (float) previousPass.getPosition().getX(), (float) nextPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY()
                    );
                } else {
                    pathForDrawing.cubicTo(
                            (float) nextPass.getPosition().getX(), (float) previousPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) previousPass.getPosition().getY(),
                            (float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY()
                    );
                }
            }
        } else {
            pathForDrawing.lineTo((float) nextPass.getPosition().getX(), (float) nextPass.getPosition().getY());
        }
    }

    private void initData() {
        Pass point0 = new Pass(739, 274); // Oval
        Pass point1 = new Pass(803, 418); // Oval Copy 11
        Pass point2 = new Pass(867, 418); // Oval Copy 12
        Pass point3 = new Pass(1399, 498); // Oval Copy 16
        Pass point4 = new Pass(1207, 418); // Oval Copy 18
        Pass point5 = new Pass(1271, 498); // Oval Copy 21
        Pass point6 = new Pass(523, 274); // Oval Copy
        Pass point7 = new Pass(387, 274); // Oval Copy 2
        Pass point8 = new Pass(287, 350); // Oval Copy 3
        Pass point9 = new Pass(287, 462); // Oval Copy 4
        Pass point10 = new Pass(287, 570); // Oval Copy 4
        Pass point11 = new Pass(431, 602); // Oval Copy 5
        Pass point12 = new Pass(499, 602); // Oval Copy 6
        Pass point13 = new Pass(519, 638); // Oval Copy 7
        Pass point14 = new Pass(519, 678); // Oval Copy 8
        Pass point15 = new Pass(519, 766); // Oval Copy 9
        Pass point16 = new Pass(519, 854); // Oval Copy 10
        Pass point17 = new Pass(591, 918); // Oval Copy 13
        Pass point18 = new Pass(671, 918); // Oval Copy 14
        Pass point19 = new Pass(701, 968); // Oval Copy 17
        Pass point20 = new Pass(735, 1266); // Oval Copy 69
        Pass point21 = new Pass(843, 1182); // Oval Copy 20
        Pass point22 = new Pass(843, 1342); // Oval Copy 22
        Pass point23 = new Pass(843, 1462); // Oval Copy 23
        Pass point24 = new Pass(843, 1522); // Oval Copy 24
        Pass point25 = new Pass(843, 1574); // Oval Copy 25
        Pass point26 = new Pass(843, 1690); // Oval Copy 26
        Pass point27 = new Pass(735, 1726); // Oval Copy 32
        Pass point28 = new Pass(735, 1790); // Oval Copy 36
        Pass point29 = new Pass(811, 1790); // Oval Copy 39
        Pass point30 = new Pass(943, 1790); // Oval Copy 40
        Pass point31 = new Pass(995, 1790); // Oval Copy 41
        Pass point32 = new Pass(1023, 1846); // Oval Copy 42
        Pass point33 = new Pass(1023, 1902); // Oval Copy 43
        Pass point34 = new Pass(1023, 1990); // Oval Copy 44
        Pass point35 = new Pass(1023, 2090); // Oval Copy 45
        Pass point36 = new Pass(1039, 2122); // Oval Copy 47
        Pass point37 = new Pass(1083, 2178); // Oval Copy 49
        Pass point38 = new Pass(1115, 2198); // Oval Copy 51
        Pass point39 = new Pass(1163, 2198); // Oval Copy 52
        Pass point40 = new Pass(1163, 2122); // Oval Copy 50
        Pass point41 = new Pass(971, 2090); // Oval Copy 46
        Pass point42 = new Pass(947, 2126); // Oval Copy 48
        Pass point43 = new Pass(919, 2086); // Oval Copy 53
        Pass point44 = new Pass(723, 2066); // Oval Copy 54
        Pass point45 = new Pass(707, 2162); // Oval Copy 55
        Pass point46 = new Pass(623, 2282); // Oval Copy 56
        Pass point47 = new Pass(551, 2282); // Oval Copy 58
        Pass point48 = new Pass(479, 2282); // Oval Copy 59
        Pass point49 = new Pass(419, 2238); // Oval Copy 60
        Pass point50 = new Pass(303, 2238); // Oval Copy 61
        Pass point51 = new Pass(279, 2166); // Oval Copy 62
        Pass point52 = new Pass(283, 2042); // Oval Copy 63
        Pass point53 = new Pass(287, 1934); // Oval Copy 64
        Pass point54 = new Pass(375, 1922); // Oval Copy 65
        Pass point55 = new Pass(463, 1922); // Oval Copy 66
        Pass point56 = new Pass(555, 1886); // Oval Copy 67
        Pass point57 = new Pass(555, 1850); // Oval Copy 68
        Pass point58 = new Pass(643, 2238); // Oval Copy 57
        Pass point59 = new Pass(675, 1790); // Oval Copy 37
        Pass point60 = new Pass(611, 1790); // Oval Copy 38
        Pass point61 = new Pass(703, 1346); // Oval Copy 34
        Pass point62 = new Pass(735, 638); // Oval r1_01
        Pass point63 = new Pass(835, 450); // Oval r1 Copy
        Pass point64 = new Pass(839, 574); // Oval r1 Copy 2
        Pass point65 = new Pass(671, 1018); // Oval Copy 15
        Room room0 = new Room(524, 224, 216, 136, point0, point6); // Hall 12
        Hall hall12 = new Hall();
        hall12.setId("12");
        hall12.addRoom(room0);
        Room room1 = new Room(740, 224, 60, 228, point0, point1); // Hall 11
        Hall hall11 = new Hall();
        hall11.setId("11");
        hall11.addRoom(room1);
        Room room2 = new Room(800, 332, 64, 120, point1, point2, point63); // Rectangle 2 Copy 5
        Room room3 = new Room(800, 452, 64, 120, point63, point64); // Rectangle 2 Copy 18
        Room room4 = new Room(732, 572, 308, 116, point62, point64); // Rectangle 2 Copy 21
        Room room5 = new Room(672, 572, 60, 396, point18, point19, point62); // Hall 24
        Hall hall24 = new Hall();
        hall24.setId("24");
        hall24.addRoom(room5);
        Room room6 = new Room(672, 968, 60, 376, point19, point20, point61, point65); // Hall 27
        Hall hall27 = new Hall();
        hall27.setId("27");
        hall27.addRoom(room6);
        Room room7 = new Room(672, 1344, 60, 548, point27, point28, point59, point61); // Hall 33
        Hall hall33 = new Hall();
        hall33.setId("33");
        hall33.addRoom(room7);
        Room room8 = new Room(732, 964, 128, 220, point21); // Hall 26
        Hall hall26 = new Hall();
        hall26.setId("26");
        hall26.addRoom(room8);
        Room room9 = new Room(732, 1184, 128, 160, point20, point21, point22); // Hall 27
        hall27.addRoom(room9);
        Room room10 = new Room(732, 1344, 128, 120, point22, point23); // Hall 28
        Hall hall28 = new Hall();
        hall28.setId("28");
        hall28.addRoom(room10);
        Room room11 = new Room(732, 1464, 128, 56, point23, point24); // Hall 29
        Hall hall29 = new Hall();
        hall29.setId("29");
        hall29.addRoom(room11);
        Room room12 = new Room(732, 1520, 128, 56, point24, point25); // Hall 30
        Hall hall30 = new Hall();
        hall30.setId("30");
        hall30.addRoom(room12);
        Room room13 = new Room(732, 1576, 128, 116, point25, point26); // Hall 34
        Hall hall34 = new Hall();
        hall34.setId("34");
        hall34.addRoom(room13);
        Room room14 = new Room(732, 1692, 128, 68, point26, point27); // Hall 32
        Hall hall32 = new Hall();
        hall32.setId("32");
        hall32.addRoom(room14);
        Room room15 = new Room(732, 1760, 80, 132, point28, point29); // Hall 55
        Hall hall55 = new Hall();
        hall55.setId("55");
        hall55.addRoom(room15);
        Room room16 = new Room(608, 1760, 64, 60, point59, point60); // Rectangle 2 Copy 36
        Room room17 = new Room(500, 1760, 108, 88, point57, point60); // Hall 38
        Hall hall38 = new Hall();
        hall38.setId("38");
        hall38.addRoom(room17);
        Room room18 = new Room(500, 1848, 108, 40, point56, point57); // Hall 39
        Hall hall39 = new Hall();
        hall39.setId("39");
        hall39.addRoom(room18);
        Room room19 = new Room(464, 1888, 124, 44, point55, point56); // Hall 40
        Hall hall40 = new Hall();
        hall40.setId("40");
        hall40.addRoom(room19);
        Room room20 = new Room(376, 1888, 88, 48, point54, point55); // Hall 46
        Hall hall46 = new Hall();
        hall46.setId("46");
        hall46.addRoom(room20);
        Room room21 = new Room(244, 1888, 132, 48, point53, point54); // Hall 47
        Hall hall47 = new Hall();
        hall47.setId("47");
        hall47.addRoom(room21);
        Room room22 = new Room(244, 1936, 92, 108, point52, point53); // Hall 48
        Hall hall48 = new Hall();
        hall48.setId("48");
        hall48.addRoom(room22);
        Room room23 = new Room(244, 2044, 172, 120, point51, point52); // Hall 49
        Hall hall49 = new Hall();
        hall49.setId("49");
        hall49.addRoom(room23);
        Room room24 = new Room(244, 2164, 56, 100, point50, point51); // Hall 50
        Hall hall50 = new Hall();
        hall50.setId("50");
        hall50.addRoom(room24);
        Room room25 = new Room(300, 2164, 116, 100, point49, point50); // Hall 51
        Hall hall51 = new Hall();
        hall51.setId("51");
        hall51.addRoom(room25);
        Room room26 = new Room(416, 2216, 60, 100, point48, point49); // Hall 52
        Hall hall52 = new Hall();
        hall52.setId("52");
        hall52.addRoom(room26);
        Room room27 = new Room(476, 2216, 76, 100, point47, point48); // Hall 53
        Hall hall53 = new Hall();
        hall53.setId("53");
        hall53.addRoom(room27);
        Room room28 = new Room(552, 2216, 68, 100, point46, point47); // Hall 54
        Hall hall54 = new Hall();
        hall54.setId("54");
        hall54.addRoom(room28);
        Room room29 = new Room(620, 2240, 56, 76, point46, point58); // Rectangle 2 Copy 50
        Room room30 = new Room(620, 2164, 100, 76, point45, point58); // Hall 69
        Hall hall69 = new Hall();
        hall69.setId("69");
        hall69.addRoom(room30);
        Room room31 = new Room(676, 2044, 44, 120, point44, point45); // Hall 68
        Hall hall68 = new Hall();
        hall68.setId("68");
        hall68.addRoom(room31);
        Room room32 = new Room(720, 2044, 224, 40, point43, point44); // Rectangle 2 Copy 53
        Room room33 = new Room(896, 2084, 48, 52, point42, point43); // Rectangle 2 Copy 54
        Room room34 = new Room(1036, 2104, 124, 72, point36, point37, point40); // Hall 62
        Hall hall62 = new Hall();
        hall62.setId("62");
        hall62.addRoom(room34);
        Room room35 = new Room(1160, 2104, 88, 116, point39, point40); // Hall 63
        Hall hall63 = new Hall();
        hall63.setId("63");
        hall63.addRoom(room35);
        Room room36 = new Room(1036, 2176, 76, 44, point37, point38); // Rectangle 2 Copy 58
        Room room37 = new Room(1112, 2176, 48, 44, point38, point39); // Rectangle 2 Copy 59
        Room room38 = new Room(944, 2088, 96, 176, point35, point36, point41, point42); // Hall 61
        Hall hall61 = new Hall();
        hall61.setId("61");
        hall61.addRoom(room38);
        Room room39 = new Room(992, 1992, 48, 96, point34, point35); // Hall 60
        Hall hall60 = new Hall();
        hall60.setId("60");
        hall60.addRoom(room39);
        Room room40 = new Room(992, 1904, 48, 88, point33, point34); // Hall 59
        Hall hall59 = new Hall();
        hall59.setId("59");
        hall59.addRoom(room40);
        Room room41 = new Room(992, 1848, 48, 56, point32, point33); // Rectangle 2 Copy 63
        Room room42 = new Room(992, 1760, 48, 88, point31, point32); // Hall 58
        Hall hall58 = new Hall();
        hall58.setId("58");
        hall58.addRoom(room42);
        Room room43 = new Room(944, 1760, 48, 328, point30, point31, point41); // Hall 57
        Hall hall57 = new Hall();
        hall57.setId("57");
        hall57.addRoom(room43);
        Room room44 = new Room(932, 2140, 12, 124); // Hall 61
        hall61.addRoom(room44);
        Room room45 = new Room(468, 1932, 68, 44); // Hall 40
        hall40.addRoom(room45);
        Room room46 = new Room(812, 1760, 132, 132, point29, point30); // Hall 56
        Hall hall56 = new Hall();
        hall56.setId("56");
        hall56.addRoom(room46);
        Room room47 = new Room(716, 496, 84, 76); // Rectangle 2 Copy 19
        Room room48 = new Room(864, 372, 340, 76, point2, point4); // Rectangle 2 Copy 8
        Room room49 = new Room(1204, 372, 68, 156, point4, point5); // Rectangle 2 Copy 9
        Room room50 = new Room(1272, 464, 128, 64, point3, point5); // Rectangle 2 Copy 10
        Room room51 = new Room(384, 224, 140, 136, point6, point7); // Hall 13
        Hall hall13 = new Hall();
        hall13.setId("13");
        hall13.addRoom(room51);
        Room room52 = new Room(248, 224, 140, 128, point7, point8); // Hall 14
        Hall hall14 = new Hall();
        hall14.setId("14");
        hall14.addRoom(room52);
        Room room53 = new Room(248, 348, 136, 116, point8, point9); // Hall 15
        Hall hall15 = new Hall();
        hall15.setId("15");
        hall15.addRoom(room53);
        Room room54 = new Room(248, 464, 136, 108, point9, point10); // Hall 16
        Hall hall16 = new Hall();
        hall16.setId("16");
        hall16.addRoom(room54);
        Room room55 = new Room(248, 572, 184, 68, point10, point11); // Rectangle 2 Copy 7
        Room room56 = new Room(432, 572, 68, 68, point11, point12); // Hall 17
        Hall hall17 = new Hall();
        hall17.setId("17");
        hall17.addRoom(room56);
        Room room57 = new Room(500, 572, 92, 68, point12, point13); // Hall 18
        Hall hall18 = new Hall();
        hall18.setId("18");
        hall18.addRoom(room57);
        Room room58 = new Room(500, 640, 92, 40, point13, point14); // Hall 19
        Hall hall19 = new Hall();
        hall19.setId("19");
        hall19.addRoom(room58);
        Room room59 = new Room(500, 680, 92, 88, point14, point15); // Hall 20
        Hall hall20 = new Hall();
        hall20.setId("20");
        hall20.addRoom(room59);
        Room room60 = new Room(476, 768, 116, 88, point15, point16); // Hall 21
        Hall hall21 = new Hall();
        hall21.setId("21");
        hall21.addRoom(room60);
        Room room61 = new Room(476, 856, 116, 112, point16, point17); // Hall 22
        Hall hall22 = new Hall();
        hall22.setId("22");
        hall22.addRoom(room61);
        Room room62 = new Room(592, 856, 80, 112, point17, point18); // Hall 23
        Hall hall23 = new Hall();
        hall23.setId("23");
        hall23.addRoom(room62);
        Room room63 = new Room(496, 968, 176, 92, point65); // Rectangle 2 Copy 20
        Pass[] allPasses = new Pass[] {point0, point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12, point13, point14, point15, point16, point17, point18, point19, point20, point21, point22, point23, point24, point25, point26, point27, point28, point29, point30, point31, point32, point33, point34, point35, point36, point37, point38, point39, point40, point41, point42, point43, point44, point45, point46, point47, point48, point49, point50, point51, point52, point53, point54, point55, point56, point57, point58, point59, point60, point61, point62, point63, point64, point65};
        Room[] allRooms = new Room[] {room0, room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16, room17, room18, room19, room20, room21, room22, room23, room24, room25, room26, room27, room28, room29, room30, room31, room32, room33, room34, room35, room36, room37, room38, room39, room40, room41, room42, room43, room44, room45, room46, room47, room48, room49, room50, room51, room52, room53, room54, room55, room56, room57, room58, room59, room60, room61, room62, room63};
        Hall[] allHalls = new Hall[] {hall11, hall12, hall13, hall14, hall15, hall16, hall17, hall18, hall19, hall20, hall21, hall22, hall23, hall24, hall26, hall27, hall28, hall29, hall30, hall32, hall33, hall34, hall38, hall39, hall40, hall46, hall47, hall48, hall49, hall50, hall51, hall52, hall53, hall54, hall55, hall56, hall57, hall58, hall59, hall60, hall61, hall62, hall63, hall68, hall69};

        hall15.setIntensity(Intensity.HIGH);
        hall16.setIntensity(Intensity.HIGH);
        hall55.setIntensity(Intensity.HIGH);
        hall56.setIntensity(Intensity.HIGH);
        hall57.setIntensity(Intensity.HIGH);
        hall58.setIntensity(Intensity.HIGH);
        hall59.setIntensity(Intensity.HIGH);
        hall60.setIntensity(Intensity.HIGH);
        hall60.setIntensity(Intensity.MEDIUM);
        hall22.setIntensity(Intensity.MEDIUM);
        hall17.setIntensity(Intensity.MEDIUM);

        this.allHalls = allHalls;
        this.allPasses = allPasses;
        this.allRooms = allRooms;

        for (Room room : allRooms) {
            roomMarkers.add(new RoomMarker(getActivity(), room));
        }

        for (Hall hall : allHalls) {
            HallMarker hallMarker = new HallMarker(getActivity(), hall);
            hallMarks.add(hallMarker);
            mapMarks.add(hallMarker);
        }

        user = new User();
        userMark = new UserMark(getActivity(), user);
        mapMarks.add(userMark);
    }

    public void frameTo(final double x, final double y) {
        tileView.post(new Runnable() {
            @Override
            public void run() {
                tileView.scrollToAndCenter(x, y);
            }
        });
    }
}
