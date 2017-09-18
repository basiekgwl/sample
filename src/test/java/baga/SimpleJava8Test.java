package baga;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@Slf4j
public class SimpleJava8Test {

    private List<String> myList = Arrays.asList("baga", "anbo", "masy", "dazb", "raja");
    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 8, 15);

    private List<String> newFormat = new ArrayList<>();

    @Test
    public void toUpperCaseTestInJava7() {

        for (String nik : myList) {
            log.info("NIK: " + nik);
            newFormat.add(nik.toUpperCase());
        }
        log.info("All data: " + newFormat.toString());

        assertEquals(newFormat.get(0), "BAGA");
        assertEquals(newFormat.get(1), "ANBO");
        assertEquals(newFormat.get(2), "MASY");
        assertEquals(newFormat.get(3), "DAZB");
        assertEquals(newFormat.get(4), "RAJA");
    }

    @Test
    public void toLowerCaseTestInJava8() {

        List<String> collect = myList.stream().map(String::toUpperCase).collect(Collectors.toList());
        log.info("Collect1 - Stream: " + collect);

        List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
        log.info("Collect2 - Stream: " + collect1);
    }

    Employee baga = new Employee("Ginny Weasley", new BigDecimal("7400.69"), 2003, 1, 3);
    Employee dazb = new Employee("Severus Snape", new BigDecimal("9454.75"), 1996, 3, 20);
    Employee anbo = new Employee("Andrzej Borys", new BigDecimal("8341.99"), 1998, 6, 2);

    @Test
    public void myEmployeeTestWithStream() {

        List<Employee> myEmployeeList = Arrays.asList(baga, dazb, anbo);

        List<String> listOfNames = myEmployeeList.stream().map(Employee::getName).collect(Collectors.toList());
        List<Integer> listOfIds = myEmployeeList.stream().map(Employee::getId).collect(Collectors.toList());

        log.info("All Employees (by Name): " + listOfNames.toString());
        log.info("All Employees (by Id): " + listOfIds.toString());

    }

    @Test
    public void someModifications() {

        List<Employee> myEmployeeList = Arrays.asList(baga, dazb, anbo);

        List<String> myData = Arrays.asList("Baga", "Dazb", "Anbo");
//        myData.stream().findFirst().ifPresent(element -> log.info(element));
        myData.stream().findFirst().ifPresent(log::info);
        myData.forEach(item -> log.info("Item: " + item));

        List<Employee> newResults = myEmployeeList.stream().map(employee -> {
            Employee object = new Employee();

            if (employee.getName().equals("Ginny Weasley")) {
                object.setName("Ginny W.");
                object.setSalary(new BigDecimal("9000.00"));
            } else {
                object.setName(employee.getName());
                object.setSalary(employee.getSalary());
            }

            object.setId(employee.getId());

            return object;
        }).collect(Collectors.toList());

        newResults.forEach(employee -> {
            log.info("New Result: " + employee.toString());
        });
    }

    @Test
    public void mapTestJava8() {

        Map<Integer, String> HOSTING = new HashMap<>();
        HOSTING.put(1, "linode.com");
        HOSTING.put(2, "heroku.com");
        HOSTING.put(3, "digitalocean.com");
        HOSTING.put(4, "aws.amazon.com");

//        //Map -> Stream -> Filter -> String
//        String result = HOSTING.entrySet().stream().filter(item -> "aws.amazon.com".equals(item.getValue())).map(Map.Entry::getValue).collect(Collectors.joining());
//
//        // Map<Integer, String> collect = HOSTING.entrySet().stream().filter(item -> item.getKey() == 2).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
//        Map<Integer, String> collect = HOSTING.entrySet().stream().filter(item -> item.getKey() == 2).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        log.info("Result - String: " + result);
//        log.info("Result - Collect: " + collect.toString());
//
//
        SimpleJava8 streamTest = new SimpleJava8();

        streamTest.streamToMap(HOSTING);

    }

    @Test
    public void mapTestJava8List() {

        String[] employees = new String[] {"Maciej Kowalski", "Daniel Kowalski", "Monika Kowalska", "Ewelina Kowalska", "Agnieszka Enigma"};


        SimpleJava8 listTest = new SimpleJava8();

        listTest.streamForList(employees, "Kowalska");

        listTest.streamsVariousCasesForList(employees);

    }
}