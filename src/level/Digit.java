package level;

public class Digit {
	private int value;
	private boolean visible;
	
	public Digit(int value) {
		this.value = value;
		this.visible = false;
	}
	
	public Digit(int value, boolean visible) {
		this.value = value;
		this.visible = visible;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
