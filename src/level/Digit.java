package level;

public class Digit {
	private byte value;
	private boolean visible;
	
	public Digit(int value) {
		this.value = (byte) value;
		this.visible = true;
	}
	
	public Digit(int value, boolean visible) {
		this.value = (byte) value;
		this.visible = visible;
	}
	public Digit(byte value) {
		this.value = value;
		this.visible = true;
	}
	
	public Digit(byte value, boolean visible) {
		this.value = value;
		this.visible = visible;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = (byte) value;
	}
	public void setValue(byte value) {
		this.value =  value;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
