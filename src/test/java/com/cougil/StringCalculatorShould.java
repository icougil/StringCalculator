package com.cougil;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    void sum_an_unkown_amount_of_numbers_separated_by_commas() {
        assertThat(StringCalculator.add("1\n2\n3,4")).isEqualTo(10);
    }
}
