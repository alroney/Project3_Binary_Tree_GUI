package bTreePack;

/**
 * Author: Andrew Roney
 * Date: 09/12/2022
 * Description: User created Error Handling.
 */

public class InvalidTreeSyntax extends Exception{
	InvalidTreeSyntax(String msg) {
		super(msg);
	}
}
