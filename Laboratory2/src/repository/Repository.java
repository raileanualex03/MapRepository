package repository;

import exceptions.MemoryError;
import exceptions.NotFoundException;


interface Repository<TElem> {

    public boolean add(TElem elem) throws NotFoundException, MemoryError;
    public boolean remove(TElem elem) throws NotFoundException;
    public boolean update(TElem elem) throws NotFoundException, MemoryError;
    public FixedSizeArray get();

}
