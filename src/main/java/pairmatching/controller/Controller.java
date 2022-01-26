package pairmatching.controller;

import static pairmatching.domain.Crews.*;
import static pairmatching.domain.Missions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.InitService;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {

    private Mission mission;
    private List<Crew> crewList = new ArrayList<>();

    public void init() {
        setInitStatus();

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

    private void setInitStatus() {
        try {
            InitService.initCrew();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        InitService.initMission();
    }

    public void matchingPair() {
        inputMissionWithErrorHandling();

        String pairRematching = "";
        if (Pairs.isContainPairing(mission)) {
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


    private void inputMissionWithErrorHandling() {

        try {
            String missionInformation = InputView.inputMission();
            String[] splitInformation = missionInformation.split(",");

            if (splitInformation.length < 3) {
                throw new IllegalArgumentException("[ERROR] 과정, 레벨, 미션을 다 입력해주셔야 합니다.");
            }

            Level level = Level.mappingStringValue(splitInformation[1].trim());

            crewList = getCrewList(Course.mappingStringValue(splitInformation[0].trim()));
            List<Mission> missionList = getMissionList(level);

            mission = getMission(level, splitInformation[2].trim());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputMissionWithErrorHandling();
        }
    }

    public void showPair() {
        inputMissionWithErrorHandling();

        try {
            OutputView.alarmPairMatchingResult();
            for (Pair pair : Pairs.getPairList(mission)) {
                OutputView.showPairMatchingResult(getCrewNames(pair.getCrews()));
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }

    public void initPair() {
        Pairs.initializePairs();
        OutputView.initPair();
    }
}
