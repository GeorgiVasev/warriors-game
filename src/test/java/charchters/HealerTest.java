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

class HealerTest {

    private Warrior bucky;
    private Knight jim;
    private Defender james;
    private Vampire dracula;
    private Lancer lacer;
    private Healer eva;
    private Healer meri;
    private Rookie gogi;

    @BeforeEach
    public void setup() {
        bucky = new Warrior();
        jim = new Knight();
        james = new Defender();
        dracula = new Vampire();
        lacer = new Lancer();
        eva = new Healer();
        meri = new Healer();
        gogi = new Rookie();
    }

    @Test
    @DisplayName("HealerVsWarrior")
    void firstFight() {
        assertAll(
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertEquals(50, bucky.getHealth()),
                () -> assertEquals(5, bucky.getAttack()),
                () -> assertFalse(Battle.fight(eva, bucky)),
                () -> assertFalse(eva.isAlive()),
                () -> assertEquals(0, eva.getHealth()),
                () -> assertEquals(50, bucky.getHealth())
        );
    }

    @Test
    @DisplayName("HealerVsKnight")
    void secondFight() {
        assertAll(
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertEquals(50, jim.getHealth()),
                () -> assertEquals(7, jim.getAttack()),
                () -> assertFalse(Battle.fight(eva, jim)),
                () -> assertFalse(eva.isAlive()),
                () -> assertEquals(-3, eva.getHealth()),
                () -> assertEquals(50, jim.getHealth())
        );
    }

    @Test
    @DisplayName("HealerVsDefender")
    void thirdFight() {
        assertAll(
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertEquals(60, james.getHealth()),
                () -> assertEquals(3, james.getAttack()),
                () -> assertFalse(Battle.fight(eva, james)),
                () -> assertFalse(eva.isAlive()),
                () -> assertEquals(0, eva.getHealth()),
                () -> assertEquals(60, james.getHealth())
        );
    }

    @Test
    @DisplayName("HealerVsVampire")
    void fourthFight() {
        assertAll(
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertEquals(40, dracula.getHealth()),
                () -> assertEquals(4, dracula.getAttack()),
                () -> assertFalse(Battle.fight(eva, dracula)),
                () -> assertFalse(eva.isAlive()),
                () -> assertEquals(0, eva.getHealth()),
                () -> assertEquals(40, dracula.getHealth())
        );
    }

    @Test
    @DisplayName("HealerVsLancer")
    void fifthFight() {
        assertAll(
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertEquals(50, lacer.getHealth()),
                () -> assertEquals(6, lacer.getAttack()),
                () -> assertFalse(Battle.fight(eva, lacer)),
                () -> assertFalse(eva.isAlive()),
                () -> assertEquals(0, eva.getHealth()),
                () -> assertEquals(50, lacer.getHealth())
        );
    }

    @Test
    @DisplayName("HealerVsRookie")
    void sixthFight() {
        assertAll(
                () -> assertEquals(60, eva.getHealth()),
                () -> assertEquals(0, eva.getAttack()),
                () -> assertEquals(30, gogi.getHealth()),
                () -> assertEquals(1, gogi.getAttack()),
                () -> assertFalse(Battle.fight(eva, gogi)),
                () -> assertFalse(eva.isAlive()),
                () -> assertEquals(0, eva.getHealth()),
                () -> assertEquals(30, gogi.getHealth())
        );
    }

    @DisplayName("HealerVsArmy")
    @ParameterizedTest
    @MethodSource("testHealerVsArmyFight")
    void battleHealer(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testHealerVsArmyFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Healer::new, 1)
                                .addUnit(Healer::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 2)
                                .addUnit(Healer::new, 1)
                                .addUnit(Vampire::new, 2)
                                .addUnit(Lancer::new, 2)
                                .addUnit(Healer::new, 1)
                                .addUnit(Warrior::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 2)
                                .addUnit(Lancer::new, 4)
                                .addUnit(Healer::new, 1)
                                .addUnit(Defender::new, 2)
                                .addUnit(Vampire::new, 3)
                                .addUnit(Healer::new, 1),
                        false),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Lancer::new, 1)
                                .addUnit(Healer::new, 1)
                                .addUnit(Defender::new, 2),
                        new Army()
                                .addUnit(Vampire::new, 3)
                                .addUnit(Warrior::new, 1)
                                .addUnit(Healer::new, 1)
                                .addUnit(Lancer::new, 2),
                        true),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 7)
                                .addUnit(Vampire::new, 3)
                                .addUnit(Healer::new, 1)
                                .addUnit(Warrior::new, 4)
                                .addUnit(Healer::new, 1)
                                .addUnit(Defender::new, 2),
                        new Army()
                                .addUnit(Warrior::new, 4)
                                .addUnit(Defender::new, 4)
                                .addUnit(Healer::new, 1)
                                .addUnit(Vampire::new, 6)
                                .addUnit(Lancer::new, 4),
                        true),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1)
                                .addUnit(Warrior::new, 3)
                                .addUnit(Healer::new, 1)
                                .addUnit(Warrior::new, 4)
                                .addUnit(Healer::new, 1)
                                .addUnit(Knight::new, 2),
                        new Army()
                                .addUnit(Warrior::new, 4)
                                .addUnit(Defender::new, 4)
                                .addUnit(Healer::new, 1)
                                .addUnit(Vampire::new, 6)
                                .addUnit(Lancer::new, 4),
                        false)
        );
    }

}
