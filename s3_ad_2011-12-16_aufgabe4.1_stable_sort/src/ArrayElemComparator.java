import java.util.Comparator;
import java.util.List;

//http://www.torsten-horn.de/techdocs/java-generics.htm#super-versus-extends
class ArrayElemComparator<T extends Comparable<? super T>> implements Comparator<List<T>>{

	final private int compareIndex;
	
	public ArrayElemComparator(int compareIndex) {
		this.compareIndex = compareIndex-1;
    }

	@Override
	public int compare(List<T> thisObj, List<T> thatObj) {
        return (thisObj.get(compareIndex)).compareTo(thatObj.get(compareIndex));
	}
}
