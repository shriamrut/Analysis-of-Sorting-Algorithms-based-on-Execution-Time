/**Time taken to produce the output is 20 seconds,because of the computations**/

import java.util.Random;
/**libraries for saving data into files**/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**Main Class**/
public class SortingAnalysis {
	
/**Arrays t1,t2,t3,t4,t5,t6 for storing the time done by each sorting algorithm for each size**/	
public double t1[]=new double[3];
public double t2[]=new double[3];
public double t3[]=new double[3];
public double t4[]=new double[3];
public double t5[]=new double[3];
public double t6[]=new double[3];
/**Main array**/
public static int arr[]=new int[1000000];
/**Temporary array for sorting array element**/
public static int temp[]=new int[1000000];

public static void main(String[] arg)
{
	/**For generating random values,the random class is used**/
	Random rand=new Random();
	SortingAnalysis sa=new SortingAnalysis();
	for(int i=0;i<100000;i++)
		arr[i]=rand.nextInt()%100000;
	/*Each sorting algorithms are called for different sizes*/
	
	sa.insertionSort(100);
	sa.selectionSort(100);
	sa.bubbleSort(100);
	sa.insertionSort(10000);
	sa.selectionSort(10000);
	sa.bubbleSort(10000);
	sa.insertionSort(100000);
	sa.selectionSort(100000);
	sa.bubbleSort(100000);
	double start,end;
	
	//temporary array is created for each size,as mergesort is implemented using recursion
	//t4 array is used for storing time taken for mergesort
	for(int i=0;i<100;i++)
		temp[i]=arr[i];
	start=System.currentTimeMillis();//starting the clock
	sa.mergesort(0, 99);
	end=System.currentTimeMillis();//stoping the clock
	sa.t4[0]=end-start;
	
	for(int i=0;i<10000;i++)
		temp[i]=arr[i];
	start=System.currentTimeMillis();
	sa.mergesort(0,9999);
	end=System.currentTimeMillis();
	sa.t4[1]=end-start;
	
	for(int i=0;i<100000;i++)
		temp[i]=arr[i];
	start=System.currentTimeMillis();
	sa.mergesort(0, 99999);
	end=System.currentTimeMillis();
	sa.t4[2]=end-start;
	
	//temporary array is created for each size,as quicksort is implemented using recursion
	//t5 array is used for storing time taken for quicksort
	for(int i=0;i<100;i++)
		temp[i]=arr[i];
	start=System.currentTimeMillis();//starting the clock
	sa.quicksort(0, 99);
	end=System.currentTimeMillis();//stoping the clock
	sa.t5[0]=end-start;
	
	for(int i=0;i<10000;i++)
		temp[i]=arr[i];
	start=System.currentTimeMillis();
	sa.quicksort(0,9999);
	end=System.currentTimeMillis();
	sa.t5[1]=end-start;
	
	for(int i=0;i<100000;i++)
		temp[i]=arr[i];
	start=System.currentTimeMillis();
	sa.quicksort(0, 99999);
	end=System.currentTimeMillis();
	sa.t5[2]=end-start;
	
	//calling heap sort function for each size
	sa.heapsort(100);
	sa.heapsort(10000);
	sa.heapsort(100000);
	
	try {
		sa.printTime();
	} catch (IOException e) {
		/*IOException generated for when something goes wrong while accessing the file */
		// TODO Auto-generated catch block
		System.out.println("IOException");
		e.printStackTrace();
	}
	
}
/**Heap Sort Algorithm,Time Complexity - O(nlog(n))**/
public void heapsort(int l)
{
	for(int i=0;i<l;i++) //creating a temporary array of size l
		temp[i]=arr[i];
	double start=System.currentTimeMillis();
	for(int i=(l/2)-1;i>=0;i--)
	{
		heapify(l,i);        //building max-heap in linear time
	}
	for (int i=l-1; i>=0; i--) //Extract element one by one and place it in the end
    { 
        int t=temp[0]; //move current max to the i th position
        temp[0]=temp[i]; 
        temp[i]=t; 
        heapify(i, 0);  // max heapify is called on the heap
    } 
	double end=System.currentTimeMillis();
	//the array t6 is used to store the time taken for each length,in heapsort() 
	if(l==100)
		this.t6[0]=end-start;
	else if(l==10000)
		this.t6[1]=end-start;
	else if(l==100000)
		this.t6[2]=end-start;
}
/**Max Heapify function**/
public void heapify(int n,int i)
{
	int largest = i; // initialize largest as root 
    int l=2*i+1; // left = 2*i + 1 
    int r=2*i+2; // right = 2*i + 2 

    // if left child is larger than root 
    if (l<n && temp[l]>temp[largest]) 
        largest = l; 

    // if right child is larger than the largest(comparision with root and left) 
    if (r<n  && temp[r]>temp[largest]) 
        largest=r; 
    if (largest != i) 
    { 
        int t=temp[i]; 
        temp[i]=temp[largest]; 
        temp[largest]=t; 
        // recursively heapify the affected sub-tree 
        heapify(n,largest); 
    } 
}
/**Quick Sort algorithm,time complexity-O(nlog(n))**/
public void quicksort(int l,int r)
{
	if (l < r) //base condition
    { 
        int pi = partition(l,r); //partition of the temp array
        quicksort(l, pi-1); //recursive call towards the left half
        quicksort(pi+1, r); //recursive call towards the right half
    } 
}
/**Partioning the array **/
int partition(int l, int r) 
{ 
    int pivot = temp[r];  
    int i = (l-1); // index of smaller element 
    for (int j=l; j<r; j++) 
    { 
        // If current element is smaller than or equal to pivot
        if (temp[j] <= pivot) 
        { 
            i++; 

            // swaping temp[i],temp[j] 
            int t = temp[i]; 
            temp[i] = temp[j]; 
            temp[j] = t; 
        } 
    }
    // swap temp[i+1] and temp[r] (or pivot) 
    int t = temp[i+1]; 
    temp[i+1] = temp[r]; 
    temp[r] = t; 

    return i+1; 
}
/**Merge Sort algorithm,time complexity-O(nlog(n))**/
public void mergesort(int l,int r)
{
	if(l<r)//base condition
	{
		int m=(l+r)/2;
		mergesort(l,m);//recursive call on the left part of the array
		mergesort(m+1,r);//recursive call on the right part of the array
		merge(l,m,r);
	}
}
public void merge(int l,int m,int r)
{
	int s1=m-l+1;//Size of left array
	int s2=r-m;//Size of right array
	int left[]=new int[s1];//new left array
	int right[]=new int[s2];//new right array
	for(int i=0;i<s1;i++)
		left[i]=temp[l+i];//copying data to left array
	for(int i=0;i<s2;i++)
		right[i]=temp[m+i+1];//copying data to right array
	int i=0,j=0,k=l;
	//merging the two arrays
	while(i<s1&&j<s2)
	{
		if(left[i]<=right[j])
		{
			temp[k]=left[i];
			i++;
		}
		else
		{
			temp[k]=right[j];
			j++;
		}
		k++;
	}
	//checking for left overs on the left array,we have to check both as either one of them can be empty
	while(i<s1)
	{
		temp[k]=left[i];
		k++;
		i++;
	}
	while(j<s2)
	{
		temp[k]=right[j];
		j++;
		k++;
	}
}
/*Bubble Sort Algorithm time complexity O(n^2)*/
public void bubbleSort(int len)
{
	for(int i=0;i<len;i++)
		temp[i]=arr[i];
	/*Starting the clock*/
	double start=System.currentTimeMillis();
	for(int i=0;i<len;i++)
	{
		for(int j=0;j<(len-i)-1;j++)
		{
			if(temp[j+1]<temp[j])
			{
				int t=temp[j];
				temp[j]=temp[j+1];
				temp[j+1]=t;
			}
		}
	}
	/*Stoping the clock*/
	double end=System.currentTimeMillis();
	/*t1 is used to store time taken for each size in bubble sort*/
	/*difference is calculated between start and end,which gives us the duration*/
	if(len==100)
		t1[0]=end-start;
	else if(len==10000)
		t1[1]=end-start;
	else if(len==100000)
		t1[2]=end-start;
}
/*Selection Sort Algorithm,time complexity O(n^2)*/
public void selectionSort(int len)
{
	for(int i=0;i<len;i++)
		temp[i]=arr[i];
	double start=System.currentTimeMillis();
	for(int i=0;i<len;i++)
	{
		int mini=i;//default minimum value index
		for(int j=i+1;j<len;j++)//finding the minimum value on that subarray
		{
			if(temp[j]<temp[mini])
				mini=j;
		}	
		int t=temp[i];
		temp[i]=temp[mini];
		temp[mini]=t;//swapping the intial value with the minimum value of that subarray
	}
	/*t2 is used to store time taken for each size in selection sort*/
	double end=System.currentTimeMillis();
	if(len==100)
		t2[0]=end-start;
	else if(len==10000)
		t2[1]=end-start;
	else if(len==100000)
		t2[2]=end-start;
}
/*Insertion Sort Algorithm,time complexity O(n^2)*/
public void insertionSort(int len)
{
	for(int i=0;i<len;i++)
		temp[i]=arr[i];
	double start=System.currentTimeMillis();
	for(int i=1;i<len;i++)
	{
		int t=temp[i];
		int j=i-1;
		while(j>=0 && temp[j]>t)//finding the appropriate position to place the value on each iteration of i
		{
			temp[j+1]=temp[j];
			j=j-1;
		}
		temp[j+1]=t;//placing the value in its apt position
	}
	double end=System.currentTimeMillis();
	/*t3 is used to store time taken for each size in insertion sort*/
	if(len==100)
		t3[0]=end-start;
	else if(len==10000)
		t3[1]=end-start;
	else if(len==100000)
		t3[2]=end-start;
}
public void printTime() throws IOException //IOException is thrown if there is something wrong while accessing files
{
	System.out.println("Algorithm:     100     10000    100000");
	System.out.println("BubbleSort:    "+t1[0]+"ms   "+t1[1]+"ms   "+t1[2]+"ms" );
	System.out.println("SelectionSort: "+t2[0]+"ms   "+t2[1]+"ms   "+t2[2]+"ms");
	System.out.println("InsertionSort: "+t3[0]+"ms   "+t3[1]+"ms   "+t3[2]+"ms");
	System.out.println("MergeSort:     "+t4[0]+"ms   "+t4[1]+"ms   "+t4[2]+"ms");
	System.out.println("QuickSort:     "+t5[0]+"ms   "+t5[1]+"ms   "+t5[2]+"ms");
	System.out.println("HeapSort:      "+t6[0]+"ms   "+t6[1]+"ms   "+t6[2]+"ms");
	File f=new File("output.txt");
	if(!f.exists())//if a file is not excisting create a new one
			f.createNewFile();	
	/*FileWrite class object is used to write strings to files*/
	FileWriter writer=new FileWriter(f);
	writer.write("Algorithm:     100     10000    100000\n");
	writer.write("BubbleSort:    "+t1[0]+"ms   "+t1[1]+"ms   "+t1[2]+"ms\n");
	writer.write("SelectionSort: "+t2[0]+"ms   "+t2[1]+"ms   "+t2[2]+"ms\n");
	writer.write("InsertionSort: "+t3[0]+"ms   "+t3[1]+"ms   "+t3[2]+"ms\n");
	writer.write("MergeSort:     "+t4[0]+"ms   "+t4[1]+"ms   "+t4[2]+"ms\n");
	writer.write("QuickSort:     "+t5[0]+"ms   "+t5[1]+"ms   "+t5[2]+"ms\n");
	writer.write("HeapSort:      "+t6[0]+"ms   "+t6[1]+"ms   "+t6[2]+"ms");
	writer.close();
	System.out.println("finished");
}
}
