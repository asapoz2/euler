#pragma once
#include <vector>
#include <stdio.h>
#include "euler.h"
#include <vector>
#include <chrono>
#include <thread>
#include <set>
#include <algorithm>
#include <iostream>
#include <map>

struct frac {
	int top;
	int bottom;
};
void prob35();
void problem259();
void pushbackarrays(std::vector<struct frac> current, std::vector<std::vector<struct frac>>* v,
	std::set<int>* reachable, int* sum);
void pushbackconcatarrays(std::vector<struct frac> current, std::vector<std::vector<struct frac>>* pile,
	std::vector<std::vector<struct frac>>* v,
	std::set<int>* reachable, int* sum);
std::string stringadd(std::string a, std::string b);
int NumDigits(int x);
int bigten(int x);
void problem264();
int findsquare(int input, bool* flag);
void findsquareto5(int input, int* tot, int* five);
void addRootToTotal(int firstsquare, int secondsquare, int thirdsquare, bool addtwice);