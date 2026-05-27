package io.github.joemcverry.scrollingarray;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic class that generates combinations from an input list using a
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
public class CombinatorialArrayList<T> {
	private final List<T> originalList;
	private final int maxCombinations;
	private ScrollingArray scrollingArray;
	private int currentCombinationSize;

	/**
	 * Constructs a CombinatorialArrayList with the specified list and combination
	 * size.
	 * 
	 * @param originalList    the input list to generate combinations from
	 * @param combinationSize the number of elements in each combination
	 * @throws IllegalArgumentException if combinationSize is invalid
	 */
	public CombinatorialArrayList(List<T> originalList, int combinationSize) {
		validateCombinationSize(combinationSize, originalList.size());
		this.originalList = originalList;
		this.currentCombinationSize = combinationSize;
		this.maxCombinations = combinationSize;
		this.scrollingArray = new ScrollingArray(combinationSize, originalList.size());
	}

	/**
	 * Constructs a CombinatorialArrayList with the specified list, combination
	 * size, and maximum combinations.
	 * 
	 * @param originalList    the input list to generate combinations from
	 * @param combinationSize the initial number of elements in each combination
	 * @param maxCombinations the maximum number of elements in combinations
	 * @throws IllegalArgumentException if combinationSize or maxCombinations is
	 *                                  invalid
	 */
	public CombinatorialArrayList(List<T> originalList, int combinationSize, int maxCombinations) {
		validateCombinationSize(combinationSize, originalList.size());
		if (combinationSize > maxCombinations) {
			throw new IllegalArgumentException("maxCombinations must be at least combinationSize: " + maxCombinations);
		}
		if (maxCombinations > originalList.size()) {
			throw new IllegalArgumentException("maxCombinations cannot exceed list size: " + maxCombinations);
		}
		this.originalList = originalList;
		this.currentCombinationSize = combinationSize;
		this.maxCombinations = maxCombinations;
		this.scrollingArray = new ScrollingArray(combinationSize, originalList.size());
	}

	/**
	 * Returns a new list containing the current combination.
	 * 
	 * @return a list of type T containing the current combination
	 */
	public List<T> getNewList() {
		List<T> newList = new ArrayList<>();
		for (int pos : scrollingArray.getArray()) {
			newList.add(originalList.get(pos));
		}
		return newList;
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
			scrollingArray = new ScrollingArray(currentCombinationSize, originalList.size());
			return true;
		}
		return false;
	}

	/**
	 * Validates the combination size against the list size.
	 * 
	 * @param combinationSize the size to validate
	 * @param listSize        the size of the input list
	 * @throws IllegalArgumentException if combinationSize is invalid
	 */
	private void validateCombinationSize(int combinationSize, int listSize) {
		if (combinationSize < 1 || combinationSize > listSize) {
			throw new IllegalArgumentException("Invalid combination size: " + combinationSize);
		}
	}
}