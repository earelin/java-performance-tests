package net.earelin.java.performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.UUID;

public class UuidValidationRunner {

    @State(Scope.Benchmark)
    public static class Ids {
        public String[] values = new String[] {
                "8814bbc7-9867-4103-ade9-8a9c473672d7",
                "asdfg456fdoigvosd",
                "83e7e197-3b18-4c66-b471-efd21ad8c803",
                "asfg8uhnadsf08asudf9iuo;lasdf",
                "85eb1c8e-631d-4cd7-b075-f6d62a40ebac",
                "9s8yhjbdsfbv9a738209-rkopmfa;sdfas",
                "284703f3-5b46-40a8-ab79-fb8ca3d98242",
                "fdsg9[3km41208rd-0asdjhntgp9e809rtu",
                "5c08d674-e745-4fe7-b467-90276da52294",
                "jda;fojaspo[djf;laksjdnfpaosijdfpoas"
        };
    }

    @Benchmark
    public void uuid_validating_string(Ids input, Blackhole blackhole) {
        for (String value : input.values) {
            blackhole.consume(validateUsingUuid(value));
        }
    }

    @Benchmark
    public void regular_expression_validating_string(Ids input, Blackhole blackhole) {
        for (String value : input.values) {
            blackhole.consume(validateUsingRegex(value));
        }
    }

    private boolean validateUsingUuid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean validateUsingRegex(String id) {
        return id.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    }
}
