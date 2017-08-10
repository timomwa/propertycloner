package ke.co.technovation.example;

import java.io.Serializable;

import ke.co.technovation.annotations.Ignore;
import ke.co.technovation.annotations.Transferable;

/**
 * DieselCar is a moving thing.
 * 
 * It can be found in legacy code which, if you touch,
 * you will cause code to break. You are re-factoring
 * the old code, but without necessarily causing 
 * delays in delivery.
 * 
 * @author timothymwangi
 *
 */

/* 

  When used at class level, the converter will
  assume all properties with getters are
  transferable

*/
@Transferable
public class DieselCar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7290065463142560821L;
	public Long id;
	public Integer wheelCount;
	public String paintCode;
	public String sideMirrorStyle;
	public String seatColor;
	public String upholsteryColorCode;
	public Integer groundClearance;
	public Integer steeringWheelDiameter;
	public String wheelSize;
	
	
	/*
	 * The @Ignore annotation tells the converter
	 * to ignore this value if @Transferable is used at
	 * class level
	*/
	@Ignore
	public Integer horsePower;
	
	@Ignore
	public Integer lbTorque;
	
	/** Only unique to the diesel car **/
	public Integer engineCapacity;
	public Integer fuelTankCapacity;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getWheelCount() {
		return wheelCount;
	}
	public void setWheelCount(Integer wheelCount) {
		this.wheelCount = wheelCount;
	}
	public String getPaintCode() {
		return paintCode;
	}
	public void setPaintCode(String paintCode) {
		this.paintCode = paintCode;
	}
	public String getSideMirrorStyle() {
		return sideMirrorStyle;
	}
	public void setSideMirrorStyle(String sideMirrorStyle) {
		this.sideMirrorStyle = sideMirrorStyle;
	}
	public String getSeatColor() {
		return seatColor;
	}
	public void setSeatColor(String seatColor) {
		this.seatColor = seatColor;
	}
	public String getUpholsteryColorCode() {
		return upholsteryColorCode;
	}
	public void setUpholsteryColorCode(String upholsteryColorCode) {
		this.upholsteryColorCode = upholsteryColorCode;
	}
	public Integer getGroundClearance() {
		return groundClearance;
	}
	public void setGroundClearance(Integer groundClearance) {
		this.groundClearance = groundClearance;
	}
	public Integer getSteeringWheelDiameter() {
		return steeringWheelDiameter;
	}
	public void setSteeringWheelDiameter(Integer steeringWheelDiameter) {
		this.steeringWheelDiameter = steeringWheelDiameter;
	}
	public String getWheelSize() {
		return wheelSize;
	}
	public void setWheelSize(String wheelSize) {
		this.wheelSize = wheelSize;
	}
	public Integer getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	public Integer getFuelTankCapacity() {
		return fuelTankCapacity;
	}
	public void setFuelTankCapacity(Integer fuelTankCapacity) {
		this.fuelTankCapacity = fuelTankCapacity;
	}
	public Integer getHorsePower() {
		return horsePower;
	}
	public void setHorsePower(Integer horsePower) {
		this.horsePower = horsePower;
	}
	public Integer getLbTorque() {
		return lbTorque;
	}
	public void setLbTorque(Integer lbTorque) {
		this.lbTorque = lbTorque;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nDieselCar \n[\n\tid=");
		builder.append(id);
		builder.append(", \n\twheelCount=");
		builder.append(wheelCount);
		builder.append(", \n\tpaintCode=");
		builder.append(paintCode);
		builder.append(", \n\tsideMirrorStyle=");
		builder.append(sideMirrorStyle);
		builder.append(", \n\tseatColor=");
		builder.append(seatColor);
		builder.append(", \n\tupholsteryColorCode=");
		builder.append(upholsteryColorCode);
		builder.append(", \n\tgroundClearance=");
		builder.append(groundClearance);
		builder.append(", \n\tsteeringWheelDiameter=");
		builder.append(steeringWheelDiameter);
		builder.append(", \n\twheelSize=");
		builder.append(wheelSize);
		builder.append(", \n\tengineCapacity=");
		builder.append(engineCapacity);
		builder.append(", \n\tfuelTankCapacity=");
		builder.append(fuelTankCapacity);
		builder.append(", \n\thorsePower=");
		builder.append(horsePower);
		builder.append(", \n\tlbTorque=");
		builder.append(lbTorque);
		builder.append("\n]\n\n");
		return builder.toString();
	}
	
	

}
