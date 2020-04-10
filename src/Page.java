

public class Page {
	// false - отсутствует в памяти, true - присутствует
    private boolean availability;
    // индекс страницы в физической памяти
    private int indexRealPage;

    public Page(boolean availability){
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getIndexRealPage() {
        return indexRealPage;
    }

    public void setIndexRealPage(int indexRealPage) {
        this.indexRealPage = indexRealPage;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}