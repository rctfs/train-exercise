package com.cleverti.trainExercise;

import com.cleverti.trainExercise.pojos.TrainStationsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class TrainStationCompleterService {

    public TrainStationsResponse getResponse(String input, List<String> allStations) {
        List<String> nextStations = stationComplete.apply(input, allStations);
        return TrainStationsResponse.builder().possibleStations(nextStations).possibleChars(getNextChars.apply(input, nextStations)).build();
    }

   protected BiFunction<String, List<String>, List<String>> stationComplete = (input, allStations) ->
            allStations.stream()
                    .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
                    .collect(Collectors.toList());


    protected BiFunction<String, List<String>, Set<String>> getNextChars = (input, nextStations) ->
            nextStations.stream()
                    .filter(s -> !s.equalsIgnoreCase(input))
                    .map(s -> s.split(input)[1])
                    .map(s -> s.substring(0, 1))
                    .collect(Collectors.toSet());

}
