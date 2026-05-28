package io.github.joemcverry.scrollingarray;

/**
 * A class that manages a scrolling array for generating combinations.
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
public class ScrollingArray {
	private final int combinationCount;
	private final int originalSize;
	private final int[] array;
	private final boolean repeatAllPositions;

	/**
	 * Constructs a ScrollingArray with specified combination count and size.
	 * 
	 * @param combinationCount number of elements in each combination
	 * @param originalSize     total number of available elements
	 */
	public ScrollingArray(int combinationCount, int originalSize) {
		this(combinationCount, originalSize, false);
	}

	/**
	 * Constructs a ScrollingArray with specified parameters.
	 * 
	 * @param combinationCount   number of elements in each combination
	 * @param originalSize       total number of available elements
	 * @param repeatAllPositions whether to allow repeated positions
	 */
	public ScrollingArray(int combinationCount, int originalSize, boolean repeatAllPositions) {
		this.combinationCount = combinationCount;
		this.originalSize = originalSize;
		this.repeatAllPositions = repeatAllPositions;
		this.array = new int[combinationCount];

		for (int i = 0; i < combinationCount; i++) {
			array[i] = repeatAllPositions ? 0 : i;
		}
	}

	/**
	 * Returns the current array of combination indices.
	 * 
	 * @return the current combination array
	 */
	public int[] getArray() {
		return array;
	}

	/**
	 * Attempts to scroll to the next valid combination.
	 * 
	 * @return true if a new valid combination was found, false otherwise
	 */
	public boolean scroll() {
		return scroll(combinationCount - 1);
	}

	/**
	 * Recursively scrolls the array at the specified level.
	 * 
	 * @param level the current level to scroll
	 * @return true if a new valid combination was found, false otherwise
	 */
	private boolean scroll(int level) {
		if (level < 0) {
			return false;
		}

		array[level]++;
		if (array[level] <= originalSize - (combinationCount - level)) {
			return true;
		}

		if (scroll(level - 1)) {
			array[level] = repeatAllPositions ? 0 : array[level - 1] + 1;
			return true;
		}
		return false;
	}
}