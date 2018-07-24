#include <iostream>
#include <climits>
#include <Bits.h>
#include <vector>
#include <string>
#include <cassert> //for assert
#include <memory> //for auto_ptr



using namespace std;

//question 1

// the simpler function
template<class func>
int useDarkPower(func darkPower)
{
	//remember that power is one of darklord's members!
	return darkPower(power);
}

//functors
class Blazer
{
private:
	int power;
public:
	Blazer(int power){this->power=power;}
	int operator()(){cout<<"only when the flames of hell will sear this wretched earth, shall i be at peace"<<endl; return 3;}
};//and so on for the others...

//virtual function override
/*class Horrifying Darklord: public Darklord
{
	virtual int useDarkPower(){cout<<"never again shall your mind be safe from the horrors of the abyss"<<endl; return (2*power)+15;};
}*/

void useDarkPower()
{
	/*call with function pointer
	*int (Darklord::*darkPower)(int power)=Darklord::blaze;
	* useDarkPower(darkPower(power));
	*/

	/* call it with functor
	* Blazer b(power);
	*useDarkPower(b);
	*/
}

//question 3
typedef struct trashChecker
{ bool isGarbage; 
unsigned bytesOK;
}trash;

template<class T>
trash atolstoy_v41(T object)
{/*undecidable*/;}

//note:  you can improve this one too :)
void assertionCases()
{
	int i;
	trash  result=atolstoy_v41(i);
	//basic case, garbage int
	assert(result.bytesOK==0 && result.isGarbage==true);
	char buff[10];
	buff[0]='t';
	buff[1]='r';
	result=atolstoy_v41(buff);
	//partly garbage char*
	assert(result.bytesOK==2 && result.isGarbage==true);
	buff[2]='\0';
	result=atolstoy_v41(buff);
	//since this is a real string, it's not recognized as garbage
	assert(result.bytesOK==sizeof(buff) && result.isGarbage==false);
	buff[3]='t';
	//still is a full string
	assert(result.bytesOK==sizeof(buff) && result.isGarbage==false);
	trash tricky;
	tricky.bytesOK=5;
	result=atolstoy_v41(tricky);
	assert(result.bytesOK==sizeof(unsigned) && result.isGarbage==true);
	int j=5;
	int k=3;
	int b=4;
	int a;
	std::vector<int> vec;
	vec.push_back(j);
	vec.push_back(k);
	vec.push_back(b);
	vec.push_back(a);
	result=atolstoy_v41(vec);
	assert(result.bytesOK==(sizeof((vec.size()-1)*sizeof(int))+sizeof(std::vector<int>)) && result.isGarbage==true);
}//ok fine, that last one was too crazy, i admit



//question 3b,Strong guarantee!



class WarMachine
{
	//nothrow dtor...
public:
	~WarMachine(){try{;}catch(...){;}}
	WarMachine & WarMachine::operator=(const WarMachine & rhs) 
	{
		WarMachine(rhs).swap(*this);
		return *this;
	}
	void WarMachine::swap(const WarMachine & other) 
	{
		//swap contents:
		//swap(...,...) and many other fields...;
		
	}
};

class Army
{
public:
	~Army(){try{;}catch(...){;}}
	Army & Army::operator=(const Army & rhs) 
	{
		Army(rhs).swap(*this);
		return *this;
	}
	void Army::swap(const Army & other) 
	{
		//swap contents:
		//swap(...,...) and many other fields...;
		
	}
};

//here's the function!
void takeOverTheWorld()
{
	//RAII - every resource has a container
	auto_ptr <Army>myBigInvincibleArmy(new Army);
	auto_ptr <WarMachine> ironMan(new WarMachine);
	//some code (not your business!!)
	auto_ptr <Army>anotherInvincibleArmy(new Army);
	//swap(inside classes)
	myBigInvincibleArmy=anotherInvincibleArmy;
}