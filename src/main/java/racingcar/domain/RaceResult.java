package racingcar.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceResult {
    Map<String, Integer> cars;

    public RaceResult(List<Car> cars) {
        this.cars = Collections.unmodifiableMap(
            new LinkedHashMap<String, Integer>() {{
                cars.forEach(car -> put(car.toString(), car.getPosition()));
            }}
        );
    }

    public Map<String, Integer> getResult() {
        return cars;
    }

    public Map<String, Integer> getWinners() {
        int positionOfWinners = Collections.max(cars.values());
        return Collections.unmodifiableMap(
                cars.entrySet().stream()
                    .filter(x -> x.getValue() == positionOfWinners)
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()))
        );
    }
}