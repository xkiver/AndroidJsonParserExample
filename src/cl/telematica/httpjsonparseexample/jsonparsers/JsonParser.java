package cl.telematica.httpjsonparseexample.jsonparsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cl.telematica.httpjsonparseexample.models.LegsModel;
import cl.telematica.httpjsonparseexample.models.PolylineModel;
import cl.telematica.httpjsonparseexample.models.RouteDetailModel;
import cl.telematica.httpjsonparseexample.models.RouteModel;

public class JsonParser {
	
	public static RouteModel getRouteModelFromString(String data){
		RouteModel routeModel = new RouteModel();
		List<RouteDetailModel> detailList = new ArrayList<RouteDetailModel>();
		List<LegsModel> legsList = new ArrayList<LegsModel>();
		try {
			JSONObject routeModelJson = (JSONObject) new JSONTokener(data).nextValue();
			routeModel.status = routeModelJson.getString("status");
			
			JSONArray routeDetailsJson = routeModelJson.getJSONArray("routes");
			int size = routeDetailsJson.length();
			for(int i = 0; i < size; i++){
				JSONObject details = routeDetailsJson.getJSONObject(i);
				RouteDetailModel routeDetails = new RouteDetailModel();
				PolylineModel polylineModel = new PolylineModel();
				
				JSONArray legArray = details.getJSONArray("legs");
				int legSize = legArray.length();
				for(int j = 0; j < legSize; j++){
					JSONObject legObject = legArray.getJSONObject(j);
					LegsModel legModel = new LegsModel();
					legModel.startAddress = legObject.getString("start_address");
					legModel.endAddress = legObject.getString("end_address");
					
					legsList.add(legModel);
				}
				
				JSONObject overviewPolyline = details.getJSONObject("overview_polyline");
				polylineModel.points = overviewPolyline.getString("points");
				
				routeDetails.legsList = legsList;
				routeDetails.overviewPolyline = polylineModel;
				
				routeDetails.copyrights = details.getString("copyrights");
				
				detailList.add(routeDetails);
			}
			
			routeModel.routesList = detailList;
			return routeModel;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
