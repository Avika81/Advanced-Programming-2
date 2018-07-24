#include <iostream>
#include <climits>
#include <Bits.h>
#include <vector>
#include <string>
using namespace std;

//question 1

class Beast
{

public:
	enum type{
		tiger,volture,snake,scorpion,roc,shark,falcon,dragon
	};

private:
	string m_name;
	int m_age;
	int might;
	type m_type;//should not change

public:
	Beast(){};
	Beast(string name,int age,type ty)
	{
		m_name=name;
		m_age=age;
		m_type=ty;
		might=0;
	}

	Beast(Beast & beast) //coffee constructor
	{
		m_name=beast.m_name;
		m_age=beast.m_age;
		m_type=beast.m_type;
		might=0;
	}

	void attack(){};

	void spy(){};

	void setName(string name){m_name=name;};

	string getName(){return m_name;};

	int getMight(){ return might;}

	void growUp(){++m_age;}

	void getStronger(){++might;}
};

class StrongHold{
private:
	string m_name;
public:
	StrongHold(){};
	StrongHold(string name){m_name=name;}
	//cappuchino constructor(an improvement on the coffee's part)
	StrongHold(StrongHold & strongHold){m_name=strongHold.m_name;}
};

class Darklord
{
protected:
	string m_alias;//that's how the common people speak out the darklord's horrible name...
	string m_mastery;//darklord's special mastery, effects his useDarkPower Method. might be "Inferno","NightMares" "WitchCraft" or "BeastMastery"
	int power;//always positive, weak lords aren't dark
	StrongHold *strongHold;
	Beast *BEAST;
	vector<Darklord> colleagues;

public: 
	Darklord(){};
	Darklord(string alias,string mastery,int pow,StrongHold & stronghol,Beast & beast)
	{
		m_alias=alias;
		m_mastery=mastery;
		power=pow;
		*strongHold=stronghol;
		*BEAST=beast;
	};

	string getName(){return m_alias;}
	
	int getPower(){return power;}
	
	void asencd(){++power;}
	
	void setMastery(int mastery){m_mastery=mastery;}
	
	StrongHold & getHold(){return *strongHold;}
	
	Beast & getBeast(){return *BEAST;}
	
	void setStrongHold(StrongHold & hold){delete strongHold; strongHold=new StrongHold(hold);}
	
	void setBeast(Beast beast){delete BEAST; BEAST=new Beast(beast);}
	
	void Conquer(Darklord rival);
	
	virtual int useDarkPower();
	
	virtual void laughOutLoud(){cout<<"mwhahahahahahah!"<<endl;};
	
	void trainBeast(){BEAST->getStronger();}
	
	virtual void becomeTheHeroEveryoneWasExpectiongForAndSaveTheWorldFromitsOwnStupidSelf(){};
	
	void addColleague(Darklord darkFriend){colleagues.push_back(darkFriend);}

private:
	int blaze(){cout<<"only when the flames of hell will sear this wretched earth, shall i be at peace"<<endl; return 3*power; }
	int horrify(){cout<<"never again shall your mind be safe from the horrors of the abyss"<<endl; return (2*power)+15; }
	int castSpell(){cout<<"Come to my side, forces of shadow and bleakness, cast an unholy blight upon this oblivious wretch!"<<endl; return power*power; }
	int unleashHorde(){cout<<"on your feet, dogs of plight! slash,gash, and break the bones of my foes!"<<endl; return power*BEAST->getMight(); }
};

//question 1 b
//if this function return zero, it might be function failure, and "darkness failed!!" should be printed
int Darklord::useDarkPower()
{
	int casualties;
	if(m_mastery.compare("Inferno")==0)
		casualties=blaze();
	if(m_mastery.compare("NightMares")==0)
		casualties=horrify();
	if(m_mastery.compare("WitchCraft")==0)
		casualties=castSpell();
	if(m_mastery.compare("BeastMastery")==0)
		casualties=unleashHorde();
	return casualties;
}


//question 3

//some useful classes...
class WarMachine
{
};

class Army
{

};

//here's the function!
void takeOverTheWorld()
{
	Army* myBigInvincibleArmy=new Army();
	WarMachine* ironMan=new WarMachine();

	//some code (not your business!!)
	try
	{
		delete ironMan;//MUHAHAHAHA!
		Army* anotherInvincibleArmy= new Army;
		myBigInvincibleArmy=anotherInvincibleArmy;
	}
	catch(...)
	{
		delete myBigInvincibleArmy;
		delete ironMan;
	}
}

//question 7
template<int N>
class Vicious
{
public:	static const int val= Vicious<N - 1>::val + Vicious<N-2>::val;
};

template <>
class Vicious<1> 
{
public: static const int val = 1;
};

template <>
class Vicious<0>
{
public: static const int val = 1;
};


void veryVicious()
{
	
	std::cout << Vicious<10>::val << std::endl;
}


