import java.util.ArrayList;

public class Operation {
	//таблица страниц виртуальной памяти
    private TablePages tableVirtualMemory;
    //таблица страничных блоков физической памяти
    private ArrayList<Page> tableRealMemory;
    // вместимость физической памяти
    private int maxFrames;
    private ArrayList<Integer> loaning = new ArrayList<>();    

    public Operation (int sizeOfRAM, int sizeOfPage){
    	// страницы виртуальной памяти
        tableVirtualMemory = new TablePages();
        //таблица страничных блоков физической памяти
        tableRealMemory = new ArrayList<Page>();
        // вместимость физической памяти
        maxFrames = sizeOfRAM / sizeOfPage;
        // таблица виртуальный страниц в 2 раза больше физической памяти
        for(int i = 0; i < (sizeOfRAM * 2) / sizeOfPage; i++){
        	// память пуста
        	Page page = new Page(false);
            page.setIndexRealPage(-43424);
            tableVirtualMemory.add(page);
        }
    }

    @SuppressWarnings("unchecked")
	public void insertIntoPhysicalMemory(int pageIndex){
    	Object[] resultObjects;
    	LeastRecentlyUsed algorithm = new LeastRecentlyUsed(tableVirtualMemory, tableRealMemory, loaning);
    	//произошло обращение к виртуальному адресному пространству страницы с номером pageIndex
    	Page page = tableVirtualMemory.get(pageIndex);
        if(!page.isAvailability()){
        	// если есть место в физической памяти
        	if(tableRealMemory.size() < maxFrames){
        		// помечаем, что присутствует в физической памяти
        		page.setAvailability(true);
        		// добавляем страницу в конец физической памяти
        		tableRealMemory.add(page);
        		// запоминаем индекс в физической памяти
        		int indexOfPageFrames = tableRealMemory.indexOf(page);
                page.setIndexRealPage(indexOfPageFrames);
                // при добавлении запомнили индекс виртуального адреса, куда добавили
                loaning.add(pageIndex);
            // если закончилось место в физической памяти
        	} else if(tableRealMemory.size() == maxFrames){
                resultObjects = algorithm.leastRecentlyUsed(page);
                loaning.add(pageIndex);
                tableRealMemory = (ArrayList<Page>)resultObjects[0];
                tableVirtualMemory.setPagesRecords((ArrayList<Page>)resultObjects[1]);
                loaning = (ArrayList<Integer>)resultObjects[2];
            }
        }
        printFrames();
        printVirtualPages();
    }   

    public int getTableVirtualPagesSize(){
        return tableVirtualMemory.size();
    }

    public void printFrames(){
        int i = 0;
        System.out.println("Физическая память");
        for (Page frame : tableRealMemory) {
            System.out.println("i = " + i + "; Проверка присутствия " + frame.getIndexRealPage());
            i++;
        }
        System.out.println();
    }

    private void printVirtualPages(){
    	int i = 0;
        System.out.println("Виртувльня память");
        for (Page page : tableVirtualMemory.getPagesRecords()) {
        	System.out.println("i = " + i + "; Проверка присутствия = "
                    + page.getIndexRealPage() + "; IndexFrame = " + page.getIndexRealPage());
        	i++;
		}
        System.out.println();
    }
}