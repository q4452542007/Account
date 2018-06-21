package mapsoft.com.account;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.UUID;

/**
 * @author djl
 * @function
 */

public class SalesBill extends DataSupport{

    //单号
    private UUID mId;
    //销售金额
    private double money;
    //住址
    private String location;
    //对此单销售的描述
    private String comment;
    //是否付款
    private boolean isPay;
    //购买人的姓名
    private String name;
    //日期
    private Date mDate;

    private String phoneNum;

    public SalesBill() {
        this(UUID.randomUUID());
    }

    public SalesBill(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}

