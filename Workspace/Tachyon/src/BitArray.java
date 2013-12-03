public class BitArray {
	long[] data;
	int num;
	int n;

	public BitArray(int n) {
		this.n = n;
		num = n / 32 + 1;

		data = new long[num];
	}

	public void shift() {
		for (int i = 0; i < num; i++) {
			data[i] = data[i] >> 1;

			if (i != num - 1) {
				int v = (int) (data[i + 1] & 1);

				long temp = 1 << 31;

				if (v == 0) {
					data[i] = data[i] & (~temp);
				} else {
					data[i] = data[i] | temp;
				}
			}
		}
	}

	public void and(BitArray b) {
		for (int i = 0; i < num; i++) {
			data[i] = data[i] & b.data[i];
		}
	}

	public int getBit(int i) {
		int which = i / 32;
		int offset = i % 32;

		return (int) ((data[which] >> offset) & 1);
	}

	public void setBit(int i, int v) {
		int which = i / 32;
		int offset = i % 32;

		long temp = 1 << offset;

		if (v == 0) {
			data[which] = data[which] & (~temp);
		} else {
			data[which] = data[which] | temp;
		}
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < n; i++) {
			s =  getBit(i) + s;
		}
		return s;
	}
}
