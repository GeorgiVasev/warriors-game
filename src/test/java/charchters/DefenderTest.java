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

class DefenderTest {

    private IWarrior bucky;
    private Knight jim;
    private Defender bob;
    private Defender james;
    private Vampire dracula;
    private Lancer lacer;
    private Healer eva;
    private Rookie gogi;

    @BeforeEach
    public void setup() {
        bucky = new Warrior();
        jim = new Knight();
        bob = new Defender();
        james = new Defender();
        dracula = new Vampire();
        lacer = new Lancer();
        eva = new Healer();
        gogi = new Rookie();
    }

    @Test
    @DisplayName("DefenderVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(50, bucky.getHealth()),
                () -> assertEquals(5, bucky.getAttack()),
                () -> assertTrue(Battle.fight(james, bucky)),
                () -> assertTrue(james.isAlive()),
                () -> assertEquals(12, james.getHealth()),
                () -> assertEquals(-1, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("DefenderVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertFalse(Battle.fight(james, jim)),
                () -> assertFalse(james.isAlive()),
                () -> assertEquals(0, james.getHealth()),
                () -> assertEquals(14, jim.getHealth())
        );
    }

    @Test
    @DisplayName("DefenderVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(60, bob.getHealth()),
                () -> assertEquals(3, bob.getAttack()),
                () -> assertTrue(Battle.fight(james, bob)),
                () -> assertTrue(james.isAlive()),
                () -> assertEquals(1, james.getHealth()),
                () -> assertEquals(0, bob.getHealth())
        );
    }

    @Test
    @DisplayName("DefenderVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(james, dracula)),
                () -> assertTrue(james.isAlive()),
                () -> assertEquals(22, james.getHealth()),
                () -> assertEquals(-1, dracula.getHealth())
        );
    }


    @Test
    @DisplayName("DefenderVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertFalse(Battle.fight(james, lacer)),
                () -> assertFalse(james.isAlive()),
                () -> assertEquals(0, james.getHealth()),
                () -> assertEquals(5, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("DefenderVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(james, gogi)),
                () -> assertTrue(james.isAlive()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(0, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("DefenderVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(james, eva)),
                () -> assertTrue(james.isAlive()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(0, eva.getHealth())
        );
    }

    @DisplayName("DefenderVsArmy")
    @ParameterizedTest
    @MethodSource("testDefenderVsArmyFight")
    void battleKnight(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testDefenderVsArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Defender::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Warrior::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 2),
                        new Army()
                                .addUnit(Knight::new, 1)
                                .addUnit(Knight::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 1),
                        new Army()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Rookie::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 1),
                        new Army()
                                .addUnit(Lancer::new, 1)
                                .addUnit(Vampire::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 2),
                        new Army()
                                .addUnit(Lancer::new, 1)
                                .addUnit(Rookie::new, 1),
                        true)
        );
    }
}