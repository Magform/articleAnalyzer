public class ArrayMap implements Map {
	private Pair[] p;
	private int pSize;
	private static final int INITIALSIZE = 1;

	public ArrayMap() {
		p = new Pair[INITIALSIZE];
		makeEmpty();
	}

	public void makeEmpty() {
		pSize = 0;
	}

	public boolean isEmpty() {
		return (pSize == 0);
	}

	public int put(String key, int value) {
		if (key == null || value == -1) {
			throw new IllegalArgumentException();
		}
		int oldValue = remove(key);
		if (pSize == p.length) {
			p = resize(p, p.length + 1);
		}
		p[pSize++] = new Pair(key, value);
		return oldValue;
	}

	public int remove(String key) {
		for (int i = 0; i < pSize; i++) {
			if (p[i].getKey().equals(key)) {
				int value = p[i].getValue();
				p[i] = p[pSize - 1];
				pSize--;
				return value;
			}
		}
		return -1;
	}

	public int get(String key) {
		for (int i = 0; i < pSize; i++) {
			if (p[i].getKey().equals(key)) {
				return p[i].getValue();
			}
		}
		return -1;
	}

	public String[] keys() {
		String[] keys = new String[pSize];
		for (int i = 0; i < pSize; i++) {
			keys[i] = p[i].getKey();
		}
		return keys;
	}

	public void orderByValue() {
		if (p != null) {
			for (int i = 0; i < p.length - 1; i++) {
				int maxPos = findMaxPosFrom(i);
				if (maxPos != i) {
					swap(maxPos, i);
				}
			}
		}
	}

	private void swap(int i, int j) {
		Pair temp = new Pair(p[i].getKey(), p[i].getValue());
		p[i].setValue(p[j].getValue());
		p[i].setKey(p[j].getKey());
		p[j].setValue(temp.getValue());
		p[j].setKey(temp.getKey());
	}

	private int findMaxPosFrom(int from) {
		int pos = from;
		for (int i = pos + 1; i < p.length; i++) {
	    if (p[i].getValue() > p[pos].getValue()) {
	    	pos = i;
			}
			else if (p[i].getValue() == p[pos].getValue()) {
				if (p[i].getKey().compareTo(p[pos].getKey()) < 0) {
					pos = i;
				}
			}
		}
		return pos;
	}

	private static Pair[] resize(Pair[] a, int aNewSize) {
		if (a == null) {
			return new Pair[0];
		}
		if (aNewSize < 0) {
			throw new IllegalArgumentException();
		}
		Pair[] newA = new Pair[aNewSize];
		if (aNewSize < a.length) {
			for (int i = 0; i < aNewSize; i++) {
				newA[i] = a[i];
			}
		}
		else {
			for (int i = 0; i < a.length; i++) {
				newA[i] = a[i];
			}
		}
		return newA;
	}

	private class Pair {
		private String key;
		private int value;

		public Pair(String k, int v) {
			setKey(k);
			setValue(v);
		}

		public String getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}

		public void setKey(String k) {
			key = k;
		}

		public void setValue(int v) {
			value = v;
		}
	}
}
