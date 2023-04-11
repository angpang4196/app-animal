package data.sido;

import java.util.Arrays;

public class SidoItems {

	SidoItem[] item;

	public SidoItem[] getItem() {
		return item;
	}

	public void setItem(SidoItem[] item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Items [item=" + Arrays.toString(item) + "]";
	}

}
