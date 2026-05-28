package io.github.joemcverry.scrollingarray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the CombinatorialArrayList class for fixed-size combinations from lists
 * of 3 or 4 elements.
 * 
 * @author Joe McVerry Copyright 2026 Joe McVerry Licensed under the Apache
 *         License, Version 2.0 (the "License"); you may not use this file
 *         except in compliance with the License. You may obtain a copy of the
 *         License at http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 */
public class TestSingleCountsOf3and4ArrayLists {

	private static final List<String> STRING_INPUT = List.of("One", "Two", "Three");
	private static final List<Double> DOUBLE_INPUT = List.of(1.0, 20.0, 0.3, 4.3);

	@Test
	void testCombinationsOfSize2From3Elements() {
		List<String> inputList = new ArrayList<>(STRING_INPUT);
		CombinatorialArrayList<String> combinator = new CombinatorialArrayList<>(inputList, 2);

		List<List<String>> expected = List.of(List.of("One", "Two"), List.of("One", "Three"), List.of("Two", "Three"));

		int combinationCount = 0;
		do {
			List<String> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(2, sublist.size(), "Sublist should contain exactly 2 elements");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(3, combinationCount, "Expected exactly 3 combinations");
	}

	@Test
	void testCombinationsOfSize3From4Elements() {
		List<Double> inputList = new ArrayList<>(DOUBLE_INPUT);
		CombinatorialArrayList<Double> combinator = new CombinatorialArrayList<>(inputList, 3);

		List<List<Double>> expected = List.of(List.of(1.0, 20.0, 0.3), List.of(1.0, 20.0, 4.3), List.of(1.0, 0.3, 4.3),
				List.of(20.0, 0.3, 4.3));

		int combinationCount = 0;
		do {
			List<Double> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(3, sublist.size(), "Sublist should contain exactly 3 elements");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(4, combinationCount, "Expected exactly 4 combinations");
	}

	@Test
	void testCombinationsOfSize2From4Elements() {
		List<Double> inputList = new ArrayList<>(DOUBLE_INPUT);
		CombinatorialArrayList<Double> combinator = new CombinatorialArrayList<>(inputList, 2);

		List<List<Double>> expected = List.of(List.of(1.0, 20.0), List.of(1.0, 0.3), List.of(1.0, 4.3),
				List.of(20.0, 0.3), List.of(20.0, 4.3), List.of(0.3, 4.3));

		int combinationCount = 0;
		do {
			List<Double> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(2, sublist.size(), "Sublist should contain exactly 2 elements");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(6, combinationCount, "Expected exactly 6 combinations");
	}
}