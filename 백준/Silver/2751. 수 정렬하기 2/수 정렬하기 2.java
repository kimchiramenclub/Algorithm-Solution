public class Main {

	public static void main(String[] args) throws Exception	{
		int N = nextInt();
		MinHeap mh = new MinHeap(N);
		for(int i = 0; i < N; i++) {
			mh.add(nextInt());;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(mh.poll()).append('\n');
		}
		System.out.println(sb);
	}

	static final int SIZE = 1 << 13;
	static byte[] buffer = new byte[SIZE];
	static int index, size;
	static int nextInt() throws Exception {
		int sign = 0;
    	int n = 0;
    	byte c;
    	while ((c = read()) <= 32);
    	if(c == 45) {
    		sign = 1;
    		c = read();
    	}
    	do n = (n << 3) + (n << 1) + (c & 15);
    	while ((c = read()) > 47 && c < 58);
    	return sign == 0 ? n : ~n + 1;
    }
	static byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

class MinHeap {
	int index;
	int[] heap; // binary tree
	
	public MinHeap(int capacity) {
		this.heap = new int[capacity + 1];
		this.index = 0;
	}
	
	public int getSize() {
		return index;
	}
	
	public int peek() {
		return heap[1];
	}
	
	public void add(int value) {
		this.heap[++index] = value;
		int parentIdx, childIdx = index;
		
		while((parentIdx = childIdx >> 1) > 0) { // shift up
			if(value < heap[parentIdx]) {
	    		heap[childIdx] = heap[parentIdx]; // change
	    		heap[parentIdx] = value;
	    		childIdx >>= 1;
	    	}
			else break;
		}
	}
	
	public int poll() {
		if(index == 0) return 0;
		
		int min = this.heap[1];
		this.heap[1] = this.heap[index--];
		int parentIdx = 1, leftIdx, rightIdx, parent, left, right;
		while((leftIdx = parentIdx << 1) <= index) {
			parent = heap[parentIdx];
			left = heap[leftIdx];
			
			if((rightIdx = leftIdx + 1) <= index) {
				right = heap[rightIdx];
				
				if(left < right && left < parent) { // left
					heap[parentIdx] = left;
					heap[leftIdx] = parent;
					parentIdx = leftIdx;
				}
				else if(right < parent) { // right
					heap[parentIdx] = right;
					heap[rightIdx] = parent;
					parentIdx = rightIdx;
				}
				else break;
			}
			else {
				if(left < parent) {
					heap[parentIdx] = left;
					heap[leftIdx] = parent;
				}
				break;
			}
		}
		
		return min;
	}
}