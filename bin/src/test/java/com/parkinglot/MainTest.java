package com.parkinglot;

import org.junit.Test;

public class MainTest {

    @Test
    public void shouldReadFile() {
        //given
        String[] inputFile = {"file_inputs.txt"};
        //when
        Main.main(inputFile);
        //then
    }
}