package es.franl2p.utils;

public class Converters {

	/**
	 * Takes a String and converts it to a positive greater than 0 Integer number.
	 * @param num String with the number to convert.
	 * @return Integer with the converted number or null if it cannot be converted.
	 */
	public static Integer convert2Number (String num) {
		Integer number = null;
		
		try {
			number = new Integer(num);
			if (number <= 0) {
				number = null;
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
		
		return number;
	}
}