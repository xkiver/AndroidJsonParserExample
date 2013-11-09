package cl.telematica.httpjsonparseexample;

import java.util.List;

import cl.telematica.httpjsonparseexample.asynctask.DownloadManager;
import cl.telematica.httpjsonparseexample.interfaces.DownloadListener;
import cl.telematica.httpjsonparseexample.jsonparsers.JsonParser;
import cl.telematica.httpjsonparseexample.models.LegsModel;
import cl.telematica.httpjsonparseexample.models.RouteDetailModel;
import cl.telematica.httpjsonparseexample.models.RouteModel;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadActivity extends Activity implements DownloadListener {
	
	private ProgressBar progressBar;
	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		text = (TextView) findViewById(R.id.textView1);
		
		new DownloadManager(this, 10000, 15000, "GET")
					.execute(getString(R.string.page_url));
	}

	@Override
	public void onRequestStart() {
		if(progressBar.getVisibility() == View.GONE)
			progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void onRequestComplete(String data) {
		if(progressBar.getVisibility() == View.VISIBLE)
			progressBar.setVisibility(View.GONE);
		String textToShow = "";
		RouteModel model = JsonParser.getRouteModelFromString(data);
		if(model != null){
			List<RouteDetailModel> details = model.routesList;
			if(details != null && details.size() > 0){
				RouteDetailModel dModel = details.get(0);
				List<LegsModel> legsList = dModel.legsList;
				if(legsList != null && legsList.size() > 0){
					LegsModel lModel = legsList.get(0);
					textToShow = "Resultado: \n\n" +
										"status: " + model.status + "\n\n" +
										"copyright: " + dModel.copyrights + "\n\n" +
										"Steps length: " + dModel.legsList.size() + "\n\n" +
										"Start address: " + lModel.startAddress + "\n\n" +
										"End address: " + lModel.endAddress;
				} else {
					textToShow = "error";
				}
			} else {
				textToShow = "error";
			}
		} else {
			textToShow = "error";
		}
		text.setText(textToShow);
	}

	@Override
	public void onError(String error, int code) {
		if(progressBar.getVisibility() == View.VISIBLE)
			progressBar.setVisibility(View.GONE);
		text.setText(error);
	}	

}
