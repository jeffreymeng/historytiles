package level;

public class Digit {
	private byte value;
	private boolean isVisible;
	private boolean isSpace;
	
	public Digit(int value) {
		this.value = (byte) value;
		this.isVisible = true;
		this.isSpace = false;
	}
	
	public Digit(int value, boolean visible) {
		this.value = (byte) value;
		this.isVisible = visible;
		this.isSpace = false;
	}
	public Digit(int value, boolean visible, boolean isSpace) {
		this.value = (byte) value;
		this.isVisible = visible;
		this.isSpace = false;
	}
	public Digit(boolean visible, boolean isSpace) {
		
		this.isSpace = true;
		this.isVisible = visible;
	}
	public Digit(byte value) {
		this.value = value;
		this.isVisible = true;
	}
	
	public Digit(byte value, boolean visible) {
		this.value = value;
		this.isVisible = visible;
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
		return isVisible;
	}
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public boolean isSpace() {
		return isSpace;
	}

	public void setSpace(boolean isSpace) {
		this.isSpace = isSpace;
	}
}
