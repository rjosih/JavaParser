package root;

public class CheckIdentifier {
	static boolean isString(byte b) {
		if (b == 2) {
			return true;
		} else {
			return false;
		}
	}

	static boolean isInteger(byte b) {
		if (b == 1) {
			return true;
		} else {
			return false;
		}
	}

	static boolean isList(byte b) {
		if (b == 5) {
			return true;
		} else {
			return false;
		}
	}

	static boolean isDictionary(byte b) {
		if (b == 6) {
			return true;
		} else {
			return false;
		}
	}
}
