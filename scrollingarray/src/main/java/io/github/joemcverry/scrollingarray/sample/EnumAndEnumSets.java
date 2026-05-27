package io.github.joemcverry.scrollingarray.sample;

/**
 * used for sample programs
 * Joe McVerry
 */


import java.util.EnumSet;

import io.github.joemcverry.scrollingarray.CombinatorialArray;

public class EnumAndEnumSets {

	enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	};

	public static void main(String[] args) {
		CombinatorialArray<Day> dayArray = new CombinatorialArray<>(Day.values(), 4, Day.values().length - 2);
		do {
			EnumSet<Day> days = EnumSet.of(dayArray.getNewArray()[0], dayArray.getNewArray());
			System.out.println(days);
		} while (dayArray.scroll());

	}

}
