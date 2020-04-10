import java.util.ArrayList;

public class LeastRecentlyUsed {
	
	private TablePages tableVirtualMemory;
	private ArrayList<Page> tableRealMemory;
	ArrayList<Integer> loaning = new ArrayList<>();
	
	public LeastRecentlyUsed(TablePages tableVirtualMemory, ArrayList<Page> tableRealMemory,
			ArrayList<Integer> loaning) {
		this.tableVirtualMemory = tableVirtualMemory;
		this.tableRealMemory = tableRealMemory;
		this.loaning = loaning;	
	}
	
	 public Object[] leastRecentlyUsed(Page page) {
			Object[] result = new Object[3];
		   	int index = 0;
		   	int[] lastUsedDurations = new int[tableRealMemory.size()];
	    	for (int i = 0; i < lastUsedDurations.length; i++) {
	    		lastUsedDurations[i] = -1;
	    	}
		   	point:
		   	for (int i = 0; i < loaning.size(); i++) {
					int temp = loaning.get(i);
					for (int j = 0; j < tableVirtualMemory.size(); j++) {
						if((tableVirtualMemory.get(j).isAvailability()) && (j == temp)
								&& (lastUsedDurations[tableVirtualMemory.get(j).getIndexRealPage()] == -1)) {
							lastUsedDurations[tableVirtualMemory.get(j).getIndexRealPage()] = i;
							if (AreAllDurationsFound(lastUsedDurations)) {
								index = GetIndexOfMinElement(lastUsedDurations);
								System.out.println("Введен " + tableVirtualMemory.get(j).getIndexRealPage());
								break point;
							}
						}
					}
				}
		   	tableRealMemory.get(index).setAvailability(false);
		   	tableRealMemory.get(index).setIndexRealPage(-43424);
		   	tableRealMemory.remove(index);
		    tableRealMemory.add(index, page);
	        page.setIndexRealPage(index);
	        page.setAvailability(true);
	        result[0] = tableRealMemory;
	        result[1] = tableVirtualMemory.getPagesRecords();
	        result[2] = loaning;
	        return result;
	   }

	 private boolean AreAllDurationsFound(int[] a) {
	    	for (int i = 0; i < a.length; i++) {
	    		if (a[i] == -1) {
	    			return false;
	    		}    		
	    	}
	    	return true;
	    }
	    
	    private int GetIndexOfMinElement(int[] a) {
	    	int min = a[0];
	    	int index = 0;
	    	for (int i = 0; i < a.length; i++) {
	    		if (a[i] < min) {
	    			min = a[i];
	    			index = i;
	    		}
	    	}
	    	return index;
	    }
 }
