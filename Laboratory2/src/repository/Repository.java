package repository;

import exceptions.MemoryError;
import exceptions.NotFoundException;


abstract class Repository<TElem> {

    abstract boolean add(TElem elem) throws NotFoundException, MemoryError;
    abstract boolean remove(TElem elem) throws NotFoundException;
    abstract boolean update(TElem elem) throws NotFoundException, MemoryError;
    abstract FixedSizeArray get();

}
