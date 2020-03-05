package io.cosmos.msg;

import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgWithdrawDelegatorRewardValue;
import io.cosmos.msg.utils.type.MsgWithdrawValidatorCommissionValue;

public class MsgWithdrawValidatorCommission extends MsgBase {

    public static void main(String[] args) {
        MsgWithdrawValidatorCommission msg = new MsgWithdrawValidatorCommission();
        msg.setMsgType("cosmos-sdk/MsgWithdrawValidatorCommission");
        String mnemonic = "sentence deputy little switch fiction balcony hollow iron net index sound hollow scare attitude only cushion best candy wonder phone napkin sketch announce derive";
        msg.initMnemonic(mnemonic);

        Message messages = msg.produceMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083");

        msg.submit(messages,
                "6",
                "200000",
                "cosmos withdraw");
    }

    public Message produceMsg(String validatorAddr) {

        MsgWithdrawValidatorCommissionValue value = new MsgWithdrawValidatorCommissionValue();
        value.setValidatorAddress(validatorAddr);

        Message<MsgWithdrawValidatorCommissionValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
