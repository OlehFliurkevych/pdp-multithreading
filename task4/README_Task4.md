Create simple object pool with support for multithreaded environment. 
No any extra inheritance, polymorphism or generics needed here, just implementation of simple class:

~~~
/**
Pool that block when it has not any items or it full
**/
    public class BlockingObjectPool {

    /**
    * Creates filled pool of passed size
    *
    * @param size of pool
    */
    public BlockingObjectPool(int size) {
        ...
    }

    /**
    * Gets object from pool or blocks if pool is empty
    *
    * @return object from pool
    */
    public Object get() {
        ...
    }

    /**
    * Puts object to pool or blocks if pool is full
    *
    * @param object to be taken back to pool
    */
    public void put(Object object) {
        ...
    }
    }
~~~
Use any blocking approach you like.
