package battles;

import Armies.Army;
import charchters.Knight;
import charchters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BattleTest {

    private Warrior carl;
    private Warrior bucky;
    private Knight jim;
    private Army blueArmy;
    private Army redArmy;

    @BeforeEach
    public void setup() {
        // Char Setup
        carl = new Warrior();
        bucky = new Warrior();
        jim = new Knight();

        // Armies setup
        blueArmy = new Army();
        redArmy = new Army();
    }

    @Test
    @DisplayName("Fight 1")
    void firstFight() {
        assertFalse(Battle.fight(carl, jim));
    }

    @Test
    @DisplayName("Fight 2")
    void secondFight() {
        assertTrue(Battle.fight(jim, carl));
    }

    @Test
    @DisplayName("Fight 3")
    void thirdFight() {
        Battle.fight(carl, bucky);
        assertTrue(carl.isAlive());
    }

    @Test
    @DisplayName("Fight 4")
    void fourthFight() {
        Battle.fight(jim, carl);
        assertTrue(jim.isAlive());
    }

    @Test
    @DisplayName("Fight 5")
    void fifthFight() {
        Battle.fight(carl, bucky);
        assertFalse(bucky.isAlive());
    }

    @Test
    @DisplayName("Fight 6")
    void sixthFight() {
        Battle.fight(bucky, jim);
        assertTrue(jim.isAlive());
    }

    @Test
    @DisplayName("Fight 7")
    void seventhFight() {
        Battle.fight(carl, jim);
        assertFalse(Battle.fight(jim, bucky));
    }

    //Battle test where two armies fight
    @Test
    @DisplayName("Battle 1")
    void firstBattle() {
        blueArmy.addUnit(Warrior::new, 1);
        redArmy.addUnit(Warrior::new, 2);

        assertFalse(Battle.fight(blueArmy, redArmy));
    }

    static List<Arguments> testTwoWarriorFight() {
        return List.of(
                Arguments.arguments(
                        new Army()
                                .addUnit(Warrior::new, 5)
                                .addUnit(Warrior::new, 5),
                        new Army()
                                .addUnit(Warrior::new, 5)
                                .addUnit(Warrior::new, 5),
                        true)
        );
    }

    @Test
    @DisplayName("Battle 2")
    void secondBattle() {
        blueArmy.addUnit(Warrior::new, 2);
        redArmy.addUnit(Warrior::new, 3);
        assertFalse(Battle.fight(blueArmy, redArmy));
    }

    @Test
    @DisplayName("Battle 3")
    void thirdBattle() {
        blueArmy.addUnit(Warrior::new, 5);
        redArmy.addUnit(Warrior::new, 7);
        assertFalse(Battle.fight(blueArmy, redArmy));
    }

    @Test
    @DisplayName("Battle 4")
    void fourthBattle() {
        blueArmy.addUnit(Warrior::new, 20);
        redArmy.addUnit(Warrior::new, 21);
        assertTrue(Battle.fight(blueArmy, redArmy));
    }

    @Test
    @DisplayName("Battle 5")
    void fifthBattle() {
        blueArmy.addUnit(Warrior::new, 10);
        redArmy.addUnit(Warrior::new, 11);
        assertTrue(Battle.fight(blueArmy, redArmy));
    }

    @Test
    @DisplayName("Battle 6")
    void sixthBattle() {
        blueArmy.addUnit(Warrior::new, 11);
        redArmy.addUnit(Warrior::new, 7);
        assertTrue(Battle.fight(blueArmy, redArmy));
    }

}