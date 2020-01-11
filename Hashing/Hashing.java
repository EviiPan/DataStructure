package myHashing;

public class Hashing {
	int capacity;
	int elementCount;
	
	Hashing(){
		this.capacity = 0;
		this.elementCount = 0;
	}
	
	int hash(int key) {
		return key % capacity;
	}
	
	int rehash(int key) {
		return (key / capacity) % capacity;
	}
	
	protected class Pair{
		int key;
		int value;
		
		Pair(int key, int value){
			this.key = key;
			this.value = value;
		}
	}
}
