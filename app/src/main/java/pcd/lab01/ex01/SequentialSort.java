package pcd.lab01.ex01;

import java.util.*;

public class SequentialSort {

	static final int VECTOR_SIZE = 200000000;
	
	public static void main(String[] args) throws InterruptedException {
		// log(Integer.toString(Runtime.getRuntime().availableProcessors()));
		log("Generating array...");
		long[] v = genArray(VECTOR_SIZE);
		
		log("Array generated.");
		log("Sorting (" + VECTOR_SIZE + " elements)...");
	
		
		long t0 = System.nanoTime();
		List<Thread> t = new ArrayList<Thread>();
		int initial = 0;
		int space = v.length/Runtime.getRuntime().availableProcessors();
		int finale = space;
		for(int i = 0; i<Runtime.getRuntime().availableProcessors(); i++){
			final int init = initial;
			final int fin = finale;
			Runnable r1 = () -> {
				Arrays.sort(v, init, fin);
			};
			initial += space;
			finale += space;
			Thread thread = new Thread(r1);
			thread.start();
			t.add(thread);
		}	
		for(int i = 0; i<Runtime.getRuntime().availableProcessors(); i++){
			t.get(i).join();
		}		
		Arrays.sort(v, 0, v.length);
		long t1 = System.nanoTime();
		log("Done. Time elapsed: " + ((t1 - t0) / 1000000) + " ms");
		

		// dumpArray(v);
	}





	private static long[] genArray(int n) {
		Random gen = new Random(System.currentTimeMillis());
		long v[] = new long[n];
		for (int i = 0; i < v.length; i++) {
			v[i] = gen.nextLong();
		}
		return v;
	}

	private static void dumpArray(long[] v) {
		for (long l:  v) {
			System.out.print(l + " ");
		}
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}
