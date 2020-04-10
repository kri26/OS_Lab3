import java.util.ArrayList;
import java.util.Iterator;

public class Operation {
   private ArrayList<Page> tableVirtualMemory;
   private ArrayList<Page> tableRealMemory;
   private int capacityRealMemory;
   ArrayList<Integer> loaning = new ArrayList<>();

   public Operation(int memRAM, int sizeOfPage){
       tableVirtualMemory = new ArrayList<Page>();
       tableRealMemory = new ArrayList<Page>();
       capacityRealMemory = memRAM/sizeOfPage;
       for(int i = 0; i <(memRAM * 2)/sizeOfPage; i++){
           Page page = new Page(false);
           page.setIndexRealPage(-43424);
           tableVirtualMemory.add(page);
       }
   }

   public void inputNumberOfPage(int pageIndex){
       Page page = tableVirtualMemory.get(pageIndex);
       if(!page.isAvailability()){
           if(tableRealMemory.size() < capacityRealMemory){
               page.setAvailability(true);
               tableRealMemory.add(page);
               int indexOfPageFrames = tableRealMemory.indexOf(page);
               page.setIndexRealPage(indexOfPageFrames);
               loaning.add(pageIndex);
           }else if(tableRealMemory.size() == capacityRealMemory){
        	   leastRecentlyUsed(page, pageIndex);
           }
       }
       printPageFrames();
       printPages();
   }
   
   private void leastRecentlyUsed(Page page, int pageIndex) {
	   	int index = 0;
	   	loop:
	   	for (int i = 0; i < loaning.size(); i++) {
				int temp = loaning.get(i);
				for (int j = 0; j < tableVirtualMemory.size(); j++) {
					if((tableVirtualMemory.get(j).isAvailability()) && (j == temp)) {
						Page result = tableVirtualMemory.get(j);
						index = result.getIndexRealPage();
						break loop;
					}
				}
			}
	   	tableRealMemory.get(index).setAvailability(false);
	   	tableRealMemory.get(index).setIndexRealPage(-43424);
	   	tableRealMemory.remove(index);
	    tableRealMemory.add(index, page);
        page.setIndexRealPage(index);
        page.setAvailability(true);
       
   }

   public int getSizeTablePages(){
       return tableVirtualMemory.size();
   }

   public void printPageFrames(){
       int i = 0;
       System.out.println("");
       System.out.println("Физическая память");
       for (Page currentPage: tableRealMemory) {
           System.out.println("i = " + i + "; Приверка присутсвия = " + currentPage.isAvailability());
           i++;
       }
       System.out.println();
   }

   private void printPages(){
       System.out.println("Виртуальная помять");
       for(int i = 0; i<tableVirtualMemory.size(); i++){
           System.out.println("i = " + i + "; Приверка присутсвия = "
                   + tableVirtualMemory.get(i).isAvailability() + "; IndexFrame = " + tableVirtualMemory.get(i).getIndexRealPage());
       }
       System.out.println();
   }
}
