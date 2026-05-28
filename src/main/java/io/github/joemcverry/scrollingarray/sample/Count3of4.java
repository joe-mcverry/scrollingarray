package io.github.joemcverry.scrollingarray.sample;

import java.util.ArrayList;
import java.util.List;

import io.github.joemcverry.scrollingarray.CombinatorialArray;
import io.github.joemcverry.scrollingarray.CombinatorialArrayList;

/**
 * Demonstrates generating combinations using CombinatorialArray and
 * CombinatorialArrayList.
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
public class Count3of4 {
	/**
	 * Main method to demonstrate combination generation.
	 * 
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args) {
		// Demonstrate CombinatorialArrayList with a list of strings
		List<String> inputList = new ArrayList<>();
		inputList.add("One");
		inputList.add("Two");
		inputList.add("Three");
		inputList.add("Four");

		try {
			CombinatorialArrayList<String> listCombinator = new CombinatorialArrayList<>(inputList, 3, 4);
			int listCount = 0;
			do {
				List<String> sublist = listCombinator.getNewList();
				System.out.printf("List %d: %s%n", listCount, sublist);
				listCount++;
			} while (listCombinator.scroll());
		} catch (IllegalArgumentException e) {
			System.err.println("Error with list combinator: " + e.getMessage());
		}

		// Demonstrate CombinatorialArray with an array of strings
		String[] fields = { "A", "BB", "CCC", "D" };
		try {
			CombinatorialArray<String> arrayCombinator = new CombinatorialArray<>(fields, 1, fields.length);
			do {
				String[] sublist = arrayCombinator.getNewArray();
				System.out.print("{");
				for (int i = 0; i < sublist.length; i++) {
					System.out.print("\"" + sublist[i] + "\"");
					if (i < sublist.length - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("}");
			} while (arrayCombinator.scroll());
		} catch (IllegalArgumentException e) {
			System.err.println("Error with array combinator: " + e.getMessage());
		}
	}
}