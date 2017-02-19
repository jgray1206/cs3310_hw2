package edu.wmich.cs3310.hw2.Gray.sorts;

import java.util.LinkedList;

/**
 * This interface is the foundation for my class that will 
 * contain all the sorts needed for this project.
 * @author john
 *
 */
public interface ISort<E extends Comparable<E>> {
	
	/**
	 * Sorts an array via bubble sort.
	 * @param input An unsorted, random array
 	 * @return A sorted array
	 */
	public E[] arrayBubble(E[] input);
	
	/**
	 * Sorts a linked list via bubble sort
	 * @param input An unsorted, random linked list
	 * @return A sorted linked list
	 */
	public LinkedList<E> linkedBubble(LinkedList<E> input);
	
	/**
	 * Sorts an array via merge sort.
	 * @param input An unsorted, random array
	 * @param lower The lower bound for the array
	 * @param upper The upper bound for the array
	 * @return A sorted array
	 */
	public E[] arrayMergeSort(E[] input, int lower, int upper);
	
	/**
	 * Merges the recursively split array chunks from arrayMergeSort
	 * @param input the unsorted, random array
	 * @param lower the lower bound of the current chunk
	 * @param mid point between lower/upper
	 * @param upper the upper bound of current chunk
	 * @return the two chunks combined into one bigger, sorted chunk
	 */
	public E[] mergeArray(E[] input, int lower, int mid, int upper);
	
	/**
	 * Sorts a linked list via merge sort
	 * @param input An unsorted, random linked list
	 * @param lower The lower bound for the linked list
	 * @param upper The upper bound for the linked list
	 * @return A sorted linked list
	 */
	public LinkedList<E> linkedMergeSort(LinkedList<E> input, int lower, int upper);
	
	/**
	 * Merges the recursively split linked list chunks from linkedMergeSort
	 * @param input the unsorted, random array
	 * @param lower the lower bound of the current chunk
	 * @param mid mid point between lower/upper
	 * @param upper the upper bound of the current chunk
	 * @return the two chunks combined into one bigger, sorted chunk
	 */
	public LinkedList<E> mergeLinked(LinkedList<E> input, int lower, int mid, int upper);
	
	/**
	 * Sorts an array via quick sort
	 * @param input An unsorted, random array
	 * @param lower The lower bound of the portion of array
	 * @param upper The upper bound of the portion of array
	 * @return The sorted array
	 */
	public E[] arrayQuick(E[] input, int lower, int upper);
	
	/**
	 * Sorts a linked list via quick sort
	 * @param input An unsorted, random linked list
	 * @param lower The lower bound of the portion of linked list
	 * @param upper The upper bound of the portion of linked list
	 * @return The sorted linked list
	 */
	public LinkedList<E> linkedQuick(LinkedList<E> input, int lower, int upper);
	
	/**
	 * Sorts an array via insertion sort
	 * @param input An unsorted, random array
	 * @return The sorted array
	 */
	public E[] arrayInsertion(E[] input);
	
	/**
	 * Sorts a linked list via insertion sort
	 * @param input An unsorted, random linked list
	 * @return The sorted linked list
	 */
	public LinkedList<E> linkedInsertion(LinkedList<E> input);
	
	/**
	 * Sorts an array via built in Java sort
	 * @param input An unsorted, random array
	 * @return The sorted array
	 */
	public E[] arrayJava(E[] input);
	
	/**
	 * Sorts a linked list via built in Java sort
	 * @param input An unsorted, random linked list
	 * @return The sorted linked list
	 */
	public LinkedList<E> linkedJava(LinkedList<E> input);
	
	/**
	 * EXTRA CREDIT:
	 * Sorts an array via selection sort
	 * @param input An unsorted, random array
	 * @return The sorted array
	 */
	public E[] arraySelection(E[] input);
	
	/**
	 * EXTRA CREDIT:
	 * Sorts a linked list via selection sort
	 * @param input An unsorted, random linked list
	 * @return The sorted linked list
	 */
	public LinkedList<E> linkedSelection(LinkedList<E> input);
	
}
