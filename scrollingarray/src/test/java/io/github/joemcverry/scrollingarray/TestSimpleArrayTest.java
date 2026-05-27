package io.github.joemcverry.scrollingarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the CombinatorialArray class for invalid sizes and single-element
 * combinations.
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
public class TestSimpleArrayTest {

	private static final String[] INPUT_ARRAY = { "One", "Two", "Three" };

	@Test
	void testInvalidCombinationSizes() {
		assertThrows(IllegalArgumentException.class, () -> new CombinatorialArray<>(INPUT_ARRAY, 0),
				"Should throw IllegalArgumentException for combination size 0");

		assertThrows(IllegalArgumentException.class,
				() -> new CombinatorialArray<>(INPUT_ARRAY, INPUT_ARRAY.length + 1),
				"Should throw IllegalArgumentException for combination size equal to array length");
	}

	@Test
	void testSingleElementCombinations() {
		CombinatorialArray<String> combinator;
		try {
			combinator = new CombinatorialArray<>(INPUT_ARRAY, 1);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<String[]> expected = List.of(new String[] { "One" }, new String[] { "Two" }, new String[] { "Three" });

		int combinationCount = 0;
		do {
			String[] sublist = combinator.getNewArray();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(1, sublist.length, "Sublist should contain exactly one element");
			assertArrayEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(3, combinationCount, "Expected exactly 3 combinations");
	}
}