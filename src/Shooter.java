/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/
package com.vk.lab9;

/**
 * Interface for objects that can shoot something.
 * T The type of "bullet" that is fired.
 */
public interface Shooter<T extends GameObject> {

    /**
     * Create a new "bullet" and return it.
     * @return Fired object.
     */
    T fire();

}