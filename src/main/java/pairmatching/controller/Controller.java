package pairmatching.controller;

import static pairmatching.domain.Crews.*;
import static pairmatching.domain.Missions.*;
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
        InitService.initCrew();
        InitService.initMission();

        while (true) {
            String mainOption = InputView.selectFunction();
            if ("1".equals(mainOption)) {
                matchingPair();
            } else if ("2".equals(mainOption)) {

            } else if ("3".equals(mainOption)) {

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
        List<Pair> pairList = Pairs.createPair(crewList, mission);

        OutputView.alarmPairMatchingResult();
        for (Pair pair : pairList) {
            OutputView.showPairMatchingResult(pair.getCrews());
        }


    }
}
