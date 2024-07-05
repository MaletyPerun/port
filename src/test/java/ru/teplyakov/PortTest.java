package ru.teplyakov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.teplyakov.Port.convertIndexesToSequencesStream;
import static ru.teplyakov.Port.getUniqueOrderedGroups;


class PortTest {

//    @BeforeEach
//    void setUp() {
//    }

//    static Stream<Arguments> provideLists() {
//        return Stream.of(
//                Arguments.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)),
//                Arguments.of(Arrays.asList(10, 20, 30), Arrays.asList(40, 50, 60), Arrays.asList(70, 80, 90)),
//                Arguments.of(Arrays.asList(100, 200, 300), Arrays.asList(400, 500, 600), Arrays.asList(700, 800, 900))
//        );
//    }

//    @ParameterizedTest
//    @MethodSource("provideLists")
//    void testLists(List<Integer> list1, List<Integer> list2, List<Integer> list3) {
//        // Проверка для всех 3 списков
//        assertEquals(3, list1.size());
//        assertEquals(3, list2.size());
//        assertEquals(3, list3.size());
//        // Добавьте здесь другие проверки, которые вам нужны
//    }

    // Стандартный тест, который можно добавить для сравнения
//    @Test
//    void standardTest() {
//        List<Integer> list = Arrays.asList(1, 2, 3);
//        assertEquals(3, list.size());
//    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void convertIndexesToSequencesTest() {

//        String[] indexes = {"1,3-5","3-4"};
//        String[] indexes = {"1,3-5", "4,2-5", "2", "", " ", "3-4"};
//        String[] indexes = {"9,12", "1,3-5", "4,2-5", "2", "", " ", "3-4"};
//        String[] indexes = {"2,4", "2,4"};

//        String[] indexes = {"4,2-5"};
//        String[] indexes = {"1,3-5", "2", "3-4", "10-12"};
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        ans.get(0).add(1);
        ans.get(0).add(3);
        ans.get(0).add(4);
        ans.get(0).add(5);
        ans.get(1).add(2);
        ans.get(2).add(3);
        ans.get(2).add(4);

        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> sequences = convertIndexesToSequencesStream(indexes);
        assertEquals(ans, sequences);
    }

    @Test
    @DisplayName("Конвертация с пустыми элементами")
    void convertIndexesToSequencesNotBlankAndEmptyTest() {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        ans.get(0).add(1);
        ans.get(0).add(3);
        ans.get(0).add(4);
        ans.get(0).add(5);
        ans.get(1).add(3);
        ans.get(1).add(4);

        String[] indexes = {"1,3-5", "", " ", "3-4"};
        List<List<Integer>> sequences = convertIndexesToSequencesStream(indexes);
        assertEquals(ans, sequences);
    }

    @Test
    @DisplayName("Конвертация с пустыми элементами и сортировка")
    void convertIndexesToSequencesSortedTest() {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        ans.get(0).add(2);
        ans.get(0).add(3);
        ans.get(0).add(4);
        ans.get(0).add(6);
        ans.get(1).add(1);
        ans.get(1).add(2);
        ans.get(1).add(3);

        String[] indexes = {"6,2-4", "2-3,1", "", " "};
        List<List<Integer>> sequences = convertIndexesToSequencesStream(indexes);
        assertEquals(ans, sequences);
    }

    @Test
    @DisplayName("Преобразование всех возможных переборок")
    void getUniqueOrderedGroupsTest() {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        ans.get(0).add(2);
        ans.get(0).add(3);

        ans.get(1).add(2);
        ans.get(1).add(4);

        ans.get(2).add(3);
        ans.get(2).add(3);

        ans.get(3).add(3);
        ans.get(3).add(4);

        List<List<Integer>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(new ArrayList<>());

        data.get(0).add(2);
        data.get(0).add(3);

        data.get(1).add(3);
        data.get(1).add(4);

        List<List<Integer>> groups = getUniqueOrderedGroups(data);
        assertEquals(ans, groups);
    }
}