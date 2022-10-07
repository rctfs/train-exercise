package com.cleverti.trainExercise.pojos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class TrainStationsResponse {

    private List<String> possibleStations;
    private Set<String> possibleChars;
}
