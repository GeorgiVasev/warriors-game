package battles;

import charchters.*;
import org.example.Armies.Army;
import org.example.battles.Battle;
import org.example.charchters.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class BattleTest {

    @DisplayName("Battle Two whole Armies fight")
    @ParameterizedTest
    @MethodSource("testTwoWholeArmiesFight")
    void battleArmies(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testTwoWholeArmiesFight() {
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


    @DisplayName("Battle 2 Straight armies fight")
    @ParameterizedTest
    @MethodSource("testStraightFight")
    void battleStraightArmies(Army blue, Army red, boolean expected) {
        assertEquals(expected, Battle.straightArmyFight(blue, red));
    }

    static List<Arguments> testStraightFight() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 2)
                                .addUnit(Knight::new, 1),
                        new Army()
                                .addUnit(Knight::new, 1)
                                .addUnit(Healer::new, 1)
                                .addUnit(Knight::new, 1),
                        true)
                /*
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
                        false),
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 10),
                        new Army()
                                .addUnit(Warrior::new, 6)
                                .addUnit(Lancer::new, 5),
                        false)

                 */
        );
    }
}