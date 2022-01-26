package pairmatching.controller;

import static pairmatching.domain.Crews.*;
import static pairmatching.domain.Missions.*;
import java.io.IOException;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;
import pairmatching.service.InitService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {
    public void init() {
        try {
            InitService.initCrew();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        InitService.initMission();

        while (true) {
            String mainOption = InputView.selectFunction();
            if ("1".equals(mainOption)) {
                matchingPair();
            } else if ("2".equals(mainOption)) {
                showPair();
            } else if ("3".equals(mainOption)) {
                initPair();
            } else if ("Q".equals(mainOption)) {
                break;
            }
        }
    }

    public void matchingPair() {
        String missionInformation = InputView.inputMission();
        String[] splitInformation = missionInformation.split(",");
        Course course = Course.mappingStringValue(splitInformation[0].trim());
        Level level = Level.mappingStringValue(splitInformation[1].trim());

        List<Crew> crewList = getCrewList(course);
        List<Mission> missionList = getMissionList(level);

        Mission mission = getMission(level, splitInformation[2].trim());
        // List<Pair> pairList = Pairs.createPair(crewList, mission);

        String pairRematching = "";
        if (Pairs.isExistPair(mission)) {
            pairRematching = InputView.inputPairRematching().trim();
        }

        if (pairRematching.isEmpty() || "네".equals(pairRematching)) {
            Pairs.createPair(crewList, mission);
        }

        OutputView.alarmPairMatchingResult();
        for (Pair pair : Pairs.getPairList(mission)) {
            OutputView.showPairMatchingResult(getCrewNames(pair.getCrews()));
        }


    }


    public void showPair() {
        String missionInformation = InputView.inputMission();
        String[] splitInformation = missionInformation.split(",");

        Course course = Course.mappingStringValue(splitInformation[0].trim());
        Level level = Level.mappingStringValue(splitInformation[1].trim());

        List<Crew> crewList = getCrewList(course);
        List<Mission> = getMission(level, splitInformation[2].trim());

        if(Pairs.isExistPair(mission)){
            OutputView.alarmPairMatchingResult();
        }

        for(Pair pair: Pairs.getPairList(mission)){
            OutputView.showPairMatchingResult(getCrewNames(pair.getCrews()));
        }else{
            System.out.println("[ERROR] 매칭 이력이 없습니다.");
        }


    }

    public void initPair() {
        Pairs.initializePairs();
        OutputView.initPair();
    }
}
