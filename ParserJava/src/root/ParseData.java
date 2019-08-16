package root;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParseData {
	public static int counter = 0;
	static CheckIdentifier checkIdentifier;

	public static String parseByteArray(byte[] bytes) {
		LinkedHashMap<String, String> dict = new LinkedHashMap<String, String>();
		byte numberOfElements = bytes[counter+5];
		counter += 6;
//		 System.out.println("Number of elements: " + Integer.toString(numberOfElements));
		for (int i = 0; i < numberOfElements; i++) {
//			 System.out.println("Loop run #" + Integer.toString(i));
			String key;

			// find key
			if (CheckIdentifier.isString(bytes[counter])) {
				
				System.out.println("Test: " + bytes[counter]);
				counter++;
				key = "\'";
				while (bytes[counter] != 0) {
					key += (char) bytes[counter];
					counter++;
				}
				counter++;
				key += "\'";
//				 System.out.println("key#" + Integer.toString(i+1) + ": " + key);
			} else if (CheckIdentifier.isInteger(bytes[counter])) {
				counter += 4;
				key = Integer.toString((int) bytes[counter]);
				counter++;

			} else {
				key = "NULL";
				System.out.println("Something is wrong in finding key#" + Integer.toString(i + 1));
				System.out.println("counter position: " + Integer.toString(counter));
				System.out.println("Byte at counter: " + bytes[counter]);
			}
			// System.out.print("key#" + Integer.toString(i+1) + ": " + key + "\t");
			// find value
			String value = null;
			if (CheckIdentifier.isString(bytes[counter])) {
				counter++;
				value = "\'";
				while (bytes[counter] != 0) {
					value += (char) bytes[counter];
					counter++;
				}
				counter++;
				value += "\'";
			}

			else if (CheckIdentifier.isInteger(bytes[counter])) {
				counter += 4;
				value = Integer.toString((int) bytes[counter]);
				counter++;
			} else if (CheckIdentifier.isList(bytes[counter])) {
				List<String> list = new ArrayList<String>();
				counter += 5;
				int numberOfItems = (int) bytes[counter];
				counter++;
//				 System.out.println("Number of items in list: " +
//				 Integer.toString(numberOfItems));

				for (int k = 0; k < numberOfItems; k++) {
					String item = null;
					if (CheckIdentifier.isString(bytes[counter])) {
						counter++;
						item = "\'";
						while (bytes[counter] != 0) {
							item += (char) bytes[i];
							counter++;
						}
						
						item += "\'";
						
					} else if (CheckIdentifier.isInteger(bytes[counter])) {
						counter += 4;
						item = Integer.toString((int) bytes[counter]);
					} else if (CheckIdentifier.isDictionary(bytes[counter])) {
						item = parseByteArray(bytes);
					} else {
						System.out.println("Reached else statement in list#" + Integer.toString(k));
					}

					list.add(item);
				}
				value = list.toString();
			} else if (CheckIdentifier.isDictionary(bytes[counter])) {
				value = parseByteArray(bytes);
			} else {
				value = "NULL";
				System.out.println("Something is wrong in finding value#" + Integer.toString(i + 1));
				System.out.println("counter position: " + Integer.toString(counter));
				System.out.println("Byte at counter: " + bytes[counter]);
			}

//			 System.out.println("value#" + Integer.toString(i+1) + ": " +
//			 value.toString());

			// add key, value to dictionary
			dict.put(key, value);
		}
        // Displaying elements of LinkedHashMap
		String out = "{" + "\n";
		Set<?> set = dict.entrySet();
		Iterator<?> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) it.next();
			out += "\t";
			out += mapEntry.getKey();
			out += ": ";

			out += mapEntry.getValue();
			out += ", ";
			out += "\n";
		}
		out += "\t" + "\r" + "}";
		return out;
	}

}
