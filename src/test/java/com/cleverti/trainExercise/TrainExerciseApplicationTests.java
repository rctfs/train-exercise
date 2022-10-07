package com.cleverti.trainExercise;

import com.cleverti.trainExercise.pojos.TrainStationsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainExerciseApplicationTests {

    @Autowired
    private TrainStationCompleterService trainStationCompleterService;

    private final List<String> allStations = List.of("Santarem", "Santa Margarida", "Santa iria Azoia", "Santo Antonio", "Santo", "Santo Tirso", "Rossio", "Samora Correia");

    @Test
    void givenInput_shouldReturn_oneStation() {

        List<String> strings = trainStationCompleterService.stationComplete.apply("Sant", List.of("Santarem", "Rossio"));

        assertEquals(1, strings.size());
        assertTrue(strings.contains("Santarem"));

    }

    @Test
    void givenInput_shouldReturn_oneChar() {
        Set<String> nextChars = trainStationCompleterService.getNextChars.apply("Sant", List.of("Santarem"));

        assertTrue(nextChars.contains("a"));
    }

    @Test
    void givenInput_shouldReturn_completeResponse() {
        TrainStationsResponse nextStationsResponse = trainStationCompleterService.getResponse("Sant", allStations);

        assertEquals(6, nextStationsResponse.getPossibleStations().size());
        assertTrue(nextStationsResponse.getPossibleStations().containsAll(List.of("Santarem", "Santa Margarida", "Santa iria Azoia", "Santo Antonio")));

        assertEquals(2, nextStationsResponse.getPossibleChars().size());
        assertTrue(nextStationsResponse.getPossibleChars().containsAll(List.of("a", "o")));

    }

    @Test
    void givenFullStation_shouldReturn_emptyChars_oneStation() {
        TrainStationsResponse nextStationsResponse = trainStationCompleterService.getResponse("Santa Margarida", allStations);

        assertEquals(1, nextStationsResponse.getPossibleStations().size());
        assertTrue(nextStationsResponse.getPossibleStations().contains("Santa Margarida"));

        assertEquals(0, nextStationsResponse.getPossibleChars().size());
    }

    @Test
    void givenFullStation_shouldReturn_listChars_andStations() {
        TrainStationsResponse nextStationsResponse = trainStationCompleterService.getResponse("Santo", allStations);

        assertEquals(3, nextStationsResponse.getPossibleStations().size());
        assertTrue(nextStationsResponse.getPossibleStations().containsAll(List.of("Santo", "Santo Tirso", "Santo Antonio")));

        assertEquals(1, nextStationsResponse.getPossibleChars().size());
        assertTrue(nextStationsResponse.getPossibleChars().contains(" "));

    }

}