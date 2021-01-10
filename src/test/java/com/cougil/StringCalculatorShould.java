package com.cougil;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringCalculatorShould {

    @Test
    void return_0_for_empty_string() {
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    void return_the_same_number_if_present_only_one() {
        assertThat(StringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    void sum_two_numbers_separated_by_new_lines_or_commas() {
        assertThat(StringCalculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    void sum_an_unknown_amount_of_numbers_separated_by_commas() {
        assertThat(StringCalculator.add("1\n2\n3,4")).isEqualTo(10);
    }

    @Test
    void sum_using_another_delimiter_defined_in_the_first_line() {
        assertThat(StringCalculator.add("//;\n1;2")).isEqualTo(3);
    }

    @Test
    void summing_negative_numbers_should_fail() {
        assertThatThrownBy(()-> StringCalculator.add("-1,-2"))
                .isExactlyInstanceOf(NumberFormatException.class)
                .hasMessageContaining("Negatives are not allowed. Found: -1,-2");
    }

    @Test
    void summing_ignoring_numbers_bigger_than_1000() {
        assertThat(StringCalculator.add("1\n2000,3")).isEqualTo(4);
    }
}
