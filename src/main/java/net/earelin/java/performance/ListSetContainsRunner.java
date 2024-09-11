package net.earelin.java.performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

public class ListSetContainsRunner {
    @State(Scope.Benchmark)
    public static class Values {
        public String[] values = new String[] {
                "8709438529038745098",
                "8814",
                "asd456",
                "83.77e19",
                "g8uhn",
                "85342.8",
                "9s8yhj",
                "284703",
                "fdsg9[",
                "89jsadf7yhni",
                "asdf08js-d098fjasodkfnma0s978dhoi",
                "-974797456093145.9587093475",
                "5984309.34832467e-5",
                "jhsadoifhdsa9j898",
                "SDFGAsdf90ujsd8fhas89",
                "as87dfhgasdf98asdfjJ(*H(J*(JH",
                "N**(*HVUTGIUPhg8osdjhaf098j",
                "lsdafnla;sdjJOIJHONMOIO",
                "98jdafOIJ()J&HOJLKJ",
                "BNUYGkljhiuygid8f9jp-a",
                "m;uhdL;7898098079O87",
                "ngolkm&*(*KSADJFO;AHJS;L",
                "ncyhjnhsla)(HPOIH)(*U"
        };

        public String[] searchValue = new String[] {
                "asdf08js-d098fjasodkfnma0s978dhoi",
                "lkjfa9j9",
                "83.77e19",
                "dskafp809kdafhiksdf"
        };

        public Set<String> unmutableSet = Set.of(values);

        public Set<String> hashSet = new HashSet<>(List.of(values));

        public List<String> unmutableList = List.of(values);

        public List<String> arrayList = new ArrayList<>(List.of(values));
    }

    @Benchmark
    public void unmutable_set_contains_string(ListSetContainsRunner.Values input, Blackhole blackhole) {
        for (String searchValue : input.searchValue) {
            blackhole.consume(input.unmutableSet.contains(searchValue));
        }
    }

    @Benchmark
    public void hash_set_contains_string(ListSetContainsRunner.Values input, Blackhole blackhole) {
        for (String searchValue : input.searchValue) {
            blackhole.consume(input.hashSet.contains(searchValue));
        }
    }

    @Benchmark
    public void unmutable_list_contains_string(ListSetContainsRunner.Values input, Blackhole blackhole) {
        for (String searchValue : input.searchValue) {
            blackhole.consume(input.unmutableList.contains(searchValue));
        }
    }

    @Benchmark
    public void array_list_contains_string(ListSetContainsRunner.Values input, Blackhole blackhole) {
        for (String searchValue : input.searchValue) {
            blackhole.consume(input.arrayList.contains(searchValue));
        }
    }
}
