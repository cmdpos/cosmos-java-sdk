package io.cosmos.msg;

import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgSetWithdrawAddrValue;


public class MsgSetWithdrawAddress extends MsgBase {

    public static void main(String[] args) {
        MsgSetWithdrawAddress msg = new MsgSetWithdrawAddress();
        msg.setMsgType("cosmos-sdk/MsgModifyWithdrawAddress");
        msg.init("2c999c5afe7f0c902846e1b286fed29c5c5914998655d469568560955abe0d5d");

        Message messages = msg.produceMsg("cosmos1hg40dv5e237qy28vtyum52ygke32ez35hm307h");

        msg.submit(messages,
                "6",
                "200000",
                "cosmos set withdrawAddr");
    }

    public Message produceMsg(String withdrawAddr) {

        MsgSetWithdrawAddrValue value = new MsgSetWithdrawAddrValue();
        value.setWithdrawAddress(withdrawAddr);
        value.setDelegatorAddress(this.address);

        Message<MsgSetWithdrawAddrValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }

}
