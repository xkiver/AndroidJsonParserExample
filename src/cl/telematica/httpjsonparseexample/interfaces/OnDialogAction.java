package cl.telematica.httpjsonparseexample.interfaces;

import android.content.DialogInterface;

public interface OnDialogAction {
	
	/**
	 * Called when the user press the positive button
	 */
	public void onPositiveButtonPressed(DialogInterface dialog);
	
	/**
	 * Called when the user press the negative button
	 */
	public void onNegativeButtonPressed(DialogInterface dialog);
	
	/**
	 * Called when the dialog is cancelled
	 */
	public void onDialogCancel(DialogInterface dialog);

}
