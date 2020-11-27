package model.adt;

import java.util.Collection;
import java.util.HashMap;

public class MyDictionary<T1, T2> implements IDictionary<T1,T2>{
    HashMap<T1, T2> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<T1,T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        dictionary.put(v1,v2);
    }

    @Override
    public void remove(T1 v1){
        dictionary.remove(v1);
    }
    @Override
    public void update(T1 v1, T2 v2) {
        T2 oldValue = this.lookup(v1);
        dictionary.replace(v1, oldValue, v2);
    }

    @Override
    public T2 lookup(T1 id) {
        return dictionary.get(id);
    }

    @Override
    public boolean isDefined(String id) {
            return dictionary.containsKey(id);
    }

    public String toString(){
        return dictionary.toString();
    }


    public Collection<T2> getContent() {
        return dictionary.values();
    }
}
