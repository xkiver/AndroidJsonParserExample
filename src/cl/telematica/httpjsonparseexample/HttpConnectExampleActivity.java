package cl.telematica.httpjsonparseexample;

import cl.telematica.httpjsonparseexample.factories.MessageFactory;
import cl.telematica.httpjsonparseexample.interfaces.OnDialogAction;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HttpConnectExampleActivity extends Activity {
	
	private Button button;
	
	private ConnectivityManager connManager;
	private NetworkInfo netInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_connect_example);
		
		connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(netInfo != null && netInfo.isConnected()){
					Intent intent = new Intent(getApplicationContext(), DownloadActivity.class);
					startActivity(intent);
					finish();
				} else {
					AlertDialog dialog = MessageFactory.getAlertDialog(HttpConnectExampleActivity.this, new OnDialogAction() {
						
											@Override
											public void onPositiveButtonPressed(DialogInterface dialog) {
												dialog.dismiss();
												startActivity(new Intent(Settings.ACTION_SETTINGS));
											}
											
											@Override
											public void onNegativeButtonPressed(DialogInterface dialog) {
												dialog.dismiss();
											}
											
											@Override
											public void onDialogCancel(DialogInterface dialog) {
												dialog.dismiss();
											}
										},  getString(R.string.connectivity_error_title), 
											getString(R.string.connectivity_error), 
											getString(R.string.accept_button), 
											getString(R.string.config_button));
					
					if(dialog != null)
						dialog.show();
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		netInfo = connManager.getActiveNetworkInfo();
	}

}
