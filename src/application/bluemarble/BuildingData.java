package application.bluemarble;

public class BuildingData{
	BluemarbleGameController bgc;

	int buyLand, buyVilla, buyBuilding, buyHotel;		// 땅 구매 비용
	int passLand, passVilla, passBuilding, passHotel;	// 땅 통행 비용

	public int buyLand() {
		return buyLand;
	}

	public int buyVilla() {
		return buyVilla;
	}

	public int buyBuilding() {
		return buyBuilding;
	}

	public int buyHotel() {
		return buyHotel;
	}

	public int passLand() {
		return passLand;
	}

	public int passVilla() {
		return passVilla;
	}

	public int passBuilding() {
		return passBuilding;
	}

	public int passHotel() {
		return passHotel;
	}

	void startPoint() {
		buyLand = 0;
		passLand = 0;
	}
	// 타이베이
	void Taibei() {
		buyLand = 50000;	buyVilla = 50000;	buyBuilding = 150000;	buyHotel = 250000;
		passLand = 2000;	passVilla = 10000;	passBuilding = 90000;	passHotel = 250000;
	}
	// 황금카드1번

	void goldCard1() {
		buyLand = 0;
		passLand = 0;
	}
	// 홍콩
	void HongKong() {
		buyLand = 80000;	buyVilla = 50000;	buyBuilding = 150000;	buyHotel = 250000;
		passLand = 4000;	passVilla = 20000;	passBuilding = 180000;	passHotel = 450000;
	}
	// 마닐라
	void Manila() {
		buyLand = 80000;	buyVilla = 50000;	buyBuilding = 150000;	buyHotel = 250000;
		passLand = 4000;	passVilla = 20000;	passBuilding = 180000;	passHotel = 450000;
	}
	//제주도
	void Jeju() {
		buyLand = 100000;
		passLand = 150000;
	}
	// 싱가포르
	void Singapore() {
		buyLand = 100000;	buyVilla = 50000;	buyBuilding = 150000;	buyHotel = 250000;
		passLand = 6000;	passVilla = 30000;	passBuilding = 180000;	passHotel = 450000;
	}

	// 황금카드2번
	void goldCard2() {
		buyLand = 0;
		passLand = 0;
	}
	// 카이로
	void Cairo() {
		buyLand = 100000;	buyVilla = 50000;	buyBuilding = 150000;	buyHotel = 250000;
		passLand = 6000;	passVilla = 30000;	passBuilding = 270000;	passHotel = 550000;
	}
	// 이스탄불
	void Istanbul() {
		buyLand = 120000;	buyVilla = 50000;	buyBuilding = 150000;	buyHotel = 250000;
		passLand = 8000;	passVilla = 40000;	passBuilding = 300000;	passHotel = 600000;
	}
	/*
	 * 2번라인
	 */
	// 무인도
	void isLand() {
		buyLand = 0;
		passLand = 0;
	}
	// 아테네
	void Athenae() {
		buyLand = 140000;	buyVilla = 100000;	buyBuilding = 300000;	buyHotel = 500000;
		passLand = 10000;	passVilla = 50000;	passBuilding = 450000;	passHotel = 750000;
	}
	// 황금카드3번
	void goldCard3() {
		buyLand = 0;
		passLand = 0;
	}
	// 코펜하겐
	void Copenhagen() {
		buyLand = 160000;	buyVilla = 100000;	buyBuilding = 300000;	buyHotel = 500000;
		passLand = 12000;	passVilla = 60000;	passBuilding = 500000;	passHotel = 900000;
	}
	// 스톡홀름
	void Stockholm() {
		buyLand = 160000;	buyVilla = 100000;	buyBuilding = 300000;	buyHotel = 500000;
		passLand = 12000;	passVilla = 60000;	passBuilding = 500000;	passHotel = 900000;
	}
	// 콩코드 여객기
	void Concorde() {
		buyLand = 300000;	buyVilla = 0;	buyBuilding = 0;	buyHotel = 0;
		passLand = 200000;	passVilla = 0;	passBuilding = 0;	passHotel = 0;
	}
	// 취리히
	void Zurich() {
		buyLand = 180000;	buyVilla = 100000;	buyBuilding = 300000;	buyHotel = 500000;
		passLand = 14000;	passVilla = 70000;	passBuilding = 550000;	passHotel = 950000;
	}
	// 황금카드4번
	void goldCard4() {
		buyLand = 0;
		passLand = 0;
	}
	// 베를린
	void Berlin() {
		buyLand = 160000;	buyVilla = 100000;	buyBuilding = 300000;	buyHotel = 500000;
		passLand = 12000;	passVilla = 70000;	passBuilding = 550000;	passHotel = 900000;
	}
	// 몬트리올
	void Montreal() {
		buyLand = 200000;	buyVilla = 100000;	buyBuilding = 300000;	buyHotel = 500000;
		passLand = 16000;	passVilla = 80000;	passBuilding = 600000;	passHotel = 1000000;
	}


	/*
	 * 3번라인
	 */
	// 사회복지기금
	void socialMoneyGet(){
		buyLand = 0;
		passLand = 0;
	}
	// 부에노스아이레스
	void BuenosAires() {
		buyLand = 220000;	buyVilla = 150000;	buyBuilding = 400000;	buyHotel = 750000;
		passLand = 18000;	passVilla = 90000;	passBuilding = 700000;	passHotel = 1050000;
	}
	// 황금카드5번
	void goldCard5() {
		buyLand = 0;
		passLand = 0;
	}
	// 상파울루
	void SaoPaulo() {
		buyLand = 240000;	buyVilla = 150000;	buyBuilding = 400000;	buyHotel = 750000;
		passLand = 20000;	passVilla = 100000;	passBuilding = 750000;	passHotel = 1100000;
	}
	// 시드니
	void Sydney() {
		buyLand = 240000;	buyVilla = 150000;	buyBuilding = 400000;	buyHotel = 750000;
		passLand = 20000;	passVilla = 100000;	passBuilding = 750000;	passHotel = 1100000;
	}
	// 부산
	void Busan() {
		buyLand = 500000;	buyVilla = 0;	buyBuilding = 0;	buyHotel = 0;
		passLand = 600000;	passVilla = 0;	passBuilding = 0;	passHotel = 0;
	}
	// 하와이
	void Hawaii() {
		buyLand = 260000;	buyVilla = 150000;	buyBuilding = 450000;	buyHotel = 750000;
		passLand = 22000;	passVilla = 110000;	passBuilding = 800000;	passHotel = 1150000;
	}
	// 리스본
	void Lisbon() {
		buyLand = 260000;	buyVilla = 150000;	buyBuilding = 450000;	buyHotel = 750000;
		passLand = 22000;	passVilla = 110000;	passBuilding = 800000;	passHotel = 1150000;
	}
	// 퀸엘리자베스호
	void QueenElizabeth() {
		buyLand = 300000;	buyVilla = 0;	buyBuilding = 0;	buyHotel = 0;
		passLand = 250000;	passVilla = 0;	passBuilding = 0;	passHotel = 0;
	}
	// 마드리드
	void Madrid() {
		buyLand = 280000;	buyVilla = 150000;	buyBuilding = 450000;	buyHotel = 750000;
		passLand = 24000;	passVilla = 120000;	passBuilding = 850000;	passHotel = 1200000;
	}
	/*
	 * 4번라인
	 */
	// 우주여행
	void spaceTraval() {
		buyLand = 0;
		passLand = 0;
	}
	// 도쿄
	void Tokyo() {
		buyLand = 300000;	buyVilla = 200000;	buyBuilding = 600000;	buyHotel = 1000000;
		passLand = 26000;	passVilla = 130000;	passBuilding = 900000;	passHotel = 1270000;
	}
	// 콜롬비아호
	void Colombia() {
		buyLand = 300000;	buyVilla = 0;	buyBuilding = 0;	buyHotel = 0;
		passLand = 26000;	passVilla = 0;	passBuilding = 0;	passHotel = 0;
	}
	// 파리
	void Paris() {
		buyLand = 320000;	buyVilla = 200000;	buyBuilding = 600000;	buyHotel = 1000000;
		passLand = 28000;	passVilla = 150000;	passBuilding = 1000000;	passHotel = 1400000;
	}
	// 로마
	void Roma() {
		buyLand = 320000;	buyVilla = 200000;	buyBuilding = 600000;	buyHotel = 1000000;
		passLand = 28000;	passVilla = 150000;	passBuilding = 1000000;	passHotel = 1400000;
	}
	// 황금카드6번
	void goldCard6() {
		buyLand = 0;
		passLand = 0;
	}
	// 런던
	void London() {
		buyLand = 350000;	buyVilla = 200000;	buyBuilding = 600000;	buyHotel = 1000000;
		passLand = 35000;	passVilla = 170000;	passBuilding = 1100000;	passHotel = 1500000;
	}
	// 뉴옥
	void NewYork() {
		buyLand = 350000;	buyVilla = 200000;	buyBuilding = 600000;	buyHotel = 1000000;
		passLand = 35000;	passVilla = 170000;	passBuilding = 1100000;	passHotel = 1500000;
	}
	// 서울
	void Seoul() {
		buyLand = 700000;
		passLand = 800000;
	}


	// ==================================================
	//                 땅 주인, 땅 구매 여부
	// ==================================================

	String TaibeiOwner, HongKongOwner, ManilaOwner, JejuOwner,	SingaporeOwner,
			CairoOwner, IstanbulOwner,	AthenaeOwner, CopenhagenOwner, StockholmOwner,
			ConcordeOwner,	ZurichOwner, BerlinOwner, MontrealOwner, BuenosAiresOwner,
			SaoPauloOwner, SydneyOwner, BusanOwner, HawaiiOwner, LisbonOwner,
			QueenElizabethOwner, MadridOwner, TokyoOwner, ColombiaOwner, ParisOwner,
			RomaOwner, LondonOwner, NewYorkOwner, SeoulOwner;
				
	/* 총 사용할 타입 종류 : 9가지(String)
	* 0(1) 0(2) 0(3) >> (1)빌라 구매여부, (2)빌딩 구매여부, (3)호텔 구매여부 
	* 000: 건축물이 존재하지 않을 때(토지만 구매했을 때)
	* 100: 빌라만 구매했을 때
	* 010: 빌딩만 구매했을 때
	* 001: 호텔만 구매했을 때
	* 011: 빌딩과 호텔을 구매했을 때
	* 110: 빌라와 빌딩만 구매했을 때
	* 101: 빌라와 호텔을 구매했을 때
	* 111: 빌라, 빌딩, 호텔을 구매했을 때
	*/

	String TaibeiType, HongKongType, ManilaType, JejuType,	SingaporeType,
			CairoType, IstanbulType, AthenaeType, CopenhagenType, StockholmType,
			ConcordeType, ZurichType, BerlinType, MontrealType, BuenosAiresType,
			SaoPauloType, SydneyType, BusanType, HawaiiType, LisbonType,
			QueenElizabethType, MadridType, TokyoType, ColombiaType, ParisType,
			RomaType, LondonType, NewYorkType, SeoulType;
	
	public String TaibeiOwner() {
		return TaibeiOwner;
	}

	public void setTaibeiOwner(String taibeiOwner) {
		TaibeiOwner = taibeiOwner;
	}

	public String HongKongOwner() {
		return HongKongOwner;
	}

	public void setHongKongOwner(String hongKongOwner) {
		HongKongOwner = hongKongOwner;
	}

	public String ManilaOwner() {
		return ManilaOwner;
	}

	public void setManilaOwner(String manilaOwner) {
		ManilaOwner = manilaOwner;
	}

	public String JejuOwner() {
		return JejuOwner;
	}

	public void setJejuOwner(String jejuOwner) {
		JejuOwner = jejuOwner;
	}

	public String SingaporeOwner() {
		return SingaporeOwner;
	}

	public void setSingaporeOwner(String singaporeOwner) {
		SingaporeOwner = singaporeOwner;
	}

	public String CairoOwner() {
		return CairoOwner;
	}

	public void setCairoOwner(String cairoOwner) {
		CairoOwner = cairoOwner;
	}

	public String IstanbulOwner() {
		return IstanbulOwner;
	}

	public void setIstanbulOwner(String istanbulOwner) {
		IstanbulOwner = istanbulOwner;
	}

	public String AthenaeOwner() {
		return AthenaeOwner;
	}

	public void setAthenaeOwner(String athenaeOwner) {
		AthenaeOwner = athenaeOwner;
	}

	public String CopenhagenOwner() {
		return CopenhagenOwner;
	}

	public void setCopenhagenOwner(String copenhagenOwner) {
		CopenhagenOwner = copenhagenOwner;
	}

	public String StockholmOwner() {
		return StockholmOwner;
	}

	public void setStockholmOwner(String stockholmOwner) {
		StockholmOwner = stockholmOwner;
	}

	public String ConcordeOwner() {
		return ConcordeOwner;
	}

	public void setConcordeOwner(String concordeOwner) {
		ConcordeOwner = concordeOwner;
	}

	public String ZurichOwner() {
		return ZurichOwner;
	}

	public void setZurichOwner(String zurichOwner) {
		ZurichOwner = zurichOwner;
	}

	public String BerlinOwner() {
		return BerlinOwner;
	}

	public void setBerlinOwner(String berlinOwner) {
		BerlinOwner = berlinOwner;
	}

	public String MontrealOwner() {
		return MontrealOwner;
	}

	public void setMontrealOwner(String montrealOwner) {
		MontrealOwner = montrealOwner;
	}

	public String BuenosAiresOwner() {
		return BuenosAiresOwner;
	}

	public void setBuenosAiresOwner(String buenosAiresOwner) {
		BuenosAiresOwner = buenosAiresOwner;
	}

	public String SaoPauloOwner() {
		return SaoPauloOwner;
	}

	public void setSaoPauloOwner(String saoPauloOwner) {
		SaoPauloOwner = saoPauloOwner;
	}

	public String SydneyOwner() {
		return SydneyOwner;
	}

	public void setSydneyOwner(String sydneyOwner) {
		SydneyOwner = sydneyOwner;
	}

	public String BusanOwner() {
		return BusanOwner;
	}

	public void setBusanOwner(String busanOwner) {
		BusanOwner = busanOwner;
	}

	public String HawaiiOwner() {
		return HawaiiOwner;
	}

	public void setHawaiiOwner(String hawaiiOwner) {
		HawaiiOwner = hawaiiOwner;
	}

	public String LisbonOwner() {
		return LisbonOwner;
	}

	public void setLisbonOwner(String lisbonOwner) {
		LisbonOwner = lisbonOwner;
	}

	public String QueenElizabethOwner() {
		return QueenElizabethOwner;
	}

	public void setQueenElizabethOwner(String queenElizabethOwner) {
		QueenElizabethOwner = queenElizabethOwner;
	}

	public String MadridOwner() {
		return MadridOwner;
	}

	public void setMadridOwner(String madridOwner) {
		MadridOwner = madridOwner;
	}

	public String TokyoOwner() {
		return TokyoOwner;
	}

	public void setTokyoOwner(String tokyoOwner) {
		TokyoOwner = tokyoOwner;
	}

	public String ColombiaOwner() {
		return ColombiaOwner;
	}

	public void setColombiaOwner(String colombiaOwner) {
		ColombiaOwner = colombiaOwner;
	}

	public String ParisOwner() {
		return ParisOwner;
	}

	public void setParisOwner(String parisOwner) {
		ParisOwner = parisOwner;
	}

	public String RomaOwner() {
		return RomaOwner;
	}

	public void setRomaOwner(String romaOwner) {
		RomaOwner = romaOwner;
	}

	public String LondonOwner() {
		return LondonOwner;
	}

	public void setLondonOwner(String londonOwner) {
		LondonOwner = londonOwner;
	}

	public String NewYorkOwner() {
		return NewYorkOwner;
	}

	public void setNewYorkOwner(String newYorkOwner) {
		NewYorkOwner = newYorkOwner;
	}

	public String SeoulOwner() {
		return SeoulOwner;
	}

	public void setSeoulOwner(String seoulOwner) {
		SeoulOwner = seoulOwner;
	}

	public String TaibeiType() {
		return TaibeiType;
	}

	public void setTaibeiType(String taibeiType) {
		TaibeiType = taibeiType;
	}

	public String HongKongType() {
		return HongKongType;
	}

	public void setHongKongType(String hongKongType) {
		HongKongType = hongKongType;
	}

	public String ManilaType() {
		return ManilaType;
	}

	public void setManilaType(String manilaType) {
		ManilaType = manilaType;
	}

	public String JejuType() {
		return JejuType;
	}

	public void setJejuType(String jejuType) {
		JejuType = jejuType;
	}

	public String SingaporeType() {
		return SingaporeType;
	}

	public String CairoType() {
		return CairoType;
	}

	public void setCairoType(String cairoType) {
		CairoType = cairoType;
	}

	public String IstanbulType() {
		return IstanbulType;
	}

	public void setIstanbulType(String istanbulType) {
		IstanbulType = istanbulType;
	}

	public String AthenaeType() {
		return AthenaeType;
	}

	public void setAthenaeType(String athenaeType) {
		AthenaeType = athenaeType;
	}

	public String CopenhagenType() {
		return CopenhagenType;
	}

	public void setCopenhagenType(String copenhagenType) {
		CopenhagenType = copenhagenType;
	}

	public String StockholmType() {
		return StockholmType;
	}

	public void setStockholmType(String stockholmType) {
		StockholmType = stockholmType;
	}

	public String ConcordeType() {
		return ConcordeType;
	}

	public void setConcordeType(String concordeType) {
		ConcordeType = concordeType;
	}

	public String ZurichType() {
		return ZurichType;
	}

	public void setZurichType(String zurichType) {
		ZurichType = zurichType;
	}

	public String BerlinType() {
		return BerlinType;
	}

	public void setBerlinType(String berlinType) {
		BerlinType = berlinType;
	}

	public String MontrealType() {
		return MontrealType;
	}

	public void setMontrealType(String montrealType) {
		MontrealType = montrealType;
	}

	public String BuenosAiresType() {
		return BuenosAiresType;
	}

	public void setBuenosAiresType(String buenosAiresType) {
		BuenosAiresType = buenosAiresType;
	}

	public String SaoPauloType() {
		return SaoPauloType;
	}

	public void setSaoPauloType(String saoPauloType) {
		SaoPauloType = saoPauloType;
	}

	public String SydneyType() {
		return SydneyType;
	}

	public void setSydneyType(String sydneyType) {
		SydneyType = sydneyType;
	}

	public String BusanType() {
		return BusanType;
	}

	public void setBusanType(String busanType) {
		BusanType = busanType;
	}

	public String HawaiiType() {
		return HawaiiType;
	}

	public void setHawaiiType(String hawaiiType) {
		HawaiiType = hawaiiType;
	}

	public String LisbonType() {
		return LisbonType;
	}

	public void setLisbonType(String lisbonType) {
		LisbonType = lisbonType;
	}

	public String QueenElizabethType() {
		return QueenElizabethType;
	}

	public void setQueenElizabethType(String queenElizabethType) {
		QueenElizabethType = queenElizabethType;
	}

	public String MadridType() {
		return MadridType;
	}

	public void setMadridType(String madridType) {
		MadridType = madridType;
	}

	public String TokyoType() {
		return TokyoType;
	}

	public void setTokyoType(String tokyoType) {
		TokyoType = tokyoType;
	}

	public String ColombiaType() {
		return ColombiaType;
	}

	public void setColombiaType(String colombiaType) {
		ColombiaType = colombiaType;
	}

	public String ParisType() {
		return ParisType;
	}

	public void setParisType(String parisType) {
		ParisType = parisType;
	}

	public String RomaType() {
		return RomaType;
	}

	public void setRomaType(String romaType) {
		RomaType = romaType;
	}

	public String LondonType() {
		return LondonType;
	}

	public void setLondonType(String londonType) {
		LondonType = londonType;
	}

	public String NewYorkType() {
		return NewYorkType;
	}

	public void setNewYorkType(String newYorkType) {
		NewYorkType = newYorkType;
	}

	public String SeoulType() {
		return SeoulType;
	}

	public void setSeoulType(String seoulType) {
		SeoulType = seoulType;
	}
}


//타이베이
//	땅 구매비용 : 대지료 5만, 호텔 25만, 빌딩 15만, 별장 5만
//	땅 통행비용 : 대지료 2천, 호텔 25만, 빌딩 9만, 별장 1만
//홍콩
//	땅 구매비용 : 대지료 8만, 호텔 25만, 빌딩 15만, 별장 5만
//	땅 통행비용 : 대지료 4천, 호텔 45만, 빌딩 18만, 별장 2만
//마닐라
//	땅 구매비용 : 대지료 8만, 호텔 25만, 빌딩 15만, 별장 5만
//	땅 통행비용 : 대지료 4천, 호텔 45만, 빌딩 18만, 별장 2만
//싱가포르
//	땅 구매비용 : 대지료 10만, 호텔 25만, 빌딩 15만, 별장 5만
//	땅 통행비용 : 대지료 6천, 호텔 55만, 빌딩 27만, 별장 3만
//카이로
//	땅 구매비용 : 대지료 10만, 호텔 25만, 빌딩 15만, 별장 5만
//	땅 통행비용 : 대지료 6천, 호텔 55만, 빌딩 27만, 별장 3만
//이스탄불
//	땅 구매비용 : 대지료 12만, 호텔 25만, 빌딩 15만, 별장 5만
//	땅 통행비용 : 대지료 8천, 호텔 60만, 빌딩 30만, 별장 4만
//
//
//아테네
//	땅 구매비용 : 대지료 14만, 호텔 50만, 빌딩 30만, 별장 10만
//	땅 통행비용 : 대지료 1만, 호텔 75만, 빌딩 45만, 별장 5만
//코펜하겐
//	땅 구매비용 : 대지료 16만, 호텔 50만, 빌딩 30만, 별장 10만
//	땅 통행비용 : 대지료 1만2천, 호텔 90만, 빌딩 50만, 별장 6만
//스톡홀름
//	땅 구매비용 : 대지료 16만, 호텔 50만, 빌딩 30만, 별장 10만
//	땅 통행비용 : 대지료 1만2천, 호텔 90만, 빌딩 50만, 별장 6만
//콩코드 여객기
//	땅 구매비용 : 30만
//	땅 통행비용 : 20만
//취리히
//	땅 구매비용 : 대지료 18만, 호텔 50만, 빌딩 30만, 별장 10만
//	땅 통행비용 : 대지료 1만4천, 호텔 95만, 빌딩 55만, 별장 7만
//베를린
//	땅 구매비용 : 대지료 16만, 호텔 50만, 빌딩 30만, 별장 10만
//	땅 통행비용 : 대지료 1만2천, 호텔 90만, 빌딩 50만, 별장 6만
//몬트리올
//	땅 구매비용 : 대지료 20만, 호텔 50만, 빌딩 30만, 별장 10만
//	땅 통행비용 : 대지료 1만6천, 호텔 100만, 빌딩 60만, 별장 8만
//
//
//부에노스아이레스
//	땅 구매비용 : 대지료 22만, 호텔 75만, 빌딩 40만, 별장 15만
//	땅 통행비용 : 대지료 1만8천, 호텔 105만, 빌딩 70만, 별장 9만
//상파울루
//	땅 구매비용 : 대지료 24만, 호텔 75만, 빌딩 45만, 별장 15만
//	땅 통행비용 : 대지료 2만, 호텔 110만, 빌딩 75만, 별장 10만
//시드니
//	땅 구매비용 : 대지료 24만, 호텔 75만, 빌딩 45만, 별장 15만
//	땅 통행비용 : 대지료 2만, 호텔 110만, 빌딩 75만, 별장 10만
//부산
//	땅 구매비용 : 50만
//	땅 통행비용 : 60만
//하와이
//	땅 구매비용 : 대지료 26만, 호텔 75만, 빌딩 45만, 별장 15만
//	땅 통행비용 : 대지료 2만2천, 호텔 115만, 빌딩 80만, 별장 11만
//리스본
//	땅 구매비용 : 대지료 26만, 호텔 75만, 빌딩 45만, 별장 15만
//	땅 통행비용 : 대지료 2만2천, 호텔 115만, 빌딩 80만, 별장 11만
//퀸엘리자베스호
//	땅 구매비용 : 30만
//	땅 통행비용 : 25만
//마드리드
//	땅 구매비용 : 대지료 28만, 호텔 75만, 빌딩 45만, 별장 15만
//	땅 통행비용 : 대지료 2만4천, 호텔 120만, 빌딩 85만, 별장 12만
//
//
//도쿄
//	땅 구매비용 : 대지료 30만, 호텔 100만, 빌딩 60만, 별장 20만
//	땅 통행비용 : 대지료 2만6천, 호텔 127만, 빌딩 90만, 별장 13만
//콜롬비아호
//	땅 구매비용 : 45만
//	땅 통행비용 : 30만
//파리
//	땅 구매비용 : 대지료 32만, 호텔 100만, 빌딩 60만, 별장 20만
//	땅 통행비용 : 대지료 2만8천, 호텔 140만, 빌딩 100만, 별장 15만
//로마
//	땅 구매비용 : 대지료 32만, 호텔 100만, 빌딩 60만, 별장 20만
//	땅 통행비용 : 대지료 2만8천, 호텔 140만, 빌딩 100만, 별장 15만
//런던
//	땅 구매비용 : 대지료 35만, 호텔 100만, 빌딩 60만, 별장 20만
//	땅 통행비용 : 대지료 3만5천, 호텔 150만, 빌딩 110만, 별장 17만
//뉴옥
//	땅 구매비용 : 대지료 35만, 호텔 100만, 빌딩 60만, 별장 20만
//	땅 통행비용 : 대지료 3만5천, 호텔 150만, 빌딩 110만, 별장 17만
//서울
//	땅 구매비용 : 대지료 70만, 
//	땅 통행비용 : 대지료 80만, 