package com.geekbrains.HW4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.debug("DEBUG");//TRACE, DEBUG, INFO, WARN, ERROR
    }

    //п.2 ДЗ, тесты:
// 1) на входные данные (на существование-ЕСТЬ в самой функции, но проверим как Исключение, т.к. при  этом не возвращает площадь,
// 2) на правильность вычисление площади, в т.ч. на вырожденность (s=0)
    @ParameterizedTest
    @CsvSource({"-1,-1,-1",
            "-1,1,1",
//            "0,0,0", // для проверки существования "полностью" вырожденного треугольника, но СУЩЕСТВУЮЩЕГО (негативный тест)
            "1,1,3" // для несуществующего
    })
    @DisplayName("Тест несуществования треугольника по таким сторонам")
    void given3sidesWhenTriangleThenFalse(int a, int b, int c) {
        Assertions.assertAll(() -> Assertions.assertThrows(Exception.class, () -> Triangle.triangleArea(a, b, c)));
    }

    @ParameterizedTest
    @CsvSource({"0.0,0,0,0,0.1", // еще раз полностью вырожденный
            "0.43301, 1, 1, 1, 0.00001",
            "433012.70189, 1000, 1000, 1000, 0.00001",
            "0,-333, 666, 999, 0.1", // для несуществующего (негативный тест)
            "0, 333, 666, 999, 0.1",
            "0, 2000000000, 1000000000, 1000000000, 0.1"
    })
    @DisplayName("Тест вычисления площади")
    void checkCalcArea(double expectedArea, int a, int b, int c, double accuracy) throws Exception {
        Assertions.assertAll(() -> Assertions.assertEquals(expectedArea, Triangle.triangleArea(a, b, c), accuracy));
    }

//    @Test
//    @BeforeAll
//    static void beforeAll() {
//    }
//
//    @BeforeEach
//    void beforeEach() {
//    }
//
//    @AfterEach
//    void afterEach() {
//    }

}
