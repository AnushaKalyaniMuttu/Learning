List<String> words = List.of("apple", "banana", "cherry", "date", "fig", "grape");

Map<Integer, List<String>> groupedByLength = words.stream()
    .collect(Collectors.groupingBy(String::length));
System.out.println(groupedByLength);
// Output: {3=[fig], 4=[date], 5=[apple, grape], 6=[banana, cherry]}
