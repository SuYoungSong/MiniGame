package application.bluemarble;

import javafx.scene.image.Image;

public class Player {
    // 플레이어 닉네임, 자산, 현금, 도시 개수, 차례
    private String nickname;
    private int x, y;
    private int positionIndex;
    private long asset;
    private long money;
    private int[] cityNum;
    private int turn;
    private Image profileImgURI;



    Player(String nickaname, long money, Image profileImgURI){
        //유저 생성 시 가지고 있는 자산은 현금밖에 없기 때문에 자산과 현금에 같은 값 삽입
        this.nickname = nickaname;
        this.asset = money;
        this.money = money;
        this.profileImgURI = profileImgURI;
    }


    int positionIndex(){
        return positionIndex;
    }

    void setPositionIndex(int positionIndex){
        this.positionIndex = positionIndex;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int[] position(){
        //int[0] = x, int[1] = y
        return new int[] {x, y};
    }

    public String nickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long asset() {
        return asset;
    }

    public void setAsset(long asset) {
        this.asset = asset;
    }

    public long money() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
    public void refreshAsset() {
    	this.asset = money;	// 땅 가격 추가 해야함
    }
//    public int cityCnt() {
//        return cityCnt;
//    }

//    public void setCityCnt(int cityCnt) {
//        this.cityCnt = cityCnt;
//    }

    public Image profileImgURI() {
        return profileImgURI;
    }

    public void setProfileImgURI(Image profileImgURI) {
        this.profileImgURI = profileImgURI;
    }
}
