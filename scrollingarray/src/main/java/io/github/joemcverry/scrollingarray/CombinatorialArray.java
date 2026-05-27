package io.github.joemcverry.scrollingarray;

import java.lang.reflect.Array;

/**
 * A generic class that generates combinations from an input array using a
 * ScrollingArray.
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
public class CombinatorialArray<T> {
	private final T[] originalArray;
	private final int maxCombinations;
	private ScrollingArray scrollingArray;
	private int currentCombinationSize;

	/**
	 * Constructs a CombinatorialArray with the specified array and combination
	 * size.
	 * 
	 * @param originalArray   the input array to generate combinations from
	 * @param combinationSize the number of elements in each combination
	 * @throws IllegalArgumentException if combinationSize is invalid
	 */
	public CombinatorialArray(T[] originalArray, int combinationSize) {
		validateCombinationSize(combinationSize, originalArray.length);
		this.originalArray = originalArray;
		this.currentCombinationSize = combinationSize;
		this.maxCombinations = combinationSize;
		this.scrollingArray = new ScrollingArray(combinationSize, originalArray.length);
	}

	/**
	 * Constructs a CombinatorialArray with the specified array, combination size,
	 * and maximum combinations.
	 * 
	 * @param originalArray   the input array to generate combinations from
	 * @param combinationSize the initial number of elements in each combination
	 * @param maxCombinations the maximum number of elements in combinations
	 * @throws IllegalArgumentException if combinationSize or maxCombinations is
	 *                                  invalid
	 */
	public CombinatorialArray(T[] originalArray, int combinationSize, int maxCombinations) {
		validateCombinationSize(combinationSize, originalArray.length);
		if (combinationSize > maxCombinations) {
			throw new IllegalArgumentException("maxCombinations must be at least combinationSize: " + maxCombinations);
		}
		if (maxCombinations > originalArray.length) {
			throw new IllegalArgumentException("maxCombinations cannot exceed array length: " + maxCombinations);
		}
		this.originalArray = originalArray;
		this.currentCombinationSize = combinationSize;
		this.maxCombinations = maxCombinations;
		this.scrollingArray = new ScrollingArray(combinationSize, originalArray.length);
	}

	/**
	 * Returns a new array containing the current combination.
	 * 
	 * @return an array of type T containing the current combination
	 */
	public T[] getNewArray() {
		int[] indices = scrollingArray.getArray();
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) Array.newInstance(originalArray.getClass().getComponentType(), indices.length);
		for (int i = 0; i < indices.length; i++) {
			newArray[i] = originalArray[indices[i]];
		}
		return newArray;
	}

	/**
	 * Advances to the next combination, increasing combination size if necessary.
	 * 
	 * @return true if a new combination is available, false if all combinations are
	 *         exhausted
	 */
	public boolean scroll() {
		if (scrollingArray.scroll()) {
			return true;
		}
		if (currentCombinationSize < maxCombinations) {
			currentCombinationSize++;
			scrollingArray = new ScrollingArray(currentCombinationSize, originalArray.length);
			return true;
		}
		return false;
	}

	/**
	 * Validates the combination size against the array length.
	 * 
	 * @param combinationSize the size to validate
	 * @param arrayLength     the length of the input array
	 * @throws IllegalArgumentException if combinationSize is invalid
	 */
	private void validateCombinationSize(int combinationSize, int arrayLength) {
		if (combinationSize < 1 || combinationSize > arrayLength) {
			throw new IllegalArgumentException("Invalid combination size: " + combinationSize);
		}
	}
}