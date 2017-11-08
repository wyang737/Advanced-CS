public class ArrayQueue<E>{

	private E[] data;
	private int start = 0;
	private int end = 0;
	private int size = 0;

	public ArrayQueue(){
		data = (E[]) new Object[10];
	}	
	
	public void add(E e){
		if (data[end] == null){
			data[end] = e;
			end ++;
			size ++;
			if (end == data.length){
				end = 0;
			}
		} else {
			fixSize();
			add(e);
		}
	}
	
	private void fixSize(){
		E[] temp;
		temp = (E[]) new Object[data.length * 2];
		int counter = 0;
		for (int i = start; i < data.length; i ++){
			temp[counter] = data[i];
			counter ++;
		}
		for (int j = 0; j < start; j ++){
			temp[counter] = data[j];
			counter ++;
		}
		start = 0;
		end = data.length;
		data = temp;
	}

	public E remove(){
		if (start == data.length){
			start = 0;
		}
		E element = data[start];
		data[start] = null;
		start ++;
		size --;
		return element; 
	} 

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public void print(){
		System.out.println("------");
		System.out.println("start: " + start);
		for (int i = start; i < data.length; i ++){
			System.out.println(i + ":" + data[i]);
		}
		for (int i = 0; i < start; i ++){
			System.out.println(i + ":" + data[i]);
		}
		System.out.println("end: " + end);
		System.out.println("------");
	}

	public E peek(){
		return data[start];
	}
	
}
