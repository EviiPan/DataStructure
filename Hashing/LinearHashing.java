package myHashing;

public class LinearHashing extends Hashing implements Hash{
	Pair[] linearMap;
	
	LinearHashing(int capacity){
		super();
		this.capacity = capacity;
		this.linearMap = new Pair[capacity];
	}
	
	@Override
	public void insert(int key, int value) {
		int index = hash(key);
		while(linearMap[index] != null && linearMap[index].key != key) {
			index = (index+1) % capacity;
		}
		linearMap[index] = new Pair(key, value);
		elementCount++;
	}
	
	@Override
	public int search(int key) throws Exception{
		int index = hash(key);
		while(linearMap[index] != null && linearMap[index].key != key) {
			index = (index+1) % capacity;
		}
		if(linearMap[index] == null)
			throw new Exception("key not found");
		return linearMap[index].value;
	}
	
	@Override
	public void delete(int key) throws Exception{
		int index = hash(key);
		while(linearMap[index] != null && linearMap[index].key != key) {
			index = (index+1) % capacity;
		}
		if(linearMap[index] == null)
			return;
		int next = (index+1) % capacity;
		while(linearMap[next] != null) {
			int reindex = hash(linearMap[next].key);
			if((next >= (index+1) % capacity && (reindex < (index+1) % capacity || reindex > next))
					|| (next < (index+1) % capacity && (reindex > next && reindex < (index+1) % capacity))) {
				linearMap[index] = linearMap[next];
				//linearMap[next] = null;
				index = next;
			}
			next = (next+1) % capacity;
		}
		linearMap[index] = null;
		elementCount--;
	}
	
	@Override
	public void clear() {
		this.linearMap = new Pair[capacity];
		this.elementCount = 0;
	}
}
