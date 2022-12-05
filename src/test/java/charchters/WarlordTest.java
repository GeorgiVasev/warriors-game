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

class WarlordTest {

    private Warrior carl;
    private Warrior bucky;
    private Knight jim;
    private Defender james;
    private Vampire dracula;
    private Lancer lacer;
    private Healer eva;
    private Rookie gogi;
    private Warlord goro;

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
        goro = new Warlord();
    }

    @Test
    @DisplayName("WarlordVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(50, carl.getHealth()),
                () -> assertEquals(5, carl.getAttack()),
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertFalse(Battle.fight(carl, goro)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(-2, carl.getHealth()),
                () -> assertEquals(61, goro.getHealth())
        );
    }

    @Test
    @DisplayName("WarlordVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertTrue(Battle.fight(goro, jim)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(40, goro.getHealth()),
                () -> assertEquals(-2, jim.getHealth())
        );
    }

    @Test
    @DisplayName("WarlordVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertTrue(Battle.fight(goro, james)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(71, goro.getHealth()),
                () -> assertEquals(0, james.getHealth())
        );
    }

    @Test
    @DisplayName("WarlordVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertTrue(Battle.fight(goro, dracula)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(76, goro.getHealth()),
                () -> assertEquals(0, dracula.getHealth())
        );
    }


    @Test
    @DisplayName("WarlordVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertTrue(Battle.fight(goro, lacer)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(52, goro.getHealth()),
                () -> assertEquals(-2, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("WarlordVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertTrue(Battle.fight(goro, gogi)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(-2, gogi.getHealth())
        );
    }


    @Test
    @DisplayName("WarlordVsHealer")
    void seventhFight() {
        assertAll(
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(4, goro.getAttack()),
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertTrue(Battle.fight(goro, eva)),
                () -> assertTrue(goro.isAlive()),
                () -> assertEquals(100, goro.getHealth()),
                () -> assertEquals(0, eva.getHealth())
        );
    }

    @DisplayName("WarlordInArmy")
    @ParameterizedTest
    @MethodSource("testWarlordInArmyFight")
    void battleWarlord(Army blue, Army red, boolean expected) {


        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testWarlordInArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Warlord::new, 1)
                                .addUnit(Warrior::new, 2)
                                .addUnit(Lancer::new, 2)
                                .addUnit(Healer::new, 2),
                        new Army()
                                .addUnit(Warlord::new, 3)
                                .addUnit(Vampire::new, 1)
                                .addUnit(Healer::new, 2)
                                .addUnit(Knight::new, 2),
                        true)
        );
    }
}
