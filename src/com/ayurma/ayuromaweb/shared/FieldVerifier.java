package com.ayurma.ayuromaweb.shared;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {

	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return name.length() < 100;
	}
	public static boolean isValidStringLength(String name,int maxLength){
		if (name == null) {
			return false;
		}
		return name.length() < maxLength;
	}
	public static boolean isIntegerFormat(String strInt){
		boolean isInt=false;
		int i;
		try{
			i=Integer.valueOf(strInt);
			isInt=true;

		}catch(NumberFormatException e){
			isInt=false;

		}
		return isInt;
	}
	public static boolean isFloatFormat(String strNumber){
		boolean isFloat=false;
		float fNum;
		try{
			fNum=Float.valueOf(strNumber);
			isFloat=true;

		}catch(NumberFormatException e){
			isFloat=false;

		}
		return isFloat;
	}
	public static boolean isEmailValid(String email){
		boolean isValid=false;
		String pattern ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		isValid=email.matches(pattern);
		return isValid;
	}
	public static boolean isEmpty(String string){
		String str=string.trim();
		if(str.isEmpty()) return true;
		else return false;
		
	}
}
