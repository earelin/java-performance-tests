package net.earelin.java.performance;

import org.apache.commons.collections4.CollectionUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class StreamMapVsLoopRunner {

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
    public void map_with_collection_transform(Words input, Blackhole blackhole) {
        var result = CollectionUtils.transformingCollection(input.values, word -> format("%s - %s", word, word));
        blackhole.consume(result);
    }

    @Benchmark
    public void map_with_loop(Words input, Blackhole blackhole) {
        List<String> result = new ArrayList<>();
        for (String word : input.values) {
            result.add(format("%s - %s", word, word));
        }
        blackhole.consume(result);
    }

    @Benchmark
    public void map_with_stream(Words input, Blackhole blackhole) {
        blackhole.consume(input.values.stream()
                .map(word -> format("%s - %s", word, word))
                .collect(Collectors.toList()));
    }
}
