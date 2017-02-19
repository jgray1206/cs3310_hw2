package edu.wmich.cs3310.hw2.Gray.sorts;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class holds all the different sorts needed for this project.
 * It contains linked list and array implementations of bubble, merge,
 * quick, insertion, built in java, and selection sort.
 * 
 * All Javadocs for these methods are in the ISort interface included with
 * this project.
 * @author john
 *
 */
public class Sort<E extends Comparable<E>> implements ISort<E> {

	@Override
	public E[] arrayBubble(E[] input) {
		if(input==null||input.length<2){
			return input;
		}
		
		boolean sorted = false;
		
		while(!sorted){
			sorted = true;
			for(int i = 0; i < input.length-1; i++){	
				if((input[i]).compareTo(input[i+1]) > 0){
					E temp = input[i+1];
					input[i+1] = input[i];
					input[i] = temp;
					sorted = false;
				}
			}
		}
		return input;
	}

	@Override
	public LinkedList<E> linkedBubble(LinkedList<E> input) {
		if(input==null||input.size()<2){
			return input;
		}
		
		boolean sorted = false;
		
		while(!sorted){
			sorted = true;
			for(int i = 0; i < input.size()-1; i++){	
				if((input.get(i)).compareTo(input.get(i+1)) > 0){
					E temp = input.get(i+1);
					input.set(i+1,input.get(i));
					input.set(i, temp);
					sorted = false;
				}
			}
		}
		return input;
	}

	@Override
	public E[] arrayMergeSort(E[] input, int lower, int upper) {
		if(upper-lower<=1){
			return input;
		}
		int mid = lower+(upper-lower)/2;
		arrayMergeSort(input, mid, upper);
		arrayMergeSort(input, lower, mid);
		return mergeArray(input, lower, mid, upper);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E[] mergeArray(E[] input, int lower, int mid, int upper){
		E[] temp1 = (E[])new Comparable[mid-lower];
		E[] temp2 = (E[])new Comparable[upper-mid];
		
		for(int i = 0; i < temp1.length; i++){
			temp1[i] = input[i+lower];
		}
		for(int j = 0; j<temp2.length; j++){
			temp2[j] = input[j+mid];
		}
		
		int index1 = 0;
		int index2 = 0;
		int arrayIndex = lower;
		
		while(index1 < temp1.length && index2 < temp2.length){
			if(temp1[index1].compareTo(temp2[index2])<=0){
				input[arrayIndex] = temp1[index1];
				index1++;
				arrayIndex++;
			}
			else{
				input[arrayIndex] = temp2[index2];
				index2++;
				arrayIndex++;
			}
		}
		
		while(index1<temp1.length){
			input[arrayIndex] = temp1[index1];
			index1++;
			arrayIndex++;
		}
		
		while(index2<temp2.length){
			input[arrayIndex] = temp2[index2];
			index2++;
			arrayIndex++;
		}
		
		return input;
	}
	
	@Override
	public LinkedList<E> linkedMergeSort(LinkedList<E> input, int lower, int upper) {
		if(upper-lower<=1){
			return input;
		}
		int mid = lower+(upper-lower)/2;
		linkedMergeSort(input, mid, upper);
		linkedMergeSort(input, lower, mid);
		return mergeLinked(input, lower, mid, upper);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LinkedList<E> mergeLinked(LinkedList<E> input, int lower, int mid, int upper) {
		LinkedList<E> temp1 = (LinkedList<E>)new LinkedList<Comparable<E>>();
		LinkedList<E> temp2 = (LinkedList<E>)new LinkedList<Comparable<E>>();
		
		for(int i = 0; i < mid-lower; i++){
			temp1.add(input.get(i+lower));
		}
		for(int j = 0; j<upper-mid; j++){
			temp2.add(input.get(j+mid));
		}
		
		int index1 = 0;
		int index2 = 0;
		int arrayIndex = lower;
		
		while(index1 < temp1.size() && index2 < temp2.size()){
			if(temp1.get(index1).compareTo(temp2.get(index2))<=0){
				input.set(arrayIndex, temp1.get(index1));
				index1++;
				arrayIndex++;
			}
			else{
				input.set(arrayIndex, temp2.get(index2));
				index2++;
				arrayIndex++;
			}
		}
		
		while(index1<temp1.size()){
			input.set(arrayIndex, temp1.get(index1));
			index1++;
			arrayIndex++;
		}
		
		while(index2<temp2.size()){
			input.set(arrayIndex, temp2.get(index2));
			index2++;
			arrayIndex++;
		}
		
		return input;
	}

	@Override
	public E[] arrayQuick(E[] input, int lower, int upper) {
		if(input==null||input.length<=2){
			return input;
		}
		
		int l = lower;
		int u = upper;
		E pivot = input[lower+(upper-lower)/2];
		while(l<=u){
			while(input[l].compareTo(pivot)<0){
				l++;
			}
			while(input[u].compareTo(pivot)>0){
				u--;
			}
			if(l<=u){
				E temp = input[l];
				input[l] = input[u];
				input[u] = temp;
				l++;
				u--;
			}
		}
		
		if(lower < u)
			input = arrayQuick(input, lower, u);
		if(l < upper)
			input = arrayQuick(input, l, upper);
		return input;
	}

	@Override
	public LinkedList<E> linkedQuick(LinkedList<E> input, int lower, int upper) {
		if(input==null||input.size()<=2){
			return input;
		}
		
		int l = lower;
		int u = upper;
		E pivot = input.get(lower+(upper-lower)/2);
		while(l<=u){
			while(input.get(l).compareTo(pivot)<0){
				l++;
			}
			while(input.get(u).compareTo(pivot)>0){
				u--;
			}
			if(l<=u){
				E temp = input.get(l);
				input.set(l, input.get(u));
				input.set(u, temp);
				l++;
				u--;
			}
		}
		
		if(lower < u)
			input = linkedQuick(input, lower, u);
		if(l < upper)
			input = linkedQuick(input, l, upper);
		return input;
	}

	@Override
	public E[] arrayInsertion(E[] input) {
		for(int i = 1; i < input.length; i++){
			E temp = input[i];
			int index = i;
			
			while(index>=1 && input[index-1].compareTo(temp)>0){
				input[index] = input[index-1];
				index--;
			}
			
			input[index] = temp;			
		}
		return input;
	}

	@Override
	public LinkedList<E> linkedInsertion(LinkedList<E> input) {
		for(int i = 1; i < input.size(); i++){
			E temp = input.get(i);
			int index = i;
			
			while(index>=1 && input.get(index-1).compareTo(temp)>0){
				input.set(index, input.get(index-1));
				index--;
			}
			
			input.set(index, temp);
		}
		
		return input;
	}

	@Override
	public E[] arrayJava(E[] input) {
		if(input==null||input.length<2){
			return input;
		}
		Arrays.sort(input);
		return input;
	}

	@Override
	public LinkedList<E> linkedJava(LinkedList<E> input) {
		if(input==null||input.size()<2){
			return input;
		}
		Collections.sort(input);
		return input;
	}

	@Override
	public E[] arraySelection(E[] input) {
		int index = 0;
		int min;
		E temp;
		for(int i = 0; i < input.length-1; i++){
			index = i;
			min = i;
			for(int j = i; j < input.length; j++){
				if(input[j].compareTo(input[min])<0){
					min = j;
				}
			}
			temp = input[index];
			input[index] = input[min];
			input[min] = temp;
		}
		return input;
	}

	@Override
	public LinkedList<E> linkedSelection(LinkedList<E> input) {
		int index = 0;
		int min;
		E temp;
		for(int i = 0; i < input.size()-1; i++){
			index = i;
			min = i;
			for(int j = i; j < input.size(); j++){
				if(input.get(j).compareTo(input.get(min))<0){
					min = j;
				}
			}
			temp = input.get(index);
			input.set(index,input.get(min));
			input.set(min, temp);
		}
		return input;
	}



	
}
