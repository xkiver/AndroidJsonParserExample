package cl.telematica.httpjsonparseexample.factories;

import cl.telematica.httpjsonparseexample.interfaces.OnDialogAction;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Build;
import android.view.ContextThemeWrapper;

public class MessageFactory {
	
	@SuppressLint("InlinedApi")
	public static AlertDialog getAlertDialog(Context ctx,
			final OnDialogAction actionHandler, String title, String message,
			String negativeButton, String positiveButton) {
		AlertDialog.Builder builder;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			builder = new AlertDialog.Builder(new ContextThemeWrapper(ctx, android.R.style.Theme_Holo_Dialog));
		} else {
			builder = new AlertDialog.Builder(ctx);
		}
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton(negativeButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (actionHandler != null)
							actionHandler.onNegativeButtonPressed(dialog);
					}
				});
		builder.setPositiveButton(positiveButton,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (actionHandler != null)
							actionHandler.onPositiveButtonPressed(dialog);
					}
				});
		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				if (actionHandler != null)
					actionHandler.onDialogCancel(dialog);
			}
		});

		AlertDialog dialogAlert = builder.create();
		return dialogAlert;
	}

}
