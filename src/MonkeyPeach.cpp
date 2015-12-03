//============================================================================
// Name        : Monkey&Peach.cpp
// Author      : dun
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

int main() {
	//int ans = 0;//答案
	int x = 0; //猴子的数量
	int n[20] = { 0 }; //每次剩余的桃子数
	cin >> x; //猴子数
	int i = 0;

	while (i < x) {
		if (1 == n[i] % x) {
			n[i + 1] = n[i] - 1 - n[i] / x;
			i++;
		} else {
			i = 0;
			n[0]++;
		}
	}
	cout << n[0]; //输出桃子数的结果
	return 0;
}
