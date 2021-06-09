package com.florian935.basicsfluxmono.transform;

import com.florian935.basicsfluxmono.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

public class CollectSortedList {

    public void collectSortedList() {
        Mono<List<String>> stream = Flux.just("D", "A", "C", "B")
                .collectSortedList();

        stream.subscribe(System.out::println);
    }

    public void collectSortedListWithComparator() {
        Mono<List<Employee>> stream = Flux.fromStream(getEmployees().stream())
                .collectSortedList(Comparator.comparing(Employee::getAge));

        stream.subscribe(System.out::println);
    }

    private List<Employee> getEmployees() {
        return List.of(
                new Employee("Toto", 32),
                new Employee("Tata", 18),
                new Employee("Titi", 53),
                new Employee("Tete", 12)
        );
    }
}
