#include "stdafx.h" 
#include "Euler.h"
#include <vector>
#include <chrono>
#include <thread>
#include <time.h>
#include <math.h> 

using namespace std;

void prob35() {
	std::vector<int> result;
	std::vector<int> primes;
	std::set<int> primeSet;
	primes.push_back(2);
	result.push_back(2);
	primeSet.insert(2);
	for (int i = 3; i < 1000000; i++)
	{
		bool prime = true;
		for (int j = 0; j<primes.size() && primes[j] * primes[j] <= i; j++)
		{
			if (i % primes[j] == 0)
			{
				prime = false;
				break;
			}
		}
		if (prime)
		{
			primes.push_back(i);
			primeSet.insert(i);
			//cout << i << " ";
			int topdig = 0;
			int lastdig = i;
			for (int dig = i; dig > 0; dig /= 10) {
				lastdig = dig % 10;
				if (lastdig > topdig || lastdig % 2 == 0) {
					topdig = lastdig;
				}
			}
			if (lastdig >= topdig) {
				bool nofails = true;
				//start checking rotations
				const int leni = ceil(log10(i + 1));
				int len = leni;
				int* arr = new int[leni];
				arr[0] = i;
				int powten = 10;
				for (int e = 1; e < len - 1; e++) {
					powten *= 10;
				}
				int full = i;
				for (int dig = i, ctr = 0; dig > 9; dig /= 10, ctr++) {
					len--;
					int currdig = full % 10;

					full = (powten * currdig) + (full / 10);
					cout << "testing " << full << " rotation #" << (leni - len) << " of " << i << ",";
					if (primeSet.find(full) != primeSet.end()) {
						arr[len] = full;
					}
					else {
						nofails = false;
						break;
					}
				}

				if (nofails) {
					cout << "found one !" << i << ",";
					for (int pos = 0; pos < leni; pos++) {
						if (pos > 0 && arr[pos - 1] == arr[pos]) {
							continue;
						}
						result.push_back(arr[pos]);
					}
				}
				delete[] arr;
			}
		}

	}
	std::cout << "result:" << ",";
	for (std::vector<int>::const_iterator i = result.begin(); i != result.end(); ++i) {
		std::cout << *i << ' ';
	}
	std::cout << "result size:" << result.size() << ",";


}



void problem259() {
	//for each array of new numbers
	//predict the next set/array by operating one of the operations
	//stop when size is one
	clock_t t1, t2;
	t1 = clock();
	vector<vector<struct frac>> v;
	vector<vector<struct frac>> pile;
	vector<struct frac> start;
	std::set<int> reachable;
	start.push_back({ 1,1 });
	start.push_back({ 2,1 });
	start.push_back({ 3,1 });
	start.push_back({ 4,1 });
	start.push_back({ 5,1 });
	start.push_back({ 6,1 });
	start.push_back({ 7,1 });
	start.push_back({ 8,1 });
	start.push_back({ 9,1 });
	int sum = 0;
	vector<struct frac> current = start;
	pile.push_back(start);
	while (pile.size()>0) {
		//cout << "vsize()" << pile.size() << ",";
		current = pile.back();
		pile.pop_back();
		v.push_back(current);
		pushbackconcatarrays(current, &pile, &v, &reachable, &sum);

		//std::this_thread::sleep_for(std::chrono::milliseconds(500));
	}
	//getchar();
	while (v.size()>0) {
		/*if (v.size() % 512 == 0) {
			cout << "vsize()" << v.size() << ",";
		}*/
		current = v.back();
		v.pop_back();
		pushbackarrays(current, &v, &reachable, &sum);

		//std::this_thread::sleep_for(std::chrono::milliseconds(500));
	}
	t2 = clock();
	float diff((float)t2 - (float)t1);
	cout << "took " << (diff / CLOCKS_PER_SEC) << " seconds." << ",";
	//v.push_back(start);
	//cout << "sum of reachable numbers = " << sum << ",";
	vector<string> sumstr;
	int currentsum = 0;
	for (std::set<int>::iterator iter = reachable.begin(); iter != reachable.end(); iter++) {
		int thissum = (*iter) + currentsum;
		if (thissum > 0) {
			currentsum = thissum;
		}
		else {
			sumstr.push_back(std::to_string(currentsum));
			currentsum = *iter;
		}
	}
	sumstr.push_back(std::to_string(currentsum));
	//cout << "pushing back last sum " << currentsum << ",";
	string completesum = "0";
	for (vector<string>::iterator iter = sumstr.begin(); iter != sumstr.end(); iter++) {
		completesum = stringadd(completesum, *iter);
	}
	cout << "sum of reachable numbers = " << completesum << ",";
	diff=((float)t2 - (float)t1);
	cout << "took " << (diff / CLOCKS_PER_SEC) << " seconds." << ",";



}

string stringadd(string str1, string str2) {
	//cout << "calling with " << str1 << " and " << str2 << ",";
	size_t n = max(str1.size(), str2.size());
    if (n > str1.size())
        str1 = string(n-str1.size(), '0') + str1;
    if (n > str2.size())
        str2 = string(n-str2.size(), '0') + str2;
	string finalstr(n + 1, '0');
	// The carry
	char carry = 0;

	// Iterators
	string::const_reverse_iterator s1 = str1.rbegin(), e = str1.rend(),
		s2 = str2.rbegin();
	string::reverse_iterator f = finalstr.rbegin();

	// Conversion
	for (; s1 != e; ++s1, ++s2, ++f)
	{
		// Bracketing to avoid overflow
		char tmp = (*s1 - '0') + (*s2 - '0') + carry;
		if (tmp > 9)
		{
			carry = 1;
			tmp -= 10;
		}
		else
		{
			carry = 0;
		}
		*f = tmp + '0';
	}
	finalstr[0] = carry + '0';

	// Remove leading zeros from result
	n = finalstr.find_first_not_of("0");
	if (n != string::npos)
	{
		finalstr = finalstr.substr(n);
	}
	//cout << "finalstr " << finalstr << ",";
	return finalstr;
}

void printvecs(vector<vector<struct frac>>* v) {
	for (vector<vector<struct frac>>::iterator iter = v->begin(); iter != v->end(); iter++) {
		for (std::vector<struct frac>::iterator it = (*iter).begin(); it != (*iter).end(); ++it) {
			cout << "{" << (*it).top << "," << (*it).bottom << "}" << ",";
		}
	}
}

void pushbackarrays(vector<struct frac> current, vector<vector<struct frac>>* v,
	std::set<int>* reachable, int* sum) {
	int mode = 0;
	for (int mode = 0; mode < 4; mode++) {
		for (int i = 0; i < current.size() - 1; i++) {
			//cout << "i:" << i << ",";
			vector<struct frac> next;
			next.reserve(current.size() - 1);
			int ins = 0;
			while (ins < i) {
				next.push_back((current)[ins]);
				ins++;
			}
			if (mode == 0) {
				struct frac nextfrac {
					((current)[ins].bottom * (current)[ins + 1].top) +
						((current)[ins + 1].bottom * (current)[ins].top),
						(current)[ins].bottom * (current)[ins + 1].bottom
				};
				next.push_back(nextfrac);
			}
			else if (mode == 1) {
				struct frac nextfrac {
					((current)[ins + 1].bottom * (current)[ins].top) -
						((current)[ins].bottom * (current)[ins + 1].top),
						(current)[ins].bottom * (current)[ins + 1].bottom
				};
				next.push_back(nextfrac);
			}
			else if (mode == 2) {
				struct frac nextfrac {
					((current)[ins].top * (current)[ins + 1].top),
						(current)[ins].bottom * (current)[ins + 1].bottom
				};
				next.push_back(nextfrac);
			}
			else if (mode == 3) {
				struct frac nextfrac {
					((current)[ins].top * (current)[ins + 1].bottom),
						(current)[ins].bottom * (current)[ins + 1].top
				};
				next.push_back(nextfrac);
			}
			ins += 2;

			while (ins < current.size()) {
				next.push_back((current)[ins]);
				ins++;
			}
			//cout << "next.size: "<<next.size() << ",";

			if (next.size() <= 1) {
				if (next.front().bottom != 0 &&
					((next.front().top % next.front().bottom) == 0)) {
					int num = (next.front()).top / (next.front()).bottom;
					if (reachable->find(num) == reachable->end() && num>0) {
						(*sum) = (*sum) + num;
						reachable->insert(num);
						//cout << "found one: " << num << ",";
					}
				}
			}
			else if (next.size() == 2) {
				struct frac done = {
					((next)[0].bottom * (next)[1].top) +
					((next)[1].bottom * (next)[0].top),
					(next)[0].bottom * (next)[1].bottom
				};
				if (done.bottom != 0 &&
					((done.top % done.bottom) == 0)) {
					int num = (done).top / (done).bottom;
					if (reachable->find(num) == reachable->end() && num>0) {
						(*sum) = (*sum) + num;
						reachable->insert(num);
						//cout << "found one: " << num << ",";
					}
				}
				done = {
					(next[1].bottom * next[0].top) -
					(next[0].bottom * next[1].top),
					next[0].bottom * next[1].bottom
				};
				if (done.bottom != 0 &&
					((done.top % done.bottom) == 0)) {
					int num = (done).top / (done).bottom;
					if (reachable->find(num) == reachable->end() && num>0) {
						(*sum) = (*sum) + num;
						reachable->insert(num);
						//cout << "found one: " << num << ",";
					}
				}
				done = {
					(next[0].top * next[1].top) ,
					next[0].bottom * next[1].bottom
				};
				if (done.bottom != 0 &&
					((done.top % done.bottom) == 0)) {
					int num = (done).top / (done).bottom;
					if (reachable->find(num) == reachable->end() && num>0) {
						(*sum) = (*sum) + num;
						reachable->insert(num);
						//cout << "found one: " << num << ",";
					}
				}
				done = {
					(next[0].top * next[1].bottom),
					next[0].bottom * next[1].top
				};
				if (done.bottom != 0 &&
					((done.top % done.bottom) == 0)) {
					int num = (done).top / (done).bottom;
					if (reachable->find(num) == reachable->end() && num>0) {
						(*sum) = (*sum) + num;
						reachable->insert(num);
						//cout << "found one: " << num << ",";
					}
				}
			}
			else {
				v->push_back(next);
			}
		}
	}

}
int NumDigits(int x)
{
	x = abs(x);
	return (x < 10 ? 1 :
		(x < 100 ? 2 :
			(x < 1000 ? 3 :
				(x < 10000 ? 4 :
					(x < 100000 ? 5 :
						(x < 1000000 ? 6 :
							(x < 10000000 ? 7 :
								(x < 100000000 ? 8 :
									(x < 1000000000 ? 9 :
										10)))))))));
}

int bigten(int x)
{
	x = abs(x);
	return (x < 10 ? 10 :
		(x < 100 ? 100 :
			(x < 1000 ? 1000 :
				(x < 10000 ? 10000 :
					(x < 100000 ? 100000 :
						(x < 1000000 ? 1000000 :
							(x < 10000000 ? 10000000 :
								(x < 100000000 ? 100000000 :
									(x < 1000000000 ? 1000000000 :
										10000000000)))))))));
}


void pushbackconcatarrays(vector<struct frac> current, vector<vector<struct frac>>* pile,
	vector<vector<struct frac>>* v,
	std::set<int>* reachable, int* sum) {
	for (int i = 0; i < current.size() - 1; i++) {
		//cout << "i:" << i << ",";
		vector<struct frac> next;
		int ins = 0;
		while (ins < i) {
			next.push_back((current)[ins]);
			ins++;
		}
		
		int second = (current)[ins + 1].top;
		int first = (current)[ins].top;
		int leading = bigten(second)*first;
		//multiply by length of second
		struct frac nextfrac {
			(leading + (current)[ins + 1].top), 1
		};
		next.push_back(nextfrac);

		ins += 2;

		while (ins < current.size()) {
			next.push_back((current)[ins]);
			ins++;
		}
		//cout << "next.size: " << next.size() << ",";

		if (next.size() <= 1) {
			if (next.front().top > 0 && next.front().bottom != 0 &&
				((next.front().top % next.front().bottom) == 0)) {
				int num = (next.front()).top / (next.front()).bottom;
				if (reachable->find(num) == reachable->end()) {
					(*sum) = (*sum) + num;
					reachable->insert(num);
					//cout << "found one: " << num << ",";
				}
			}
		}
		else {
			pile->push_back(next);
		}
	}


}

void problem264_take1() {
	const int maxperim = 50;

	for (int i = 0; i < maxperim; i++) {
		for (int j = 0; j < maxperim; j++) {
			int distsquared = (i*i) + (j*j);
			//line of alititude through point and 
			struct frac slope {(i-5), j};
			//b=y-mx
			//b=j-(i-5)/j*i
			//b= jj-i*i+5*i,j
			struct frac bintercept {(j*j)-(i*i)+5*i, j};
			//distsqare= x^2 +y^2
			//0=-x^2 + (mx+b)^2 +distsqare
			//0=(m*m-1)x^2+2bmx+b^2+distsquare
			//a=(m*m-1),1
			//a=m2-j2,j2
			//b=2*slope*b,j2
			//c=b*b+distquare
			int a { (i - 5)*(i - 5) - j*j };
			int bcoef {(2*i - 10) * bintercept.top};
			int c {(bintercept.top*bintercept.top)+(distsquared*j*j)};
			//-b+-sqrt(b2-4ac)/2a
			//-b-sqrt(b2-4ac)
			if (a == 0) continue;
			int squaredpart = (bcoef*bcoef) - (4 * a*c);
			double squareroot = sqrt(squaredpart);
			double absolute = abs(squareroot);
			bool isint{ absolute == floor(absolute) };
			if (!isint) {
				continue;
			}
			int firstx = (-bcoef) + squareroot;
			if (firstx % (2 * a) == 0) {
				firstx = firstx / (2 * a);
			}
			else {
				continue;
			}

			int secondx = (-bcoef) - squareroot;
			if (secondx % (2 * a) == 0) {
				secondx = secondx / (2 * a);
			}
			else {
				continue;
			}

			if (j == 0) {
				continue;
			}

			int firsty =(slope.top * firstx + bintercept.top);
			if (firsty % j == 0) {
				firsty = firsty / j;
			}
			else {
				continue;
			}

			int secondy = (slope.top * secondx + bintercept.top);
			if (secondy % j == 0) {
				secondy = secondy / j;
			}
			else {
				continue;
			}
			cout << "point 1" << i << "," << j << ", 2: " << 
				firstx << "," << firsty << ", 3:" << secondx << "," << secondy << endl;
		}
	}

}

std::map<int, int> roots;

int maxfoundroot = 0;
int findsquare(int input, bool* flag) {
	/*std::map<int, int>::iterator found = roots.find(input);
	if (found != roots.end()) {
		*flag = true;
		return (*found).second;
	}*/
	int upper = input / 2;
	int lower = 1;
	int guess;
	while ((upper - lower) > 1)
	{
		guess = (lower + upper) / 2;
		int guesssquared = guess*guess;
		if (guesssquared > input) {
			upper = guess;
		}
		else {
			lower = guess;
		}
		//roots.insert(std::make_pair(guesssquared, guess));
	}
	*flag = (lower*lower) == input ;
	return lower;
	//use binary search to find spot and fill set with guesses
	//return spot
}
//const int maxperim = 50;
const int maxperim = 1000;
int last5 = 0;
int total = 0;
void problem264() {
	const int maxradius = maxperim/2;
	const int maxradiussq = maxradius*maxradius;
	int firstsquare = ((-4 - 5)*(-4 - 5)) + ((3)*(3 ));
	int secondsquare = ((-4 - 4)*(-4 - 4)) + ((6)*(6));
	int thirdsquare = (1) + (9);
	addRootToTotal(firstsquare, secondsquare, thirdsquare, true);
	firstsquare = ((-3 - 5)*(-3 - 5)) + (16);
	secondsquare = ((-3 - 3)*(-3 - 3)) + (64);
	thirdsquare = (4) + (16);
	addRootToTotal(firstsquare, secondsquare, thirdsquare, true);
	firstsquare = 50;
	secondsquare = 100;
	thirdsquare = 50;
	addRootToTotal(firstsquare, secondsquare, thirdsquare, false);

	roots.insert(std::make_pair(0, 0));

	//can split searching along line y=x
	for (int i = 0; i < maxradius; i++) {
		cout << "i:" << i << endl;
		for (int j = 0; j < maxradius; j++) {
			int distsquared = (i*i) + (j*j);
			if (distsquared > maxradiussq) {
				break;
			}
			/*if (i == 5) {
				cout << endl;
			}*/
			//for each x starting at -r to 0 (at least one triangle
			//of the pair will be in the top left hemisphere)
			//, check if r2-x2 is an integer square
			//find equation of that altitude
			//the lines of the 2 points are perpendicular to 
			//intersection of perpendicular lines to altitude starting at 2 points
			//	is 3rd point
			//if integer we have a solution


			//line perpendicular to altitude, starting at second point
			struct frac m1 { (5 - i), j };

			bool wassquare = false;
			int dist = findsquare(distsquared, &wassquare);
			for (int p = -dist; p < 0; p++) {
				wassquare = false;
				int ysquared = distsquared - p*p;
				int q = findsquare(ysquared, &wassquare);
				if (!wassquare) continue;
				//cout << "found point: " << i << "," << j << ", and " << p << ", " << q << endl;
				//line perpendicular to this altitude, 
				struct frac m2 { (5 - p), q };
				//if q is zero, second line has to go through origin from second poijnt
				if (j == 0) {
					m1 = { -q, p };
				}
				// if 5-p=zero, do something else
				//b=y-mx
				//b=q-m1.top*p/m1.bottom
				struct frac b1 { q*j - m1.top * p, j };
				struct frac b2 { (q*j) - m2.top* i, q };
				if (j == 0) {
					//from point 2 to origin
					b1 = { 0, j };
				}
				//y=mx+b
				//y=m2x+b2
				//(m-m2)x+(b-b2)
				//(b2-b)/(m-m2)
				int x3 = b2.top *j - b1.top *q;
				int bottom = (m1.top*q - m2.top *j);
				int y3;
				if (x3 == 0) {
					y3 = 0;
				}
				else {
					if ((bottom == 0 || x3%bottom != 0) && x3 != 0) {
						continue;
					}
					x3 /= bottom;
				}
				//y=mx+b
				y3 = (m1.top*x3) + b1.top;
				if (j == 0 || y3%j != 0) {
					continue;
				}
				y3 /= j;

				if (y3*y3 + x3*x3 != distsquared || (x3 == i && y3 == j)) continue;
				cout << "found point: " << i << "," << j << ", and " <<
					p << ", " << q << ", and " << x3 << ", " << y3 << endl;
				//figure out perimeter
				//figure out the flipped versions
				//figure out what to do with first points

				//first point 
				int firstsquare = ((i - p)*(i - p)) + ((j - q)*(j - q));
				int secondsquare = ((i - x3)*(i - x3)) + ((j - y3)*(j - y3));
				int thirdsquare = ((p - x3)*(p - x3)) + ((q - y3)*(q - y3));
				bool addtwice = j != 0 || x3 != p;
				addRootToTotal(firstsquare, secondsquare, thirdsquare, addtwice);
			}

		}
	}
	cout << "totals: " << total << "." << last5 << endl;
}



const int tenfactor = 1000000;
void addRootToTotal(int firstsquare, int secondsquare, int thirdsquare, bool addtwice) {
	int ctotal = 0;
	int c5 = 0;
	int root = 0;
	int five = 0;
	findsquareto5(firstsquare, &root, &five);
	ctotal += root;
	c5 += five;
	root = 0;
	five = 0;
	findsquareto5(secondsquare, &root, &five);
	ctotal += root;
	c5 += five;
	findsquareto5(thirdsquare, &root, &five);
	ctotal += root;
	c5 += five;



	if (ctotal < maxperim || (ctotal == maxperim && c5 == 0)) {
		cout << "was good point" << endl;
		if (addtwice) {
			ctotal *= 2;
			c5 *= 2;
		}
		last5 += c5;
		if (last5 > tenfactor) {
			int extraint = last5 / tenfactor;
			last5 -= extraint*tenfactor;
			total += extraint;
		}
		total += ctotal;
	}
}

void findsquareto5(int input, int* tot, int* five) {
	// use earlier fun to find int below
	// square it and use long division to find decimal square that's left in int form
	// use same earlier fun to find last five?
	bool flag = false;
	int solid = findsquare(input, &flag);
	*tot = solid;
	if (flag) {
		*five = 0;
		return;
	}
	//solid is integer part 
	//rest can use double
	double rest = (double)input / (solid*solid);
	double rootrest = (sqrt(rest)*solid) - solid;
	rootrest *= tenfactor;
	*five = rootrest;
}