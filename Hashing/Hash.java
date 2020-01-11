package myHashing;

public interface Hash {
	void insert(int key, int value) throws Exception;
    int search(int key) throws Exception;
    void delete(int key) throws Exception;
    void clear();
}
