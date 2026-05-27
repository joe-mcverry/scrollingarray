package io.github.joemcverry.scrollingarray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the CombinatorialArrayList class for generating combinations of sizes 2
 * to 4.
 * 
 * Copyright 2026 Joe McVerry Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
public class TestMultipleCountsOf3and4ArrayLists {

	@Test
	void testCombinationsOfSize2And3From3Elements() {
		List<String> inputList = new ArrayList<>();
		inputList.add("One");
		inputList.add("Two");
		inputList.add("Three");

		CombinatorialArrayList<String> combinator;
		try {
			combinator = new CombinatorialArrayList<>(inputList, 2, 3);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<List<String>> expected = List.of(List.of("One", "Two"), List.of("One", "Three"), List.of("Two", "Three"),
				List.of("One", "Two", "Three"));

		int combinationCount = 0;
		do {
			List<String> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			assertEquals(combinationCount < 3 ? 2 : 3, sublist.size(),
					"Incorrect combination size at index " + combinationCount);
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(4, combinationCount, "Expected exactly 4 combinations");
	}

	@Test
	void testCombinationsOfSize3And4From4Elements() {
		List<Double> inputList = new ArrayList<>();
		inputList.add(1.0);
		inputList.add(20.0);
		inputList.add(0.3);
		inputList.add(4.3);

		CombinatorialArrayList<Double> combinator;
		try {
			combinator = new CombinatorialArrayList<>(inputList, 3, 4);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<List<Double>> expected = List.of(List.of(1.0, 20.0, 0.3), List.of(1.0, 20.0, 4.3), List.of(1.0, 0.3, 4.3),
				List.of(20.0, 0.3, 4.3), List.of(1.0, 20.0, 0.3, 4.3));

		int combinationCount = 0;
		do {
			List<Double> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			assertEquals(combinationCount < 4 ? 3 : 4, sublist.size(),
					"Incorrect combination size at index " + combinationCount);
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(5, combinationCount, "Expected exactly 5 combinations");
	}

	@Test
	void testCombinationsOfSize2To4From4Elements() {
		List<Double> inputList = new ArrayList<>();
		inputList.add(1.0);
		inputList.add(20.0);
		inputList.add(0.3);
		inputList.add(4.3);

		CombinatorialArrayList<Double> combinator;
		try {
			combinator = new CombinatorialArrayList<>(inputList, 2, 4);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<List<Double>> expected = List.of(List.of(1.0, 20.0), List.of(1.0, 0.3), List.of(1.0, 4.3),
				List.of(20.0, 0.3), List.of(20.0, 4.3), List.of(0.3, 4.3), List.of(1.0, 20.0, 0.3),
				List.of(1.0, 20.0, 4.3), List.of(1.0, 0.3, 4.3), List.of(20.0, 0.3, 4.3), List.of(1.0, 20.0, 0.3, 4.3));

		int combinationCount = 0;
		do {
			List<Double> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			int expectedSize = combinationCount < 6 ? 2 : combinationCount < 10 ? 3 : 4;
			assertEquals(expectedSize, sublist.size(), "Incorrect combination size at index " + combinationCount);
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(11, combinationCount, "Expected exactly 11 combinations");
	}
}