package charchters;

import org.example.Armies.Army;
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

class VampireTest {

    private IWarrior bucky;
    private Knight jim;
    private Defender james;
    private Vampire dracula;
    private Vampire contier;
    private Lancer lacer;
    private Healer eva;
    private Rookie gogi;

    @BeforeEach
    public void setup() {
        bucky = new Warrior();
        jim = new Knight();
        james = new Defender();
        dracula = new Vampire();
        contier = new Vampire();
        lacer = new Lancer();
        eva = new Healer();
        gogi = new Rookie();
    }

    @Test
    @DisplayName("VampireVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(dracula, bucky)),
                () -> assertTrue(dracula.isAlive()),
                () -> assertEquals(4, dracula.getHealth()),
                () -> assertEquals(-2, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("VampireVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertFalse(Battle.fight(dracula, jim)),
                () -> assertFalse(dracula.isAlive()),
                () -> assertEquals(-2, dracula.getHealth()),
                () -> assertEquals(18, jim.getHealth())
        );
    }

    @Test
    @DisplayName("VampireVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertFalse(Battle.fight(dracula, james)),
                () -> assertFalse(dracula.isAlive()),
                () -> assertEquals(-1, dracula.getHealth()),
                () -> assertEquals(20, james.getHealth())
        );
    }

    @Test
    @DisplayName("VampireVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertEquals(40, contier.getHealth()),
                () -> assertEquals(4, contier.getAttack()),
                () -> assertTrue(Battle.fight(dracula, contier)),
                () -> assertTrue(dracula.isAlive()),
                () -> assertEquals(4, dracula.getHealth()),
                () -> assertEquals(0, contier.getHealth())
        );
    }


    @Test
    @DisplayName("VampireVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertFalse(Battle.fight(dracula, lacer)),
                () -> assertFalse(dracula.isAlive()),
                () -> assertEquals(-2, dracula.getHealth()),
                () -> assertEquals(10, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("VampireVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(dracula, gogi)),
                () -> assertTrue(dracula.isAlive()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(-2, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("VampireVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(dracula, eva)),
                () -> assertTrue(dracula.isAlive()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(0, eva.getHealth())
        );
    }

    @DisplayName("VampireVsArmy")
    @ParameterizedTest
    @MethodSource("testVampireVsArmyFight")
    void battleVampire(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testVampireVsArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Vampire::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Warrior::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Vampire::new, 2),
                        new Army()
                                .addUnit(Defender::new, 1)
                                .addUnit(Defender::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Vampire::new, 1),
                        new Army()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Rookie::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Defender::new, 1)
                                .addUnit(Vampire::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Vampire::new, 1)
                                .addUnit(Defender::new, 1),
                        false)
        );
    }
}