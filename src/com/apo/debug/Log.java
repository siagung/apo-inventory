package com.apo.debug;

/**Log convenience methods (just println methods, nothing special)**/
public class Log {
	
	/**Create a debug message (System.out.println)
	 * 
	 * @param source The object where the debug message is coming from
	 * @param message The actual debug message
	 */
	public static void debugMsg (Object source, String message) {
		debugMsg(source.getClass().getCanonicalName(), message);
	}
	
	/**Create an error message (System.err.println)
	 * 
	 * @param source The object where the error message is coming from
	 * @param message The actual error message
	 */
	public static void errorMsg (Object source, String message) {
		errorMsg(source.getClass().getCanonicalName(), message);
	}
	
	/**Create a debug message (System.out.println)
	 * 
	 * @param classObject the Class object representation of the class the call is coming from (use Class.getClass() or ClassName.class)
	 * @param message the message to be printed to console
	 */
	public static void debugMsg (Class<?> classObject, String message) {
		debugMsg(classObject.getCanonicalName(), message);
	}
	
	/**Create an error message (System.err.println)
	 * 
	 * @param classObject a Class object representation of the class the call is coming from (use Class.getClass() or ClassName.class)
	 * @param message the message to be printed to console
	 */
	public static void errorMsg (Class<?> classObject, String message) {
		errorMsg(classObject.getCanonicalName(), message);
	}
	
	/**Create a debug message (regular System.out.println)
	 * 
	 * @param TAG the TAG assigned for a class
	 * @param message the debug message to be printed to console
	 */
	public static void debugMsg (String TAG, String message) {
		System.out.println(TAG + ": " + message);
	}
	
	/**Create an error message (regular System.err.println)
	 * 
	 * @param TAG the TAG assigned for a class
	 * @param message the debug message to be printed to console
	 */
	public static void errorMsg (String TAG, String message) {
		System.err.println(TAG + ": " + message);
	}
}
