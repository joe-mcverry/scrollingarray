package io.github.joemcverry.scrollingarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the CombinatorialArray class for generating combinations of sizes 2 to
 * 4 from arrays.
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
public class TestMultipleCountsOf3and4Arrays {

	private static final String[] STRING_INPUT = { "One", "Two", "Three" };
	private static final Double[] DOUBLE_INPUT = { 1.0, 20.0, 0.3, 4.3 };

	@Test
	void testCombinationsOfSize2And3From3Elements() {
		CombinatorialArray<String> combinator;
		try {
			combinator = new CombinatorialArray<>(STRING_INPUT, 2, 3);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<String[]> expected = List.of(new String[] { "One", "Two" }, new String[] { "One", "Three" },
				new String[] { "Two", "Three" }, new String[] { "One", "Two", "Three" });

		int combinationCount = 0;
		do {
			String[] sublist = combinator.getNewArray();
			assertNotNull(sublist, "Sublist should not be null");
			assertArrayEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			assertEquals(combinationCount < 3 ? 2 : 3, sublist.length,
					"Incorrect combination size at index " + combinationCount);
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(4, combinationCount, "Expected exactly 4 combinations");
	}

	@Test
	void testCombinationsOfSize3And4From4Elements() {
		CombinatorialArray<Double> combinator;
		try {
			combinator = new CombinatorialArray<>(DOUBLE_INPUT, 3, 4);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<Double[]> expected = List.of(new Double[] { 1.0, 20.0, 0.3 }, new Double[] { 1.0, 20.0, 4.3 },
				new Double[] { 1.0, 0.3, 4.3 }, new Double[] { 20.0, 0.3, 4.3 }, new Double[] { 1.0, 20.0, 0.3, 4.3 });

		int combinationCount = 0;
		do {
			Double[] sublist = combinator.getNewArray();
			assertNotNull(sublist, "Sublist should not be null");
			assertArrayEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			assertEquals(combinationCount < 4 ? 3 : 4, sublist.length,
					"Incorrect combination size at index " + combinationCount);
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(5, combinationCount, "Expected exactly 5 combinations");
	}

	@Test
	void testCombinationsOfSize2To4From4Elements() {
		CombinatorialArray<Double> combinator;
		try {
			combinator = new CombinatorialArray<>(DOUBLE_INPUT, 2, 4);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<Double[]> expected = List.of(new Double[] { 1.0, 20.0 }, new Double[] { 1.0, 0.3 },
				new Double[] { 1.0, 4.3 }, new Double[] { 20.0, 0.3 }, new Double[] { 20.0, 4.3 },
				new Double[] { 0.3, 4.3 }, new Double[] { 1.0, 20.0, 0.3 }, new Double[] { 1.0, 20.0, 4.3 },
				new Double[] { 1.0, 0.3, 4.3 }, new Double[] { 20.0, 0.3, 4.3 }, new Double[] { 1.0, 20.0, 0.3, 4.3 });

		int combinationCount = 0;
		do {
			Double[] sublist = combinator.getNewArray();
			assertNotNull(sublist, "Sublist should not be null");
			assertArrayEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			int expectedSize = combinationCount < 6 ? 2 : combinationCount < 10 ? 3 : 4;
			assertEquals(expectedSize, sublist.length, "Incorrect combination size at index " + combinationCount);
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(11, combinationCount, "Expected exactly 11 combinations");
	}
}