package ke.co.technovation.example;

import java.io.Serializable;

/**
 * ElectricCar is where bussiness needs
 * in terms of code, but doesn't know it yet.
 * 
 * You need to start using ElectricCar sooner than later.
 * 
 * You need to inherit a lot from the DieselCar in many places
 * in your code.
 * 
 * 
 * @author timothymwangi
 *
 */
public class ElectricCar implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4196983756666004109L;
	public Integer wheelCount;
	public String paintCode;
	public String sideMirrorStyle;
	public String seatColor;
	public String upholsteryColorCode;
	public Integer groundClearance;
	public Integer steeringWheelDiameter;
	public String wheelSize;
	public Integer horsePower;
	public Integer lbTorque;
	
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
		builder.append("\nElectricCar \n[\n\twheelCount=");
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
		builder.append(", \n\thorsePower=");
		builder.append(horsePower);
		builder.append(", \n\tlbTorque=");
		builder.append(lbTorque);
		builder.append("\n]\n\n");
		return builder.toString();
	}
	

}
