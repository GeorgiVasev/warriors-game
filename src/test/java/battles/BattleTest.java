package battles;

import Armies.Army;
import charchters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class BattleTest {

    private Warrior carl;
    private Warrior bucky;
    private Knight jim;
    private Defender james;
    private Defender bob;
    private Rookie gogi;
    private Vampire dracula;
    private Lancer freeLancer;

    @BeforeEach
    public void setup() {
        // Char Setup
        carl = new Warrior();
        bucky = new Warrior();
        jim = new Knight();
        james = new Defender();
        bob = new Defender();
        gogi = new Rookie();
        dracula = new Vampire();
        freeLancer = new Lancer();
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

    @Test
    @DisplayName("Fight 8")
    void eightFight() {
        Battle.fight(james, gogi);
        assertEquals(60, james.getHealth());
        assertTrue(Battle.fight(james, bob));
    }

    @Test
    @DisplayName("Fight 9")
    void ninthFight() {
        Battle.fight(james, gogi);
        assertTrue(Battle.fight(james, bucky));
    }

    @Test
    @DisplayName("Fight 10")
    void tenthFight() {
        assertTrue(Battle.fight(james, dracula));
        assertEquals(22, james.getHealth());
        assertEquals(-1, dracula.getHealth());
    }

    @Test
    @DisplayName("Fight 11")
    void eleventhFight() {
        assertTrue(Battle.fight(freeLancer, james));
        assertTrue(freeLancer.isAlive());
    }

    //Battle test where two armies fight

    @DisplayName("Battle 1")
    @ParameterizedTest
    @MethodSource("testTwoArmiesFight")
    void battleArmies(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testTwoArmiesFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 2),
                        false),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 2),
                        new Army()
                                .addUnit(Warrior::new, 3),
                        false),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 5),
                        new Army()
                                .addUnit(Warrior::new, 7),
                        false),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 20),
                        new Army()
                                .addUnit(Warrior::new, 21),
                        true),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 10),
                        new Army()
                                .addUnit(Warrior::new, 11),
                        true),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 11),
                        new Army()
                                .addUnit(Warrior::new, 7),
                        true),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 5)
                                .addUnit(Defender::new, 4)
                                .addUnit(Defender::new, 5),
                        new Army()
                                .addUnit(Warrior::new, 4),
                        true),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 5)
                                .addUnit(Warrior::new, 20)
                                .addUnit(Defender::new, 4),
                        new Army()
                                .addUnit(Warrior::new, 21),
                        true),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 10)
                                .addUnit(Defender::new, 5)
                                .addUnit(Warrior::new, 5),
                        new Army()
                                .addUnit(Defender::new, 10),
                        true),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 2)
                                .addUnit(Warrior::new, 1)
                                .addUnit(Defender::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 5),
                        false),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 2)
                                .addUnit(Vampire::new, 3)
                                .addUnit(Warrior::new, 4),
                        new Army()
                                .addUnit(Warrior::new, 4)
                                .addUnit(Defender::new, 4)
                                .addUnit(Vampire::new, 3),
                        false),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 11)
                                .addUnit(Vampire::new, 3)
                                .addUnit(Warrior::new, 4),
                        new Army()
                                .addUnit(Warrior::new, 4)
                                .addUnit(Defender::new, 4)
                                .addUnit(Vampire::new, 13),
                        true),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 9)
                                .addUnit(Vampire::new, 3)
                                .addUnit(Warrior::new, 8),
                        new Army()
                                .addUnit(Warrior::new, 4)
                                .addUnit(Defender::new, 4)
                                .addUnit(Vampire::new, 13),
                        true),
                arguments(
                        new Army()
                                .addUnit(Defender::new, 2)
                                .addUnit(Vampire::new, 2)
                                .addUnit(Lancer::new, 4)
                                .addUnit(Warrior::new, 1),
                        new Army()
                                .addUnit(Warrior::new, 2)
                                .addUnit(Lancer::new, 2)
                                .addUnit(Defender::new, 2)
                                .addUnit(Vampire::new, 3),
                        true),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Lancer::new, 1)
                                .addUnit(Defender::new, 2),
                        new Army()
                                .addUnit(Vampire::new, 3)
                                .addUnit(Warrior::new, 1)
                                .addUnit(Lancer::new, 2),
                        false),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Warrior::new, 1),
                        true),
                arguments(
                        new Army()
                                .addUnit(Lancer::new, 2),
                        new Army()
                                .addUnit(Rookie::new, 1)
                                .addUnit(Warrior::new, 2),
                        true)
        );
    }
}