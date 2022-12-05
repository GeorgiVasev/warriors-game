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

class KingTest {

    private King alexander;
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
        alexander = new King();
    }

    @Test
    @DisplayName("KingVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(50, bucky.getHealth()),
                () -> assertEquals(5, bucky.getAttack()),
                () -> assertTrue(Battle.fight(alexander, bucky)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(55, alexander.getHealth()),
                () -> assertEquals(0, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("KingVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(50, george.getHealth()),
                () -> assertEquals(7, george.getAttack()),
                () -> assertTrue(Battle.fight(alexander, george)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(37, alexander.getHealth()),
                () -> assertEquals(0, george.getHealth())
        );
    }

    @Test
    @DisplayName("KingVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertTrue(Battle.fight(alexander, james)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(43, alexander.getHealth()),
                () -> assertEquals(0, james.getHealth())
        );
    }

    @Test
    @DisplayName("KingVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(alexander, dracula)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(52, alexander.getHealth()),
                () -> assertEquals(-1, dracula.getHealth())
        );
    }


    @Test
    @DisplayName("KingVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertTrue(Battle.fight(alexander, lacer)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(46, alexander.getHealth()),
                () -> assertEquals(0, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("KingVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(alexander, gogi)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(95, alexander.getHealth()),
                () -> assertEquals(0, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("KingVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(5, alexander.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(alexander, eva)),
                () -> assertTrue(alexander.isAlive()),
                () -> assertEquals(100, alexander.getHealth()),
                () -> assertEquals(0, eva.getHealth())
        );
    }



    @DisplayName("KingVsArmy")
    @ParameterizedTest
    @MethodSource("testKingVsArmyFight")
    void battleKnight(Army blue, Army red, boolean expected) {


        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testKingVsArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(King::new, 2),
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(King::new, 2),
                        false)
        );
    }
}