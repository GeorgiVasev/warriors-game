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

class KnightTest {

    private IWarrior bucky;
    private Knight jim;
    private Knight george;
    private Defender james;
    private Vampire dracula;
    private Lancer lacer;
    private Healer eva;
    private Rookie gogi;

    @BeforeEach
    public void setup() {
        bucky = new Warrior();
        jim = new Knight();
        george = new Knight();
        james = new Defender();
        dracula = new Vampire();
        lacer = new Lancer();
        eva = new Healer();
        gogi = new Rookie();
    }

    @Test
    @DisplayName("KnightVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(50, bucky.getHealth()),
                () -> assertEquals(5, bucky.getAttack()),
                () -> assertTrue(Battle.fight(jim, bucky)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(15, jim.getHealth()),
                () -> assertEquals(-6, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("KnightVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(50, george.getHealth()),
                () -> assertEquals(7, george.getAttack()),
                () -> assertTrue(Battle.fight(jim, george)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(1, jim.getHealth()),
                () -> assertEquals(-6, george.getHealth())
        );
    }

    @Test
    @DisplayName("KnightVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertTrue(Battle.fight(jim, james)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(17, jim.getHealth()),
                () -> assertEquals(0, james.getHealth())
        );
    }

    @Test
    @DisplayName("KnightVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(jim, dracula)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(22, jim.getHealth()),
                () -> assertEquals(-2, dracula.getHealth())
        );
    }


    @Test
    @DisplayName("KnightVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertTrue(Battle.fight(jim, lacer)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(8, jim.getHealth()),
                () -> assertEquals(-6, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("KnightVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(jim, gogi)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(46, jim.getHealth()),
                () -> assertEquals(-5, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("KnightVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(jim, eva)),
                () -> assertTrue(jim.isAlive()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(-3, eva.getHealth())
        );
    }

    @DisplayName("KnightVsArmy")
    @ParameterizedTest
    @MethodSource("testKnightVsArmyFight")
    void battleKnight(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testKnightVsArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Knight::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Warrior::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Knight::new, 2),
                        new Army()
                                .addUnit(Defender::new, 1)
                                .addUnit(Defender::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Knight::new, 1),
                        new Army()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Rookie::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Knight::new, 1),
                        new Army()
                                .addUnit(Defender::new, 1)
                                .addUnit(Vampire::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Knight::new, 1),
                        new Army()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Defender::new, 1),
                        true)
        );
    }
}
