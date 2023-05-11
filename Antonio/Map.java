public interface Map extends Container {
	int put(String key, int value);
	int remove(String key);
	int get(String key);
	String[] keys();
}
