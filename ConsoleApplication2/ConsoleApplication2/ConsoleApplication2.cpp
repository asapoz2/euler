// ConsoleApplication2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <list>
#include <iterator> 
#include "Euler.h"
#include "algos.h"

using namespace std;

static const int len = 16;


int main()
{
	//const int len = 46;
	//int foo[len];
	//initList(foo, len);
	//testAlgos(foo);
	//prob35();
	//problem259();
	problem264();

	getchar();
    return 0;
}



void initList(int foo[], int len) {
	stringstream ss;
	ss << "[";
	for (int i = 0; i < len; i++) {
		int num = rand() % 1000;
		foo[i] = num;
		ss << num;
		if (i != len - 1) {
			ss << ",";
		}
	}
	ss << "]";
	cout << ss.str() << endl;
}

void printArr(int* foo, int len) {
	stringstream ss;
	ss << "[";
	for (int i = 0; i < len; i++) {
		ss << foo[i];
		if (i != len - 1) {
			ss << ",";
		}
	}
	ss << "]";
	cout << ss.str() << endl;
}