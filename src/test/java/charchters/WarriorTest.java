package charchters;

import org.example.Armies.WholeArmy;
import org.example.battles.Battle;
import org.example.charchters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WarriorTest {

    private Warrior carl;
    private Warrior bucky;
    private Knight jim;
    private Defender james;
    private Vampire dracula;
    private Lancer lacer;
    private Healer eva;
    private Rookie gogi;

    @BeforeEach
    public void setup() {
        bucky = new Warrior();
        carl = new Warrior();
        jim = new Knight();
        james = new Defender();
        dracula = new Vampire();
        lacer = new Lancer();
        eva = new Healer();
        gogi = new Rookie();
    }

    @Test
    @DisplayName("WarriorVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(50, bucky.getHealth()),
                () -> assertEquals(5, bucky.getAttack()),
                () -> assertTrue(Battle.fight(carl, bucky)),
                () -> assertTrue(carl.isAlive()),
                () -> assertEquals(5, carl.getHealth()),
                () -> assertEquals(0, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("WarriorVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertFalse(Battle.fight(carl, jim)),
                () -> assertFalse(carl.isAlive()),
                () -> assertEquals(-6, carl.getHealth()),
                () -> assertEquals(10, jim.getHealth())
        );
    }

    @Test
    @DisplayName("WarriorVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertFalse(Battle.fight(carl, james)),
                () -> assertFalse(carl.isAlive()),
                () -> assertEquals(-1, carl.getHealth()),
                () -> assertEquals(9, james.getHealth())
        );
    }

    @Test
    @DisplayName("WarriorVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(carl, dracula)),
                () -> assertTrue(carl.isAlive()),
                () -> assertEquals(2, carl.getHealth()),
                () -> assertEquals(-1, dracula.getHealth())
        );
    }


    @Test
    @DisplayName("WarriorVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertFalse(Battle.fight(james, lacer)),
                () -> assertFalse(james.isAlive()),
                () -> assertEquals(0, james.getHealth()),
                () -> assertEquals(5, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("WarriorVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(carl, gogi)),
                () -> assertTrue(carl.isAlive()),
                () -> assertEquals(45, carl.getHealth()),
                () -> assertEquals(0, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("WarriorVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(carl, eva)),
                () -> assertTrue(carl.isAlive()),
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(0, eva.getHealth())
        );
    }

    @DisplayName("WarriorVsArmy")
    @ParameterizedTest
    @MethodSource("testWarriorVsArmyFight")
    void battleKnight(WholeArmy blue, WholeArmy red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testWarriorVsArmyFight() {
        return List.of(
                arguments(
                        new WholeArmy()
                                .addUnit(Warrior::new, 2),
                        new WholeArmy()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Warrior::new, 1),
                        true),
                arguments(
                        new WholeArmy()
                                .addUnit(Warrior::new, 2),
                        new WholeArmy()
                                .addUnit(Knight::new, 1)
                                .addUnit(Knight::new, 1),
                        false),
                arguments(
                        new WholeArmy()
                                .addUnit(Warrior::new, 1),
                        new WholeArmy()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Rookie::new, 1),
                        true),
                arguments(
                        new WholeArmy()
                                .addUnit(Warrior::new, 2),
                        new WholeArmy()
                                .addUnit(Lancer::new, 1)
                                .addUnit(Vampire::new, 1),
                        false),
                arguments(
                        new WholeArmy()
                                .addUnit(Warrior::new, 2),
                        new WholeArmy()
                                .addUnit(Lancer::new, 1)
                                .addUnit(Rookie::new, 1),
                        true)
        );
    }

}