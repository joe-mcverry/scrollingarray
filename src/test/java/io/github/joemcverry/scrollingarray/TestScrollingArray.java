package io.github.joemcverry.scrollingarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Tests the ScrollingArray class for generating combinations of size 2 from 3
 * elements.
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
public class TestScrollingArray {

	@Test
	void testCombinationsOfSize2From3Elements() {
		ScrollingArray scrollingArray = new ScrollingArray(2, 3, false);
		int[][] expected = { { 0, 1 }, { 0, 2 }, { 1, 2 } };

		int combinationCount = 0;
		do {
			int[] current = scrollingArray.getArray();
			assertArrayEquals(expected[combinationCount], current,
					"Combination " + combinationCount + " does not match expected");
			combinationCount++;
		} while (scrollingArray.scroll() && combinationCount < expected.length);

		assertFalse(scrollingArray.scroll(), "No more combinations should be available");
		assertEquals(3, combinationCount, "Expected exactly 3 combinations");
	}
}