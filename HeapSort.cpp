#include<bits/stdc++.h>
#include<limits.h>
using namespace std;

void swap(int *a, int *b)
{
	int *temp;
	temp = a;
	a = b;
	b = temp;
}


int parent(int i)
{
	return i/2;
}

int leftChild(int i)
{
	return 2 * i;
}

int rightChild(int i)
{
	return 2 * i + 1;
}

void siftDown(int i, int h[], int size)
{
	int maxIndex = i;
	int l = leftChild(i);
	int r = rightChild(i);
	if(l <= size && h[l] > h[maxIndex])
		maxIndex = l;
	if(r <= size && h[r] > h[maxIndex])
		maxIndex = r;
	if(i != maxIndex)
	{
		swap(h[i], h[maxIndex]);
		siftDown(maxIndex, h, size);
	}
}


int main()
{
	int n;
	cin >> n;
	int a[n];
	int size = n - 1;
	
	for(int i = 0; i < n; i++)
		cin >> a[i];
	
	//Build the heap
	for(int i = n/2; i >= 0; i--)
		siftDown(i, a, size);
	
	//In-place Heap-Sort
	for(int i = 0; i < n - 1; i++)
	{
		swap(a[0], a[size]);
		size--;
		siftDown(0, a, size);	
	}
	
	for(int i = 0; i < n; i++)
		cout << a[i]<< " " ;
}

