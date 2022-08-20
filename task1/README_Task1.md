###Report 

Using *ConcurrentHashMap* instead of simple *HashMap* resolve the problem, but using *Collections.synchronizeMap()* doesn't help.

The simplest way to fix *ConcurrentModificationException* in code, when we are using simple implementation of *HashMap* - use *synchronized* block.
