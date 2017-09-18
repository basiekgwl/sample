package baga;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SimpleJava8 {


    private long counter;
    private void wasCalled() {
        counter++;
        log.info("map() was called ... " + counter);
    }

    public void streamToMap(Map<Integer, String> mapData) {

        Iterator<Map.Entry<Integer, String>> iterator = mapData.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            log.info("Iterator::Java7: Entry --- Key " + entry.getKey() + " and Value: " + entry.getValue());
        }

        Set<Map.Entry<Integer, String>> mapEntry = mapData.entrySet();

        for (Map.Entry<Integer, String> element : mapEntry) {
            log.info("FOR: Key" + element.getKey() + " Value: " + element.getValue());
        }

        mapData.forEach((k, v) -> log.info("Java8: Key: " + k + " Value: " + v));


        Map<Integer, String> lambda = mapData.entrySet().stream()
                .filter(item -> item.getKey() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        log.info("Lambda: " + lambda);

    }

    public void streamForList(String[] myTestList, String expectedName) {

        List<String> arrayList = Stream.of(myTestList).filter(item -> item.contains(expectedName)).collect(Collectors.toList());

        Optional<String> anyElement = arrayList.stream().findAny();
        Optional<String> firstElement = arrayList.stream().findFirst();

        log.info("Result: " + arrayList);
        log.info("Any: " + anyElement);
        log.info("First: " + firstElement);

        List<String> list = Arrays.asList(myTestList);

        long size = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).count();

        log.info("Size (skip1): " + size);

        arrayList = Stream.of(myTestList).map(element -> element.substring(0, 3)).sorted().collect(Collectors.toList());

        log.info("substring: " + arrayList);
    }

    public void streamsVariousCasesForList(String[] testDataTable) {

        List<String> myNewList = Stream.of(testDataTable).map(item -> {
            String[] nameAndSName = item.split(" ");
            log.info("Name: " + nameAndSName[0]);
            return nameAndSName[0];
        }).sorted().collect(Collectors.toList());

        log.info("NEW sorted List : " + myNewList);

        counter = 0;
        List<String> newData = myNewList.stream().skip(2).map(item -> {
            wasCalled();
            return item.toUpperCase();
        }).collect(Collectors.toList());

        log.info("NEW List: " + newData + " counter: " + counter);

        Optional<String> someData = newData.stream().filter(item -> item.contains("M")).map(item -> {
            log.info("map() was called again");
            return item.toLowerCase();
        }).findFirst();

        log.info("some data: " + someData);

        counter = 0;
        List<String> elementContainsE = Arrays.stream(testDataTable).filter( item -> {
            wasCalled();
            return item.contains("E");
        }).collect(Collectors.toList());


        log.info("Elements contain 'E': " + elementContainsE);
    }


}
