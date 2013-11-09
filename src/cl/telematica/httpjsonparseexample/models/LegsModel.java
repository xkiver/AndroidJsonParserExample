package cl.telematica.httpjsonparseexample.models;

import java.util.List;

public class LegsModel {
	
	public DistanceModel distance;
	
	public DurationModel duration;
	
	public String endAddress;
	
	public EndLocationModel endLocation;
	
	public String startAddress;
	
	public StartLocationModel startLocation;
	
	public List<StepModel> steps;

}
