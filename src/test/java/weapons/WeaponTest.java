package weapons;

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

class WeaponTest {

    Weapon sword;
    Weapon shield;
    Weapon greatAxe;
    Weapon katana;
    Weapon magicWand;
    Weapon superWaffle;
    Weapon custom;

    Warrior bucky;
    Knight carl;
    King alexander;
    Defender james;
    Lancer lacer;
    Healer eva;
    Vampire dracula;

    Army blue;
    Army red;

    @BeforeEach
    void setUp() {
        sword = Weapon.builder()
                .health(5)
                .attack(2)
                .build();
        shield = Weapon.builder()
                .health(20)
                .attack(-1)
                .defense(2)
                .build();
        greatAxe = Weapon.builder()
                .health(-15)
                .attack(5)
                .defense(-5)
                .vampirism(10)
                .build();
        katana = Weapon.builder()
                .health(-20)
                .attack(6)
                .defense(-5)
                .vampirism(50)
                .build();
        magicWand = Weapon.builder()
                .health(30)
                .attack(3)
                .healPower(3)
                .build();
        superWaffle = Weapon.builder()
                .health(100)
                .attack(-100)
                .build();
        custom = Weapon.builder()
                .health(-10)
                .attack(5)
                .vampirism(40)
                .rangeDamage(50)
                .build();

        bucky = new Warrior();
        carl = new Knight();
        james = new Defender();
        dracula = new Vampire();
        lacer = new Lancer();
        eva = new Healer();
        blue = new Army();
        red = new Army();
        alexander = new King();
    }

    @Test
    @DisplayName("Equip Sword")
    void equip() {
        bucky.equipWeapons(sword, katana, magicWand);
        carl.equipWeapons(sword, sword, sword);

        assertEquals(65, bucky.getHealth());
        assertEquals(16, bucky.getAttack());
        assertEquals(65, carl.getHealth());
        assertEquals(13, carl.getAttack());

    }

    @Test
    @DisplayName("Equip Two Swords")
    void equipTwoSwords() {
        bucky.equipWeapons(sword, sword);
        carl.equipWeapons(sword, sword);

        alexander.equipWeapons(sword, katana, magicWand, greatAxe, shield, custom);
        assertEquals(110, alexander.getHealth());
        assertEquals(25, alexander.getAttack());
        assertEquals(50, alexander.getRangeDamage());
        assertEquals(100, alexander.getVampirism());
        assertEquals(-8, alexander.getDefense());


        assertEquals(60, bucky.getHealth());
        assertEquals(9, bucky.getAttack());
        assertEquals(60, carl.getHealth());
        assertEquals(11, carl.getAttack());

        assertFalse(Battle.fight(bucky, carl));
    }

    @Test
    @DisplayName("Fight with SuperWaffle")
    void fightOne() {
        bucky.equipWeapons(superWaffle);
        carl.equipWeapons(sword);


        assertEquals(150, bucky.getHealth());
        assertEquals(-95, bucky.getAttack());
        assertEquals(55, carl.getHealth());
        assertEquals(9, carl.getAttack());

        assertFalse(Battle.fight(bucky, carl));
    }

    @Test
    @DisplayName("WarriorVsVampire")
    void fightTwo() {
        bucky.equipWeapons(custom);
        dracula.equipWeapons(sword);

        assertEquals(40, bucky.getHealth());
        assertEquals(10, bucky.getAttack());
        assertEquals(45, dracula.getHealth());
        assertEquals(6, dracula.getAttack());
        assertEquals(50, dracula.getVampirism());

        assertTrue(Battle.fight(bucky, dracula));
    }

    @Test
    @DisplayName("DefenderVsLancer")
    void fightTree() {
        james.equipWeapons(shield);
        lacer.equipWeapons(greatAxe);

        assertEquals(80, james.getHealth());
        assertEquals(2, james.getAttack());
        assertEquals(4, james.getDefense());
        assertEquals(35, lacer.getHealth());
        assertEquals(11, lacer.getAttack());

        assertFalse(Battle.fight(james, lacer));
    }

    @Test
    @DisplayName("HealerVsKnight")
    void fightFour() {
        eva.equipWeapons(magicWand);
        carl.equipWeapons(katana);

        assertEquals(90, eva.getHealth());
        assertEquals(0, eva.getAttack());
        assertEquals(5, eva.getHealPower());
        assertEquals(30, carl.getHealth());
        assertEquals(13, carl.getAttack());

        assertFalse(Battle.fight(eva, carl));
    }

    @Test
    @DisplayName("King With Weapons")
    void equipKing() {
        alexander.equipWeapons(sword, katana, magicWand, greatAxe, shield, custom);
        assertEquals(110, alexander.getHealth());
        assertEquals(25, alexander.getAttack());
        assertEquals(50, alexander.getRangeDamage());
        assertEquals(100, alexander.getVampirism());
        assertEquals(-8, alexander.getDefense());
    }

    @DisplayName("Battle Two whole Armies fight with Weapons")
    @ParameterizedTest
    @MethodSource("testTwoWholeArmiesFightWithWeapons")
    void battleArmies(Army blue, Army red, boolean expected) {
        blue.equipWarriorAtPosition(0, magicWand);
        blue.equipWarriorAtPosition(1, greatAxe);
        red.equipWarriorAtPosition(0, magicWand);
        red.equipWarriorAtPosition(1, greatAxe);
        assertEquals(expected, Battle.fight(blue, red));
    }

    static List<Arguments> testTwoWholeArmiesFightWithWeapons() {
        return List.of(
                arguments(
                        new Army()
                                .addUnit(Warrior::new, 1)
                                .addUnit(Lancer::new, 1),
                        new Army()
                                .addUnit(Vampire::new, 1)
                                .addUnit(Healer::new, 1),
                        true)
        );
    }
}

