package io.cosmos.msg;

import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgSetWithdrawAddrValue;
import io.cosmos.msg.utils.type.MsgWithdrawDelegatorRewardValue;

public class MsgWithdrawDelegatorReward extends MsgBase {

    public static void main(String[] args) {
        MsgWithdrawDelegatorReward msg = new MsgWithdrawDelegatorReward();
        msg.setMsgType("cosmos-sdk/MsgWithdrawDelegationReward");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceMsg("cosmosvaloper1y5cj26cexle8mrpxfksnly2djzxx79zq2mf083");

        msg.submit(messages,
                "stake",
                "6",
                "200000",
                "testchain",
                "cosmos set withdrawAddr");
    }

    public Message produceMsg(String validatorAddr) {

        MsgWithdrawDelegatorRewardValue value = new MsgWithdrawDelegatorRewardValue();
        value.setValidatorAddress(validatorAddr);
        value.setDelegatorAddress(this.address);

        Message<MsgWithdrawDelegatorRewardValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
