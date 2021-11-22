package application.bluemarble;

public class Player {
    // 플레이어 닉네임, 자산, 현금, 도시 개수, 차례
    private String nickname;
    private long asset;
    private long money;
    private int cityCnt;
    private int turn;
    
    Player(){}
    Player(String nickaname, long money){
        //유저 생성 시 가지고 있는 자산은 현금밖에 없기 때문에 자산과 현금에 같은 값 삽입
        this.nickname = nickaname;
        this.asset = money;
        this.money = money;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getAsset() {
        return asset;
    }

    public void setAsset(long asset) {
        this.asset = asset;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getCityCnt() {
        return cityCnt;
    }

    public void setCityCnt(int cityCnt) {
        this.cityCnt = cityCnt;
    }
}
