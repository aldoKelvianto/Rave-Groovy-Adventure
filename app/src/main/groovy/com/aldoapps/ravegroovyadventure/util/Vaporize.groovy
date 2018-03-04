package com.aldoapps.ravegroovyadventure.util

class Vaporize {
    static String vaporize(String input) {
        input.toUpperCase().collect {
            it += ' '
        }.toString()
                .replace(',', '')
                .replace('[', '')
                .replace(']', '')
    }
}