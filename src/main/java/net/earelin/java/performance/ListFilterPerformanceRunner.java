package net.earelin.java.performance;

import org.apache.commons.collections4.CollectionUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListFilterPerformanceRunner {

    @State(Scope.Benchmark)
    public static class Words {
        private static final String[] WORDS = new String[] {"the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he",
                "was", "for", "on", "are", "as", "with", "his", "they", "I", "at", "be", "this", "have", "from", "or",
                "one", "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", "your", "can", "said",
                "there", "use", "an", "each", "which", "she", "do", "how", "their", "if", "will", "up", "other",
                "about", "out", "many", "then", "them", "these", "so", "some", "her", "would", "make", "like", "him",
                "into", "time", "has", "look", "two", "more", "write", "go", "see", "number", "no", "way", "could",
                "people", "my", "than", "first", "water", "been", "call", "who", "oil", "its", "now", "find", "long",
                "down", "day", "did", "get", "come", "made", "may", "part"};
        public List<String> values = new ArrayList<>(List.of(WORDS));
    }

    @Benchmark
    public void filter_list_with_stream(Words input, Blackhole blackhole) {
        blackhole.consume(input.values.stream()
                .filter(word -> word.equals("these"))
                .collect(Collectors.toList()));
    }

    @Benchmark
    public void filter_list_with_commons_collection(Words input, Blackhole blackhole) {
        blackhole.consume(CollectionUtils.filter(input.values, word -> word.equals("these")));
    }
}
