package net.earelin.java.performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

public class DoubleValidationRunner {
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
                "5",
                "jda;foj",
                "-974797456093145.9587093475",
                "5984309.34832467e-5"
        };
    }

    @Benchmark
    public void parse_double_validating_string(DoubleValidationRunner.Values input, Blackhole blackhole) {
        for (String value : input.values) {
            blackhole.consume(validateUsingFloatParse(value));
        }
    }

    @Benchmark
    public void regular_expression_validating_string(DoubleValidationRunner.Values input, Blackhole blackhole) {
        for (String value : input.values) {
            blackhole.consume(validateUsingRegex(value));
        }
    }

    @Benchmark
    public void commons_lang_validating_string(DoubleValidationRunner.Values input, Blackhole blackhole) {
        for (String value : input.values) {
            blackhole.consume(validateUsingCommonsLang(value));
        }
    }

    private boolean validateUsingFloatParse(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateUsingRegex(String value) {
        return value.matches("^[+\\-]?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+\\-]?\\d+)?$");
    }

    private boolean validateUsingCommonsLang(String value) {
        return isCreatable(value);
    }
}
