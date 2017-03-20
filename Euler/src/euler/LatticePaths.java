package euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LatticePaths {

	public static void main(String[] args) throws Exception {
		/*
		 * System.out.println("paths 2,2 "+paths(2,2));
		 * System.out.println("paths 20,20 "+paths(20,20)); //407575348
		 * 407575348
		 */
		ExecutorService executor = Executors.newFixedThreadPool(4);
		// doPaths(executor, new PathsCallable(18, 20), new PathsCallable(19,
		// 19));
		// 137846528820
		// took millis:49129

		// ConcurrentHashMap<Entry, Long> store = new ConcurrentHashMap<Entry,
		// Long>();
		Map<Entry, Long> store = Collections
				.synchronizedMap(new HashMap<Entry, Long>());
		/*
		 * doPaths(executor, 2l, new PathsMapsCallable(18, 20, store), new
		 * PathsMapsCallable(19, 19, store));
		 */
		// paths 20,20 137846528820
		// took millis:3
		/*
		 * executor.submit(new PathsMapsCallable(4, 4, store));
		 * doPaths(executor, 2l, new PathsMapsCallable(18, 20, store), new
		 * PathsMapsCallable(19, 19, store));
		 */
		long[][] values = new long[21][21];
		long startmillis = System.currentTimeMillis();
		long result = smartPathsWithArray(20,20,values);
		long endmillis = System.currentTimeMillis() - startmillis;
		System.out.println("paths 20,20 " + result);
		System.out.println("took millis:" + endmillis);
		
	}

	private static void doPaths(ExecutorService executor, long multiplier,
			Callable<Long>... args) {
		long startmillis = System.currentTimeMillis();
		ArrayList<Future<Long>> futures = new ArrayList<Future<Long>>(
				args.length);
		for (Callable<Long> arg : args) {
			futures.add(executor.submit(arg));
		}
		// Future<Long> h1future = c1);
		// Future<Long> h2future = executor.submit(c2);
		long result = 0;
		try {
			for (Future<Long> future : futures) {
				result += future.get();
			}
		} catch (ExecutionException ex) {
			ex.printStackTrace();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endmillis = System.currentTimeMillis() - startmillis;
		System.out.println("paths 20,20 " + multiplier * result);
		System.out.println("took millis:" + endmillis);
	}

	public static long paths(int h, int v) {
		if (h == 0 || v == 0)
			return 1;
		return paths(h - 1, v) + paths(h, v - 1);
	}

	public static long smartPaths(int h, int v) {
		if (h == 0 || v == 0)
			return 1;
		if (h == v) {
			return 2 * smartPaths(h - 1, v);
		}
		return smartPaths(h - 1, v) + smartPaths(h, v - 1);
	}

	public static long smartPathsWithMaps(int h, int v, Map<Entry, Long> store) {
		if (h == 0 || v == 0)
			return 1;
		Entry entry = Entry.getEntry(h, v);
		Long tableval = store.get(entry);
		if (tableval != null) {
			return tableval;
		}
		long result;
		if (h == v) {
			result = 2 * smartPathsWithMaps(h - 1, v, store);
		} else {
			result = smartPathsWithMaps(h - 1, v, store)
					+ smartPathsWithMaps(h, v - 1, store);
		}
		store.put(entry, result);
		return result;
	}

	public static long smartPathsWithArray(int h, int v, long[][] store) {
		if (h == 0 || v == 0)
			return 1;
		if (store[h][v] != 0) {
			return store[h][v];
		}
		long result;
		if (h == v) {
			result = 2 * smartPathsWithArray(h - 1, v, store);
		} else {
			result = smartPathsWithArray(h - 1, v, store)
					+ smartPathsWithArray(h, v - 1, store);
		}
		store[h][v] = result;
		return result;
	}

	public static class PathsCallable implements Callable<Long> {
		int h, v;

		public PathsCallable(int h, int v) {
			this.h = h;
			this.v = v;
		}

		@Override
		public Long call() throws Exception {
			return LatticePaths.smartPaths(h, v);
		}
	}

	public static class PathsMapsCallable implements Callable<Long> {
		int h, v;
		Map<Entry, Long> store;

		public PathsMapsCallable(int h, int v, Map<Entry, Long> store) {
			this.h = h;
			this.v = v;
			this.store = store;
		}

		@Override
		public Long call() throws Exception {
			return LatticePaths.smartPathsWithMaps(h, v, store);
		}
	}

	public static class Entry {
		private int bigger, smaller;
		static Entry[][] instances;
		static {
			instances = new Entry[30][30];
		}

		private Entry(int bigger, int smaller) {
			this.bigger = bigger;
			this.smaller = smaller;

		}

		public static Entry getEntry(int l1, int l2) {
			int bigger, smaller;
			if (l1 > l2) {
				bigger = l1;
				smaller = l2;
			} else {
				bigger = l2;
				smaller = l1;
			}
			if (instances[bigger][smaller] == null) {
				instances[bigger][smaller] = new Entry(bigger, smaller);
			}
			return instances[bigger][smaller];
		}

		@Override
		public boolean equals(Object o) {
			if (o.getClass() != Entry.class) {
				return false;
			}
			Entry other = (Entry) o;
			return other.bigger == this.bigger && other.smaller == this.smaller;
		}

		@Override
		public int hashCode() {
			int hash = 1;
			hash = (hash * 17 + this.bigger);
			hash = (hash * 31 + this.smaller);
			return hash;
		}

		@Override
		public String toString() {
			return new StringBuilder("[").append(smaller).append(",")
					.append(bigger).append("]").toString();
		}
	}

}
