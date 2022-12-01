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


class LancerTest {

    private IWarrior bucky;
    private Knight jim;
    private Defender james;
    private Vampire dracula;
    private Lancer freeLancer;
    private Lancer lacer;
    private Healer eva;
    private Rookie gogi;

    @BeforeEach
    public void setup() {
        bucky = new Warrior();
        jim = new Knight();
        james = new Defender();
        dracula = new Vampire();
        freeLancer = new Lancer();
        lacer = new Lancer();
        eva = new Healer();
        gogi = new Rookie();
    }

    @Test
    @DisplayName("LancerVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertTrue(Battle.fight(freeLancer, bucky)),
                () -> assertTrue(freeLancer.isAlive()),
                () -> assertEquals(10, freeLancer.getHealth()),
                () -> assertEquals(-4, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("LancerVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertFalse(Battle.fight(freeLancer, jim)),
                () -> assertFalse(freeLancer.isAlive()),
                () -> assertEquals(-6, freeLancer.getHealth()),
                () -> assertEquals(2, jim.getHealth())
        );
    }

    @Test
    @DisplayName("LancerVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertTrue(Battle.fight(freeLancer, james)),
                () -> assertTrue(freeLancer.isAlive()),
                () -> assertEquals(8, freeLancer.getHealth()),
                () -> assertEquals(0, james.getHealth())
        );
    }

    @Test
    @DisplayName("LancerVsVampire")
    void forthFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(freeLancer, dracula)),
                () -> assertTrue(freeLancer.isAlive()),
                () -> assertEquals(14, freeLancer.getHealth()),
                () -> assertEquals(-2, dracula.getHealth())
        );
    }

    @Test
    @DisplayName("LancerVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertTrue(Battle.fight(freeLancer, lacer)),
                () -> assertTrue(freeLancer.isAlive()),
                () -> assertEquals(2, freeLancer.getHealth()),
                () -> assertEquals(-4, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("LancerVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(freeLancer, gogi)),
                () -> assertTrue(freeLancer.isAlive()),
                () -> assertEquals(46, freeLancer.getHealth()),
                () -> assertEquals(0, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("LancerVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(6, freeLancer.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(freeLancer, eva)),
                () -> assertTrue(freeLancer.isAlive()),
                () -> assertEquals(50, freeLancer.getHealth()),
                () -> assertEquals(0, eva.getHealth())
        );
    }

    @DisplayName("LancerVsArmy")
    @ParameterizedTest
    @MethodSource("testLancerVsArmyFight")
    void battleLancer(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testLancerVsArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Knight::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Defender::new, 1)
                                .addUnit(Defender::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Defender::new, 1)
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