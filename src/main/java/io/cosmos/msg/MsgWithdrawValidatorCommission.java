package io.cosmos.msg;

import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgWithdrawDelegatorRewardValue;
import io.cosmos.msg.utils.type.MsgWithdrawValidatorCommissionValue;

public class MsgWithdrawValidatorCommission extends MsgBase {

    public static void main(String[] args) {
        MsgWithdrawValidatorCommission msg = new MsgWithdrawValidatorCommission();
        msg.setMsgType("cosmos-sdk/MsgWithdrawValidatorCommission");
        msg.init("571af5d4eaf9a54f50110123979e5c1d461b7a1f2f48322da775621187cd0cc1");

        Message messages = msg.produceMsg("cosmosvaloper1az394pclywqpaktrnrlpfjal30d9re36gstk8d");

        msg.submit(messages,
                "stake",
                "6",
                "200000",
                "testchain",
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
