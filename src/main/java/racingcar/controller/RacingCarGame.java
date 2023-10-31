package racingcar.controller;

import java.util.*;
import camp.nextstep.edu.missionutils.Console;
import racingcar.model.RacingCar;
import racingcar.model.RacingCarRace;
import racingcar.view.RacingCarGameView;

public class RacingCarGame {
    RacingCarRace race;
    RacingCarGameView view;

    public RacingCarGame() {
        view = new RacingCarGameView();
    }

    public void run() {
        this.prepare();
        this.start();
    }

    private void prepare() {
        String[] carNames = this.getCarName();
        int totalMoveCount = this.getTotalMoveCount();

        List<RacingCar> racingCars = Arrays.stream(carNames).map(RacingCar::new).toList();

        this.race = new RacingCarRace(racingCars, totalMoveCount);
    }

    private void start() {
        view.printResultText();

        for(int n=0; n<this.race.getTotalMoveCount(); n++) {
            this.race.move();
            view.printResult(this.race.getCarsStatus());
        }

        view.printWinner(this.race.getWinnerCarNames());
    }


    private String[] getCarName() {
        view.enterCarNames();
        String carNamesStr = Console.readLine();
        // TODO 유효성 체크
        return carNamesStr.split(",");
    }

    private int getTotalMoveCount() {
        view.enterTotalMoveCount();
        String totalMoveCountStr = Console.readLine();
        // TODO 유효성 체크
        return Integer.parseInt(totalMoveCountStr);
    }
}
