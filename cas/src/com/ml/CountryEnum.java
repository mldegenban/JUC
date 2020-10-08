package com.ml;

/**
 * @author mei0000
 * @date 2020/10/7 - 0:07
 */
public enum CountryEnum {
    ONE(1,"齐"),TWO(1,"楚"),THREE(1,"燕"),FOUR(1,"赵"),FIVE(1,"韩"),SIX(1,"魏");
    private Integer retCode;
    private String retMessage;


    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }
    CountryEnum(Integer retCode, String retMessage) {
        this.retCode=retCode;
        this.retMessage = retMessage;
    }
    public static CountryEnum forEach_CountryEnum(Integer index){
        CountryEnum[] arr = CountryEnum.values();
/*        for ( CountryEnum e : arr){
            if(index==e.getRetCode()){
                return e;
            }
        }
        return null;*/
        for (CountryEnum countryEnum : arr) {
            if(index==countryEnum.getRetCode()){
                return countryEnum;
            }
        }
        return  null;
    }

}
