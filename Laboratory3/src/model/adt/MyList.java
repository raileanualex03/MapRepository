package model.adt;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {
    ArrayList<T> list;

    public MyList(){
        list = new ArrayList<T>();
    };
    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public T pop() {
        return list.remove(list.size()-1);
    }

    public String toString(){
        return list.toString();
    }
}
