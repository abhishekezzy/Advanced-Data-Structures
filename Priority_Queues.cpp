#include<bits/stdc++.h>
#include<limits.h>
using namespace std;
int maxSize = 100000;
int size = -1;
int h[100000];

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


void siftUp(int i)
{
	if (i == 0 || h[parent(i)] > h[i])
		return;
	swap(h[i], h[parent(i)]);
	i = parent(i);
	siftUp(i);
}

void siftDown(int i)
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
		siftDown(maxIndex);
	}
}


void insert(int p)
{
	if(size == maxSize)
		return;
	size = size + 1;
	h[size] = p;
	siftUp(size);
}

int extractMax()
{
	if(size == -1)
		cout << "Empty";
	int result = h[0];
	h[0] = h[size];
	size--;
	siftDown(0);
	return result;
}

void remove(int i)
{
	if(size == -1)
		cout << "Empty";
	h[i] = INT_MAX;
	siftUp(i);
	extractMax();
}

void changePriority(int i, int p)
{
	int oldp = h[i];
	h[i] = p;
	if(h[i] > oldp)
		siftUp(i);
	else
		siftDown(i);
}

int getMax()
{
	if(size == -1)
		cout << "Empty";
	return h[0];
} 

int main()
{
	int ch, ele, pos, priority;
	ch = 1;
	while(ch >= 1 && ch <= 5)
	{
		cin >> ch;
		switch(ch)
		{
			case 1:
				cin >> ele;
				insert(ele);
				break;
			case 2:
				cout << "getMax() -> " << getMax() << endl;
				break;
			case 3:
				cout << "extractMax() -> " << extractMax() << endl;
				break;
			case 4:
				cin >> pos;
				remove(pos);
				break;
				//cout << "removed -> " << remove(pos) << endl;
			case 5:
				cin >> pos >> priority;
				changePriority(pos, priority);	
				break;
		}	
	}
	for(int i = 0; i <= size; i++)
		cout <<	h[i] << " ";	
}

