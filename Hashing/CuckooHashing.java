package myHashing;

public class CuckooHashing extends Hashing implements Hash{
	Pair[] h1;
	Pair[] h2;
	int[] cnt1;
	int[] cnt2;
	
	CuckooHashing(int capacity) {
		super();
		this.h1 = new Pair[capacity];
		this.h2 = new Pair[capacity];
		this.cnt1 = new int[10000];
		this.cnt2 = new int[10000];
		this.capacity = capacity;
	}
	
	@Override
	public void insert(int key, int value) throws Exception{
		int index = hash(key);
		int i = 0;
		Pair p = new Pair(key, value);
		Pair original = h1[index];
		while(original != null && ((i == 0 && cnt1[key] < 3) || (i == 1 && cnt2[key] < 3))) {
			if(i == 0) {
				original = h1[index];
				h1[index] = p;
				cnt1[index]++;
			}
			else {
				original = h2[index];
				h2[index] = p;
				cnt2[index]++;
			}
			p = original;
			i = 1 - i;
			index = (p != null ? (i == 0 ? hash(p.key) : rehash(p.key)) : 0);
		}
		
		if((i == 0 && cnt1[key] >= 3) || (i == 1 && cnt2[key] >= 3)) {
			throw new Exception("loop found");
		}
		
		if(i == 0)
			h1[index] = p;
		else
			h2[index] = p;
		elementCount++; 
	}
	
	@Override
	public int search(int key) {
		int index = hash(key);
		if(h1[index] != null && h1[index].key == key) {
			return h1[index].value;
		}
		else {
			index = rehash(key);
			if(h2[index] != null && h2[index].key == key) {
				return h2[index].value;
			}
		}
		return -1;
	}
	
	@Override
	public void delete(int key) throws Exception{
		int index = hash(key);
		if(h1[index] != null && h1[index].key == key) {
			h1[index] = null;
			elementCount--;
		}
		else {
			index = rehash(key);
			if(h2[index] != null && h2[index].key == key) {
				h2[index] = null;
				elementCount--;
			}
		}
	}
	
	@Override
	public void clear() {
		this.h1 = new Pair[capacity];
		this.h2 = new Pair[capacity];
		this.cnt1 = new int[capacity];
		this.cnt2 = new int[capacity];
		this.elementCount = 0;
	}
}
