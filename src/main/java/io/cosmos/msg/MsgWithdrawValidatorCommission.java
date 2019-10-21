package io.cosmos.msg;

import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgWithdrawDelegatorRewardValue;
import io.cosmos.msg.utils.type.MsgWithdrawValidatorCommissionValue;

public class MsgWithdrawValidatorCommission extends MsgBase {

    public static void main(String[] args) {
        MsgWithdrawValidatorCommission msg = new MsgWithdrawValidatorCommission();
        msg.setMsgType("cosmos-sdk/MsgWithdrawValidatorCommission");
        String mnemonic = "one neither they audit pen exile fire smart tongue express blanket burden culture shove curve address together pottery suggest lady sell clap seek whisper";
        msg.initMnemonic(mnemonic);

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
