package battles;

import charchters.Knight;
import charchters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    private Warrior carl;
    private Warrior bucky;
    private Knight jim;

    @BeforeEach
    public void setup() {
        carl = new Warrior();
        bucky = new Warrior();
        jim = new Knight();
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
}