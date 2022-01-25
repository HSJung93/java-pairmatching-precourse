package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Missions {
    private static Map<Level, List<Mission>> levelListMap = new HashMap<>();
    private List<Mission> level1Missions = new ArrayList<>();
    private List<Mission> level2Missions = new ArrayList<>();
    private List<Mission> level4Missions = new ArrayList<>();

    public void addLevel1Mission(List<String> missionNames) {
        for (String missionName : missionNames) {
            level1Missions.add(new Mission(Level.LEVEL1, missionName));
        }
    }

    public void addLevel2Mission(List<String> missionNames) {
        for (String missionName : missionNames) {
            level2Missions.add(new Mission(Level.LEVEL2, missionName));
        }
    }

    public void addLevel4Mission(List<String> missionNames) {
        for (String missionName : missionNames) {
            level4Missions.add(new Mission(Level.LEVEL4, missionName));
        }
    }

    public void addMapCrewList() {
        levelListMap.put(Level.LEVEL1, level1Missions);
        levelListMap.put(Level.LEVEL2, level1Missions);
        levelListMap.put(Level.LEVEL4, level1Missions);

    }

    public static List<Mission> getCrewList(Level level) {
        return levelListMap.get(level);
    }
}
