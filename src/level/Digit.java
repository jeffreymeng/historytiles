package level;

public class Digit {
	private byte value;
	private boolean visible;
	private boolean isSpace;
	
	public Digit(int value) {
		this.value = (byte) value;
		this.visible = true;
		this.isSpace = false;
	}
	
	public Digit(int value, boolean visible) {
		this.value = (byte) value;
		this.visible = visible;
		this.isSpace = false;
	}
	public Digit(int value, boolean visible, boolean isSpace) {
		this.value = (byte) value;
		this.visible = visible;
		this.isSpace = false;
	}
	public Digit(boolean visible, boolean isSpace) {
		
		this.isSpace = true;
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
		
		return (int) value;
	}
	public int getByteValue() {
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

	public boolean isSpace() {
		return isSpace;
	}

	public void setSpace(boolean isSpace) {
		this.isSpace = isSpace;
	}
}
