#include <stdio.h>
#include <tchar.h>
#include <stdlib.h>
#include <iostream>
#include <sstream>




#pragma once
// TODO: reference additional headers your program requires here
void testAlgos(int arr[]);
void mergesort(int arr[]);
void splitnmerge(int workArr[], int begin, int end, int result[]);
void merge(int workArr[], int begin, int middle, int end, int result[]);
void bubblesort(int foo[]);

void merge(int workArr[], int begin, int end, int result[]);
void initSkipList(struct SKIP_LIST* skiplist);
void insertItem(int randomNum, struct SKIP_LIST* skiplist);
void printSLElems(struct SKIP_LIST *skiplist);
void printWholeSL(struct SKIP_LIST *skiplist);
