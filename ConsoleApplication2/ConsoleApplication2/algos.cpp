#include "stdafx.h" 
#include "algos.h"

using namespace std;
static const int len = 16;
struct UP_DOWN {   // Declare PERSON struct type  
	struct UP_DOWN* left;
	struct UP_DOWN* right;
	struct UP_DOWN* up;
	struct UP_DOWN* down;
	int val;
} my_up_down;

struct UP_DOWN_LIST {
	struct UP_DOWN* first;
	struct UP_DOWN_LIST* up;
	struct UP_DOWN_LIST* down;
	struct UP_DOWN end;
}my_up_down_list;

struct SKIP_LIST {   // Declare PERSON struct type  
	struct UP_DOWN_LIST* top;
	struct UP_DOWN_LIST* bottom;
	int levels;
} family_member;


void testAlgos(int foo[]) {
	//mergesort(foo);
	//printArr(foo,len);

	//struct with list of double linked lists

	struct SKIP_LIST skiplist;
	initSkipList(&skiplist);
	cout << " after init first val is " << skiplist.top->first->val << endl;
	int randomNum = 6;
	insertItem(randomNum, &skiplist);
	cout << "from main" << endl;
	printWholeSL(&skiplist);
	insertItem(11, &skiplist);
	cout << "from main" << endl;
	printWholeSL(&skiplist);
	insertItem(7, &skiplist);
	cout << "from main" << endl;
	printWholeSL(&skiplist);
	insertItem(12, &skiplist);
	cout << "from main" << endl;
	printWholeSL(&skiplist);
	insertItem(5, &skiplist);
	printWholeSL(&skiplist);
	insertItem(15, &skiplist);
	printWholeSL(&skiplist);
	insertItem(16, &skiplist);
	printWholeSL(&skiplist);
	insertItem(26, &skiplist);
	printWholeSL(&skiplist);
	insertItem(25, &skiplist);
	printWholeSL(&skiplist);
	for (int i = 0; i < len; i++) {
		insertItem(foo[i], &skiplist);
	}
	printWholeSL(&skiplist);
}

void insertItem(int elem, struct SKIP_LIST* skiplist) {

	bool first = true;
	struct UP_DOWN_LIST* currListPtr = skiplist->top;
	int levelsSearched = 0;
	struct UP_DOWN* currElemPtr = currListPtr->first;
	while (levelsSearched < (skiplist->levels)) {

		while (currElemPtr->val<elem && currElemPtr != &(currListPtr->end)) {
			currElemPtr = currElemPtr->right;
		}
		if (currElemPtr->val == elem) {
			return; //already in there
		}
		currElemPtr = currElemPtr->left;
		if (++levelsSearched < (skiplist->levels)) {
			currElemPtr = currElemPtr->down;
		}
	}
	cout << "levels searched " << levelsSearched << " currelemptr "
		<< currElemPtr->val << " on insert of " << elem << endl;
	//at the bottom now, find the final place
	while (currElemPtr->val<elem && currElemPtr != &(currListPtr->end)) {
		currElemPtr = currElemPtr->right;
	}
	// either at last or at val greater than elem, time to insert
	struct UP_DOWN* newestBottom = new struct UP_DOWN;
	newestBottom->left = currElemPtr->left;
	newestBottom->right = currElemPtr;
	newestBottom->val = elem;
	newestBottom->down = nullptr;
	newestBottom->up = nullptr;
	currElemPtr->left->right = newestBottom;
	currElemPtr->left = newestBottom;

	//adding levels
	struct UP_DOWN* currAdded = newestBottom;
	int currentLevel = 2;
	currListPtr = skiplist->bottom;
	int num = rand() % 2;
	while (num == 1) {
		//insert item
		if (currentLevel > skiplist->levels) {
			//create new level
			skiplist->top = new struct UP_DOWN_LIST;
			//add neginf to start
			struct UP_DOWN* neginf = new struct UP_DOWN;
			neginf->val = -2147483647;
			//insert new value
			struct UP_DOWN* nextval = new struct UP_DOWN;
			nextval->val = elem;
			//set neginfs props
			neginf->right = nextval;
			neginf->up = nullptr;
			neginf->left = nullptr;
			//neginfs point to eachother
			neginf->down = currListPtr->first;
			currListPtr->first->up = neginf;
			//update end of top list
			skiplist->top->end.left = nextval;
			skiplist->top->end.up = nullptr;
			skiplist->top->end.down = &(currListPtr->end);
			skiplist->top->end.right = nullptr;
			skiplist->top->end.val = 2147483647;
			//update list's pointers
			currListPtr->up = skiplist->top;
			skiplist->top->down = currListPtr;
			//rest of newval
			nextval->right = &(skiplist->top->end);
			nextval->left = neginf;
			nextval->up = nullptr;
			nextval->down = currAdded;
			currAdded->up = nextval;
			//update skiplist
			skiplist->top->down = currListPtr;
			skiplist->top->first = neginf;

			//iterate
			currAdded = nextval;
			skiplist->levels = skiplist->levels + 1;
			currListPtr = skiplist->top;
			currentLevel++;
		}
		else {
			// already a level above me
			struct UP_DOWN* structwithup = currAdded;
			//go left till you find an upp, might be neginf
			while (structwithup->up == nullptr) {
				structwithup = structwithup->left;
			}
			structwithup = structwithup->up;
			//go right 
			while (structwithup->val <elem) {
				structwithup = structwithup->right;
			}
			//insert new value
			struct UP_DOWN* nextval = new struct UP_DOWN;
			nextval->val = elem;
			nextval->right = structwithup;
			nextval->left = structwithup->left;
			nextval->up = nullptr;
			nextval->down = currAdded;
			//right node properties
			structwithup->left->right = nextval;
			structwithup->left = nextval;

			//iterate
			currAdded = nextval;
			currListPtr = currListPtr->up;
			currentLevel++;
		}

		//flip again
		num = rand() % 2;
		if (num == 1) {
			cout << " inserting " << elem << "at a higher level: " << currentLevel << endl;
		}
		cout << " num is " << num << endl;
		//num = 0;
	}

	newestBottom->up = nullptr;
}

void workinginsertItem(int elem, struct SKIP_LIST* skiplist) {

	bool first = true;
	struct UP_DOWN_LIST* currListPtr = skiplist->top;
	int level = 0;
	while (level < (skiplist->levels)) {

	}
	struct UP_DOWN* currElemPtr = currListPtr->first;
	while (currElemPtr->val<elem && currElemPtr != &(currListPtr->end)) {
		currElemPtr = currElemPtr->right;
	}
	// either at last or at val greater than elem
	struct UP_DOWN* latest = new struct UP_DOWN;
	latest->left = currElemPtr->left;
	latest->right = currElemPtr;
	latest->val = elem;
	currElemPtr->left->right = latest;
	currElemPtr->left = latest;
}

void printSLElems(struct SKIP_LIST *skiplist) {
	struct UP_DOWN_LIST* currListPtr = skiplist->bottom;
	struct UP_DOWN* currElemPtr = currListPtr->first;
	while (currElemPtr != &currListPtr->end) {
		cout << currElemPtr->val << ",";
		currElemPtr = currElemPtr->right;
	}
	cout << endl;
}
void printWholeSL(struct SKIP_LIST *skiplist) {
	struct UP_DOWN_LIST* currListPtr = skiplist->top;
	do {
		struct UP_DOWN* currElemPtr = currListPtr->first;
		while (currElemPtr != &currListPtr->end) {
			cout << currElemPtr->val << ",";
			currElemPtr = currElemPtr->right;
		}
		cout << endl;
		currListPtr = currListPtr->down;
	} while (currListPtr != nullptr);
	cout << endl;
}

void initSkipList(struct SKIP_LIST *skiplist) {
	skiplist->top = new struct UP_DOWN_LIST;
	skiplist->bottom = skiplist->top;
	struct UP_DOWN* neginf = new struct UP_DOWN;
	neginf->right = &(skiplist->top->end);
	neginf->up = nullptr;
	neginf->down = nullptr;
	neginf->left = nullptr;
	skiplist->top->end.left = neginf;
	skiplist->top->end.up = nullptr;
	skiplist->top->end.down = nullptr;
	skiplist->top->end.right = nullptr;
	skiplist->top->end.val = 2147483647;
	skiplist->top->down = nullptr;
	skiplist->top->up = nullptr;
	neginf->val = -2147483647;

	skiplist->top->first = neginf;
	skiplist->levels = 1;

	cout << " in init first val is " << skiplist->top->first->val << endl;
}

void mergesort(int arr[]) {
	int workArr[len];
	memcpy(workArr, arr, len*sizeof(int));
	splitnmerge(workArr, 0, len, arr);
	memcpy(arr, workArr, len*sizeof(int));
}

void splitnmerge(int workArr[], int begin, int end, int result[]) {
	if (end - begin < 2)
		return;
	int middle = (end + begin) / 2;
	splitnmerge(result, begin, middle, workArr);
	splitnmerge(result, middle, end, workArr);
	merge(workArr, begin, middle, end, result);
}

void merge(int workArr[], int begin, int middle, int end, int result[]) {
	int i = begin, j = middle;
	for (int k = begin; k < end; k++) {
		if (i < middle && (j >= end || result[i] <= result[j])) {
			workArr[k] = result[i];
			i++;
		}
		else {
			workArr[k] = result[j];
			j++;
		}
	}
}

void insertionsort(int foo[]) {
	for (int i = 1; i < len; i++) {
		int x = foo[i];
		int j = i - 1;
		while (j >= 0 && foo[j] > x) {
			foo[j + 1] = foo[j];
			j = j - 1;
		}
		foo[j + 1] = x;
	}
}



void bubblesort(int foo[]) {
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
	cout << ss.str() << endl << " doing sort " << endl;
	ss.clear();
	ss.str("");

	bool swapped = false;
	do {
		swapped = false;
		for (int i = 0; i < len; i++) {

			if (foo[i - 1] > foo[i]) {
				// swap and remember that you swapped
				int temp = foo[i - 1];
				foo[i - 1] = foo[i];
				foo[i] = temp;
				swapped = true;
			}
		}
	} while (swapped);

	//printArr(foo, len);
}