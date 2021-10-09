import biuoop.GUI;
import general.Game.Level1;
import general.Game.Level2;
import general.Game.Level3;
import general.Game.Level4;
import general.Game.AnimationRunner;
import general.Game.LevelInformation;
import general.Game.GameFlow;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Ass6Game - class that start the game.
 */
public class Ass6Game {

    /**
     * main - this is the main method of the program.
     * this method starts the game
     *
     * @param args String[]
     */
    public static void main(String[] args) {

        List<LevelInformation> levelInformationListOptions = new ArrayList<>();
        levelInformationListOptions.add(new Level1());
        levelInformationListOptions.add(new Level2());
        levelInformationListOptions.add(new Level3());
        levelInformationListOptions.add(new Level4());

        List<LevelInformation> levelInformationList = new LinkedList<>();

        boolean atLeastOneNumber = false;
        for (String s : args) {
            try {
                int levelNumber = Integer.parseInt(s);
                if (levelNumber >= 1 && levelNumber <= 4) {
                    atLeastOneNumber = true;
                    levelInformationList.add(levelInformationListOptions.get(levelNumber - 1));
                }
            } catch (Exception e) {
                System.out.println("You entered wrong input but its OK, we ignored that.");
            }
        }

        // in case of there is no valid input or any input at all.
        if (!atLeastOneNumber) {
            levelInformationList.addAll(levelInformationListOptions);
        }


        GUI gui = new GUI("game", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());
        gameFlow.runLevels(levelInformationList);

        gui.close();
    }
}
