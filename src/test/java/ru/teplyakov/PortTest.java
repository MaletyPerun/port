package ru.teplyakov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.teplyakov.Port.convertIndexesToSequencesStream;
import static ru.teplyakov.Port.getUniqueOrderedGroups;


class PortTest {

    @Test
    void convertIndexesToSequencesTest() {

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