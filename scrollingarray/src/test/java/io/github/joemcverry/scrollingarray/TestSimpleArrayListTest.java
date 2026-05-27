package io.github.joemcverry.scrollingarray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the CombinatorialArrayList class for invalid sizes and single-element
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
public class TestSimpleArrayListTest {

	private static final List<String> INPUT_LIST = List.of("One", "Two", "Three");

	@Test
	void testInvalidCombinationSizes() {
		List<String> inputList = new ArrayList<>(INPUT_LIST);

		assertThrows(IllegalArgumentException.class, () -> new CombinatorialArrayList<>(inputList, 0),
				"Should throw IllegalArgumentException for combination size 0");

		assertThrows(IllegalArgumentException.class,
				() -> new CombinatorialArrayList<>(inputList, inputList.size() + 1),
				"Should throw IllegalArgumentException for combination size equal to list size");
	}

	@Test
	void testSingleElementCombinations() {
		List<String> inputList = new ArrayList<>(INPUT_LIST);
		CombinatorialArrayList<String> combinator;
		try {
			combinator = new CombinatorialArrayList<>(inputList, 1);
		} catch (IllegalArgumentException e) {
			fail("Construction should not fail: " + e.getMessage());
			return;
		}

		List<List<String>> expected = List.of(List.of("One"), List.of("Two"), List.of("Three"));

		int combinationCount = 0;
		do {
			List<String> sublist = combinator.getNewList();
			assertNotNull(sublist, "Sublist should not be null");
			assertEquals(1, sublist.size(), "Sublist should contain exactly one element");
			assertEquals(expected.get(combinationCount), sublist,
					"Combination " + combinationCount + " does not match expected");
			combinationCount++;
		} while (combinator.scroll());

		assertEquals(3, combinationCount, "Expected exactly 3 combinations");
	}
}