import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    private Car car;
    private static int totalTrips;

    @BeforeAll
    static void initAll() {
        totalTrips = 0;
        System.out.println("Запуск всех тестов для Car. Инициализация счетчика totalTrips = 0");
    }

    @BeforeEach
    void setUp() {
        car = new Car("Toyota", 50.0);
        System.out.println("Создан новый автомобиль Toyota с 50л");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Тест завершен. Текущее количество успешных поездок: " + totalTrips);
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Все тесты Car завершены. Общее количество успешных поездок: " + totalTrips);
    }

    @Test
    @DisplayName("Проверка начального уровня топлива у Toyota")
    void testInitialFuelLevel() {
        assertEquals(50.0, car.getFuelLevel(), 0.001, "Начальный уровень топлива должен быть 50 литров");
        assertEquals(0.0, car.getMileage(), 0.001, "Начальный пробег должен быть 0");
        System.out.println("Выполняется testInitialFuelLevel - начальное топливо: " + car.getFuelLevel());
    }

    @Test
    @DisplayName("Поездка 50 км - успешна, топливо уменьшается, пробег увеличивается")
    void testDrive50km() {
        boolean result = car.drive(50.0);

        assertTrue(result, "Поездка на 50 км должна быть возможна");
        assertEquals(45.0, car.getFuelLevel(), 0.001, "Топливо должно уменьшиться на 5 литров");
        assertEquals(50.0, car.getMileage(), 0.001, "Пробег должен увеличиться на 50 км");

        totalTrips++;

        System.out.println("Выполняется testDrive50km - расход топлива: 5л, пробег: " + car.getMileage());
    }

    @Test
    @DisplayName("Поездка 600 км - невозможна из-за нехватки топлива")
    void testDrive600km() {
        double fuelBefore = car.getFuelLevel();
        double mileageBefore = car.getMileage();

        boolean result = car.drive(600.0);

        assertFalse(result, "Поездка на 600 км невозможна - не хватает топлива");
        assertEquals(fuelBefore, car.getFuelLevel(), 0.001, "Уровень топлива не должен измениться");
        assertEquals(mileageBefore, car.getMileage(), 0.001, "Пробег не должен измениться");

        System.out.println("Выполняется testDrive600km - топлива недостаточно для поездки");
    }
}