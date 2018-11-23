package com.bc.trial1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamChecker {

	public static void main(String... args) {

		
		Stream s = Stream.of(1,11,11111,111,1,1111,1111111,11111111);
		
		s.forEach(System.out::println);
		
		List<Integer> numbers = Arrays.asList(2, 8, 3, 4, 7, 1, 5, 6);

		List<Integer> evens = numbers.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());

		List<Integer> evens2 = numbers.stream().map(e -> e + 6).collect(Collectors.toList());

		List<Object> sorted = numbers.stream().sorted((i1, i2) -> ((i1 > i2) ? 1 : (i1 < i2) ? -1 : 0))
				.collect(Collectors.toList());
		
		System.out.println(numbers.stream().min((n1,n2)->n1.compareTo(n2)).get());
		System.out.println(evens);
		System.out.println(evens2);
		System.out.println(sorted);

		List<String> countries = Arrays.asList("India", "Australia", "Sri Lanka", "Nepal", "USA");

		Comparator<String> c = (S1, S2) -> {
			int l1 = S1.length();
			int l2 = S2.length();

			if (l1 > l2)
				return 1;
			else if (l2 > l1)
				return -1;
			else
				return S1.compareTo(S2);
		};

		List<String> sortedCountries = countries.stream().sorted(c).collect(Collectors.toList());
		
		System.out.println(countries);
		System.out.println(sortedCountries);
		
	}

}
